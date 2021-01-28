package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

/**
 * Classe utilitária para armazenar cláusulas SQL para filtrar consultas.
 * 
 * <p>
 * A classe mapeia o campo, a operação e o valor a ser utilizado em cláusulas
 * para filtrar consultas SQL
 * </p>
 * <p>
 * Através do método {@link #getClausula()}, esta classe formata a cláusula,
 * como no exemplo abaixo: <code>
 * UPPER(DBPROD.MOTVO_DEP_IDTFD.RMOTVO_DEP_IDTFD) LIKE :param1
 * </code>
 * </p>
 * <p>
 * A propriedade {@link #param} armazena o nome do parâmetro a ser usado na
 * {@link #getClausula()}. Por exemplo: <code>param1</code>
 * <p>
 * O método {@link #valor} armazena o valor já formatado para preenchimento
 * do parâmetro. Por exemplo: <code>%TESTE</code>
 * 
 * @author Marcelo Damasceno
 *
 * @param <T> Tipo do campo que o critério armazena
 */
public class CriterioConsultaVO<T extends IEntidadeCampo> implements Serializable {

	private static final long serialVersionUID = 5938378242394951493L;
	
	private T campo;
	
	private TipoOperacao operacao;

	private String param;

	private String valor;
	
	private String clausula;

	/**
	 * Construtor para CriterioConsultaVO
	 */
	public CriterioConsultaVO() {
		super();
	}

	/**
	 * Construtor para CriterioConsultaVO
	 * 
	 * @param campo
	 *            Campo a ser mapeado
	 * @param operacao
	 *            Tipo de operação utilizada no critério de consulta.
	 * @param valor
	 *            Valor do campo.
	 * @param param
	 *            Nome do parâmetro
	 */
	public CriterioConsultaVO(T campo, TipoOperacao operacao, String valor, String param) {
		super();
		this.campo = campo;
		this.operacao = operacao;
		this.param = param;
		this.valor = valor;
		this.clausula = operacao.formatarClausula(campo.getNome(), param);
	}
	
	/**
	 * Construtor para CriterioConsultaVO
	 * 
	 * @param clausula
	 *            Cláusula fixa não dependente de campo ou parâmetro.
	 */
	public CriterioConsultaVO(String clausula) {
		super();
		this.clausula = clausula;
	}

	/**
	 * Monta o critério da consulta
	 * 
	 * @return Critério da consulta
	 */
	public String getClausula() {
		return clausula;
	}
	
	/**
	 * Método utilitário para formatar o valor em função da operação definida
	 * neste critério.
	 * 
	 * @return Valor formatado para ser usado na query.
	 */
	public String getValorFormatado() {
		return operacao.formatarValor(valor);
	}

	/**
	 * Retorna o parâmetro
	 * 
	 * @return Nome do parâmetro usado na consulta
	 */
	public String getParam() {
		return param;
	}

	/**
	 * Retorna Valor para o preenchimento do parâmetro
	 * 
	 * @return Valor para o preenchimento do parâmetro
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * Define valor para o preenchimento do parâmetro
	 * 
	 * @param valor
	 *            Valor para o preenchimento do parâmetro
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	/**
	 * Retorna campo
	 * @return o campo
	 */
	public T getCampo() {
		return campo;
	}
	
	/**
	 * Retorna operacao
	 * @return o operacao
	 */
	public TipoOperacao getOperacao() {
		return operacao;
	}
	
	/*
	 * (não-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('[').append(getClausula()).append(", ").append(param).append(", ")
				.append(valor).append(']');
		return sb.toString();
	}

}
