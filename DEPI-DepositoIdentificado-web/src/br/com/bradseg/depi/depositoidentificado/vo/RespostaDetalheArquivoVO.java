package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * RespostaDetalheArquivoVO
 * @param RespostaDetalheArquivoVO
 */
@XmlRootElement(name="RespostaDetalheArquivo")
public class RespostaDetalheArquivoVO implements Serializable {
	
	/** serial. */
	private static final long serialVersionUID = -1384442475458794228L;	
	
	private String 		tipoArquivo;
	private String 		arquivo;
	
	public RespostaDetalheArquivoVO(String tipoArquivo, String arquivo) {
		super();
		this.tipoArquivo = tipoArquivo;
		this.arquivo = arquivo;
	}
	/**
	 * Obtem tipoArquivo.
	 * @return the tipoArquivo
	 */
	public String getTipoArquivo() {
		return tipoArquivo;
	}
	/**
	 * Define tipoArquivo
	 * @param tipoArquivo the tipoArquivo to set
	 */
	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	/**
	 * Obtem arquivo.
	 * @return the arquivo
	 */
	public String getArquivo() {
		return arquivo;
	}
	/**
	 * Define arquivo.
	 * @param arquivo the arquivo to set
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}



}
