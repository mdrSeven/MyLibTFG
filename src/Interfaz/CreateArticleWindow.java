/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static Helpers.InterfaceHelper.*;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Pablo Infantes
 */
public class CreateArticleWindow extends JFrame{
    
    //BoxLayout - Layout principal
    BoxLayout box = new BoxLayout(getContentPane(), WIDTH);
    
    //JPanel - Código y cantidad
    JPanel codeAndAmountPanel = new JPanel();
    
    JLabel codeLabel = new JLabel("Código:");
    JTextField codeText = new JTextField(13);
    JLabel amountLabel = new JLabel("Cantidad:");
    JTextField amountText = new JTextField(3);
    
    //JPanel - Nombre y precio
    JPanel nameAndPricePanel = new JPanel();
    
    JLabel nameLabel = new JLabel("Nombre:");
    JTextField nameText = new JTextField(13);
    JLabel priceLabel = new JLabel("Precio:");
    JTextField priceText = new JTextField(5);
    JLabel ivaPercentageLabel = new JLabel("IVA: ");
    JTextField ivaPercentage = new JTextField(3);
    
    //JPanel - Descripción y botones
    JPanel descriptionAndButtonsPanel = new JPanel();
    
    JTextArea descriptionTextArea = new JTextArea(5,20);
    
    //JPanel - Botones
    JPanel buttonsPanel = new JPanel();
    
    JButton createButton = new JButton("Crear");
    JButton cancelButton = new JButton("Cancelar");
    
            
    public CreateArticleWindow(){
        setLayout(box);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, WIDTH));
        addComponentsToPanel(codeAndAmountPanel, codeLabel, codeText, amountLabel, amountText);
        addComponentsToPanel(nameAndPricePanel, nameLabel, nameText, priceLabel, priceText);
        addComponentsToPanel(descriptionAndButtonsPanel, descriptionTextArea, buttonsPanel);
        buttonsPanel.add(createButton, 0);
        buttonsPanel.add(cancelButton, 0);
        //addComponentsToMainFrame(this, codeAndAmountPanel, nameAndPricePanel, descriptionAndButtonsPanel);
        addComponentsToMainFrame(this, codeAndAmountPanel, nameAndPricePanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
