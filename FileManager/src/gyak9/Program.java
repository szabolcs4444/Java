package gyak9;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Program extends JFrame {
	private String selectedFile="";
	private File sourceFile,destFile;
	private File CurDir;
	private String CurDirText="";
	private String separator = System.getProperty("file.separator");

	private JPanel contentPane;
	private JTextField textCurFolder;
	private JTextField textNewDirName;
	private JTextField textNewFileName;
	private JTextArea textNewFileContent;
	private JTextField textSource;
	private JTextField textDestination;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
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
	public Program() {
		setTitle("FileSystem Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 813, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current Folder:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(4, 24, 94, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnList = new JButton("List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl1 = new ContentList(Program.this,CurDir,1);
				ctl1.setVisible(true);
			}
		});
		btnList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnList.setBounds(4, 76, 89, 23);
		contentPane.add(btnList);
		
		CurDir = new File(System.getProperty("user.dir"));
		CurDirText = CurDir.getAbsolutePath();
		
		textCurFolder = new JTextField(CurDirText);
		textCurFolder.setBackground(Color.WHITE);
		textCurFolder.setEditable(false);
		textCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textCurFolder.setBounds(103, 21, 675, 20);
		contentPane.add(textCurFolder);
		textCurFolder.setColumns(10);
		
		JButton btnChangeUpFolder = new JButton("Change Up \u2191 Folder");
		btnChangeUpFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CurDir = CurDir.getParentFile();
					CurDirText = CurDir.getAbsolutePath();
					textCurFolder.setText(CurDirText);
				}catch(Exception ex) {
					SM("You are on the top dir!\n You can't go higher!",0);
				}
			}
		});
		btnChangeUpFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangeUpFolder.setBounds(135, 76, 158, 23);
		contentPane.add(btnChangeUpFolder);
		
		JButton btnChangeChildFolder = new JButton("Change Child \u2193 Folder");
		btnChangeChildFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl2 = new ContentList(Program.this,CurDir,2);
				ctl2.setVisible(true);
				String outDir = ctl2.getOutDir();
				CurDir = new File(CurDir.getPath()+separator+outDir);
				CurDirText = CurDir.getAbsolutePath();
				textCurFolder.setText(CurDirText);
			}
		});
		btnChangeChildFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChangeChildFolder.setBounds(361, 77, 172, 23);
		contentPane.add(btnChangeChildFolder);
		
		JButton btnCreateNewFolder = new JButton("Create New Folder");
		btnCreateNewFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newDir = RTF(textNewDirName);
				if(newDir.length()==0) {
					SM("New Dir Name field is empty!",0);
				}else {
					File temp = new File(CurDir.getPath()+separator+newDir);
					if(temp.exists()) {
						SM("A folder with this name already exists!",0);
					}else {
						temp.mkdir();
						SM("Folder created!",1);
						textNewDirName.setText(null);
					}
				}
			}
		});
		btnCreateNewFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateNewFolder.setBounds(620, 128, 158, 23);
		contentPane.add(btnCreateNewFolder);
		
		JLabel lblNewFolderName = new JLabel("New Folder Name:");
		lblNewFolderName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewFolderName.setBounds(434, 105, 127, 14);
		contentPane.add(lblNewFolderName);
		
		textNewDirName = new JTextField();
		textNewDirName.setBounds(433, 130, 158, 20);
		contentPane.add(textNewDirName);
		textNewDirName.setColumns(10);
		
		JButton btnDeleteCurFolder = new JButton("Delete Cur. Folder");
		btnDeleteCurFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File newCurDir = CurDir.getParentFile();
				boolean ok = CurDir.delete();
				if(!ok) {
				SM("The folder is not empty, cannot be deleted!",0);
				}else {
						CurDir = newCurDir;
						CurDirText = CurDir.getAbsolutePath();
						textCurFolder.setText(CurDirText);
						SM("The current folder ha become the parent folder!",1);
					}
				
			}
		});
		btnDeleteCurFolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteCurFolder.setBounds(620, 162, 158, 23);
		contentPane.add(btnDeleteCurFolder);
		
		JTextArea textNewFileContent = new JTextArea();
		textNewFileContent.setBounds(4, 133, 358, 121);
		contentPane.add(textNewFileContent);
		
		JLabel lblNewLabel_1 = new JLabel("New File Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(434, 191, 127, 14);
		contentPane.add(lblNewLabel_1);
		
		textNewFileName = new JTextField();
		textNewFileName.setBounds(434, 216, 157, 20);
		contentPane.add(textNewFileName);
		textNewFileName.setColumns(10);
		
		JButton btnCreateNewFile = new JButton("Create New File");
		btnCreateNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fileName = textNewFileName.getText();
				if(fileName.length()==0) {
					SM("File name is missing!",0);
				}else {
					CreateFile(textNewFileContent,fileName);
					textNewFileName.setText(null);
					textNewFileContent.setText(null);
					SM("File created",1);
				}
			}
		});
		btnCreateNewFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCreateNewFile.setBounds(620, 215, 158, 23);
		contentPane.add(btnCreateNewFile);
		
		JButton btnDeleteFile = new JButton("Delete File");
		btnDeleteFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentList ctl3 = new ContentList(Program.this,CurDir,3);
				ctl3.setVisible(true);
			}
		});
		btnDeleteFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDeleteFile.setBounds(620, 242, 158, 23);
		contentPane.add(btnDeleteFile);
		
		JLabel lblNewLabel_2 = new JLabel("Copy File:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(4, 265, 63, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Select the source folder as the Current Dir");
		lblNewLabel_3.setBounds(4, 286, 266, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Select the file from the folder, with Select File button");
		lblNewLabel_3_1.setBounds(4, 300, 266, 14);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Select the destination folder for the current folder");
		lblNewLabel_3_2.setBounds(4, 322, 266, 14);
		contentPane.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("Press the Select button to select the current folder as the destination folder");
		lblNewLabel_3_2_1.setBounds(4, 337, 367, 14);
		contentPane.add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("Press Copy File button to copy");
		lblNewLabel_3_2_2.setBounds(4, 360, 266, 14);
		contentPane.add(lblNewLabel_3_2_2);
		
		JLabel lblNewLabel_4 = new JLabel("Source: Folder + File");
		lblNewLabel_4.setBounds(4, 403, 116, 14);
		contentPane.add(lblNewLabel_4);
		
		textSource = new JTextField();
		textSource.setBackground(Color.WHITE);
		textSource.setEditable(false);
		textSource.setBounds(4, 415, 391, 20);
		contentPane.add(textSource);
		textSource.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Destination Folder (+ File)");
		lblNewLabel_5.setBounds(4, 456, 138, 14);
		contentPane.add(lblNewLabel_5);
		
		textDestination = new JTextField();
		textDestination.setBackground(Color.WHITE);
		textDestination.setEditable(false);
		textDestination.setBounds(4, 469, 391, 20);
		contentPane.add(textDestination);
		textDestination.setColumns(10);
		
		JButton btnSelectFile = new JButton("Select File");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(Program.this);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					sourceFile = jfc.getSelectedFile();
					selectedFile= sourceFile.getName();
					textSource.setText(sourceFile.getAbsolutePath());
				}
				
				
						
			}
		});
		btnSelectFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectFile.setBounds(433, 414, 158, 23);
		contentPane.add(btnSelectFile);
		
		JButton btnSelectDestfolder = new JButton("Select Dest.Folder");
		btnSelectDestfolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("Choose a directory to save your file: ");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(Program.this);
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					destFile = new File(jfc.getSelectedFile()+ separator + selectedFile);
					textDestination.setText(destFile.getAbsolutePath());
				}
				
			}
		});
		btnSelectDestfolder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectDestfolder.setBounds(433, 468, 158, 23);
		contentPane.add(btnSelectDestfolder);
		
		JButton btnCopyFile = new JButton("Copy File");
		btnCopyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textSource.getText().length()==0) {
					SM("No source file selected",0);
				}else if(textDestination.getText().length()==0) {
					SM("No destination folder selected",0);
				}else {
					copyFile(sourceFile,destFile);
					SM("File copied",1);
				}
				
			}
		});
		btnCopyFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCopyFile.setBounds(633, 440, 158, 23);
		contentPane.add(btnCopyFile);
		
		JButton btnNewButton = new JButton("Help");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help he = new Help(leftCornerX(), leftCornerY());
				he.setVisible(true);
			}
		});
		btnNewButton.setBounds(689, 77, 89, 23);
		contentPane.add(btnNewButton);
	}
	public void SM(String msg,int type) {
		JOptionPane.showMessageDialog(Program.this, msg,"FileSystem Manager message",type);
	}
	
	public String RTF(JTextField jtf) {
		return jtf.getText();
	}
	
	public void CreateFile(JTextArea jta, String fileName) {
		fileName = CurDir.getAbsolutePath()+separator+fileName;
		try {
			PrintStream out = new PrintStream(new FileOutputStream(fileName));
			out.print(jta.getText());
			out.close();
			SM("Data is written to a file!",1);
		}catch(IOException ioe) {
			SM("CreateFile method :"+ ioe.getMessage(),0);
		}
	}
	
	public void copyFile(File source, File dest) {
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while((length = is.read(buffer))>0){
				os.write(buffer,0,length);
			}
			is.close();
			os.close();
			
		}catch(Exception e ) {
			SM("copyFile: "+e.getMessage(),0);
		}
	}
	
	public int leftCornerX() {
		Point bs = getLocation();
		int bsx = (int)bs.getX();
		return bsx;
	
	}
	
	public int leftCornerY() {
		Point bs = getLocation();
		int bsy = (int)bs.getY();
		return bsy;
	}
}
