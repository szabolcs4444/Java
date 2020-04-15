import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checker {
	private SimpleDateFormat RDF = new SimpleDateFormat("yyyy.MM.dd");
	public boolean filled(JTextField a,String an) {
		String s = RTF(a);
		if(s.length()>0) {
			return true;
		}else {
			SM("Hiba: a(z)"+an+"mez� �res",0);
			return false;
		}
	}
	
	public boolean goodInt(JTextField a,String an) {
		String s = RTF(a);
		boolean b = filled(a,an);
		if(b) try {
			Integer.parseInt(s);
		}catch(NumberFormatException e) {
			SM("A(z)"+an+"mez�ben hib�s a sz�madat!",0);
			b = false;
		}
		return b;
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program �zenet",tipus);
	}
	
	public boolean DateFormatChecker(String SDate) {
		try {
			Date date = RDF.parse(SDate);
			return true;
		}catch(ParseException ef){
			return false;
		}
	}
	
	public boolean goodDate(JTextField a,String an) {
		String s = RTF(a);
		boolean b = filled(a,an);
		if(b&& DateFormatChecker(s)) {
			return true;
		}else {
			SM("A(z)"+an+" mez�ben hib�s d�tum!",0);
			return false;
		}
	}
	public boolean filled(JTextField a) {
		String s = RTF(a);
		if(s.length()>0) {
			return true;
		
		}else {
			return false;
		}
	}
	public int stringTont(String s) {
		int x = -1;
		try {
			x = Integer.valueOf(s);
		}catch(NumberFormatException nfe) {
			SM("stringToInt: " +nfe.getMessage(),0);
		}
		return x;
	}

}
