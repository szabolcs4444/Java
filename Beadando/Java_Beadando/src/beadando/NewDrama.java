package beadando;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewDrama extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textAzonosito;
	private JTextField textCim;
	private JTextField textElsobemutatas;
	private JTextField textRendezo;
	private JTextField textJegyar;
	private DBMethods dbm = new DBMethods();

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public NewDrama(int dbkez) {
		Checker c = new Checker();
		setBounds(100, 100, 494, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Azonos\u00EDt\u00F3: ");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewLabel.setBounds(24, 21, 78, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblCm = new JLabel("C\u00EDm: ");
		lblCm.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCm.setBounds(24, 62, 78, 14);
		contentPanel.add(lblCm);
		
		JLabel lblRendez = new JLabel("Rendez\u0151: ");
		lblRendez.setFont(new Font("Calibri", Font.BOLD, 14));
		lblRendez.setBounds(24, 103, 78, 14);
		contentPanel.add(lblRendez);
		
		JLabel lblElsBemutats = new JLabel("Els\u0151 Bemutat\u00E1s: ");
		lblElsBemutats.setFont(new Font("Calibri", Font.BOLD, 14));
		lblElsBemutats.setBounds(23, 147, 106, 14);
		contentPanel.add(lblElsBemutats);
		
		JLabel lblJegyr = new JLabel("Jegy\u00E1r: ");
		lblJegyr.setFont(new Font("Calibri", Font.BOLD, 14));
		lblJegyr.setBounds(24, 189, 78, 14);
		contentPanel.add(lblJegyr);
		
		textAzonosito = new JTextField();
		textAzonosito.setForeground(Color.BLACK);
		textAzonosito.setBackground(Color.WHITE);
		textAzonosito.setBounds(123, 16, 78, 20);
		contentPanel.add(textAzonosito);
		textAzonosito.setColumns(10);
		
		textCim = new JTextField();
		textCim.setBounds(123, 57, 241, 20);
		contentPanel.add(textCim);
		textCim.setColumns(10);
		
		textElsobemutatas = new JTextField();
		textElsobemutatas.setBounds(122, 142, 150, 20);
		contentPanel.add(textElsobemutatas);
		textElsobemutatas.setColumns(10);
		
		textRendezo = new JTextField();
		textRendezo.setBounds(123, 98, 189, 20);
		contentPanel.add(textRendezo);
		textRendezo.setColumns(10);
		
		textJegyar = new JTextField();
		textJegyar.setBounds(123, 184, 96, 20);
		contentPanel.add(textJegyar);
		textJegyar.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("D\u00E1tum - eeee.hh.nn");
		lblNewLabel_1.setFont(new Font("Sitka Heading", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(282, 142, 135, 23);
		contentPanel.add(lblNewLabel_1);
		
		JButton btnBeszur = new JButton("Hozz\u00E1ad");
		btnBeszur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.goodInt(textAzonosito,"Azonosító")&&c.goodAzon(textAzonosito, "Azonositó"))
					if(c.filled(textCim,"Cím"))
						if(c.filled(textRendezo,"Rendezõ"))
						if(c.goodDate(textElsobemutatas,"Bemutatás dátuma"))
								if(c.goodInt(textJegyar,"Jegyár")) 
									if(dbkez == 0) {
									FileManager.Insert(ProgramDrama.textBetolt.getText().toString(),c.RTF(textAzonosito), c.RTF(textCim),c.RTF(textRendezo),c.RTF(textElsobemutatas),  c.RTF(textJegyar));
									}
									else {
										dbm.Connect();
										dbm.Insert(RTF(textAzonosito), c.RTF(textCim),c.RTF(textRendezo), c.RTF(textElsobemutatas), c.RTF(textJegyar));
										dbm.Disconnect();
									}
			}
		});
		btnBeszur.setBackground(new Color(0, 128, 0));
		btnBeszur.setBounds(123, 231, 89, 23);
		contentPanel.add(btnBeszur);
		
		JButton btnTisztt = new JButton("Clear");
		btnTisztt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAzonosito.setText("");
				textCim.setText("");
				textElsobemutatas.setText("");
				textRendezo.setText("");
				textJegyar.setText("");
			}
		});
		btnTisztt.setBackground(new Color(0, 128, 0));
		btnTisztt.setBounds(275, 231, 89, 23);
		contentPanel.add(btnTisztt);
		
		JButton btnKilps = new JButton("Kil\u00E9p\u00E9s");
		btnKilps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnKilps.setBackground(Color.RED);
		btnKilps.setBounds(383, 298, 89, 23);
		contentPanel.add(btnKilps);
	}
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
}
