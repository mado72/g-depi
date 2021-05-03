package br.com.bradseg.depi.depositoidentificado.util;

/**
 * Interface utilitária que copia o java.util.Suppier (java 8), uma interface
 * funcional e pode, portanto, ser usada como destino de atribuição para uma
 * referência de método.
 * 
 * @author Marcelo Damasceno
 * 
 * @param <T> O tipo do resultado deste fornecedor
 */
public interface Fornecedor<T> {

	/**
	 * Retorna o resultado
	 * 
	 * @return Um resultado
	 */
	public T get();
	
}
