/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.SelectClientsEvents;
import Helpers.JsonHelper;
import Objects.Client;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Pablo
 */
public class SelectClients extends JFrame {

    public static javax.swing.JTable clientsTable;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel nifLabel;
    public static javax.swing.JTextField nifText;
    public static javax.swing.JButton searchButton;

    public SelectClients() {
        initializeComponents();
    }

    public void initializeComponents() {
        nifLabel = new javax.swing.JLabel();
        nifText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        nifLabel.setText("DNI/CIF");

        searchButton.setText("Buscar");

        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "DNI", "Nombre"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(clientsTable);
        if (clientsTable.getColumnModel().getColumnCount() > 0) {
            clientsTable.getColumnModel().getColumn(0).setResizable(false);
            clientsTable.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(nifLabel)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addComponent(nifText, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(searchButton)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(nifLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nifText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        poblateTable();

        searchButton.addActionListener(new SelectClientsEvents());
        clientsTable.addMouseListener(new SelectClientsEvents());
        pack();
        setVisible(true);
    }

    public static void poblateTable() {
        ArrayList<Client> clients = JsonHelper.getAllClients();

        DefaultTableModel tbm = (DefaultTableModel) clientsTable.getModel();
        for (Client c : clients) {
            Object row[] = {c.getDni(), c.getName()};
            tbm.addRow(row);
        }
        clientsTable.setModel(tbm);
    }

    public static void poblateTable(Client c) {
        DefaultTableModel tbm = (DefaultTableModel) clientsTable.getModel();
        tbm.setRowCount(0);
        Object row[] = {c.getDni(), c.getName()};
        tbm.addRow(row);
        clientsTable.setModel(tbm);

    }
}
