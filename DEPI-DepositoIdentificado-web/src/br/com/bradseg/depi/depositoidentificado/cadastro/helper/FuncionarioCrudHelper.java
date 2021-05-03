package br.com.bradseg.depi.depositoidentificado.cadastro.helper;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.UsuarioFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.FuncionarioCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.Fornecedor;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * Classe auxiliar para o fluxo de cadastro de departamento.
 * 
 * @author Marcelo Damasceno
 */
public class FuncionarioCrudHelper implements
		CrudHelper<FuncionarioCampo, UsuarioVO, CrudForm> {

	private static final Logger LOGGER = LoggerFactory.getLogger(FuncionarioCrudHelper.class);
	
	private transient UsuarioFacade facade;
	
	public void setFacade(UsuarioFacade facade) {
		this.facade = facade;
	}
	
	// Métodos para o filtro

	@Override
	public String getChaveTituloConsultar() {
		return null;
	}

	@Override
	public String getChaveTituloListar() {
		return null;
	}

	@Override
	public FiltroConsultarForm<FuncionarioCampo> criarFiltroModel() {
		Fornecedor<Collection<FuncionarioCampo>> fornecedor = new Fornecedor<Collection<FuncionarioCampo>>() {
			
			@Override
			public Collection<FuncionarioCampo> get() {
				return Arrays.asList(FuncionarioCampo.values());
			}
			
		};
		
		Funcao<String, FuncionarioCampo> obterEntidade = new Funcao<String, FuncionarioCampo>() {
			
			@Override
			public FuncionarioCampo apply(String source) {
				if (source == null || source.trim().isEmpty()) {
					return null;
				}
				return FuncionarioCampo.valueOf(source);
			}
		};
		
		return new FiltroConsultarForm<>(fornecedor, obterEntidade);
	}

	@Override
	public List<UsuarioVO> processarCriterios(int codUsuario,
			List<CriterioConsultaVO<FuncionarioCampo>> criterios) {

		try {
			ArrayList<CriterioConsultaVO<?>> aux = new ArrayList<CriterioConsultaVO<?>>(criterios);
			
			FiltroUtil filtro = new FiltroUtil();
			filtro.setCriterios(aux);
			
			return facade.obterPorFiltro(filtro);
		} catch (IntegrationException e) {
			if (e.getCause() instanceof DataIntegrityViolationException) {
				DataIntegrityViolationException dataE = (DataIntegrityViolationException) e
						.getCause();
				if (dataE.getCause() instanceof SQLDataException) {
					SQLDataException sqlE = (SQLDataException) dataE.getCause();
					if (sqlE.getSQLState().contains("22001")) {
						LOGGER.error(
								"Falha ao processar criterios de consulta. Parâmetro inválido. SQLSTATE=22001",
								e);
						throw new DEPIIntegrationException(sqlE,
								"erro.SQLSTATE.22001");
					}
				}
			}
			
			LOGGER.error("Falha ao processar criterios de consulta", e);
			throw new DEPIIntegrationException(e);
			
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
	}

	// Métodos para o CRUD

	@Override
	public String getChaveTituloDetalhar() {
		return null;
	}

	@Override
	public String getChaveTituloAlterar() {
		return null;
	}

	@Override
	public String getChaveTituloIncluir() {
		return null;
	}

	@Override
	public CrudForm criarCrudModel() {
		return null;
	}

	@Override
	public void preencherFormularioEdicao(CrudForm model, int codUsuario,
			String ipCliente) throws DEPIIntegrationException {
	}

	@Override
	public EstadoRegistro persistirDados(CrudForm model, LoginVo usuarioLogado)
			throws DEPIIntegrationException {
		
		return null;
	}

	@Override
	public void excluirRegistros(List<UsuarioVO> voList)
			throws DEPIIntegrationException {
	}

	@Override
	public UsuarioVO obterPorChave(UsuarioVO vo, int codUsuario,
			String ipCliente) {
		FiltroUtil filtro = new FiltroUtil();
		filtro.getCriterios().add(
				new CriterioConsultaVO<FuncionarioCampo>(
						FuncionarioCampo.Matricula, TipoOperacao.IgualNumerico, 
						String.valueOf(vo.getCodigoUsuario()), ""));
		List<UsuarioVO> lista = facade.obterPorFiltro(filtro);
		
		if (lista.isEmpty()) {
			return null;
		}
		return lista.get(0);
	}

}
