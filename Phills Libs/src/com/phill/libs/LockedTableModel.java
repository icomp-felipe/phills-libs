package com.phill.libs;

import javax.swing.table.*;

public class LockedTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	public LockedTableModel(String[] colunas) {
		super(null,colunas);
	}
	
	public LockedTableModel(String[][] dados, String[] colunas) {
		super(dados,colunas);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
