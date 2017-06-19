/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.MainWindow;
import Interfaz.SelectBillWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Interfaz.SelectBillWindow.SelectBillConstants.*;
import Objects.Bill;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class SelectBillEvents implements ActionListener, MouseListener {

    int idElement;

    public SelectBillEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (idElement) {
            case SEARCH_BUTTON:
                searchBill();
                JButton btn = (JButton)ae.getSource();
                SwingUtilities.getWindowAncestor(btn).pack();
                break;
            case SHOW_ALL_BUTTON:
                SelectBillWindow.poblateTable();
                JButton btn2 = (JButton)ae.getSource();
                SwingUtilities.getWindowAncestor(btn2).pack();
                break;

        }
    }

    private void searchBill() {
        try{
            int billNum = Integer.parseInt(SelectBillWindow.billNumberText.getText());
            Bill billToSearch = JsonHelper.searchBill(billNum);
            SelectBillWindow.poblateTable(billToSearch);
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Los parámetros de búsqueda no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getClickCount() == 2){
            JTable table = (JTable)me.getSource();
            int row = table.getSelectedRow();
            Bill b = JsonHelper.searchBill((Integer)(table.getValueAt(row, 0)));
            try {
                MainWindow.isRefund = true;
                MainWindow.poblateTable(b.getProducts());
                MainWindow.discountText.setText(""+b.getDiscount());
                MainWindow.updateAmounts();
                MainWindow.assignedClient = b.getAssignedClient();
                MainWindow.selectedClientLabel.setText(b.getAssignedClient() == null ? "Sin cliente" : b.getAssignedClient().getDni() + ", "+b.getAssignedClient().getName());
                MainWindow.toggleButtons(true);
                MainWindow.billNumberLabel.setText("Número de factura a devolver: ");
                MainWindow.billNumberText.setText(""+b.getBillNumber());
                MainWindow.finishBillButton.setText("Finalizar Devolución");
                MainWindow.selectClientButton.setEnabled(false);
                MainWindow.discountText.setEditable(false);
                MainWindow.addArticleButton.setEnabled(false);
            } catch (FileNotFoundException ex) {
                
            }
            SwingUtilities.getWindowAncestor(table).dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
