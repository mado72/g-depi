package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.form.cadastro.MotivoDepositoFiltroForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import ch.qos.logback.core.joran.action.Action;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Processa os dados do filtro de motivo de deposito
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class ConsultarMotivoDepositoFiltroAction extends BaseAction
		implements ModelDriven<MotivoDepositoFiltroForm>, ServletRequestAware, SessionAware {

	private static final long serialVersionUID = -8879650843559189786L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ConsultarMotivoDepositoFiltroAction.class);

	private MotivoDepositoFiltroForm model = new MotivoDepositoFiltroForm();

	private HttpServletRequest request;

	private Map<String, Object> sessionData;
	
	@Override
	public MotivoDepositoFiltroForm getModel() {
		return model;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public void setSession(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}

	// ------------ métodos --------------------------------------------------
	
	public MotivoDepositoCampo[] getMotivoDepositoFiltroList() {
		return MotivoDepositoCampo.valuesForCriteria();
	}
	
	/**
	 * Processa o filtro de pesquisa de Motivo Depósito.
	 * 
	 * @return {@link Action.SUCCESS}
	 */
	@Override
	public String execute() throws Exception {
		LOGGER.info("Processado filtro de consulta de motivo de depósito");
		
		String acao = request.getParameter("acao");
		if ("firstOpening".equals(acao)) {
			sessionData.remove(this.getModel().getContextoFiltro());
		}
		
		// FIXME Processar a consulta e substituir pelo objeto
		Object resultadoConsulta = new Object();
		request.setAttribute(model.getContextoFiltro(), resultadoConsulta);
		request.setAttribute("decorator", "vazio");
		
		return SUCCESS;
	}

}
