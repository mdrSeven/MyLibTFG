package Helpers;

import Objects.Book;
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

public class JsonHelper {
    
    static JsonWriter writer;
    static String path = "C:\\Users\\Pablo Infantes\\Documents\\Claves\\Libro.json";
    
    public static void createBook() throws IOException{
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
       // writer = new JsonWriter(new FileWriter("C:\\Users\\Pablo Infantes\\Documents\\Claves\\Libro.json"));
        String json = gson.toJson(testSettings());
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(json);
        }
    }
    
    public static ArrayList<Book> testSettings(){
        ArrayList<Book> bookList = new ArrayList();
        for(int n=0;n<4;n++){
            bookList.add(new Book(n,21, (10+n), "Libroo "+n, "Es un libro muy bonito. Numero "+n));
        }
        return bookList;
    }
    
    public static void writeFile(String test) throws IOException{
            
    }
    
    public static void readBooks() throws FileNotFoundException{
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileReader fr = new FileReader(path);
        Type listType = new TypeToken<ArrayList<Book>>(){}.getType();
        ArrayList<Book> books = gson.fromJson(fr, listType);
        
        for(Book book : books){
            System.out.println(book.toString());
        }
    }
    
}
