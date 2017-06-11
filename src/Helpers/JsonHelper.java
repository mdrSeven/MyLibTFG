package Helpers;

import Objects.Book;
import Objects.Client;
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
            JOptionPane.showMessageDialog(null, "Ese ISBN ya existe en la base de datos!", "Error", JOptionPane.ERROR_MESSAGE);

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

    private static ArrayList<Client> getAllClients() {
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

}
