package com.phill.libs.table;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/** Contains a custom implementation of a {@link DefaultTableCellRenderer}, allowing to determine each of the
 *  individual JTable's cell background color, according to a {@link TableCellValidator#validate(Object)} method result.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 26/APR/2021
 *  @see TableCellValidator */
public class ValidatorCellRenderer extends DefaultTableCellRenderer {

	// Serial
	private static final long serialVersionUID = 706024344834355674L;
	
	// Validator and color attributes
	private final TableCellValidator validator;
	private final Color validColor, invalidColor;
	
	/** Main constructor only setting attributes.
	 *  @param validator - validator interface
	 *  @param validColor - background color to be applied to a cell if its content is valid. In other words, if ({@link TableCellValidator#validate(Object)} returned 'true')
	 *  @param invalidColor - background color to be applied to a cell if its content is invalid. In other words, if ({@link TableCellValidator#validate(Object)} returned 'false') */
	public ValidatorCellRenderer(final TableCellValidator validator, final Color validColor, final Color invalidColor) {
		
		this.validator    = validator;
		this.validColor   = validColor;
		this.invalidColor = invalidColor;
		
	}
	
	/** Applies the {@link TableCellValidator#validate(Object)} method in the given <code>value</code> to determine the current cell's background color.<br>
	 *  {@inheritDoc} */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		// Usually table cells are rendered as JLabels
		JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		// Determining which color to use according to the validator
		Color cellColor = validator.validate(value) ? validColor : invalidColor;

		// Applying cell backgound color
		cell.setBackground(cellColor);
		
		return cell;
	}

}