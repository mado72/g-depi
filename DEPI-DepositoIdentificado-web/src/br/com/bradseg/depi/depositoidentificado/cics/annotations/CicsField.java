/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para mapear os campos de acesso ao CICS.
 *  
 * @author Marcelo Damasceno
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CicsField {
	
	public static final String TRES = "000";
	public static final String QUATRO = "0000";
	public static final String CINCO = "00000";
	
	/**
	 * Determina se o campo é de entrada, saída ou entrada e saída. 
	 */
	public static enum Direction {
		In, Out, InOut
	}

	/**
	 * Define a ordem do campo para a mensagem do CICS
	 * @return A ordem do campo
	 */
	int order();
	
	/**
	 * O nome do campo utilizado no CICS. Por padrão, o nome do campo Java é utilizado para o campo CICS.
	 * @return Nome do campo
	 */
	String fieldName() default "";
	
	/**
	 * Retorna a direção do parâmetro CICS. Por default é definido como saída. 
	 * @return Direção do parâmetro
	 */
	Direction direction() default Direction.Out;

	int size() default 0;
	
	String pattern() default "";

	/**
	 * O tamanho decimal do campo
	 * @return decimais
	 */
	int decimals() default 0;
	
	/**
	 * Quantidade de ocorrências do campo
	 * @return Quantidade, 0 default
	 */
	int occurrences() default 0;
}
