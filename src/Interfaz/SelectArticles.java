/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.SelectArticleEvents;
import Helpers.JsonHelper;
import Objects.Book;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pablo
 */
public class SelectArticles extends JFrame{
    
    public static javax.swing.JTable articlesTable;
    public static javax.swing.JLabel codeLabel;
    public static javax.swing.JTextField codeText;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JButton searchButton;
    
    public SelectArticles() throws FileNotFoundException{
        initComponents();
    }

    private void initComponents() throws FileNotFoundException {
        setTitle("Artículos");
        codeLabel = new javax.swing.JLabel();
        codeText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        articlesTable = new javax.swing.JTable();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        //<editor-fold defaultstate="collapsed" desc="Ajustes Estéticos">
        codeLabel.setText("Código:");
        
        searchButton.setText("Buscar");
        
        articlesTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null}
                },
                new String [] {
                    "Código", "Nombre", "Cantidad", "Precio", "IVA", "Detalles"
                }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(articlesTable);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(codeLabel)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(codeText, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(searchButton)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(codeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(codeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                                .addContainerGap())
        );
//</editor-fold>
        
        //Eventos
        searchButton.addActionListener(new SelectArticleEvents());
        articlesTable.addMouseListener(new SelectArticleEvents());
        poblateTable();
        
        pack();
        setVisible(true);
    }
    
    public static void poblateTable() throws FileNotFoundException{
        ArrayList<Book> books = JsonHelper.getAllBooks();

        DefaultTableModel tbm = (DefaultTableModel) articlesTable.getModel();
        tbm.setRowCount(0);
        for (Book b : books) {
            Object row[] = {b.getCode(), b.getName(), b.getAmount(), b.getPrice(), b.getIvaPercentage(), b.getDetails()};
            tbm.addRow(row);
        }
        articlesTable.setModel(tbm);
    }
    
    public static void poblateTable(Book book){
        DefaultTableModel tbm = (DefaultTableModel) articlesTable.getModel();
        tbm.setRowCount(0);
        Object row[] = {book.getCode(), book.getName(), book.getAmount(), book.getPrice(), book.getIvaPercentage(), book.getDetails()};
        tbm.addRow(row);
        articlesTable.setModel(tbm);
    }
}
