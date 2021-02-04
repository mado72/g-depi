/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.GrupoAcessoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.GrupoAcessoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.GrupoAcessoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

/**
 * Ação para salvar dados do formulário Grupo Acesso.
 * 
 * @author Marcelo Damasceno
 */
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
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="cia.codigoCompanhia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.grupoacesso.cia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="depto.codigoDepartamento", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.grupoacesso.departamento')})}")
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}
	
}
