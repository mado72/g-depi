package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.MotivoDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.MotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
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
		SalvarAction<MotivoDepositoVO, MotivoDepositoEditarFormModel> {
	
	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient MotivoDepositoCrudHelper crudHelper;
	
	@Autowired
	void setFacade(MotivoDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<MotivoDepositoVO, MotivoDepositoEditarFormModel> getCrudHelper() {
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
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="descricaoBasica", trim=true, minLength="1", maxLength="20", key="errors.minlength"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="descricaoDetalhada", trim=true, minLength="1", maxLength="200", key="errors.minlength")
			}
		)
	@Override
	public String execute() {
		return super.execute();
	}

}
