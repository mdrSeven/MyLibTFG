/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Objects.Bill;
import Objects.Book;
import Objects.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Pablo Infantes
 */
public class PdfHelper {

    private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
    private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);

    private static final Font categoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    private static final Font subcategoryFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font blueFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

    public static void createPDF(Bill b) throws FileNotFoundException {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            try {
                PdfWriter.getInstance(document, new FileOutputStream("C://MyLib//Factura" + b.getBillNumber() + ".pdf"));
            } catch (FileNotFoundException fileNotFoundException) {
            }
            document.open();

            document.add(new Paragraph("Num factura: " + b.getBillNumber(), paragraphFont));
            document.add(new Paragraph("DNI Cliente: " + b.getAssignedClient().getDni(), paragraphFont));

            PdfPTable table = new PdfPTable(6);
            String[] headers = {"Código", "Nombre", "Cantidad", "IVA", "Precio", "Total"};
            for (int n = 0; n < 6; n++) {
                PdfPCell header = new PdfPCell(new Phrase(headers[n]));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(header);
            }
            table.setHeaderRows(1);
            
            ArrayList<Product> productos = b.getProducts();
            for (int row = 0; row < productos.size(); row++) {
                Product p = productos.get(row);
                Book book = JsonHelper.searchBook(p.getCode());
                for (int col = 0; col < 6; col++) {
                   table.addCell(book.getCode());
                   table.addCell(book.getName());
                   table.addCell(""+p.getAmount());
                   table.addCell(""+book.getIvaPercentage());
                   table.addCell(""+book.getPrice());
                   table.addCell(""+book.getPrice() * p.getAmount());
                }
            }
            document.add(table);
            document.close();
        } catch (DocumentException documentException) {
        }
    }
}
