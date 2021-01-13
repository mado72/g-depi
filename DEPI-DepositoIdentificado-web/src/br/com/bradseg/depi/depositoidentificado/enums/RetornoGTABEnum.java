package br.com.bradseg.depi.depositoidentificado.enums;

/**
 * Classe responsável por representar dos tipos de relatórios gerados no Jasper
 * 
 * @author CPM Braxis Capgemini em 15/07/2017
 */
public enum RetornoGTABEnum {

	OK					(0,"Sucesso"), 
	ERRO_DB2			(1,"Erro no DB2"),
	INEXISTENTE			(2,"Registro Inexistente"),
	ERRO_PREENCHIMENTO	(3,"Erro de preenchimento do Código do Ramo")
	
	;
	private Integer codigo;
	private String  descricao;

	private RetornoGTABEnum(Integer codigo, String descricao) {
		this.setCodigo(codigo);
		this.setDescricao(descricao);
	}
	
	public static boolean isSucesso(Integer codigo){
		return OK.getCodigo().equals(codigo);
	}
	
	public static boolean isInexistente(Integer codigo){
		return INEXISTENTE.getCodigo().equals(codigo);
	}

	/**
	 * Retorna o atributo codigo.
	 * @return Retorna o atributo codigo do tipo Integer.
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Especifica o atributo codigo.
	 * @param codigo Integer referente a codigo a ser setado.
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna o atributo descricao.
	 * @return Retorna o atributo descricao do tipo String.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Especifica o atributo descricao.
	 * @param descricao String referente a descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}