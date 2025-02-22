package com.phill.libs.ui;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/** Implements an {@link MaskFormatter} to be used in pair with a {@link JFormattedTextField}.
 *  It behaves similarly to a default {@link MaskFormatter}, but the internal 'value' parameter
 *  is set to 'null' everytime the text field has invalid data (that does not match the mask).
 *  It also ignores the 'focusLostBehavior' constants from {@link JFormattedTextField}.<br>
 *  Note 1: to keep data integrity, always set your text using the {@link JFormattedTextField#setText(String)} method
 *  instead of setValue method, and get using the {@link JFormattedTextField#getValue()} method;<br>
 *  Note 2: this formatter always returns a String when using the {@link JFormattedTextField#getValue()} method.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 12/SEP/2021 */
public class ErasableMaskFormatter extends MaskFormatter {
	
	private static final long serialVersionUID = 8102845975669763153L;

	public ErasableMaskFormatter() {
		super.setCommitsOnValidEdit(true);
	}
	
	public ErasableMaskFormatter(final String mask) throws ParseException {
		super.setMask(mask);
		super.setCommitsOnValidEdit(true);
	}

	@Override
	public Object stringToValue(String string) throws ParseException {
		
		try {
			return super.stringToValue(string);
		}
		catch (ParseException exception) {
			return null;
		}
		
	}
	
}