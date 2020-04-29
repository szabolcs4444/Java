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

public class DramaModify extends JDialog {
	private JTable table;
	private DramaTableModel drama;
	private Checker checker = new Checker();
	private DatabaseMethods dbMethods = new DatabaseMethods();
	private JTextField textIdentifier;
	private JTextField textTitle;
	private JTextField textDirector;
	private JTextField textPerformanceDate;
	private JTextField textTicketPrice;
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
	public DramaModify(JFrame frame, DramaTableModel dramaTableModel, int databaseOperator, File fileLoad) {
		super(frame, "Drama modification", true);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		drama = dramaTableModel;
		setBounds(100, 100, 850, 400);
		getContentPane().setLayout(null);

		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonExit.setBackground(Color.RED);
		buttonExit.setBounds(631, 331, 169, 23);
		getContentPane().add(buttonExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 11, 800, 200);
		getContentPane().add(scrollPane);

		table = new JTable(drama);
		scrollPane.setViewportView(table);

		JButton buttonModify = new JButton("Modify");
		buttonModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int piece = 0, signal = 0;
				for (int x = 0; x < drama.getRowCount(); x++) {
					if (Boolean.TRUE.equals(drama.getValueAt(x, 0))) {
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
						if (dataFilledCount() > 0) {
							boolean ok = true;
							if (checker.filled(textIdentifier)) {
								ok = checker.goodIdentifier(textIdentifier, "Identifier");
							}
							if (ok && checker.filled(textTicketPrice)) {
								ok = checker.goodInt(textTicketPrice, "Ticket Price");
							}
							if (ok && checker.filled(textPerformanceDate)) {
								ok = checker.goodDate(textPerformanceDate, "Performance date");
							}
							if (ok) {
								if (databaseOperator == 1) {
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
									if (checker.filled(textPerformanceDate)) {
										dbMethods.update(mkod, "performanceDate", textPerformanceDate.getText());
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
								if (checker.filled(textPerformanceDate)) {
									drama.setValueAt(textPerformanceDate.getText(), signal, 4);
								}
								if (checker.filled(textTicketPrice)) {
									drama.setValueAt(checker.stringToInt(textTicketPrice.getText()), signal, 5);
								}

								if (databaseOperator == 0) {
									FileManager.modify(fileLoad, drama);
									reset(signal);

								}
							} else {

								JOptionPane.showMessageDialog(null,
										"Error: the Identifier field is empty or wrong!\nEnter a number between 1 and 100000!\n"
												+ "Wrong date format or incorrent number in ticket price field! ",
										"Program message", 0);

							}
						}
					}

				}
			}
		});
		buttonModify.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonModify.setBackground(new Color(0, 128, 0));
		buttonModify.setBounds(10, 331, 169, 23);
		getContentPane().add(buttonModify);

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

		textPerformanceDate = new JTextField();
		textPerformanceDate.setBounds(484, 269, 181, 20);
		getContentPane().add(textPerformanceDate);
		textPerformanceDate.setColumns(10);

		textTicketPrice = new JTextField();
		textTicketPrice.setBounds(728, 269, 72, 20);
		getContentPane().add(textTicketPrice);
		textTicketPrice.setColumns(10);

		JLabel labelNewIdentifier = new JLabel("New identifier:");
		labelNewIdentifier.setBounds(24, 244, 110, 14);
		getContentPane().add(labelNewIdentifier);

		JLabel labelNewTitle = new JLabel("New title:");
		labelNewTitle.setBounds(154, 244, 82, 14);
		getContentPane().add(labelNewTitle);

		JLabel labelNewDirector = new JLabel("New director:");
		labelNewDirector.setBounds(336, 243, 82, 14);
		getContentPane().add(labelNewDirector);

		JLabel labelNewPerformanceDate = new JLabel("New performance date: ");
		labelNewPerformanceDate.setBounds(514, 244, 139, 14);
		getContentPane().add(labelNewPerformanceDate);

		JLabel labelNewTicketPrice = new JLabel("New ticket price: ");
		labelNewTicketPrice.setBounds(721, 244, 117, 14);
		getContentPane().add(labelNewTicketPrice);

		for (int i = 0; i < 6; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(map.get(i));
		}
	}

	public int dataFilledCount() {
		int dataPiece = 0;
		if (checker.filled(textIdentifier)) {
			dataPiece++;
		}
		if (checker.filled(textTitle)) {
			dataPiece++;
		}
		if (checker.filled(textDirector)) {
			dataPiece++;
		}
		if (checker.filled(textPerformanceDate)) {
			dataPiece++;
		}
		if (checker.filled(textTicketPrice)) {
			dataPiece++;
		}
		return dataPiece;
	}

	public void reset(int i) {
		textIdentifier.setText("");
		textTitle.setText("");
		textDirector.setText("");
		textPerformanceDate.setText("");
		textTicketPrice.setText("");
		drama.setValueAt(false, i, 0);
	}
}
