package task;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.io.File;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

public class ProgramDrama extends JFrame {

	private JPanel contentPane;

	private DramaTM drama;
	private File fileLoad;
	private String comboBoxString;
	private String comboBoxStringSecond;
	static JTextField textLoad;
	private JTextField textPrint;
	private int dbOperator;
	private String dbConnect;
	private DBMethods dbMethods = new DBMethods();
	private XmlWriter xmlWriter = new XmlWriter();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramDrama frame = new ProgramDrama();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProgramDrama() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 510);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btnLoad = new JButton("Load data");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String csvEmpty = "";
				String csvFile = ".csv file";
				String csvSqlite = "sqlite db";
				if (csvEmpty.equals(comboBoxString)) {
					JOptionPane.showMessageDialog(null, "Choose one file format! ", " Program message", 0);
				} else if (csvFile.equals(comboBoxString)) {
					dbOperator = 0;
					FileDialog fileDialog = new FileDialog(new Frame(), " ", FileDialog.LOAD);
					fileDialog.setFile("*.csv");
					fileDialog.setVisible(true);
					if (fileDialog.getFile() != null) {
						fileLoad = new File(fileDialog.getDirectory(), fileDialog.getFile());
						String bName = fileDialog.getFile();
						textLoad.setText(bName);
						FileManager.csvReader(fileLoad, drama);
					}
				} else if (csvSqlite.equals(comboBoxString)) {
					dbOperator = 1;
					dbMethods.reg();
					dbMethods.connect();
					dbConnect = textLoad.getText();
					drama = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}

			}
		});
		btnLoad.setBackground(new Color(0, 128, 0));
		btnLoad.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLoad.setBounds(603, 33, 175, 23);
		contentPane.add(btnLoad);

		JButton btnListData = new JButton("List data");
		btnListData.setBackground(new Color(0, 128, 0));
		btnListData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbOperator == 0) {
					drama = FileManager.csvReader(fileLoad, drama);
				} else if (dbOperator == 1) {
					dbMethods.connect();
					drama = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}
				DramaList dl = new DramaList(ProgramDrama.this, drama);
				dl.setVisible(true);
			}
		});
		btnListData.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnListData.setBounds(10, 33, 155, 23);
		contentPane.add(btnListData);

		String element[] = { "", ".csv file", "sqlite db" };
		JComboBox comboBox = new JComboBox();
		for (String s : element)
			comboBox.addItem(s);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				comboBoxString = (String) comboBox.getSelectedItem();
				textLoad.setText(comboBoxString);
			}
		});

		comboBox.setBounds(186, 34, 135, 22);
		contentPane.add(comboBox);

		textLoad = new JTextField();
		textLoad.setBounds(322, 35, 271, 20);
		contentPane.add(textLoad);
		textLoad.setColumns(10);

		JLabel lblFormatSelectionLoad = new JLabel("Format selection:");
		lblFormatSelectionLoad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFormatSelectionLoad.setBounds(186, 9, 147, 14);
		contentPane.add(lblFormatSelectionLoad);

		JLabel lblNewLabel_1 = new JLabel("The name of the file to load:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(376, 8, 187, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnNewData = new JButton("Add new data");
		btnNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbOperator == 0) {
					NewDrama nd = new NewDrama(dbOperator);
					nd.setVisible(true);
				} else if (dbOperator == 1) {
					NewDrama nd = new NewDrama(dbOperator);
					nd.setVisible(true);
				}
			}
		});
		btnNewData.setBackground(new Color(0, 128, 0));
		btnNewData.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNewData.setBounds(10, 134, 175, 23);
		contentPane.add(btnNewData);

		JButton btnModifyData = new JButton("Modify data");
		btnModifyData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbOperator == 0) {
					drama = FileManager.csvReader(fileLoad, drama);

				} else if (dbOperator == 1) {
					dbMethods.connect();
					drama = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}

				DramaMod dramaMod = new DramaMod(ProgramDrama.this, drama, dbOperator, fileLoad);
				dramaMod.setVisible(true);
			}
		});
		btnModifyData.setBackground(new Color(0, 128, 0));
		btnModifyData.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnModifyData.setBounds(10, 197, 175, 23);
		contentPane.add(btnModifyData);

		JButton btnDeleteData = new JButton("Delete data");
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbOperator == 0) {
					drama = FileManager.csvReader(fileLoad, drama);

				} else if (dbOperator == 1) {
					dbMethods.connect();
					drama = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}

				DramaDel dd = new DramaDel(ProgramDrama.this, drama, dbOperator, fileLoad);
				dd.setVisible(true);
			}
		});
		btnDeleteData.setBackground(new Color(0, 128, 0));
		btnDeleteData.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnDeleteData.setBounds(10, 267, 175, 23);
		contentPane.add(btnDeleteData);

		JButton btnSaveData = new JButton("Save data");
		btnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringEmpty = "";
				String csvFile = ".csv file";
				String csvSqlite = "sqlite db";
				String csvJson = ".json file";
				String csvXml = ".xml file";
				String csvPdf = ".pdf file";
				if (stringEmpty.equals(comboBoxStringSecond)) {
					JOptionPane.showMessageDialog(null, "Choose one file format! ", " Program message", 0);
				} else if (drama.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "No data to print! ", " Program message", 0);
				} else if (csvFile.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename specified! ", " Program message", 0);
					} else {
						FileManager.csvWriter(textPrint.getText().toString(), drama);
					}
				} else if (csvSqlite.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No table name specified! ", " Program message", 0);
					} else {
						dbMethods.dbWriter(textPrint.getText().toString(), drama);
					}
				} else if (csvXml.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename specified! ", " Program message", 0);
					} else {
						XmlWriter.xmlWriter(textPrint.getText().toString(), drama);
					}
				} else if (csvJson.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename  specified! ", " Program message", 0);
					} else {
						JsonWriter.JsoncsvWriter(textPrint.getText().toString(), drama);
					}
				} else if (csvPdf.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename specified! ", " Program message", 0);
					} else {
						PdfWrite.pdfWrite(textPrint.getText().toString(), drama);
					}
				}
			}
		});
		btnSaveData.setBackground(new Color(0, 128, 0));
		btnSaveData.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnSaveData.setBounds(609, 361, 169, 23);
		contentPane.add(btnSaveData);
		String element2[] = { "", ".csv file", ".xml file", ".json file", "sqlite db", ".pdf file" };
		JComboBox comboBox_1 = new JComboBox();
		for (String s : element2)
			comboBox_1.addItem(s);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringEmpty = "";
				comboBoxStringSecond = (String) comboBox_1.getSelectedItem();
				if (stringEmpty.equals(textLoad.getText())) {
					JOptionPane.showMessageDialog(null, "No source specified! ", " Program message", 0);
				}
				if (!stringEmpty.equals(textLoad.getText())) {
					textPrint.getText();
				}
			}
		});

		comboBox_1.setBounds(186, 362, 135, 22);
		contentPane.add(comboBox_1);

		JLabel lblFormatSelectionSave = new JLabel("Format selection:");
		lblFormatSelectionSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFormatSelectionSave.setBounds(186, 337, 147, 14);
		contentPane.add(lblFormatSelectionSave);

		textPrint = new JTextField();
		textPrint.setBounds(322, 363, 277, 20);
		contentPane.add(textPrint);
		textPrint.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("the name of the file/table you want to save:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(322, 337, 271, 14);
		contentPane.add(lblNewLabel_1_1);

		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnExit.setBounds(609, 441, 169, 23);
		contentPane.add(btnExit);
	}

}
