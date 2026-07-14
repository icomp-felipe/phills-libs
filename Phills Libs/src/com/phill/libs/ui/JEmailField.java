package com.phill.libs.ui;

import java.awt.Color;
import javax.swing.JTextField;

import org.apache.commons.validator.routines.EmailValidator;

/** Implements an email validation algorithm in a {@link JTextField} to allow
 *  the user knowing if the typed email is valid in realtime. The foreground
 *  color of this field is automatically changed to 'green' when its email is
 *  valid, and 'white' otherwise.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.6, 14/JUL/2026
 *  @see JTextField  */
public class JEmailField extends JTextField {

	// Serial
	private static final long serialVersionUID = -935514959875047838L;

	// Custom color
	private final Color gr_lt = new Color(0x84efa5);

	{
		this.addKeyListener((KeyReleasedListener) (_) -> parse());
	}
	
	/** Does the validation and updates the UI. */
	private synchronized boolean parse() {
		
		boolean matches = this.isCoherent();
		
		setBackground(matches ? gr_lt : Color.WHITE);
		
		return matches;
	}

	/** Applies the given text and the validation algorithm.
	 *  @param string - string text */
	@Override
	public void setText(String string) {
		super.setText(string);	parse();
	}
	
	/** Tells if the internal text is a valid e-mail address.
	 *  @return 'true' if the internal data is a valid e-mail or 'false' otherwise */
	public boolean isCoherent() {
		return EmailValidator.getInstance().isValid(getText());
	}
	
}
