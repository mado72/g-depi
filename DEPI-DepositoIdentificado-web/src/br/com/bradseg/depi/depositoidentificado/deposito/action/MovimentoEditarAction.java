package br.com.bradseg.depi.depositoidentificado.deposito.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.MovimentoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MovimentoAcao;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class MovimentoEditarAction
		extends BaseModelAction<MovimentoEditarFormModel> {

	private static final long serialVersionUID = -7675543657126275320L;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MovimentoEditarAction.class);
	
	private final MovimentoEditarFormModel model = new MovimentoEditarFormModel();
	
	private DepositoFacade facade;
	
	@Override
	public MovimentoEditarFormModel getModel() {
		return model;
	}
	
	@Autowired
	public void setFacade(DepositoFacade facade) {
		this.facade = facade;
	}
	
	public String manter() {
		model.setSubtitulo("Dep\u00F3sito - Manter Movimento");
		
		DepositoVO vo = facade.obterPorChave(
				new DepositoVO(Long.parseLong(model.getCodigo())),
				getCodUsuarioLogado(), getIp());

		model.preencherDeposito(vo);
		
		MovimentoDepositoVO movimento = facade.obterMovimentoDeposito(vo);

		if (movimento == null) {
			model.setCodMovimentoAcao(MovimentoAcao.ACEITE.getCodigo());
			
			MovimentoDepositoVO chaveMovimento = new MovimentoDepositoVO(
					vo.getCodigoDepositoIdentificado());
			
			model.preencherMovimento(chaveMovimento);
			model.setDataMovimento("Sem Movimento");
			model.setSalvarAction("incluir");
		}
		else {
			model.preencherMovimento(movimento);
			model.setSalvarAction("alterar");
		}
		
		return INPUT;
	}
	
	public String incluir() {
		try {
			int codUsuario = getCodUsuarioLogado();
			
			MovimentoDepositoVO vo = model.obterMovimento();
			vo.setCodigoResponsavelUltimaAtualizacao(codUsuario);
			
			facade.inserirMovimento(vo);
			
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Falha ao incluir", e);
			addActionError(e.getMessage());
			return INPUT;
		}
	}
	
	public String alterar() {
		try {
			int codUsuario = getCodUsuarioLogado();
			
			MovimentoDepositoVO vo = model.obterMovimento();
			vo.setCodigoResponsavelUltimaAtualizacao(codUsuario);
			
			facade.alterarMovimento(vo);
			
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Falha ao atualizar", e);
			addActionError(e.getMessage());
			return INPUT;
		}
	}
	
}
