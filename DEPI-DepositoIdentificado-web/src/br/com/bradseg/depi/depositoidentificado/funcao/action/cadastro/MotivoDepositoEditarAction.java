package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.form.cadastro.MotivoDepositoEditarForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesView;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import com.opensymphony.xwork2.Action;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class MotivoDepositoEditarAction extends FiltroAction<MotivoDepositoEditarForm> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoEditarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private MotivoDepositoEditarForm _model;
	
	@Autowired
	private MotivoDepositoFacade facade;
	
	@Override
	public MotivoDepositoEditarForm getModel() {
		if (sessionData.containsKey(MotivoDepositoEditarForm.NOME_FORM)) {
			_model = (MotivoDepositoEditarForm) sessionData.get(MotivoDepositoEditarForm.NOME_FORM);
		}
		else {
			this.novaInstanciaModel();
		}
		return _model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		_model = new MotivoDepositoEditarForm();
		sessionData.put(MotivoDepositoEditarForm.NOME_FORM, _model);
	}
	
	/**
	 * Apenas redireciona a saída para INPUT. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link Action#INPUT}
	 */
	public String novo() {
		sessionData.remove(MotivoDepositoEditarForm.NOME_FORM);
		
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
	
	public String salvar() {
		return SUCCESS;
	}
	
	public String voltar() {
		return SUCCESS;
	}
	
	private void preencherFormulario() {
		int codigo = Integer.parseInt(this.getModel().getCodigo());

		MotivoDepositoVO vo = new MotivoDepositoVO();
		vo.setCodigoMotivoDeposito(codigo);
		
		MotivoDepositoVO instancia = facade.obterPorChave(vo);
		
		getModel().setDetalhar(false);
		getModel().setDescricaoBasica(instancia.getDescricaoBasica());
		getModel().setDescricaoDetalhada(instancia.getDescricaoDetalhada());
	}
	
	
	/**
	 * Processa os dados do filtro.
	 * 
	 * @return {@link Action#SUCCESS}, quando consegue realizar a consulta. Senão {@link Action#ERROR}
	 */
	public String consultar() {
		try {
			processarFiltro();
			
			return SUCCESS;
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Falha na consulta", e);
			addActionError(e.getMessage());
			
			return ERROR;
		}
		
	}
	
	public String incluir() {
		return SUCCESS;
	}
	
	private void processarFiltro() {
/*
        CriterioFiltroUtil filtro = new CriterioFiltroUtil();
        List<CriterioFiltroUtil> listCriterios = new ArrayList<CriterioFiltroUtil>();

        for (int i = 0; i < model.getCampo().size(); i++) {
            CriterioFiltroUtil criterio = new CriterioFiltroUtil();
            criterio.setCampo(MotivoDepositoCampo.obterPorNome(model.getCampo().get(i)));
            criterio.setOperacao(TipoOperacao.obterPorCodigo(model.getOperacao().get(i)));
            criterio.setValor(model.getValor().get(i));
            listCriterios.add(criterio);
        }
        listCriterios.add(CriterioFiltroUtil.getDefaultCriterioAtivo());
        filtro.setCriterios(listCriterios);
*/

        for (int i = 0; i < getModel().getCampo().size(); i++) {
        	
        }

		List<MotivoDepositoVO> retorno = facade.obterTodosMotivoDepositvo();
		
		getModel().setColecaoDados(retorno);
		
		
/*		
		DepiObjectMapper mapper = new DepiObjectMapper();
		try {
			for (Iterator<MotivoDepositoVO> iterator = retorno.iterator(); iterator.hasNext();) {
				MotivoDepositoVO item = (MotivoDepositoVO) iterator.next();
				if ("N".equals(item.getIndicadorAtivo())) {
					iterator.remove();
				}
			}
			String json = mapper.writeValueAsString(retorno);
			request.setAttribute("json", json);
		} catch (JsonProcessingException e) {
		}
*/		
		if (retorno == null || retorno.isEmpty()) {
			String message = super.getText(ConstantesView.MSG_CONSULTA_RETORNO_VAZIO);
			addActionMessage(message);
		}
	}

}
