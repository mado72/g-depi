package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.MotivoDepositoCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.ERRO_GERAL;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class MotivoDepositoConsultarAction extends FiltroAction<MotivoDepositoCampo, FiltroConsultarForm<MotivoDepositoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;

	private static final String LABEL_DESCRICAO_BASICA = "%enum.MotivoDepositoCampo.DescricaoBasica%";

	private static final String LABEL_DESCRICAO_DETALHADA = "%enum.MotivoDepositoCampo.DescricaoDetalhada%";
	
	private static final String CAMPO_DESCRICAO_BASICA = "descricaoBasica";

	private static final String CAMPO_DESCRICAO_DETALHADA = "descricaoDetalhada";
	
	private transient MotivoDepositoCrudHelper filtroHelper;

	@Override
	protected CrudHelper<MotivoDepositoCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new MotivoDepositoCrudHelper();
		}
		return filtroHelper;
	}
	
	@Autowired
	protected void setFacade(MotivoDepositoFacade facade) {
		this.filtroHelper.setFacade(facade);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction#validarCriterio(br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO)
	 */
	@Override
	protected void validarCriterio(CriterioConsultaVO<MotivoDepositoCampo> criterio) {
		MotivoDepositoCampo campo = criterio.getCampo();
		String valor = criterio.getValor();
		
		switch (campo) {
		case DescricaoBasica: {
			if (valor == null || valor.isEmpty()) {
				addFieldError(CAMPO_DESCRICAO_BASICA, BaseUtil.getTextoFormatado(
						ConstantesDEPI.ERRO_GERAL.ERRORS_REQUIRED,
						LABEL_DESCRICAO_BASICA));
			}
			else if (valor.length() > 40){
				addFieldError(CAMPO_DESCRICAO_BASICA, BaseUtil.getTextoFormatado(
						ERRO_GERAL.ERRO_CAMPO_EXCESSO,
						LABEL_DESCRICAO_BASICA, "40"));
			}
			break;
		}
		case DescricaoDetalhada: {
			if (valor == null || valor.isEmpty()) {
				addFieldError(CAMPO_DESCRICAO_DETALHADA, BaseUtil.getTextoFormatado(
						ConstantesDEPI.ERRO_GERAL.ERRORS_REQUIRED,
						LABEL_DESCRICAO_DETALHADA));
			}
			else if (valor.length() > 200){
				addFieldError(CAMPO_DESCRICAO_DETALHADA, BaseUtil.getTextoFormatado(
						ERRO_GERAL.ERRO_CAMPO_EXCESSO,
						LABEL_DESCRICAO_DETALHADA, "200"));
			}
			
			break;
		}

		default:
			break;
		}
	}

}
