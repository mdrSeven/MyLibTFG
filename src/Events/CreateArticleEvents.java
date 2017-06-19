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
import com.sun.media.jfxmediaimpl.MediaDisposer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class CreateArticleEvents implements ActionListener, MediaDisposer.Disposable {

    int idElement;

    public CreateArticleEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (idElement == CREATE_BUTTON) {
            Book bookToCreate = getBook();

            if (bookToCreate.isValid()) {

                try {
                    if (JsonHelper.insertBook(bookToCreate)) {
                        JButton btn = (JButton) ae.getSource();
                        JFrame jf = (JFrame) SwingUtilities.getRoot(btn);
                        jf.dispose();
                        dispose();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(CreateArticleEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    

    private Book getBook() {
        String code = CreateArticleWindow.codeText.getText();
        int amount = (Integer) CreateArticleWindow.amountSpinner.getValue();
        int ivaPercentage = defaultIvaPercentage;
        double price = Validators.validateNumber(CreateArticleWindow.priceText.getText()) ? Double.parseDouble(CreateArticleWindow.priceText.getText()) : -1;
        String name = CreateArticleWindow.nameText.getText();
        String details = CreateArticleWindow.descriptionTextArea.getText();

        return new Book(code, amount, ivaPercentage, price, name, details);
    }

    @Override
    public void dispose() {
    }

}
