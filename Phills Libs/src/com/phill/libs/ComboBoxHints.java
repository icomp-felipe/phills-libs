package com.phill.libs;

import java.awt.Component;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

public class ComboBoxHints extends BasicComboBoxRenderer {

	private final List<String> hints_list;
	private static final long serialVersionUID = 1L;

	public ComboBoxHints(List<String> hints_list) {
		this.hints_list = hints_list;
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
		
		JComponent aux = (JComponent) super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);
		
		System.out.println(hints_list.size());
		
		if (-1 < index && null != value && null != hints_list)
            list.setToolTipText(hints_list.get(index));
		
        return aux;
		
		/*
		if (isSelected) {
			
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			
			if (index > -1)
				list.setToolTipText(hints_list.get(index));
		}
		else {
			
			setBackground(list.getBackground());
			setForeground(list.getForeground());
			
		}
		
		setFont(list.getFont());
		setText((value == null) ? "" : value.toString());
		**/
	}
	
}
