/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Marcelo Damasceno
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CicsFiller {
	
	/**
	 * Determina se o Filler é antes ou após o campo. 
	 */
	public static enum FillerSide {
		PRE, POS;
	}

	/**
	 * Retorna a constante para ser preenchida como campo FILLER do CICS. Quando
	 * definido, usará {@link #fillerSide()} para definir se o filler vem antes
	 * ou após o campo.
	 * 
	 * @return String constante do filler.
	 */
	String value() default "";
	
	/**
	 * Apenas usando quando o campo define um filler.
	 * @return PRE antes do campo, POS depois do campo.
	 */
	FillerSide fillerSide() default FillerSide.POS;

}
