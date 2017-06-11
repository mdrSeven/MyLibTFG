/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.SelectClients;
import Objects.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class SelectClientsEvents implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        String dniToSearch = SelectClients.nifText.getText();
        
        Client clientSearched = JsonHelper.searchClient(dniToSearch);
        if(clientSearched != null)
            SelectClients.poblateTable(clientSearched);
        else
            JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
    }
    
}
