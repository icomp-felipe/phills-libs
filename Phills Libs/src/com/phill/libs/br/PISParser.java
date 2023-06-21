package com.phill.libs.br;

import com.phill.libs.StringUtils;

/** Implementa o algoritmo de verificação de PIS/PASEP/NIS/NIT.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 3.8, 21/JUN/2023 */
public class PISParser {
	
	/** Aplica a máscara de PIS na string informada.
	 *  @param pis - PIS contendo apenas os 11 dígitos
	 *  @return Uma string com a máscara de PIS aplicada caso os requisitos
	 *  deste parâmetro sejam cumpridos, ou a string original, caso contrário. */
	public static String format(final String pis) {
		
		if ( (pis == null) || (pis.length() != 11))
			return pis;
		
		return String.format("%s.%s.%s-%s",pis.substring(0,3),pis.substring(3,8),pis.substring(8,10),pis.substring(10));
	}

	/** Implementa a máscara de desidentificação do PIS, de acordo com a LGPD.
	 *  @param pis - número de PIS
	 *  @see <a href=https://repositorio.cgu.gov.br/bitstream/1/66920/3/Parecer_Referencial_001_2021_CONJUR_CGU_CGU_AGU.pdf>Parecer Referencial CGU</a> */
	public static String oculta(final String pis) {
		
		if ( (pis == null) || (pis.length() != 11))
			return pis;
		
		return String.format("***.%s.%s.*-*", pis.substring(3,6), pis.substring(6,9));
	}
	
	/** Verifica se um número de PIS/PASEP/NIS/NIT é válido.
	 *  @param pis - String contendo número de PIS, pode conter máscara ou não, aqui apenas os números são extraídos.
	 *  @return Validade do PIS (cálculo numérico). */
	public static boolean parse(String pis) {
		
		// Se o pis recebido for nulo, encerro o código aqui
		if (pis == null)
			return false;
			
		// Extraindo apenas os números do PIS
		pis = StringUtils.extractNumbers(pis);
		
		// Verificando absurdos
		if (verificaAbsurdos(pis))
			return false;
		
		// Se o número tiver mais que 11 dígitos, "capo" ele em 11
		if (pis.length() > 11)
			pis = pis.substring(0,11);
		
		/**************** Cálculo propriamente dito ****************/
		
		// Criando o vetor de pesos
		int[] pesos = new int[]{3,2,9,8,7,6,5,4,3,2};
		
		// Criando o vetor com os números do PIS
		int[] vetor_pis = new int[10];
		
		for (int i=0; i<10; i++)
			vetor_pis[i] = Character.getNumericValue(pis.charAt(i));
		
		// Soma de produtos dos vetores
		int soma_prod = 0;
		
		for (int i=0; i<10; i++)
			soma_prod += (vetor_pis[i] * pesos[i]);
		
		// Cálculo do dígito verificador
		int resto_divs  = (soma_prod  % 11);
		int verificador = (resto_divs < 2 ) ? 0 : (11 - resto_divs);
		
		// Validação do dígito verificador
		return (Character.getNumericValue(pis.charAt(10)) == verificador);
	}
	
	/** Verifica os absurdos de CPF inválidos.
	 *  @param cpf - String contendo o número de CPF (apenas os 11 dígitos)
	 *  @return 'true' se algum absurdo foi encontrado ou 'false', caso contrário */
	private static boolean verificaAbsurdos(String pis) {
		return (pis.equals("00000000000") || pis.equals("11111111111") ||
				pis.equals("22222222222") || pis.equals("33333333333") ||
				pis.equals("44444444444") || pis.equals("55555555555") ||
				pis.equals("66666666666") || pis.equals("77777777777") ||
				pis.equals("88888888888") || pis.equals("99999999999") ||
		        pis.length() < 11);
	}
	
}
