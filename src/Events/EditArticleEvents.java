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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class EditArticleEvents implements ActionListener, FocusListener{
    
    int idElement;
    
    public EditArticleEvents(int id){
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(idElement){
            case SEARCH_BUTTON:
        {
            try {
                searchArticle();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EditArticleEvents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        EditArticle.amountText.setText(""+searchedBook.getAmount());
        EditArticle.priceText.setText(""+searchedBook.getPrice());
        EditArticle.ivaText.setText(""+searchedBook.getIvaPercentage());
        EditArticle.descriptionText.setText(searchedBook.getDetails());
        
        EditArticle.togglePanel(true);
    }
    
}
