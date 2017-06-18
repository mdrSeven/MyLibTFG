/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.SelectRefundsWindow;
import static Interfaz.SelectRefundsWindow.SelectRefundsConstants.*;
import Objects.Refund;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pablo
 */
public class SelectRefundsEvents implements ActionListener {

    int idElement;

    public SelectRefundsEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (idElement) {
            case SEARCH_BUTTON:
                searchBill();
                break;
            case SHOW_ALL_BUTTON:
                SelectRefundsWindow.poblateTable();
                break;
            case CANCEL_BUTTON:
                JButton btn = (JButton)ae.getSource();
                SwingUtilities.getWindowAncestor(btn).dispose();
                break;
        }
    }

    private void searchBill() {
        try {
            int refundNum = Integer.parseInt(SelectRefundsWindow.refundNumberText.getText());
            Refund refundToSearch = JsonHelper.searchRefund(refundNum);
            if (refundToSearch == null) {
                JOptionPane.showMessageDialog(null, "No se ha encontrado la devolución", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                SelectRefundsWindow.poblateTable(refundToSearch);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Los parámetros de búsqueda no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
