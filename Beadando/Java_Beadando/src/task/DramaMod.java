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
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DramaMod extends JDialog {
	private JTable table;
	private DramaTM drama;
	private Checker checker = new Checker();
	private DBMethods dbMethods = new DBMethods();
	private JTextField textIdentifier;
	private JTextField textTitle;
	private JTextField textDirector;
	private JTextField textPresentation;
	private JTextField textTicketPrice;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DramaMod(JFrame frame, DramaTM bDrama, int dbOperator, File fileLoad) {
		super(frame, "Drama modification", true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		drama = bDrama;
		setBounds(100, 100, 850, 400);
		getContentPane().setLayout(null);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(631, 331, 169, 23);
		getContentPane().add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 800, 200);
		getContentPane().add(scrollPane);

		table = new JTable(drama);
		scrollPane.setViewportView(table);

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int piece = 0, signal = 0;
				for (int x = 0; x < drama.getRowCount(); x++) {
					if ((Boolean) drama.getValueAt(x, 0)) {
						piece++;
						signal = x;
					}
					if (piece == 0) {
						JOptionPane.showMessageDialog(null, "No record selected for modification", " Program message",
								0);
					}
					if (piece > 1) {
						JOptionPane.showMessageDialog(null,
								"Multiple records selected,only 1 record can be modified at a time", " Program message",
								0);
					}

					if (piece == 1) {
						if (modDataPc() > 0) {
							boolean ok = true;
							if (checker.filled(textIdentifier)) {
								ok = checker.goodInt(textIdentifier, "Identifier");
							}
							if (ok && checker.filled(textTicketPrice)) {
								ok = checker.goodInt(textTicketPrice, "Ticket Price");
							}
							if (ok) {
								if (dbOperator == 1) {
									String mkod = drama.getValueAt(signal, 1).toString();
									dbMethods.connect();
									if (checker.filled(textIdentifier)) {
										dbMethods.update(mkod, "identifier", textIdentifier.getText());
									}
									if (checker.filled(textTitle)) {
										dbMethods.update(mkod, "title", textTitle.getText());
									}
									if (checker.filled(textDirector)) {
										dbMethods.update(mkod, "director", textDirector.getText());
									}
									if (checker.filled(textPresentation)) {
										dbMethods.update(mkod, "performanceDate", textPresentation.getText());
									}
									if (checker.filled(textTicketPrice)) {
										dbMethods.update(mkod, "ticketPrice", textTicketPrice.getText());
									}
									dbMethods.disconnect();
								}

								if (checker.filled(textIdentifier)) {
									drama.setValueAt(checker.stringToInt(textIdentifier.getText()), signal, 1);
								}
								if (checker.filled(textTitle)) {
									drama.setValueAt(textTitle.getText(), signal, 2);
								}
								if (checker.filled(textDirector)) {
									drama.setValueAt(textDirector.getText(), signal, 3);
								}
								if (checker.filled(textPresentation)) {
									drama.setValueAt(textPresentation.getText(), signal, 4);
								}
								if (checker.filled(textTicketPrice)) {
									drama.setValueAt(checker.stringToInt(textTicketPrice.getText()), signal, 5);
								}

								if (dbOperator == 0) {
									FileManager.modify(fileLoad, drama);
									reset(signal);

								}
							} else {
								JOptionPane.showMessageDialog(null, "no field filled", "Program message", 1);

							}
						}
					}

				}
			}
		});
		btnModify.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnModify.setBackground(new Color(0, 128, 0));
		btnModify.setBounds(10, 331, 169, 23);
		getContentPane().add(btnModify);

		textIdentifier = new JTextField();
		textIdentifier.setBounds(10, 269, 96, 20);
		getContentPane().add(textIdentifier);
		textIdentifier.setColumns(10);

		textTitle = new JTextField();
		textTitle.setBounds(116, 269, 147, 20);
		getContentPane().add(textTitle);
		textTitle.setColumns(10);

		textDirector = new JTextField();
		textDirector.setBounds(286, 269, 169, 20);
		getContentPane().add(textDirector);
		textDirector.setColumns(10);

		textPresentation = new JTextField();
		textPresentation.setBounds(484, 269, 181, 20);
		getContentPane().add(textPresentation);
		textPresentation.setColumns(10);

		textTicketPrice = new JTextField();
		textTicketPrice.setBounds(728, 269, 72, 20);
		getContentPane().add(textTicketPrice);
		textTicketPrice.setColumns(10);

		JLabel lblNewIdentifier = new JLabel("New identifier:");
		lblNewIdentifier.setBounds(24, 244, 110, 14);
		getContentPane().add(lblNewIdentifier);

		JLabel lblNewTitle = new JLabel("New title:");
		lblNewTitle.setBounds(154, 244, 82, 14);
		getContentPane().add(lblNewTitle);

		JLabel lblNewDirector = new JLabel("New director:");
		lblNewDirector.setBounds(336, 243, 82, 14);
		getContentPane().add(lblNewDirector);

		JLabel lblNewPerformanceDate = new JLabel("New performance date: ");
		lblNewPerformanceDate.setBounds(514, 244, 139, 14);
		getContentPane().add(lblNewPerformanceDate);

		JLabel lblNewTicketPrice = new JLabel("New ticket price: ");
		lblNewTicketPrice.setBounds(721, 244, 117, 14);
		getContentPane().add(lblNewTicketPrice);

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

	public int modDataPc() {
		int dataPc = 0;
		if (checker.filled(textIdentifier)) {
			dataPc++;
		}
		if (checker.filled(textTitle)) {
			dataPc++;
		}
		if (checker.filled(textDirector)) {
			dataPc++;
		}
		if (checker.filled(textPresentation)) {
			dataPc++;
		}
		if (checker.filled(textTicketPrice)) {
			dataPc++;
		}
		return dataPc;
	}

	public void reset(int i) {
		textIdentifier.setText("");
		textTitle.setText("");
		textDirector.setText("");
		textPresentation.setText("");
		textTicketPrice.setText("");
		drama.setValueAt(false, i, 0);
	}
}
