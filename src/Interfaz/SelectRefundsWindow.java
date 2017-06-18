/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.SelectRefundsEvents;
import Helpers.JsonHelper;
import static Interfaz.SelectRefundsWindow.SelectRefundsConstants.*;
import Objects.Refund;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo
 */
public class SelectRefundsWindow extends JFrame {

    public static javax.swing.JButton cancelButton;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable refundsTable;
    public static javax.swing.JLabel refundNumber;
    public static javax.swing.JTextField refundNumberText;
    public static javax.swing.JButton searchButton;
    public static javax.swing.JButton showAllButton;

    public SelectRefundsWindow() {
        initialize();
    }

    private void initialize() {
        refundNumber = new javax.swing.JLabel();
        refundNumberText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        showAllButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        refundsTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        //<editor-fold defaultstate="collapsed" desc="Ajustes estéticos">
        refundNumber.setText("Nº Devolución:");

        searchButton.setText("Buscar");

        cancelButton.setText("Cancelar");

        showAllButton.setText("Mostrar Todo");

        refundsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nº Devolución", "Nº Factura", "Subtotal", "Total"
                }
        ) {
            Class[] types = new Class[]{
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(refundsTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(refundNumber)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(refundNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(showAllButton)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cancelButton)))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(refundNumber)
                                .addComponent(refundNumberText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchButton)
                                .addComponent(cancelButton)
                                .addComponent(showAllButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
//</editor-fold>

        //Eventos
        searchButton.addActionListener(new SelectRefundsEvents(SEARCH_BUTTON));
        showAllButton.addActionListener(new SelectRefundsEvents(SHOW_ALL_BUTTON));

        poblateTable();
        pack();
        setVisible(true);
    }

    public static void poblateTable() {
        ArrayList<Refund> refundList = JsonHelper.getAllRefunds();
        DefaultTableModel model = (DefaultTableModel) refundsTable.getModel();
        
        model.setRowCount(0);
        for (Refund r : refundList) {
            Object row[] = {r.getRefundNumber(), r.getBillNumber(), r.getSubTotal(), r.getTotal()};
            model.addRow(row);
        }
        refundsTable.setModel(model);
    }

    public static void poblateTable(Refund r) {
        DefaultTableModel model = (DefaultTableModel) refundsTable.getModel();
        
        model.setRowCount(0);
        Object row[] = {r.getRefundNumber(), r.getBillNumber(), r.getSubTotal(), r.getTotal()};
        model.addRow(row);
        refundsTable.setModel(model);
    }

    public interface SelectRefundsConstants {

        public static int SEARCH_BUTTON = 1;
        public static int SHOW_ALL_BUTTON = 2;
        public static int CANCEL_BUTTON = 3;
    }
}
