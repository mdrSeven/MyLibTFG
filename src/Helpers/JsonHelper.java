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
import javax.swing.JOptionPane;

public class JsonHelper {

    static JsonWriter writer;
    static String clientsPath = "C:\\MyLib\\Clientes.json";

    public static void createBook() throws IOException {
//        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        // writer = new JsonWriter(new FileWriter("C:\\Users\\Pablo Infantes\\Documents\\Claves\\Libro.json"));
//        String json = gson.toJson(testSettings());
//        try (FileWriter fw = new FileWriter(path)) {
//            fw.write(json);
//        }
    }

    public static void readBooks() throws FileNotFoundException {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader fr = new FileReader(clientsPath);
        Type listType = new TypeToken<ArrayList<Book>>() {
        }.getType();
        ArrayList<Book> books = gson.fromJson(fr, listType);

        for (Book book : books) {
            System.out.println(book.toString());
        }
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
    
    public static void insertClient(Client client) throws IOException{
        ArrayList<Client> clientsList = getAllClients();
        
        clientsList.add(client);
        saveClientList(clientsList);
        JOptionPane.showMessageDialog(null, "Cliente insertado correctamente", "Cliente Insertado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static Client searchClient(String dni){
        ArrayList<Client> clientsList = getAllClients();
        for(Client client : clientsList){
            if(client.getDni().equalsIgnoreCase(dni)){
                return client;
            }
        }
        return null;
        
    }
    
    public static void editClient(Client client) throws IOException{
        ArrayList<Client> clientsList = removeClient(client, true);
        
        clientsList.add(client);
        saveClientList(clientsList);
        JOptionPane.showMessageDialog(null, "Cliente editado", "Cliente Editado", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
    
    public static ArrayList<Client> removeClient(Client client, boolean isEditing) throws IOException{
        ArrayList<Client> clientsList = getAllClients();
        
        for(Client c : clientsList){
            if(c.equals(client)){
                clientsList.remove(c);
                if(!isEditing){
                    saveClientList(clientsList);
                    JOptionPane.showMessageDialog(null, "Cliente eliminado.", "Cliente eliminado", JOptionPane.INFORMATION_MESSAGE);
                    return null;
                }
                return clientsList;
            }
        }
        return null;
        
    }
    
    private static void saveClientList(ArrayList<Client> clientsList) throws IOException{
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(clientsList);
        try (FileWriter fw = new FileWriter(clientsPath)) {
            fw.write(json);
        }
    }

}
