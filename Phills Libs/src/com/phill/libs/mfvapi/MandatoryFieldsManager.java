package com.phill.libs.mfvapi;

import java.util.*;
import javax.swing.*;

/** Implements a quick (but functional) mandatory fields manager for Java Swing applications.
 *  It basically behaves by running a custom {@link MandatoryFieldValidator#validate()} method in the field you
 *  want to validate and displaying the result of this validation in the related label. The label marked as enabled (using
 *  {@link MandatoryFieldsManager#enableConditionals()} and {@link MandatoryFieldsManager#disableConditionals()} methods)
 *  has an asterisk + single space (* ) appended at the beginning of the label text (only if it doesn't already contain it). Fields registered using
 *  {@link MandatoryFieldsManager#addPermanent(JLabel, MandatoryFieldValidator, String)} method are automatically marked as enabled.
 *  When it comes to validation, the {@link MandatoryFieldsManager#validate()} and {@link MandatoryFieldsManager#validate(MandatoryFieldsLogger)}
 *  methods can be used to do the actual validation of fields. Both methods iterate over the permanents and conditionals registered fields (coming
 *  from {@link MandatoryFieldsManager#addConditional(JLabel, MandatoryFieldValidator, String, boolean)} and {@link MandatoryFieldsManager#addPermanent(JLabel, MandatoryFieldValidator, String)}
 *  methods) and paint the related labels with a red color or initial label color when the validation method returns false or true, respectively. 
 *  Alternatively you may want to use a {@link MandatoryFieldsLogger} to get the error strings (already given) thrown when the validation method
 *  returns false or even the internal {@link MandatoryFieldsManager#validate()} method.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 2.1, 11/APR/2021 */
public class MandatoryFieldsManager {

	// Mandatory field lists
	private final ArrayList<MandatoryField> permanents;
	private final ArrayList<MandatoryField> conditionals;
	
	/** Main constructor initiating internal lists. */
	public MandatoryFieldsManager() {
		
		this.permanents   = new ArrayList<MandatoryField>();
		this.conditionals = new ArrayList<MandatoryField>();
		
	}
	
	/******************************* User Interface Methods Section ************************************/
	
	/** Registers a conditional mandatory field (can be enabled or disabled). If this same field has been already registered, this method first
	 *  unregisters it and then registers again, in other words, if the same field is registered more than once, only the last one will take effect. 
	 *  @param label - label to display the mandatory field alert (asterisk)
	 *  @param validator - validator callback method. It is used to validate and determine if this field is satisfied or not
	 *  @param errorString - string to be returned by {@link #validate()} when this field is unsatisfied
	 *  @param startEnabled - defines the initial validation capability status
	 *  @param putAsterisk - puts (or not) an asterisk character at the beginning of the label text */
	public void addConditional(final JLabel label, final MandatoryFieldValidator validator, final String errorString, final boolean startEnabled, final boolean putAsterisk) {
		
		MandatoryField repeated  = find(label,conditionals);
		MandatoryField mandField = new MandatoryField(label, validator, errorString, startEnabled, putAsterisk);
		
		if (repeated == null)
			this.conditionals.add( mandField );
		else {
			removeConditional(label);
			this.conditionals.add( mandField );
		}
		
	}
	
	/** Registers a permanent mandatory field (remains mandatory forever). If this same field has been already registered, this method first
	 *  unregisters it and then registers again, in other words, if the same field is registered more than once, only the last one will take effect. 
	 *  @param label - label to display the mandatory field alert (asterisk)
	 *  @param validator - validator callback method. It is used to validate and determine if this field is satisfied or not
	 *  @param errorString - string to be returned by {@link #validate()} when this field is unsatisfied
	 *  @param putAsterisk - puts (or not) an asterisk character at the beginning of the label text */
	public void addPermanent(final JLabel label, final MandatoryFieldValidator validator, final String errorString, final boolean putAsterisk) {
		
		MandatoryField repeated  = find(label, this.permanents);
		MandatoryField mandField = new MandatoryField(label, validator, errorString, true, putAsterisk);
		
		if (repeated == null)
			this.permanents.add( mandField );
		
		else {
			removePermanent(label);
			this.permanents.add( mandField );
		}
		
	}
	
	/** Registers a permanent mandatory field (remains mandatory forever). If this same field has been already registered, this method first
	 *  unregisters it and then registers again, in other words, if the same field is registered more than once, only the last one will take effect. 
	 *  @param label - label to display the mandatory field alert (asterisk)
	 *  @param validator - validator callback method. It is used to validate and determine if this field is satisfied or not
	 *  @param errorStringFactory - a method interface that generates a dynamic String when this field is unsatisfied
	 *  @param putAsterisk - puts (or not) an asterisk character at the beginning of the label text */
	public void addPermanent(final JLabel label, final MandatoryFieldValidator validator, final MandatoryFieldString errorStringFactory, final boolean putAsterisk) {
		
		MandatoryField repeated  = find(label, this.permanents);
		MandatoryField mandField = new MandatoryField(label, validator, errorStringFactory, true, putAsterisk);
		
		if (repeated == null)
			this.permanents.add( mandField );
		
		else {
			removePermanent(label);
			this.permanents.add( mandField );
		}
		
	}
	
	/** Unregisters the given <code>label</code> by removing the asterisk from the
	 *  beginning of the label text and restoring its original foreground color.
	 *  @param label - label (related to the validation field) to be unregistered */
	public void removeConditional(final JLabel label) {
		removeMandatoryField(label,conditionals);
	}
	
	/** Unregisters the given <code>label</code> by removing the asterisk from the
	 *  beginning of the label text and restoring its original foreground color.
	 *  @param label - label (related to the validation field) to be unregistered */
	public void removePermanent(final JLabel label) {
		removeMandatoryField(label,permanents);
	}
	
	/** Tries to find a previous registered <code>label</code> in the given <code>list</code>.
	 *  @param label - label to be registered
	 *  @param list - internal registered field list */
	private MandatoryField find(final JLabel label, final ArrayList<MandatoryField> list) {
		
		for (MandatoryField field: list)
			if (field.getLabel().equals(label))
				return field;
		
		return null;
	}
	
	/** Enables all conditional fields to be validated. */
	public void enableConditionals() {
		
		for (MandatoryField field: this.conditionals)
			field.enable();
		
	}
	
	/** Disables all conditional fields to be validated. */
	public void disableConditionals() {
		
		for (MandatoryField field: this.conditionals)
			field.disable();
		
	}
	
	/** Iterates over the permanent and conditional internal lists (respectively), calling the pre-registered
	 *  {@link MandatoryField#validate()} method from each {@link MandatoryField}. The results are stored in
	 *  the given <code>logger</code> and can be retrieved by the {@link MandatoryFieldsLogger#getErrorString()} method.
	 *  @param logger - logger to store this validation results */
	public void validate(final MandatoryFieldsLogger logger) {
		
		for (MandatoryField field: this.permanents)
			parse(field,logger);
		
		for (MandatoryField field: this.conditionals)
			if (field.isEnabled())
				parse(field,logger);
		
	}
	
	/** Iterates over the permanent and conditional internal lists (respectively), calling the pre-registered
	 *  {@link MandatoryField#validate()} method from each {@link MandatoryField}. The results are stored in
	 *  the string returned by this method. */
	public String validate() {
		
		StringBuilder errorList = new StringBuilder();
		
		for (MandatoryField field: this.permanents)
			parse(field,errorList);
		
		for (MandatoryField field: this.conditionals)
			if (field.isEnabled())
				parse(field,errorList);
		
		return errorList.toString().trim();
		
	}
	
	/******************************* Class Control Methods Section *************************************/
	
	/** Unregisters the given <code>label</code> from the <code>list</code> an then removes the asterisk
	 *  from the beginning of the label text and restores its original foreground color. */
	private void removeMandatoryField(final JLabel label, final ArrayList<MandatoryField> list) {
		
		for (MandatoryField field: list) {
			
			if (field.getLabel().equals(label)) {
				
				field.disable();
				list.remove(field);
				return;
				
			}
		}
	}
	
	/** Runs the given <code>field::validate</code> method and stores its results in
	 *  the given <code>logger</code>.
	 *  @param field - field to be validated
	 *  @param logger - a {@link MandatoryFieldsLogger} to store the results of the validation method */
	private void parse(final MandatoryField field, final MandatoryFieldsLogger logger) {
		
		String result = field.validate();
		
		if (result != null)
			logger.registerViolation(field.getLabel(),result);
		
	}
	
	/** Runs the given <code>field::validate</code> method and stores its results in
	 *  the given <code>errorList</code> builder.
	 *  @param field - field to be validated
	 *  @param errorList - a {@link StringBuilder} to store the results of the validation method */
	private void parse(final MandatoryField field, final StringBuilder errorList) {
		
		String result = field.validate();
		
		if (result != null)
			errorList.append("* " + result + "\n");
		
	}
	
}
