/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Utils.Validators;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Book {

    String code;
    int ivaPercentage;
    int amount;
    double price;
    String name;
    String details;

    public Book() {

    }

    public Book(String code, int amount, int ivaPercentage, double price, String name, String details) {
        this.code = code;
        this.amount = amount;
        this.ivaPercentage = ivaPercentage;
        this.price = price;
        this.name = name;
        this.details = details;
    }

    @Override
    public String toString() {
        String toPrint = "";
        toPrint += "Nombre: " + this.name;
        toPrint += "\nPrecio: " + this.price;
        toPrint += "\nDetalles: " + this.details;
        toPrint += "\nCodigo: " + this.code;
        return toPrint;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIvaPercentage() {
        return ivaPercentage;
    }

    public void setIvaPercentage(int ivaPercentage) {
        this.ivaPercentage = ivaPercentage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean equals(Book book) {
        return this.code.equalsIgnoreCase(book.code);
    }

    public Boolean isValid() {

        if (!Validators.validateIsbn13(this.code)) {
            JOptionPane.showMessageDialog(null, "Ese ISBN es inválido", "Error de inserción", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (this.amount < 0) {
            JOptionPane.showMessageDialog(null, "Cantidad errónea", "Error de inserción", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (this.name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El nombre no puede estar vacío!", "Error de inserción", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (this.ivaPercentage < 1) {
            JOptionPane.showMessageDialog(null, "El IVA no puede ser inferior a 1", "Error de inserción", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (this.price < 0) {
            JOptionPane.showMessageDialog(null, "El precio introducido es inválido", "Error de inserción", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

}
