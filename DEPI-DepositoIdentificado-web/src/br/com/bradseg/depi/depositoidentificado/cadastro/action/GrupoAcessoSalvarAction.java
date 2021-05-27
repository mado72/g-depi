/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.GrupoAcessoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.GrupoAcessoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.GrupoAcessoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Ação para salvar dados do formulário Grupo Acesso.
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class GrupoAcessoSalvarAction extends SalvarAction<GrupoAcessoCampo, GrupoAcessoVO, GrupoAcessoEditarFormModel> {

	private static final long serialVersionUID = 4074074873743840681L;

	private static final Logger LOGGER = LoggerFactory.getLogger(GrupoAcessoSalvarAction.class);
	
	private transient GrupoAcessoCrudHelper crudHelper;

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction#getCrudHelper()
	 */
	@Override
	protected CrudHelper<GrupoAcessoCampo, GrupoAcessoVO, GrupoAcessoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new GrupoAcessoCrudHelper();
		}
		return crudHelper;
	}
	
	@Autowired
	public void setFacade(GrupoAcessoFacade facade) {
		((GrupoAcessoCrudHelper) getCrudHelper()).setFacade(facade);
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		super.validate();
		if (getModel().getCodFuncionarios() == null || getModel().getCodFuncionarios().isEmpty()) {
			addFieldError(
					"codFuncionario",
					getText(ConstantesDEPI.ERRO_CAMPO_REQUERIDO,
							new String[] { getText(ConstantesDEPI.GrupoAcesso.LABEL_FUNCIONARIO) }));
		}
	}
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codCompanhia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.grupoacesso.cia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="siglaDepartamento", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.grupoacesso.departamento')})}"),
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formul\u00e1rio validado. Chamando m\u00e9todo para concluir a opera\u00e7\u00e3o.");
		return super.execute();
	}
	
}
