package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
	public List<DepartamentoCompanhiaVO> processarCriterios(
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
		
		DepartamentoCompanhiaVO instancia = obterPeloCodigo(model.getCodigo());
		model.setCias(Collections.singletonList(instancia.getCompanhia()));
		model.setDeptos(new ArrayList<>(instancia.getDeptos()));
		
		List<Integer> codDepartamentos = new ArrayList<>(instancia.getDeptos().size());
		for (DepartamentoVO depto : instancia.getDeptos()) {
			codDepartamentos.add(depto.getCodigoDepartamento());
		}
		model.setCodDepartamentos(codDepartamentos);
	}

	private DepartamentoCompanhiaVO obterPeloCodigo(String codigoDeptoCia) {
		int codigoCompanhia = Integer.parseInt(codigoDeptoCia);
		
		return facade.obterPorCompanhiaSeguradora(new CompanhiaSeguradoraVO(codigoCompanhia));
	}

	@Override
	public EstadoRegistro persistirDados(DepartamentoCompanhiaEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {
		
		String codigo = model.getCodigo();
		boolean novo = model.getEstado() == EstadoCrud.INSERIR;
		DepartamentoCompanhiaVO instancia = new DepartamentoCompanhiaVO();
		
		int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		instancia.setCompanhia(new CompanhiaSeguradoraVO(Integer.parseInt(codigo)));
		
		List<Integer> codDepartamentos = model.getCodDepartamentos();
		ArrayList<DepartamentoVO> deptos = new ArrayList<>(codDepartamentos.size());
		
		for (Integer codDepto : codDepartamentos) {
			deptos.add(new DepartamentoVO(codDepto));
		}
		instancia.setDeptos(deptos);
		
		facade.persistir(instancia);
		
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

	@Override
	public DepartamentoCompanhiaVO obterPorChave(DepartamentoCompanhiaVO vo) {
		return facade.obterPorCompanhiaSeguradora(vo.getCompanhia());
	}

}
