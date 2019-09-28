package com.phill.libs;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/** Adiciona a funcionalidade de abrir o menu de popup de
 *  uma linha da JTable com o botão direito do mouse */
public class TableMouseListener extends MouseAdapter {
     
	private final JTable table;
	
	public TableMouseListener(JTable table) {
		this.table = table;
	}
	
    @Override
    public void mouseReleased(MouseEvent event) {
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        table.setRowSelectionInterval(currentRow, currentRow);
    }
}
