package br.com.bradseg.depi.depositoidentificado.deposito.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;

/**
 * Processa a ação de salvar do formulário Departamento
 * @author Marcelo Damasceno
 *
 */
@Controller
@Scope("request")
public class DepositoSalvarAction extends
		SalvarAction<DepositoCampo, DepositoVO, DepositoEditarFormModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient DepositoCrudHelper crudHelper;
	
	private String acaoProrrogarCancelar;
	
	public void setAcaoProrrogarCancelar(String acaoProrrogarCancelar) {
		this.acaoProrrogarCancelar = acaoProrrogarCancelar;
	}
	
	@Autowired
	protected void setFacade(DepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<DepositoCampo, DepositoVO, DepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepositoCrudHelper();
		}
		return crudHelper;
	}
	
	private void addErrorRequired(String field, String errorKey) {
		addFieldError(field, getText("errors.required", new String[]{errorKey}));
	}
	
	public void validateSalvar() {
		DepositoEditarFormModel model = getModel();
		if (StringUtils.isEmpty(model.getCodigoCompanhia())) {
			addErrorRequired("codigoCompanhia", "label.cadastro.associarmotivodeposito.cia");
		}
		if (StringUtils.isEmpty(model.getCodigoDepartamento())) {
			addErrorRequired("codigoDepartamento", "label.cadastro.associarmotivodeposito.departamento");
		}
		if (StringUtils.isEmpty(model.getCodigoMotivoDeposito())) {
			addErrorRequired("codigoMotivoDeposito", "label.cadastro.associarmotivodeposito.motivo");
		}
		if (StringUtils.isEmpty(model.getCodBanco())) {
			addErrorRequired("codBanco", "label.cadastro.associarmotivodeposito.banco");
		}
		if (StringUtils.isEmpty(model.getCodigoAgencia())) {
			addErrorRequired("codigoAgencia", "label.cadastro.associarmotivodeposito.agencia");
		}
		if (StringUtils.isEmpty(model.getContaCorrente())) {
			addErrorRequired("contaCorrente", "label.cadastro.associarmotivodeposito.conta");
		}
	}
	
	public String salvar() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}

	public void validateSalvarProrrogarCancelar() {
		// Validações para a ação.
	}
	
	public String salvarProrrogarCancelar() {
		DepositoEditarFormModel model = getModel();
		try {
			if ("P".equals(acaoProrrogarCancelar)) {
				crudHelper.prorrogar(model, getCodUsuarioLogado());
			}
			else {
				crudHelper.cancelar(model, getCodUsuarioLogado());
			}
			
			addActionMessage(getText(ConstantesDEPI.MSG_ALTERAR_EXITO));
			
		} catch (Exception e) {
			LOGGER.error("Falha ao atualizar dep\u00F3sito", e);
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
	}
}
