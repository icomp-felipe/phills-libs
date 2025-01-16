package com.phill.libs.ui;

import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/** Implements an email validation algorithm in a {@link JTextField} to allow
 *  the user knowing if the typed email is valid in realtime. The foreground
 *  color of this field is automatically changed to 'green' when its email is
 *  valid, and 'white' otherwise.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 17/SET/2020
 *  @see JTextField  */
public class JEmailField extends JTextField {

	// Serial
	private static final long serialVersionUID = -935514959875047838L;

	// Custom color
	private final Color gr_lt = new Color(0x84efa5);

	// e-mail validation pattern, following the RFC822 specification
	private final String email_pattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private final Pattern pattern;
	
	/** Main constructor building the component and applying the validation algorithm listener. */
	public JEmailField() {
		this.pattern = Pattern.compile(email_pattern,Pattern.CASE_INSENSITIVE);
		this.addKeyListener((KeyReleasedListener) (_) -> parse());
	}

	/** Does the validation and updates the UI. */
	private synchronized boolean parse() {
		
		boolean matches = pattern.matcher(getText()).matches();
		
		if (matches)
			setBackground(gr_lt);
		else
			setBackground(Color.WHITE);
		
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
		return pattern.matcher(getText()).matches();
	}
	
}
