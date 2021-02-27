package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.util.CollectionUtils;

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
	
	private static final String TITLE_CONSULTAR = "title.grupoacesso.consultar";

	private static final String TITLE_LISTAR = "title.grupoacesso.listar";
	
	private static final String TITLE_ALTERAR = "title.grupoacesso.editar";
	
	private static final String TITLE_DETALHAR = "title.grupoacesso.detalhar";

	private static final String TITLE_INCLUIR = "title.grupoacesso.novo";

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
		return TITLE_CONSULTAR;
	}

	@Override
	public String getChaveTituloListar() {
		return TITLE_LISTAR;
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
	public List<GrupoAcessoVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<GrupoAcessoCampo>> criterios) {
		
		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltro(filtro);
	}
	
	// Métodos para atender ao CRUD

	@Override
	public String getChaveTituloDetalhar() {
		return TITLE_DETALHAR;
	}

	@Override
	public String getChaveTituloAlterar() {
		return TITLE_ALTERAR;
	}

	@Override
	public String getChaveTituloIncluir() {
		return TITLE_INCLUIR;
	}

	@Override
	public GrupoAcessoEditarFormModel criarCrudModel() {
		return new GrupoAcessoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(GrupoAcessoEditarFormModel model)
			throws DEPIIntegrationException {
		
		int codigoGrupoAcesso = Integer.parseInt(model.getCodigo());
		GrupoAcessoVO instancia = obterPeloCodigo(codigoGrupoAcesso);
		
		CompanhiaSeguradoraVO companhia = instancia.getCia();
		DepartamentoVO departamento = instancia.getDepto();
		List<String> codFuncionarios = new ArrayList<>(instancia.getFuncionarios().size());
		
		for (UsuarioVO usuario : instancia.getFuncionarios()) {
			codFuncionarios.add(String.valueOf(usuario.getCodigoUsuario()));
		}

		model.setCodigoGrupoAcesso(String.valueOf(instancia.getCodigoGrupoAcesso()));
		model.setCodCompanhia(String.valueOf(companhia.getCodigoCompanhia()));
		model.setSiglaDepartamento(departamento.getSiglaDepartamento());
		model.setCodFuncionarios(codFuncionarios);
		model.setFuncionarios(instancia.getFuncionarios());
		model.setNomeGrupoAcesso(instancia.getNomeGrupoAcesso());
		
		model.setCias(Collections.singletonList(instancia.getCia()));
		
		model.setDeptos(Collections.singletonList(instancia.getDepto()));
	}
	
	/**
	 * Acrescenta à lista de funcionários aqueles que estão no código 
	 * @param model Dados do formulário
	 */
	public void preencherFuncionarios(GrupoAcessoEditarFormModel model) {
		
		if (CollectionUtils.isEmpty(model.getCodFuncionarios())) {
			return;
		}
		
		ArrayList<Integer> codFuncionarios = new ArrayList<>(model.getCodFuncionariosInt());
		
		// pega os funcionários já registrados no model, garantindo manipular lista variável
		List<UsuarioVO> funcionarios = model.getFuncionarios();
		if (funcionarios == null) {
			funcionarios = new ArrayList<>();
		}
		else {
			funcionarios = new ArrayList<>(funcionarios);
		}
		
		// mapeia os funcionários por seu código
		LinkedHashMap<Integer, UsuarioVO> mapCodFuncionario = new LinkedHashMap<>();
		for (UsuarioVO usuarioVO : funcionarios) {
			Integer codigoUsuario = usuarioVO.getCodigoUsuario();
			mapCodFuncionario.put(codigoUsuario, usuarioVO);
			// remove da lista aqueles que já foram recuperados
			codFuncionarios.remove(codigoUsuario);
		}
		
		if (! codFuncionarios.isEmpty()) {
			// recupera apenas os funcionários que não foram recuperados
			List<UsuarioVO> usuarios = facade.obterUsuarios(codFuncionarios);
			funcionarios.addAll(usuarios);
			
			model.setFuncionarios(funcionarios);
			
			// limpa os registros para evitar duplo caregamento.
			model.getCodFuncionarios().clear();
		}
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

		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		GrupoAcessoVO instancia;

		final int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		final int codCompanhia = Integer.parseInt(model.getCodCompanhia());
		final String siglaDepto = model.getSiglaDepartamento();

		
		if (novo) {
			instancia = new GrupoAcessoVO();
			instancia.setDataInclusao(new Date());
			preencherCompanhia(instancia, codCompanhia);
			preencherDepartamento(instancia, new CompanhiaSeguradoraVO(codCompanhia), siglaDepto);
		}
		else {
			instancia = obterPeloCodigo(Integer.parseInt(model.getCodigo()));
			
			if (instancia.getCia().getCodigoCompanhia() != codCompanhia) {
				preencherCompanhia(instancia, codCompanhia);
			}
			
			if (! instancia.getDepto().getSiglaDepartamento().equals(siglaDepto)) {
				preencherDepartamento(instancia, new CompanhiaSeguradoraVO(
						codCompanhia), siglaDepto);
			}
		}
		
		List <UsuarioVO> usuarios = obterUsuarios(model.getCodFuncionarios());
		
		instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		instancia.setDataHoraAtualizacao(new Date());
		instancia.setFuncionarios(usuarios);
		
		if (novo) {
			facade.inserir(instancia);
			return EstadoRegistro.NOVO;
		}
		else {
			facade.alterar(instancia);
			return EstadoRegistro.PERSISTIDO;
		}
	}

	/**
	 * Códigos dos funcionários
	 * @param codFuncionarios Lista de string com códigos dos funcionários
	 * @return Dados dos usuários a ser resgatados
	 */
	private List<UsuarioVO> obterUsuarios(List<String> codFuncionarioStr) {
		List<Integer> codFuncionarios = new ArrayList<>(codFuncionarioStr.size());
		for (String cod : codFuncionarioStr) {
			codFuncionarios.add(new Integer(cod));
		}
		return facade.obterUsuarios(codFuncionarios);
	}

	private void preencherCompanhia(GrupoAcessoVO instancia, int codCompanhia) {
		CompanhiaSeguradoraVO cia = facade.obterCompanhia(new CompanhiaSeguradoraVO(codCompanhia));
		instancia.setCia(cia);
	}

	private void preencherDepartamento(GrupoAcessoVO instancia,
			CompanhiaSeguradoraVO cia, String siglaDepto) {
		DepartamentoVO depto = new DepartamentoVO();
		depto.setSiglaDepartamento(siglaDepto);
		
		depto = facade.obterDepartamento(cia, depto);
		instancia.setDepto(depto);
	}

	@Override
	public void excluirRegistros(List<GrupoAcessoVO> voList)
			throws DEPIIntegrationException {
		
		facade.excluir(voList);
	}
	
	@Override
	public GrupoAcessoVO obterPorChave(GrupoAcessoVO vo) {
		return facade.obterPorChave(vo);
	}

	/**
	 * Lista Companhias Seguradoras
	 * @return Lista de Companhias Seguradoras
	 */
	public List<CompanhiaSeguradoraVO> obterCompanhias() {
		return facade.obterCompanhias();
	}

	/**
	 * Lista os departamentos da companhia
	 * @param vo Companhia
	 * @return Lista de departamentos
	 */
	public List<DepartamentoVO> obterDepartamentos(CompanhiaSeguradoraVO vo) {
		return facade.obterDepartamentos(vo);
	}
	
}
