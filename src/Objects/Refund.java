package Objects;

import java.util.ArrayList;
import java.util.Date;

public class Refund implements Comparable<Refund> {

    int refundNumber;
    int billNumber;
    Date date;
    double subTotal;
    double Total;
    ArrayList<Product> refundedProducts;

    public Refund(int refundNumber, int billNumber, double subTotal, double Total, ArrayList<Product> refundedProducts) {

        this.refundNumber = refundNumber;
        this.billNumber = billNumber;
        this.date = new Date();
        this.subTotal = subTotal;
        this.Total = Total;
        this.refundedProducts = refundedProducts;
    }

    public int getRefundNumber() {
        return refundNumber;
    }

    public void setRefundNumber(int refundNumber) {
        this.refundNumber = refundNumber;
    }

    public int getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(int billNumber) {
        this.billNumber = billNumber;
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

    public ArrayList<Product> getRefundedProducts() {
        return refundedProducts;
    }

    public void setRefundedProducts(ArrayList<Product> refundedProducts) {
        this.refundedProducts = refundedProducts;
    }

    @Override
    public int compareTo(Refund t) {
        return Integer.compare(this.refundNumber, t.getRefundNumber());
    }

}
