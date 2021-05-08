package br.com.bradseg.depi.depositoidentificado.vo;

/**
 * Manipula dados de Pessoa 
 * @author Globality
 */
public interface RelatorioPessoaAware {

	/**
	 * Obtém o código da pessoa
	 * @return Código
	 */
	Long getCodigoPessoa();

	/**
	 * Define a informação do CPF ou CNPJ para a pessoa 
	 * @param cpfCNPJ CPF ou CNPJ
	 */
	void setCpfCnpj(String cpfCNPJ);

}
