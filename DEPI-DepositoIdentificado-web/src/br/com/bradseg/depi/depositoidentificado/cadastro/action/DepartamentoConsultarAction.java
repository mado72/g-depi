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
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.Geral;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class DepartamentoConsultarAction extends FiltroAction<DepartamentoCampo, FiltroConsultarForm<DepartamentoCampo>> {

	/**
	 * 
	 */
	private static final String LABEL_GRID_DEPARTAMENTO_SIGLA_DEPARTAMENTO = "%enum.DepartamentoCampo.Sigla%";

	private static final String LABEL_GRID_DEPARTAMENTO_NOME_DEPARTAMENTO = "%enum.DepartamentoCampo.Nome%";

	protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient DepartamentoCrudHelper filtroHelper;
	
	@Override
	protected CrudHelper<DepartamentoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new DepartamentoCrudHelper();
		}
		return filtroHelper;
	}
	
	@Autowired
	public void setFacade(DepartamentoFacade facade) {
		filtroHelper.setFacade(facade);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction#validarCriterio(br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO)
	 */
	@Override
	protected void validarCriterio(CriterioConsultaVO<DepartamentoCampo> criterio) {
		DepartamentoCampo campo = criterio.getCampo();
		String valor = criterio.getValor();

		switch (campo) {
		case Sigla:
			validaSigla(valor);
			break;
		case Nome:
			validaNome(valor);
			break;
		default:
			break;
		}
	}

	private void validaSigla(String valor) {
		if (valor == null || valor.isEmpty()) {
			addFieldError("sigla", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					LABEL_GRID_DEPARTAMENTO_SIGLA_DEPARTAMENTO));
		}
		else if (valor.length() > 3){
			addFieldError("sigla", BaseUtil.getTextoFormatado(
					Geral.ERRO_CAMPO_EXCESSO,
					LABEL_GRID_DEPARTAMENTO_SIGLA_DEPARTAMENTO, "3"));
		}
	}

	private void validaNome(String valor) {
		if (valor == null || valor.isEmpty()) {
			addFieldError("nome", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					LABEL_GRID_DEPARTAMENTO_NOME_DEPARTAMENTO));
		}
		else if (valor.length() > 40){
			addFieldError("nome", BaseUtil.getTextoFormatado(
					Geral.ERRO_CAMPO_EXCESSO,
					LABEL_GRID_DEPARTAMENTO_NOME_DEPARTAMENTO, "40"));
		}
	}

}
