/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.SelectBillEvents;
import java.awt.FlowLayout;
import java.util.Calendar;
import static Helpers.InterfaceHelper.*;
import Helpers.JsonHelper;
import static Interfaz.SelectBillWindow.SelectBillConstants.*;
import Objects.Bill;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo Infantes
 */
public class SelectBillWindow extends JFrame {

    //BoxLayout - Layout principal
    BoxLayout box = new BoxLayout(getContentPane(), WIDTH);

    //JPanel - Panel superior
    JPanel topPanel = new JPanel();

    //JPanel - Selección de factura
    JPanel billSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JLabel billNumberLabel = new JLabel("Nº Factura");
    public static JTextField billNumberText = new JTextField(14);
    public static JButton searchBillButton = new JButton("Buscar");

    //JPanel - Otras acciones
    JPanel otherActionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    public static JButton showAllButton = new JButton("Mostrar Todo");
    public static JButton cancelButton = new JButton("Cancelar");

    //JTable - Facturas encontradas
    public static JTable billsTable = new JTable();

    public SelectBillWindow() {
        setLayout(box);
        setTitle("Seleccionar Factura");
        billSelectionPanel.setBorder(new EmptyBorder(0, 0, 0, 100));

        //Eventos
        searchBillButton.addActionListener(new SelectBillEvents(SEARCH_BUTTON));
        showAllButton.addActionListener(new SelectBillEvents(SHOW_ALL_BUTTON));
        billsTable.addMouseListener(new SelectBillEvents(BILLS_TABLE));

        //Configuración de interfaz
        poblateTable();
        addComponentsToPanel(topPanel, billSelectionPanel, otherActionsPanel);
        addComponentsToPanel(billSelectionPanel, billNumberLabel, billNumberText, searchBillButton);
        addComponentsToPanel(otherActionsPanel, showAllButton, cancelButton);
        addComponentsToMainFrame(this, topPanel, billsTable.getTableHeader(), billsTable);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void poblateTable() {
        DefaultTableModel modelo = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Nº Factura");
        modelo.addColumn("DNI/CIF Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Descuento");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Total");

        ArrayList<Bill> billList = JsonHelper.getAllBills();
        for (Bill b : billList) {
            Object[] ob = new Object[6];
            ob[0] = b.getBillNumber();
            ob[1] = b.getAssignedClient() == null ? "Sin cliente" : b.getAssignedClient().getDni();
            ob[2] = b.getDate();
            ob[3] = b.getDiscount();
            ob[4] = b.getSubTotal();
            ob[5] = b.getTotal();
            modelo.addRow(ob);
        }
        billsTable.setModel(modelo);
    }

    public static void poblateTable(Bill bill) {
        DefaultTableModel modelo = (DefaultTableModel) billsTable.getModel();
        modelo.setRowCount(0);

        Object[] ob = new Object[6];
        ob[0] = bill.getBillNumber();
        ob[1] = bill.getAssignedClient() == null ? "Sin cliente" : bill.getAssignedClient().getDni();
        ob[2] = bill.getDate();
        ob[3] = bill.getDiscount();
        ob[4] = bill.getSubTotal();
        ob[5] = bill.getTotal();
        modelo.addRow(ob);
    }

    public interface SelectBillConstants {

        public static int SEARCH_BUTTON = 1;
        public static int SHOW_ALL_BUTTON = 2;
        public static int BILLS_TABLE = 3;
    }
}
