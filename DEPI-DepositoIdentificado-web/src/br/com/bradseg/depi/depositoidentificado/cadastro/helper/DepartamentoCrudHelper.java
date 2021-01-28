package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepartamentoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de departamento.
 * 
 * @author Marcelo Damasceno
 */
public class DepartamentoCrudHelper implements
		CrudHelper<DepartamentoCampo, DepartamentoVO, DepartamentoEditarFormModel> {
	
	private transient DepartamentoFacade facade;
	
	private static final String TITLE_DEPARTAMENTO_CONSULTAR = "title.departamento.consultar";
	
	private static final String TITLE_DEPARTAMENTO_LISTAR = "title.departamento.listar";
	
	private static final String TITLE_DEPARTAMENTO_EDITAR = "title.departamento.editar";
	
	private static final String TITLE_DEPARTAMENTO_DETALHAR = "title.departamento.detalhar";

	private static final String TITLE_DEPARTAMENTO_INCLUIR = "title.departamento.novo";

	public void setFacade(DepartamentoFacade facade) {
		this.facade = facade;
	}
	
	// Métodos para o filtro

	@Override
	public String getChaveTituloConsultar() {
		return TITLE_DEPARTAMENTO_CONSULTAR;
	}

	@Override
	public String getChaveTituloListar() {
		return TITLE_DEPARTAMENTO_LISTAR;
	}

	@Override
	public FiltroConsultarForm<DepartamentoCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<DepartamentoCampo>> fornecedor = new FornecedorObjeto<Collection<DepartamentoCampo>>() {
			
			@Override
			public Collection<DepartamentoCampo> get() {
				return DepartamentoCampo.valuesForCriteria();
			}
			
		};
		
		Funcao<String, DepartamentoCampo> obterEntidade = new Funcao<String, DepartamentoCampo>() {
			
			@Override
			public DepartamentoCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return DepartamentoCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<>(fornecedor, obterEntidade);
	}

	@Override
	public List<DepartamentoVO> processarCriterios(
			List<CriterioConsultaVO<DepartamentoCampo>> criterios) {

		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		aux.add(new CriterioConsultaVO<DepartamentoCampo>("CIND_REG_ATIVO = 'S'"));
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);
		
		return facade.obterPorFiltro(filtro);
	}

	// Métodos para o CRUD

	@Override
	public String getChaveTituloDetalhar() {
		return TITLE_DEPARTAMENTO_DETALHAR;
	}

	@Override
	public String getChaveTituloAlterar() {
		return TITLE_DEPARTAMENTO_EDITAR;
	}

	@Override
	public String getChaveTituloIncluir() {
		return TITLE_DEPARTAMENTO_INCLUIR;
	}

	@Override
	public DepartamentoEditarFormModel criarCrudModel() {
		return new DepartamentoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(DepartamentoEditarFormModel model)
			throws DEPIIntegrationException {
		
		DepartamentoVO instancia = obterPeloCodigo(model.getCodigo());
		model.setSiglaDepartamento(instancia.getSiglaDepartamento());
		model.setNomeDepartamento(instancia.getNomeDepartamento());
	}

	private DepartamentoVO obterPeloCodigo(String codigo) {
		int chave = Integer.parseInt(codigo);

		DepartamentoVO vo = new DepartamentoVO();
		vo.setCodigoDepartamento(chave);
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(DepartamentoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {
		
		String codigo = model.getCodigo();
		boolean novo = codigo == null || codigo.isEmpty();
	
		DepartamentoVO instancia;
		
		if (novo) {
			instancia = new DepartamentoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		}
		else {
			instancia = obterPeloCodigo(codigo);
		}
	
		instancia.setSiglaDepartamento(model.getSiglaDepartamento());
		instancia.setNomeDepartamento(model.getNomeDepartamento());
		instancia.setIndicadoRegistroAtivo(ConstantesDEPI.INDICADOR_ATIVO);
	
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
	public void excluirRegistros(List<DepartamentoVO> voList)
			throws DEPIIntegrationException {
		facade.excluir(voList);
	}

	@Override
	public DepartamentoVO obterPorChave(DepartamentoVO vo) {
		return facade.obterPorChave(vo);
	}

}
