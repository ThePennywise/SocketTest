package io.github.pennywise.betest.utils;

public class ArgsValidator {
	
	// Validar os argumentos passados
	// O método é estático para facilitar o uso aleatório das classes
	public static boolean checkForInteger(String argument) {
		try {
			Integer.parseInt(argument);
			// Se não deu nenhum erro, já pode retornar verdadeiro.
			return true;
		} catch (NumberFormatException ex) {
			// Se deu erro, o argumento passado não é um número inteiro, portanto, retorna falso.
			return false;
		}
	}

}
