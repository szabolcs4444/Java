import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewEmp extends JDialog {
	private JTextField kod;
	private JTextField nev;
	private JTextField szid;
	private JTextField lak;
	private JTextField fiz;
	private DBMethods dbm = new DBMethods();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	
	public NewEmp(int dbkez) {
		
		Checker c = new Checker();
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnBeszur = new JButton("Besz\u00FAr");
		btnBeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(c.goodInt(kod,"Kód"))
					if(c.filled(nev,"Név"))
						if(c.goodDate(szid,"Születési idõ"))
							if(c.filled(lak,"Lakcím"))
								if(c.goodInt(fiz,"Fizetés")) 
									if(dbkez == 0) {
									FileManager.Insert(RTF(kod), RTF(nev), RTF(szid), RTF(lak), RTF(fiz));
									}
									else {
										dbm.Connect();
										dbm.Insert(RTF(kod), RTF(nev), RTF(szid), RTF(lak), RTF(fiz));
										dbm.Disconnect();
									}
			}
		});
		btnBeszur.setBounds(164, 197, 89, 23);
		getContentPane().add(btnBeszur);
		
		
		
		JLabel lblNewLabel = new JLabel("K\u00F3d:");
		lblNewLabel.setBounds(34, 22, 48, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("N\u00E9v:");
		lblNewLabel_1.setBounds(34, 47, 48, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Sz\u00FClet\u00E9si d\u00E1tum:");
		lblNewLabel_2.setBounds(34, 72, 89, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Lakc\u00EDm:");
		lblNewLabel_3.setBounds(34, 97, 48, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Fizet\u00E9s: ");
		lblNewLabel_4.setBounds(34, 122, 48, 14);
		getContentPane().add(lblNewLabel_4);
		
		kod = new JTextField();
		kod.setBounds(114, 19, 96, 20);
		getContentPane().add(kod);
		kod.setColumns(10);
		
		nev = new JTextField();
		nev.setBounds(114, 44, 96, 20);
		getContentPane().add(nev);
		nev.setColumns(10);
		
		szid = new JTextField();
		szid.setBounds(114, 69, 96, 20);
		getContentPane().add(szid);
		szid.setColumns(10);
		
		lak = new JTextField();
		lak.setBounds(114, 94, 96, 20);
		getContentPane().add(lak);
		lak.setColumns(10);
		
		fiz = new JTextField();
		fiz.setBounds(114, 119, 96, 20);
		getContentPane().add(fiz);
		fiz.setColumns(10);

	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
