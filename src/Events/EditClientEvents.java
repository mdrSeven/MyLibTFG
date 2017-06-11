/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.JsonHelper;
import Interfaz.EditClient;
import static Interfaz.EditClient.EditClientConstants.*;
import Objects.Client;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class EditClientEvents implements ActionListener {

    int idElement;

    public EditClientEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (idElement) {
            case SEARCH_BUTTON:
                searchClient();
                break;
            case SAVE_BUTTON:
                try {
                    saveClient();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case DELETE_BUTTON: {
                try {
                    deleteClient();
                } catch (IOException ex) {
                    Logger.getLogger(EditClientEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
            case CANCEL_BUTTON:
                EditClient.togglePanel(false);
                EditClient.cleanPanel();
            break;
        }
    }

    private void searchClient() {
        String searchedDNI = EditClient.nifText.getText();

        Client searchedClient = JsonHelper.searchClient(searchedDNI);
        if (searchedClient == null) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error en la búsqueda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        fillClientData(searchedClient);
    }

    private void fillClientData(Client client) {
        EditClient.nameText.setText(client.getName());
        EditClient.cNifText.setText(client.getDni());
        EditClient.addressText.setText(client.getAddress());
        EditClient.poblationText.setText(client.getPoblation());
        EditClient.provinceText.setText(client.getProvince());
        EditClient.emailText.setText(client.getEmail());
        EditClient.phoneText.setText("" + client.getPhone());
        EditClient.togglePanel(true);
    }

    private void saveClient() throws IOException {
        Client clientToSave = getClient();

        System.out.println("Entrado");
        JsonHelper.editClient(clientToSave);
    }

    private Client getClient() {
        String name = EditClient.nameText.getText();
        String dni = EditClient.cNifText.getText();
        String address = EditClient.addressText.getText();
        String poblation = EditClient.poblationText.getText();
        String province = EditClient.provinceText.getText();
        String email = EditClient.emailText.getText();
        int phone = Integer.parseInt(EditClient.phoneText.getText());

        return new Client(name, dni, address, poblation, province, email, phone);
    }

    private void deleteClient() throws IOException {
        Client clientToDelete = getClient();
        JsonHelper.removeClient(clientToDelete, false);
    }

}
