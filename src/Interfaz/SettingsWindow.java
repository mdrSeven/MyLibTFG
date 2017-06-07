/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.*;
import static javax.swing.BoxLayout.Y_AXIS;
import static Helpers.InterfaceHelper.*;

/**
 *
 * @author Pablo
 */
public class SettingsWindow extends JFrame{
    BoxLayout box = new BoxLayout(getContentPane(), WIDTH);
    
    //JPanel - Paneles principales
    JPanel mainPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    
    //JPanel - Contenido
    JPanel contentPanel = new JPanel();
    
    //JPanel - NIF
    JPanel nifPanel = new JPanel();
    JCheckBox nifCheck = new JCheckBox();
    JLabel nifLabel = new JLabel("Comprobar DNI/CIF");
    
    //JPanel - Alerta de Stock
    JPanel stockAlertPanel = new JPanel();
    JCheckBox stockAlertCheck = new JCheckBox();
    JLabel stockAlertLabel = new JLabel("Aviso Stock");
    
    //JPanel - Stock de seguridad
    JPanel securityStockPanel = new JPanel();
    JLabel securityStockLabel = new JLabel("Stock de seguridad");
    JSpinner securityStockSpinner = new JSpinner(new SpinnerNumberModel(1,1,40,1));
    
    //JPanel - Búsqueda automática
    JPanel autoSearchPanel = new JPanel();
    JCheckBox autoSearchCheck = new JCheckBox();
    JLabel autoSearchLabel = new JLabel("Búsqueda automática");
    
    //Botones
    JButton saveButton = new JButton("Guardar");
    JButton cancelButton = new JButton("Cancelar");
    
    public SettingsWindow(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(box);
        mainPanel.setLayout(new BoxLayout(mainPanel, Y_AXIS));        
        contentPanel.setLayout(new BoxLayout(contentPanel, Y_AXIS));
        
        addComponentsToPanel(nifPanel, nifCheck, nifLabel);
        addComponentsToPanel(stockAlertPanel, stockAlertCheck, stockAlertLabel);
        addComponentsToPanel(securityStockPanel, securityStockLabel, securityStockSpinner);
        addComponentsToPanel(autoSearchPanel, autoSearchCheck, autoSearchLabel);
        addComponentsToPanel(contentPanel, nifPanel, stockAlertPanel, securityStockPanel, autoSearchPanel);
        addComponentsToPanel(buttonsPanel, saveButton, cancelButton);
        addComponentsToPanel(mainPanel, contentPanel, buttonsPanel);
        
        addComponentsToMainFrame(this, mainPanel, buttonsPanel);
        pack();
        setVisible(true);
        
    }
}
