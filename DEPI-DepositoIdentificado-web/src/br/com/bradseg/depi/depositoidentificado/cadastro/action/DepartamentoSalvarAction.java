package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Processa a ação de salvar do formulário Departamento
 * @author Marcelo Damasceno
 *
 */
@Controller
public class DepartamentoSalvarAction extends
		SalvarAction<DepartamentoCampo, DepartamentoVO, DepartamentoEditarFormModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient DepartamentoCrudHelper crudHelper;
	
	@Autowired
	protected void setFacade(DepartamentoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<DepartamentoCampo, DepartamentoVO, DepartamentoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepartamentoCrudHelper();
		}
		return crudHelper;
	}
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="siglaDepartamento", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.departamento.sigla')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="nomeDepartamento", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.departamento.nome')})}")
			},
			stringLengthFields={
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="siglaDepartamento", trim=true, minLength="1", message="${getText('errors.minlength', new java.lang.String[] {getText('label.cadastro.departamento.sigla'), '1'})}"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="siglaDepartamento", trim=true, maxLength="20", message="${getText('errors.maxlength', new java.lang.String[] {getText('label.cadastro.departamento.sigla'), '20'})}"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="nomeDepartamento", trim=true, minLength="1", message="${getText('errors.minlength', new java.lang.String[] {getText('label.cadastro.departamento.nome'), '1'})}"),
					@StringLengthFieldValidator(type= ValidatorType.SIMPLE, fieldName="nomeDepartamento", trim=true, maxLength="200", message="${getText('errors.maxlength', new java.lang.String[] {getText('label.cadastro.departamento.nome'), '200'})}")
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}

}
