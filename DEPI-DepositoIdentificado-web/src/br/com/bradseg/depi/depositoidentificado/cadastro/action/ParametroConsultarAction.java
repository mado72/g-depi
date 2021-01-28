package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.MotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class ParametroConsultarAction extends FiltroAction<MotivoDepositoCampo, FiltroConsultarForm<MotivoDepositoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ParametroConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient MotivoDepositoCrudHelper filtroHelper;
	
	public ParametroConsultarAction() {
		LOGGER.debug("Instancia de filtroHelper", filtroHelper);
	}
	
	@Autowired
	protected void setFacade(MotivoDepositoFacade facade) {
		this.filtroHelper.setFacade(facade);
	}

	@Override
	protected CrudHelper<MotivoDepositoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new MotivoDepositoCrudHelper();
		}
		return filtroHelper;
	}

}
