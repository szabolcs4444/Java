package gyak9;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import java.awt.Font;

public class ContentList extends JDialog {
	private ListTM ltm;
	private JTable table;
	private String outDir="";
	private String outFile="";
	private String separator = System.getProperty("file.separator");

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public ContentList(JFrame f, File inDir,int mod) {
		super(f," List of folder entries",true);
		setBounds(100, 100, 458, 346);
		getContentPane().setLayout(null);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(339, 231, 89, 23);
		getContentPane().add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 418, 209);
		getContentPane().add(scrollPane);
		Object emptmn[] = {"Check","Object name","Dir"};
		ltm = new ListTM(emptmn,0);
		
		File[] list = inDir.listFiles();
		for(int i = 1;i<list.length;i++) {
			boolean isd = list[i].isDirectory();
			if(mod==1) {
				ltm.addRow(new Object[] {false,list[i].getName(),isd});
			}
			if(mod==2&&isd) {
				ltm.addRow(new Object[] {false,list[i].getName(),isd});
			}
			if(mod==3&& !isd) {
				ltm.addRow(new Object[] {
						false,list[i].getName(),isd
				});
			}
		}
		
		table = new JTable(ltm);
		scrollPane.setViewportView(table);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0,jel = 0,x =0;
				for(x = 0;x<ltm.getRowCount();x++) {
					if((Boolean)ltm.getValueAt(x, 0)){
						db++;
						jel = x;
						
					}
					if(db == 0) {
						SM("NO folder Selected!",0);
					}
					if(db>1) {
						SM("Multiple folders selected! Just One!",0);
					}
					if(db==1) {
						outDir = ltm.getValueAt(jel, 1).toString();
						dispose();
					
					}
				}
				
	}
		});
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0, jel = 0,x = 0;
				for (x = 0; x<ltm.getRowCount();x++){
					if((Boolean)
						ltm.getValueAt(x, 0)) {
						db++;
						jel=x;
					}
					if(db == 0) {
						SM("NO file selected!",0);
					}
					
					if(db>1) {
						SM("Multiple rows selected!\n Only select one!",0);
					}
					if(db==1) {
						String fileName = ltm.getValueAt(jel, 1).toString();
						File delFile = new File(inDir.getAbsolutePath()+separator+fileName);
						boolean ok = delFile.delete();
						if(ok) {
							ltm.removeRow(jel);
							SM("File deleted!",1);
						}
					}
				}
				
				
			}
		});
		btnDelete.setBounds(10, 231, 99, 23);
		getContentPane().add(btnDelete);
		if(mod==3) {
			btnDelete.setVisible(true);
		}else {
			btnDelete.setVisible(false);
		}
		btnChange.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnChange.setBounds(229, 231, 89, 23);
		getContentPane().add(btnChange);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				int db = 0, jel = 0,x = 0;
				for (x = 0; x<ltm.getRowCount();x++){
					if((Boolean)
						ltm.getValueAt(x, 0)) {
						db++;
						jel=x;
					}
					if(db == 0) {
						SM("NO data row selected!",0);
					}
					
					if(db>1) {
						SM("Multiple rows selected!\n Only select one!",0);
					}
					if(db==1) {
						if((Boolean) ltm.getValueAt(jel, 2)) {
							SM("The selected entry is not a file!",0);
						}else {
							String fileName = ltm.getValueAt(jel, 1).toString();
							File file = new File(inDir.getAbsolutePath()+separator+fileName);
							try {
								Desktop.getDesktop().open(file);
							}catch(IOException ex) {
								SM("No proper application to open the file",0);
							}
						}
					}
				}
					
				
			}
		});
		btnView.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnView.setBounds(117, 231, 89, 23);
		getContentPane().add(btnView);
		
		JButton btnSelectToCopy = new JButton("Select to Copy");
		btnSelectToCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int db = 0,x=0,jel=0;
				for(x=0;x<ltm.getRowCount();x++) {
					if((Boolean) ltm.getValueAt(x, 0)) {
						db++;
						jel=x;
					}
					if(db==0) {
						SM("No file selected",0);
					}
					if(db>1) {
						SM("Multiple rows selected!\\nOnly select one!",0);
					}
					if(db==1) {
						outFile = ltm.getValueAt(jel, 1).toString();
						dispose();
					}
				}
			}
		});
		btnSelectToCopy.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnSelectToCopy.setBounds(10, 265, 115, 23);
		getContentPane().add(btnSelectToCopy);
		if(mod==4) { 
			btnView.setVisible(true);
		}else {
			btnView.setVisible(false);
		}
		
		TableColumn tc = null;
		for(int i = 0;i<3;i++) {
			tc = table.getColumnModel().getColumn(i);
			if(i==0 || i== 2) {
				tc.setPreferredWidth(20);
			}else {
				tc.setPreferredWidth(250);
			}
		}

	}
	public String getOutDir() {
		return outDir;
				
	}
	
	public void SM(String msg,int type) {
		JOptionPane.showMessageDialog(ContentList.this, msg,"FileSystem Manager �zenet",type);
	}
	public String getOutFile() {
		return outFile;
	}
}
