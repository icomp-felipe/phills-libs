package com.phill.libs.table;

import javax.swing.table.DefaultTableModel;

/** This class has a custom implementation of a {@link DefaultTableModel} where only positive integers are allowed.<br>
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 25/APR/2021 */
public class PositiveIntegerTableModel extends DefaultTableModel {

	// Serial
	private static final long serialVersionUID = 3537065143842885889L;
	
	/** Main constructor setting column titles.
	 *  @param columnTitles - array of column titles */
	public PositiveIntegerTableModel(final String[] columnTitles) {
		super(null, columnTitles);
	}

	/** Returns always {@link Integer}. */
	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return Integer.class;
	}
	
	/** Sets the object value for the cell at column and row only if <code>value</code> is a positive integer. */
	@Override
	public void setValueAt(final Object value, final int row, final int column) {
		
		if (value instanceof Integer)
			if ((int) value >= 0)
				super.setValueAt(value, row, column);
		
	}
	
}