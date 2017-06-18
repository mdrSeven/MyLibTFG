/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Interfaz.SettingsWindow;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Settings implements Serializable{
    
    boolean checkDNI = true;
    boolean stockWarning = true;
    boolean autoSearch = true;
    int securityStock = 4;
    
    public Settings(){
        
    }
    
    public Settings(boolean dni, boolean stock, boolean autosearch, int securitystock){
        this.checkDNI = dni;
        this.stockWarning = stock;
        this.autoSearch = autosearch;
        this.securityStock = securitystock;
    }
    
    public void setToDefaultValues(){
        this.autoSearch = true;
        this.stockWarning = true;
        this.autoSearch = false;
        this.securityStock = 4;
    }

    public boolean isCheckDNI() {
        return checkDNI;
    }

    public void setCheckDNI(boolean checkDNI) {
        this.checkDNI = checkDNI;
    }

    public boolean isStockWarning() {
        return stockWarning;
    }

    public void setStockWarning(boolean stockWarning) {
        this.stockWarning = stockWarning;
    }

    public boolean isAutoSearch() {
        return autoSearch;
    }

    public void setAutoSearch(boolean autoSearch) {
        this.autoSearch = autoSearch;
    }

    public int getSecurityStock() {
        return securityStock;
    }

    public void setSecurityStock(int securityStock) {
        this.securityStock = securityStock;
    }
    
    public static Settings getInstance(){
        return new Settings(SettingsWindow.nifCheck.isSelected(), SettingsWindow.stockAlertCheck.isSelected()
                            , SettingsWindow.autoSearchCheck.isSelected(), (Integer)SettingsWindow.securityStockSpinner.getValue());
    }
    
}
