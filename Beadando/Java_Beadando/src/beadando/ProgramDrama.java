package beadando;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class ProgramDrama extends JFrame {

	private JPanel contentPane;
	private String szoveg ="";
	private String szoveg2 ="";
	private DramaTM drm;
	private File fbetolt;
	static JTextField textBetolt;
	private JTextField textKiir;
	private String message = "Feladat üzenet";
	private int dbkez = 0;
	private String dbconn;
	private DBMethods dbm = new DBMethods();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramDrama frame = new ProgramDrama();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProgramDrama() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 510);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBetoltes = new JButton("Adatok bet\u00F6lt\u00E9se");
		btnBetoltes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(szoveg.equals("")) {
					SM("Kérem válassza ki a formátumot! ",0);
				}else if(szoveg.equals(".csv fájl")) {
					dbkez = 0;
					FileDialog fd = new FileDialog(new Frame()," ",FileDialog.LOAD);
					fd.setFile("*.csv");
					fd.setVisible(true);
					if(fd.getFile()!= null) {
						fbetolt = new File(fd.getDirectory(),fd.getFile());
						String bnev = fd.getFile();
						textBetolt.setText(bnev);
						FileManager.CsvReader(fbetolt, drm);
					}
				}else if(szoveg.equals("sqlite db")) {
					dbkez = 1;
					dbm.Reg();
					dbm.Connect();
					dbconn = textBetolt.getText();
					drm = dbm.ReadAllData(dbconn);
					dbm.Disconnect();
				}
			}
		});
		btnBetoltes.setBackground(new Color(0, 128, 0));
		btnBetoltes.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnBetoltes.setBounds(603, 33, 175, 23);
		contentPane.add(btnBetoltes);
		
		JButton btnLista = new JButton("Adatok list\u00E1z\u00E1sa");
		btnLista.setBackground(new Color(0, 128, 0));
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez == 0) {
				drm = FileManager.CsvReader(fbetolt,drm);
				}else if(dbkez == 1) {
					dbm.Connect();
					drm = dbm.ReadAllData(dbconn);
					dbm.Disconnect();
				}
				DramaList dl = new DramaList(ProgramDrama.this,drm);
				dl.setVisible(true);
			}
		});
		btnLista.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLista.setBounds(10, 33, 155, 23);
		contentPane.add(btnLista);
		
		
		String element[] = {"",".csv fájl","sqlite db"};
		JComboBox comboBox = new JComboBox();
		for(String s : element)
			comboBox.addItem(s);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				szoveg = (String) comboBox.getSelectedItem();
				textBetolt.setText(szoveg);
			}
		});
			
		
		comboBox.setBounds(186, 34, 135, 22);
		contentPane.add(comboBox);
		
		textBetolt = new JTextField();
		textBetolt.setBounds(322, 35, 271, 20);
		contentPane.add(textBetolt);
		textBetolt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Form\u00E1tum kiv\u00E1laszt\u00E1sa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(186, 9, 147, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("A bet\u00F6ltend\u0151 f\u00E1jl neve:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(376, 8, 187, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnjAdatHozzadsa = new JButton("\u00DAj adat hozz\u00E1ad\u00E1sa");
		btnjAdatHozzadsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez==0) {
				NewDrama nd = new NewDrama(dbkez);
				nd.setVisible(true);
			}else if(dbkez ==1) {
				NewDrama nd = new NewDrama(dbkez);
				nd.setVisible(true);
			}
			}
		});
		btnjAdatHozzadsa.setBackground(new Color(0, 128, 0));
		btnjAdatHozzadsa.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnjAdatHozzadsa.setBounds(10, 134, 175, 23);
		contentPane.add(btnjAdatHozzadsa);
		
		JButton btnAdatokMdostsa = new JButton("Adatok M\u00F3dos\u00EDt\u00E1sa");
		btnAdatokMdostsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez==0) {
					drm = FileManager.CsvReader(fbetolt, drm);
					
				}else if(dbkez==1) {
					dbm.Connect();
					drm = dbm.ReadAllData(dbconn);
					dbm.Disconnect();
				}
				
				DramaMod dm= new DramaMod(ProgramDrama.this,drm,dbkez,fbetolt);
				dm.setVisible(true);
			}
		});
		btnAdatokMdostsa.setBackground(new Color(0, 128, 0));
		btnAdatokMdostsa.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAdatokMdostsa.setBounds(10, 197, 175, 23);
		contentPane.add(btnAdatokMdostsa);
		
		JButton btnAdatokTrlse = new JButton("Adatok T\u00F6rl\u00E9se");
		btnAdatokTrlse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez==0) {
					drm = FileManager.CsvReader(fbetolt, drm);
					
				}else if(dbkez==1) {
					dbm.Connect();
					drm = dbm.ReadAllData(dbconn);
					dbm.Disconnect();
				}
				
				DramaDel dd= new DramaDel(ProgramDrama.this,drm,dbkez,fbetolt);
				dd.setVisible(true);
			}
		});
		btnAdatokTrlse.setBackground(new Color(0, 128, 0));
		btnAdatokTrlse.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAdatokTrlse.setBounds(10, 267, 175, 23);
		contentPane.add(btnAdatokTrlse);
		
		JButton btnAdatokKirsa = new JButton("Adatok Ment\u00E9se");
		btnAdatokKirsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (szoveg2.equals(""))
					SM("Elõször válassza ki a cél-t!", 0);
				else if (drm.getRowCount() == 0)
					SM("Nincs kiírható adat", 0);
				else if (szoveg2.equals(".csv fájl")) {
					if (textKiir.getText().length() == 0)
						SM("Nincs megadva a cél fájl neve!", 0);
					else {
						FileManager.CsvWriter(textKiir.getText().toString(), drm);
					}
				} else if (szoveg2.equals("sqlite db")) {
					if (textKiir.getText().length() == 0)
						SM("Nincs megadva a cél fájl neve!", 0);
					else {
						dbm.DBWriter(textKiir.getText().toString(), drm);
					}
				} else if (szoveg2.equals(".xml fájl")) {
					if (textKiir.getText().length() == 0)
						SM("Nincs megadva a cél fájl neve!", 0);
					else {
						XmlWriter.XmlWRITER(textKiir.getText().toString(), drm);
					}
				} else if (szoveg2.equals(".json fájl")) {
					if (textKiir.getText().length() == 0)
						SM("Nincs megadva a cél fájl neve!", 0);
					else {
						JsonWriter.JsoncsvWriter(textKiir.getText().toString(), drm);
					}
				} else if (szoveg2.equals(".pdf fájl")) {
					if (textKiir.getText().length() == 0)
						SM("Nincs megadva a cél fájl neve!", 0);
					else {
						CreatePdf.PdfWrite(textKiir.getText().toString(), drm);
					}
				}
			}
		});
		btnAdatokKirsa.setBackground(new Color(0, 128, 0));
		btnAdatokKirsa.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnAdatokKirsa.setBounds(609, 361, 169, 23);
		contentPane.add(btnAdatokKirsa);
		String element2[] = {"",".csv fájl",".xml fájl",".json fájl","sqlite db",".pdf fájl"};
		JComboBox comboBox_1 = new JComboBox();
		for(String s : element2)
			comboBox_1.addItem(s);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				szoveg2 = (String) comboBox_1.getSelectedItem();
				if(textBetolt.getText().equals(""))
					SM("Nincs megadva forrás!",0);
				if(!textBetolt.getText().equals(""))
					textKiir.getText();
			}
		});
		
		comboBox_1.setBounds(186, 362, 135, 22);
		contentPane.add(comboBox_1);
		
		JLabel lblFormtumKivlasztsa = new JLabel("Form\u00E1tum kiv\u00E1laszt\u00E1sa");
		lblFormtumKivlasztsa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFormtumKivlasztsa.setBounds(186, 337, 147, 14);
		contentPane.add(lblFormtumKivlasztsa);
		
		textKiir = new JTextField();
		textKiir.setBounds(322, 363, 277, 20);
		contentPane.add(textKiir);
		textKiir.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("A mentett f\u00E1jl/t\u00E1bla neve:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(376, 337, 175, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnKilps = new JButton("Kil\u00E9p\u00E9s");
		btnKilps.setBackground(new Color(255, 0, 0));
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnKilps.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnKilps.setBounds(609, 441, 169, 23);
		contentPane.add(btnKilps);
	}
	
	public static void SM(String msg,int tipus) {
		JOptionPane.showMessageDialog(null, msg,"Program üzenet",tipus);
	}
}
