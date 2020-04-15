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

public class EmpList extends JDialog {
	protected static final String textField = null;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private EmpTM etm;
	

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public EmpList(JFrame f,EmpTM betm) {
		super(f,"Dolgozók listája",true);
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
		
		table.setAutoCreateRowSorter(true);
		

	}
}
