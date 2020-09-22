package com.phill.libs.mfvapi;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/** Implements a quick (but functional) mandatory field manager.  */
public class MandatoryFieldsManager {

	// Mandatory field lists
	private final ArrayList<MandatoryField> permanents;
	private final ArrayList<MandatoryField> conditionals;
	
	/** Main constructor initiating internal lists. */
	public MandatoryFieldsManager() {
		
		this.permanents   = new ArrayList<MandatoryField>();
		this.conditionals = new ArrayList<MandatoryField>();
		
	}
	
	public void addConditional(JLabel label, MandatoryFieldValidator validator, String error_string, boolean start_enabled) {
		
		MandatoryField repeated = find(label,conditionals);
		MandatoryField novo = new MandatoryField(label,validator,error_string,start_enabled);
		
		if (repeated == null)
			this.conditionals.add( novo );
		else {
			removeConditional(label);
			this.conditionals.add   ( novo );
;		}
		
		
		
	}
	
	/** Registers a permanent mandatory field.
	 *  @param label - label to display the mandatory field alert
	 *  @param validator - validator callback method. It is used to validate and determine if this field is satisfied or not
	 *  @param errorString - string to be displayed when this field is violated */
	public void addPermanent(final JLabel label, final MandatoryFieldValidator validator, final String errorString) {
		
		MandatoryField repeated = find(label, this.permanents);
		MandatoryField novo       = new MandatoryField(label, validator, errorString, true);
		
		if (repeated == null)
			this.permanents.add( novo );
		else {
			removePermanent(label);
			this.permanents.add   ( novo );
;		}
		
	}
	
	private MandatoryField find(JLabel label, ArrayList<MandatoryField> list) {
		
		for (MandatoryField mf: list)
			if (mf.equals(label))
				return mf;
		
		return null;
	}
	
	public void removeConditional(JLabel label) {
		removeMandatoryField(label,conditionals);
	}
	
	public void removePermanent(JLabel label) {
		removeMandatoryField(label,permanents);
	}
	
	public void enableConditionals() {
		
		for (MandatoryField mf: conditionals)
			mf.enable();
		
	}
	
	public void disableConditionals() {
		
		for (MandatoryField mf: conditionals)
			mf.disable();
		
	}
	
	private void removeMandatoryField(JLabel label, ArrayList<MandatoryField> list) {
		
		for (MandatoryField mf: list) {
			
			if (mf.equals(label)) {
				
				mf.disable();
				list.remove(mf);
				return;
				
			}
			
		}
			
		
	}
	
	public String validate() {
		
		StringBuilder error_list = new StringBuilder();
		
		for (MandatoryField mf: permanents)
			parse(mf,error_list);
		
		for (MandatoryField mf: conditionals)
			if (mf.isEnabled())
				parse(mf,error_list);
		
		return error_list.toString().trim();
		
	}
	
	private void parse(MandatoryField mf, StringBuilder error_list) {
		
		String result = mf.validate();
		
		if (result != null)
			error_list.append("* " + result + "\n");
		
	}
	
	
	
	public void validate(MandatoryFieldsListener listener) {
		
		for (MandatoryField mf: permanents)
			parse(mf,listener);
		
		for (MandatoryField mf: conditionals)
			if (mf.isEnabled())
				parse(mf,listener);
		
	}
	
	private void parse(MandatoryField mf, MandatoryFieldsListener listener) {
		
		String result = mf.validate();
		
		if (result != null)
			listener.append(mf.getLabel(),result);
		
	}
	
}
