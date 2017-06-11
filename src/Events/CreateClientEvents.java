package Events;

import Helpers.JsonHelper;
import Interfaz.CreateClientWindow;
import static Interfaz.CreateClientWindow.CreateClientConstants.*;
import Objects.Client;
import Utils.Validators;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static Utils.Validators.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CreateClientEvents implements ActionListener {

    int idElement;

    public CreateClientEvents(int id) {
        this.idElement = id;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (idElement) {
            case SAVE_BUTTON:
                try {
                    createClient();
                } catch (IOException ex) {
                    Logger.getLogger(CreateClientEvents.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case CANCEL_BUTTON:
                break;
        }
    }

    private void createClient() throws IOException {
        Client client = getClient();

        if (!Validators.validateDNI(client.getDni())) {
            JOptionPane.showMessageDialog(null, "DNI no válido", "Error de DNI", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validators.validateEmail(client.getEmail())) {
            JOptionPane.showMessageDialog(null, "Email no válido", "Error de Email", JOptionPane.ERROR_MESSAGE);
            return;
        }

        new CreateClientThread(client);
    }

    public static Client getClient() {
        String name = CreateClientWindow.nameText.getText();
        String dni = CreateClientWindow.nifText.getText();
        String address = CreateClientWindow.addressText.getText();
        String poblation = CreateClientWindow.poblationText.getText();
        String province = CreateClientWindow.provinceText.getText();
        String email = CreateClientWindow.emailText.getText();
        int phone = Integer.parseInt(CreateClientWindow.phoneText.getText());

        return new Client(name, dni, address, poblation, province, email, phone);
    }

    private class CreateClientThread implements Runnable {

        Thread t;
        Client client;

        public CreateClientThread(Client client) {
            this.client = client;
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            try {
                JsonHelper.insertClient(client);
            } catch (IOException ex) {
            }
        }

    }

}
