package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.GrupoAcessoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.GrupoAcessoCampo;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Classe auxiliar para o fluxo de cadastro de motivo depósito.
 * 
 * @author Marcelo Damasceno
 */
public class GrupoAcessoCrudHelper implements
		CrudHelper<GrupoAcessoCampo, GrupoAcessoVO, GrupoAcessoEditarFormModel> {

	private static final String TITLE_DEPOSITO_CONSULTAR = "title.grupoAcesso.consultar";

	private static final String TITLE_DEPOSITO_LISTAR = "title.grupoAcesso.listar";
	
	private static final String TITLE_DEPOSITO_ALTERAR = "title.grupoAcesso.editar";
	
	private static final String TITLE_DEPOSITO_DETALHAR = "title.grupoAcesso.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.grupoAcesso.novo";

	private transient GrupoAcessoFacade facade;

	public GrupoAcessoFacade getFacade() {
		return facade;
	}
	
	public void setFacade(GrupoAcessoFacade facade) {
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
	public FiltroConsultarForm<GrupoAcessoCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<GrupoAcessoCampo>> criterios = new FornecedorObjeto<Collection<GrupoAcessoCampo>>() {

			@Override
			public Collection<GrupoAcessoCampo> get() {
				return GrupoAcessoCampo.valuesForCriteria();
			}

		};

		Funcao<String, GrupoAcessoCampo> obterEntidadeCampo = new Funcao<String, GrupoAcessoCampo>() {

			@Override
			public GrupoAcessoCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return GrupoAcessoCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<GrupoAcessoCampo>(criterios, obterEntidadeCampo);
	}

	@Override
	public List<GrupoAcessoVO> processarCriterios(
			List<CriterioConsultaVO<GrupoAcessoCampo>> criterios) {
		
		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		// TODO Verificar o critério de deleção lógica.
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		List<GrupoAcessoVO> retorno = facade.obterPorFiltro(filtro);
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
	public GrupoAcessoEditarFormModel criarCrudModel() {
		return new GrupoAcessoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(GrupoAcessoEditarFormModel model)
			throws DEPIIntegrationException {
		
		int codigoGrupoAcesso = Integer.parseInt(model.getCodigoGrupoAcesso());
		GrupoAcessoVO instancia = obterPeloCodigo(codigoGrupoAcesso);
		
		CompanhiaSeguradoraVO companhia = instancia.getCia();
		DepartamentoVO departamento = instancia.getDepto();
		List<String> codFuncionarios = new ArrayList<>(instancia.getUsuarios().size());
		
		for (UsuarioVO usuario : instancia.getUsuarios()) {
			codFuncionarios.add(String.valueOf(usuario.getCodigoUsuario()));
		}

		model.setCodigoGrupoAcesso(String.valueOf(instancia.getCodigoGrupoAcesso()));
		model.setCodCompanhia(String.valueOf(companhia.getCodigoCompanhia()));
		model.setDescCompanhia(companhia.getDescricaoCompanhia());
		model.setCodDepartamento(String.valueOf(departamento.getCodigoDepartamento()));
		model.setDescDepartamento(departamento.getNomeDepartamento());
		model.setCodFuncionarios(codFuncionarios);
	}

	private GrupoAcessoVO obterPeloCodigo(int codigo) {
		GrupoAcessoVO vo = new GrupoAcessoVO();
		vo.setCodigoGrupoAcesso(codigo);
		
		GrupoAcessoVO instancia = facade.obterPorChave(vo);
		return instancia;
	}

	@Override
	public EstadoRegistro persistirDados(
			GrupoAcessoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {
/*
		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		GrupoAcessoVO instancia;

		if (novo) {
			instancia = new GrupoAcessoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			instancia = obterPeloCodigo(Integer.parseInt(model.getCodigo()));
		}
		
		instancia.setDescricaoBasica(model.getDescricaoBasica());
		instancia.setDescricaoDetalhada(model.getDescricaoDetalhada());
		instancia.setCodigoEventoContabil(CODIGO_EVENTO_CONTABIL);
		instancia.setCodigoItemContabil(CODIGO_ITEM_CONTABIL);
		
		try {
			if (novo) {
				facade.inserir(instancia);
				return EstadoRegistro.NOVO;
			}
			else {
				facade.alterar(instancia);
*/				return EstadoRegistro.PERSISTIDO; /*
			}
		} catch (Exception e) {
			throw new DEPIIntegrationException(e, ConstantesDEPI.ERRO_INTERNO);
		}
*/
	}

	@Override
	public void excluirRegistros(List<GrupoAcessoVO> voList)
			throws DEPIIntegrationException {
/*
		facade.excluirLista(voList);
*/		
	}
	
	@Override
	public GrupoAcessoVO obterPorChave(GrupoAcessoVO vo) {
		return facade.obterPorChave(vo);
	}

}
