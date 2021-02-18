package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.ContaCorrenteEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ParametroDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Classe auxiliar para o fluxo de cadastro de conta corrente.
 * 
 * @author Marcelo Damasceno
 */
public class ContaCorrenteCrudHelper implements
		CrudHelper<ContaCorrenteAutorizadaCampo, ContaCorrenteAutorizadaVO, ContaCorrenteEditarFormModel> {

	private static final String TITLE_DEPOSITO_CONSULTAR = "title.parametrodeposito.consultar";

	private static final String TITLE_DEPOSITO_LISTAR = "title.parametrodeposito.listar";
	
	private static final String TITLE_DEPOSITO_ALTERAR = "title.parametrodeposito.editar";
	
	private static final String TITLE_DEPOSITO_DETALHAR = "title.parametrodeposito.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.parametrodeposito.novo";

	private transient ContaCorrenteFacade facade;

	public ContaCorrenteFacade getFacade() {
		return facade;
	}
	
	public void setFacade(ContaCorrenteFacade facade) {
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
	public List<ContaCorrenteAutorizadaVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<ContaCorrenteAutorizadaCampo>> criterios) {

		List<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(
				criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

//		return facade.obterPorFiltroComRestricaoDeGrupoAcesso(codUsuario, filtro);
		return null;
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
	public ContaCorrenteEditarFormModel criarCrudModel() {
		return new ContaCorrenteEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(ContaCorrenteEditarFormModel m)
			throws DEPIIntegrationException {
	}

	/**
	 * Prepara o formulário para inclusão.
	 * @param codUsuario Usuário logado
	 * @param model Formulário
	 * @throws DEPIIntegrationException Erro de lógica
	 */
	public void prepararFormularioInclusao(
			int codUsuario, ContaCorrenteEditarFormModel model)
			throws DEPIIntegrationException {

		model.setEstado(EstadoCrud.INSERIR);
		
		List<CompanhiaSeguradoraVO> cias = obterCompanhias(codUsuario);
		
		if (cias.isEmpty()) {
			throw new DEPIBusinessException(
					ConstantesDEPI.ParametroDeposito.ERRO_USUARIO_SEM_GRUPO_ASSOCIADO);
		}
		
		model.setCias(cias);
		
		if (! cias.isEmpty()) {
			CompanhiaSeguradoraVO companhia = cias.get(0);
			model.setCodigoCompanhia(String.valueOf(companhia.getCodigoCompanhia()));
		}
		else {
			model.setCodigoCompanhia(null);
		}
	}
	
	/**
	 * Lista as companhias a que o usuário logado tem acesso
	 * @param codUsuario Código do usuário logado 
	 * @return Lista de companhias
	 */
	private List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
		return facade.obterCompanhias(codUsuario);
	}

	private ContaCorrenteAutorizadaVO obterPeloCodigo(String codigo) {
		if (codigo == null) {
			return null;
		}
		
		String[] sCodigos = codigo.split(";");
		if (sCodigos.length != 3) {
			return null;
		}
		
		ContaCorrenteAutorizadaVO vo = new ContaCorrenteAutorizadaVO(
				new BancoVO(Integer.parseInt(sCodigos[0])),
				Integer.parseInt(sCodigos[1]), 
				Long.parseLong(sCodigos[2]));
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(
			ContaCorrenteEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		ContaCorrenteAutorizadaVO instancia;

		if (novo) {
			instancia = new ContaCorrenteAutorizadaVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
			
			instancia.setCia(new CompanhiaSeguradoraVO(Integer.parseInt(model.getCodigoCompanhia())));
		}
		else {
			instancia = obterPeloCodigo(model.getCodigo());
		}

		// TODO preencher campos
		
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
	public void excluirRegistros(List<ContaCorrenteAutorizadaVO> voList)
			throws DEPIIntegrationException {
		facade.excluirLista(voList);
	}
	
	@Override
	public ContaCorrenteAutorizadaVO obterPorChave(ContaCorrenteAutorizadaVO vo) {
		return facade.obterPorChave(vo);
	}

}
