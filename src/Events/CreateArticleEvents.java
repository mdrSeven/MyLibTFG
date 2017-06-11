/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.CreateArticleWindow;
import static Interfaz.CreateArticleWindow.CreateArticleConstants.*;
import Objects.Book;
import static Utils.Constants.defaultIvaPercentage;
import Utils.Validators;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class CreateArticleEvents implements FocusListener, ActionListener {

    int idElement;

    public CreateArticleEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void focusGained(FocusEvent fe) {

    }

    @Override
    public void focusLost(FocusEvent fe) {
        if (idElement == PRICE_TEXT) {
            if (!Validators.validateDouble(CreateArticleWindow.priceText.getText())) {
                CreateArticleWindow.priceText.setText("");
                JOptionPane.showMessageDialog(null, "Ese precio no puede ser establecido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if(idElement == CODE_TEXT) {
            if (!Validators.validateIsbn13(CreateArticleWindow.codeText.getText())) {
                CreateArticleWindow.codeText.setText("");
                JOptionPane.showMessageDialog(null, "Ese ISBN no es correcto", "Error", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (idElement == CREATE_BUTTON) {
            Book bookToCreate = getBook();

            if (bookToCreate.isValid()) {
                try {
                    JsonHelper.insertBook(bookToCreate);
                } catch (IOException ex) {
                    Logger.getLogger(CreateArticleEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Algunos de los datos introducidos no son válidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Book getBook() {
        String code = CreateArticleWindow.codeText.getText();
        int amount = (Integer)CreateArticleWindow.amountSpinner.getValue();
        int ivaPercentage = defaultIvaPercentage;
        double price = Double.parseDouble(CreateArticleWindow.priceText.getText());
        String name = CreateArticleWindow.nameText.getText();
        String details = CreateArticleWindow.descriptionTextArea.getText();

        return new Book(code,amount,ivaPercentage, price, name, details);
    }

}
