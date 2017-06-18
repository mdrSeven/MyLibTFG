package Helpers;

import Objects.Bill;
import Objects.Book;
import Objects.Client;
import Objects.Refund;
import Utils.SettingsConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;
import java.awt.List;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class JsonHelper {

    static JsonWriter writer;
    static String clientsPath = "C:\\MyLib\\Clientes.json";
    static String articlesPath = "C:\\MyLib\\Articulos.json";
    static String billsPath = "C:\\MyLib\\Facturas.json";
    static String refundsPath = "C:\\MyLib\\Devoluciones.json";

    public static ArrayList<Book> getAllBooks() throws FileNotFoundException {
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader fr = new FileReader(articlesPath);
            Type listType = new TypeToken<ArrayList<Book>>() {
            }.getType();
            return gson.fromJson(fr, listType);
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static void insertBook(Book book) throws IOException {
        ArrayList<Book> bookList = getAllBooks();

        if (validateBookInsertion(bookList, book)) {
            bookList.add(book);
            saveBookList(bookList);
            JOptionPane.showMessageDialog(null, "Artículo insertado correctamente", "Artículo Insertado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ese ISBN ya existe en la base de datosa!", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public static void editBook(Book book) throws FileNotFoundException, IOException {
        ArrayList<Book> bookList = removeBook(book, true);

        if (validateBookInsertion(bookList, book)) {
            bookList.add(book);
            saveBookList(bookList);
            JOptionPane.showMessageDialog(null, "Artículo editado", "Artículo Editado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Ese ISBN ya existe en la base de datos!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Book searchBook(String code) throws FileNotFoundException {
        ArrayList<Book> bookList = getAllBooks();
        for (Book b : bookList) {
            if (b.getCode().equalsIgnoreCase(code)) {
                return b;
            }
        }
        return null;

    }

    public static ArrayList<Book> removeBook(Book book, Boolean isEditing) throws FileNotFoundException, IOException {
        ArrayList<Book> bookList = getAllBooks();

        for (Book b : bookList) {
            if (b.equals(book)) {
                bookList.remove(b);
                if (!isEditing) {
                    saveBookList(bookList);
                    JOptionPane.showMessageDialog(null, "Artículo eliminado.", "Artículo eliminado", JOptionPane.INFORMATION_MESSAGE);
                    return null;
                }
                return bookList;
            }
        }
        return null;
    }

    public static ArrayList<Client> getAllClients() {
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader fr = new FileReader(clientsPath);
            Type listType = new TypeToken<ArrayList<Client>>() {
            }.getType();
            return gson.fromJson(fr, listType);
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static void insertClient(Client client) throws IOException {
        ArrayList<Client> clientsList = getAllClients();

        clientsList.add(client);
        saveClientList(clientsList);
        JOptionPane.showMessageDialog(null, "Cliente insertado correctamente", "Cliente Insertado", JOptionPane.INFORMATION_MESSAGE);
    }

    public static Client searchClient(String dni) {
        ArrayList<Client> clientsList = getAllClients();
        for (Client client : clientsList) {
            if (client.getDni().equalsIgnoreCase(dni)) {
                return client;
            }
        }
        return null;

    }

    public static void editClient(Client client) throws IOException {
        ArrayList<Client> clientsList = removeClient(client, true);

        clientsList.add(client);
        saveClientList(clientsList);
        JOptionPane.showMessageDialog(null, "Cliente editado", "Cliente Editado", JOptionPane.INFORMATION_MESSAGE);
    }

    public static ArrayList<Client> removeClient(Client client, boolean isEditing) throws IOException {
        ArrayList<Client> clientsList = getAllClients();

        for (Client c : clientsList) {
            if (c.equals(client)) {
                clientsList.remove(c);
                if (!isEditing) {
                    saveClientList(clientsList);
                    JOptionPane.showMessageDialog(null, "Cliente eliminado.", "Cliente eliminado", JOptionPane.INFORMATION_MESSAGE);
                    return null;
                }
                return clientsList;
            }
        }
        return null;

    }

    public static ArrayList<Bill> getAllBills() {
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader fr = new FileReader(billsPath);
            Type listType = new TypeToken<ArrayList<Bill>>() {
            }.getType();
            return gson.fromJson(fr, listType);
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static int getCurrentBillNumber() {
        ArrayList<Bill> billList = getAllBills();
        if (billList.isEmpty()) {
            return 0;
        } else {
            return Collections.max(billList).getBillNumber() + 1;
        }
    }

    public static void insertBill(Bill bill) throws IOException {
        ArrayList<Bill> billList = getAllBills();

        billList.add(bill);
        saveBillList(billList);
        JOptionPane.showMessageDialog(null, "Factura insertada correctamente", "Factura insertada", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static Bill searchBill(int billNumber){
        ArrayList<Bill> billList = getAllBills();
        
        for (Bill bill : billList) {
            if (bill.getBillNumber() == billNumber) {
                return bill;
            }
        }
        
        return null;
    }

    public static void saveBillList(ArrayList<Bill> billList) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(billList);
        try (FileWriter fw = new FileWriter(billsPath)) {
            fw.write(json);
        }
    }

    private static void saveClientList(ArrayList<Client> clientsList) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clientsList);
        try (FileWriter fw = new FileWriter(clientsPath)) {
            fw.write(json);
        }
    }

    private static void saveBookList(ArrayList<Book> bookList) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(bookList);
        try (FileWriter fw = new FileWriter(articlesPath)) {
            fw.write(json);
        }
    }

    private static boolean validateBookInsertion(ArrayList<Book> bookList, Book book) {
        for (Book b : bookList) {
            if (b.equals(book)) {
                return false;
            }
        }
        return true;
    }

    public static void updateStock(Book b) throws IOException {
        ArrayList<Book> bookList = removeBook(b, true);

        bookList.add(b);
        saveBookList(bookList);

    }

    public static String[] getBooksWithLowStock() throws FileNotFoundException {
        ArrayList <Book> bookList = getAllBooks();
        ArrayList <String> codeList = new ArrayList<>();
        String[] codes;
        
        for(Book b : bookList){
            if(b.getAmount() < SettingsConfig.securityStock)
                codeList.add(b.getCode());
        }
        
        codes = new String[codeList.size()];
        codes = codeList.toArray(codes);
        return codeList.size() < 1 ? null : codes;
    }
    

    public static ArrayList<Refund> getAllRefunds() {
        try {
            final Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileReader fr = new FileReader(refundsPath);
            Type listType = new TypeToken<ArrayList<Refund>>() {
            }.getType();
            return gson.fromJson(fr, listType);
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        }
    }
    
    public static int getCurrentRefundNumber(){
        ArrayList<Refund> refundList = getAllRefunds();
        if (refundList.isEmpty()) {
            return 0;
        } else {
            return Collections.max(refundList).getRefundNumber() + 1;
        }
    }

    public static void insertRefund(Refund ref) throws IOException {
        ArrayList<Refund> refundList = getAllRefunds();

        refundList.add(ref);
        saveRefundList(refundList);
        JOptionPane.showMessageDialog(null, "Devolución insertada", "Devolución insertada", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void saveRefundList(ArrayList<Refund> refundList) throws IOException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(refundList);
        try (FileWriter fw = new FileWriter(refundsPath)) {
            fw.write(json);
        }
    }

    public static Refund searchRefund(int refundNum) {
        ArrayList<Refund> refundList = getAllRefunds();
        
        for (Refund ref : refundList) {
            if (ref.getRefundNumber() == refundNum) {
                return ref;
            }
        }
        
        return null;
    }

}
