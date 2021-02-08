 /**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepartamentoCompanhiaDataMapper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepartamentoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.Funcao;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Dao que representa a entidade GrupoAcessoDAO
 * @author Globality
 */
@Repository
public class DepartamentoCompanhiaDAOImpl extends JdbcDao implements DepartamentoCompanhiaDAO {
	
	private static final String PRM1 = "prm1";

	private static final String PRM2 = "prm2";

	private static final String PRM3 = "prm3";

	private static final String WHR1 = "whr1";

	private static final String WHR2 = "whr2";
	
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoCompanhiaDAOImpl.class);

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	/** A(O) data source. */
	@Autowired
	private DataSource dataSource;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {		
		return dataSource;
	}

    /**
     * Método de obter por filtro um Grupo de Usuário
     * @param filtro - filtro CriterioFiltroUtil
     * @return List - Lista de GrupoAcessoVO
     */
    @Override
	public List<DepartamentoCompanhiaVO> obterPorFiltro(FiltroUtil filtro)  {

    	

        try {
        	MapSqlParameterSource params = null;
        	
        	final StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTOCOMPANHIA_SELECT_DEPTO_CIA_CLAUSULA_ATIVO);

	        /**
	         * Parametros.
	         */
			if (!filtro.getCriterios().isEmpty()) {
				String complemento = filtro.getClausulasParciais(" AND ", true);
				query.append(complemento);
				params = filtro.getMapParamFiltro();
			}
			
			query.append(QuerysDepi.DEPARTAMENTOCOMPANHIA_ORDERBY_DHORA_ULT_ATULZ_DESC);

			return getJdbcTemplate().query(query.toString(), params,
					new DepartamentoCompanhiaDataMapper());
        } finally {
        	LOGGER.info("obterPorFiltro(CriterioFiltroUtil filtro)"); 
        }
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.dao.DepartamentoCompanhiaDAO#persistir(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO)
     */
    @Override
    public void persistir(DepartamentoCompanhiaVO vo) {
    	
    	final Funcao<DepartamentoVO, Integer> extrairCodigo = new Funcao<DepartamentoVO, Integer>() {
			@Override
			public Integer apply(DepartamentoVO source) {
				return source.getCodigoDepartamento();
			}
		};
    	
    	MapSqlParameterSource params = new MapSqlParameterSource();
    	params.addValue(WHR1, vo.getCompanhia().getCodigoCompanhia());
    	
		List<DepartamentoVO> deptosPersistidos = getJdbcTemplate()
				.query(QuerysDepi.DEPARTAMENTOCOMPANHIA_OBTERDEPARTAMENTOS_PORCOMPANHIA,
						params, new DepartamentoDataMapper());
		
		List<DepartamentoVO> paraRemover = BaseUtil.obterItensSemIntersecao(
				deptosPersistidos, vo.getDeptos(), extrairCodigo);
		List<DepartamentoVO> paraSalvar = BaseUtil.obterItensSemIntersecao(
				vo.getDeptos(), deptosPersistidos, extrairCodigo);

		for (DepartamentoVO departamentoVO : paraRemover) {
			queryDesalocar(vo, departamentoVO);
		}
		
    	for (DepartamentoVO departamentoVO : paraSalvar) {
			queryAlocarOuRealocar(vo, departamentoVO);
		}
    }
	
	/**
	 * Desativa registro da associação depto x cia
	 * @param vo Contém dados da cia
	 * @param departamentoVO Depto
	 */
	private void queryDesalocar(DepartamentoCompanhiaVO vo,
			DepartamentoVO departamentoVO) {
		
		CompanhiaSeguradoraVO cia = vo.getCompanhia();

		int codigoCompanhia = cia.getCodigoCompanhia();
		int codigoDepartamento = departamentoVO.getCodigoDepartamento();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(WHR1, codigoDepartamento);
		params.addValue(WHR2, codigoCompanhia);
		
		int count = getJdbcTemplate().update(QuerysDepi.DEPARTAMENTO_INATIVAR,
				params);
		
		if (count == 0) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_NAOCONCLUIDO,
					"excluir",
					String.valueOf(codigoDepartamento),
					String.valueOf(codigoCompanhia));
		}
	}

	/**
	 * Ativa ou cria associação Depto x Cia
	 * @param vo Dados da Companhia
	 * @param departamentoVO Depto
	 */
	private void queryAlocarOuRealocar(DepartamentoCompanhiaVO vo,
			DepartamentoVO departamentoVO) {
		
		String indAtivo = queryObterStatusAtivo(vo, departamentoVO);
		
		if (indAtivo != null) {
			if (ConstantesDEPI.INDICADOR_INATIVO.equals(indAtivo)) {
				queryAtivar(vo, departamentoVO);
			}
		}
		else {
			queryInserir(vo, departamentoVO);
		}
		
	}

	/**
	 * Obtém o indicativo da associação Depto x Cia se está ativo ou inativo ou nulo.
	 * @param vo Cia
	 * @param departamentoVO Depto
	 * @return S = Ativo, N = Inativo, null quando não existe.
	 */
	private String queryObterStatusAtivo(DepartamentoCompanhiaVO vo,
			DepartamentoVO departamentoVO) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(WHR1, departamentoVO.getCodigoDepartamento());
		params.addValue(WHR2, vo.getCompanhia().getCodigoCompanhia());
		
		try {
			String indicativo = getJdbcTemplate().queryForObject(
					QuerysDepi.DEPARTAMENTOCOMPANHIA_EXISTS, params, String.class);
			return indicativo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Ativa associação Depto x Cia
	 * @param vo Cia
	 * @param departamentoVO Depto
	 */
	private void queryAtivar(DepartamentoCompanhiaVO vo,
			DepartamentoVO departamentoVO) {
		
		int codigoCompanhia = vo.getCompanhia().getCodigoCompanhia();
		int codigoDepartamento = departamentoVO.getCodigoDepartamento();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, codigoDepartamento);
		params.addValue(WHR2, codigoCompanhia);
		params.addValue(PRM1, vo.getCodigoResponsavelUltimaAtualizacao());

		int count = getJdbcTemplate().update(QuerysDepi.DEPARTAMENTOCOMPANHIA_ATIVAR, params);
		if (count == 0) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_NAOCONCLUIDO,
					"ativar",
					String.valueOf(codigoDepartamento),
					String.valueOf(codigoCompanhia));
		}
	}

	/**
	 * Insere nova associação Depto x Cia
	 * @param vo Cia
	 * @param departamentoVO Depto
	 */
	private void queryInserir(DepartamentoCompanhiaVO vo,
			DepartamentoVO departamentoVO) {
		
		int codigoCompanhia = vo.getCompanhia().getCodigoCompanhia();
		int codigoDepartamento = departamentoVO.getCodigoDepartamento();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PRM1, codigoDepartamento);
		params.addValue(PRM2, codigoCompanhia);
		params.addValue(PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		
		int count = getJdbcTemplate().update(QuerysDepi.DEPARTAMENTOCOMPANHIA_INSERT, params);
		if (count == 0) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_NAOCONCLUIDO,
					"inserir",
					String.valueOf(codigoDepartamento),
					String.valueOf(codigoCompanhia));
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.DepartamentoCompanhiaDAO#excluir(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO)
	 */
	@Override
	public void excluir(DepartamentoCompanhiaVO vo) {
		
		int codigoCompanhia = vo.getCompanhia().getCodigoCompanhia();
		
		DepartamentoVO departamentoVO = vo.getDepartamento();
		int codigoDepartamento = departamentoVO.getCodigoDepartamento();
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(WHR1, codigoDepartamento);
		params.addValue(WHR2, codigoCompanhia);
		
		int count = getJdbcTemplate().update(QuerysDepi.DEPARTAMENTOCOMPANHIA_INATIVAR, params);
		if (count == 0) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.DepartamentoCompanhia.ERRO_NAOCONCLUIDO,
					"desativar",
					String.valueOf(codigoDepartamento),
					String.valueOf(codigoCompanhia));
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.DepartamentoCompanhiaDAO#obterPorCompanhiaSeguradoa(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public DepartamentoCompanhiaVO obterPorCompanhiaSeguradora(
			CompanhiaSeguradoraVO cia) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, cia.getCodigoCompanhia());
		
		List<DepartamentoCompanhiaVO> lista = getJdbcTemplate().query(
				QuerysDepi.DEPARTAMENTOCOMPANHIA_OBTERPORCIA, params,
				new DepartamentoCompanhiaDataMapper());
		
		if (lista.isEmpty()) {
			return null;
		}
		
		// Agrupa os departamentos em uma única instância da associação
		
		DepartamentoCompanhiaVO depCia = lista.remove(0);
		List<DepartamentoVO> deptos = depCia.getDeptos();
		
		for (DepartamentoCompanhiaVO vo : lista) {
			deptos.addAll(vo.getDeptos());
		}
		
		return depCia;
	}

    @Override
    public synchronized Boolean isReferenciado(DepartamentoCompanhiaVO grupo) {

    	try {

			MapSqlParameterSource params = new MapSqlParameterSource();

			params.addValue(WHR1, grupo.getCompanhia().getCodigoCompanhia());
			params.addValue(WHR2, grupo.getDepartamento().getCodigoDepartamento());

			List<Map<String, Object>> lista = getJdbcTemplate().queryForList(
					QuerysDepi.GRUPOACESSO_REFERENCIADO_PARAMETRODEPOSITO,
					params);

            return (!lista.isEmpty());

        } finally {
        	LOGGER.info("isReferenciado(GrupoAcessoVO grupo)"); 
        }
    }

}