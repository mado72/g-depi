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
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

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
public class ContaCorrenteSalvarAction extends
		SalvarAction<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> {
	
	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_APOLICE = "label.cadastro.parametrodeposito.apolice";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_BAIXA = "label.cadastro.parametrodeposito.baixa";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_BLOQUETO = "label.cadastro.parametrodeposito.bloqueto";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_CPF = "label.cadastro.parametrodeposito.cpf";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_DOSSIE = "label.cadastro.parametrodeposito.dossie";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_ENDOSSO = "label.cadastro.parametrodeposito.endosso";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_ITEM = "label.cadastro.parametrodeposito.item";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_PARCELA = "label.cadastro.parametrodeposito.parcela";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_PROTOCOLO = "label.cadastro.parametrodeposito.protocolo";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_RAMO = "label.cadastro.parametrodeposito.ramo";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_SUCURSAL = "label.cadastro.parametrodeposito.sucursal";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_TIPO = "label.cadastro.parametrodeposito.tipo";

	private static final String LABEL_CADASTRO_PARAMETRODEPOSITO_DIAS = "label.cadastro.parametrodeposito.dias";

	private static final String ERRORS_REQUIRED = "errors.required";

	private static final String ERRORS_INVALID = "errors.invalid";

	private static final Logger LOGGER = LoggerFactory.getLogger(ContaCorrenteSalvarAction.class);

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
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.SalvarAction#validateExecute()
	 */
	@Override
	public void validateExecute() {
		clearErrors();
		
		ParametroDepositoEditarFormModel model = getModel();
		
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_APOLICE, model.getCodigoApolice());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_BAIXA, model.getCodigoBancoVencimento());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_BLOQUETO, model.getCodigoBloqueto());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_CPF, model.getCodigoCpfCnpj());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_DOSSIE, model.getCodigoDossie());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_ENDOSSO, model.getCodigoEndosso());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_ITEM, model.getCodigoItem());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_PARCELA, model.getCodigoParcela());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_PROTOCOLO, model.getCodigoProtocolo());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_RAMO, model.getCodigoRamo());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_SUCURSAL, model.getCodigoSucursal());
		validaSN(LABEL_CADASTRO_PARAMETRODEPOSITO_TIPO, model.getCodigoTipo());
		
		if ("S".equals(model.getCodigoBancoVencimento())) {
			if (model.getNumeroDiasAposVencimento() == null) {
				addFieldError(LABEL_CADASTRO_PARAMETRODEPOSITO_DIAS, ERRORS_REQUIRED);
			}
			else {
				String label = getText(LABEL_CADASTRO_PARAMETRODEPOSITO_DIAS);
				try {
					int v = Integer.parseInt(model.getNumeroDiasAposVencimento());
					if (v < 1) {
						addFieldError(label, getText(ERRORS_INVALID, new String[]{label}));
					}
				}
				catch (Exception e) {
					addFieldError(label, getText(ERRORS_INVALID, new String[]{label}));
				}
			}
		}
	}
	
	private void validaSN(String campo, String valor) {
		if (! BaseUtil.isSorN(valor)) {
			String label = getText(campo);
			addFieldError(label, getText(ERRORS_REQUIRED, new String[]{label}));
		}
	}
	
	@Validations(
			requiredStrings={
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoCompanhia", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.departamentocompanhia.companhia')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoDepartamento", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.departamento.nome')})}"),
					@RequiredStringValidator(type= ValidatorType.SIMPLE, fieldName="codigoMotivoDeposito", message="${getText('errors.required', new java.lang.String[] {getText('label.cadastro.deposito')})}")
			}
		)
	@Override
	public String execute() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		return super.execute();
	}
}
