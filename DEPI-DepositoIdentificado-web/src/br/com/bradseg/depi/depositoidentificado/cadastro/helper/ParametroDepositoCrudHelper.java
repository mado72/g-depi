package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.ParametroDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ParametroDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de motivo depósito.
 * 
 * @author Marcelo Damasceno
 */
public class ParametroDepositoCrudHelper implements
		CrudHelper<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> {

	private static final String TITLE_DEPOSITO_CONSULTAR = "title.deposito.consultar";

	private static final String TITLE_DEPOSITO_LISTAR = "title.deposito.listar";
	
	private static final String TITLE_DEPOSITO_ALTERAR = "title.deposito.editar";
	
	private static final String TITLE_DEPOSITO_DETALHAR = "title.deposito.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.deposito.novo";

	private transient ParametroDepositoFacade facade;

	public ParametroDepositoFacade getFacade() {
		return facade;
	}
	
	public void setFacade(ParametroDepositoFacade facade) {
		this.facade = facade;
	}
	
	// Métodos para filtrar

	@Override
	public String getChaveTituloConsultar() {
		return TITLE_DEPOSITO_CONSULTAR;
	}

	@Override
	public String getChaveTituloListar() {
		return TITLE_DEPOSITO_LISTAR;
	}

	@Override
	public FiltroConsultarForm<ParametroDepositoCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<ParametroDepositoCampo>> criterios = new FornecedorObjeto<Collection<ParametroDepositoCampo>>() {

			@Override
			public Collection<ParametroDepositoCampo> get() {
				return ParametroDepositoCampo.valuesForCriteria();
			}

		};

		Funcao<String, ParametroDepositoCampo> obterEntidadeCampo = new Funcao<String, ParametroDepositoCampo>() {

			@Override
			public ParametroDepositoCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return ParametroDepositoCampo.valueOf(source);
			}
		};

		return new FiltroConsultarForm<ParametroDepositoCampo>(criterios,
				obterEntidadeCampo);
	}

	@Override
	public List<ParametroDepositoVO> processarCriterios(
			List<CriterioConsultaVO<ParametroDepositoCampo>> criterios) {

		List<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(
				criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltro(filtro);
	}
	
	// Métodos para atender ao CRUD

	@Override
	public String getChaveTituloDetalhar() {
		return TITLE_DEPOSITO_DETALHAR;
	}

	@Override
	public String getChaveTituloAlterar() {
		return TITLE_DEPOSITO_ALTERAR;
	}

	@Override
	public String getChaveTituloIncluir() {
		return TITLE_DEPOSITO_INCLUIR;
	}

	@Override
	public ParametroDepositoEditarFormModel criarCrudModel() {
		return new ParametroDepositoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(ParametroDepositoEditarFormModel model)
			throws DEPIIntegrationException {

		// FIXME Obter pela chave primária e preencher os campos
		ParametroDepositoVO instancia = obterPeloCodigo(1);
//		model.setCodigo(String.valueOf(instancia.getCodigoMotivoDeposito()));
	}

	private ParametroDepositoVO obterPeloCodigo(int codigo) {
		ParametroDepositoVO vo = new ParametroDepositoVO();
		// FIXME definir método para obter pela chave primária
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(
			ParametroDepositoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		ParametroDepositoVO instancia;

		if (novo) {
			instancia = new ParametroDepositoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			// FIXME Obter pela chave primária
			instancia = obterPeloCodigo(1);
		}
		
		// FIXME Preencher os campos
		/*
		instancia.setDescricaoBasica(model.getDescricaoBasica());
		instancia.setDescricaoDetalhada(model.getDescricaoDetalhada());
		instancia.setCodigoEventoContabil(CODIGO_EVENTO_CONTABIL);
		instancia.setCodigoItemContabil(CODIGO_ITEM_CONTABIL);
		*/
		
		try {
			if (novo) {
				facade.inserir(instancia);
				return EstadoRegistro.NOVO;
			}
			else {
				facade.alterar(instancia);
				return EstadoRegistro.PERSISTIDO;
			}
		} catch (Exception e) {
			throw new DEPIIntegrationException(e, ConstantesDEPI.ERRO_INTERNO);
		}
	}

	@Override
	public void excluirRegistros(List<ParametroDepositoVO> voList)
			throws DEPIIntegrationException {
		// FIXME Criar exclusão por listta
		// facade.excluirLista(voList);
	}
	
	@Override
	public ParametroDepositoVO obterPorChave(ParametroDepositoVO vo) {
		return facade.obterPorChave(vo);
	}

}
