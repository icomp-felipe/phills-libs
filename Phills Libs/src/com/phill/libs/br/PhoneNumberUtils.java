package com.phill.libs.br;

import com.phill.libs.StringUtils;

/** Implementa alguns métodos de tratamento de dados para números de telefone brasileiros.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.5, 18/SET/2020 */
public class PhoneNumberUtils {

	/** Faz o tratamento dos diversos tipos de contatos existentes e retorna apenas os números (com DDD).
	 *  @param contato - número de telefone com ou sem máscara, aqui apenas os dígitos são extraídos
	 *  @return String contendo apenas os números de telefone. */
	public static String extractNumber(final String contato) {
		
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
	
	/** Recupera um contato e formata com a máscara de contatos (sempre com DDD).
	 *  @param contato - string contendo apenas os dígitos do telefone
	 *  @param comDDD - informa se o contato a ser retornado possuirá DDD ou não
	 *  @return Uma string com o número de telefone com máscara.  */
	public static String format(final String contato, final boolean comDDD) {
		
		if (contato != null) {
			
			if (comDDD) {
				
				if (contato.length() == 10)
					return String.format("(%s) %s-%s",contato.substring(0,2),contato.substring(2,6),contato.substring(6));
				
				if (contato.length() == 11)
					return String.format("(%s) %s-%s",contato.substring(0,2),contato.substring(2,7),contato.substring(7));
				
			}
			else {
				
				if (contato.length() == 10)
					return String.format("%s-%s",contato.substring(2,6),contato.substring(6));
				
				if (contato.length() == 11)
					return String.format("%s-%s",contato.substring(2,7),contato.substring(7));
				
			}
			
		}
		
		return contato;
	}
	
}
