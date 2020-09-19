package com.phill.libs.table;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.TableModel;

/** This class has a custom implementation of a {@link JTable} where you can
 *  define some columns to be a {@link JCheckBox} instead of the regular text ones.<br>
 *  Note 1: {@link CheckboxTableModel} is the model class for being used with this table;<br>
 *  Note 2: remember to always use the same constructor data between table models and tables. 
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 18/SEP/2020 */
public class JCheckboxTable extends JTable {

	// Serial
	private static final long serialVersionUID = 3035035062151029830L;
	
	// Index of checkbox enabled columns
	private final ArrayList<Integer> checkboxColumnsIndex;
	private boolean editable = true;
	
	/** This constructor sets the column header and defines some column indexes to be treated
	 *  as {@link JCheckBox} instead of regular text fields. If no valid indexes are passed
	 *  (between 0 and model::getColumnCount), this class behaves like a regular {@link JTable}.<br>
	 *  Note: by default, only the checkbox fields are editable.
	 *  @param model - table model
	 *  @param checkboxColumnsIndex - indexes to be treated as {@link JCheckBox}es */
	public JCheckboxTable(final TableModel model, final int... checkboxColumnsIndex) {
		super(model);
		this.checkboxColumnsIndex = parse(model.getColumnCount(),checkboxColumnsIndex);
	}
	
	/** Iterate through the given indexes and selects those who are in the column index range.
	 *  @param columnCount - table column count
	 *  @param indexes - indexes to be treated as {@link JCheckBox}es */
	private ArrayList<Integer> parse(final int columnCount, final int... indexes) {

		final ArrayList<Integer> indexList = new ArrayList<Integer>(columnCount);
		
		for (int index: indexes)
			if ((index >= 0) && (index < columnCount))
				indexList.add(index);
		
		return indexList;
	}
	
	/** A cell is only editable if the given 'columnIndex' is a {@link JCheckBox} enabled field and the class has been marked as editable.
	 *  @param rowIndex - ignored
	 *  @param columnIndex - this one is used to determine if the current cell is editable
	 *  @return 'true' if this table is editable and the column index is a {@link JCheckBox} enabled field or 'false' otherwise. */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return this.editable && this.checkboxColumnsIndex.contains(columnIndex);
	}
	
	/** This one does the trick. If the 'columnIndex' is a {@link JCheckBox} enabled field, then a
	 *  {@link Boolean} class is returned, otherwise, {@link String}.
	 *  @return {@link Boolean} if column index is a checkbox enabled field, {@link String} otherwise. */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.checkboxColumnsIndex.contains(columnIndex) ? Boolean.class : String.class;
	}

	/** Controls the edition capability of enabled checkbox fields.
	 *  @param enable - enables or disables checkbox field edition */
	public void setEditable(final boolean enable) {
		this.editable = enable;
	}
	
}
