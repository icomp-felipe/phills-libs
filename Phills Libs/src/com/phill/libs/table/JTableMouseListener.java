package com.phill.libs.table;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

/** This class is useful when you need to open a popup menu
 *  using the mouse right click in a specific {@link JTable} row.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.5, 17/SEP/2020 */
public class JTableMouseListener extends MouseAdapter {
	
	private final JTable table;
	
	public JTableMouseListener(JTable table) {
		this.table = table;
	}
	
	/** Selects the nearest {@link JTable} row pointed by the last mouse click event.
	 *  If the algorithm can't resolve the nearest row, it simply ignores the mouse event. */
    @Override
    public void mouseReleased(MouseEvent event) {
    	
        Point point = event.getPoint();
        int currentRow = table.rowAtPoint(point);
        
        if (currentRow != -1)
        	table.setRowSelectionInterval(currentRow, currentRow);
        
    }
    
}
