package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.AssociarMotivoDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.AssociarMotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.AssociarMotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Processa a ação de salvar do formulário Departamento
 * @author Marcelo Damasceno
 *
 */
@Controller
@Scope("request")
public class AssociarMotivoDepositoSalvarAction extends
		SalvarAction<AssociarMotivoDepositoCampo, AssociarMotivoDepositoVO, AssociarMotivoDepositoEditarFormModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociarMotivoDepositoSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient AssociarMotivoDepositoCrudHelper crudHelper;
	
	@Autowired
	protected void setFacade(AssociarMotivoDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<AssociarMotivoDepositoCampo, AssociarMotivoDepositoVO, AssociarMotivoDepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new AssociarMotivoDepositoCrudHelper();
		}
		return crudHelper;
	}
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoCompanhia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.associarmotivodeposito.cia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoDepartamento", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.associarmotivodeposito.departamento')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoMotivoDeposito", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.associarmotivodeposito.motivo')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codBanco", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.associarmotivodeposito.banco')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoAgencia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.associarmotivodeposito.agencia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="contaCorrente", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.associarmotivodeposito.conta')})}")
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formul\u00e1rio validado. Chamando m\u00e9todo para concluir a opera\u00e7\u00e3o.");
		return super.execute();
	}
}
