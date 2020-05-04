package gyak9;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Help extends JDialog {

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public Help(int xk, int yk) {
		setTitle("FileSystem Manager Help by Bitman");
		getContentPane().setBackground(new Color(184, 213, 183));
		setBounds(xk+790, yk, 450, 500);
		getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(164, 413, 89, 23);
		getContentPane().add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Manage Folders");
		lblNewLabel.setBounds(24, 11, 148, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblManageFiles = new JLabel("Manage Files");
		lblManageFiles.setBounds(24, 197, 148, 14);
		getContentPane().add(lblManageFiles);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 36, 407, 150);
		getContentPane().add(scrollPane);
		
		JTextArea textManFold = new JTextArea();
		scrollPane.setViewportView(textManFold);
		textManFold.append("- When you start the program, the current folder is the folder where you"+"\n started it \n");
		textManFold.append("- Press the List button to display the entire contents of the folder\n");
		textManFold.append("- When you select a file, press View to view its contents\n");
		textManFold.append("- The content viewer is the program associated with that extension in te operating system\n");
		textManFold.append("- You can move up one level with the Change Up Folder button\n");
		textManFold.append("-Go down one level: \n");
		textManFold.append("-Click the Change Child Folder button \n");
		textManFold.append("- The subfolders appear in the list\n");
		textManFold.append("- Select the appropriate subfolder\n");
		textManFold.append("- Click the Change button\n\n");
		textManFold.append("- Create new folder:\n");
		textManFold.append("- The new folder will always be created below the current folder, so the appropriate parent folder should be selected \n");
		textManFold.append("- Enter a name for the New folder\n");
		textManFold.append("- Ckucj the Create New Folder button\n");
		textManFold.append("- If a folder exists, an Error Message will be displayed\n\n");
		textManFold.append("- Delete folder\n");
		textManFold.append("- Only the current folder can be deleted, so the appropriate folder must be selected\n");
		textManFold.append("- Only empty folders can be deleted, if not empty, Error Message will be displayed\n");
		textManFold.append("- To delete, press Delete Cur. Folder button\n");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(21, 222, 407, 143);
		getContentPane().add(scrollPane_1);
		
		JTextArea textManFile = new JTextArea();
		scrollPane_1.setViewportView(textManFile);
		textManFile.append("-Create new file: \n");
		textManFile.append("- The new file is created in the current folder, so the appropriate folder must be selected\n");
		textManFile.append("- A new file can be created with content by typing the content into the New File Content window\n");
		textManFile.append("- Create: Enter a name for the new file and press the Create New File button\n");
		textManFile.append("- Delete file\n");
		textManFile.append("- A file can be deleted from the current folder, so the appropriate folder must be selected\n");
		textManFile.append("- Press the Delete File button\n");
		textManFile.append("- The files in the folder are displayed on the panel\n");
		textManFile.append("- A file must be selected\n");
		textManFile.append("- Press the Delete key\n");
		textManFile.append("- Copy file\n");
		textManFile.append("- The file can be copied from the current folder, so the appropriate folder must be selected\n");
		textManFile.append("- Press the Select File button\n");
		textManFile.append("- In the panel, you nned to select the file to copy\n");
		textManFile.append("- Press the Select to Copy button\n");
		textManFile.append("- The absolute name of the file is displayed in the Source field\n");
		textManFile.append("- Select the destination folder for the current folder\n");
		textManFile.append("- You have to press Select Dest. Folder button\n");
		textManFile.append("- The absolute name of the file is displayed in the Destination Folder field\n");
		textManFile.append("- The copy is made when the Copy file button is pressed\n");
		

	}
}
