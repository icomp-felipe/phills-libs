package com.phill.libs.br;

import java.util.InputMismatchException;

import com.phill.libs.StringUtils;

/** Implementa o algoritmo de verificação de CPF.
 *  @author Felipe André - felipeandresouza@hotmail.com
 *  @version 3.6, 17/SET/2020 */
public class CPFParser {

	// Constantes de Controle ASCII
	private static final int ASCII_OFFSET = 0x30;
	private static final int NINTH 	  = 0x9;
	private static final int TENTH 	  = 0xA;
	private static final int ELEVENTH = 0xB;
	
	/** Verifica se um número de CPF é válido.
	 *  @param cpf - String contendo número de CPF, pode conter máscara ou não, aqui apenas os números são extraídos.
	 *  @return Validade do CPF (cálculo numérico) */
	public static boolean parse(String cpf) {
		
		// Se o cpf recebido for nulo, encerro o código aqui
		if (cpf == null)
			return false;
		
		// Extraindo apenas os números do CPF
		cpf = StringUtils.extractNumbers(cpf);
		
		// Verificando absurdos
		if (verificaAbsurdos(cpf))
			return false;
		
		// Daqui em diante é apenas cálculo numérico
		char dig10, dig11;
	    int soma, indice, result, numero, peso;

	    try {
	    	
	    	soma = 0;	peso = TENTH;
	    	
	    	for (indice=0; indice<NINTH; indice++) {              
	    		numero = cpf.charAt(indice) - ASCII_OFFSET; 
	    		soma += (numero * peso);
	    		peso--;
	    	}

	    	result = ELEVENTH - (soma % ELEVENTH);
	    	
	    	if ((result == TENTH) || (result == ELEVENTH))
	    		dig10 = '0';
	    	else
	    		dig10 = (char) (result + ASCII_OFFSET);
	    	
	    	soma = 0;	peso = ELEVENTH;
	    	
	    	for (indice=0; indice<TENTH; indice++) {
	    		numero = cpf.charAt(indice) - ASCII_OFFSET;
	    		soma += (numero * peso);
	    		peso--;
	    	}

	    	result = ELEVENTH - (soma % ELEVENTH);
	    	
	    	if ((result == TENTH) || (result == ELEVENTH))
	    		dig11 = '0';
	    	else
	    		dig11 = (char) (result + ASCII_OFFSET);
	    	
	    	if ((dig10 == cpf.charAt(NINTH)) && (dig11 == cpf.charAt(TENTH)))
	    		return true;
	    	else
	    		return false;
	    	
	    }
	    catch (InputMismatchException exception) {
	    	return false;
	    }
	    
	}
	
	/** Verifica os absurdos de CPF inválidos.
	 *  @param cpf - String contendo o número de CPF (apenas os 11 dígitos)
	 *  @return 'true' se algum absurdo foi encontrado ou 'false', caso contrário */
	private static boolean verificaAbsurdos(String cpf) {
		return (cpf.equals("00000000000") || cpf.equals("11111111111") ||
			    cpf.equals("22222222222") || cpf.equals("33333333333") ||
		        cpf.equals("44444444444") || cpf.equals("55555555555") ||
		        cpf.equals("66666666666") || cpf.equals("77777777777") ||
		        cpf.equals("88888888888") || cpf.equals("99999999999") ||
		        cpf.length() != 11);
	}
	
}
