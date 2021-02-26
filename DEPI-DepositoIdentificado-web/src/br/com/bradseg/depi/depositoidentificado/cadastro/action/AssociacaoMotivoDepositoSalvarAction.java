package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.ContaCorrenteEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.ContaCorrenteCrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

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
public class AssociacaoMotivoDepositoSalvarAction extends
		SalvarAction<ContaCorrenteAutorizadaCampo, ContaCorrenteAutorizadaVO, ContaCorrenteEditarFormModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssociacaoMotivoDepositoSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient ContaCorrenteCrudHelper crudHelper;
	
	@Autowired
	protected void setFacade(ContaCorrenteFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<ContaCorrenteAutorizadaCampo, ContaCorrenteAutorizadaVO, ContaCorrenteEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new ContaCorrenteCrudHelper();
		}
		return crudHelper;
	}
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoCompanhia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.contacorrenteautorizada.cia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoBanco", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.contacorrenteautorizada.banco')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="agencia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.contacorrenteautorizada.agencia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="contaCorrente", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.contacorrenteautorizada.contaCorrente')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="trps", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.contacorrenteautorizada.trps')})}")
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}
}
