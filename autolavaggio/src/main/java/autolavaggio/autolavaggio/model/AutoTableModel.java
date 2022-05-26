package autolavaggio.autolavaggio.model;

import java.time.format.DateTimeFormatter;

import java.util.*;

import javax.swing.table.AbstractTableModel;

public class AutoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	protected static final String[] COLUMN_NAMES = {
	        "Targa",
	        "DataConsegna",
	        "Stato",
	        "Cliente CF",
	        "Orario Delta",
	        "Tipo",
	    };
	
	private List<Auto> rowData;
	
	public void removeAll() {
		rowData = new ArrayList<>(25);
	}
	
	public AutoTableModel() {
		rowData = new ArrayList<>(25);
	}
	
	public void add(Auto... pd) {
        add(Arrays.asList(pd));
    }
	
	public void add(List<Auto> pd) {
        rowData.addAll(pd);
        fireTableDataChanged();
    }
	
	@Override
	public int getRowCount() {
		return rowData.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMN_NAMES.length;
	}
	
	@Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		Auto auto = getAutoDataAt(rowIndex);
        Object value = null;
        switch (columnIndex) {
            case 0:
                value = auto.getTarga();
                break;
            case 1:
                value =  auto.getDataConsegna().format(formatter);
                break;
            case 2:
            	value = auto.getStato();
            	break;
            case 3:
            	value = auto.getOrariodelta() == null ? "N/D" : auto.getOrariodelta().format(formatter);
            	break;
            case 4:
            	value = auto.getTipo() == null ? "N/D" : auto.getTipo();
            	break;
            default:
            	break;
        }
        return value;
	}
	
	public Auto getAutoDataAt(int row) {
        return rowData.get(row);
    }

}
