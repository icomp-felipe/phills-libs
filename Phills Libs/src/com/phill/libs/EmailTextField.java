package com.phill.libs;

import java.awt.Color;
import java.util.regex.Pattern;
import javax.swing.JTextField;

import com.phill.libs.ui.KeyReleasedListener;

/** 
 *  @author Felipe André
 *  @version 1.0, 25/09/2018
 *  @see JTextField  */
public class EmailTextField extends JTextField {

	private final Color gr_lt = new Color(0x84efa5);

	// Padrão de Reconhecimento de email, de acordo com a RFC822
	private final String email_pattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
	private final Pattern pattern;
	
	// final String email_regex = ".*@.*\\..*";
	
	private static final long serialVersionUID = 1L;
	
	public EmailTextField() {
		this.pattern = Pattern.compile(email_pattern,Pattern.CASE_INSENSITIVE);
		this.addKeyListener((KeyReleasedListener) (event) -> parse());
	}

	public synchronized boolean parse() {
		
		boolean matches = pattern.matcher(getText()).matches();
		
		if (matches)
			setBackground(gr_lt);
		else
			setBackground(Color.WHITE);
		
		return matches;
	}

	@Override
	/** Pinta de branco caso a 'string' seja vazia */
	public void setText(String string) {
		super.setText(string);	parse();
	}
	
}
