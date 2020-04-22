package task;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class DramaDel extends JDialog {
	private JTable table;
	private DramaTM drama;
	private Checker checker = new Checker();
	private DBMethods dbMethods = new DBMethods();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DramaDel(JFrame frame, DramaTM bDrama, int dbOperator, File fileLoad) {
		super(frame, "Drama Delete", true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		drama = bDrama;
		setBounds(100, 100, 850, 300);
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
		scrollPane.setBounds(0, 11, 800, 200);
		getContentPane().add(scrollPane);

		table = new JTable(drama);
		scrollPane.setViewportView(table);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int piece = 0, signal = 0;
				for (int x = 0; x < drama.getRowCount(); x++) {
					if ((Boolean) drama.getValueAt(x, 0)) {
						piece++;
						signal = x;
					}
					if (piece == 0) {
						JOptionPane.showMessageDialog(null, "No record selected! ", " Program message", 0);

					}

					if (piece > 1) {
						JOptionPane.showMessageDialog(null,
								"Multiple records selected!\n only 1 record can be deleted at a time ",
								" Program message", 0);
					}

					if (piece == 1) {
						String identifier = drama.getValueAt(signal, 1).toString();
						drama.removeRow(signal);
						if (dbOperator == 0) {
							FileManager.modify(fileLoad, drama);
						} else if (dbOperator == 1) {
							dbMethods.connect();
							dbMethods.deleteData(identifier);
							dbMethods.disconnect();
						}

						dispose();
						JOptionPane.showMessageDialog(null, "Record deleted! ", " Program message", 1);

					}
				}

			}
		});
		btnDelete.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnDelete.setBackground(new Color(0, 128, 0));
		btnDelete.setBounds(83, 231, 169, 23);
		getContentPane().add(btnDelete);

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
