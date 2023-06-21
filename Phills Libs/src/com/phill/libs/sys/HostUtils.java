package com.phill.libs.sys;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/** Contains methods to deal with host OS based features.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 2.1, 03/MAY/2021 */
public class HostUtils {

	/** Recovers the system current screen resolution width.
	 *  @return System screen width in pixels. */
	public static int getScreenWidth() {
	    return Toolkit.getDefaultToolkit().getScreenSize().width;
	}
	
	/** Opens a URL using host's default web browser.
	 *  @param url - URL */
	public static void openURL(final String url) {
		
		try { Desktop.getDesktop().browse(new URI(url)); }
		catch (Exception exception) { }
		
	}
	
	/** Starts a chat using WhatsApp Link API.
	 *  @param number - complete cellphone number, with country and region codes, numbers only
	 *  @param message - a message to be sent
	 *  @since 2.1, 03/MAY/2021 */
	public static void sendWhatsApp(final String number, final String message) {
		
		if (number != null && !number.isBlank()) {
			
			try {
				
				final String encodedMessage = URLEncoder.encode(message == null ? "" : message, StandardCharsets.UTF_8);
				final String link = String.format("https://api.whatsapp.com/send?phone=%s&text=%s", number, encodedMessage);
				
				Desktop.getDesktop().browse(new URI(link));
				
			}
			catch (Exception exception) { }
			
		}
		
	}

	public static void sendEmail(final String email) {
		
		if (email != null && !email.isBlank()) {
			
			try {
				
				final String link = String.format("mailto:%s", email);
				
				Desktop.getDesktop().mail(URI.create(link));
				
			}
			catch (Exception exception) { exception.printStackTrace();  }
			
		}
		
	}
	
}
