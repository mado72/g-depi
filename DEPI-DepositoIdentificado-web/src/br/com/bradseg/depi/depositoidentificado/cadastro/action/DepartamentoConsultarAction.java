package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

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
	
	private static final String TITLE_DEPARTAMENTO_CONSULTAR = "title.departamento.consultar";
	
	private static final String TITLE_DEPARTAMENTO_LISTAR = "title.departamento.listar";
	
	private static final String NOME_ACTION = MotivoDepositoConsultarAction.class.getSimpleName();
	
	private FiltroConsultarForm<DepartamentoCampo> model;
	
	@Autowired
	private transient DepartamentoFacade facade;
	
	@SuppressWarnings("unchecked")
	@Override
	public FiltroConsultarForm<DepartamentoCampo> getModel() {
		if (sessionData.containsKey(NOME_ACTION)) {
			model = (FiltroConsultarForm<DepartamentoCampo>) sessionData.get(NOME_ACTION);
		}
		else {
			this.novaInstanciaModel();
		}
		return model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		FornecedorObjeto<Collection<DepartamentoCampo>> fornecedor = new FornecedorObjeto<Collection<DepartamentoCampo>>() {
			
			@Override
			public Collection<DepartamentoCampo> get() {
				return DepartamentoCampo.valuesForCriteria();
			}
			
		};
		
		Funcao<String, IEntidadeCampo> obterEntidade = new Funcao<String, IEntidadeCampo>() {
			
			@Override
			public IEntidadeCampo apply(String source) {
				return DepartamentoCampo.obterPorNome(source);
			}
		};
		
		model = new FiltroConsultarForm<>(fornecedor, obterEntidade);
		sessionData.put(NOME_ACTION, model);
	}
	
	@Override
	public String iniciarFormulario() {
		setSubtituloChave(TITLE_DEPARTAMENTO_CONSULTAR);
		return super.iniciarFormulario();
	}
	
	/**
	 * Apenas redireciona a saída para SUCCESS. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String execute() {
		setSubtituloChave(TITLE_DEPARTAMENTO_CONSULTAR);
		return SUCCESS;
	}
	
	/**
	 * Processa os dados do filtro.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}, quando consegue realizar a consulta. Senão {@link com.opensymphony.xwork2.Action#ERROR}
	 */
	public String consultar() {
		try {
			processarFiltro();
			setSubtituloChave(TITLE_DEPARTAMENTO_LISTAR);
			
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
		FiltroConsultarForm<DepartamentoCampo> model = getModel();
		
		String[] criterioArray = request.getParameterValues("criterio");
		List<CriterioConsultaVO> criterios = model.preencherCriterios(criterioArray);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(criterios);
		
		List<DepartamentoVO> retorno = facade.obterPorFiltro(filtro);
		
		model.setColecaoDados(retorno);

		if (retorno == null || retorno.isEmpty()) {
			String message = super.getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
			addActionMessage(message);
		}
	}

}
