package beadando;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.*;

import javax.swing.JOptionPane;

public class DBMethods {
	private Statement s = null;
	private Connection conn=null;
	private ResultSet rs = null;
	
	
	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
			SM("Sikeres driver regisztráció!",1);
		}catch(ClassNotFoundException e ) {
			SM("Hibás driver regisztráció!"+e.getMessage(),0);
		}
	}
	
	public void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
	
	public void Connect() {
		try {
			String url = "jdbc:sqlite:D:/targyak/java/SQLite/dramadb";
			conn = DriverManager.getConnection(url);
			SM("Sikeres kapcsolódás!",1);
		}catch(SQLException e) {
			SM("A Kapcsolódás sikertelen a hiba: "+e.getMessage(),0);
		}
	}
	
	public void Disconnect() {
		if(conn!= null) {
			try {
				conn.close();
				SM("Sikeres lecsatlakozás!",1);
				
			}catch(SQLException e) {
				SM("Sikertelen lecsatlakozás a hiba: "+e.getMessage(),0);
			}
		}
	}
	
	public DramaTM ReadAllData(String ss) {
		Object drama[] = {"Jel","Azonosító","Cím","Rendezõ","Elsõ Bemutatás","Jegyár"};
		DramaTM drm = new DramaTM(drama,0);
		String cim="",rendezo="",elsobemutatas="";
		int azonosito = 0,jegyar =0;
		String sqlp = "select azonosito,cim,rendezo,elsobemutatas,jegyar from drama";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				azonosito = rs.getInt("azonosito");
				cim = rs.getString("cim");
				rendezo = rs.getString("rendezo");
				elsobemutatas = rs.getString("elsobemutatas");
				jegyar = rs.getInt("jegyar");
				drm.addRow(new Object[] {false,azonosito,cim,rendezo,elsobemutatas,jegyar});
			}
			rs.close();
		}catch(SQLException e) {
			SM(e.getMessage(),0);
		}
		return drm;
	}
	
	public void Insert(String azonosito,String cim,String rendezo,String elsobemutatas,String jegyar) {
		String sqlp= "insert into drama values("+azonosito+",'"+cim+"','"+rendezo+"','"+elsobemutatas+"',"+jegyar+")";
		try {
			s = conn.createStatement();
			s.execute(sqlp);
			SM("Adatfelvétel megtörtént! ",1);
			
		}catch(SQLException e) {
			SM("Adatfelvétel hiba : "+e.getMessage(),0);
		}
	}
	
	public void DeleteData(String azonosito) {
		String sqlp = "delete from drama where azonosito="+azonosito;
		try {
			s = conn.createStatement();
			s.execute(sqlp);
			SM("A törlés sikeres!",1);
		}catch(SQLException e) {
			SM("A törlés sikertelen! "+e.getMessage(),0);
		}
	}
	
	public void Update(String azonosito,String mnev, String madat) {
		String sqlp = "update drama set "+mnev+ "='"+madat+"' where azonosito ="+azonosito;
		try {
			s = conn.createStatement();
			s.execute(sqlp);
			SM("Módosítás megtörtént!",1);		
		}catch(SQLException e) {
			SM("JDBC Update: "+e.getMessage(),0);
		}
	}
	
	public  void DBWriter(String string,DramaTM drm) {
		try{
			Class.forName("org.sqlite.JDBC");
			SM("Sikeres driver regisztráció!",1);
			String url = "jdbc:sqlite:D:/targyak/java/SQLite/dramadb";
			conn = DriverManager.getConnection(url);
			SM("Sikeres kapcsolódás!", 1);
		    s = conn.createStatement();   
		    String sql = "CREATE TABLE '"+string+"'(\n"
		    		+ " azonosito integer primary key,\n " 
		    		+ " cim text,\n " 
		    		+ " rendezo text, \n" 
		    		+ " elsobemutatas date,\n " 
		    		+ " jegyar integer);"; 

		    s.executeUpdate(sql);
		    SM("Tábla létrehozása sikeres!", 1);
		    int grc = drm.getRowCount();
			int gcc = drm.getColumnCount();
			 for (int i = 0; i < grc; i++) {
				for (int j = 1; j < gcc - 1; j++) {
					String azonosito=drm.getValueAt(i, j).toString();
					j++;
					String cim=drm.getValueAt(i, j).toString();
					j++;
					String rendezo=drm.getValueAt(i, j).toString();
					j++;
					String elsobemutatas=drm.getValueAt(i, j).toString();
					j++;
					String jegyar=drm.getValueAt(i, j).toString();
					String sqlp= "insert into '"+string+"' values("+azonosito+", '"+cim+"', '"+rendezo+"', '"+elsobemutatas+"', "+jegyar+")";
					s=conn.createStatement();
					s.execute(sqlp);
			}	
		}	
			
			 SM("Tábla feltöltés sikeres!",1);
			
		   }catch(SQLException se){		   
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(s!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   }
	}
	
	
	
}
