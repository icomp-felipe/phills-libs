package com.phill.libs.br;

import com.phill.libs.StringUtils;

/** Implementa o algoritmo de verificação de CNPJ.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 1.0, 24/APR/2021 */
public class CNPJParser {
	
	/** Aplica a máscara de CNPJ na string informada.
	 *  @param cnpj - CNPJ com ou sem máscara
	 *  @return Uma string com a máscara de CNPJ aplicada caso os requisitos deste parâmetro sejam cumpridos
	 *  (não ser vazio e ter exatamente 14 números), ou a string original, caso contrário. */
	public static String format(final String cnpj) {
		
		final String novoCNPJ = StringUtils.extractNumbers(cnpj);
		
		if ((novoCNPJ == null) || (novoCNPJ.length() != 14))
			return cnpj;
		
		return String.format("%s.%s.%s/%s-%s", novoCNPJ.substring(0,2), novoCNPJ.substring(2,5), novoCNPJ.substring(5,8), novoCNPJ.substring(8,12), novoCNPJ.substring(12) );
	}

	/** Verifica se um número de CNPJ é válido.
	 *  @param cnpj - String contendo número de CNPJ, pode conter máscara ou não, aqui apenas os números são extraídos.
	 *  @return Validade do CNPJ (cálculo numérico). */
	public static boolean parse(final String cnpj) {
		
		// Se o CNPJ recebido for nulo, encerro o código aqui
		if (cnpj == null)
			return false;
			
		// Extraindo apenas os números do CNPJ
		String numeroCNPJ = StringUtils.extractNumbers(cnpj);
		
		// Verificando absurdos
		if (verificaAbsurdos(numeroCNPJ))
			return false;
		
		// Se o número tiver mais que 14 dígitos, "capo" ele em 14
		if (numeroCNPJ.length() > 14)
			numeroCNPJ = numeroCNPJ.substring(0,14);
		
		/**************** Cálculo do 1o dígito verificador ****************/
		
		// Criando o vetor de pesos
		int[] pesos = new int[] {5,4,3,2,9,8,7,6,5,4,3,2};
		
		// Criando o vetor de CNPJ convertendo os dígitos da String recebida para int
		int[] vetorCNPJ = new int[12];
		
		for (int i=0; i<12; i++)
			vetorCNPJ[i] = Character.getNumericValue(numeroCNPJ.charAt(i));
		
		// Soma de produtos dos vetores
		int somaProd = 0;
		
		for (int i=0; i<12; i++)
			somaProd += (vetorCNPJ[i] * pesos[i]);
		
		// Cálculo do 1o dígito verificador
		int restoDivisao   = (somaProd  % 11);
		int primeiroDigito = (restoDivisao < 2 ) ? 0 : (11 - restoDivisao);
		
		/**************** Cálculo do 2o dígito verificador ****************/
		
		// Criando o vetor de pesos
		pesos = new int[] {6,5,4,3,2,9,8,7,6,5,4,3};
		
		// Já inicializando a soma de produtos com o produto do 1o dígito verificador
		// com o seu peso, assim o mesmo vetor do passo anterior pode ser aproveitado
		somaProd = (primeiroDigito * 2);
		
		// Realizando as somas de produtos restantes
		for (int i=0; i<12; i++)
			somaProd += (vetorCNPJ[i] * pesos[i]);
		
		// Cálculo do 2o dígito verificador
		    restoDivisao  = (somaProd  % 11);
		int segundoDigito = (restoDivisao < 2 ) ? 0 : (11 - restoDivisao);
		
		// Validação do dígito verificador
		return (Character.getNumericValue(numeroCNPJ.charAt(12)) == primeiroDigito) &&
			   (Character.getNumericValue(numeroCNPJ.charAt(13)) == segundoDigito );
	}
	
	/** Verifica os absurdos de CNPJ inválidos.
	 *  @param cnpj - String contendo o número de CNPJ (apenas os 14 dígitos)
	 *  @return 'true' se algum absurdo foi encontrado ou 'false', caso contrário */
	private static boolean verificaAbsurdos(final String cnpj) {
		return (cnpj.equals("00000000000") || cnpj.equals("11111111111") ||
				cnpj.equals("22222222222") || cnpj.equals("33333333333") ||
				cnpj.equals("44444444444") || cnpj.equals("55555555555") ||
				cnpj.equals("66666666666") || cnpj.equals("77777777777") ||
				cnpj.equals("88888888888") || cnpj.equals("99999999999") ||
		        cnpj.length() < 14);
	}
	
}