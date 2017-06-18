package Events;

import Helpers.JsonHelper;
import Interfaz.*;
import static Interfaz.MainWindow.MainWindowConstants.*;
import Objects.Bill;
import Objects.Book;
import Objects.Product;
import Objects.Refund;
import Utils.SettingsConfig;
import Utils.Validators;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class MainWindowEvents implements ActionListener, KeyListener {

    int idElement;

    public MainWindowEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (idElement) {
            case NEW_BILL_ITEM:
                newBill(ae);
                break;
            case NEW_REFUND_ITEM:
                createRefund();
                break;
            case NEW_ARTICLE_ITEM: {
                try {
                    new CreateArticleWindow();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainWindowEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case NEW_CLIENT_ITEM:
                new CreateClientWindow();
                break;
            case EDIT_ARTICLE_ITEM:
                new EditArticle();
                break;
            case EDIT_CLIENT_ITEM:
                new EditClient();
                break;
            case QUERY_BILLS_ITEM:
                new SelectBillWindow();
                break;
            case QUERY_REFUND_ITEM:
                new SelectRefundsWindow();
                break;
            case QUERY_ARTICLES_ITEM: {
                try {
                    new SelectArticles();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainWindowEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case PREFERENCES_ITEM:
                new SettingsWindow();
                break;
            case ADD_ARTICLE_BUTTON: {
                try {
                    addArticle();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainWindowEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case REMOVE_ARTICLE_BUTTON:
                break;
            case REMOVE_ROW_BUTTON:
                MainWindow.removeRow();
                MainWindow.updateAmounts();
                break;
            case FINISH_BILL_BUTTON: {
                try {
                    if (!MainWindow.isRefund) {
                        if (finishBill()) {
                            JButton btn = (JButton) ae.getSource();
                            SwingUtilities.getWindowAncestor(btn).dispose();
                            new MainWindow();
                        }
                    } else {
                        if (finishRefund()) {
                            JButton btn = (JButton) ae.getSource();
                            SwingUtilities.getWindowAncestor(btn).dispose();
                            new MainWindow();
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case SELECT_CLIENT_BUTTON:
                assignClient();
                break;
        }
    }

    private void addArticle() throws FileNotFoundException {
        String searchedCode = MainWindow.codeText.getText();

        Book searchedBook = JsonHelper.searchBook(searchedCode);
        if (searchedBook != null) {
            MainWindow.addBookToBill(searchedBook);
            MainWindow.billNumberText.setText("" + JsonHelper.getCurrentBillNumber());
            MainWindow.toggleButtons(true);
            MainWindow.finishBillButton.setText("Finalizar Venta");
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(null, "Artículo no encontrado", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void updateTotal() {
        MainWindow.updateAmounts();
    }

    private boolean finishBill() throws IOException {
        int reply = JOptionPane.showConfirmDialog(null, "¿Desea finalizar la factura?", "Aviso", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            ArrayList<Product> products = MainWindow.getProducts();
            if (validateBill(products)) {
                Bill createdBill = new Bill(Integer.parseInt(MainWindow.billNumberText.getText()), Integer.parseInt(MainWindow.discountText.getText().isEmpty() ? "0" : MainWindow.discountText.getText()),
                        products, Double.parseDouble(MainWindow.subTotalText.getText().replaceAll(",", ".")),
                        Double.parseDouble(MainWindow.totalText.getText().replaceAll(",", ".")),
                        MainWindow.assignedClient);
                JsonHelper.insertBill(createdBill);
                if (SettingsConfig.stockWarning) {
                    String[] codes = JsonHelper.getBooksWithLowStock();
                    if (codes != null) {
                        JOptionPane.showMessageDialog(null, "Los artículos con código [ " + String.join(",", codes) + " ] tienen un stock muy bajo!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                return true;
            }
            return false;
        }

        return false;
    }

    private boolean validateBill(ArrayList<Product> products) throws FileNotFoundException, IOException {
        ArrayList<Book> booksToUpdate = new ArrayList();
        for (Product product : products) {
            Book b = JsonHelper.searchBook(product.getCode());
            if ((b.getAmount() - product.getAmount()) < 0) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock del producto '" + b.getName() + "'", "Error!", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                b.setAmount(b.getAmount() - product.getAmount());
                booksToUpdate.add(b);
            }
        }
        for (Book b : booksToUpdate) {
            JsonHelper.updateStock(b);
        }
        return true;
    }

    private void assignClient() {
        new SelectClients();
    }

    public void newBill(ActionEvent ae) {
        JMenuItem menuItem = (JMenuItem) ae.getSource();
        JPopupMenu popUp = (JPopupMenu) menuItem.getParent();
        Component invoker = popUp.getInvoker();
        JMenuBar menuBar = (JMenuBar) invoker.getParent();
        SwingUtilities.getWindowAncestor(menuBar).dispose();

        new MainWindow();
    }

    private void createRefund() {
        MainWindow.isRefund = true;
        new SelectBillWindow();
    }

    private boolean finishRefund() throws IOException {
        int reply = JOptionPane.showConfirmDialog(null, "¿Desea finalizar la devolución?", "Aviso", JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.YES_OPTION) {
            Bill originalBill = JsonHelper.searchBill(Integer.parseInt(MainWindow.billNumberText.getText()));
            ArrayList<Product> refundedProducts = getRefundedProducts(originalBill);
            if (refundedProducts.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se ha realizado la devolución de ningun producto", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                //Creación e inserción de nueva devolución.
                Refund ref = new Refund(JsonHelper.getCurrentRefundNumber(), originalBill.getBillNumber(),
                        Double.parseDouble(MainWindow.subTotalText.getText().replaceAll(",", ".")), 
                        Double.parseDouble(MainWindow.totalText.getText().replaceAll(",", ".")),
                        refundedProducts);
                JsonHelper.insertRefund(ref);

                //Inserción de factura a partir de la devolución
                Bill bill = new Bill(JsonHelper.getCurrentBillNumber(), originalBill.getDiscount(),
                        MainWindow.getProducts(), ref.getSubTotal(), ref.getTotal(), originalBill.getAssignedClient());
                JsonHelper.insertBill(bill);

                //Actualización del stock
                ArrayList<Book> booksToUpdate = new ArrayList();
                for (Product product : refundedProducts) {
                    Book b = JsonHelper.searchBook(product.getCode());
                    b.setAmount(b.getAmount() + product.getAmount());
                    booksToUpdate.add(b);
                }
                for (Book b : booksToUpdate) {
                    JsonHelper.updateStock(b);
                }
                return true;
            }

        }
        
        return false;
    }


    private ArrayList<Product> getRefundedProducts(Bill originalBill) {
        ArrayList<Product> actualProducts = MainWindow.getProducts();
        ArrayList<Product> refundedProducts = new ArrayList<>();

        for (Product p : originalBill.getProducts()) {

            for (int n = 0; n < actualProducts.size(); n++) {
                Product pa = actualProducts.get(n);
                if (p.getCode().equals(pa.getCode()) && p.getAmount() != pa.getAmount()) {
                    p.setAmount(p.getAmount() - pa.getAmount());
                    refundedProducts.add(p);
                    break;
                }
                if (n == actualProducts.size() - 1 && !p.getCode().equals(pa.getCode())) {
                    refundedProducts.add(p);
                }
            }
        }

        return refundedProducts;
    }

    @Override
        public void keyTyped(KeyEvent ke) {

    }

    @Override
        public void keyPressed(KeyEvent ke) {

    }

    @Override
        public void keyReleased(KeyEvent ke) {
        String discount = MainWindow.discountText.getText();
        if (Validators.validateNumber(discount)) {
            double discountNumber = Double.parseDouble(discount);
            if (discountNumber < 100) {
                MainWindow.updateAmounts();
                double currentTotal = Double.parseDouble(MainWindow.totalText.getText().replaceAll(",", "."));
                double totalResult = currentTotal - ((currentTotal * discountNumber) / 100);
                MainWindow.totalText.setText(String.format("%.2f", totalResult));
            } else {
                ke.consume();
            }
        } else if (discount.isEmpty()) {
            MainWindow.updateAmounts();
        }
    }

}
