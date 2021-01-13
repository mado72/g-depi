package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.bradseg.depi.depositoidentificado.form.AdmfinBPFiltroForm;

import com.opensymphony.xwork2.ModelDriven;

@Component
public abstract class FiltroAction<T extends AdmfinBPFiltroForm> extends BaseAction implements ModelDriven<T>, SessionAware {

	private static final long serialVersionUID = 935947361413242271L;

	private static final Logger LOGGER = LoggerFactory.getLogger(FiltroAction.class);

	protected Map<String, Object> sessionData;
	
	@Override
	public void setSession(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}
	
	abstract protected void novaInstanciaModel(); 
	
	protected void prepararFiltro() {
		LOGGER.info("Preparando contexto de filtro da consulta");
		
		String acao = request.getParameter("acao");
		if ("firstOpening".equals(acao)) {
			LOGGER.debug("Removendo formulário do contexto de sessão e criando nova instância");
			sessionData.remove(this.getModel().getContextoFiltro());
			this.novaInstanciaModel();
		}
	}
	
	protected void persistirContextoFiltro() {
		T model = getModel();
		
		sessionData.put(model.getContextoFiltro(), model);
	}

}
