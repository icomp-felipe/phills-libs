package com.phill.libs;

import java.awt.Desktop;
import java.net.URL;

public class HostUtils {

	/** Abre uma URL no navegador padrão do sistema hospedeiro */
	public static void openURL(String url) {
		
		try { Desktop.getDesktop().browse(new URL(url).toURI()); }
		catch (Exception exception) { }
		
	}
	
}
