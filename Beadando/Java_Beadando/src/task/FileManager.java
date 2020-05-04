package task;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FileManager {

	public static DramaTableModel readCsv(File fileName, DramaTableModel dramaTableModel) {
		Object[] objectDrama = { "Signal", "Identifier", "Title", "Director", "Performance date", "Ticket price" };
		DramaTableModel dramaReader = new DramaTableModel(objectDrama, 0);
		try {

			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String string = bufferedReader.readLine();
			string = bufferedReader.readLine();
			while (string != null) {
				String[] separator = string.split(";");
				dramaReader.addRow(
						new Object[] { false, separator[0], separator[1], separator[2], separator[3], separator[4] });
				string = bufferedReader.readLine();

			}
			bufferedReader.close();
			JOptionPane.showMessageDialog(null, "File opened and load successfully!", "Program message", 1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Scan failed! Error: " + e.getMessage(), " Program message", 0);

		}
		return dramaReader;
	}

	public static void insert(String fileName, String identifier, String title, String director, String performanceDate,
			String ticketPrice) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fileName, true));
			String printout = String.join(";", identifier, title, director, performanceDate, ticketPrice);
			out.println(printout);

			out.close();
			JOptionPane.showMessageDialog(null, "Data write success! ", " Program message", 1);

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "CsvWriter:" + e.getMessage(), " Program message", 0);

		}
	}

	public static void modify(File fileName, DramaTableModel dramaTableModel) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fileName));
			out.println("Identifier,Title,Director,Performance date,Ticket price");
			for (int i = 0; i < dramaTableModel.getRowCount(); i++) {
				String identifier = dramaTableModel.getValueAt(i, 1).toString();
				String title = dramaTableModel.getValueAt(i, 2).toString();
				String director = dramaTableModel.getValueAt(i, 3).toString();
				String performanceDate = dramaTableModel.getValueAt(i, 4).toString();
				String ticketPrice = dramaTableModel.getValueAt(i, 5).toString();
				String printout = String.join(";", identifier, title, director, performanceDate, ticketPrice);
				out.println(printout);

			}
			out.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Data delete unsuccesful! Error: " + e.getMessage(), "Program message",
					0);

		}
	}

	public static void writeCsv(String fileName, DramaTableModel dramaTableModel) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fileName + ".csv"));
			out.println("Identifier;Title;Director;Performance date;Ticket price");
			int getRowCount = dramaTableModel.getRowCount();
			int getCoulmnCount = dramaTableModel.getColumnCount();
			for (int i = 0; i < getRowCount; i++) {
				for (int j = 1; j < getCoulmnCount - 1; j++) {
					out.print("" + dramaTableModel.getValueAt(i, j) + ";");
				}
				out.println("" + dramaTableModel.getValueAt(i, getCoulmnCount - 1));
			}
			out.close();
			JOptionPane.showMessageDialog(null, "Data save successful! ", " Program message", 1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Data save unsuccessful! ", " Program message", 0);
		}
	}

}
