package Objects;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Bill implements Comparable<Bill>{

    int billNumber;
    int discount;
    ArrayList<Product> products;
    Date date;
    double subTotal;
    double Total;
    
    Client assignedClient;

    public Bill(int billNumber, int discount, ArrayList<Product> products, double subTotal, double Total, Client assignedClient) {
        this.billNumber = billNumber;
        this.discount = discount;
        this.products = products;
        this.date = new Date();
        this.subTotal = subTotal;
        this.Total = Total;
        this.assignedClient = assignedClient;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Client getAssignedClient() {
        return assignedClient;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient = assignedClient;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

    @Override
    public int compareTo(Bill t) {
        return Integer.compare(this.billNumber, t.getBillNumber());
    }
}
