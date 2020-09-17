package com.phill.libs;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import com.phill.libs.table.JTableRowData;

/** Provides useful methods to deal with Table components in Java UI applications.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.0, 17/SEP/2020 */
public class TableUtils {

	/** Get an object from the given list related to the current selected table row.
	 *  @param table - table filled with data
	 *  @param list - list of objects (source of the given table data)
	 *  @return An object corresponding to the selected table row. */
	public static <T> T getSelected(final JTable table, final ArrayList<T> list) {
		
		int index = table.getSelectedRow();
		return (index == -1) ? null : list.get(index);
		
	}
	
	/** Remove all rows from a given table model.
	 *  @param model - table model: when removing rows from here, it automaticaly reflects to UI */
	public static void clear(final DefaultTableModel model) {
		
		int rows = model.getRowCount();
		
		for (int i=0; i<rows; i++)
			model.removeRow(0);
		
	}
	
	/** Remove all rows from a given table model and updates the given label text with a '0' String.
	 *  @param model - table model: when removing rows from here, it automaticaly reflects to UI
	 *  @param label - a {@link JLabel} to have its text updated */
	public static void clear(final DefaultTableModel model, final JLabel label) {
		
		clear(model);
		label.setText("0");
		
	}
	
	/** Get the row count from the given 'model' and sets it in the 'label'.
	 *  @param model - table data
	 *  @param label - a {@link JLabel} to have its text updated with table size */
	public static void updateSize(final DefaultTableModel model, final JLabel label) {
		
		String size = Integer.toString(model.getRowCount()); 
		label.setText(size);
		
	}
	
	/** Adds a new row to a table with data coming from the {@link JTableRowData} object.
	 *  @param model - table data
	 *  @param data - row data */
	public static void add(DefaultTableModel model, JTableRowData data) {
		
		Object[] resume = data.getRowData();
		model.addRow(resume);
		
	}
	
	/** Fills a table with data coming from the given list and updates the label with table size data.
	 *  @param model - table
	 *  @param list - source of table data
	 *  @param label - a {@link JLabel} to have its text updated with table size */
	public static <T extends JTableRowData> void load(final DefaultTableModel model, final List<T> list, final JLabel label) {
		
		clear(model);
		
		for (JTableRowData resume: list)
			add(model,resume);
		
		updateSize(model,label);
		
	}
	
	/** Displays every column preferred width inside a {@link JTable}.
	 *  @param table - table of contents */
	public static void listWidth(final JTable table) {
		
		TableColumnModel model = table.getColumnModel();
		
		for (int i=0; i<table.getColumnCount(); i++)
			System.out.printf("Column %d: %d\n",i,model.getColumn(i).getPreferredWidth());
		
	}
	
}
