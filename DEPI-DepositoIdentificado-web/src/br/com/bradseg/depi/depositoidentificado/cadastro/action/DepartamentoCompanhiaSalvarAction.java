package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoCompanhiaEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCompanhiaCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCompanhiaCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;

/**
 * Processa a ação de salvar do formulário Departamento
 * @author Marcelo Damasceno
 *
 */
@Controller
@Scope("request")
public class DepartamentoCompanhiaSalvarAction extends
		SalvarAction<DepartamentoCompanhiaCampo, DepartamentoCompanhiaVO, DepartamentoCompanhiaEditarFormModel> {
	
	private static final String LABEL_CADASTRO_DEPARTAMENTOCOMPANHIA_DEPARTAMENTO = "label.cadastro.departamentocompanhia.departamento";

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoCompanhiaSalvarAction.class);

	private static final long serialVersionUID = -3923052243969907744L;
	
	private transient DepartamentoCompanhiaCrudHelper crudHelper;
	
	@Autowired
	protected void setFacade(DepartamentoCompanhiaFacade facade) {
		crudHelper.setFacade(facade);
	}
	
	@Override
	protected CrudHelper<DepartamentoCompanhiaCampo, DepartamentoCompanhiaVO, DepartamentoCompanhiaEditarFormModel> getCrudHelper() {
		if (crudHelper == null) {
			crudHelper = new DepartamentoCompanhiaCrudHelper();
		}
		return crudHelper;
	}
	
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#validate()
	 */
	@Override
	public void validate() {
		if (CollectionUtils.isEmpty(getModel().getCodDepartamentos())) {
			addFieldError(
					"codDepartmentos",
					getText(ConstantesDEPI.ERRO_CAMPO_REQUERIDO,
							new String[]{ getText(LABEL_CADASTRO_DEPARTAMENTOCOMPANHIA_DEPARTAMENTO)}));
		}
		super.validate();
	}

	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}

}
