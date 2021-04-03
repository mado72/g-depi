package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bucb.servicos.model.pessoa.vo.ListarPessoaPorFiltroSaidaVO;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.DepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de conta corrente.
 * 
 * @author Marcelo Damasceno
 */
public class DepositoCrudHelper implements
		CrudHelper<DepositoCampo, DepositoVO, DepositoEditarFormModel> {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DepositoCrudHelper.class);

	private transient DepositoFacade facade;
	
	private final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

	public DepositoFacade getFacade() {
		return facade;
	}
	
	public void setFacade(DepositoFacade facade) {
		this.facade = facade;
	}
	
	private static final String TITLE_CONSULTAR = "title.deposito.consultar";

	private static final String TITLE_LISTAR = "title.deposito.listar";
	
	private static final String TITLE_ALTERAR = "title.deposito.editar";
	
	private static final String TITLE_DETALHAR = "title.deposito.detalhar";

	private static final String TITLE_INCLUIR = "title.deposito.novo";
	
	private static final NumberFormat DF = DecimalFormat.getInstance(new Locale("pt", "BR"));

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
	public FiltroConsultarForm<DepositoCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<DepositoCampo>> criterios = new FornecedorObjeto<Collection<DepositoCampo>>() {

			@Override
			public Collection<DepositoCampo> get() {
				return DepositoCampo.valuesForCriteria();
			}

		};

		Funcao<String, DepositoCampo> obterEntidadeCampo = new Funcao<String, DepositoCampo>() {

			@Override
			public DepositoCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return DepositoCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<DepositoCampo>(criterios, obterEntidadeCampo);
	}

	@Override
	public List<DepositoVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<DepositoCampo>> criterios) {
		
		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltro(codUsuario, filtro);
	}
	
	// Métodos para atender ao CRUD

	@Override
	public DepositoEditarFormModel criarCrudModel() {
		return new DepositoEditarFormModel();
	}
	
	@Override
	public void preencherFormularioEdicao(DepositoEditarFormModel model, int codUsuario, String ipCliente)
			throws DEPIIntegrationException {
		
		DepositoVO vo = obterPeloCodigo(model.getCodigo(), codUsuario, ipCliente);
		
		CompanhiaSeguradoraVO ciaVO = facade.obterCompanhiaSeguradora(vo.getCia());
		DepartamentoVO depto = facade.obterDepartamento(vo.getDepartamento());
		MotivoDepositoVO motivoDepositoVO = facade.obterMotivoDeposito(vo.getMotivoDeposito());
		
		List<ListarPessoaPorFiltroSaidaVO> pessoasCorporativas = preencheListaPessoasCorporativas(
				codUsuario, ipCliente, vo);
		removeRegistrosDeOutrosDepositantes(vo, pessoasCorporativas);
		
		model.setPessoasCorporativas(pessoasCorporativas);
		model.setPessoaDepositante(vo.getPessoaDepositante());
		
		BancoVO banco = facade.obterBanco(vo.getBanco());
		AgenciaVO agencia = facade.obterAgencia(vo.getBanco(), vo.getCodigoAgencia());
		ContaCorrenteAutorizadaVO conta = facade.obterConta(vo.getBanco(),
				vo.getCodigoAgencia(), vo.getContaCorrente());
		
		model.setCodigoCompanhia(String.valueOf(ciaVO.getCodigoCompanhia()));
		model.setCodigoDepartamento(String.valueOf(depto.getCodigoDepartamento()));
		model.setCodigoMotivoDeposito(String.valueOf(motivoDepositoVO.getCodigoMotivoDeposito()));
		
		model.setCias(Collections.singletonList(ciaVO));
		model.setDeptos(Collections.singletonList(depto));
		model.setMotivos(Collections.singletonList(motivoDepositoVO));
		model.setAgencias(Collections.singletonList(agencia));
		model.setBancos(Collections.singletonList(banco));
		model.setContas(Collections.singletonList(conta));
		model.setDescricaoDetalhadaMotivo(motivoDepositoVO.getDescricaoDetalhada());
		
		model.setContaInterna(String.valueOf(conta.getCodigoInternoCC()));
		model.setTrps(conta.getTrps());
		model.setCpfCnpj(vo.getCpfCnpj());
		model.setNomePessoa(vo.getNomePessoa());
		
		model.setSucursal(vo.getSucursal());
		model.setBloqueto(vo.getBloqueto());
		model.setTipoDocumento(vo.getTipoDocumento());
		model.setApolice(vo.getApolice());
		model.setProtocolo(vo.getProtocolo());
		model.setRamo(vo.getRamo());
		model.setEndosso(vo.getEndosso());
		model.setDossie(vo.getDossie());
		model.setParcela(vo.getParcela());
		model.setItem(vo.getItem());
		
		model.setObservacaoDeposito(vo.getObservacaoDeposito());
		model.setTipoGrupoRecebimento(vo.getTipoGrupoRecebimento());
		if (vo.getDtVencimentoDeposito() != null) {
			model.setDtVencimentoDeposito(SDF.format(vo.getDtVencimentoDeposito()));
		}
		
		if (vo.getVlrDepositoRegistrado() != null) {
			String valor = DF.format(vo.getVlrDepositoRegistrado());
			model.setVlrDepositoRegistrado(valor);
		}
		
		model.setCodigoDepositoIdentificado(vo.getCodigoDepositoIdentificado());
		model.setDv(vo.getDv());
		
		if (vo.getDataProrrogacao() != null) {
			model.setDataProrrogacao(SDF.format(vo.getDataProrrogacao()));
		}
		else if (vo.getDtVencimentoDeposito() != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(vo.getDtVencimentoDeposito());
			c.add(Calendar.DAY_OF_YEAR, 1);
			model.setDataProrrogacao(SDF.format(c.getTime()));
		}
		
		model.setDtCancelamentoDepositoIdentificado(dataFormatada(vo.getDtCancelamentoDepositoIdentificado()));
		model.setDtVencimentoDeposito(dataFormatada(vo.getDtVencimentoDeposito()));
		
		model.setParametro(facade.obterParametro(vo));
	}

	private List<ListarPessoaPorFiltroSaidaVO> preencheListaPessoasCorporativas(
			int codUsuario, String ipCliente, DepositoVO vo) {
		List<ListarPessoaPorFiltroSaidaVO> pessoasCorporativas;
		try {
			pessoasCorporativas = new ArrayList<>(facade.listarPessoas(vo.getCpfCnpj(), ipCliente, codUsuario));
		} catch (Exception e) {
			LOGGER.warn("N\u00E3o conseguiu preencher lista de pessoasCorporativas. {}", e.getMessage());
			pessoasCorporativas = Collections.emptyList();
		}
		
		return pessoasCorporativas;
	}

	/**
	 * Remove os itens cujo código seja diferente do definido na VO. Quando há um depositante definido na VO
	 * e somente se quer deixá-lo listado.
	 * @param vo VO
	 * @param pessoasCorporativas Lista de pessoas corporativas. A lista deve ter a capacidade de remover itens.
	 */
	private void removeRegistrosDeOutrosDepositantes(DepositoVO vo,
			List<ListarPessoaPorFiltroSaidaVO> pessoasCorporativas) {
		
		if (vo.getPessoaDepositante() > 0) {
			for (Iterator<ListarPessoaPorFiltroSaidaVO> iterator = pessoasCorporativas.iterator(); iterator.hasNext();) {
				ListarPessoaPorFiltroSaidaVO item = iterator.next();
				if (item.getCodigoPessoa() != vo.getPessoaDepositante()) {
					iterator.remove();
				}
			}
		}
	}
	
	private String dataFormatada(Date dt) {
		if (dt != null) {
			return SDF.format(dt);
		}
		return null;
	}
	
	public DepositoVO obterPeloCodigo(String codigo, int codUsuario, String ipCliente) {
		DepositoVO vo = new DepositoVO(Long.parseLong(codigo));
		
		return facade.obterPorChave(vo, codUsuario, ipCliente);
	}
	
	@Override
	public EstadoRegistro persistirDados(
			DepositoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		DepositoVO vo;

		final int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		
		vo = new DepositoVO();
		vo.setDataInclusao(new Date());

		model.obterValores(vo);
		vo.setCodigoResponsavelUltimaAtualizacao(usuarioId);
		vo.setDataHoraAtualizacao(new Date());
		
		facade.inserir(vo);
		return EstadoRegistro.NOVO;
	}

	@Override
	public void excluirRegistros(List<DepositoVO> voList)
			throws DEPIIntegrationException {
		
		facade.excluirLista(voList);
	}
	
	@Override
	public DepositoVO obterPorChave(DepositoVO vo, int codUsuario, String ipCliente) {
		return facade.obterPorChave(vo, codUsuario, ipCliente);
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

	public void prorrogar(DepositoEditarFormModel model, int codUsuarioLogado) {
		DepositoVO vo = new DepositoVO(Long.parseLong(model.getCodigo()));
		vo.setCodigoResponsavelUltimaAtualizacao(codUsuarioLogado);
		vo.setIndicadorDepositoProrrogado(ConstantesDEPI.INDICADOR_ATIVO);
		vo.setDataProrrogacao(BaseUtil.parserStringToSQLDate(model.getDataProrrogacao()));
		
		getFacade().prorrogar(vo, null);
	}

	public void cancelar(DepositoEditarFormModel model, int codUsuarioLogado) {
		DepositoVO vo = new DepositoVO(Long.parseLong(model.getCodigo()));
		vo.setCodigoResponsavelUltimaAtualizacao(codUsuarioLogado);
		vo.setIndicadorDepositoCancelado(ConstantesDEPI.INDICADOR_ATIVO);
		vo.setDtCancelamentoDepositoIdentificado(BaseUtil.parserStringToSQLDate(model.getDtCancelamentoDepositoIdentificado()));
		
		getFacade().cancelar(vo, null);
	}

}
