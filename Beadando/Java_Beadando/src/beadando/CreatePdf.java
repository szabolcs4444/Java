package beadando;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdf {
	public static void PdfWrite(String file,DramaTM drm) {
		try {
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream(file+ ".pdf"));
			doc.open();
			
			addContent(doc, drm);
			doc.close();
			SM("A pdf sikeresen létrejött!",1);
		}catch(Exception e) {
			e.printStackTrace();
			SM("A pdf létrehozása sikertelen!",0);
		}
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
	
	private static void createTable(Section drama,DramaTM drm) throws BadElementException{
		PdfPTable table = new PdfPTable(5);
		PdfPCell cella = new PdfPCell(new Phrase("Azonosito"));
		cella.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cella);
		
		cella = new PdfPCell(new Phrase("Cim"));
		cella.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cella);
		
		cella = new PdfPCell(new Phrase("Rendezo"));
		cella.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cella);
		
		cella = new PdfPCell(new Phrase("Bemutatas"));
		cella.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cella);
		
		cella = new PdfPCell(new Phrase("Jegyar"));
		cella.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cella);
		
		table.setHeaderRows(1);
		int grc = drm.getRowCount();
		int gcc = drm.getColumnCount();
		for(int i = 0;i<grc;i++) {
			for(int j=1;j<gcc;j++) {
				table.addCell(drm.getValueAt(i, j).toString());
			}
		}
		drama.add(table);
	}
	private static void addContent(Document doc,DramaTM drm) throws DocumentException{
		Chapter ct = new Chapter(1);
		Paragraph pg = new Paragraph("Táblázat:");
		Section drama = ct.addSection(pg);
		createTable(drama,drm);
		doc.add(ct);
	}
	
	
}
