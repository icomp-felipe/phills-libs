package com.phill.libs;

import javax.swing.table.DefaultTableModel;

public class BooleanTableModel extends DefaultTableModel {

	private final int booleanColumn;
	private static final long serialVersionUID = 1L;
	
	public BooleanTableModel(Object[] colunas) {
		this(null,colunas);
	}
	
	public BooleanTableModel(Object[][] dados, Object[] colunas) {
		super(dados,colunas);
		this.booleanColumn = (colunas.length - 1);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return (column == booleanColumn);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Class getColumnClass(int columnIndex) {
		return (columnIndex == booleanColumn) ? Boolean.class : String.class;
	}

}
