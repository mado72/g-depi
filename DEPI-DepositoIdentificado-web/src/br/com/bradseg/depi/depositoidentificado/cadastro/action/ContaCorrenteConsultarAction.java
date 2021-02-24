package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.ContaCorrenteCrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class ContaCorrenteConsultarAction extends FiltroAction<ContaCorrenteAutorizadaCampo, FiltroConsultarForm<ContaCorrenteAutorizadaCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ContaCorrenteConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient ContaCorrenteCrudHelper filtroHelper;
	
	@Autowired
	protected void setFacade(ContaCorrenteFacade facade) {
		this.filtroHelper.setFacade(facade);
	}

	@Override
	protected CrudHelper<ContaCorrenteAutorizadaCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new ContaCorrenteCrudHelper();
		}
		return filtroHelper;
	}
	
}
