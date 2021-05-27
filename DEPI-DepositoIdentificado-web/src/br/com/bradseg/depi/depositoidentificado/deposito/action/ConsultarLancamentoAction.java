package br.com.bradseg.depi.depositoidentificado.deposito.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.ConsultarLancamentoFormModel;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class ConsultarLancamentoAction
		extends BaseModelAction<ConsultarLancamentoFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ConsultarLancamentoAction.class);
	
	private final ConsultarLancamentoFormModel model = new ConsultarLancamentoFormModel();
	
	private transient DepositoFacade facade;
	
	@Override
	public ConsultarLancamentoFormModel getModel() {
		return model;
	}
	
	@Autowired
	public void setFacade(DepositoFacade facade) {
		this.facade = facade;
	}
	
	public void validateConsultar() {
		clearActionErrors();
		clearFieldErrors();
		
		if (StringUtils.isEmpty(model.getCodigo())) {
			addFieldError("codigo", getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{"codigo"}));
		}
	}
	
	public String consultar() {
		model.setSubtitulo("Dep\u00F3sito - Consultar Lan\u00E7amento.");
		
		try {
			DepositoVO vo = facade.obterPorChave(
					new DepositoVO(Long.parseLong(model.getCodigo())),
					getCodUsuarioLogado(), getIp());

			model.preencherDeposito(vo);
			
			LancamentoDepositoVO lancamento = facade.obterLancamentoDeposito(vo);
			
			if (lancamento == null) {
				addActionError(getText("msg.consulta.lancamento.vazio"));
				return ERROR;
			}
			
			model.preencherDeposito(vo);
			model.preencherLancamento(lancamento);
			
			return INPUT;
		} catch (Exception e) {
			LOGGER.error("Erro ao consultar lan\u00e7amento.", e);
			addActionError(e.getMessage());
			return ERROR;
		}
	}
	
}
