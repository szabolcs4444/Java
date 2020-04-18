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
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DramaMod extends JDialog {
	private JTable table;
	private DramaTM drm;
	private Checker c = new Checker();
	private DBMethods dbm = new DBMethods();
	private JTextField textAzonosito;
	private JTextField textCim;
	private JTextField textRendezo;
	private JTextField textElsobemutatas;
	private JTextField textJegyar;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DramaMod(JFrame f, DramaTM bdrm,int dbkez,File fbetolt) {
		super(f,"Színdarab Módosítása",true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		drm = bdrm;
		setBounds(100, 100, 850, 400);
		getContentPane().setLayout(null);
		
		JButton btnKilps = new JButton("Kil\u00E9p\u00E9s");
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilps.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnKilps.setBackground(Color.RED);
		btnKilps.setBounds(631, 331, 169, 23);
		getContentPane().add(btnKilps);
		
		JScrollPane scrollPane = new JScrollPane();
		/*scrollPane.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int db =0,jel = 0,x=0;
				
				for(x = 0;x<drm.getRowCount();x++)
					if((Boolean)drm.getValueAt(x, 0)) {
						db++;
						jel = x;
						if(db==1) {
	            textAzonosito.setText(drm.getValueAt(x, 1).toString());
	            textCim.setText(drm.getValueAt(x, 2).toString());
	            textElsobemutatas.setText(drm.getValueAt(x, 3).toString());
	            textElsobemutatas.setText(drm.getValueAt(x, 4).toString());
	            textJegyar.setText(drm.getValueAt(x, 5).toString());
	            
						}
			}
			}
		});*/
		scrollPane.setBounds(0, 11, 800, 200);
		getContentPane().add(scrollPane);
		
		table = new JTable(drm);
		scrollPane.setViewportView(table);
		
		JButton btnTorles = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db =0,jel = 0,x=0;
				for(x = 0;x<drm.getRowCount();x++)
					if((Boolean)drm.getValueAt(x, 0)) {
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
						if(c.filled(textAzonosito)) ok = c.goodInt(textAzonosito, "Kód");
						if(ok && c.filled(textJegyar)) ok = c.goodInt(textJegyar, "Fizetés");
						if(ok) {
							if(dbkez == 1) {
								String mkod = drm.getValueAt(jel, 1).toString();
								dbm.Connect();
								if(c.filled(textAzonosito)) dbm.Update(mkod, "azonosito", c.RTF(textAzonosito));
								if(c.filled(textCim)) dbm.Update(mkod, "cim", c.RTF(textCim));
								if(c.filled(textRendezo)) dbm.Update(mkod, "rendezo", c.RTF(textRendezo));
								if(c.filled(textElsobemutatas)) dbm.Update(mkod, "elsobemutatas", c.RTF(textElsobemutatas));
								if(c.filled(textJegyar)) dbm.Update(mkod, "jegyar", c.RTF(textJegyar));
								dbm.Disconnect();
							}
							
							if(c.filled(textAzonosito)) drm.setValueAt(c.stringTont(c.RTF(textAzonosito)), jel, 1);
							if(c.filled(textCim)) drm.setValueAt(c.RTF(textCim), jel, 2);
							if(c.filled(textRendezo)) drm.setValueAt(c.RTF(textRendezo), jel, 3);
							if(c.filled(textElsobemutatas)) drm.setValueAt(c.RTF(textElsobemutatas), jel,	4);
							if(c.filled(textJegyar)) drm.setValueAt(c.stringTont(c.RTF(textJegyar)), jel, 5);
							
							if(dbkez == 0) {
								FileManager.Modosit(fbetolt, drm);
								reset(jel);
								
							}
						}else {
							c.SM("Nincs kitöltve egyetlen módosító mezõ sem", 1);
						}
					}
				}
				
				
			}
		});
		btnTorles.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnTorles.setBackground(new Color(0, 128, 0));
		btnTorles.setBounds(10, 331, 169, 23);
		getContentPane().add(btnTorles);
		
		textAzonosito = new JTextField();
		textAzonosito.setBounds(10, 269, 96, 20);
		getContentPane().add(textAzonosito);
		textAzonosito.setColumns(10);
		
		textCim = new JTextField();
		textCim.setBounds(116, 269, 147, 20);
		getContentPane().add(textCim);
		textCim.setColumns(10);
		
		textRendezo = new JTextField();
		textRendezo.setBounds(286, 269, 169, 20);
		getContentPane().add(textRendezo);
		textRendezo.setColumns(10);
		
		textElsobemutatas = new JTextField();
		textElsobemutatas.setBounds(484, 269, 181, 20);
		getContentPane().add(textElsobemutatas);
		textElsobemutatas.setColumns(10);
		
		textJegyar = new JTextField();
		textJegyar.setBounds(728, 269, 72, 20);
		getContentPane().add(textJegyar);
		textJegyar.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u00DAj azonos\u00EDt\u00F3:");
		lblNewLabel.setBounds(24, 244, 96, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u00DAj c\u00EDm:");
		lblNewLabel_1.setBounds(168, 244, 48, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u00DAj rendez\u0151:");
		lblNewLabel_2.setBounds(336, 243, 82, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u00DAj bemutat\u00E1s ideje: ");
		lblNewLabel_3.setBounds(534, 243, 131, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u00DAj jegy\u00E1r: ");
		lblNewLabel_4.setBounds(728, 244, 72, 14);
		getContentPane().add(lblNewLabel_4);

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
	public int modDataPc() {
		int pc = 0;
		if(c.filled(textAzonosito)){
			pc++;
		}
		if(c.filled(textCim)) {
			pc++;
		}
		if(c.filled(textRendezo)) {
			pc++;
		}
		if(c.filled(textElsobemutatas)) {
			pc++;
		}
		if(c.filled(textJegyar)) {
			pc++;
		}
		return pc;
	}
	
	public void reset(int i) {
		textAzonosito.setText("");
		textCim.setText("");
		textRendezo.setText("");
		textElsobemutatas.setText("");
		textJegyar.setText("");
		drm.setValueAt(false, i, 0);
	}
}
