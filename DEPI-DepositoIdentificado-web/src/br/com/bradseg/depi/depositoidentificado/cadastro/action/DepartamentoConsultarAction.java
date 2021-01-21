package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCampo;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class DepartamentoConsultarAction extends FiltroAction<FiltroConsultarForm<DepartamentoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	@Autowired
	private transient DepartamentoFacade facade;

	private transient DepartamentoCrudHelper filtroHelper;
	
	@Override
	protected CrudHelper<?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new DepartamentoCrudHelper(facade);
		}
		return filtroHelper;
	}

}
