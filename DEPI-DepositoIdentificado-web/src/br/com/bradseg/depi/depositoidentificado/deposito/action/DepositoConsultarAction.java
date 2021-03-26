package br.com.bradseg.depi.depositoidentificado.deposito.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepositoCampo;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class DepositoConsultarAction extends FiltroAction<DepositoCampo, FiltroConsultarForm<DepositoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(DepositoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient DepositoCrudHelper filtroHelper;
	
	@Autowired
	protected void setFacade(DepositoFacade facade) {
		this.filtroHelper.setFacade(facade);
	}

	@Override
	protected CrudHelper<DepositoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new DepositoCrudHelper();
		}
		return filtroHelper;
	}
	
}
