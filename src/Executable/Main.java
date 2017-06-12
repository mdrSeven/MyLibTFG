/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Executable;

import static Helpers.JsonHelper.*;
import Helpers.SettingsHelper;
import Interfaz.CreateArticleWindow;
import Interfaz.CreateClientWindow;
import Interfaz.MainWindow;
import Interfaz.EditArticle;
import Interfaz.EditClient;
import Interfaz.SelectBillWindow;
import Interfaz.SelectClients;
import Interfaz.SettingsWindow;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Pablo
 */
public class Main {
    public static void main(String args[]) throws ClassNotFoundException, IOException{
        File directory = new File("C:\\MyLib");
        if(!directory.exists())
            directory.mkdir();
       new MainWindow();
    }
}
