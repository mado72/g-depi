package br.com.bradseg.depi.depositoidentificado.vo;

/**
 * Manipula situação
 *  
 * @author Globality
 */
public interface RelatorioDescricaoSituacaoAware {

	/**
	 * Código da situação
	 * @return Código da situação
	 */
	Integer getCodigoSituacao();
	
	/**
	 * Define situacao
	 * @param situacao Descrição
	 */
	void setDescricaoSituacao(String situacao);
}