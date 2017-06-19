/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.EditArticle;
import Interfaz.EditClient;
import Interfaz.SelectArticles;
import Objects.Book;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class SelectArticleEvents implements ActionListener, MouseListener{

    
    public SelectArticleEvents(){

    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        String codeToSearch = SelectArticles.codeText.getText();
        
        try {
            Book searchedBook = JsonHelper.searchBook(codeToSearch);
            
            if(searchedBook != null){
                SelectArticles.poblateTable(searchedBook);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SelectArticleEvents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(me.getClickCount() == 2){
            JTable table = (JTable)me.getSource();
            int row = table.getSelectedRow();
            
            new EditArticle();
            EditArticle.codeText.setText((String)(table.getValueAt(row, 0)));
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
