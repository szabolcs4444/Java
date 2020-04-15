import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JOptionPane;

public class FileManager {
		public static EmpTM CsvReader() {
			Object emptmn[] = {"Jel","Kód","Név","Szülidõ","Lakóhely","Fizetés"};
			EmpTM etm = new EmpTM(emptmn,0);
			
			
			try {
				BufferedReader in = new BufferedReader(new FileReader("adatok.csv"));
				String s = in.readLine();
				s = in.readLine();
				while(s!=null) {
					String [] st = s.split(";");
					etm.addRow(new Object [] {false, st[0],st[1],st[2],st[3],st[4]});
					s=in.readLine();
					}
				in.close();
					
				}catch(IOException ioe) {
					System.err.println(ioe.getMessage());
									
					
			}
			return etm;
			
		}
		
	public static void Insert(EmpTM etm) {
		String x = ";";
		try {
			PrintStream out = new PrintStream (new FileOutputStream("adatok.csv"));
			out.println("Kód;Név;Születési_idõ;Lakcím;Fizetés");
			for(int i = 0;i<etm.getRowCount();i++) {
				String kod = etm.getValueAt(i, 1).toString();
				String nev = etm.getValueAt(i, 2).toString();
				String szid = etm.getValueAt(i, 3).toString();
				String lak = etm.getValueAt(i, 4).toString();
				String fiz = etm.getValueAt(i, 5).toString();
				out.println(kod+x+nev+x+szid+x+lak+x+fiz);
			}
			out.close();
			
			
		}catch(IOException e) {
		SM("FM.Insert:"+e.getMessage(),0);
		
		}
	}
	public static void SM(String msg, int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
	public static void Insert(String kod,String nev,String szid,String lak,String fiz) {
		String x = ";";
		try {
			PrintStream out = new PrintStream (new FileOutputStream("adatok.csv",true));
			out.println(kod+x+nev+x+szid+x+lak+x+fiz);
			
			out.close();
			SM("Adatij kiírva!",1);
			
		}catch(IOException e) {
		SM("CsvWriter:"+e.getMessage(),0);
		
		}
	}
		
}
