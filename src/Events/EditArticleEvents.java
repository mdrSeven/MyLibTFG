/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.EditArticle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import static Interfaz.EditArticle.EditArticleConstants.*;
import Objects.Book;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class EditArticleEvents implements ActionListener, FocusListener {

    int idElement;

    public EditArticleEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (idElement) {
            case SEARCH_BUTTON: {
                try {
                    searchArticle();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(EditArticleEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case SAVE_BUTTON: {
                try {
                    saveArticle();
                } catch (IOException ex) {
                    Logger.getLogger(EditArticleEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case CANCEL_BUTTON:
                EditArticle.togglePanel(false);
                break;
        }
    }

    @Override
    public void focusGained(FocusEvent fe) {

    }

    @Override
    public void focusLost(FocusEvent fe) {

    }

    private void searchArticle() throws FileNotFoundException {
        String searchedCode = EditArticle.codeText.getText();

        Book searchedBook = JsonHelper.searchBook(searchedCode);
        if (searchedBook == null) {
            JOptionPane.showMessageDialog(null, "Artículo no encontrado", "Error en la búsqueda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        fillBookData(searchedBook);
    }

    private void fillBookData(Book searchedBook) {
        EditArticle.nameText.setText(searchedBook.getName());
        EditArticle.amountText.setText("" + searchedBook.getAmount());
        EditArticle.priceText.setText("" + searchedBook.getPrice());
        EditArticle.ivaText.setText("" + searchedBook.getIvaPercentage());
        EditArticle.descriptionText.setText(searchedBook.getDetails());

        EditArticle.togglePanel(true);
    }

    private void saveArticle() throws IOException {
        Book bookToSave = getBook();

        if (bookToSave.isValid()) {
            JsonHelper.editBook(bookToSave);
        } else {
            JOptionPane.showMessageDialog(null, "Los valores introducidos no son válidos, revíselos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Book getBook() {
        String code = EditArticle.codeText.getText();
        int ivaPercentage = EditArticle.ivaText.getText().isEmpty() ? 0 : Integer.parseInt(EditArticle.ivaText.getText());
        int amount = EditArticle.amountText.getText().isEmpty() ? -1 : Integer.parseInt(EditArticle.amountText.getText());
        double price = EditArticle.priceText.getText().isEmpty() ? -1 : Double.parseDouble(EditArticle.priceText.getText());
        String name = EditArticle.nameText.getText();
        String details = EditArticle.descriptionText.getText();

        return new Book(code, amount, ivaPercentage, price, name, details);
    }

}
