package com.phill.libs.table;

import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;

/** This class has a custom implementation of a {@link DefaultTableModel} where you can
 *  define some columns to be a {@link JCheckBox} instead of the regular text ones.<br>
 *  Note 1: {@link JCheckboxTable} is the table class for being used with this model;<br>
 *  Note 2: remember to always use the same constructor data between table models and tables. 
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 18/SEP/2020 */
public class CheckboxTableModel extends DefaultTableModel {

	// Serial
	private static final long serialVersionUID = 4948608145552686729L;
	
	// Index of checkbox enabled columns
	private final ArrayList<Integer> checkboxColumnsIndex;

	/** This constructor sets the column header and defines some column indexes to be treated
	 *  as {@link JCheckBox} instead of regular text fields. If no valid indexes are passed
	 *  (between 0 and columns.lenght), this class behaves like a regular {@link DefaultTableModel}.<br>
	 *  Note: by default, only the checkbox fields are editable.
	 *  @param header - column header object data - usually a String array
	 *  @param checkboxColumnsIndex - indexes to be treated as {@link JCheckBox}es */
	public CheckboxTableModel(final Object[] header, final int... checkboxColumnsIndex) {
		this(header,null,checkboxColumnsIndex);
	}
	
	/** This constructor sets the column header, fills the initial data and defines some column indexes to be treated
	 *  as {@link JCheckBox} instead of regular text fields. If no valid indexes are passed
	 *  (between 0 and columns.lenght), this class behaves like a regular {@link DefaultTableModel}.<br>
	 *  Note: by default, only the checkbox fields are editable.
	 *  @param header - column header object data - usually a String array
	 *  @param data - initial data to be filled
	 *  @param checkboxColumnsIndex - indexes to be treated as {@link JCheckBox}es */
	public CheckboxTableModel(final Object[] header, final Object[][] data, final int... checkboxColumnsIndex) {
		super(data,header);
		this.checkboxColumnsIndex = parse(header,checkboxColumnsIndex);
	}
	
	/** Iterate through the given indexes and selects those who are in the column index range.
	 *  @param header - column header data
	 *  @param indexes - indexes to be treated as {@link JCheckBox}es */
	private ArrayList<Integer> parse(final Object[] header, final int... indexes) {

		final ArrayList<Integer> indexList = new ArrayList<Integer>(header.length);
		
		for (int index: indexes)
			if ((index >= 0) && (index < header.length))
				indexList.add(index);
		
		return indexList;
	}
	
	/** A cell is only editable if the given 'columnIndex' is a {@link JCheckBox} enabled field.
	 *  @param rowIndex - ignored
	 *  @param columnIndex - this one is used to determine if the current cell is editable
	 *  @return 'true' if the column index is a {@link JCheckBox} enabled field or 'false' otherwise. */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return this.checkboxColumnsIndex.contains(columnIndex);
	}
	
	/** This one does the trick. If the 'columnIndex' is a {@link JCheckBox} enabled field, then a
	 *  {@link Boolean} class is returned, otherwise, {@link String}.
	 *  @return {@link Boolean} if column index is a checkbox enabled field, {@link String} otherwise. */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.checkboxColumnsIndex.contains(columnIndex) ? Boolean.class : String.class;
	}
	
}
