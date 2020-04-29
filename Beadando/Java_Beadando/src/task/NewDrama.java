package task;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewDrama extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textIdentifier;
	private JTextField textTitle;
	private JTextField textPerformanceDate;
	private JTextField textDirector;
	private JTextField textTicketPrice;
	private DatabaseMethods dbMethods = new DatabaseMethods();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public NewDrama(int databaseOperator) {
		Checker checker = new Checker();
		setBounds(100, 100, 494, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel labelNewIdentifier = new JLabel("Identifier: ");
		labelNewIdentifier.setFont(new Font("Calibri", Font.BOLD, 14));
		labelNewIdentifier.setBounds(24, 21, 78, 14);
		contentPanel.add(labelNewIdentifier);

		JLabel labelTitle = new JLabel("Title: ");
		labelTitle.setFont(new Font("Calibri", Font.BOLD, 14));
		labelTitle.setBounds(24, 62, 78, 14);
		contentPanel.add(labelTitle);

		JLabel labelDirector = new JLabel("Director: ");
		labelDirector.setFont(new Font("Calibri", Font.BOLD, 14));
		labelDirector.setBounds(24, 103, 78, 14);
		contentPanel.add(labelDirector);

		JLabel labelPresentation = new JLabel("Performance date: ");
		labelPresentation.setFont(new Font("Calibri", Font.BOLD, 14));
		labelPresentation.setBounds(24, 147, 119, 14);
		contentPanel.add(labelPresentation);

		JLabel labelTicketPrice = new JLabel("Ticket price: ");
		labelTicketPrice.setFont(new Font("Calibri", Font.BOLD, 14));
		labelTicketPrice.setBounds(24, 189, 78, 14);
		contentPanel.add(labelTicketPrice);

		textIdentifier = new JTextField();
		textIdentifier.setForeground(Color.BLACK);
		textIdentifier.setBackground(Color.WHITE);
		textIdentifier.setBounds(123, 16, 78, 20);
		contentPanel.add(textIdentifier);
		textIdentifier.setColumns(10);

		textTitle = new JTextField();
		textTitle.setBounds(123, 57, 241, 20);
		contentPanel.add(textTitle);
		textTitle.setColumns(10);

		textPerformanceDate = new JTextField();
		textPerformanceDate.setBounds(145, 142, 150, 20);
		contentPanel.add(textPerformanceDate);
		textPerformanceDate.setColumns(10);

		textDirector = new JTextField();
		textDirector.setBounds(123, 98, 189, 20);
		contentPanel.add(textDirector);
		textDirector.setColumns(10);

		textTicketPrice = new JTextField();
		textTicketPrice.setBounds(123, 184, 96, 20);
		contentPanel.add(textTicketPrice);
		textTicketPrice.setColumns(10);

		JLabel labelDateFormat = new JLabel("Date - yyyy.MM.dd");
		labelDateFormat.setFont(new Font("Sitka Heading", Font.PLAIN, 13));
		labelDateFormat.setBounds(305, 143, 135, 23);
		contentPanel.add(labelDateFormat);

		JButton buttonAdd = new JButton("Add");
		buttonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!(checker.goodInt(textIdentifier, "Identifier")
						&& checker.goodIdentifier(textIdentifier, "Azonositó")))

				{
					JOptionPane.showMessageDialog(null,
							"Error: the Identifier field is empty or wrong!\n Enter a number between 1 and 100000! ",
							"Program message", 0);

					if (!checker.textFilled(textTitle, "Title")) {
						JOptionPane.showMessageDialog(null, "Error: the Title field is empty", "Program message", 0);

						if (!checker.textFilled(textDirector, "Director")) {
							JOptionPane.showMessageDialog(null, "Error: the Director field is empty", "Program message",
									0);

							if (!checker.goodDate(textPerformanceDate, "Performance date")) {
								JOptionPane.showMessageDialog(null, "Performance date field as the wrong date format. ",
										"Program message", 0);

								if (!(checker.goodInt(textTicketPrice, "ticket Price")
										&& checker.textFilled(textTicketPrice, "Ticket Price"))) {
									JOptionPane.showMessageDialog(null,
											"Error: the ticket field has incorrect number or empty!", "Program message",
											0);
								}
							}
						}
					}
				} else if (databaseOperator == 0) {
					FileManager.insert(ProgramDrama.textLoad.getText().toString(), textIdentifier.getText(),
							textTitle.getText(), textDirector.getText(), textPerformanceDate.getText(),
							textTicketPrice.getText());
				} else if (databaseOperator == 1) {
					dbMethods.connect();
					dbMethods.insert(textIdentifier.getText(), textTitle.getText(), textDirector.getText(),
							textPerformanceDate.getText(), textTicketPrice.getText());
					dbMethods.disconnect();
				}

			}

		});
		buttonAdd.setBackground(new Color(0, 128, 0));
		buttonAdd.setBounds(123, 231, 89, 23);
		contentPanel.add(buttonAdd);

		JButton buttonClear = new JButton("Clear");
		buttonClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textIdentifier.setText("");
				textTitle.setText("");
				textPerformanceDate.setText("");
				textDirector.setText("");
				textTicketPrice.setText("");
			}
		});
		buttonClear.setBackground(new Color(0, 128, 0));
		buttonClear.setBounds(275, 231, 89, 23);
		contentPanel.add(buttonClear);

		JButton buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonExit.setBackground(Color.RED);
		buttonExit.setBounds(383, 298, 89, 23);
		contentPanel.add(buttonExit);
	}

}
