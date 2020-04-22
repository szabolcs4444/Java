package task;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

public class JsonWriter {
	public static void JsoncsvWriter(String fileName, DramaTM drama) {
		JSONObject jsonObject = new JSONObject();
		try {
			int getRowCount = drama.getRowCount();
			int getColumnCount = drama.getColumnCount();
			FileWriter file = new FileWriter(fileName + ".json");
			for (int i = 0; i < getRowCount; i++) {
				for (int j = 1; j < getColumnCount - 1; j += 5) {
					jsonObject.put("Identifier", drama.getValueAt(i, 1).toString());
					jsonObject.put("Title", drama.getValueAt(i, 2).toString());
					jsonObject.put("Director", drama.getValueAt(i, 3).toString());
					jsonObject.put("Performance date", drama.getValueAt(i, 4).toString());
					jsonObject.put("Ticket price", drama.getValueAt(i, 5).toString());
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
