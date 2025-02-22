package com.phill.libs.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

/** Custom implementation of a {@link JProgressBar}.
 *  Paints the component foreground according to the given colors and current value.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.5, 22/MAY/2021 */
public class ProgressBarColor extends JProgressBar {

	// Serial
	private static final long serialVersionUID = 4998115482851752147L;
	
	// Foreground colors
	private final Color plain, complete, overflow;
	
	/** Main constructor setting the foreground colors.
	 *  @param plain - this will be used when the value is between 0 and 99
	 *  @param complete - color used when value is 100
	 *  @param overflow - color used when value is over 100 */
	public ProgressBarColor(final Color plain, final Color complete, final Color overflow) {
		
		this.plain = plain;
		this.complete = complete;
		this.overflow = overflow;
		
		setUI(new BasicProgressBarUI() {
			protected Color getSelectionBackground() { return Color.BLACK; }
			protected Color getSelectionForeground() { return Color.BLACK; }
		});
		
	}
	
	/** Paints the foreground according to the given <code>value</code>. */
	@Override
	public void setValue(int value) {
		
		if (value < 100)
			setForeground(plain);
		
		else if (value > 100)
			setForeground(overflow);
		else
			
			setForeground(complete);
		
		super.setValue(value);
	}
	
}