package com.phill.libs.table;

import javax.swing.JTable;

/** This interface helps dealing with {@link JTable} data filling.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 17/SEP/2020 */
@FunctionalInterface
public interface JTableRowData {

	/** Method to help dealing with {@link JTable} data filling. 
	 * @return An {@link Object} array with data to be filled in a {@link JTable} (from left to right). */
	public Object[] getRowData();
	
}
