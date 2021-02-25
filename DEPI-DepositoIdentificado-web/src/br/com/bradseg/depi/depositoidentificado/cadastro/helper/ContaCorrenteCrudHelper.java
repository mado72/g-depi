package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.ContaCorrenteEditarFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
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

	private transient ContaCorrenteFacade facade;

	public ContaCorrenteFacade getFacade() {
		return facade;
	}
	
	public void setFacade(ContaCorrenteFacade facade) {
		this.facade = facade;
	}
	
	private static final String TITLE_CONSULTAR = "title.contacorrenteautorizada.consultar";

	private static final String TITLE_LISTAR = "title.contacorrenteautorizada.listar";
	
	private static final String TITLE_ALTERAR = "title.contacorrenteautorizada.editar";
	
	private static final String TITLE_DETALHAR = "title.contacorrenteautorizada.detalhar";

	private static final String TITLE_DEPOSITO_INCLUIR = "title.contacorrenteautorizada.novo";
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
	public FiltroConsultarForm<ContaCorrenteAutorizadaCampo> criarFiltroModel() {
		FornecedorObjeto<Collection<ContaCorrenteAutorizadaCampo>> criterios = new FornecedorObjeto<Collection<ContaCorrenteAutorizadaCampo>>() {

			@Override
			public Collection<ContaCorrenteAutorizadaCampo> get() {
				return ContaCorrenteAutorizadaCampo.valuesForCriteria();
			}

		};

		Funcao<String, ContaCorrenteAutorizadaCampo> obterEntidadeCampo = new Funcao<String, ContaCorrenteAutorizadaCampo>() {

			@Override
			public ContaCorrenteAutorizadaCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return ContaCorrenteAutorizadaCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<ContaCorrenteAutorizadaCampo>(criterios, obterEntidadeCampo);
	}

	@Override
	public List<ContaCorrenteAutorizadaVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<ContaCorrenteAutorizadaCampo>> criterios) {
		
		ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(aux);

		return facade.obterPorFiltro(codUsuario, filtro);
	}
	
	// Métodos para atender ao CRUD

	@Override
	public ContaCorrenteEditarFormModel criarCrudModel() {
		return new ContaCorrenteEditarFormModel();
	}

	@Override
	public void preencherFormularioEdicao(ContaCorrenteEditarFormModel model)
			throws DEPIIntegrationException {
		
		ContaCorrenteAutorizadaVO vo = obterPeloCodigo(model.getCodigo());
		
		CompanhiaSeguradoraVO companhia = vo.getCia();
		model.setCias(Collections.singletonList(companhia));
		model.setCodigoCompanhia(BaseUtil.blankIfNull(companhia.getCodigoCompanhia()));
		
		model.setAgencia(BaseUtil.getValueMaskFormat("99999", BaseUtil.blankIfNull(vo.getCodigoAgencia()), true));
		model.setCodigoBanco(BaseUtil.getValueMaskFormat("9999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoExterno()), true));
		model.setDescricaoBanco(vo.getBanco().getDescricaoBanco());
		model.setContaCorrente(BaseUtil.getValueMaskFormat("9999999999999", BaseUtil.blankIfNull(vo.getContaCorrente()), true));
		model.setContaInterna(BaseUtil.getValueMaskFormat("999999", BaseUtil.blankIfNull(vo.getBanco().getCdBancoInterno()), true));
		model.setObservacao(vo.getObservacao());
		model.setTrps(BaseUtil.getValueMaskFormat("9999999999999", BaseUtil.blankIfNull(vo.getTrps()), true));
		model.setDescricaoAgencia(vo.getDescricaoAgencia());
	}

	public ContaCorrenteAutorizadaVO obterPeloCodigo(String codigo) {
		ContaCorrenteAutorizadaVO vo = new ContaCorrenteAutorizadaVO();
		
		String[] partes = codigo.split(";");
		vo.setCia(new CompanhiaSeguradoraVO(Integer.parseInt(partes[0])));
		vo.setBanco(new BancoVO(Integer.parseInt(partes[1])));
		vo.setCodigoAgencia(Integer.parseInt(partes[2]));
		vo.setContaCorrente(Long.parseLong(partes[3]));
		
		return facade.obterPorChave(vo);
	}

	@Override
	public EstadoRegistro persistirDados(
			ContaCorrenteEditarFormModel model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {

		boolean novo = model.getCodigo() == null || model.getCodigo().trim().isEmpty();
		
		ContaCorrenteAutorizadaVO vo;

		final int usuarioId = Integer.parseInt(usuarioLogado.getId().replace("\\D", ""));
		final int codCompanhia = Integer.parseInt(model.getCodigoCompanhia());
		
		if (novo) {
			vo = new ContaCorrenteAutorizadaVO();
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
		vo.setContaCorrente(Long.parseLong(model.getContaCorrente()));
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
	public void excluirRegistros(List<ContaCorrenteAutorizadaVO> voList)
			throws DEPIIntegrationException {
		
		facade.excluirLista(voList);
	}
	
	@Override
	public ContaCorrenteAutorizadaVO obterPorChave(ContaCorrenteAutorizadaVO vo) {
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
