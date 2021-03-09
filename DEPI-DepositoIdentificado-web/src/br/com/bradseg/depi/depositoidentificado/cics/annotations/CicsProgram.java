/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação que define os parâmetros utilizados por um programa CICS. 
 * @author Marcelo Damasceno
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CicsProgram {

	/**
	 * Retorna o nome do programa CICS
	 * @return Nome do programa
	 */
	String programName();
	
	/**
	 * Retorna o nome da transação CICS
	 * @return Nome da transação
	 */
	String transactionName();
	
	/**
	 * Retorna o tamanho da COMM_LENGTH
	 * @return Tamanho em bytes
	 */
	int commLength();
	
}
