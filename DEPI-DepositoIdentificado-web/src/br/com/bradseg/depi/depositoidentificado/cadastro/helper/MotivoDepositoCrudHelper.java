package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.MotivoDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.Fornecedor;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de motivo depósito.
 * 
 * @author Marcelo Damasceno
 */
public class MotivoDepositoCrudHelper implements
		CrudHelper<MotivoDepositoCampo, MotivoDepositoVO, MotivoDepositoEditarFormModel> {

	private static final String TITLE_DEPOSITO_CONSULTAR = "title.motivodeposito.consultar";

	private static final String TITLE_DEPOSITO_LISTAR = "title.motivodeposito.listar";
	
	private static final String TITLE_DEPOSITO_ALTERAR = "title.motivodeposito.editar";
	
	private static final String TITLE_DEPOSITO_DETALHAR = "title.motivodeposito.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.motivodeposito.novo";

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
	public FiltroConsultarForm<MotivoDepositoCampo> criarFiltroModel() {
		Fornecedor<Collection<MotivoDepositoCampo>> criterios = new Fornecedor<Collection<MotivoDepositoCampo>>() {

			@Override
			public Collection<MotivoDepositoCampo> get() {
				return MotivoDepositoCampo.valuesForCriteria();
			}

		};

		Funcao<String, MotivoDepositoCampo> obterEntidadeCampo = new Funcao<String, MotivoDepositoCampo>() {

			@Override
			public MotivoDepositoCampo apply(String source) {
				if (source == null || source.isEmpty()) {
					return null;
				}
				return MotivoDepositoCampo.valueOf(source);
			}
		};

		return new FiltroConsultarForm<MotivoDepositoCampo>(criterios,
				obterEntidadeCampo);
	}

	@Override
	public List<MotivoDepositoVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<MotivoDepositoCampo>> criterios) {
		
		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		aux.add(new CriterioConsultaVO<MotivoDepositoCampo>("CIND_REG_ATIVO = 'S'"));
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltroMotivoDepositvo(filtro);
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
	public MotivoDepositoEditarFormModel criarCrudModel() {
		return new MotivoDepositoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(MotivoDepositoEditarFormModel model, int codUsuario, String ipCliente)
			throws DEPIIntegrationException {
		
		MotivoDepositoVO instancia = obterPeloCodigo(Integer.parseInt(model.getCodigo()));
		model.setDescricaoBasica(instancia.getDescricaoBasica());
		model.setDescricaoDetalhada(instancia.getDescricaoDetalhada());
	}

	private MotivoDepositoVO obterPeloCodigo(int codigo) {
		MotivoDepositoVO vo = new MotivoDepositoVO();
		vo.setCodigoMotivoDeposito(codigo);
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(
			MotivoDepositoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		String codigo = model.getCodigo();
		boolean novo = codigo == null || codigo.isEmpty();
		
		MotivoDepositoVO instancia;

		if (novo) {
			model.setEstado(EstadoCrud.INSERIR);
			instancia = new MotivoDepositoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			model.setEstado(EstadoCrud.ALTERAR);
			instancia = obterPeloCodigo(Integer.parseInt(codigo));
		}
		
		instancia.setDescricaoBasica(model.getDescricaoBasica());
		instancia.setDescricaoDetalhada(model.getDescricaoDetalhada());
		instancia.setCodigoEventoContabil(CODIGO_EVENTO_CONTABIL);
		instancia.setCodigoItemContabil(CODIGO_ITEM_CONTABIL);
		
		if (novo) {
			facade.inserir(instancia);
			return EstadoRegistro.NOVO;
		}
		else {
			facade.alterar(instancia);
			return EstadoRegistro.PERSISTIDO;
		}
	}

	@Override
	public void excluirRegistros(List<MotivoDepositoVO> voList)
			throws DEPIIntegrationException {
		facade.excluirLista(voList);		
	}
	
	@Override
	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo, int codUsuario, String ipCliente) {
		return facade.obterPorChave(vo);
	}

}
