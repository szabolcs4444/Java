package gyak9;

import javax.swing.table.DefaultTableModel;

public class ListTM extends DefaultTableModel {
	public ListTM(Object fildNames[],int rows) {
		super(fildNames,rows);
	}
	
	public boolean isCellEditable(int row, int col) {
		if(col == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public Class<?> getColumnClass(int index){
		if(index == 0 || index == 2) {
			return (Boolean.class);
		}else {
			return(String.class);
		}
	}

}
