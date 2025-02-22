package com.phill.libs.sys;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/** Contains some methods to manipulate the system clipboard.
 *  @author Felipe André - felipeandre.eng@gmail.com
 *  @version 1.0, 23/JUN/2020 */
public class ClipboardUtils {
	
	/** Paste a given 'text' to the system clipboard.
	 *  @param text - Custom text to be written at system clipboard
	 *  @see Clipboard */
	public static void paste(String text) {
		
        final Clipboard       clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        final StringSelection selection = new StringSelection(text);
        
        clipboard.setContents(selection, null);
        
    }
	
	/** Copy a text from the system clipboard.
	 *  @return A {@link String} containing the system cliboard text. */
	public static String copy() {
		
		String         text   = "";
		Clipboard clipboard   = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable contents = clipboard.getContents(null);
		
		boolean hasTransferableText = (contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
		
		if (hasTransferableText) {
		   	try { text = (String) contents.getTransferData(DataFlavor.stringFlavor); }
		   	catch (UnsupportedFlavorException | IOException exception) { }
		}
		
	    return text;
	}

}
