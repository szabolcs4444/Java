package task;

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
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DramaList extends JDialog {
	private JTable table;
	private DramaTableModel drama;
	private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	static {
		map.put(0, 70);
		map.put(1, 120);
		map.put(2, 400);
		map.put(3, 400);
		map.put(4, 400);
		map.put(5, 150);
	}

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DramaList(JFrame frame, DramaTableModel dramaTableModel) {
		super(frame, "Drama list", true);
		drama = dramaTableModel;
		setBounds(100, 100, 900, 300);
		getContentPane().setLayout(null);

		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonExit.setBackground(Color.RED);
		buttonExit.setBounds(352, 231, 169, 23);
		getContentPane().add(buttonExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 850, 200);
		getContentPane().add(scrollPane);

		table = new JTable(drama);
		scrollPane.setViewportView(table);

		for (int i = 0; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(map.get(i));
		}

	}

}
