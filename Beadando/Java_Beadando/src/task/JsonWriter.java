package task;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

public class JsonWriter {
	public static void write(String fileName, DramaTableModel dramaTableModel) {
		JSONObject jsonObject = new JSONObject();
		try {
			int getRowCount = dramaTableModel.getRowCount();
			int getColumnCount = dramaTableModel.getColumnCount();
			FileWriter file = new FileWriter(fileName + ".json");
			for (int i = 0; i < getRowCount; i++) {
				for (int j = 1; j < getColumnCount - 1; j += 5) {
					jsonObject.put("Identifier", dramaTableModel.getValueAt(i, 1).toString());
					jsonObject.put("Title", dramaTableModel.getValueAt(i, 2).toString());
					jsonObject.put("Director", dramaTableModel.getValueAt(i, 3).toString());
					jsonObject.put("Performance date", dramaTableModel.getValueAt(i, 4).toString());
					jsonObject.put("Ticket price", dramaTableModel.getValueAt(i, 5).toString());
				}
				file.write(jsonObject.toJSONString());
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "the JSON file  was not created!: " + e.getMessage(),
					" Program message", 0);
		}
		JOptionPane.showMessageDialog(null, "JSON file created!", " Program message", 1);

	}

}
