package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * RespostaDetalheRegistroJson
 *
 */
@XmlRootElement(name="RespostaDetalheRegistroJson")
public class RespostaDetalheRegistroJsonVO implements Serializable {
	
	
	/** serial. */
	private static final long serialVersionUID = -1384442475458794228L;	
	
	private String 		detalheJson;

	/**´
	 * Retorna Json
	 * @return the detalheJson
	 */
	public String getDetalheJson() {
		return detalheJson;
	}

	/**
	 * Seta Json
	 * @param detalheJson the detalheJson to set
	 */
	public void setDetalheJson(String detalheJson) {
		this.detalheJson = detalheJson;
	}

}
