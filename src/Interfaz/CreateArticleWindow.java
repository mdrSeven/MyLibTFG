/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Events.CreateArticleEvents;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static Helpers.InterfaceHelper.*;
import Helpers.JsonHelper;
import static Interfaz.CreateArticleWindow.CreateArticleConstants.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

/**
 *
 * @author Pablo Infantes
 */
public class CreateArticleWindow extends JFrame implements ActionListener{
    
    //BoxLayout - Layout principal
    BoxLayout box = new BoxLayout(getContentPane(), WIDTH);
    
    //JPanel - Código y cantidad
    public static JPanel codeAndAmountPanel = new JPanel();
    
    public static JLabel codeLabel = new JLabel("Código:");
    public static JTextField codeText = new JTextField(13);
    public static JLabel amountLabel = new JLabel("Cantidad:");
    public static JSpinner amountSpinner = new JSpinner(new SpinnerNumberModel(0,0,Utils.Constants.defaultMaxStock,1));
    
    //JPanel - Nombre y precio
    public static JPanel nameAndPricePanel = new JPanel();
    
    public static JLabel nameLabel = new JLabel("Nombre:");
    public static JTextField nameText = new JTextField(13);
    public static JLabel priceLabel = new JLabel("Precio:");
    public static JTextField priceText = new JTextField(7);
    public static JLabel ivaPercentageLabel = new JLabel("IVA: ");
    public static JTextField ivaPercentage = new JTextField(3);
    
    //JPanel - Descripción y botones
    public static JPanel descriptionAndButtonsPanel = new JPanel();
    
    public static JTextArea descriptionTextArea = new JTextArea(5,20);
    
    //JPanel - Botones
    public static JPanel buttonsPanel = new JPanel();
    
    public static JButton createButton = new JButton("Crear");
    public static JButton cancelButton = new JButton("Cancelar");
    
            
    public CreateArticleWindow() throws FileNotFoundException{
        setLayout(box);
        setTitle("Crear Artículo");
        
        //Inicialización
        codeText.setText("");
        descriptionTextArea.setText("Descripción");
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        descriptionTextArea.setBorder(BorderFactory.createCompoundBorder(border,
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        
        //Eventos
        codeText.addFocusListener(new CreateArticleEvents(CODE_TEXT));
        priceText.addFocusListener(new CreateArticleEvents(PRICE_TEXT));
        
        createButton.addActionListener(new CreateArticleEvents(CREATE_BUTTON));
        cancelButton.addActionListener(this);
        
        //Introducción
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, WIDTH));
        addComponentsToPanel(codeAndAmountPanel, codeLabel, codeText, amountLabel, amountSpinner);
        addComponentsToPanel(nameAndPricePanel, nameLabel, nameText, priceLabel, priceText);
        addComponentsToPanel(descriptionAndButtonsPanel, descriptionTextArea, buttonsPanel);
        buttonsPanel.add(cancelButton, 0);
        buttonsPanel.add(createButton, 0);
        addComponentsToMainFrame(this, codeAndAmountPanel, nameAndPricePanel, descriptionAndButtonsPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        this.dispose();
    }
    
    public interface CreateArticleConstants{
        public static int CREATE_BUTTON = 1;
        public static int CODE_TEXT = 3;
        public static int PRICE_TEXT = 4;
    }
}
