package com.phill.libs;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

public class PasswordFieldTableCellRenderer extends JPasswordField implements TableCellRenderer, Serializable{

	private static final long serialVersionUID = 1L;

	protected Border noFocusBorder;

	private Color unselectedForeground;
	private Color unselectedBackground;

	public PasswordFieldTableCellRenderer() {
	super();
	noFocusBorder = new EmptyBorder(1, 2, 1, 2);
	setOpaque(true);
	setBorder(noFocusBorder);
	this.setBorder( BorderFactory.createEmptyBorder());
	}

	public void setForeground(Color c) {
	super.setForeground(c);
	unselectedForeground = c;
	}

	public void setBackground(Color c) {
	super.setBackground(c);
	unselectedBackground = c;
	}

	public void updateUI() {
	super.updateUI();
	setForeground(null);
	setBackground(null);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
	boolean isSelected, boolean hasFocus, int row, int column) {

	if (isSelected) {
	super.setForeground(table.getSelectionForeground());
	super.setBackground(table.getSelectionBackground());
	}else {
	super.setForeground((unselectedForeground != null) ? unselectedForeground : table.getForeground());
	super.setBackground((unselectedBackground != null) ? unselectedBackground : table.getBackground());
	}

	setFont(table.getFont());

	if (hasFocus) {
	setBorder( UIManager.getBorder("Table.focusCellHighlightBorder") );
	if (table.isCellEditable(row, column)) {
	super.setForeground( UIManager.getColor("Table.focusCellForeground") );
	super.setBackground( UIManager.getColor("Table.focusCellBackground") );
	}
	}else {
	setBorder(noFocusBorder);
	}

	setValue(value);

	return this;
	}

	protected void setValue(Object value) {
	setText((value == null) ? "" : value.toString());
	}

}
