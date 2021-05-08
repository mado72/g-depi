package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * 
 * @author Globality
 */
public class PessoaVO implements Serializable {

	private static final long serialVersionUID = -1021387522710954730L;

	private long codigo;
	
	private String nome;
	
	private long cpfCnpj;
	
	private int tipo;

	/**
	 * Construtor padrão
	 */
	public PessoaVO() {
		// Construtor padrão
		super();
	}
	
	/**
	 * Construtor para preencher campos
	 * @param codigo Código da pessoa
	 * @param nome Nome da pessoa
	 * @param cpfCnpj CPF ou CNPJ
	 * @param tipo Tipo da pessoa: 3 = Física, 4 = Jurídica
	 */
	public PessoaVO(long codigo, String nome, long cpfCnpj, int tipo) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.tipo = tipo;
	}
	
	/**
	 * É pessoa física
	 * @return true = sim
	 */
	public boolean isPessoaFisica() {
		return tipo == ConstantesDEPI.TIPO_PESSOA_FISICA;
	}
	
	/**
	 * É pessoa jurídica
	 * @return true = sim
	 */
	public boolean isPessoaJuridica() {
		return tipo == ConstantesDEPI.TIPO_PESSOA_JURIDICA;
	}

	/**
	 * Retorna codigo
	 * @return o codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * Define codigo
	 * @param codigo o codigo a ser configurado
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna nome
	 * @return o nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define nome
	 * @param nome o nome a ser configurado
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Retorna cpfCnpj
	 * @return o cpfCnpj
	 */
	public long getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define cpfCnpj
	 * @param cpfCnpj o cpfCnpj a ser configurado
	 */
	public void setCpfCnpj(long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Retorna tipo
	 * @return o tipo
	 */
	public int getTipo() {
		return tipo;
	}

	/**
	 * Define tipo
	 * @param tipo o tipo a ser configurado
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
