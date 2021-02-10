/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

/**
 * VO Utilitário para mapear um código e seu indicativo. Usado nas queries que
 * verificam se existe registro em uma entidade definida. 
 */
public class CodigoIndicativoVO implements Serializable {
	
	private static final long serialVersionUID = -1617630831872782963L;

	private Integer codigo;
	
	private String indicativo;

	/**
	 * Retorna codigo
	 * @return o codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Define codigo
	 * @param codigo valor codigo a ser definido
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna indicativo
	 * @return o indicativo
	 */
	public String getIndicativo() {
		return indicativo;
	}

	/**
	 * Define indicativo
	 * @param indicativo valor indicativo a ser definido
	 */
	public void setIndicativo(String indicativo) {
		this.indicativo = indicativo;
	}

}
