package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.form.cadastro.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

import com.opensymphony.xwork2.Action;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class DepartamentoConsultarAction extends FiltroAction<FiltroConsultarForm<DepartamentoCampo>> {
	
    protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private static final String ACTION_NAME = DepartamentoConsultarAction.class.getSimpleName();
	
	private FiltroConsultarForm<DepartamentoCampo> _model;
	
	@Autowired
	private DepartamentoFacade facade;
	
	@SuppressWarnings("unchecked")
	@Override
	public FiltroConsultarForm<DepartamentoCampo> getModel() {
		if (sessionData.containsKey(ACTION_NAME)) {
			_model = (FiltroConsultarForm<DepartamentoCampo>) sessionData.get(ACTION_NAME);
		}
		else {
			this.novaInstanciaModel();
		}
		return _model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		FornecedorObjeto<Collection<DepartamentoCampo>> entidades = new FornecedorObjeto<Collection<DepartamentoCampo>>() {
			
			@Override
			public Collection<DepartamentoCampo> get() {
				return Arrays.asList(DepartamentoCampo.valuesForCriteria());
			}
		};
		
		_model = new FiltroConsultarForm<>(entidades);
		sessionData.put(ACTION_NAME, _model);
	}
	
	/**
	 * Apenas redireciona a saída para SUCCESS. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link Action#SUCCESS}
	 */
	public String execute() {
		return SUCCESS;
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
		return "incluirAcao";
	}
	
	private void processarFiltro() {
/*
		FIXME Tirar este comentário e implementar método para preparar o filtro da consulta. Substituir
		facade.obterTodosMotivoDepositvo() por outra consulta com filtro.

        CriterioFiltroUtil filtro = new CriterioFiltroUtil();
        List<CriterioFiltroUtil> listCriterios = new ArrayList<CriterioFiltroUtil>();

        for (int i = 0; i < model.getCampo().size(); i++) {
            CriterioFiltroUtil criterio = new CriterioFiltroUtil();
            criterio.setCampo(DepartamentoCampo.obterPorNome(model.getCampo().get(i)));
            criterio.setOperacao(TipoOperacao.obterPorCodigo(model.getOperacao().get(i)));
            criterio.setValor(model.getValor().get(i));
            listCriterios.add(criterio);
        }
        listCriterios.add(CriterioFiltroUtil.getDefaultCriterioAtivo());
        filtro.setCriterios(listCriterios);
*/
		
		List<DepartamentoVO> retorno = facade.obterTodos();
		
		getModel().setColecaoDados(retorno);

		if (retorno == null || retorno.isEmpty()) {
			String message = super.getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
			addActionMessage(message);
		}
	}

}
