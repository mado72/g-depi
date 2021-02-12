package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.ParametroDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.ParametroDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ParametroDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Processa a ação de salvar do formulário Departamento
 * @author Marcelo Damasceno
 *
 */
@Controller
@Scope("request")
public class ParametroSalvarAction extends
		SalvarAction<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParametroSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient ParametroDepositoCrudHelper crudHelper;
	
	@Autowired
	protected void setFacade(ParametroDepositoFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new ParametroDepositoCrudHelper();
		}
		return crudHelper;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
//		FIXME Validar os campos 
//		if (CollectionUtils.isEmpty(getModel().getSiglaDepartamentos())) {
//			LOGGER.info("Departamento não informado.");
//			addFieldError(
//					"siglaDepartmentos",
//					getText(ConstantesDEPI.ERRO_CAMPO_REQUERIDO,
//							new String[]{ getText(LABEL_CADASTRO_DEPARTAMENTOCOMPANHIA_DEPARTAMENTO)}));
//		}
		super.validate();
	}

	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}

}
