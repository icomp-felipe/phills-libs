package com.phill.libs;

import java.awt.*;
import javax.swing.*;

public class ProgressBarColor extends JProgressBar {

	private static final long serialVersionUID = 1L;
	
	private static final Color OVERFLOW   = new Color(0xf98487);
	private static final Color INCOMPLETE = new Color(0xf9fb64);
	private static final Color COMPLETE   = new Color(0x83fb94);
	
	public ProgressBarColor() {
		setPreferredSize(new Dimension(80,15));
	}
	
	@Override
	public void setValue(int value) {
		
		if (value < 100)
			setForeground(INCOMPLETE);
		else if (value > 100)
			setForeground(OVERFLOW);
		else
			setForeground(COMPLETE);
		
		super.setValue(value);
	}
	
}
