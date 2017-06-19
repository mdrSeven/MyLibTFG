/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.EditArticleEvents;
import static Interfaz.EditArticle.EditArticleConstants.*;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Pablo
 */
public class EditArticle extends JFrame{
    public static javax.swing.JLabel amountLabel;
    public static javax.swing.JButton cancelButton;
    public static javax.swing.JButton closeButton;
    public static javax.swing.JLabel codeLabel;
    public static javax.swing.JTextField codeText;
    public static javax.swing.JButton deleteArticleButton;
    public static javax.swing.JLabel ivaLabel;
    public static javax.swing.JTextField ivaText;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JPanel mainPanel;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea descriptionText;
    public static javax.swing.JTextField amountText;
    public static javax.swing.JLabel nameLabel;
    public static javax.swing.JTextField nameText;
    public static javax.swing.JLabel priceLabel;
    public static javax.swing.JTextField priceText;
    public static javax.swing.JButton saveButton;
    public static javax.swing.JButton searchButton;
    
    public EditArticle(){
        initComponents();
    }
    
    private void initComponents(){

        setTitle("Editar Artículo");
        
        codeLabel = new javax.swing.JLabel();
        codeText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        mainPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        priceText = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        amountText = new javax.swing.JTextField();
        ivaLabel = new javax.swing.JLabel();
        ivaText = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        deleteArticleButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();

        //Eventos
        searchButton.addActionListener(new EditArticleEvents(SEARCH_BUTTON));
        saveButton.addActionListener(new EditArticleEvents(SAVE_BUTTON));
        closeButton.addActionListener(new EditArticleEvents(CLOSE_BUTTON));
        deleteArticleButton.addActionListener(new EditArticleEvents(DELETE_BUTTON));
        cancelButton.addActionListener(new EditArticleEvents(CANCEL_BUTTON));
        // <editor-fold defaultstate="collapsed" desc="Ajustes estéticos">
        codeLabel.setText("Código:");

        searchButton.setText("Buscar");

        closeButton.setText("Cerrar");

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        nameLabel.setText("Nombre:");

        priceLabel.setText("Precio: ");

        amountLabel.setText("Cantidad:");

        ivaLabel.setText("IVA:");

        jLabel1.setText("Descripción (Opcional):");

        descriptionText.setColumns(20);
        descriptionText.setRows(5);
        jScrollPane1.setViewportView(descriptionText);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(priceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ivaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ivaText)))
                        .addGap(18, 18, 18)
                        .addComponent(amountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountText))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountLabel)
                    .addComponent(amountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(priceText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ivaLabel)
                    .addComponent(ivaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        deleteArticleButton.setText("Eliminar Articulo");

        cancelButton.setText("Cancelar");

        saveButton.setText("Guardar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(codeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codeText, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addGap(18, 18, 18)
                        .addComponent(closeButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteArticleButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(closeButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteArticleButton)
                    .addComponent(cancelButton)
                    .addComponent(saveButton))
                .addContainerGap())
        );
    //</editor-fold>
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        togglePanel(false);
        pack();
        setVisible(true);
    }
    
    public static void togglePanel(Boolean status){
        for(Component v : mainPanel.getComponents()){
            v.setEnabled(status);
        }
        codeText.setEnabled(!status);
        searchButton.setEnabled(!status);
        descriptionText.setEnabled(status);
        deleteArticleButton.setEnabled(status);
        saveButton.setEnabled(status);
        cancelButton.setEnabled(status);
    }
    
    public interface EditArticleConstants{
        public static int SEARCH_BUTTON = 1;
        public static int CLOSE_BUTTON = 2;
        public static int DELETE_BUTTON = 3;
        public static int SAVE_BUTTON = 4;
        public static int CANCEL_BUTTON = 5;
    }
}
