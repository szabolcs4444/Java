import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpDel extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private EmpTM etm;
	private Checker c = new Checker();
	private DBMethods dbm = new DBMethods();
	//private DbMethods dbm = new DbMethods();
	
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public EmpDel(JFrame f,EmpTM betm,int dbkez) {
		super(f,"Dolgozók törlése",true);
		etm = betm;
		setBounds(100, 100, 450, 300);
		
		JButton btnBezar = new JButton("bez\u00E1r");
		btnBezar.setBounds(152, 231, 89, 23);
		btnBezar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnBezar);
		
		JButton btnAdatsorTrlse = new JButton("Adatsor T\u00F6rl\u00E9se");
		btnAdatsorTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0,jel = 0,x = 0;
				for(x = 0;x<etm.getRowCount();x++) {
					if((Boolean)etm.getValueAt(x, 0)){
						db++;
						jel=x;
					}
					if(db==0) {
						c.SM("Nincs kijelölve rekord", 0);
					}
					if(db>1) {
						c.SM("Több rekord van kijelölve!\n Egyszerre csak egy rekord törölhetõ", 0);
					}
					if(db==1) {
						String kod = etm.getValueAt(jel, 1).toString();
						etm.removeRow(jel);
						if(dbkez==0) {
							FileManager.Insert(etm);	
						}else {
							dbm.Connect();
							dbm.DeleteData(kod);
							dbm.Disconnect();
						}
						
						dispose();
						c.SM("A rekord törölve", 1);
					}
				}
				
			}
		});
		btnAdatsorTrlse.setBounds(10, 231, 120, 23);
		getContentPane().add(btnAdatsorTrlse);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 438, 265);
		getContentPane().add(scrollPane);
		
		table = new JTable(etm);
		scrollPane.setViewportView(table);
		
		
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
}
