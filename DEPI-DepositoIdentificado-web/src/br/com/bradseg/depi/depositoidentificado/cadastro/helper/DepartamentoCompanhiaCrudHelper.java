package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.util.CollectionUtils;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoCompanhiaEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCompanhiaCampo;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de DepartamentoCompanhia.
 * 
 * @author Marcelo Damasceno
 */
public class DepartamentoCompanhiaCrudHelper implements
		CrudHelper<DepartamentoCompanhiaCampo, DepartamentoCompanhiaVO, DepartamentoCompanhiaEditarFormModel> {

	private transient DepartamentoCompanhiaFacade facade;
	
	private static final String TITLE_DEPARTAMENTOCOMPANHIA_CONSULTAR = "title.departamentocompanhia.consultar";
	
	private static final String TITLE_DEPARTAMENTOCOMPANHIA_LISTAR = "title.departamentocompanhia.listar";
	
	private static final String TITLE_DEPARTAMENTOCOMPANHIA_EDITAR = "title.departamentocompanhia.editar";
	
	private static final String TITLE_DEPARTAMENTOCOMPANHIA_DETALHAR = "title.departamentocompanhia.detalhar";

	private static final String TITLE_DEPARTAMENTOCOMPANHIA_INCLUIR = "title.departamentocompanhia.novo";

	public void setFacade(DepartamentoCompanhiaFacade facade) {
		this.facade = facade;
	}
	
	// Métodos para o filtro

	@Override
	public String getChaveTituloConsultar() {
		return TITLE_DEPARTAMENTOCOMPANHIA_CONSULTAR;
	}

	@Override
	public String getChaveTituloListar() {
		return TITLE_DEPARTAMENTOCOMPANHIA_LISTAR;
	}

	@Override
	public FiltroConsultarForm<DepartamentoCompanhiaCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<DepartamentoCompanhiaCampo>> fornecedor = new FornecedorObjeto<Collection<DepartamentoCompanhiaCampo>>() {
			
			@Override
			public Collection<DepartamentoCompanhiaCampo> get() {
				return DepartamentoCompanhiaCampo.valuesForCriteria();
			}
			
		};
		
		Funcao<String, DepartamentoCompanhiaCampo> obterEntidade = new Funcao<String, DepartamentoCompanhiaCampo>() {
			
			@Override
			public DepartamentoCompanhiaCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return DepartamentoCompanhiaCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<>(fornecedor, obterEntidade);
	}

	@Override
	public List<DepartamentoCompanhiaVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<DepartamentoCompanhiaCampo>> criterios) {

		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);
		
		return facade.obterPorFiltro(filtro);
	}

	// Métodos para o CRUD

	@Override
	public String getChaveTituloDetalhar() {
		return TITLE_DEPARTAMENTOCOMPANHIA_DETALHAR;
	}

	@Override
	public String getChaveTituloAlterar() {
		return TITLE_DEPARTAMENTOCOMPANHIA_EDITAR;
	}

	@Override
	public String getChaveTituloIncluir() {
		return TITLE_DEPARTAMENTOCOMPANHIA_INCLUIR;
	}

	@Override
	public DepartamentoCompanhiaEditarFormModel criarCrudModel() {
		return new DepartamentoCompanhiaEditarFormModel();
	}
	
	@Override
	public void preencherFormularioEdicao(DepartamentoCompanhiaEditarFormModel model)
			throws DEPIIntegrationException {
		// TODO obter cias
		List<DepartamentoCompanhiaVO> items = obterPeloCodigo(model.getCodigo());
		
		DepartamentoCompanhiaVO instancia = items.get(0);
		
		List<DepartamentoVO> deptos = new ArrayList<>(items.size());
		
		List<String> siglaDepartamentos = new ArrayList<>(items.size());
		
		for (DepartamentoCompanhiaVO vo : items) {
			DepartamentoVO depto = vo.getDepartamento();
			deptos.add(depto);
			siglaDepartamentos.add(depto.getSiglaDepartamento());
		}
		
		model.setCias(Collections.singletonList(instancia.getCompanhia()));
		model.setDeptos(new ArrayList<>(deptos));
		model.setSiglaDepartamentos(siglaDepartamentos);
	}

	private List<DepartamentoCompanhiaVO> obterPeloCodigo(String codigoDeptoCia) {
		
		String[] codigo = codigoDeptoCia.split(";");
		int codigoCompanhia = Integer.parseInt(codigo[0]);
		
		return facade.obterPorCompanhiaSeguradora(new CompanhiaSeguradoraVO(codigoCompanhia));
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper#obterPorChave(java.lang.Object)
	 */
	@Override
	public DepartamentoCompanhiaVO obterPorChave(DepartamentoCompanhiaVO vo) {
		return null;
	}

	@Override
	public EstadoRegistro persistirDados(DepartamentoCompanhiaEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {
		
		int codUsuario = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		
		List<String> siglaDepartamentos = model.getSiglaDepartamentos();
		ArrayList<DepartamentoVO> deptos = new ArrayList<>(siglaDepartamentos.size());
		
		for (String sigla : siglaDepartamentos) {
			deptos.add(new DepartamentoVO(sigla));
		}

		boolean novo = model.getEstado() == EstadoCrud.INSERIR;

		final CompanhiaSeguradoraVO cia;
		if (model.getCodCompanhia() != null && ! model.getCodCompanhia().isEmpty()) {
			cia = new CompanhiaSeguradoraVO(Integer.parseInt(model.getCodCompanhia()));
		}
		else {
			String[] codigos = model.getCodigo().split(";");
			cia = new CompanhiaSeguradoraVO(Integer.parseInt(codigos[0]));
		}
		
		facade.persistir(cia, deptos, codUsuario);
		
		if (novo) {
			return EstadoRegistro.NOVO;
		}
		return EstadoRegistro.PERSISTIDO;
	}

	@Override
	public void excluirRegistros(List<DepartamentoCompanhiaVO> voList)
			throws DEPIIntegrationException {
		
		facade.excluir(voList);
	}

	/**
	 * Acrescenta à lista de departamentos aqueles que estão no código 
	 * @param model Dados do formulário
	 */
	public void preencherDepartamentos(
			DepartamentoCompanhiaEditarFormModel model) {
		
		if (CollectionUtils.isEmpty(model.getSiglaDepartamentos())) {
			return;
		}
		
		ArrayList<String> siglas = new ArrayList<>(model.getSiglaDepartamentos());
		
		// pega os departamentos já registrados no model, garantindo manipular lista variável
		List<DepartamentoVO> deptos = model.getDeptos();
		if (deptos == null) {
			deptos = new ArrayList<>();
		}
		else {
			deptos = new ArrayList<>(deptos);
		}
		
		// mapeia os funcionários por seu código
		LinkedHashMap<String, DepartamentoVO> mapSigla = new LinkedHashMap<>();
		for (DepartamentoVO vo: deptos) {
			String sigla = vo.getSiglaDepartamento();
			mapSigla.put(sigla, vo);
			// remove da lista aqueles que já foram recuperados
			siglas.remove(sigla);
		}
		
		if (! siglas.isEmpty()) {
			// recupera apenas os departamentos que não foram recuperados
			List<DepartamentoVO> lista = facade.obterDepartamentos(siglas);
			deptos.addAll(lista);
			
			model.setDeptos(deptos);
			
			// limpa os registros para evitar duplo caregamento.
			model.getSiglaDepartamentos().clear();
		}
	}

	/**
	 * Obtém lista de companhias 
	 * @return Lista
	 */
	public List<CompanhiaSeguradoraVO> obterCompanhias() {
		return facade.obterCompanhias();
	}

}
