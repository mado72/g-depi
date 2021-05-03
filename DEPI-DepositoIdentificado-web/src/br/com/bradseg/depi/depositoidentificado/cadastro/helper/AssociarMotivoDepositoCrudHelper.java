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
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.Fornecedor;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;
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
	
	private static final String TITLE_CONSULTAR = "title.associarmotivodeposito.consultar";

	private static final String TITLE_LISTAR = "title.associarmotivodeposito.listar";
	
	private static final String TITLE_ALTERAR = "title.associarmotivodeposito.editar";
	
	private static final String TITLE_DETALHAR = "title.associarmotivodeposito.detalhar";

	private static final String TITLE_INCLUIR = "title.associarmotivodeposito.novo";

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
		return TITLE_INCLUIR;
	}

	@Override
	public FiltroConsultarForm<AssociarMotivoDepositoCampo> criarFiltroModel() {
		Fornecedor<Collection<AssociarMotivoDepositoCampo>> criterios = new Fornecedor<Collection<AssociarMotivoDepositoCampo>>() {

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
	public void preencherFormularioEdicao(
			AssociarMotivoDepositoEditarFormModel model, int codUsuario,
			String ipCliente) throws DEPIIntegrationException {
		
		AssociarMotivoDepositoVO vo = obterPeloCodigo(model.getCodigo());
		
		CompanhiaSeguradoraVO ciaVO = facade.obterCompanhiaSeguradora(vo.getCia());
		DepartamentoVO depto = facade.obterDepartamento(vo.getDepartamento());
		MotivoDepositoVO motivoDepositoVO = facade.obterMotivoDeposito(vo.getMotivoDeposito());
		
		EventoContabilVO eventoContabilVO = facade
				.obterEventoContabil(motivoDepositoVO.getCodigoEventoContabil());
		
		ItemContabilVO itemContabilVO = facade.obterItemContabil(
				motivoDepositoVO.getCodigoEventoContabil(),
				motivoDepositoVO.getCodigoItemContabil());
		
		BancoVO banco = facade.obterBanco(vo.getBanco());
		AgenciaVO agencia = facade.obterAgencia(vo.getBanco(), vo.getCodigoAgencia());
		ContaCorrenteAutorizadaVO conta = facade.obterConta(vo.getBanco(),
				vo.getCodigoAgencia(), vo.getContaCorrente());
		
		model.setCias(Collections.singletonList(ciaVO));
		model.setDeptos(Collections.singletonList(depto));
		model.setMotivos(Collections.singletonList(motivoDepositoVO));
		model.setAgencias(Collections.singletonList(agencia));
		model.setBancos(Collections.singletonList(banco));
		model.setContas(Collections.singletonList(conta));
		model.setDescricaoDetalhadaMotivo(motivoDepositoVO.getDescricaoDetalhada());
		
		model.setCodigoEventoContabil(String.valueOf(eventoContabilVO.getCodigoTipo()));
		model.setDescricaoEventoContabil(eventoContabilVO.getDescricaoTipo());
		model.setCodigoItemContabil(String.valueOf(itemContabilVO.getCodigoTipo()));
		model.setDescricaoItemContabil(itemContabilVO.getDescricaoTipo());
		model.setContaInterna(String.valueOf(conta.getCodigoInternoCC()));
	}
	
	public AssociarMotivoDepositoVO obterPeloCodigo(String codigo) {
		AssociarMotivoDepositoVO vo = analisarCodigo(codigo);
		
		return facade.obterPorChave(vo);
	}

	public AssociarMotivoDepositoVO analisarCodigo(String codigo) {
		AssociarMotivoDepositoVO vo = new AssociarMotivoDepositoVO();
		
		String[] partes = codigo.split(";");
		vo.setCia(new CompanhiaSeguradoraVO(Integer.parseInt(partes[0])));
		vo.setDepartamento(new DepartamentoVO(Integer.parseInt(partes[1])));
		vo.setMotivoDeposito(new MotivoDepositoVO(Integer.parseInt(partes[2])));
		vo.setBanco(new BancoVO(Integer.parseInt(partes[3])));
		vo.setCodigoAgencia(Integer.parseInt(partes[4]));
		vo.setContaCorrente(Long.parseLong(partes[5]));
		return vo;
	}
	
	@Override
	public EstadoRegistro persistirDados(
			AssociarMotivoDepositoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		AssociarMotivoDepositoVO vo;

		final int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		
		vo = new AssociarMotivoDepositoVO();
		vo.setDataInclusao(new Date());

		model.obterValores(vo);
		vo.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		vo.setDataHoraAtualizacao(new Date());
		
		facade.inserir(vo);
		return EstadoRegistro.NOVO;
	}

	@Override
	public void excluirRegistros(List<AssociarMotivoDepositoVO> voList)
			throws DEPIIntegrationException {
		
		facade.excluirLista(voList);
	}
	
	@Override
	public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo,
			int codUsuario, String ipCliente) {
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
		return facade.obterDepartamentosComRestricaoParametroDeposito(codUsuario, ciaVO);
	}
	
	/**
	 * Lista Motivos de Depósito associados a um departamento x cia
	 * @param codUsuario Código do usuário logado
	 * @param deptoCia Associação Depto x Cia
	 * @return Lista de Motivos de Depósito
	 */
	public List<MotivoDepositoVO> obterMotivosDeposito(int codUsuario, DepartamentoCompanhiaVO deptoCia) {
		return facade.obterMotivosDeposito(codUsuario, deptoCia);
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

	/**
	 * Obtém o evento contábil do motivo depósito
	 * @param motivo motivo depósito
	 * @return Evento contábil
	 */
	public EventoContabilVO obterEventoContabil(MotivoDepositoVO motivo) {
		return facade.obterEventoContabil(motivo.getCodigoEventoContabil());
	}

	/**
	 * Obtém o item contábil do motivo depósito
	 * @param motivo motivo depósito
	 * @return Item contábil
	 */
	public ItemContabilVO obterItemContabil(MotivoDepositoVO motivo) {
		return facade.obterItemContabil(motivo.getCodigoEventoContabil(), motivo.getCodigoItemContabil());
	}

	/**
	 * Obtém uma companhia seguradora
	 * @param companhiaSeguradoraVO Fornece o código da cia
	 * @return Companhia Seguradora
	 */
	public CompanhiaSeguradoraVO obterCompanhia(
			CompanhiaSeguradoraVO companhiaSeguradoraVO) {
		return facade.obterCompanhiaSeguradora(companhiaSeguradoraVO);
	}

	/**
	 * Obtém um departamento
	 * @param departamentoVO Fornece o código do departamento
	 * @return Departamento
	 */
	public DepartamentoVO obterDepartamento(DepartamentoVO departamentoVO) {
		return facade.obterDepartamento(departamentoVO);
	}

	/**
	 * Obtém o motivo depósito
	 * @param motivoDepositoVO Fornece o código do motivo
	 * @return Motivo Depósito
	 */
	public MotivoDepositoVO obterMotivoDeposito(
			MotivoDepositoVO motivoDepositoVO) {
		return facade.obterMotivoDeposito(motivoDepositoVO);
	}

	/**
	 * Obtém lista de agências de um banco
	 * @param ciaVO Companhia
	 * @param bancoVO Banco
	 * @return Agências
	 */
	public List<AgenciaVO> obterAgencias(CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO) {
		return facade.obterAgencias(ciaVO, bancoVO);
	}

	/**
	 * Obtém lista de contas
	 * @param codUsuario Usuário logado
	 * @param ciaVO Companhia
	 * @param bancoVO Banco
	 * @param agenciaVO Agência
	 * @return Lista de contas
	 */
	public List<ContaCorrenteAutorizadaVO> obterContasCorrente(
			int codUsuario,
			CompanhiaSeguradoraVO ciaVO, BancoVO bancoVO,
			AgenciaVO agenciaVO) {
		
		return facade.obterContas(codUsuario, ciaVO, bancoVO, agenciaVO);
	}

}
