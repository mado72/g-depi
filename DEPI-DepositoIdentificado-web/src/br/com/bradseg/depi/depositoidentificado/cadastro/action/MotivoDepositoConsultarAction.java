package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("request")
public class MotivoDepositoConsultarAction extends FiltroAction<FiltroConsultarForm<MotivoDepositoCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private static final String TITLE_DEPOSITO_CONSULTAR = "title.deposito.consultar";
	
	private static final String TITLE_DEPOSITO_LISTAR = "title.deposito.listar";
	
	private static final String NOME_ACTION = MotivoDepositoConsultarAction.class.getSimpleName();
	
	private transient FiltroConsultarForm<MotivoDepositoCampo> model;
	
	@Autowired
	private transient MotivoDepositoFacade facade;
	
	@Override
	@SuppressWarnings("unchecked")
	public FiltroConsultarForm<MotivoDepositoCampo> getModel() {
		if (sessionData.containsKey(NOME_ACTION)) {
			model = (FiltroConsultarForm<MotivoDepositoCampo>) sessionData.get(NOME_ACTION);
		}
		else {
			this.novaInstanciaModel();
		}
		return model;
	}
	
	@Override
	protected void novaInstanciaModel() {

		FornecedorObjeto<Collection<MotivoDepositoCampo>> criterios = new FornecedorObjeto<Collection<MotivoDepositoCampo>>() {
			
			@Override
			public Collection<MotivoDepositoCampo> get() {
				return MotivoDepositoCampo.valuesForCriteria();
			}
			
		};
		
		Funcao<String, IEntidadeCampo> obterEntidadeCampo = new Funcao<String, IEntidadeCampo>() {

			@Override
			public IEntidadeCampo apply(String source) {
				return MotivoDepositoCampo.obterPorDescricao(source);
			}
		};
		
		model = new FiltroConsultarForm<MotivoDepositoCampo>(criterios, obterEntidadeCampo);
		sessionData.put(NOME_ACTION, model);
	}
	
	@Override
	public String iniciarFormulario() {
		setSubtituloChave(TITLE_DEPOSITO_CONSULTAR);
		return super.iniciarFormulario();
	}
	
	/**
	 * Apenas redireciona a saída para SUCCESS. Usado para apresentar o
	 * formulário armazenado na sessão.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#SUCCESS}
	 */
	public String execute() {
		setSubtituloChave(TITLE_DEPOSITO_CONSULTAR);
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
			setSubtituloChave(TITLE_DEPOSITO_LISTAR);
			
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
		String[] criterioArray = request.getParameterValues("criterio");
		processarCriterios(Arrays.asList(criterioArray));
	}
	
	@Override
	protected void processarCriterios(Collection<String> criterioColecao) {
		List<CriterioConsultaVO> criterios = new ArrayList<>(model.preencherCriterios(criterioColecao));
		criterios.add(new CriterioConsultaVO("CIND_REG_ATIVO = :OPT1", "OPT1", ConstantesDEPI.SIM));
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(criterios);

		List<MotivoDepositoVO> retorno = facade.obterPorFiltroMotivoDepositvo(filtro);
		
		FiltroConsultarForm<MotivoDepositoCampo> model = getModel();
		model.setColecaoDados(retorno);

		if (retorno == null || retorno.isEmpty()) {
			String message = super.getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
			addActionMessage(message);
		}
	}

}
