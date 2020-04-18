package beadando;

import javax.swing.table.DefaultTableModel;

public class DramaTM extends DefaultTableModel {
	public DramaTM (Object fildNames[],int rows) {
		super(fildNames,rows);
	}
	
	public boolean isCellEditable(int row,int col) {
		if(col==0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public Class<?> getColumnClass(int index){
		if(index==0) {
			return(Boolean.class);
		}else if(index==1||index==5) {
			return(Integer.class);
			
		}
		return(String.class);
	}
}