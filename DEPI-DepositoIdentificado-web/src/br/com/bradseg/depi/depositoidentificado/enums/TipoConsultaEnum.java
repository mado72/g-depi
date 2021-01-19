package br.com.bradseg.depi.depositoidentificado.enums;

/**
 * Classe respons�vel por representar dos tipos de Consulta
 *
 */
public enum TipoConsultaEnum {
	
	COBRANCA(1,"COBRANCA"),
	PAGAMENTO(2,"PAGAMENTO");
	
	private Integer codigoTipoConsulta;
	private String descricaoTipoConsulta;

	/**
	 * Construtor Status Compensacao
	 * 
	 * @param codigoTipoConsulta codigo do status da compensacao
	 * @param descricaoTipoConsulta descricao do status da compensacao
	 */
	private TipoConsultaEnum(Integer codigoTipoConsulta, String descricaoStatus) {
		this.codigoTipoConsulta = codigoTipoConsulta;
		this.descricaoTipoConsulta = descricaoStatus;
	}
	
	
	/**
	 * Método respons�vel por retornar o enum do Status Compensacao
	 * 
	 * @param codigoTipoConsulta informa o codigo do Status da compensacao
	 * @return StatuscompensacaoEnum enum de status compensa��o
	 */
	public static TipoConsultaEnum getTipoConsultaEnum(Integer codigoTipoConsulta) {
		
		TipoConsultaEnum enumEncontrado = null;
		
		for (TipoConsultaEnum e : TipoConsultaEnum.values()) {
			if (e.getCodigoTipoConsulta().equals(codigoTipoConsulta)) {
				enumEncontrado = e;
				break;
			}
		}
		
		return enumEncontrado;
	}
	
	/**
	 * Retorna o codigo do tipo Consulta
	 * @return the codigoTipoConsulta Codigo Tipo Consulta
	 */
	public Integer getCodigoTipoConsulta() {
		return codigoTipoConsulta;
	}
	/**
	 * Define o codigo do tipo Consulta
	 * 
	 * @param codigoTipoConsulta the codigoStatus to set
	 */
	public void setCodigoTipoConsulta(Integer codigoTipoConsulta) {
		this.codigoTipoConsulta = codigoTipoConsulta;
	}
	/**
	 * Retorna a descricao do Status
	 * @return the descricaoStatus descricao do Status
	 */
	public String getDescricaoTipoConsulta() {
		return descricaoTipoConsulta;
	}
	/**
	 * Define descricao do status
	 * @param descricaoTipoConsulta the descricao Tipo Consulta to set
	 */
	public void setDescricaoTipoConsulta(String descricaoTipoConsulta) {
		this.descricaoTipoConsulta = descricaoTipoConsulta;
	}

}
