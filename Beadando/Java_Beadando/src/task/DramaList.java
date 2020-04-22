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
	private DramaTM drama;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DramaList(JFrame frame, DramaTM bDrama) {
		super(frame, "Drama list", true);
		drama = bDrama;
		setBounds(100, 100, 900, 300);
		getContentPane().setLayout(null);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(352, 231, 169, 23);
		getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 850, 200);
		getContentPane().add(scrollPane);

		table = new JTable(drama);
		scrollPane.setViewportView(table);

		TableColumn tableColumn = null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 70);
		map.put(1, 120);
		map.put(2, 400);
		map.put(3, 400);
		map.put(4, 400);
		map.put(5, 150);
		for (int i = 0; i < 6; i++) {
			tableColumn = table.getColumnModel().getColumn((i));
			tableColumn.setPreferredWidth(map.get(i));
		}

	}

}
