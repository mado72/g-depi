package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.MotivoDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.MotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Processa a ação de salvar do formulário MotivoDeposito
 * @author Marcelo Damasceno
 *
 */
@Controller
public class MotivoDepositoSalvarAction extends
		SalvarAction<MotivoDepositoCampo, MotivoDepositoVO, MotivoDepositoEditarFormModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient MotivoDepositoCrudHelper crudHelper;
	
	@Autowired
	protected void setFacade(MotivoDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<MotivoDepositoCampo, MotivoDepositoVO, MotivoDepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new MotivoDepositoCrudHelper();
		}
		return crudHelper;
	}
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="descricaoBasica", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.motivodeposito.descricaoBasica')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="descricaoDetalhada", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.motivodeposito.descricaoDetalhada')})}")
			},
			stringLengthFields={
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="descricaoBasica", trim=true, minLength="1", message="${getText('errors.minlength', new java.lang.String[] {getText('label.cadastro.motivodeposito.descricaoBasica'), '1'})}"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="descricaoBasica", trim=true, maxLength="20", message="${getText('errors.maxlength', new java.lang.String[] {getText('label.cadastro.motivodeposito.descricaoBasica'), '20'})}"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="descricaoDetalhada", trim=true, minLength="1", message="${getText('errors.minlength', new java.lang.String[] {getText('label.cadastro.motivodeposito.descricaoDetalhada'), '1'})}"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="descricaoDetalhada", trim=true, maxLength="200", message="${getText('errors.maxlength', new java.lang.String[] {getText('label.cadastro.motivodeposito.descricaoDetalhada'), '200'})}")
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}

}
