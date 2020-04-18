package beadando;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class DramaDel extends JDialog {
	private JTable table;
	private DramaTM drm;
	private Checker c = new Checker();
	private DBMethods dbm = new DBMethods();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DramaDel(JFrame f, DramaTM bdrm,int dbkez,File fbetolt) {
		super(f,"Színdarab törlése",true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		drm = bdrm;
		setBounds(100, 100, 850, 300);
		getContentPane().setLayout(null);
		
		JButton btnKilps = new JButton("Kil\u00E9p\u00E9s");
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilps.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnKilps.setBackground(Color.RED);
		btnKilps.setBounds(352, 231, 169, 23);
		getContentPane().add(btnKilps);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 800, 200);
		getContentPane().add(scrollPane);
		
		table = new JTable(drm);
		scrollPane.setViewportView(table);
		
		JButton btnTorles = new JButton("T\u00F6rl\u00E9s");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0,jel = 0,x = 0;
				for(x = 0;x<drm.getRowCount();x++) 
					if((Boolean)drm.getValueAt(x, 0)){
						db++;
						jel=x;
					}
					if(db==0)c.SM("Nincs kijelölve rekord", 0);
					
					if(db>1)c.SM("Több rekord van kijelölve!\n Egyszerre csak egy rekord törölhetõ", 0);
					
					if(db==1) {
						String azonosito = drm.getValueAt(jel, 1).toString();
						drm.removeRow(jel);
						if(dbkez==0) {
							FileManager.Modosit(fbetolt,drm);	
						}else if(dbkez==1){
							dbm.Connect();
							dbm.DeleteData(azonosito);
							dbm.Disconnect();
						}
						
						dispose();
						c.SM("A rekord törölve", 1);
					}
				
				
			}
		});
		btnTorles.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnTorles.setBackground(new Color(0, 128, 0));
		btnTorles.setBounds(83, 231, 169, 23);
		getContentPane().add(btnTorles);

		TableColumn tc = null;
		for(int i = 0;i<6;i++) {
			tc = table.getColumnModel().getColumn(i);
			if(i==0) {
				tc.setPreferredWidth(70);
			
			}else if(i==1){
				tc.setPreferredWidth(120);
			}
			
			
			else if(i==2||i==3||i==4) {
				tc.setPreferredWidth(400);
			}else {
				tc.setPreferredWidth(100);
			}
			
		}
		
		
		
	}
}
