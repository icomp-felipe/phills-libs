package com.phill.libs.sys;

import java.awt.Desktop;
import java.net.URL;

/** Contains methods to deal with host OS based features.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 17/SEP/2020 */
public class HostUtils {

	/** Opens a URL using host's default web browser.
	 *  @param url - URL */
	public static void openURL(final String url) {
		
		try { Desktop.getDesktop().browse(new URL(url).toURI()); }
		catch (Exception exception) { }
		
	}
	
}
