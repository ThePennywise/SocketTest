package io.github.pennywise.betest.utils;

public class ArgsValidator {
	
	// Validar os argumentos passados
	// O m�todo � est�tico para facilitar o uso aleat�rio das classes
	public static boolean checkForInteger(String argument) {
		try {
			Integer.parseInt(argument);
			// Se n�o deu nenhum erro, j� pode retornar verdadeiro.
			return true;
		} catch (NumberFormatException ex) {
			// Se deu erro, o argumento passado n�o � um n�mero inteiro, portanto, retorna falso.
			return false;
		}
	}

}
