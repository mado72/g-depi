/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

/**
 * Representa uma entidade com código e valor 
 * @author Marcelo Damasceno
 */
public class CodigoValorVO implements Serializable {

	private static final long serialVersionUID = 1741759615325174810L;

	private String codigo;
	
	private String valor;

	/**
	 * Retorna codigo
	 * @return o codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Define codigo
	 * @param codigo valor codigo a ser definido
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna valor
	 * @return o valor
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Define valor
	 * @param valor valor valor a ser definido
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
