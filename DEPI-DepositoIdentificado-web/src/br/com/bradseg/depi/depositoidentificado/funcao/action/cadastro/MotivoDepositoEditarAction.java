package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.form.cadastro.MotivoDepositoEditarForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import com.opensymphony.xwork2.Action;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class MotivoDepositoEditarAction extends BaseModelAction<MotivoDepositoEditarForm> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoEditarAction.class);
    
    private static final String ACTION_NAME = MotivoDepositoEditarAction.class.getSimpleName() + "_FORM";

	private static final long serialVersionUID = -7675543657126275320L;
	
	private MotivoDepositoEditarForm _model;
	
	@Autowired
	private MotivoDepositoFacade facade;
	
	@Override
	public MotivoDepositoEditarForm getModel() {
		if (sessionData.containsKey(ACTION_NAME)) {
			_model = (MotivoDepositoEditarForm) sessionData.get(ACTION_NAME);
		}
		else {
			this.novaInstanciaModel();
		}
		return _model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		_model = new MotivoDepositoEditarForm();
		sessionData.put(ACTION_NAME, _model);
	}
	
	/**
	 * Apenas redireciona a saída para INPUT. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link Action#INPUT}
	 */
	public String novo() {
		clearData();
		
		getModel().setDetalhar(false);
		
		return INPUT;
	}
	
	public String alterar() {
		preencherFormulario();
		
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
	 * @return {@link Action#SUCCESS} quando for processado corretamente.
	 */
	public String enviar() {
		MotivoDepositoEditarForm model = getModel();
		
		if ("salvar".equals(model.getAcao())) {
			persistirDados(model);
		}

		return SUCCESS;
	}

	private void persistirDados(MotivoDepositoEditarForm model) {
		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();

		MotivoDepositoVO instancia;
		
		if (novo) {
			instancia = new MotivoDepositoVO();
		}
		else {
			instancia = obterPeloCodigo();
		}

		instancia.setDescricaoBasica(model.getDescricaoBasica());
		instancia.setDescricaoDetalhada(model.getDescricaoDetalhada());

		try {
			if (novo) {
				facade.inserir(instancia);
			}
			else {
				facade.alterar(instancia);
			}
		} catch (Exception e) {
			throw new DEPIIntegrationException(e, "msg.erro.interno");
		}
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
	}

	private MotivoDepositoVO obterPeloCodigo() {
		int codigo = Integer.parseInt(this.getModel().getCodigo());

		MotivoDepositoVO vo = new MotivoDepositoVO();
		vo.setCodigoMotivoDeposito(codigo);
		
		MotivoDepositoVO instancia = facade.obterPorChave(vo);
		return instancia;
	}
	
}
