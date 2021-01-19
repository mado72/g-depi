package br.com.bradseg.depi.depositoidentificado.enums;

/**
 * Classe responsável por representar dos tipos de Cias 
 * 
 * @author CPM Braxis Capgemini em 15/07/2017
 */
public enum CiaEnum {

	SAUDE			(456,	5711,"92.693.118/0001-60","20261-901","RJ","225","ANS_456.gif"), 
	SAUDE_DENTAL	(476,	51,"58.119.199/0001-51","06455-020","SP","125","ANS_1239.gif"),//0051
	BARE			(244,	5312,"","","","","ANS_476.gif"),
	VIDA			(186,	6866,"","","","","")
	;
	
	private Integer codigoInterno;
	private Integer  codigoExterno;
	private String   cnpj;
	private String   cep;
	private String   uf;
	private String   numeroEndereco;
	private String   imagemRelatorio;
	/**
	 * Construtor padr�o
	 * @param codigoInt - codigoInt 
	 * @param codigoExt - codigoExt
	 */
	private CiaEnum(Integer codigoInt, Integer codigoExt,String cnpj,String cep,String uf,String numero,String imagemRelatorio) {
		this.setCodigoInterno(codigoInt);
		this.setCodigoExterno(codigoExt);
		this.setCnpj(cnpj);
		this.setCep(cep);
		this.setUf(uf);
		this.setNumeroEndereco(numero);
		this.setImagemRelatorio(imagemRelatorio);
	}
	
	public static Integer getCiaInterna(Integer codigoCiaExterna){
		for (CiaEnum e : CiaEnum.values()) {
			if (e.getCodigoExterno().equals(codigoCiaExterna)) {
				return e.getCodigoInterno();
			}
		}
		return null;
	}
	
	public static Integer getCiaExterna(Integer codigoCiaExterna){
		for (CiaEnum e : CiaEnum.values()) {
			if (e.getCodigoExterno().equals(codigoCiaExterna)) {
				return e.getCodigoInterno();
			}
		}
		return null;
	}
	
	
	public static boolean isValida(Integer codigoCiaExterna){
		for (CiaEnum e : CiaEnum.values()) {
			if (e.getCodigoExterno().equals(codigoCiaExterna)) {
				return true;
			}
		}	
		return false;
	}
	
//	public static String getCnpj(String cnpj){
//		for (CiaEnum e : CiaEnum.values()) {
//			if (e.getCnpj().equals(cnpj)) {
//				return e.getCnpj();
//			}
//		}
//		return null;
//	}
	
	public static CiaEnum getCiaEnum(Integer codigoCiaExterna){
		for (CiaEnum e : CiaEnum.values()) {
			if (e.getCodigoExterno().equals(codigoCiaExterna)) {
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Retorna o atributo codigoInterno.
	 * @return Retorna o atributo codigoInterno do tipo Integer.
	 */
	public Integer getCodigoInterno() {
		return codigoInterno;
	}

	/**
	 * Especifica o atributo codigoInterno.
	 * @param codigoInterno Integer referente a codigoInterno a ser setado.
	 */
	public void setCodigoInterno(Integer codigoInterno) {
		this.codigoInterno = codigoInterno;
	}

	/**
	 * Retorna o atributo codigoExterno.
	 * @return Retorna o atributo codigoExterno do tipo Integer.
	 */
	public Integer getCodigoExterno() {
		return codigoExterno;
	}

	/**
	 * Especifica o atributo codigoExterno.
	 * @param codigoExterno Integer referente a codigoExterno a ser setado.
	 */
	public void setCodigoExterno(Integer codigoExterno) {
		this.codigoExterno = codigoExterno;
	}

	/**
	 * Retorna o atributo cnpj
	 * @return the cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Especifica o atributo
	 * @param cnpj the cnpj to set
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * Retorna o CEP
	 * 
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Especifica o CEP
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Retorna a UF
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Determina a UF
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Retorna o numero Endereco
	 * @return the numeroEndereco
	 */
	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	/**
	 * Especifica o numeroEndereco
	 * @param numeroEndereco the numeroEndereco to set
	 */
	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	/**
	 * Retorna a string da imagem do relatorio
	 * @return the imagemRelatorio
	 */
	public String getImagemRelatorio() {
		return imagemRelatorio;
	}

	/**
	 * Especifica a string da imagem do relatorio
	 * @param imagemRelatorio the imagemRelatorio to set
	 */
	public void setImagemRelatorio(String imagemRelatorio) {
		this.imagemRelatorio = imagemRelatorio;
	}
	
	
	
}