/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Model para capturar parâmetros para as consultas que devolvem JSON  
 * @author Marcelo Damasceno
 */
@JsonInclude(Include.NON_NULL)
public class JsonRequestVO implements Serializable {

	private static final long serialVersionUID = 3844439712696115138L;
	
	private Object response;
	
	private Object erro;
	
	private String codigoCia;

	/**
	 * Retorna response
	 * @return o response
	 */
	public Object getResponse() {
		return response;
	}
	
	/**
	 * Define response
	 * @param response valor response a ser definido
	 */
	public void setResponse(Object response) {
		this.response = response;
	}
	
	/**
	 * Retorna codigoCia
	 * @return o codigoCia
	 */
	public String getCodigoCia() {
		return codigoCia;
	}
	
	/**
	 * Define codigoCia
	 * @param codigoCia valor codigoCia a ser definido
	 */
	public void setCodigoCia(String codigoCia) {
		this.codigoCia = codigoCia;
	}
	
	/**
	 * Retorna erro
	 * @return o erro
	 */
	public Object getErro() {
		return erro;
	}
	
	/**
	 * Define erro
	 * @param erro valor erro a ser definido
	 */
	public void setErro(Object erro) {
		this.erro = erro;
	}

}
