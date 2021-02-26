package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.AssociarMotivoDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.AssociarMotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de conta corrente.
 * 
 * @author Marcelo Damasceno
 */
public class AssociarMotivoDepositoCrudHelper implements
		CrudHelper<AssociarMotivoDepositoCampo, AssociarMotivoDepositoVO, AssociarMotivoDepositoEditarFormModel> {

	private transient AssociarMotivoDepositoFacade facade;

	public AssociarMotivoDepositoFacade getFacade() {
		return facade;
	}
	
	public void setFacade(AssociarMotivoDepositoFacade facade) {
		this.facade = facade;
	}
	
	private static final String TITLE_CONSULTAR = "title.AssociacaoMotivoDeposito.consultar";

	private static final String TITLE_LISTAR = "title.AssociacaoMotivoDeposito.listar";
	
	private static final String TITLE_ALTERAR = "title.AssociacaoMotivoDeposito.editar";
	
	private static final String TITLE_DETALHAR = "title.AssociacaoMotivoDeposito.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.AssociacaoMotivoDeposito.novo";
	// Métodos para filtrar

	@Override
	public String getChaveTituloConsultar() {
		return TITLE_CONSULTAR;
	}

	@Override
	public String getChaveTituloListar() {
		return TITLE_LISTAR;
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
		return TITLE_DEPOSITO_INCLUIR;
	}

	@Override
	public FiltroConsultarForm<AssociarMotivoDepositoCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<AssociarMotivoDepositoCampo>> criterios = new FornecedorObjeto<Collection<AssociarMotivoDepositoCampo>>() {

			@Override
			public Collection<AssociarMotivoDepositoCampo> get() {
				return AssociarMotivoDepositoCampo.valuesForCriteria();
			}

		};

		Funcao<String, AssociarMotivoDepositoCampo> obterEntidadeCampo = new Funcao<String, AssociarMotivoDepositoCampo>() {

			@Override
			public AssociarMotivoDepositoCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return AssociarMotivoDepositoCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<AssociarMotivoDepositoCampo>(criterios, obterEntidadeCampo);
	}

	@Override
	public List<AssociarMotivoDepositoVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<AssociarMotivoDepositoCampo>> criterios) {
		
		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltro(codUsuario, filtro);
	}
	
	// Métodos para atender ao CRUD

	@Override
	public AssociarMotivoDepositoEditarFormModel criarCrudModel() {
		return new AssociarMotivoDepositoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(AssociarMotivoDepositoEditarFormModel model)
			throws DEPIIntegrationException {
		
		AssociarMotivoDepositoVO vo = obterPeloCodigo(model.getCodigo());
		
		CompanhiaSeguradoraVO companhia = vo.getCia();
		model.setCias(Collections.singletonList(companhia));
		model.setCodigoCompanhia(BaseUtil.blankIfNull(companhia.getCodigoCompanhia()));
		
		model.setAgencia(BaseUtil.getValueMaskFormat("99999", BaseUtil.blankIfNull(vo.getCodigoAgencia()), true));
		model.setCodigoBanco(BaseUtil.getValueMaskFormat("9999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoExterno()), true));
		model.setDescricaoBanco(vo.getBanco().getDescricaoBanco());
		model.setAssociacaoMotivoDeposito(BaseUtil.getValueMaskFormat("9999999999999", BaseUtil.blankIfNull(vo.getAssociacaoMotivoDeposito()), true));
		model.setContaInterna(BaseUtil.getValueMaskFormat("999999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoInterno()), true));
		model.setObservacao(vo.getObservacao());
		model.setTrps(BaseUtil.getValueMaskFormat("9999999999999", BaseUtil.blankIfNull(vo.getTrps()), true));
		model.setDescricaoAgencia(vo.getDescricaoAgencia());
	}

	public AssociarMotivoDepositoVO obterPeloCodigo(String codigo) {
		AssociarMotivoDepositoVO vo = new AssociarMotivoDepositoVO();
		
		String[] partes = codigo.split(";");
		vo.setCia(new CompanhiaSeguradoraVO(Integer.parseInt(partes[0])));
		vo.setBanco(new BancoVO(Integer.parseInt(partes[1])));
		vo.setCodigoAgencia(Integer.parseInt(partes[2]));
		vo.setAssociacaoMotivoDeposito(Long.parseLong(partes[3]));
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(
			AssociarMotivoDepositoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		AssociarMotivoDepositoVO vo;

		final int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		final int codCompanhia = Integer.parseInt(model.getCodigoCompanhia());
		
		if (novo) {
			vo = new AssociarMotivoDepositoVO();
			vo.setDataInclusao(new Date());
		}
		else {
			vo = obterPeloCodigo(model.getCodigo());
		}
		
		vo.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		vo.setDataHoraAtualizacao(new Date());
		vo.setCia(new CompanhiaSeguradoraVO(codCompanhia));
		vo.setBanco(new BancoVO(Integer.parseInt(model.getCodigoBanco())));
		vo.setCodigoAgencia(Integer.parseInt(model.getAgencia()));
		vo.setAssociacaoMotivoDeposito(Long.parseLong(model.getAssociacaoMotivoDeposito()));
		vo.setObservacao(model.getObservacao());
		vo.setTrps(Long.parseLong(model.getTrps()));
		
		if (novo) {
			facade.inserir(vo);
			return EstadoRegistro.NOVO;
		}
		else {
			facade.alterar(vo);
			return EstadoRegistro.PERSISTIDO;
		}
	}

	@Override
	public void excluirRegistros(List<AssociarMotivoDepositoVO> voList)
			throws DEPIIntegrationException {
		
		facade.excluirLista(voList);
	}
	
	@Override
	public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo) {
		return facade.obterPorChave(vo);
	}

	/**
	 * Lista Companhias Seguradoras
	 * @param codUsuario Código do usuário logado
	 * @return Lista de Companhias Seguradoras
	 */
	public List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
		return facade.obterCompanhias(codUsuario);
	}
	
	/**
	 * Lista Departamentos de uma companhia
	 * @param codUsuario Código do usuário logado
	 * @param ciaVO Companhia Seguradora
	 * @return Lista de Departamentos
	 */
	public List<DepartamentoVO> obterDepartamentos(int codUsuario, CompanhiaSeguradoraVO ciaVO) {
		return facade.obterDepartamentos(codUsuario, ciaVO);
	}
	
	/**
	 * Lista Motivos de Depósito associados a um departamento
	 * @param codUsuario Código do usuário logado
	 * @return Lista de Motivos de Depósito
	 */
	public List<MotivoDepositoVO> obterMotivosDeposito(int codUsuario, DepartamentoVO depto) {
		return facade.obterMotivosDeposito(codUsuario, depto);
	}
	
	/**
	 * Lista os bancos associados a uma companhia
	 * @param cia Companhia
	 * @return Lista de bancos
	 */
	public List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia) {
		return facade.obterBancos(cia);
	}
	
	/**
	 * Obtém dados de um banco
	 * @param vo Contém o código do banco
	 * @return Banco
	 */
	public BancoVO obterBanco(BancoVO vo) {
		return facade.obterBanco(vo);
	}

}
