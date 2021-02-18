package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.ParametroDepositoEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm.EstadoCrud;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ParametroDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.FornecedorObjeto;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoPKVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Classe auxiliar para o fluxo de cadastro de motivo depósito.
 * 
 * @author Marcelo Damasceno
 */
public class ContaCorrenteCrudHelper implements
		CrudHelper<ParametroDepositoCampo, ParametroDepositoVO, ParametroDepositoEditarFormModel> {

	private static final String TITLE_DEPOSITO_CONSULTAR = "title.parametrodeposito.consultar";

	private static final String TITLE_DEPOSITO_LISTAR = "title.parametrodeposito.listar";
	
	private static final String TITLE_DEPOSITO_ALTERAR = "title.parametrodeposito.editar";
	
	private static final String TITLE_DEPOSITO_DETALHAR = "title.parametrodeposito.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.parametrodeposito.novo";

	private transient ParametroDepositoFacade facade;

	public ParametroDepositoFacade getFacade() {
		return facade;
	}
	
	public void setFacade(ParametroDepositoFacade facade) {
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
	public List<ParametroDepositoVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<ParametroDepositoCampo>> criterios) {

		List<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(
				criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltroComRestricaoDeGrupoAcesso(codUsuario, filtro);
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
	public ParametroDepositoEditarFormModel criarCrudModel() {
		return new ParametroDepositoEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(ParametroDepositoEditarFormModel m)
			throws DEPIIntegrationException {

		ParametroDepositoVO v = obterPeloCodigo(m.getCodigo());
		
		m.setCodigoApolice(v.getCodigoApolice());
		m.setCodigoBancoVencimento(v.getCodigoBancoVencimento());
		m.setCodigoBloqueto(v.getCodigoBloqueto());
		m.setCodigoCompanhia(String.valueOf(v.getCompanhia().getCodigoCompanhia()));
		m.setCodigoCpfCnpj(v.getCodigoCpfCnpj());
		m.setCodigoDepartamento(String.valueOf(v.getDepartamento().getCodigoDepartamento()));
		m.setCodigoDossie(v.getCodigoDossie());
		m.setCodigoEndosso(v.getCodigoEndosso());
		m.setCodigoItem(v.getCodigoItem());
		m.setCodigoMotivoDeposito(String.valueOf(v.getMotivoDeposito().getCodigoMotivoDeposito()));
		m.setCodigoParcela(v.getCodigoParcela());
		m.setCodigoProtocolo(v.getCodigoProtocolo());
		m.setCodigoRamo(v.getCodigoRamo());
		m.setCodigoSucursal(v.getCodigoSucursal());
		m.setCodigoTipo(v.getCodigoTipo());
		m.setDescricaoBancoVencimento(v.getDescricaoBancoVencimento());
		m.setDescricaoBasicaMotivo(v.getMotivoDeposito().getDescricaoBasica());
		m.setDescricaoDeposito(v.getDescricaoDeposito());
		m.setDescricaoDetalhadaMotivo(v.getMotivoDeposito().getDescricaoDetalhada());
		m.setNumeroDiasAposVencimento(String.valueOf(v.getNumeroDiasAposVencimento()));
		m.setOutrosDocumentosNecessarios(v.getOutrosDocumentosNecessarios());
		
		if (v.isReferenciadoDeposito()) {
			m.setReferenciadoDeposito(ParametroDepositoEditarFormModel.VALOR_SIM);
		}
		else {
			m.setReferenciadoDeposito(ParametroDepositoEditarFormModel.VALOR_NAO);
		}

		v.setCompanhia(obterCompanhia(v));
		m.setCias(Collections.singletonList(v.getCompanhia()));
		
		v.setDepartamento(obterDepartamento(v.getDepartamento()));
		m.setDeptos(Collections.singletonList(v.getDepartamento()));
		
		v.setMotivoDeposito(obterMotivo(v.getMotivoDeposito()));
		
		m.setDescricaoDetalhadaMotivo(v.getMotivoDeposito().getDescricaoDetalhada());
		m.setMotivos(Collections.singletonList(v.getMotivoDeposito()));
		
		m.setCodigo(new ParametroDepositoPKVO(
						v.getDepartamento().getCodigoDepartamento(), 
						v.getMotivoDeposito().getCodigoMotivoDeposito(), 
						v.getCompanhia().getCodigoCompanhia())
				.toString());
	}

	private DepartamentoVO obterDepartamento(DepartamentoVO departamento) {
		return facade.obterDepartamento(departamento);
	}

	private CompanhiaSeguradoraVO obterCompanhia(ParametroDepositoVO v) {
		return facade.obterCompanhia(v.getCompanhia());
	}
	
	private MotivoDepositoVO obterMotivo(MotivoDepositoVO motivoDeposito) {
		return facade.obterMotivo(motivoDeposito);
	}

	/**
	 * Prepara o formulário para inclusão.
	 * @param codUsuario Usuário logado
	 * @param model Formulário
	 * @throws DEPIIntegrationException Erro de lógica
	 */
	public void prepararFormularioInclusao(
			int codUsuario, ParametroDepositoEditarFormModel model)
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
			List<DepartamentoVO> deptos = obterDepatamentos(codUsuario, companhia);
			model.setDeptos(deptos);
			if (! deptos.isEmpty()) {
				DepartamentoVO depto = deptos.get(0);
				model.setCodigoDepartamento(String.valueOf(depto.getCodigoDepartamento()));
			}
		}
		else {
			model.setCodigoCompanhia(null);
			model.setDeptos(null);
		}
		
		model.setMotivos(obterMotivos());
	}
	
	/**
	 * Lista as companhias a que o usuário logado tem acesso
	 * @param codUsuario Código do usuário logado 
	 * @return Lista de companhias
	 */
	private List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
		return facade.obterCompanhias(codUsuario);
	}

	private List<DepartamentoVO> obterDepatamentos(
			int codUsuario, CompanhiaSeguradoraVO companhia) {
		return facade.obterComRestricaoGrupoAcesso(codUsuario, companhia);
	}

	/**
	 * @return
	 */
	private List<MotivoDepositoVO> obterMotivos() {
		return facade.obterMotivos();
	}

	private ParametroDepositoVO obterPeloCodigo(String codigos) {
		ParametroDepositoPKVO pkvo = new ParametroDepositoPKVO(codigos);
		
		ParametroDepositoVO vo = new ParametroDepositoVO(
				pkvo.getCodigoDepartamento(), pkvo.getCodigoMotivo(),
				pkvo.getCodigoCompanhia());
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(
			ParametroDepositoEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		ParametroDepositoVO instancia;

		if (novo) {
			instancia = new ParametroDepositoVO();
			
			int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
			instancia.setCodigoResponsavelUltimaAtualizacao(usuarioId);
			
			instancia.setCompanhia(new CompanhiaSeguradoraVO(Integer.parseInt(model.getCodigoCompanhia())));
			instancia.setMotivoDeposito(new MotivoDepositoVO(Integer.parseInt(model.getCodigoMotivoDeposito())));
			instancia.setDepartamento(new DepartamentoVO(Integer.parseInt(model.getCodigoDepartamento())));
		}
		else {
			instancia = obterPeloCodigo(model.getCodigo());
		}

		instancia.setCodigoApolice(model.getCodigoApolice());
		instancia.setCodigoBancoVencimento(model.getCodigoBancoVencimento());
		instancia.setCodigoBloqueto(model.getCodigoBloqueto());
		instancia.setCodigoCpfCnpj(model.getCodigoCpfCnpj());
		instancia.setCodigoDossie(model.getCodigoDossie());
		instancia.setCodigoEndosso(model.getCodigoEndosso());
		instancia.setCodigoItem(model.getCodigoItem());
		instancia.setCodigoParcela(model.getCodigoParcela());
		instancia.setCodigoProtocolo(model.getCodigoProtocolo());
		instancia.setCodigoRamo(model.getCodigoRamo());
		instancia.setCodigoSucursal(model.getCodigoSucursal());
		instancia.setCodigoTipo(model.getCodigoTipo());
		
		if (model.getNumeroDiasAposVencimento() != null) {
			instancia.setNumeroDiasAposVencimento(Integer.parseInt(model
					.getNumeroDiasAposVencimento()));
		}
		
		instancia.setDescricaoBasicaMotivo(model.getDescricaoBasicaMotivo());
		instancia.setDescricaoDetalhadaMotivo(model.getDescricaoDetalhadaMotivo());
		instancia.setOutrosDocumentosNecessarios(model.getOutrosDocumentosNecessarios());
		
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
	public void excluirRegistros(List<ParametroDepositoVO> voList)
			throws DEPIIntegrationException {
		// FIXME Criar exclusão por listta
		// facade.excluirLista(voList);
	}
	
	@Override
	public ParametroDepositoVO obterPorChave(ParametroDepositoVO vo) {
		return facade.obterPorChave(vo);
	}

}
