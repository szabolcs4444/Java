package beadando;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;


public class JsonWriter {
	public static void JsoncsvWriter(String fnev, DramaTM drm) {
		JSONObject jsonObject = new JSONObject();
		try {
			int rdb = drm.getRowCount();
			int cdb = drm.getColumnCount();
			FileWriter file = new FileWriter(fnev + ".json");
			for (int i = 0; i < rdb; i++) {
				for (int j = 1; j < cdb - 1; j++) {
					jsonObject.put("Sorszam", drm.getValueAt(i, 1).toString());
					j++;
					jsonObject.put("Idopont", drm.getValueAt(i, 2).toString());
					j++;
					jsonObject.put("Nev", drm.getValueAt(i, 3).toString());
					j++;
					jsonObject.put("Problema", drm.getValueAt(i, 4).toString());
					j++;
					jsonObject.put("Prioritas", drm.getValueAt(i, 5).toString());
				}				
					file.write(jsonObject.toJSONString());
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
			SM("Nem jott letre JSON file!:", 0);
		}
		SM("JSON file created!", 1);
	}
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
}
