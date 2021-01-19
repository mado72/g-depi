package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Superclasse para Action que utiliza um Model, implementando {@link ModelDriven}.
 * <p>
 * Fornece suporte a dados em sessão e método para retirar da sessão os dados do formulário.
 * </p>
 * @author Marcelo Damasceno
 *
 * @param <T> Tipo do Modl
 */
@Controller
public abstract class BaseModelAction<T> extends BaseAction implements ModelDriven<T>,
		SessionAware {

	private static final long serialVersionUID = 4671957667151873914L;

	protected Map<String, Object> sessionData;

	@Override
	public void setSession(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}
	
	/**
	 * Remove a instância da sessão correte.
	 */
	protected void clearData() {
		String name = getClass().getSimpleName();
		this.sessionData.remove(name);
		this.request.getSession().removeAttribute(name);
	}
	
	/**
	 * Método para criar uma nova instância do Model.
	 */
	abstract protected void novaInstanciaModel(); 

}
