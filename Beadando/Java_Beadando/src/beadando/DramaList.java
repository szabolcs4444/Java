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
import java.awt.event.ActionEvent;

public class DramaList extends JDialog {
	private JTable table;
	private DramaTM drm;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DramaList(JFrame f, DramaTM bdrm) {
		super(f,"Szindarabok listája",true);
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
