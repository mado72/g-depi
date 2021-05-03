package br.com.bradseg.depi.depositoidentificado.vo;

/**
 * Manipula companhia
 * 
 * @author Globality
 */
public interface RelatorioCompanhiaAware {

	/**
	 * Obtém código da cia
	 * @return Código
	 */
	Integer getCodigoCia();
	
	/**
	 * Define descrição da cia
	 * @param descricao descrição
	 */
	void setDescricaoCia(String descricao);
	
}
