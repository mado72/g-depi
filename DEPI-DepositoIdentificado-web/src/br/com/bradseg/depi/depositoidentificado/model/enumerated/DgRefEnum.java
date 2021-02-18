package br.com.bradseg.depi.depositoidentificado.model.enumerated;

/**
 * Classe responsável por representar o Layout do DG_REF
 * 
 * @author CPM Braxis Capgemini em 19/07/2017
 */
public enum DgRefEnum {

	EMPRESA				(0,"Empresa"				,4),
	SUCURSAL_EMISSAO	(4,"Sucursal Emiss�o"		,4),
	RAMO				(8,"Ramo Principal"			,4),
	APOLICE				(12,"Ap�lice"				,8),
	
	OBJETO_SEGURO		(0,"Objeto Seguro"			,20),
	
	ENDOSSO				(20,"Endosso"				,7),
	ID_UNICO			(27,"Identificador �nico"	,15),
	PARCELA				(42,"Parcela"				,4),
	PROPOSTA			(46,"Proposta"				,10),
	SINISTRO			(56,"Sinistro"				,9);

	private Integer posicao;
	private String  nome;
	private Integer tamanho;
	
	private DgRefEnum(Integer posicao, String nome, Integer tamanho) {
		this.posicao = posicao;
		this.nome = nome;
		this.tamanho = tamanho;
	}
	/**
	 * Retorna o atributo posicao.
	 * @return Retorna o atributo posicao do tipo Integer.
	 */
	public Integer getPosicao() {
		return posicao;
	}

	/**
	 * Especifica o atributo posicao.
	 * @param posicao Integer referente a posicao a ser setado.
	 */
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}

	/**
	 * Retorna o atributo nome.
	 * @return Retorna o atributo nome do tipo String.
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * Especifica o atributo nome.
	 * @param nome String referente a nome a ser setado.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * Retorna o atributo tamanho.
	 * @return Retorna o atributo tamanho do tipo Integer.
	 */
	public Integer getTamanho() {
		return tamanho;
	}
	/**
	 * Especifica o atributo tamanho.
	 * @param tamanho Integer referente a tamanho a ser setado.
	 */
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

}