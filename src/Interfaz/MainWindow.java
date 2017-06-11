package Interfaz;

import Events.MainWindowEvents;
import static Helpers.InterfaceHelper.*;
import static Interfaz.MainWindow.MainWindowConstants.*;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pablo
 */
public class MainWindow extends JFrame{
    
    public static javax.swing.JSpinner amountSpinner;
    public static javax.swing.JMenuItem refundBillItem;
    public static javax.swing.JTextField codeText;
    public static javax.swing.JTextField discountText;
    public static javax.swing.JMenuItem editArticleItem;
    public static javax.swing.JMenuItem editClientItem;
    public static javax.swing.JMenu editMenu;
    public static javax.swing.JButton finishBillButton;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JMenu jMenu1;
    public static javax.swing.JMenu jMenu3;
    public static javax.swing.JMenu jMenu4;
    public static javax.swing.JMenu jMenu5;
    public static javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable mainTable;
    public static javax.swing.JMenuItem newBillItem;
    public static javax.swing.JMenuItem preferencesItem;
    public static javax.swing.JMenuItem queryArticlesItem;
    public static javax.swing.JMenuItem queryBillsItem;
    public static javax.swing.JMenuItem queryRefundsItem;
    public static javax.swing.JMenuItem registerArticleItem;
    public static javax.swing.JMenuItem registerClientItem;
    public static javax.swing.JMenu registerItem;
    public static javax.swing.JButton removeButton;
    public static javax.swing.JButton removeRowButton;
    public static javax.swing.JButton selectClientButton;
    public static javax.swing.JLabel selectedClientLabel;
    public static javax.swing.JLabel subTotalLabel;
    public static javax.swing.JTextField subTotalText;
    public static javax.swing.JLabel totalLabel;
    public static javax.swing.JTextField totalText;
    
    
    public MainWindow(){
        initComponents();
    }
    

    private void initComponents() {
        amountSpinner = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        codeText = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        finishBillButton = new javax.swing.JButton();
        removeRowButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        selectClientButton = new javax.swing.JButton();
        selectedClientLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        discountText = new javax.swing.JTextField();
        subTotalLabel = new javax.swing.JLabel();
        subTotalText = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        totalText = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newBillItem = new javax.swing.JMenuItem();
        refundBillItem = new javax.swing.JMenuItem();
        registerItem = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        registerArticleItem = new javax.swing.JMenuItem();
        registerClientItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        editArticleItem = new javax.swing.JMenuItem();
        editClientItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        queryBillsItem = new javax.swing.JMenuItem();
        queryRefundsItem = new javax.swing.JMenuItem();
        queryArticlesItem = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        preferencesItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //Eventos
        newBillItem.addActionListener(new MainWindowEvents(NEW_BILL_ITEM));
        refundBillItem.addActionListener(new MainWindowEvents(NEW_REFUND_ITEM));
        registerArticleItem.addActionListener(new MainWindowEvents(NEW_ARTICLE_ITEM));
        registerClientItem.addActionListener(new MainWindowEvents(NEW_CLIENT_ITEM));
        editArticleItem.addActionListener(new MainWindowEvents(EDIT_ARTICLE_ITEM));
        editClientItem.addActionListener(new MainWindowEvents(EDIT_CLIENT_ITEM));
        queryBillsItem.addActionListener(new MainWindowEvents(QUERY_BILLS_ITEM));
        queryArticlesItem.addActionListener(new MainWindowEvents(QUERY_ARTICLES_ITEM));
        queryRefundsItem.addActionListener(new MainWindowEvents(QUERY_REFUND_ITEM));
        preferencesItem.addActionListener(new MainWindowEvents(PREFERENCES_ITEM));

        //<editor-fold defaultstate="collapsed" desc="Ajustes Estéticos">
        
        
        jLabel1.setText("Código del artículo:");
        
        jButton1.setText("Añadir");
        
        removeButton.setText("Quitar");
        
        finishBillButton.setText("Finalizar Devolución");
        
        removeRowButton.setText("Eliminar Fila");
        
        mainTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null}
                },
                new String [] {
                    "Nº Articulos", "Código", "Nombre", "Cantidad", "Precio", "IVA", "Detalles"
                }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };
            
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(mainTable);
        
        selectClientButton.setText("Seleccionar Cliente");
        
        selectedClientLabel.setText("Ningun Cliente Seleccionado");
        
        jLabel2.setText("DESCUENTO(%): ");
        
        subTotalLabel.setText("SUBTOTAL:");
        
        totalLabel.setText("TOTAL:");
        
        jMenu1.setText("Factura");
        
        newBillItem.setText("Nueva Factura");
        jMenu1.add(newBillItem);
        
        refundBillItem.setText("Nueva Devolución");
        jMenu1.add(refundBillItem);
        
        jMenuBar1.add(jMenu1);
        
        registerItem.setText("Alta/Devolución");
        
        jMenu5.setText("Alta");
        
        registerArticleItem.setText("Artículo");
        jMenu5.add(registerArticleItem);
        
        registerClientItem.setText("Cliente");
        jMenu5.add(registerClientItem);
        
        registerItem.add(jMenu5);
        
        editMenu.setText("Modificación");
        
        editArticleItem.setText("Artículo");
        editMenu.add(editArticleItem);
        
        editClientItem.setText("Cliente");
        editMenu.add(editClientItem);
        
        registerItem.add(editMenu);
        
        jMenuBar1.add(registerItem);
        
        jMenu3.setText("Consultas");
        
        queryBillsItem.setText("Facturas");
        jMenu3.add(queryBillsItem);
        
        queryRefundsItem.setText("Devoluciones");
        jMenu3.add(queryRefundsItem);
        
        queryArticlesItem.setText("Artículos");
        jMenu3.add(queryArticlesItem);
        
        jMenuBar1.add(jMenu3);
        
        jMenu4.setText("Configuración");
        
        preferencesItem.setText("Preferencias");
        jMenu4.add(preferencesItem);
        
        jMenuBar1.add(jMenu4);
        
        setJMenuBar(jMenuBar1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(codeText, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(removeButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(removeRowButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(finishBillButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(selectClientButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(selectedClientLabel)
                                                .addGap(78, 78, 78)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(discountText, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(subTotalLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(subTotalText, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(totalLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(totalText, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(amountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(codeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton1)
                                        .addComponent(removeButton)
                                        .addComponent(finishBillButton)
                                        .addComponent(removeRowButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(selectClientButton)
                                        .addComponent(selectedClientLabel)
                                        .addComponent(jLabel2)
                                        .addComponent(discountText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(subTotalLabel)
                                        .addComponent(subTotalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalLabel)
                                        .addComponent(totalText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        pack();
//</editor-fold>
        setVisible(true);
    }
    
    public interface MainWindowConstants{
        public static int NEW_BILL_ITEM = 1;
        public static int NEW_REFUND_ITEM = 2;
        public static int NEW_ARTICLE_ITEM = 3;
        public static int NEW_CLIENT_ITEM = 4;
        public static int EDIT_ARTICLE_ITEM = 5;
        public static int EDIT_CLIENT_ITEM = 6;
        public static int QUERY_BILLS_ITEM = 7;
        public static int QUERY_REFUND_ITEM = 8;
        public static int QUERY_ARTICLES_ITEM = 9;
        public static int PREFERENCES_ITEM = 10;
    }
}
