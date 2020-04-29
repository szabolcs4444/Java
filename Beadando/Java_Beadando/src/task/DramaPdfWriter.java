package task;

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

public class DramaPdfWriter {
	public static void write(String file, DramaTableModel dramaTableModel) {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(file + ".pdf"));
			document.open();

			addContent(document, dramaTableModel);
			document.close();
			JOptionPane.showMessageDialog(null, "pdf successful created", "Program message", 1);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "pdf creation failed: " + e.getMessage(), " Program message", 0);

		}
	}

	private static void createTable(Section section, DramaTableModel dramaTableModel) throws BadElementException {
		PdfPTable table = new PdfPTable(5);
		PdfPCell cell = new PdfPCell(new Phrase("Identifier"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Title"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Director"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Performance date"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase("Ticket price"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		table.setHeaderRows(1);
		int getRowCount = dramaTableModel.getRowCount();
		int getColumnCount = dramaTableModel.getColumnCount();
		for (int i = 0; i < getRowCount; i++) {
			for (int j = 1; j < getColumnCount; j++) {
				table.addCell(dramaTableModel.getValueAt(i, j).toString());
			}
		}
		section.add(table);
	}

	private static void addContent(Document document, DramaTableModel dramaTableModel) throws DocumentException {
		Chapter chapter = new Chapter(1);
		Paragraph paragraph = new Paragraph("spreadsheet:");
		Section dramaSection = chapter.addSection(paragraph);
		createTable(dramaSection, dramaTableModel);
		document.add(chapter);
	}

}
