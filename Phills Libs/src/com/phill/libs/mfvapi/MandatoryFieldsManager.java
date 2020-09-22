package com.phill.libs;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import com.phill.libs.ui.AlertDialog;

public class MandatoryFieldsManager {

	private ArrayList<MandatoryField> permanents;
	private ArrayList<MandatoryField> conditionals;
	
	public MandatoryFieldsManager() {
		
		this.permanents   = new ArrayList<MandatoryField>();
		this.conditionals = new ArrayList<MandatoryField>();
		
	}
	
	public void addConditional(JLabel label, MandatoryFieldValidator validator, String error_string, boolean start_enabled) {
		
		MandatoryField repeated = find(label,conditionals);
		MandatoryField mf = new MandatoryField(label,validator,error_string,start_enabled,repeated);
		
		this.conditionals.add(mf);
		
	}
	
	public void addPermanent(JLabel label, MandatoryFieldValidator validator, String error_string) {
		
		MandatoryField repeated = find(label,permanents);
		MandatoryField mf = new MandatoryField(label,validator,error_string,true,repeated);
		
		this.permanents.add(mf);
		
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
			listener.append(mf.getComponent(),result);
		
	}
	
	
	@Deprecated
	public void validate(boolean nothing) {
		
		StringBuilder error_list = new StringBuilder();
		
		for (MandatoryField mf: permanents)
			parse(mf,error_list);
		
		for (MandatoryField mf: conditionals)
			if (mf.isEnabled())
				parse(mf,error_list);
		
		proccessResults(error_list);
		
	}
	
	@Deprecated
	private void proccessResults(StringBuilder error_list) {
		
		String errors = error_list.toString().trim();
		
		if (!errors.isEmpty())
			AlertDialog.error("Campos Obrigatórios",errors);
		
	}
	
	
	
	private class MandatoryField {
		
		private JLabel label;
		private MandatoryFieldValidator validator;
		private String error_string;
		private boolean enabled;
		private Color original_color;
		
		public MandatoryField(JLabel label, MandatoryFieldValidator validator, String error_string, boolean enabled, MandatoryField repeated) {
			
			this.label = label;
			this.validator = validator;
			this.error_string = error_string;
			this.original_color = label.getForeground();
			this.enabled = enabled;
			
			setMandatory(enabled,repeated);
			
		}
		
		public JLabel getComponent() {
			return this.label;
		}

		public String validate() {
			
			boolean status = validator.validate();
			
			if (status)
				setNormal();
			else
				setError();
			
			return (status) ? null : error_string;
		}

		private void setMandatory(boolean enabled, MandatoryField repeated) {
			
			if (repeated != null)
				if (enabled)
					repeated.enable();
				else
					repeated.disable();
			else if (enabled)
				setMandatory();
			
		}
		
		private void setMandatory() {
			
			String new_text = "* " + label.getText();
			label.setText(new_text);
			
		}
		
		private void unsetMandatory() {
			
			String new_text = label.getText().substring(2);
			label.setText(new_text);
			setNormal();
			
		}
		
		public boolean isEnabled() {
			return this.enabled;
		}
		
		public boolean equals(JLabel label) {
			return this.label == label;
		}
		
		public void enable() {
			
			if (!isEnabled()) {
				setMandatory();
				this.enabled = true;
			}
			
		}
		
		public void disable() {
			
			if (isEnabled()) {
				unsetMandatory();
				this.enabled = false;
			}
			
		}
		
		private void setNormal() {
			label.setForeground(original_color);
		}
		
		private void setError() {
			label.setForeground(Color.RED);
		}
		
	}
	
}
