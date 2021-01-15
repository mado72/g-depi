package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoEditarForm;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

import com.opensymphony.xwork2.Action;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class DepartamentoEditarAction extends BaseModelAction<DepartamentoEditarForm> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoEditarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private DepartamentoEditarForm _model;
	
	@Autowired
	private DepartamentoFacade facade;
	
	@Override
	public DepartamentoEditarForm getModel() {
		if (sessionData.containsKey(DepartamentoEditarForm.NOME_FORM)) {
			_model = (DepartamentoEditarForm) sessionData.get(DepartamentoEditarForm.NOME_FORM);
		}
		else {
			this.novaInstanciaModel();
		}
		return _model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		_model = new DepartamentoEditarForm();
		sessionData.put(DepartamentoEditarForm.NOME_FORM, _model);
	}
	
	/**
	 * Apenas redireciona a saída para INPUT. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link Action#INPUT}
	 */
	public String novo() {
		sessionData.remove(DepartamentoEditarForm.NOME_FORM);
		
		novaInstanciaModel();
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
		DepartamentoEditarForm model = getModel();
		
		if ("salvar".equals(model.getAcao())) {
			persistirDados(model);
		}

		return SUCCESS;
	}

	private void persistirDados(DepartamentoEditarForm model) {
		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();

		DepartamentoVO instancia;
		
		if (novo) {
			instancia = new DepartamentoVO();
		}
		else {
			instancia = obterPeloCodigo();
		}

		instancia.setSiglaDepartamento(model.getSiglaDepartamento());
		instancia.setNomeDepartamento(model.getNomeDepartamento());

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
		DepartamentoVO instancia = obterPeloCodigo();
		
		DepartamentoEditarForm model = getModel();
		
		model.setDetalhar(false);
		model.setCodigo(String.valueOf(instancia.getCodigoDepartamento()));
		model.setSiglaDepartamento(instancia.getSiglaDepartamento());
		model.setNomeDepartamento(instancia.getNomeDepartamento());
	}

	private DepartamentoVO obterPeloCodigo() {
		int codigo = Integer.parseInt(this.getModel().getCodigo());

		DepartamentoVO vo = new DepartamentoVO();
		vo.setCodigoDepartamento(codigo);
		
		DepartamentoVO instancia = facade.obterPorChave(vo);
		return instancia;
	}
	
}
