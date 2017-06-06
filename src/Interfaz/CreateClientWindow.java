
package Interfaz;

import javax.swing.*;
import static Helpers.InterfaceHelper.*;
import java.awt.FlowLayout;
import static javax.swing.BoxLayout.Y_AXIS;

/**
 *
 * @author Pablo
 */
public class CreateClientWindow extends JFrame{
    //JPanel - Paneles principales
    JPanel firstPanel = new JPanel();
    JPanel secondPanel = new JPanel();
    
    JLabel nameLabel = new JLabel("*Nombre");
    JTextField nameText = new JTextField(15);
    JLabel nifLabel = new JLabel("*DNI");
    JTextField nifText = new JTextField(9);
    
    //JPanel - Direccion y CP
    
    JLabel addressLabel = new JLabel("Dirección");
    JTextField addressText = new JTextField(15);
    JLabel poboxLabel = new JLabel("C. Postal");
    JTextField poboxText = new JTextField(15);
    
    //JPanel - Provincia y población
    
    JLabel countryLabel = new JLabel("Provincia");
    JTextField countryText = new JTextField(15);
    JLabel poblationLabel = new JLabel("Población");
    JTextField poblationText = new JTextField(15);
    
    //JPanel - Teléfono y email
    
    JLabel phoneLabel = new JLabel("Teléfono");
    JTextField phoneText = new JTextField(15);
    JLabel emailLabel = new JLabel("Email");
    JTextField emailText = new JTextField(9);
    
    //JPanel - Botones
    JPanel buttonsPanel = new JPanel();
    
    JButton createButton = new JButton("Crear");
    JButton cancelButton = new JButton("Cancelar");
    
    public CreateClientWindow(){
        setLayout(new FlowLayout());
        setTitle("Crear cliente");
        firstPanel.setLayout(new BoxLayout(firstPanel, Y_AXIS));
        secondPanel.setLayout(new BoxLayout(secondPanel, Y_AXIS));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComponentsToPanel(buttonsPanel, createButton, cancelButton);
        addComponentsToPanel(firstPanel, nameLabel, nameText, addressLabel, addressText,
                              countryLabel, countryText,  phoneLabel, phoneText);
        addComponentsToPanel(secondPanel, nifLabel, nifText, poblationLabel, poblationText
                            , emailLabel, emailText, buttonsPanel);
        addComponentsToMainFrame(this, firstPanel, secondPanel);
        pack();
        setVisible(true);
    }
}
