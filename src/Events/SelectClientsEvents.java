/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.MainWindow;
import Interfaz.SelectClients;
import Objects.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class SelectClientsEvents implements ActionListener, MouseListener {

    @Override
    public void actionPerformed(ActionEvent ae) {
        String dniToSearch = SelectClients.nifText.getText();

        Client clientSearched = JsonHelper.searchClient(dniToSearch);
        if (clientSearched != null) {
            SelectClients.poblateTable(clientSearched);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            Client c = JsonHelper.searchClient((String) target.getValueAt(row, 0));
            MainWindow.assignedClient = c;
            MainWindow.selectedClientLabel.setText(c.getDni() + ", " + c.getName());
            SwingUtilities.getWindowAncestor(target).dispose();
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
