/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Admin
 */
public class Book {
    int code;
    int ivaPercentage;
    double price;
    String name;
    String details;
    
    public Book(){
        
    }
    
    public Book(int code, int ivaPercentage, double price, String name, String details){
        this.code = code;
        this.ivaPercentage = ivaPercentage;
        this.price = price;
        this.name = name;
        this.details = details;
    }
    
    @Override
    public String toString(){
        String toPrint = "";
        toPrint += "Nombre: "+this.name;
        toPrint += "\nPrecio: "+this.price;
        toPrint += "\nDetalles: "+this.details;
        toPrint += "\nCodigo: "+this.code;
        return toPrint;
    }
    
    
}
