package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.MotivoDepositoEditarForm;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

public class MotivoDepositoCrudHelper implements
		CrudHelper<MotivoDepositoCampo, MotivoDepositoVO> {

	private static final String TITLE_DEPOSITO_CONSULTAR = "title.deposito.consultar";

	private static final String TITLE_DEPOSITO_LISTAR = "title.deposito.listar";
	
	private static final String TITLE_DEPOSITO_ALTERAR = "title.deposito.editar";
	
	private static final String TITLE_DEPOSITO_DETALHAR = "title.deposito.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.deposito.novo";

	/**
     * código do evento contábil.
     */
	private static final int CODIGO_EVENTO_CONTABIL = 361;

	/**
     * código do evento contábil.
     */
	private static final int CODIGO_ITEM_CONTABIL = 472;

	private transient MotivoDepositoFacade facade;

	public MotivoDepositoFacade getFacade() {
		return facade;
	}
	
	public void setFacade(MotivoDepositoFacade facade) {
		this.facade = facade;
	}

	@Override
	public String getChaveTituloConsultar() {
		return TITLE_DEPOSITO_CONSULTAR;
	}

	@Override
	public String getChaveTituloListar() {
		return TITLE_DEPOSITO_LISTAR;
	}

	@Override
	public FiltroConsultarForm<MotivoDepositoCampo> criarFiltroModel() {
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

		return new FiltroConsultarForm<MotivoDepositoCampo>(criterios,
				obterEntidadeCampo);
	}

	@Override
	public List<MotivoDepositoVO> processarCriterios(
			List<CriterioConsultaVO> criterios) {
		criterios = new ArrayList<>(criterios);
		criterios.add(new CriterioConsultaVO("CIND_REG_ATIVO = :OPT1", "OPT1",
				ConstantesDEPI.SIM));
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(criterios);

		List<MotivoDepositoVO> retorno = facade.obterPorFiltroMotivoDepositvo(filtro);
		return retorno;
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
	public CrudForm criarCrudModel() {
		return new MotivoDepositoEditarForm();
	}

	@Override
	public void preencherFormularioEdicao(CrudForm model)
			throws DEPIIntegrationException {
		
		MotivoDepositoEditarForm form = (MotivoDepositoEditarForm) model;
		
		MotivoDepositoVO instancia = obterPeloCodigo(Integer.parseInt(form.getCodigo()));
		form.setCodigo(String.valueOf(instancia.getCodigoMotivoDeposito()));
		form.setDescricaoBasica(instancia.getDescricaoBasica());
		form.setDescricaoDetalhada(instancia.getDescricaoDetalhada());
	}

	private MotivoDepositoVO obterPeloCodigo(int codigo) {
		MotivoDepositoVO vo = new MotivoDepositoVO();
		vo.setCodigoMotivoDeposito(codigo);
		
		MotivoDepositoVO instancia = facade.obterPorChave(vo);
		return instancia;
	}

	@Override
	public EstadoRegistro persistirDados(
			CrudForm model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		MotivoDepositoEditarForm form = (MotivoDepositoEditarForm) model;

		boolean novo = form.getCodigo() == null || form.getCodigo().trim().isEmpty();
		
		MotivoDepositoVO instancia;

		if (novo) {
			instancia = new MotivoDepositoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			instancia = obterPeloCodigo(Integer.parseInt(form.getCodigo()));
		}
		
		instancia.setDescricaoBasica(form.getDescricaoBasica());
		instancia.setDescricaoDetalhada(form.getDescricaoDetalhada());
		instancia.setCodigoEventoContabil(CODIGO_EVENTO_CONTABIL);
		instancia.setCodigoItemContabil(CODIGO_ITEM_CONTABIL);
		
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
	public void excluirRegistros(List<MotivoDepositoVO> voList)
			throws DEPIIntegrationException {
		facade.excluirLista(voList);		
	}
	
	@Override
	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo) {
		return facade.obterPorChave(vo);
	}

}
