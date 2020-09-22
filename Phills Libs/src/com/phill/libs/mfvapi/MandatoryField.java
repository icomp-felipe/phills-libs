package com.phill.libs.mfvapi;

import java.awt.Color;
import javax.swing.JLabel;

/** Implements the actual mandatory field, which consists by an internal {@link JLabel} related to the validation field,
 *  a {@link MandatoryFieldValidator} validator who tells if this instance is satisfied or not, an error string returned
 *  by this class {@link #validate()} method, a validation activation control attribute (boolean) and the original label
 *  {@link Color}.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.0, 22/SEP/2020 */
public class MandatoryField {
	
	// Constants
	private final JLabel label;
	private final MandatoryFieldValidator validator;
	private final String error_string;
	private final Color original_color;
	
	// Mandatory field activation status
	private boolean enabled;
	
	/** Constructor setting parameters.
	 *  @see MandatoryField */
	protected MandatoryField(final JLabel label, final MandatoryFieldValidator validator, final String error_string, final boolean enabled) {
		
		this.label = label;
		this.validator = validator;
		this.error_string = error_string;
		this.original_color = label.getForeground();
		
		this.enabled = enabled;
		
		if (enabled)
			setMandatory();
		
	}
	
	/******************************* Class Control Methods Section *************************************/
	
	/** Turns this field mandatory by appending an asterisk
	 *  character to the beginning of the given label text.<br>
	 *  Note: if the label text already begins with the '* ' pattern this method does nothing. */
	private void setMandatory() {

		final String labelText = this.label.getText();

		// Little treatment to avoid asterisk mess
		if (!labelText.matches("\\* .*"))
			this.label.setText("* " + labelText);
		
	}
	
	/** Unturns this field mandatory by removing the asterisk
	 *  character put by {@link #setMandatory()}. It also reverses
	 *  the internal label foreground color to its original one.<br>
	 *  Note: if the label text does not matches the '* ' pattern
	 *  this method only resets the label color. */
	private void unsetMandatory() {
		
		final String labelText = this.label.getText();
		
		// Little treatment to avoid asterisk mess
		if (labelText.matches("\\* .*"))
			this.label.setText(labelText.substring(2));
		
		setNormal();
		
	}

	/** Enables the validation capability for this field. If it's not already enabled, the internal
	 *  label text is marked with an asterisk, and this class marked as able to validate. */
	protected void enable() {
		
		if (!isEnabled()) {
			setMandatory();
			this.enabled = true;
		}
		
	}
	
	/** Disables the validation capability for this field. If it's not already disabled, the asterisk from
	 *  the internal label text is removed, and this class marked as unable to validate. */
	protected void disable() {
		
		if (isEnabled()) {
			unsetMandatory();
			this.enabled = false;
		}
		
	}
	
	/** Runs the validation algorithm designed for this instance and paints the internal {@link JLabel} according to this validation.
	 *  @return The internal <code>error_string</code> if this instance it's not satisfied
	 *  ({@link MandatoryFieldValidator#validate()} has returned false) or <code>null</code> otherwise. */
	protected String validate() {
		
		boolean valid = this.validator.validate();
		
		if (valid)
			setNormal();
		else
			setError();
		
		return (valid) ? null : this.error_string;
	}
	
	/******************************* Getters and Setters Section ****************************************/
	
	/** One instance is equals to another if their internal labels are the same (have the same instance).
	 *  @return 'true' if both internal and given labels have the same instance, or 'false' otherwise. */
	protected boolean equals(final JLabel label) {
		return this.label == label;
	}
	
	/** Retrieves the internal {@link JLabel}.
	 *  @return The internal label. */
	protected JLabel getLabel() {
		return this.label;
	}

	/** Tells if the current instance is enabled for validation.
	 *  @return Validation status for this instance. */
	protected boolean isEnabled() {
		return this.enabled;
	}
	
	/** Paints the internal label with a red color. */
	private void setError() {
		this.label.setForeground(Color.RED);
	}
	
	/** Paints the internal label with its original color. */
	private void setNormal() {
		this.label.setForeground(this.original_color);
	}
	
}