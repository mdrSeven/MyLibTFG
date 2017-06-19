/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import Objects.Bill;
import Objects.Book;
import Objects.Client;
import Objects.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static void createPDF(Bill b) throws FileNotFoundException, BadElementException, IOException {
        try {
            Document document = new Document(PageSize.A4, 20, 20, 20, 20);
            File f = new File("C:\\MyLib\\FacturasPDF\\Factura n" + b.getBillNumber() + ".pdf");
            if (f.exists()) {
                f.delete();
            }
            try {
                PdfWriter.getInstance(document, new FileOutputStream("C:\\MyLib\\FacturasPDF\\Factura n" + b.getBillNumber() + ".pdf"));
            } catch (FileNotFoundException fileNotFoundException) {
            }
            document.open();
            
            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setSpacingAfter(10f);
            
            
            PdfPCell cellData = new PdfPCell(new Phrase("CES SAN JOSÉ\nCalle Virgen de las Flores, 23\n29007\nMálaga"));
            cellData.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellData.setBorder(Rectangle.NO_BORDER);
            
            headerTable.addCell(cellData);
            document.add(headerTable);
            
            PdfPTable billNumTable = new PdfPTable(2);
            billNumTable.setSpacingAfter(5f);
            billNumTable.setSpacingBefore(5f);
            
            PdfPCell cellLabel = new PdfPCell(new Phrase("Num.Factura: "));
            cellLabel.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellLabel.setBorder(PdfPCell.TOP | PdfPCell.LEFT | PdfPCell.BOTTOM);
            
            PdfPCell cellBillNum = new PdfPCell(new Phrase(""+b.getBillNumber()));
            cellBillNum.setBorder(PdfPCell.TOP | PdfPCell.RIGHT | PdfPCell.BOTTOM);
            cellBillNum.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            billNumTable.addCell(cellLabel);
            billNumTable.addCell(cellBillNum);
            document.add(billNumTable);
            
            Client c = b.getAssignedClient();
            if (c == null) {
                PdfPTable tableClient = new PdfPTable(2);
            } else {
                PdfPTable tableClient = new PdfPTable(2);
                tableClient.setSpacingAfter(5f);
                PdfPCell cellName, cellDni, cellAddress, cellPoblation;
                cellName = new PdfPCell(new Phrase("NOMBRE: "+c.getName()));
                cellName.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellName.setBorder(PdfPCell.LEFT | PdfPCell.TOP);
                
                cellDni = new PdfPCell(new Phrase("DNI: "+c.getDni()));
                cellDni.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellDni.setBorder(PdfPCell.RIGHT | PdfPCell.TOP);
                
                cellAddress = new PdfPCell(new Phrase("DOMICILIO: "+c.getAddress()));
                cellAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellAddress.setBorder(PdfPCell.LEFT | PdfPCell.BOTTOM);
                
                cellPoblation = new PdfPCell(new Phrase("POBLACIÓN: "+c.getPoblation()));
                cellPoblation.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellPoblation.setBorder(PdfPCell.RIGHT | PdfPCell.BOTTOM);
                
                tableClient.addCell(cellName);
                tableClient.addCell(cellDni);
                tableClient.addCell(cellAddress);
                tableClient.addCell(cellPoblation);
                document.add(tableClient);
                
            }
            PdfPTable productsTable = new PdfPTable(6);
            String[] headers = {"Código", "Nombre", "Cantidad", "IVA", "Precio", "Total"};
            for (int n = 0; n < 6; n++) {
                PdfPCell header = new PdfPCell(new Phrase(headers[n]));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                productsTable.addCell(header);
            }
            productsTable.setHeaderRows(1);

            ArrayList<Product> productos = b.getProducts();
            for (int row = 0; row < productos.size(); row++) {
                Product p = productos.get(row);
                Book book = JsonHelper.searchBook(p.getCode());
                String[] data = {book.getCode(), book.getName(), "" + p.getAmount(), "" + book.getIvaPercentage(), "" + book.getPrice(), String.format("%.2f", book.getPrice() * p.getAmount())};
                for (int col = 0; col < 6; col++) {
                    PdfPCell cell = new PdfPCell(new Phrase(data[col]));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    if (row == productos.size() - 1) {
                        cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT | PdfPCell.BOTTOM);
                    } else {
                        cell.setBorder(PdfPCell.LEFT | PdfPCell.RIGHT);
                    }
                    productsTable.addCell(cell);
                }
            }
            document.add(productsTable);
            
            PdfPTable footTable = new PdfPTable(3);
            footTable.setSpacingBefore(5f);
            String[] headersFoot = {"Descuento", "SubTotal", "Total"};
            for (int n = 0; n < 3; n++) {
                PdfPCell header = new PdfPCell(new Phrase(headersFoot[n]));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                footTable.addCell(header);
            }
            footTable.addCell(new Phrase(b.getDiscount()+"%"));
            footTable.addCell(new Phrase(""+b.getSubTotal()));
            footTable.addCell(new Phrase(""+b.getTotal()));
            
            document.add(footTable);
            
            PdfPTable dateTable = new PdfPTable(1);
            dateTable.setSpacingBefore(10f);
            
            PdfPCell date = new PdfPCell(new Phrase(""+b.getDate()));
            date.setHorizontalAlignment(Element.ALIGN_RIGHT);
            date.setBorder(Rectangle.NO_BORDER);
            dateTable.addCell(date);
            
            document.add(dateTable);
            document.close();
        } catch (DocumentException documentException) {
        }
    }

}
