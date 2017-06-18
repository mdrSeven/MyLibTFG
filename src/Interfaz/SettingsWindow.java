/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.SettingsEvents;
import javax.swing.*;
import static javax.swing.BoxLayout.Y_AXIS;
import static Helpers.InterfaceHelper.*;
import Utils.SettingsConfig;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class SettingsWindow extends JFrame implements ActionListener{

    BoxLayout box = new BoxLayout(getContentPane(), WIDTH);

    //JPanel - Paneles principales
    JPanel mainPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    //JPanel - Contenido
    JPanel contentPanel = new JPanel();

    //JPanel - NIF
    JPanel nifPanel = new JPanel();
    public static JCheckBox nifCheck = new JCheckBox();
    public static JLabel nifLabel = new JLabel("Comprobar DNI/CIF");

    //JPanel - Alerta de Stock
    JPanel stockAlertPanel = new JPanel();
    public static JCheckBox stockAlertCheck = new JCheckBox();
    public static JLabel stockAlertLabel = new JLabel("Aviso Stock");

    //JPanel - Stock de seguridad
    public static JPanel securityStockPanel = new JPanel();
    public static JLabel securityStockLabel = new JLabel("Stock de seguridad");
    public static JSpinner securityStockSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 40, 1));

    //JPanel - Búsqueda automática
    JPanel autoSearchPanel = new JPanel();
    public static JCheckBox autoSearchCheck = new JCheckBox();
    public static JLabel autoSearchLabel = new JLabel("Búsqueda automática");

    //Botones
    public static JButton saveButton = new JButton("Guardar");
    public static JButton cancelButton = new JButton("Cancelar");

    public SettingsWindow() {
        setTitle("Preferencias");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(box);
        mainPanel.setLayout(new BoxLayout(mainPanel, Y_AXIS));
        contentPanel.setLayout(new BoxLayout(contentPanel, Y_AXIS));
        
        //Eventos
        saveButton.addActionListener(new SettingsEvents());
        cancelButton.addActionListener(this);
        
        //Añadido de los componentes
        addComponentsToPanel(nifPanel, nifCheck, nifLabel);
        addComponentsToPanel(stockAlertPanel, stockAlertCheck, stockAlertLabel);
        addComponentsToPanel(securityStockPanel, securityStockLabel, securityStockSpinner);
        addComponentsToPanel(autoSearchPanel, autoSearchCheck, autoSearchLabel);
        addComponentsToPanel(contentPanel, nifPanel, stockAlertPanel, securityStockPanel, autoSearchPanel);
        addComponentsToPanel(buttonsPanel, saveButton, cancelButton);
        addComponentsToPanel(mainPanel, contentPanel, buttonsPanel);

        addComponentsToMainFrame(this, mainPanel, buttonsPanel);
        configureWindow();
        pack();
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.dispose();
    }
    
    private void configureWindow(){
        stockAlertCheck.setSelected(SettingsConfig.stockWarning);
        autoSearchCheck.setSelected(SettingsConfig.autoSearch);
        nifCheck.setSelected(SettingsConfig.checkDNI);
        securityStockSpinner.setValue((Integer)SettingsConfig.securityStock);
    }
}
