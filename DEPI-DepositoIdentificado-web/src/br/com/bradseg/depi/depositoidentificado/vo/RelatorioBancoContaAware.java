package br.com.bradseg.depi.depositoidentificado.vo;

/**
 * Manipula Banco e Conta Corrente
 *  
 * @author Marcelo Damasceno
 */
public interface RelatorioBancoContaAware {

	Integer getCodigoCia();
	
	Integer getCodigoAgencia();
	
	Integer getCodigoBanco();
	
	Long getCodigoConta();
	
	void setDescricaoBanco(String banco);
	
	void setDescricaoConta(String conta);
	
}
