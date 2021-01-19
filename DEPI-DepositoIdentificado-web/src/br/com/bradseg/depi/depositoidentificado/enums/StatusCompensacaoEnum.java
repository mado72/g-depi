package br.com.bradseg.depi.depositoidentificado.enums;

/**
 * Enum de status Compensa��o
 *
 */
public enum StatusCompensacaoEnum {
	
	ABERTO(1,"Aberto"),
	CANCELADO(2,"Cancelado"),
	PRESCRITO(3,"Prescrito"),
	COMPENSADO(4,"Compensado");
	
	private Integer codigoStatus;
	private String descricaoStatus;

	/**
	 * Construtor Status Compensacao
	 * 
	 * @param codigoStatus codigo do status da compensacao
	 * @param descricaoStatus descricao do status da compensacao
	 */
	private StatusCompensacaoEnum(Integer codigoStatus, String descricaoStatus) {
		this.codigoStatus = codigoStatus;
		this.descricaoStatus = descricaoStatus;
	}
	
	
	/**
	 * Método respons�vel por retornar o enum do Status Compensacao
	 * 
	 * @param codigoStatus informa o codigo do Status da compensacao
	 * @return StatuscompensacaoEnum enum de status compensa��o
	 */
	public static StatusCompensacaoEnum getStatusCompensacaoEnum(Integer codigoStatus) {
		
		StatusCompensacaoEnum enumEncontrado = null;
		
		for (StatusCompensacaoEnum e : StatusCompensacaoEnum.values()) {
			if (e.getCodigoStatus().equals(codigoStatus)) {
				enumEncontrado = e;
				break;
			}
		}
		
		return enumEncontrado;
	}
	
	/**
	 * Retorna o codigo do status
	 * @return the codigoStatus codigo do Status da compensacao
	 */
	public Integer getCodigoStatus() {
		return codigoStatus;
	}
	/**
	 * Define o codigo do status
	 * 
	 * @param codigoStatus the codigoStatus to set
	 */
	public void setCodigoStatus(Integer codigoStatus) {
		this.codigoStatus = codigoStatus;
	}
	/**
	 * Retorna a descricao do Status
	 * @return the descricaoStatus descricao do Status
	 */
	public String getDescricaoStatus() {
		return descricaoStatus;
	}
	/**
	 * Define descricao do status
	 * @param descricaoStatus the descricaoStatus to set
	 */
	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

}
