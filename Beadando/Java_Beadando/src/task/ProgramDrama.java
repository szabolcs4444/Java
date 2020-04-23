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

	private DramaTableDesign dramaTableModel;
	private File fileLoad;
	private String comboBoxString;
	private String comboBoxStringSecond;
	static JTextField textLoad;
	private JTextField textPrint;
	private int databaseOperator;
	private String dbConnect;
	private DatabaseMethods dbMethods = new DatabaseMethods();
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
		JButton buttonLoad = new JButton("Load data");
		buttonLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String csvEmpty = "";
				String csvFile = ".csv file";
				String csvSqlite = "sqlite db";
				if (csvEmpty.equals(comboBoxString)) {
					JOptionPane.showMessageDialog(null, "Choose one file format! ", " Program message", 0);
				} else if (csvFile.equals(comboBoxString)) {
					databaseOperator = 0;
					FileDialog fileDialog = new FileDialog(new Frame(), " ", FileDialog.LOAD);
					fileDialog.setFile("*.csv");
					fileDialog.setVisible(true);
					if (fileDialog.getFile() != null) {
						fileLoad = new File(fileDialog.getDirectory(), fileDialog.getFile());
						String bName = fileDialog.getFile();
						textLoad.setText(bName);
						FileManager.readCsv(fileLoad, dramaTableModel);
					}
				} else if (csvSqlite.equals(comboBoxString)) {
					databaseOperator = 1;
					dbMethods.reg();
					dbMethods.connect();
					dbConnect = textLoad.getText();
					dramaTableModel = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}

			}
		});
		buttonLoad.setBackground(new Color(0, 128, 0));
		buttonLoad.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonLoad.setBounds(603, 33, 175, 23);
		contentPane.add(buttonLoad);

		JButton buttonListData = new JButton("List data");
		buttonListData.setBackground(new Color(0, 128, 0));
		buttonListData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (databaseOperator == 0) {
					dramaTableModel = FileManager.readCsv(fileLoad, dramaTableModel);
				} else if (databaseOperator == 1) {
					dbMethods.connect();
					dramaTableModel = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}
				DramaList dl = new DramaList(ProgramDrama.this, dramaTableModel);
				dl.setVisible(true);
			}
		});
		buttonListData.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonListData.setBounds(10, 33, 155, 23);
		contentPane.add(buttonListData);

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

		JLabel labelFormatSelectionLoad = new JLabel("Format selection:");
		labelFormatSelectionLoad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelFormatSelectionLoad.setBounds(186, 9, 147, 14);
		contentPane.add(labelFormatSelectionLoad);

		JLabel labelNewLabel_1 = new JLabel("The name of the file to load:");
		labelNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNewLabel_1.setBounds(376, 8, 187, 14);
		contentPane.add(labelNewLabel_1);

		JButton buttonNewData = new JButton("Add new data");
		buttonNewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (databaseOperator == 0) {
					NewDrama nd = new NewDrama(databaseOperator);
					nd.setVisible(true);
				} else if (databaseOperator == 1) {
					NewDrama nd = new NewDrama(databaseOperator);
					nd.setVisible(true);
				}
			}
		});
		buttonNewData.setBackground(new Color(0, 128, 0));
		buttonNewData.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonNewData.setBounds(10, 134, 175, 23);
		contentPane.add(buttonNewData);

		JButton buttonModifyData = new JButton("Modify data");
		buttonModifyData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (databaseOperator == 0) {
					dramaTableModel = FileManager.readCsv(fileLoad, dramaTableModel);

				} else if (databaseOperator == 1) {
					dbMethods.connect();
					dramaTableModel = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}

				DramaModify dramaMod = new DramaModify(ProgramDrama.this, dramaTableModel, databaseOperator, fileLoad);
				dramaMod.setVisible(true);
			}
		});
		buttonModifyData.setBackground(new Color(0, 128, 0));
		buttonModifyData.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonModifyData.setBounds(10, 197, 175, 23);
		contentPane.add(buttonModifyData);

		JButton buttonDeleteData = new JButton("Delete data");
		buttonDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (databaseOperator == 0) {
					dramaTableModel = FileManager.readCsv(fileLoad, dramaTableModel);

				} else if (databaseOperator == 1) {
					dbMethods.connect();
					dramaTableModel = dbMethods.readAllData(dbConnect);
					dbMethods.disconnect();
				}

				DramaDelete dd = new DramaDelete(ProgramDrama.this, dramaTableModel, databaseOperator, fileLoad);
				dd.setVisible(true);
			}
		});
		buttonDeleteData.setBackground(new Color(0, 128, 0));
		buttonDeleteData.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonDeleteData.setBounds(10, 267, 175, 23);
		contentPane.add(buttonDeleteData);

		JButton buttonSaveData = new JButton("Save data");
		buttonSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringEmpty = "";
				String csvFile = ".csv file";
				String csvSqlite = "sqlite db";
				String csvJson = ".json file";
				String csvXml = ".xml file";
				String csvPdf = ".pdf file";
				if (stringEmpty.equals(comboBoxStringSecond)) {
					JOptionPane.showMessageDialog(null, "Choose one file format! ", " Program message", 0);
				} else if (dramaTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "No data to print! ", " Program message", 0);
				} else if (csvFile.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename specified! ", " Program message", 0);
					} else {
						FileManager.writeCsv(textPrint.getText().toString(), dramaTableModel);
					}
				} else if (csvSqlite.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No table name specified! ", " Program message", 0);
					} else {
						dbMethods.write(textPrint.getText().toString(), dramaTableModel);
					}
				} else if (csvXml.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename specified! ", " Program message", 0);
					} else {
						XmlWriter.xmlWriter(textPrint.getText().toString(), dramaTableModel);
					}
				} else if (csvJson.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename  specified! ", " Program message", 0);
					} else {
						JsonWriter.write(textPrint.getText().toString(), dramaTableModel);
					}
				} else if (csvPdf.equals(comboBoxStringSecond)) {
					if (textPrint.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "No filename specified! ", " Program message", 0);
					} else {
						PdfWrite.write(textPrint.getText().toString(), dramaTableModel);
					}
				}
			}
		});
		buttonSaveData.setBackground(new Color(0, 128, 0));
		buttonSaveData.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonSaveData.setBounds(609, 361, 169, 23);
		contentPane.add(buttonSaveData);
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

		JLabel labelFormatSelectionSave = new JLabel("Format selection:");
		labelFormatSelectionSave.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelFormatSelectionSave.setBounds(186, 337, 147, 14);
		contentPane.add(labelFormatSelectionSave);

		textPrint = new JTextField();
		textPrint.setBounds(322, 363, 277, 20);
		contentPane.add(textPrint);
		textPrint.setColumns(10);

		JLabel labelNewLabel_1_1 = new JLabel("the name of the file/table you want to save:");
		labelNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelNewLabel_1_1.setBounds(322, 337, 271, 14);
		contentPane.add(labelNewLabel_1_1);

		JButton buttonExit = new JButton("Exit");
		buttonExit.setBackground(new Color(255, 0, 0));
		buttonExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonExit.setFont(new Font("Arial Black", Font.BOLD, 12));
		buttonExit.setBounds(609, 441, 169, 23);
		contentPane.add(buttonExit);
	}

}
