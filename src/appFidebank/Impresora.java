package appFidebank;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import javax.swing.JTable;


public class Impresora {

    public static void imprimir(JTable tabla, String nombreArchivo) {
        Document documento = new Document() {};
        try {
            PdfWriter.getInstance(documento, new FileOutputStream(nombreArchivo));
            documento.open();

            Paragraph titulo = new Paragraph("Resumen detallado de movimientos bancarios",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16));
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            documento.add(new Paragraph(" "));
            documento.add(new Paragraph("Detalle:",
                    FontFactory.getFont(FontFactory.HELVETICA, 12)));

            documento.add(new Paragraph(" "));

            PdfPTable pdfTabla = new PdfPTable(tabla.getColumnCount());
            pdfTabla.setWidthPercentage(100);

            for (int i = 0; i < tabla.getColumnCount(); i++) {
                PdfPCell celda = new PdfPCell(new Phrase(tabla.getColumnName(i)));
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                celda.setPadding(5);
                pdfTabla.addCell(celda);
            }

            for (int fila = 0; fila < tabla.getRowCount(); fila++) {
                for (int col = 0; col < tabla.getColumnCount(); col++) {
                    Object valor = tabla.getValueAt(fila, col);
                    pdfTabla.addCell(valor != null ? valor.toString() : "");
                }
            }

            documento.add(pdfTabla);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
}
