/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Helpers.SettingsHelper;
import Objects.Settings;
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
public class SettingsEvents implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent ae) {
        Settings actualSettings = Settings.getInstance();
        System.out.println("Evento: "+actualSettings.getSecurityStock());
        try {
            SettingsHelper.saveSettings(actualSettings);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se han podido guardar los ajustes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
