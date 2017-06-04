/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Pablo
 */
public class InterfaceHelper {
    public static void addItemsToMainMenuBar(JMenuBar mainMenuBar, JMenuItem... items){
        for(int n=0;n<items.length;n++){
            mainMenuBar.add(items[n]);
        }
    }
    
    public static void addItemsToMenu(JMenu menu, JMenuItem... items){
        for(int n=0;n<items.length;n++){
            menu.add(items[n]);
        }
    }
    
    public static void addItemsToSubMenu(JMenu parentItem, JMenuItem... childItems){
        for(int n=0;n<childItems.length;n++){
            parentItem.add(childItems[n]);
        }
    }
    
    public static void addComponentsToPanel(JPanel panel, Component... components){
        for(int n=0;n<components.length;n++){
            panel.add(components[n]);
        }
    }
    
    public static void addComponentsToMainFrame(JFrame mainFrame, Component... components){
        for(int n=0;n<components.length;n++){
            mainFrame.add(components[n]);
        }
    }
}
