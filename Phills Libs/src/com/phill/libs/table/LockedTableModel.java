package com.phill.libs.table;

import javax.swing.table.*;

/** This custom implementation of a {@link DefaultTableModel} keeps all data as
 *  non-editable fields, by always returning 'false' in isCellEditable method.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.5, 18/SEP/2020 */
public class LockedTableModel extends DefaultTableModel {

	// Serial
	private static final long serialVersionUID = 1382761112840525640L;

	/** Constructs a {@link LockedTableModel} with as many columns as
	 *  there are elements in <code>header</code>. Each column's name
	 *  will be taken from the <code>header</code> array.
	 *  @param columns - column header object data - usually a String array */
	public LockedTableModel(final String[] header) {
		super(null,header);
	}
	
	/** Constructs a <code>LockedTableModel</code> and initializes the table
	 *  by passing <code>data</code> and <code>header</code>
	 *  to the <code>setDataVector</code>
	 *  method. The first index in the <code>Object[][]</code> array is
	 *  the row index and the second is the column index.
	 *  @param header - the names of the columns
	 *  @param data - the data of the table */
	public LockedTableModel(final String[] header, final String[][] data) {
		super(data,header);
	}

	/** @return false */
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
