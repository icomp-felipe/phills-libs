package com.phill.libs.mfvapi;

import java.util.*;
import javax.swing.*;

/** Implements a simple logger to the {@link MandatoryFieldsManager}.
 *  Basically you may want to keep track of several field validations avoiding
 *  creating and manipulating strings to every single validation method.
 *  In fact, you can have as many {@link MandatoryFieldsManager}s as you may need, each one
 *  of them containing lots os fields to be validated, but you only need one instance of this
 *  logger and pass them to your managers.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 2.0, 22/SEP/2020 */
public class MandatoryFieldsLogger {

	/** Unsatisfied fields labels */
	private final ArrayList<JLabel> unsatisfields;
	
	/** Essential fields - user cannot trespass them */
	private final ArrayList<JLabel> essentials;
	
	/** Internal error list */
	private final StringBuilder error_list;
	
	/** Constructor allocating resources. */
	public MandatoryFieldsLogger() {
		
		this.unsatisfields = new ArrayList<JLabel>();
		this.essentials    = new ArrayList<JLabel>();
		this.error_list    = new StringBuilder();
		
	}
	
	/******************************* Class Control Methods Section *************************************/
	
	/** Registers an essential field (user cannot continue without satisfying it).
	 *  @param label - label related to the validation field */
	public void addEssential(final JLabel label) {
		
		if (!this.essentials.contains(label))
			 this.essentials.add(label);
		
	}
	
	/** Umregisters an essential field.
	 *  @param label - label related to the validation field */
	public void removeEssential(JLabel label) {
		
		this.essentials.remove(label);
		
	}

	/** Appends the <code>validationResult</code> and its related <code>label</code>
	 *  to the internal unsatisfied fields list and error string (only if it hasn't been already appended).
	 *  @param label - label related to the validation field
	 *  @param validationResult - returned by {@link MandatoryField#validate()} */
	protected void registerViolation(final JLabel label, final String validationResult) {
		
		if (!this.unsatisfields.contains(label)) {
			
			this.error_list.append("* " + validationResult + "\n");
			this.unsatisfields.add(label);
			
		}
		
	}
	
	/** Resets the internal unsatisfied fields list, originally coming from {@link #registerViolation(JLabel, String)}. */
	public void clear() {
		
		this.unsatisfields.clear();
		this.error_list.setLength(0);
		
	}
	
	/********************************* Useful Getters Section *******************************************/
	
	/** Retrieves the final validation error string, composed by every {@link MandatoryField#validate()} return.
	 *  @return All validation error strings merged into a single one, or 'null' if no validation errors were found. */
	public String getErrorString() {
		
		String error_string = error_list.toString().trim();
		
		return (error_string.isEmpty()) ? null : error_string;
	}
	
	/** Retrieves the registered unsatisfied fields list.
	 *  @return A list composed by all registered unsatisfied fields. */
	public ArrayList<JLabel> getUnsatisfiedFields() {
		return this.unsatisfields;
	}
	
	/** Tells if there's any unsatisfied field in the pre-registered list.
	 *  @return 'true' if any of the pre-registered fields has not passed the validation algorithm, or 'false' otherwise. */
	public boolean hasErrors() {
		return this.unsatisfields.size() > 0;
	}
	
	/** @return The amount of errors found during the validation process. */
	public int getErrorsCount() {
		return this.unsatisfields.size();
	}
	
	/** Tells if there's any essential unsatisfied field in the pre-registered list.
	 *  @return 'true' if any of the essential pre-registered fields has not passed the validation algorithm, or 'false' otherwise. */
	public boolean hasEssentialErrors() {
		
		for (JLabel label: this.unsatisfields)
			if (this.essentials.contains(label))
				return true;
		
		return false;
	}
	
}
