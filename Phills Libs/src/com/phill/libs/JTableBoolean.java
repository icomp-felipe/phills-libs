package com.phill.libs;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class JTableBoolean extends JTable {

	private final int booleanColumn;
	private static final long serialVersionUID = 1L;

	public JTableBoolean(TableModel model, int booleanColumn) {
		super(model);
		this.booleanColumn = booleanColumn;
	}
	
	@Override
    public Class<?> getColumnClass(int column) {
		return (column == booleanColumn) ? Boolean.class : String.class;
    }
	
}
