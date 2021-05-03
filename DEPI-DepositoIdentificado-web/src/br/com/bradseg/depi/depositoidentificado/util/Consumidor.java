package br.com.bradseg.depi.depositoidentificado.util;

/**
 * Reproduz a interface java.util.Supplier do Java 8. 
 * @author Globality
 *
 * @param <T> Tipo
 */
public interface Consumidor<T> {

	public void consome(T t);
	
}
