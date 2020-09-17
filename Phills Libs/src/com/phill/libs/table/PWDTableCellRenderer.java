package com.phill.libs.table;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;

/** This class helps implementing a {@link JPasswordField} cell in a {@link JTable}.
 *  NOTE: this class was copied from the internet, but I forgot to reference it :(
 *  so it has no author.
 *  @version 1.5, 17/SEP/2020 */
public class PWDTableCellRenderer extends JPasswordField implements TableCellRenderer, Serializable {

	// Serial
	private static final long serialVersionUID = -6469029866408222324L;

	protected Border noFocusBorder;

	private Color unselectedForeground;
	private Color unselectedBackground;

	public PWDTableCellRenderer() {
		super();
		
		this.noFocusBorder = new EmptyBorder(1, 2, 1, 2);
		
		setOpaque(true);
		setBorder(noFocusBorder);
		setBorder(BorderFactory.createEmptyBorder());
	}

	@Override
	public void setForeground(Color color) {
		super.setForeground(color);
		unselectedForeground = color;
	}

	@Override
	public void setBackground(Color color) {
		super.setBackground(color);
		unselectedBackground = color;
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setForeground(null);
		setBackground(null);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		if (isSelected) {
			super.setForeground(table.getSelectionForeground());
			super.setBackground(table.getSelectionBackground());
		} else {
			super.setForeground((unselectedForeground != null) ? unselectedForeground : table.getForeground());
			super.setBackground((unselectedBackground != null) ? unselectedBackground : table.getBackground());
		}
		
		setFont(table.getFont());
		
		if (hasFocus) {
			
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
			
			if (table.isCellEditable(row, column)) {
				super.setForeground(UIManager.getColor("Table.focusCellForeground"));
				super.setBackground(UIManager.getColor("Table.focusCellBackground"));
			}
		} else
			setBorder(noFocusBorder);
		
		setValue(value);
		
		return this;
	}

	/** Sets a value in this component only if the object is not null.
	 *  @param value - object value */
	protected void setValue(Object value) {
		setText((value == null) ? "" : value.toString());
	}

}
