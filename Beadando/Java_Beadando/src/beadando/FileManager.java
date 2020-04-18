package beadando;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FileManager {
	private static String uzenet = "Feladat üzenet";
	public static DramaTM CsvReader(File fnev,DramaTM drm) {
		Object drama[] = {"Jel","Azonosító","Cím","Rendezõ","Elsõ Bemutatás","Jegyár"};
		DramaTM drm2 = new DramaTM(drama,0);
		try {
		
		BufferedReader in = new BufferedReader(new FileReader(fnev));
		String s = in.readLine();
		s=in.readLine();
		while(s!=null) {
			String[] st = s.split(";");
			drm2.addRow(new Object[] {
					false,st[0],st[1],st[2],st[3],st[4]
			});
			s=in.readLine();
			
		}
		in.close();
		SM("File megnyitása és a beolvasás megtörtént!",1);
		}catch(IOException e) {
			SM("A Beolvasás sikertelen! A hiba: "+e.getMessage(),0);
		}
		return drm2;
	}
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
	public static void Insert(String fnev,String azonosito,String cim,String rendezo,String elsobemutatas,String jegyar) {
		String x = ";";
		try {
			PrintStream out = new PrintStream (new FileOutputStream(fnev,true));
			out.println(azonosito+x+cim+x+rendezo+x+elsobemutatas+x+jegyar);
			
			out.close();
			SM("Adatok kiírása sikeres!",1);
			
		}catch(IOException e) {
		SM("CsvWriter:"+e.getMessage(),0);
		
		}
	}
	
	public static void Modosit(File fnev,DramaTM drm) {
		String x =";";
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fnev));
			out.println("Azonosito;Cim,Rendezo,Elsobemutatas,Jegyar");
			for(int i =0;i<drm.getRowCount();i++) {
				String azonosito = drm.getValueAt(i, 1).toString();
				String cim = drm.getValueAt(i, 2).toString();
				String rendezo = drm.getValueAt(i, 3).toString();
				String elsobemutatas = drm.getValueAt(i, 4).toString();
				String jegyar = drm.getValueAt(i, 5).toString();
				out.println(azonosito+x+cim+x+rendezo+x+elsobemutatas+x+jegyar);
				
			}
			out.close();
		}catch(IOException e) {
			SM("A törlés Sikertelen! A hiba: "+e.getMessage(),0);
		}
	}
	
	public static void CsvWriter(String fnev , DramaTM drm) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fnev+".csv"));
			out.println("Azonosito;Cim;Rendezo;Elsobemutato;Jegyar");
			int grc = drm.getRowCount();
			int gcc = drm.getColumnCount();
			for(int i = 0;i<grc;i++) {
				for(int j=1;j<gcc-1;j++) {
					out.print("" + drm.getValueAt(i, j)+ ";");
				}
				out.println(""+ drm.getValueAt(i, gcc-1));
			}
			out.close();
			JOptionPane.showMessageDialog(null, "Adatok mentése sikeres!",uzenet,1);
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Adatok mentése sikertelen!",uzenet,0);
		}
	}

}
