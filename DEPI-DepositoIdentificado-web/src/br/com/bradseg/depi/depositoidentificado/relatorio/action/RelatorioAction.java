/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseAction;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Super classe para organizar as actions de relatórios.
 * 
 * @param <T>
 *            Classe que representa os dados de entrada para processar os
 *            formulários de consulta.
 */
@Controller
public abstract class RelatorioAction<T extends Serializable> extends BaseAction implements ModelDriven<T> {

	private static final long serialVersionUID = -7335052700150751819L;
	
	/**
	 * Método para validar os dados de entrada. Caso algum erro seja
	 * identificado na validação dos dados de entrada, as mensagens de erro
	 * devem ser informadas utilizando os métodos :
	 * <ul>
	 * <li>
	 * {@link com.opensymphony.xwork2.ActionSupport#addActionMessage(String)} -
	 * para mensagens gerais na página</li>
	 * <li> {@link com.opensymphony.xwork2.ActionSupport#addActionError(String)}
	 * - para erros de negócio</li>
	 * <li>
	 * {@link com.opensymphony.xwork2.ActionSupport#addFieldError(String, String)}
	 * - para validações de campo</li>
	 * </ul>
	 */
	public abstract void validateConsultar();
	
	/**
	 * Sempre retorna SUCCESS
	 * @return SUCCESS
	 */
	public String consultar() {
		
		return SUCCESS;
	}

}
