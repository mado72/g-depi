package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RespostaDetalheRegistrosDigitaisVO
 * @param RespostaDetalheRegistrosDigitaisVO
 */
@XmlRootElement(name="RespostaDetalheRegistrosDigitais")
public class RespostaDetalheRegistrosDigitaisVO implements Serializable {
	
	
	/** serial. */
	private static final long serialVersionUID = -1384442475458794228L;	
	
	private Long 		parcelaBoleto;
	private String 		sistema;
	private String 		pgmChamador;
	private String 		situacao;
	private Date 		dataRegistroDigital;		
	private Integer     codSituacaoEvento;
	private Long        codJson;
	private String 		cpfCnpj;

	/**
	 * Retorna cpfCnpj
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * Define cpfCnpj.
	 * @param cpfCnpj the cpfCnpf to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	/**
	 * Retorna parcelaBoleto
	 * @return the parcelaBoleto
	 */
	public Long getParcelaBoleto() {
		return parcelaBoleto;
	}
	/**
	 * Define parcelaBoleto.
	 * @param parcelaBoleto the parcelaBoleto to set
	 */
	public void setParcelaBoleto(Long parcelaBoleto) {
		this.parcelaBoleto = parcelaBoleto;
	}
	/**
	 * Retorna sistema
	 * @return the sistema
	 */
	public String getSistema() {
		return sistema;
	}
	/**
	 * Define sistema.
	 * @param sistema the sistema to set
	 */
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	/**
	 * Retorna pgmChamador
	 * @return the pgmChamador
	 */
	public String getPgmChamador() {
		return pgmChamador;
	}
	/**
	 * DefinepgmChamador.
	 * @param pgmChamador the pgmChamador to set
	 */
	public void setPgmChamador(String pgmChamador) {
		this.pgmChamador = pgmChamador;
	}
	/**
	 * Retorna situacao
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}
	/**
	 * Define situacao.
	 * @param situacao the situacao to set
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	/**
	 * Retorna dtaRegistroDigital
	 * @return the dataRegistroDigital
	 */
	public Date getDataRegistroDigital() {
		
		if(dataRegistroDigital != null){
			return (Date)dataRegistroDigital.clone();
		}
		return null;
	}
	/**
	 * Define dataRegistriDigital.
	 * @param dataRegistroDigital the dataRegistroDigital to set
	 */
	public void setDataRegistroDigital(Date dataRegistroDigital) {
		if(dataRegistroDigital != null){
			this.dataRegistroDigital = (Date)dataRegistroDigital.clone();
		}
		
	}
	/**
	 * Retorna Codigo Situacao
	 * @return the codSituacaoEvento
	 */
	public Integer getCodSituacaoEvento() {
		return codSituacaoEvento;
	}
	/**
	 * Define codSituacaoEvento.
	 * @param codSituacaoEvento the codSituacaoEvento to set
	 */
	public void setCodSituacaoEvento(Integer codSituacaoEvento) {
		this.codSituacaoEvento = codSituacaoEvento;
	}
	/**
	 * Retorna JSon
	 * @return the codJson
	 */
	public Long getCodJson() {
		return codJson;
	}
	/**
	 * Define codJson.
	 * @param codJson the codJson to set
	 */
	public void setCodJson(Long codJson) {
		this.codJson = codJson;
	}

	

}
