package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.MotivoDepositoEditarForm;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class MotivoDepositoEditarAction extends BaseModelAction<MotivoDepositoEditarForm> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoEditarAction.class);
	
	private static final String TITLE_DEPOSITO_EDITAR = "title.deposito.editar";
	
	private static final String TITLE_DEPOSITO_NOVO = "title.deposito.novo";
    
    private static final String ACTION_NAME = MotivoDepositoEditarAction.class.getSimpleName() + "_FORM";

	private static final long serialVersionUID = -7675543657126275320L;
	
	/**
     * código do evento contábil.
     */
	private static final int CODIGO_EVENTO_CONTABIL = 361;

	/**
     * código do evento contábil.
     */
	private static final int CODIGO_ITEM_CONTABIL = 472;

	
	private MotivoDepositoEditarForm model;
	
	@Autowired
	private transient MotivoDepositoFacade facade;
	
	@Override
	public MotivoDepositoEditarForm getModel() {
		if (sessionData.containsKey(ACTION_NAME)) {
			model = (MotivoDepositoEditarForm) sessionData.get(ACTION_NAME);
		}
		else {
			this.novaInstanciaModel();
		}
		return model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		model = new MotivoDepositoEditarForm();
		sessionData.put(ACTION_NAME, model);
	}
	
	public String exibir() {
		return INPUT;
	}
	
	/**
	 * Apenas redireciona a saída para INPUT. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String novo() {
		getModel().setDetalhar(false);
		
		setSubtituloChave(TITLE_DEPOSITO_NOVO);
		
		return INPUT;
	}
	
	public String alterar() {
		preencherFormulario();
		
		setSubtituloChave(TITLE_DEPOSITO_EDITAR);
		
		return INPUT;
	}
	
	public String detalhar() {
		preencherFormulario();
		
		getModel().setDetalhar(true);
		return INPUT;
	}
	
	/**
	 * Processa o envio do formulário. Processa o parâmetro <code>acao</code>
	 * para identificar se é para salvar (inclusão ou alteração), cancelar ou
	 * voltar.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS} quando for processado corretamente.
	 */
	public String enviar() {
		MotivoDepositoEditarForm model = getModel();
		
		if ("salvar".equals(model.getAcao())) {
			persistirDados();
		}
		
		return SUCCESS;
	}
	
	public String excluir() {
		excluirRegistros();
		
		return SUCCESS;
	}

	private void persistirDados() {
		MotivoDepositoEditarForm model = getModel();
		
		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
	
		MotivoDepositoVO instancia;
		
		if (novo) {
			LoginVo usuarioLogado = getUsuarioLogado();
			instancia = new MotivoDepositoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			instancia = obterPeloCodigo();
		}
	
		instancia.setDescricaoBasica(model.getDescricaoBasica());
		instancia.setDescricaoDetalhada(model.getDescricaoDetalhada());
		instancia.setCodigoEventoContabil(CODIGO_EVENTO_CONTABIL);
		instancia.setCodigoItemContabil(CODIGO_ITEM_CONTABIL);
	
		try {
			if (novo) {
				facade.inserir(instancia);
				addActionMessage("msg.inserir.sucesso");
			}
			else {
				facade.alterar(instancia);
				addActionMessage("msg.alterar.sucesso");
			}
		} catch (Exception e) {
			throw new DEPIIntegrationException(e, "msg.erro.interno");
		}
	}

	private void excluirRegistros() {
		String[] codigos = request.getParameterValues("codigo");
		
		List<MotivoDepositoVO> lista = new ArrayList<>();
		
		for (String codigo : codigos) {
			MotivoDepositoVO vo = new MotivoDepositoVO();
			vo.setCodigoMotivoDeposito(new Integer(codigo));
			
			vo = facade.obterPorChave(vo);
			lista.add(vo);
		}
		
		facade.excluirLista(lista);
		addActionMessage("msg.excluir.sucesso");
	}

	public String voltar() {
		return SUCCESS;
	}
	
	private void preencherFormulario() {
		MotivoDepositoVO instancia = obterPeloCodigo();
		
		MotivoDepositoEditarForm model = getModel();
		
		model.setDetalhar(false);
		model.setCodigo(String.valueOf(instancia.getCodigoMotivoDeposito()));
		model.setDescricaoBasica(instancia.getDescricaoBasica());
		model.setDescricaoDetalhada(instancia.getDescricaoDetalhada());
		
		clearActionErrors();
	}

	private MotivoDepositoVO obterPeloCodigo() {
		int codigo = Integer.parseInt(this.getModel().getCodigo());

		MotivoDepositoVO vo = new MotivoDepositoVO();
		vo.setCodigoMotivoDeposito(codigo);
		
		MotivoDepositoVO instancia = facade.obterPorChave(vo);
		return instancia;
	}
	
}
