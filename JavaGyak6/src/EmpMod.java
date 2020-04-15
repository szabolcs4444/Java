import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;



import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class EmpMod extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private EmpTM etm;
	private DBMethods dbm = new DBMethods();
	
	private Checker c = new Checker();
	private JTextField kod;
	private JTextField nev;
	private JTextField szid;
	private JTextField lak;
	private JTextField fiz;
	//private DbMethods dbm = new DbMethods();
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public EmpMod(JFrame f,EmpTM betm,int dbkez) {
		super(f,"Dolgozók Módosítása",true);
		etm = betm;
		
		setBounds(100, 100, 519, 396);
		
		JButton btnBezar = new JButton("bez\u00E1r");
		btnBezar.setBounds(152, 231, 89, 23);
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnBezar);
		
		
		
		
		JButton btnAdatsorTrlse = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnAdatsorTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db =0,jel = 0,x=0;
				for(x = 0;x<etm.getRowCount();x++)
					if((Boolean)etm.getValueAt(x, 0)) {
						db++;
						jel = x;
					}
				if(db==0)
					c.SM("Nincs kijelölve a módosítandó rekord", 0);
				if(db>1)
					c.SM("Több rekord van kijelölve,egyszerre csak egyet lehet", 0);
				
				if(db==1) {
					if(modDataPc()>0) {
						boolean ok = true;
						if(c.filled(kod)) ok = c.goodInt(kod, "Kód");
						if(ok && c.filled(fiz)) ok = c.goodInt(fiz, "Fizetés");
						if(ok) {
							if(dbkez == 1) {
								String mkod = etm.getValueAt(jel, 1).toString();
								dbm.Connect();
								if(c.filled(nev)) dbm.Update(mkod, "nev", c.RTF(nev));
								if(c.filled(szid)) dbm.Update(mkod, "szulido", c.RTF(szid));
								if(c.filled(lak)) dbm.Update(mkod, "lakohely", c.RTF(lak));
								if(c.filled(fiz)) dbm.Update(mkod, "fizetes", c.RTF(fiz));
								if(c.filled(kod)) dbm.Update(mkod, "kod", c.RTF(kod));
								dbm.Disconnect();
							}
							
							if(c.filled(kod)) etm.setValueAt(c.stringTont(c.RTF(kod)), jel, 1);
							if(c.filled(nev)) etm.setValueAt(c.RTF(nev), jel, 2);
							if(c.filled(szid)) etm.setValueAt(c.RTF(szid), jel, 3);
							if(c.filled(lak)) etm.setValueAt(c.RTF(lak), jel,	4);
							if(c.filled(fiz)) etm.setValueAt(c.stringTont(c.RTF(fiz)), jel, 5);
							
							if(dbkez == 0) {
								FileManager.Insert(etm);
								jel = 0;
								
							}
						}else {
							c.SM("Nincs kitöltve egyetlen módosító mezõ sem", 1);
						}
					}
				}
			}
		});
		btnAdatsorTrlse.setBounds(10, 231, 120, 23);
		getContentPane().add(btnAdatsorTrlse);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 462, 296);
		getContentPane().add(scrollPane);
		table = new JTable(etm);
		scrollPane.setRowHeaderView(table);
		
		
		
		kod = new JTextField();
		kod.setBounds(10, 318, 53, 20);
		getContentPane().add(kod);
		kod.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(73, 318, 96, 20);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		szid = new JTextField();
		szid.setBounds(180, 318, 96, 20);
		getContentPane().add(szid);
		szid.setColumns(10);
		
		lak = new JTextField();
		lak.setBounds(295, 318, 96, 20);
		getContentPane().add(lak);
		lak.setColumns(10);
		
		fiz = new JTextField();
		fiz.setBounds(401, 318, 96, 20);
		getContentPane().add(fiz);
		fiz.setColumns(10);
		
		
		TableColumn tc = null;
		for(int i = 0;i<6;i++) {
			tc = table.getColumnModel().getColumn(i);
			if(i ==0 || i==1) tc.setPreferredWidth(30);
			else if(i == 4)	tc.setPreferredWidth(150);
			else {
				tc.setPreferredWidth(100);
			}
		}
		
		
		
		}
	
	public int modDataPc() {
		int pc = 0;
		if(c.filled(kod)){
			pc++;
		}
		if(c.filled(nev)) {
			pc++;
		}
		if(c.filled(szid)) {
			pc++;
		}
		if(c.filled(lak)) {
			pc++;
		}
		if(c.filled(fiz)) {
			pc++;
		}
		return pc;
	}
}
