package br.com.bradseg.depi.depositoidentificado.enums;


/**
 * Enum referente aos tipos de Pessoa do sistema
 * 
 */
public enum TipoPessoaEnum {

	FISICA(1,"F","F�sica"),
	JURIDICA(2,"J","Jur�dica");

	private String descricaoTipoPessoa;
	private String siglaTipoPessoa;
	private Integer codigoTipoPessoa;

	/**
	 * Método construtor
	 * 
	 * @param descricaoTipoPessoa
	 * @param codigoTipoPessoa
	 */
	private TipoPessoaEnum(Integer codigoTipoPessoa,String sigla, String descricaoTipoPessoa) {
		this.descricaoTipoPessoa = descricaoTipoPessoa;
		this.siglaTipoPessoa= sigla; 
		this.codigoTipoPessoa = codigoTipoPessoa;
	}

	/**
	 * Retorna o atributo codigoTipoPessoa.
	 * 
	 * @return Retorna o atributo codigoTipoPessoa do tipo Integer.
	 */
	public Integer getCodigo() {
		return codigoTipoPessoa;
	}

	/**
	 * Retorna o atributo descricaoTipoPessoa.
	 * 
	 * @return Retorna o atributo descricaoTipoPessoa do tipo String.
	 */
	public String getDescricaoTipoPessoa() {
		return descricaoTipoPessoa;
	}

	/**
	 * Especifica o atributo descricaoTipoPessoa.
	 * 
	 * @param descricaoTipoPessoa String referente a descricaoTipoPessoa a ser setado.
	 */
	public void setDescricaoTipoPessoa(String descricaoTipoPessoa) {
		this.descricaoTipoPessoa = descricaoTipoPessoa;
	}

	/**
	 * Retorna o atributo codigoTipoPessoa.
	 * 
	 * @return Retorna o atributo codigoTipoPessoa do tipo Integer.
	 */
	public Integer getCodigoTipoPessoa() {
		return codigoTipoPessoa;
	}

	/**
	 * Especifica o atributo codigoTipoPessoa.
	 * 
	 * @param codigoTipoPessoa Integer referente a codigoTipoPessoa a ser setado.
	 */
	public void setCodigoTipoPessoa(Integer codigoTipoPessoa) {
		this.codigoTipoPessoa = codigoTipoPessoa;
	}
	

	/**
	 * Retorna o atributo siglaTipoPessoa.
	 * @return Retorna o atributo siglaTipoPessoa do tipo String.
	 */
	public String getSiglaTipoPessoa() {
		return siglaTipoPessoa;
	}

	/**
	 * Especifica o atributo siglaTipoPessoa.
	 * @param siglaTipoPessoa String referente a siglaTipoPessoa a ser setado.
	 */
	public void setSiglaTipoPessoa(String siglaTipoPessoa) {
		this.siglaTipoPessoa = siglaTipoPessoa;
	}

	/**
	 * Método responsável por retornar o enum do tipo de pessoa
	 * 
	 * @param codigoTipoPessoa - código do Tipo de Pessoa
	 * @return TipoPessoa - objeto Enum para TipoPessoa
	 */
	public static TipoPessoaEnum getTipoPessoaEnum(Integer codigoTipoPessoa) {
		
		TipoPessoaEnum enumEncontrado = null;
		
		for (TipoPessoaEnum e : TipoPessoaEnum.values()) {
			if (e.getCodigoTipoPessoa().equals(codigoTipoPessoa)) {
				enumEncontrado = e;
				break;
			}
		}
		
		return enumEncontrado;
	}
	
	/**
	 * Método utilizado para confirmar se o tipo do enum � PF
	 * @return boolean - booleano informando se a pessoa � f�sica
	 */
	public boolean isFisica(){		
		return FISICA.getCodigo().equals(this.codigoTipoPessoa); 
	}
	/**
	 * Método utilizado para confirmar se o tipo do enum � PJ
	 * @return boolean - booleano informando se a pessoa � f�sica
	 */
	public boolean isJuridica(){		
		return JURIDICA.getCodigo().equals(this.codigoTipoPessoa); 
	}
}
