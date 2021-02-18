package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.ParametroDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ParametroDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class ParametroConsultarAction extends FiltroAction<ParametroDepositoCampo, FiltroConsultarForm<ParametroDepositoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(ParametroConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient ParametroDepositoCrudHelper filtroHelper;
	
	public ParametroConsultarAction() {
		LOGGER.debug("Instancia de filtroHelper", filtroHelper);
		setMensagemConsultaSemResultado(getText(ConstantesDEPI.ParametroDeposito.ERRO_USUARIO_SEM_GRUPO_ASSOCIADO));
	}
	
	@Autowired
	protected void setFacade(ParametroDepositoFacade facade) {
		this.filtroHelper.setFacade(facade);
	}

	@Override
	protected CrudHelper<ParametroDepositoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new ParametroDepositoCrudHelper();
		}
		return filtroHelper;
	}

}
