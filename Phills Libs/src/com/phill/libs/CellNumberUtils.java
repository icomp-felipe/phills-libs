package com.phill.libs;

/** build_20180927 */
public class CellNumberUtils {

	/** Faz o tratamento dos diversos tipos de contatos existentes e retorna apenas os números (com DDD) */
	public static String extractNumber(String contato) {
		
		if (contato != null) {
			
			// Aqui faço a extração dos dígitos do contato
			String aux = StringUtils.extractNumbers(contato);
			
			// Verifica se o número é composto apenas por zeros
			if (aux.matches("^[01]+$"))
				return null;
			
			// Tratamento para telefones convencionais antigos. Ex.: 624-5097
			if (aux.length() == 7)
				return String.format("923%s",aux);
			
			// Tratamento para números de telefone sem DDD
			if (aux.length() == 8)
				if ((aux.charAt(0) == '9') || (aux.charAt(0) == '8'))	// Se celular, adiciono o DDD 92 e o nono dígito...
						return String.format("929%s",aux);
				else
					return String.format("92%s",aux);		// ... senão adiciono apenas o DDD
			
			// Tratamento para números de celular com o nono dígito, porém, sem DDD
			if (aux.length() == 9)
				return String.format("92%s",aux);
			
			// Tratamento para números de telefone com DDD
			if (aux.length() == 10)
				if ((aux.charAt(2) == '9') || (aux.charAt(2) == '8'))	// Se celular, adiciono o nono dígito...
					return String.format("%s9%s",aux.substring(0,2),aux.substring(2));
				else
					return aux;	// ... senão o contato já está no formato correto
			
			// Tratamento para números de celular com DDD e nono dígito
			if (aux.length() == 11)
				return aux;
			
		}
		
		return null;
	}
	
	/** Recupera um contato e formata com a máscara de contatos (sempre com DDD) */
	public static String format(String contato) {
		
		if (contato != null) {
			
			if (contato.length() == 10)
				return String.format("(%s) %s-%s",contato.substring(0,2),contato.substring(2,6),contato.substring(6));
			
			if (contato.length() == 11)
				return String.format("(%s) %s-%s",contato.substring(0,2),contato.substring(2,7),contato.substring(7));
			
		}
		
		return contato;
	}
	
}
