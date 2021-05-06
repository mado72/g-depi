package br.com.bradseg.depi.depositoidentificado.relatorio.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Contém os dados do relatório
 * @author Globality
 */
public class DadosRelatorioVO implements Serializable{
	
	private static final long serialVersionUID = -1323406211367203036L;

	private String retorno;
	
	private String relatorio;
	
	private List<? extends Serializable> dados;
	
	private Map<String, Serializable> params;
	
	/**
	 * Construtor padrão
	 */
	public DadosRelatorioVO() {
		// Construtor default
	}

	/**
	 * Construtor para preencher os valores dos campos
	 * @param retorno Retorno da ação
	 * @param relatorio Relatório
	 * @param params Parâmetros do relatório
	 * @param dados Dados do relatório
	 */
	public DadosRelatorioVO(String retorno, String relatorio, Map<String, Serializable> params,
			List<? extends Serializable> dados) {
		super();
		this.retorno = retorno;
		this.relatorio = relatorio;
		this.dados = dados;
		this.params = params;
	}

	/**
	 * Retorna retorno
	 * @return o retorno
	 */
	public String getRetorno() {
		return retorno;
	}

	/**
	 * Define retorno
	 * @param retorno o retorno a ser configurado
	 */
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	/**
	 * Retorna relatorio
	 * @return o relatorio
	 */
	public String getRelatorio() {
		return relatorio;
	}

	/**
	 * Define relatorio
	 * @param relatorio o relatorio a ser configurado
	 */
	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	/**
	 * Retorna dados
	 * @return o dados
	 */
	public List<? extends Serializable> getDados() {
		return dados;
	}

	/**
	 * Define dados
	 * @param dados o dados a ser configurado
	 */
	public void setDados(List<? extends Serializable> dados) {
		this.dados = dados;
	}

	/**
	 * Retorna params
	 * @return o params
	 */
	public Map<String, Serializable> getParams() {
		return params;
	}

	/**
	 * Define params
	 * @param params o params a ser configurado
	 */
	public void setParams(Map<String, Serializable> params) {
		this.params = params;
	}

}
