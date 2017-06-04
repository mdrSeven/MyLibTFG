/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Objects.Settings;
import Utils.Constants;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;
import javax.swing.Icon;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class SettingsHelper implements Serializable{
    
    public static void readSettings() throws ClassNotFoundException, IOException{
        Settings currentSettings;
        
        currentSettings = getSettingsFromSettingsFile();
    }
    
    private static Settings getSettingsFromSettingsFile() throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectInputStream obInput;
        Settings settingsToReturn = new Settings();
        
        try{
            obInput = new ObjectInputStream(new FileInputStream(Constants.SettingsPath));
            settingsToReturn = (Settings)obInput.readObject();
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Ajustes no cargados, estableciendo a valores predeterminados...", "Información", JOptionPane.INFORMATION_MESSAGE);
            settingsToReturn.setToDefaultValues();
        }
        
        return settingsToReturn;
    }
}
