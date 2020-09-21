package com.phill.libs.sys;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.net.URL;

/** Contains methods to deal with host OS based features.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.0, 20/SEP/2020 */
public class HostUtils {

	/** Recovers the system current screen resolution width.
	 *  @return System screen width in pixels. */
	public static int getScreenWidth() {
	    return Toolkit.getDefaultToolkit().getScreenSize().width;
	}
	
	/** Opens a URL using host's default web browser.
	 *  @param url - URL */
	public static void openURL(final String url) {
		
		try { Desktop.getDesktop().browse(new URL(url).toURI()); }
		catch (Exception exception) { }
		
	}
	
}
