package br.com.bradseg.depi.depositoidentificado.util;

/**
 * Reproduz a interface java.util.Function do Java 8.
 * 
 * <p>
 * Esta é uma interface funcional e pode, portanto, ser usada como destino de
 * atribuição para uma expressão lambda ou referência de método.
 * 
 * @author Marcelo Damasceno
 * 
 * @param <S>
 *            Tipo da entrada
 * @param <R>
 *            Tipo da saída
 */
public interface Funcao<S, R> {

	/**
	 * Representa uma função que aceita um argumento e produz um resultado.
	 * 
	 * @param source
	 *            argumento da função
	 * @return resultado da função
	 */
	R apply(S source);
	
}
