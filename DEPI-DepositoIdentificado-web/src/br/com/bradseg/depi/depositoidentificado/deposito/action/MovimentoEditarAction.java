package br.com.bradseg.depi.depositoidentificado.deposito.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.MovimentoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MovimentoAcao;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
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
	
	private transient DepositoFacade facade;
	
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
			model.setSalvarAction("salvarIncluir");
		}
		else {
			model.preencherMovimento(movimento);
			model.setSalvarAction("salvarAlterar");
		}
		
		return INPUT;
	}
	
	public String incluir() {
		try {
			int codUsuario = getCodUsuarioLogado();
			
			MovimentoDepositoVO vo = model.obterMovimento();
			vo.setCodigoResponsavelUltimaAtualizacao(codUsuario);
			
			facade.inserirMovimento(vo, getIp());
			
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
	public void validateSalvarAlterar() {
		validateSalvar();
	}
	
	public void validateSalvarIncluir() {
		validateSalvar();
	}
	
	private void validateSalvar() {
		MovimentoAcao acao = MovimentoAcao.obterPorCodigo(model.getCodMovimentoAcao());
		
		switch (acao) {
		case ACEITE:
			break;
		case RECEITA:
		case TRANSFERENCIA:
			validarBanco();
			break;
		case DEVOLUCAO:
			validarBanco();
			validarNroCheque();
			break;
		default:
			break;
		}
	}
	
	public String salvarAlterar() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		
		MovimentoDepositoVO vo = model.obterMovimento();
		
		try {
			facade.alterarMovimento(vo);
			addActionMessage(getText(ConstantesDEPI.MSG_ALTERAR_EXITO));
			
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Falha ao salvar movimento.", e);
			addActionError(e.getMessage());
			return INPUT;
		}
	}

	public String salvarIncluir() {
		LOGGER.info("Formulário validado. Chamando método para concluir a operação.");
		
		MovimentoDepositoVO vo = model.obterMovimento();
		vo.setCodigoResponsavelUltimaAtualizacao(getCodUsuarioLogado());
		
		try {
			facade.inserirMovimento(vo, getIp());
			addActionMessage(getText(ConstantesDEPI.MSG_INSERIR_EXITO));
			
			return SUCCESS;
		} catch (Exception e) {
			LOGGER.error("Falha ao salvar movimento.", e);
			addActionError(e.getMessage());
			return INPUT;
		}
	}

	private void validarBanco() {
		validarLong("Banco", model.getBancoMovimento());
		validarLong("Ag\u00EAngia", model.getAgenciaMovimento());
		validarLong("Conta", model.getContaMovimento());
	}

	private void validarNroCheque() {
		validarLong("Cheque N\u00FAmero", model.getChequeMovimento());
	}

	private void validarLong(String field, String valor) {
		if (StringUtils.isEmpty(valor)) {
			addFieldError(field, getText("errors.required", new String[]{field}));
			return;
		}
		
		try {
			long l = Long.parseLong(valor);
			if (l == 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			addFieldError(field, getText("errors.invalid", new String[]{field}));
			return;
		}
	}
	
}
