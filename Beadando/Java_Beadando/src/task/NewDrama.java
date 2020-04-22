package task;

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
	private JTextField textIdentifier;
	private JTextField textTitle;
	private JTextField textPerformanceDate;
	private JTextField textDirector;
	private JTextField textTicketPrice;
	private DBMethods dbMethods = new DBMethods();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public NewDrama(int dbOperator) {
		Checker checker = new Checker();
		setBounds(100, 100, 494, 367);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.LIGHT_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewIdentifier = new JLabel("Identifier: ");
		lblNewIdentifier.setFont(new Font("Calibri", Font.BOLD, 14));
		lblNewIdentifier.setBounds(24, 21, 78, 14);
		contentPanel.add(lblNewIdentifier);

		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTitle.setBounds(24, 62, 78, 14);
		contentPanel.add(lblTitle);

		JLabel lblDirector = new JLabel("Director: ");
		lblDirector.setFont(new Font("Calibri", Font.BOLD, 14));
		lblDirector.setBounds(24, 103, 78, 14);
		contentPanel.add(lblDirector);

		JLabel lblPresentation = new JLabel("Performance date: ");
		lblPresentation.setFont(new Font("Calibri", Font.BOLD, 14));
		lblPresentation.setBounds(24, 147, 119, 14);
		contentPanel.add(lblPresentation);

		JLabel lblTicketPrice = new JLabel("Ticket price: ");
		lblTicketPrice.setFont(new Font("Calibri", Font.BOLD, 14));
		lblTicketPrice.setBounds(24, 189, 78, 14);
		contentPanel.add(lblTicketPrice);

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

		JLabel lblDateFormat = new JLabel("Date - yyyy.MM.dd");
		lblDateFormat.setFont(new Font("Sitka Heading", Font.PLAIN, 13));
		lblDateFormat.setBounds(305, 143, 135, 23);
		contentPanel.add(lblDateFormat);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checker.goodInt(textIdentifier, "Identifier")
						&& checker.goodIdentifier(textIdentifier, "Azonositó")) {
					if (checker.filled(textTitle, "Title")) {
						if (checker.filled(textDirector, "Director")) {
							if (checker.goodDate(textPerformanceDate, "Performance date")) {
								if (checker.goodInt(textTicketPrice, "ticket Price")) {
									if (dbOperator == 0) {
										FileManager.insert(ProgramDrama.textLoad.getText().toString(),
												textIdentifier.getText(), textTitle.getText(), textDirector.getText(),
												textPerformanceDate.getText(), textTicketPrice.getText());
									} else {
										dbMethods.connect();
										dbMethods.insert(textIdentifier.getText(), textTitle.getText(),
												textDirector.getText(), textPerformanceDate.getText(),
												textTicketPrice.getText());
										dbMethods.disconnect();
									}
								}
							}
						}
					}
				}
			}
		});
		btnAdd.setBackground(new Color(0, 128, 0));
		btnAdd.setBounds(123, 231, 89, 23);
		contentPanel.add(btnAdd);

		JButton btnTisztt = new JButton("Clear");
		btnTisztt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textIdentifier.setText("");
				textTitle.setText("");
				textPerformanceDate.setText("");
				textDirector.setText("");
				textTicketPrice.setText("");
			}
		});
		btnTisztt.setBackground(new Color(0, 128, 0));
		btnTisztt.setBounds(275, 231, 89, 23);
		contentPanel.add(btnTisztt);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBackground(Color.RED);
		btnExit.setBounds(383, 298, 89, 23);
		contentPanel.add(btnExit);
	}

}
