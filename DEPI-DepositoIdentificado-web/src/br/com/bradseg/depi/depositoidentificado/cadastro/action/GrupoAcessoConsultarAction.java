package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.GrupoAcessoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.GrupoAcessoCampo;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class GrupoAcessoConsultarAction extends FiltroAction<GrupoAcessoCampo, FiltroConsultarForm<GrupoAcessoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(GrupoAcessoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient GrupoAcessoCrudHelper filtroHelper;
	
	public GrupoAcessoConsultarAction() {
		LOGGER.debug("Instancia de filtroHelper", filtroHelper);
	}
	
	@Autowired
	protected void setFacade(GrupoAcessoFacade facade) {
		this.filtroHelper.setFacade(facade);
	}

	@Override
	protected CrudHelper<GrupoAcessoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new GrupoAcessoCrudHelper();
		}
		return filtroHelper;
	}

}
