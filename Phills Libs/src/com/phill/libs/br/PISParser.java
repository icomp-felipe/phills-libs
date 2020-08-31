package com.phill.libs.br;

import com.phill.libs.StringUtils;

/** Classe de verificação do PIS/PASEP
 *  @author  Felipe André
 *  @version 1.0, 11/09/2018 */
public class PISParser {

	@Deprecated
	public static boolean validaPIS(String pis) {
		
		String aux = StringUtils.extractNumbers(pis);
		
		return (aux.length() == 11);
	}
	
	/** Algoritmo de verificação de PIS/PASEP */
	public static boolean parse(String pis) {
		
		// Extraindo apenas os números
		String numbers = StringUtils.extractNumbers(pis);
		
		// Se o número tiver menos que 11 dígitos ou for nulo, o PIS já é inválido
		if ((numbers == null) || (numbers.length() < 11))
			return false;
		
		// Se o número tiver mais que 11 dígitos, "capo" ele em 11
		if (numbers.length() > 11)
			numbers = numbers.substring(0,11);
		
		// Verifica absurdos
		if (numbers.equals("00000000000") ||
			numbers.equals("11111111111") ||
			numbers.equals("22222222222") ||
			numbers.equals("33333333333") ||
			numbers.equals("44444444444") ||
			numbers.equals("55555555555") ||
			numbers.equals("66666666666") ||
			numbers.equals("77777777777") ||
			numbers.equals("88888888888") ||
			numbers.equals("99999999999"))
			return false;

		// Criando o vetor de pesos
		int[] pesos = new int[]{3,2,9,8,7,6,5,4,3,2};
		
		// Criando o vetor com os números do PIS
		int[] vetor_pis = new int[10];
		
		for (int i=0; i<10; i++)
			vetor_pis[i] = Character.getNumericValue(numbers.charAt(i));
		
		// Soma de produtos dos vetores
		int soma_prod = 0;
		
		for (int i=0; i<10; i++)
			soma_prod += (vetor_pis[i] * pesos[i]);
		
		// Cálculo do dígito verificador
		int resto_divs  = (soma_prod  % 11);
		int verificador = (resto_divs < 2 ) ? 0 : (11 - resto_divs);
		
		// Validação do dígito verificador
		return (Character.getNumericValue(numbers.charAt(10)) == verificador);
	}
	
}
