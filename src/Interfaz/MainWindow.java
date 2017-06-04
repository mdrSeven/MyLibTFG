package Interfaz;

import static Helpers.InterfaceHelper.*;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Pablo
 */
public class MainWindow extends JFrame{
    
    //Layout principal
    BoxLayout mainLayout = new BoxLayout(getContentPane(), WIDTH);
    
    //JPanel MenuBar
    JPanel panelMenuBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    //MenuBar
    JMenuBar mainMenuBar = new JMenuBar();
    
    //MenuBar - Factura
    JMenu billingMenu = new JMenu("Factura");
    JMenuItem newBillItem = new JMenuItem("Nueva Factura");
    JMenuItem refundBillItem = new JMenuItem("Nueva Devolución");
    
    //MenuBar - Alta/Modificación
    JMenu registerEditMenu = new JMenu("Alta/Devolución");
    JMenu registerMenu = new JMenu("Alta");
    JMenu editMenu = new JMenu("Modificación");
    
        //SubMenu - Alta
        JMenuItem registerArticleItem = new JMenuItem("Artículo");
        JMenuItem registerClientItem = new JMenuItem("Cliente");
        
        //SubMenu - Modificación
        JMenuItem editArticleItem = new JMenuItem("Artículo");
        JMenuItem editClientItem = new JMenuItem("Cliente");
    
    //MenuBar - Consultas
    JMenu queryMenu = new JMenu("Consultas");
    JMenuItem queryBillsItem = new JMenuItem("Facturas");
    JMenuItem queryRefundsItem = new JMenuItem("Devoluciones");
    JMenuItem queryClientsItem = new JMenuItem("Clientes");
    JMenuItem queryArticlesItem = new JMenuItem("Artículos");
    
    //MenuBar - Configuración
    JMenu settingsMenu = new JMenu("Configuración");
    JMenuItem preferencesItem = new JMenuItem("Preferencias");
    
    //MenuBar - Ayuda
    JMenu helpMenu = new JMenu("Ayuda");
    JMenuItem aboutItem = new JMenuItem("Acerca De");
    JMenuItem helpItem = new JMenuItem("Ayuda");
    
    /*****************************************************************/
    //JPanel - Panel superior padre.
    JPanel panelTopParent = new JPanel();
    
    //JPanel - Panel de búsqueda
    JPanel panelSearchMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(1,1,40,1));
    JLabel articleCodeLabel = new JLabel("Código del artículo:");
    JTextField articleCodeText = new JTextField(13);
    JButton addArticleButton = new JButton("Añadir");
    JButton deleteArticle = new JButton("Quitar");
    
    //JPanel - Panel de acciones sobre factura
    JPanel panelBillActions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    JButton deleteRowButton = new JButton("Eliminar fila");
    JButton endRefundButton = new JButton("Finalizar devolución");
    
    //JTable - Tabla en la que se representa.
    JTable mainTable = new JTable();
    
    //JScrollPane - Scroll para la tabla
    JScrollPane scrollPanel = new JScrollPane(mainTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    
    //JPanel - Panel inferior
    JPanel bottomPanel = new JPanel();
    
    //JPanel - Panel cliente
    JPanel clientPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    
    JButton selectClientButton = new JButton("Seleccionar Cliente");
    JLabel selectedClientLabel = new JLabel("Ningun cliente seleccionado");
    
    //JPanel -  Descuento, subtototal y total
    JPanel amountsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    JLabel discountLabel = new JLabel("DESCUENTO (%): ");
    JTextField discountText = new JTextField(4);
    
    JLabel subTotalLabel = new JLabel("SUBTOTAL: ");
    JTextField subTotalText = new JTextField(6);
    
    JLabel totalLabel = new JLabel("TOTAL: ");
    JTextField totalText = new JTextField(6);
    
    
    
    public MainWindow(){
        setLayout(mainLayout);
        setTitle("MyLib");
        setSize(840,413);
        panelSearchMenu.setBorder(new EmptyBorder(0,0,0,75));
        panelBillActions.setBorder(new EmptyBorder(0,0,0,25));
        clientPanel.setBorder(new EmptyBorder(0,0,0,100));
        configureTable();
        
        configureMenuBar();
        
        addComponentsToPanel(panelTopParent, panelSearchMenu, panelBillActions);
        addComponentsToPanel(panelMenuBar, mainMenuBar);
        addComponentsToPanel(panelSearchMenu, amountSpinner, articleCodeLabel, articleCodeText, 
                                    addArticleButton, deleteArticle);
        addComponentsToPanel(panelBillActions, deleteRowButton, endRefundButton);
        addComponentsToPanel(bottomPanel, clientPanel, amountsPanel);
        addComponentsToPanel(clientPanel, selectClientButton, selectedClientLabel);
        addComponentsToPanel(amountsPanel, discountLabel, discountText, subTotalLabel, subTotalText,
                            totalLabel, totalText);
        
        addComponentsToMainFrame(this, panelMenuBar, panelTopParent, mainTable.getTableHeader(), mainTable, bottomPanel);
        setVisible(true);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void configureTable() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nº Articulos");
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");
        modelo.addColumn("IVA");
        modelo.addColumn("Detalles");
       
        Object[] ob = new Object[7];
        ob[0] = 4;
        ob[1] = "1234567890123";
        ob[2] = "Perico el tuerto. La saga";
        ob[3] = 9;
        ob[4] = 25.00;
        ob[5] = "21%";
        ob[6] = "Es un libro muy bonito me lo recomendó mi abuela";
        modelo.addRow(ob);
        mainTable.setModel(modelo);
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }
    
    private void configureMenuBar(){
        addItemsToMenu(billingMenu, newBillItem, refundBillItem);
        addItemsToMenu(registerEditMenu, registerMenu, editMenu);
        addItemsToMenu(queryMenu, queryBillsItem, queryRefundsItem, queryClientsItem, queryArticlesItem);
        addItemsToMenu(settingsMenu, preferencesItem);
        addItemsToMenu(helpMenu, aboutItem, helpItem);
        addItemsToSubMenu(registerMenu, registerArticleItem, registerClientItem);
        addItemsToSubMenu(editMenu, editArticleItem, editClientItem);
        addItemsToMainMenuBar(mainMenuBar,billingMenu, registerEditMenu, queryMenu, settingsMenu, helpMenu);
    }
}
