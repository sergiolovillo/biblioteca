package biblioteca;

import javax.swing.table.DefaultTableModel;

public class ModeloDatos extends DefaultTableModel{
	public ModeloDatos() {
		super();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
