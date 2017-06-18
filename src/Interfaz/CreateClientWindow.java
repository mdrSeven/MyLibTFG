
package Interfaz;

import Events.CreateClientEvents;
import javax.swing.*;
import static Helpers.InterfaceHelper.*;
import static Interfaz.CreateClientWindow.CreateClientConstants.SAVE_BUTTON;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.BoxLayout.Y_AXIS;

/**
 *
 * @author Pablo
 */
public class CreateClientWindow extends JFrame implements ActionListener{
    //JPanel - Paneles principales
    public static JPanel firstPanel = new JPanel();
    public static JPanel secondPanel = new JPanel();
    
    public static JLabel nameLabel = new JLabel("*Nombre");
    public static JTextField nameText = new JTextField(15);
    public static JLabel nifLabel = new JLabel("*DNI");
    public static JTextField nifText = new JTextField(9);
    
    //JPanel - Direccion y CP
    
    public static JLabel addressLabel = new JLabel("Dirección");
    public static JTextField addressText = new JTextField(15);
    public static JLabel poboxLabel = new JLabel("C. Postal");
    public static JTextField poboxText = new JTextField(15);
    
    //JPanel - Provincia y población
    
    public static JLabel provinceLabel = new JLabel("Provincia");
    public static JTextField provinceText = new JTextField(15);
    public static JLabel poblationLabel = new JLabel("Población");
    public static JTextField poblationText = new JTextField(15);
    
    //JPanel - Teléfono y email
    
    public static JLabel phoneLabel = new JLabel("Teléfono");
    public static JTextField phoneText = new JTextField(15);
    public static JLabel emailLabel = new JLabel("Email");
    public static JTextField emailText = new JTextField(9);
    
    //JPanel - Botones
    public static JPanel buttonsPanel = new JPanel();
    
    public static JButton createButton = new JButton("Crear");
    public static JButton cancelButton = new JButton("Cancelar");
    
    public CreateClientWindow(){
        setLayout(new FlowLayout());
        setTitle("Crear cliente");
        
        //Inicialización
        nameText.setText("");
        nifText.setText("");
        addressText.setText("");
        poboxText.setText("");
        phoneText.setText("");
        emailText.setText("");
        
        //Eventos
        createButton.addActionListener(new CreateClientEvents(SAVE_BUTTON));
        cancelButton.addActionListener(this);
        
        firstPanel.setLayout(new BoxLayout(firstPanel, Y_AXIS));
        secondPanel.setLayout(new BoxLayout(secondPanel, Y_AXIS));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addComponentsToPanel(buttonsPanel, createButton, cancelButton);
        addComponentsToPanel(firstPanel, nameLabel, nameText, addressLabel, addressText,
                              provinceLabel, provinceText,  phoneLabel, phoneText);
        addComponentsToPanel(secondPanel, nifLabel, nifText, poblationLabel, poblationText
                            , emailLabel, emailText, buttonsPanel);
        addComponentsToMainFrame(this, firstPanel, secondPanel);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.dispose();
    }
    
    public interface CreateClientConstants {
        
        final static int SAVE_BUTTON = 1;
        final static int CANCEL_BUTTON = 2;
        
    }
}
