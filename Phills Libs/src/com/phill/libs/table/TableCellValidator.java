package com.phill.libs.table;

/** Contains a method to validate a JTable cell data.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 26/APR/2021 */
@FunctionalInterface
public interface TableCellValidator {
	
	/** Validates a cell data.
	 *  @param cellData - data filled by DefaultTableCellRenderer::getTableCellRendererComponent which represents data from an unique JTable cell
	 *  @return A validation status, defined by the implementer. */
	public boolean validate(final Object cellData);

}
