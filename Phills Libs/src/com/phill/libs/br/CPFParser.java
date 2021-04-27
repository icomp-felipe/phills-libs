package com.phill.libs.br;

import com.phill.libs.StringUtils;

/** Implementa o algoritmo de verificação de CPF.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 3.7, 24/APR/2021 */
public class CPFParser {
	
	/** Aplica a máscara de CPF na string informada.
	 *  @param cpf - CPF contendo apenas os 11 dígitos
	 *  @return Uma string com a máscara de CPF aplicada caso os requisitos
	 *  deste parâmetro sejam cumpridos, ou a string original, caso contrário. */
	public static String format(final String cpf) {
		
		if ( (cpf == null) || (cpf.length() != 11))
			return cpf;
		
		return String.format("%s.%s.%s-%s",cpf.substring(0,3),cpf.substring(3,6),cpf.substring(6,9),cpf.substring(9));
	}
	
	/** Verifica se um número de CPF é válido.
	 *  @param cpf - String contendo número de CPF, pode conter máscara ou não, aqui apenas os números são extraídos.
	 *  @return Validade do CPF (cálculo numérico) */
	public static boolean parse(final String cpf) {
		
		// Se o CPF recebido for nulo, encerro o código aqui
		if (cpf == null)
			return false;
					
		// Extraindo apenas os números do CPF
		String numeroCPF = StringUtils.extractNumbers(cpf);
				
		// Verificando absurdos
		if (verificaAbsurdos(numeroCPF))
			return false;

		/**************** Cálculo do 1o dígito verificador ****************/
		
		// Criando o vetor de pesos
		int[] pesos = new int[] {10,9,8,7,6,5,4,3,2};
		
		// Criando o vetor de CPF convertendo os dígitos da String recebida para int
		int[] vetorCPF = new int[10];
		
		for (int i=0; i < vetorCPF.length; i++)
			vetorCPF[i] = Character.getNumericValue(numeroCPF.charAt(i));
		
		// Soma de produtos dos vetores
		int somaProd = 0;
		
		for (int i=0; i < pesos.length; i++)
			somaProd += (vetorCPF[i] * pesos[i]);
		
		// Cálculo do 1o dígito verificador
		int restoDivisao   = 11 - (somaProd  % 11);
		int primeiroDigito = (restoDivisao >= 10) ? 0 : restoDivisao;
		
		/**************** Cálculo do 2o dígito verificador ****************/

		// Criando o vetor de pesos
		pesos = new int[] {11,10,9,8,7,6,5,4,3};
		
		// Já inicializando a soma de produtos com o produto do 1o dígito verificador
		// com o seu peso, assim o mesmo vetor do passo anterior pode ser aproveitado
		somaProd = (vetorCPF[9] * 2);
		
		// Realizando as somas de produtos restantes
		for (int i=0; i < pesos.length; i++)
			somaProd += (vetorCPF[i] * pesos[i]);
		
		// Cálculo do 2o dígito verificador
	        restoDivisao  = 11 - (somaProd  % 11);
	    int segundoDigito = (restoDivisao >= 10) ? 0 : restoDivisao;
	
	    // Validação do dígito verificador
	 	return (Character.getNumericValue(numeroCPF.charAt( 9)) == primeiroDigito) &&
	 		   (Character.getNumericValue(numeroCPF.charAt(10)) == segundoDigito );
	 }
	
	/** Verifica os absurdos de CPF inválidos.
	 *  @param cpf - String contendo o número de CPF (apenas os 11 dígitos)
	 *  @return 'true' se algum absurdo foi encontrado ou 'false', caso contrário */
	private static boolean verificaAbsurdos(final String cpf) {
		return (cpf.equals("00000000000") || cpf.equals("11111111111") ||
			    cpf.equals("22222222222") || cpf.equals("33333333333") ||
		        cpf.equals("44444444444") || cpf.equals("55555555555") ||
		        cpf.equals("66666666666") || cpf.equals("77777777777") ||
		        cpf.equals("88888888888") || cpf.equals("99999999999") ||
		        cpf.length() != 11);
	}
	
}