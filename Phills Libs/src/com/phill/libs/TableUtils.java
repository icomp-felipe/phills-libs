package com.phill.libs;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class TableUtils {

	public static <T> T getSelected(JTable table, ArrayList<T> list) {
		
		int index = table.getSelectedRow();
		return (index == -1) ? null : list.get(index);
		
	}
	
	public static void clear(DefaultTableModel model) {
		
		int rows = model.getRowCount();
		
		for (int i=0; i<rows; i++)
			model.removeRow(0);
		
	}
	
	public static void clear(DefaultTableModel model, JLabel label) {
		
		clear(model);
		label.setText("0");
		
	}
	
	public static void updateSize(DefaultTableModel model, JLabel label) {
		
		String size = Integer.toString(model.getRowCount()); 
		label.setText(size);
		
	}
	
	public static void add(DefaultTableModel model, Resumeable data) {
		
		Object[] resume = data.getResume();
		model.addRow(resume);
		
	}
	
	public static <T extends Resumeable> void load(DefaultTableModel model, List<T> list, JLabel label) {
		
		clear(model);
		
		for (Resumeable resume: list)
			add(model,resume);
		
		updateSize(model,label);
		
	}
	
	public static void listTableWidth(JTable table) {
		
		TableColumnModel model = table.getColumnModel();
		
		for (int i=0; i<table.getColumnCount(); i++)
			System.out.println("Column " + i + ": " + model.getColumn(i).getPreferredWidth());
		
	}
	
}
