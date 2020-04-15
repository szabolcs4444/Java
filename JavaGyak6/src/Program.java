import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Program extends JFrame {
	private EmpTM etm;
	private JPanel contentPane;
	private DBMethods dbm = new DBMethods();
	private int dbkez = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
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
	public Program() {
		dbm.Reg();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLista = new JButton("Lista");
		btnLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez == 0) {
					etm = FileManager.CsvReader();
					
				}else {
					dbm.Connect();
					etm = dbm.ReadAllData();
					dbm.Disconnect();
				}
				
				EmpList el = new EmpList(Program.this,etm);
				el.setVisible(true);
			}
		});
		btnLista.setBounds(10, 11, 89, 23);
		contentPane.add(btnLista);
		
		JButton btnUjAdat = new JButton("\u00DAj adatsor");
		btnUjAdat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmp ne = new NewEmp(dbkez);
				ne.setVisible(true);
			}
		});
		btnUjAdat.setBounds(10, 45, 89, 23);
		contentPane.add(btnUjAdat);
		
		JButton btnTrls = new JButton("T\u00F6rl\u00E9s");
		btnTrls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dbkez ==0) {
					etm = FileManager.CsvReader();
				}else {
					dbm.Connect();
					etm = dbm.ReadAllData();
					dbm.Disconnect();
				}
				
				EmpDel ed = new EmpDel(Program.this,etm, dbkez);
				ed.setVisible(true);
			}
		});
		btnTrls.setBounds(10, 79, 89, 23);
		contentPane.add(btnTrls);
		
		JButton btnMdosts = new JButton("M\u00F3dos\u00EDt\u00E1s");
		btnMdosts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbkez == 0) {
					etm = FileManager.CsvReader();
				}else {
					dbm.Connect();
					etm = dbm.ReadAllData();
					dbm.Disconnect();
				}
				
				EmpMod em = new EmpMod(Program.this,etm,dbkez);
				
				em.setVisible(true);

			}
		});
		btnMdosts.setBounds(10, 113, 89, 23);
		contentPane.add(btnMdosts);
		
		JButton btnPrba = new JButton("Pr\u00F3ba");
		btnPrba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbm.Connect();
				dbm.Disconnect();
			}
		});
		btnPrba.setBounds(149, 11, 89, 23);
		contentPane.add(btnPrba);
		
		JCheckBox chckbxDbKezels = new JCheckBox("DB kezel\u00E9s");
		chckbxDbKezels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxDbKezels.isSelected()) {
					dbkez = 1;
				}else {
					dbkez = 0;
				}
				
				
			}
		});
		chckbxDbKezels.setBounds(166, 113, 99, 23);
		contentPane.add(chckbxDbKezels);
		
		Object emptmn[] = {"Jel","Kód","Név","Szülidõ","Lakóhely","Fizetés"};
		etm = new EmpTM(emptmn, 0);
	}
}
