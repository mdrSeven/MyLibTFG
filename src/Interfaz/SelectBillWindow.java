/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import java.awt.FlowLayout;
import java.util.Calendar;
import static Helpers.InterfaceHelper.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo Infantes
 */
public class SelectBillWindow extends JFrame{
    
    //BoxLayout - Layout principal
    BoxLayout box = new BoxLayout(getContentPane(), WIDTH);
    
    //JPanel - Panel superior
    JPanel topPanel = new JPanel();
    
    //JPanel - Selección de factura
    JPanel billSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    JLabel billNumberLabel = new JLabel("Nº Factura");
    JTextField billNumberText = new JTextField(14);
    JButton searchBillButton = new JButton("Buscar");
    JButton selectBillButton = new JButton("Seleccionar Factura");
    
    //JPanel - Otras acciones
    JPanel otherActionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    JButton showAllButton = new JButton("Mostrar Todo");
    JButton cancelButton = new JButton("Cancelar");
    
    //JTable - Facturas encontradas
    JTable billsTable = new JTable();
    
    public SelectBillWindow(){
        setLayout(box);
        setTitle("Seleccionar Factura");
        billSelectionPanel.setBorder(new EmptyBorder(0,0,0,100));
        
        configureTable();
        addComponentsToPanel(topPanel, billSelectionPanel, otherActionsPanel);
        addComponentsToPanel(billSelectionPanel, billNumberLabel, billNumberText, searchBillButton, selectBillButton);
        addComponentsToPanel(otherActionsPanel, showAllButton, cancelButton);
        addComponentsToMainFrame(this, topPanel,billsTable.getTableHeader(), billsTable);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    private void configureTable(){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nº Factura");
        modelo.addColumn("DNI/CIF Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Descuento");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Total");
       
        Object[] ob = new Object[7];
        ob[0] = 4;
        ob[1] = "77491330Y";
        ob[2] = Calendar.DAY_OF_MONTH + "/"+Calendar.MONTH + "/"+Calendar.YEAR;
        ob[3] = Calendar.HOUR_OF_DAY;
        ob[4] = "25%";
        ob[5] = "300";
        ob[6] = "320";
        modelo.addRow(ob);
        billsTable.setModel(modelo);
    }
}
