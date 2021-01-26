package br.com.bradseg.depi.depositoidentificado.funcao.action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper.EstadoRegistro;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

@Controller
public abstract class SalvarAction<VO, F extends CrudForm> extends BaseModelAction<F> {

	private static final long serialVersionUID = 1949214415954508324L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SalvarAction.class);

	private final F model;
	
	public SalvarAction() {
		this.model = getCrudHelper().criarCrudModel();
	}
	
	@Override
	public F getModel() {
		return model;
	}
	
	public void validateExecute() {
		LOGGER.debug("Limpando action errors. Possui algum? {}", hasErrors());
		clearActionErrors();
		clearMessages();
	}
	
	public void validateInput() {
		LOGGER.debug("Validação possui erros: {}", hasErrors());
		clearErrorsAndMessages();
	}

	private void persistirDados() {
		LOGGER.info("Persistindo dados do formulário");
		
		clearErrorsAndMessages();

		F model = getModel();
		CrudHelper<VO, F> helper = getCrudHelper();
		
		LoginVo usuarioLogado = getUsuarioLogado();
		
		EstadoRegistro estado = helper.persistirDados(model, usuarioLogado);

		if (estado == EstadoRegistro.NOVO) {
			addActionMessage(getText(ConstantesDEPI.MSG_INSERIR_EXITO));
		}
		else {
			addActionMessage(getText(ConstantesDEPI.MSG_ALTERAR_EXITO));
		}
	}
	
	protected abstract CrudHelper<VO, F> getCrudHelper();
	
	@Override
	public String execute() {
		try {
			persistirDados();
		} catch (DEPIBusinessException e) {
			addActionError(e.getMessage());
			return INPUT;
		} catch (DEPIIntegrationException e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
	
	@Override
	@SkipValidation
	public String input() {
		if (getModel().getEstado() == EstadoCrud.ALTERAR) {
			return ERROR;
		}
		return INPUT;
	}

}
