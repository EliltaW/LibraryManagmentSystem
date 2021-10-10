package librarysystem.tables;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class CustomTableModel extends AbstractTableModel {

    private List<Object[]> tableValues; 
    
    public void setTableValues(Object [][] vals) {
        tableValues = Arrays.asList(vals);
    }

    public void setTableValues(List<String[]> vals) {
    	if(vals == null) {
    		return;
    	}
    	Iterator<String[]> it = vals.iterator();
    	while(it.hasNext()){
    		addRow(it.next());
    	}
    }    
    public Object[][] getTableValues() {
        return (Object[][])tableValues.toArray();
    }

    public void addRow(Object row){
    	if(row instanceof Object[]){
    		addRow((Object[])row);
    	}
    	
    }
    
    public void addRow(Object[] row){
    	if(tableValues == null){
    		tableValues = new ArrayList<Object[]>();
    	}
    	tableValues.add(row);
    }

    public Object getValueAt(int rowIndex, int colIndex) {
    	Object[] row = (Object[])tableValues.get(rowIndex);
        return row[colIndex];
    }

    public void setValueAt(Object val, int rowIndex, int colIndex) {
    }
    public int getColumnCount() {
        return 0;
    }
    public int getRowCount() {
        if(tableValues==null) return 0;
        return tableValues.size();
    }

	private static final long serialVersionUID = 3257846584573376055L;

}
     