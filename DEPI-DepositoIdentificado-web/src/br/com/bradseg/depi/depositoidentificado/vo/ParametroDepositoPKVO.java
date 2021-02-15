/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import org.springframework.util.StringUtils;

/**
 * 
 * Representa a chave de Parâmetro Depósito
 *
 */
public class ParametroDepositoPKVO implements Serializable {
	
	private static final long serialVersionUID = 747240740566118985L;
	
	private int codigoDepartamento;
	private int codigoMotivo; 
	private int codigoCompanhia;

	/**
	 * Construtor padrão 
	 */
	public ParametroDepositoPKVO() {
	}

	/**
	 * Construtor para definir os campos da classe
	 * @param codigoDepartamento Código do departamento
	 * @param codigoMotivo Código Motivo
	 * @param codigoCompanhia Código da Companhia
	 */
	public ParametroDepositoPKVO(int codigoDepartamento, int codigoMotivo,
			int codigoCompanhia) {
		super();
		this.codigoDepartamento = codigoDepartamento;
		this.codigoMotivo = codigoMotivo;
		this.codigoCompanhia = codigoCompanhia;
	}

	/**
	 * Construtor para definir os campos da classe a partir da sua representação
	 * em string.
	 * 
	 * @param codigos
	 *            Códigos separados por ";"
	 */
	public ParametroDepositoPKVO(String codigos) {
		super();
		String[] partes = codigos.split(";");
		this.codigoDepartamento = Integer.parseInt(partes[0]);
		this.codigoMotivo = Integer.parseInt(partes[1]);
		this.codigoCompanhia = Integer.parseInt(partes[2]);
	}

	/**
	 * Retorna codigoDepartamento
	 * @return o codigoDepartamento
	 */
	public int getCodigoDepartamento() {
		return codigoDepartamento;
	}

	/**
	 * Define codigoDepartamento
	 * @param codigoDepartamento valor codigoDepartamento a ser definido
	 */
	public void setCodigoDepartamento(int codigoDepartamento) {
		this.codigoDepartamento = codigoDepartamento;
	}

	/**
	 * Retorna codigoMotivo
	 * @return o codigoMotivo
	 */
	public int getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * Define codigoMotivo
	 * @param codigoMotivo valor codigoMotivo a ser definido
	 */
	public void setCodigoMotivo(int codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	/**
	 * Retorna codigoCompanhia
	 * @return o codigoCompanhia
	 */
	public int getCodigoCompanhia() {
		return codigoCompanhia;
	}

	/**
	 * Define codigoCompanhia
	 * @param codigoCompanhia valor codigoCompanhia a ser definido
	 */
	public void setCodigoCompanhia(int codigoCompanhia) {
		this.codigoCompanhia = codigoCompanhia;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return StringUtils.arrayToDelimitedString(new Object[]{
				codigoDepartamento,
				codigoMotivo,
				codigoCompanhia
		}, ";");
	}
	
}
