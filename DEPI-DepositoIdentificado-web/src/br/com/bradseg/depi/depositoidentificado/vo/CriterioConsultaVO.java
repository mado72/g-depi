package br.com.bradseg.depi.depositoidentificado.vo;

/**
 * Classe utilitária para armazenar cláusulas SQL para filtrar consultas.
 * 
 * <p>
 * Na propriedade {@link #criterio}, deve-se passar a cláusula com as funções,
 * conversões etc., caso houver, associado com o nome do parâmetro de valor. Por
 * exemplo: <code>
 * UPPER(DBPROD.MOTVO_DEP_IDTFD.RMOTVO_DEP_IDTFD) LIKE :param1
 * </code>
 * </p>
 * <p>
 * A propriedade {@link #param} armazena o nome do parâmetro usado em
 * {@link #criterio}. Por exemplo: <code>param1</code>
 * <p>
 * A propriedade {@link #valor} armazena o valor já formatado para preenchimento
 * do parâmetro. Por exemplo: <code>%TESTE</code>
 * 
 * @author Marcelo Damasceno
 */
public class CriterioConsultaVO {
	
	private String criterio;
	
	private String param;
	
	private String valor;
	
	/**
	 * Construtor para CriterioConsultaVO
	 */
	public CriterioConsultaVO() {
	}

	/**
	 * Construtor para CriterioConsultaVO
	 * @param criterio Critério da consulta
	 * @param param Nome do parâmetro usado no critério
	 * @param valor Valor a ser preenchido no parâmetro
	 */
	public CriterioConsultaVO(String criterio, String param, String valor) {
		super();
		this.criterio = criterio;
		this.param = param;
		this.valor = valor;
	}

	/**
	 * @return Critério da consulta
	 */
	public String getCriterio() {
		return criterio;
	}

	/**
	 * @param criterio
	 *            Critério a ser utilizado. O critério deve já estar com todas
	 *            as funções, conversões etc. que serão utilizadas na consulta.
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}
	
	/**
	 * @return Nome do parâmetro usado na consulta
	 */
	public String getParam() {
		return param;
	}
	
	/**
	 * @param param Define o nome do parâmetro
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return Valor para o preenchimento do parâmetro 
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor Valor para o preenchimento do parâmetro
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/* (não-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(criterio).append(", ").append(param).append(", ")
				.append(valor).append(']');
		return sb.toString();
	}

}
