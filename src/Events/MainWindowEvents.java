/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.*;
import static Interfaz.MainWindow.MainWindowConstants.*;
import Objects.Book;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class MainWindowEvents implements ActionListener{

    int idElement;
    
    public MainWindowEvents(int id){
        this.idElement = id;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch(idElement){
            case NEW_BILL_ITEM:
                break;
            case NEW_REFUND_ITEM:
                break;
            case NEW_ARTICLE_ITEM:
        {
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
        {
            try {
                new CreateArticleWindow();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindowEvents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case EDIT_CLIENT_ITEM:
                new EditClient();
                break;
            case QUERY_BILLS_ITEM:
                new SelectBillWindow();
                break;
            case QUERY_REFUND_ITEM:
                break;
            case QUERY_ARTICLES_ITEM:
                new SelectArticles();
                break;
            case PREFERENCES_ITEM:
                new SettingsWindow();
                break;
            case ADD_ARTICLE_BUTTON:
        {
            try {
                addArticle();
                updateTotal();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindowEvents.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                break;
            case REMOVE_ARTICLE_BUTTON:
                break;
            case REMOVE_ROW_BUTTON:
                MainWindow.removeRow();
                break;
            case FINISH_BILL_BUTTON:
                break;
        }
    }

    private void addArticle() throws FileNotFoundException {
        String searchedCode = MainWindow.codeText.getText();
        
        Book searchedBook = JsonHelper.searchBook(searchedCode);
        if(searchedBook != null){
            MainWindow.addBookToBill(searchedBook);
        }
    }

    private void updateTotal() {
        MainWindow.updateAmounts();
    }
    
}
