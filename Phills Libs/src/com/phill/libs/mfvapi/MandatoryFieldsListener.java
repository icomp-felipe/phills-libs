package com.phill.libs.mfvapi;

import java.util.*;
import javax.swing.*;

import com.phill.libs.ui.AlertDialog;

public class MandatoryFieldsListener {

	private final ArrayList<JLabel> components;
	private final ArrayList<JLabel> essentials;
	private final StringBuilder error_list;
	
	public MandatoryFieldsListener() {
		this.components = new ArrayList<JLabel>();
		this.essentials = new ArrayList<JLabel>();
		this.error_list = new StringBuilder();
	}

	public void append(JLabel component, String result) {
		
		if (!components.contains(component)) {
			
			error_list.append("* " + result + "\n");
			components.add(component);
			
		}
		
	}
	
	public void addEssential(JLabel component) {
		
		if (!essentials.contains(component))
			essentials.add(component);
		
	}
	
	public void removeEssential(JLabel component) {
		
		essentials.remove(component);
		
	}
	
	public String getErrorString() {
		
		String error_string = error_list.toString().trim();
		
		return (error_string.isEmpty()) ? null : error_string;
	}
	
	public void showError() {

		String error_string = getErrorString();
		
		if (error_string != null)
			AlertDialog.error("Campos Obrigatórios",error_string);
		
	}
	
	public ArrayList<JLabel> getErrorComponents() {
		return components;
	}
	
	public boolean hasErrors() {
		return components.size() > 0;
	}
	
	public boolean hasEssentialErrors() {
		
		for (JLabel label: components)
			if (essentials.contains(label))
				return true;
		
		return false;
	}
	
	public void clear() {
		components.clear();
		error_list.setLength(0);
	}
	
}
