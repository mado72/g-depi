package br.com.bradseg.depi.depositoidentificado.funcao.action.cadastro;

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
import br.com.bradseg.depi.depositoidentificado.form.cadastro.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesView;
import br.com.bradseg.depi.depositoidentificado.util.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import com.opensymphony.xwork2.Action;

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
	
	private static final String NOME_ACTION = MotivoDepositoConsultarAction.class.getSimpleName();
	
	private FiltroConsultarForm<MotivoDepositoCampo> _model;
	
	@Autowired
	private MotivoDepositoFacade facade;
	
	@Override
	@SuppressWarnings("unchecked")
	public FiltroConsultarForm<MotivoDepositoCampo> getModel() {
		if (sessionData.containsKey(NOME_ACTION)) {
			_model = (FiltroConsultarForm<MotivoDepositoCampo>) sessionData.get(NOME_ACTION);
		}
		else {
			this.novaInstanciaModel();
		}
		return _model;
	}
	
	@Override
	protected void novaInstanciaModel() {
		FornecedorObjeto<Collection<MotivoDepositoCampo>> criterios = new FornecedorObjeto<Collection<MotivoDepositoCampo>>() {
			@Override
			public Collection<MotivoDepositoCampo> get() {
				return Arrays.asList(MotivoDepositoCampo.valuesForCriteria());
			}
		};
		_model = new FiltroConsultarForm<MotivoDepositoCampo>(criterios);
		sessionData.put(NOME_ACTION, _model);
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
		FiltroConsultarForm<MotivoDepositoCampo> model = getModel();
		
		List<CriterioConsultaVO> criterios = new ArrayList<>();
		criterios.add(new CriterioConsultaVO("INDICADOR_ATIVO = :param0", "param0", ConstantesDEPI.INDICADOR_ATIVO));
		
		if (model.getCriterios() != null) {
			int paramIdx = 0;
			
			for (String item: model.getCriterios()) {
				String[] criterioFiltro = item.split(";");
				MotivoDepositoCampo campo = MotivoDepositoCampo.obterPorDescricao(criterioFiltro[0]);
				TipoOperacao operacao = TipoOperacao.valueOf(criterioFiltro[1]);
				String valor = criterioFiltro[2];
				
				String param = "param" + ++paramIdx;
				
				CriterioConsultaVO criterioConsultaVO = montarCriterioConsulta(
						campo, operacao, valor, param);
				criterios.add(criterioConsultaVO);
			}
		}
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(criterios);
		
		List<MotivoDepositoVO> retorno = facade.obterPorFiltroMotivoDepositvo(filtro);
		
		model.setColecaoDados(retorno);

		if (retorno == null || retorno.isEmpty()) {
			String message = super.getText(ConstantesView.MSG_CONSULTA_RETORNO_VAZIO);
			addActionMessage(message);
		}
	}

}
