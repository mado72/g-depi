package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.bradseg.depi.depositoidentificado.form.AdmfinBPFiltroForm;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;

@Component
public abstract class FiltroAction<T extends AdmfinBPFiltroForm> extends BaseAction implements ModelDriven<T>, SessionAware {

	private static final long serialVersionUID = 935947361413242271L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAction.class);

	protected Map<String, Object> sessionData;

	@Resource
	private transient String www3;
	
	@Override
	public void setSession(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}

	public String getWww3() {
		return www3;
	}
	
	public String getEstatico() {
		return request.getContextPath() + "/includes";
	}
	
	abstract protected void novaInstanciaModel(); 
	
	protected void prepararFiltro() {
		LOGGER.info("Preparando contexto de filtro da consulta");
		
		if (getModel() != null && getModel().getColecaoDados() != null) {
			getModel().setColecaoDados(null);
		}
		
		LOGGER.debug("Removendo formulário do contexto de sessão e criando nova instância");
		sessionData.remove(this.getModel().getContextoFiltro());
		this.getModel().setColecaoDados(null);
		
		this.novaInstanciaModel();
		
		this.getModel().setColecaoDados(null);
	}
	
	protected void persistirContextoFiltro() {
		T model = getModel();
		
		sessionData.put(model.getContextoFiltro(), model);
	}
	
	/**
	 * Primeiro método chamado da Action. Cria uma nova instância de formulário
	 * na sessão e armazena-a na sessão do usuário.
	 * 
	 * @return {@link Action#SUCCESS}
	 */
	public String iniciarFormulario() {
		this.prepararFiltro();
		
		this.persistirContextoFiltro();
		
		return SUCCESS;
	}

}
