package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.AssociarMotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.AssociarMotivoDepositoCampo;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class AssociarMotivoDepositoConsultarAction extends FiltroAction<AssociarMotivoDepositoCampo, FiltroConsultarForm<AssociarMotivoDepositoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AssociarMotivoDepositoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient AssociarMotivoDepositoCrudHelper filtroHelper;
	
	@Autowired
	protected void setFacade(AssociarMotivoDepositoFacade facade) {
		this.filtroHelper.setFacade(facade);
	}

	@Override
	protected CrudHelper<AssociarMotivoDepositoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new AssociarMotivoDepositoCrudHelper();
		}
		return filtroHelper;
	}
	
}
