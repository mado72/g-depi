package br.com.bradseg.depi.depositoidentificado.model.enumerated;

/**
 * Classe responsável por representar dos tipos de documentos
 * 
 * @author CPM Braxis Capgemini em 15/07/2017
 */
public enum TipoDocumentoEnum {

	CPF					(1,"CPF","BR2"),
	CNPJ				(2,"CNPJ","BR1"),
	CEI					(3,"CEI","FS0009"),
	ID_REFUGIADO		(4,"Identifcador Refugiado","FS0008"), 
	RNE					(5,"RNE","FS0003"),
	PASSAPORTE			(6,"Passaporte","FS0002");

	private Integer codigo;
	private String  descricao;
	private String codigoSap;

	private TipoDocumentoEnum(Integer codigo, String descricao,String codigoSap) {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
		this.setCodigoSap(codigoSap);
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCodigoSap() {
		return codigoSap;
	}
	
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}

	public static TipoDocumentoEnum getTipoDoc(String codigoSap){
		TipoDocumentoEnum enumEncontrado = null;
		for (TipoDocumentoEnum e : TipoDocumentoEnum.values()) {
			if (e.getCodigoSap().equals(codigoSap)) {
				enumEncontrado = e;
				break;
			}
		}		
		return enumEncontrado;
	}

}