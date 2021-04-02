/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.ContaCorrenteRowMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Implementa os métodos de {@link ContaCorrenteDAO}
 * @author Marcelo Damasceno
 */
@Repository
public class ContaCorrenteDAOImpl extends JdbcDao implements ContaCorrenteDAO {
	
	private static final String PRM1 = "prm1";
	
	private static final String PRM2 = "prm2";
	
	private static final String PRM3 = "prm3";
	
	private static final String PRM4 = "prm4";
	
	private static final String PRM5 = "prm5";
	
	private static final String PRM6 = "prm6";
	
	private static final String PRM7 = "prm7";
	
	private static final String PRM8 = "prm8";
	
	private static final String PRM9 = "prm9";
	
	private static final String PRM10 = "prm10";
	
	private static final String PRM11 = "prm11";
	
	private static final String WHR1 = "whr1";
	
	private static final String WHR2 = "whr2";
	
	private static final String WHR3 = "whr3";

	@Autowired
	private DataSource dataSource;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		return dataSource;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#incluir(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void incluir(ContaCorrenteAutorizadaVO vo) {

		String ativo = obterAtivo(vo);
		if (ativo == null) {
			queryInsert(vo);
		}
		else if ("N".equals(ativo)){
			queryAtivar(vo);
		}
		else {
			throw new DEPIBusinessException("msg.erro.contacorrenteautorizada.duplicada", 
					String.valueOf(vo.getCia().getCodigoCompanhia()),
					String.valueOf(vo.getBanco().getCdBancoExterno()),
					String.valueOf(vo.getCodigoAgencia()),
					String.valueOf(vo.getContaCorrente()));
		}
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#alterar(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void alterar(ContaCorrenteAutorizadaVO vo) {
		
		if (! "S".equals(obterAtivo(vo))) {
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE2, vo.toString());
		}
		
		queryAlterar(vo);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#excluir(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void excluir(ContaCorrenteAutorizadaVO vo) {
		
		if (! "S".equals(obterAtivo(vo))) {
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE2, vo.toString());
		}
		
		queryInativar(vo);
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#isReferenciado(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public boolean isReferenciado(ContaCorrenteAutorizadaVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, vo.getBanco().getCdBancoExterno());
		params.addValue(WHR2, vo.getCodigoAgencia());
		params.addValue(WHR3, vo.getContaCorrente());
		
		try {
			Boolean existe = getJdbcTemplate().queryForObject(QuerysDepi.CONTACORRENTEAUTORIZADA_REFERENCIADO_CONTACORRENTEMOTIVODEPOSITO, params, Boolean.class);
			return existe != null && existe.booleanValue();
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
	 */
	@Override
	public List<ContaCorrenteAutorizadaVO> obterPorFiltro(FiltroUtil filtro) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		StringBuilder query = new StringBuilder(QuerysDepi.CONTACORRENTEAUTORIZADA_OBTERPORFILTRO);
		
		return consultarPorFiltro(filtro, query, params);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO#obterPorFiltroComRestricao(int, br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
	 */
	@Override
	public List<ContaCorrenteAutorizadaVO> obterPorFiltroComRestricao(
			int codUsuario, FiltroUtil filtro) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, codUsuario);
		
		StringBuilder query = new StringBuilder(QuerysDepi.CONTACORRENTEAUTORIZADA_OBTERPORFILTROCOMRESTRICAODEGRUPOACESSO);
		
		return consultarPorFiltro(filtro, query, params);
	}

	private List<ContaCorrenteAutorizadaVO> consultarPorFiltro(
			FiltroUtil filtro, StringBuilder query, MapSqlParameterSource params) {
		if (!filtro.getCriterios().isEmpty()) {
			query.append(filtro.getClausulasParciais(" AND ", true));
			params.addValues(filtro.getMapParamFiltro().getValues());
		}
		
		query.append(QuerysDepi.CONTACORRENTEAUTORIZADA_ORDERBY);
		
		List<ContaCorrenteAutorizadaVO> lista = getJdbcTemplate().query(query.toString(), params, new ContaCorrenteRowMapper());
		
		if (lista == null || lista.isEmpty() ) {
			throw new DEPIBusinessException(ConstantesDEPI.ERRO_SEMRESULTADO);
		}
		
		return lista;
	}

	private String obterAtivo(ContaCorrenteAutorizadaVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, vo.getBanco().getCdBancoExterno());
		params.addValue(WHR2, vo.getCodigoAgencia());
		params.addValue(WHR3, vo.getContaCorrente());
	
		try {
			return getJdbcTemplate()
					.queryForObject(QuerysDepi.CONTACORRENTEAUTORIZADA_EXISTS,
							params, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	private void queryAtivar(ContaCorrenteAutorizadaVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, vo.getBanco().getCdBancoExterno());
		params.addValue(WHR2, vo.getCodigoAgencia());
		params.addValue(WHR3, vo.getContaCorrente());
		params.addValue(PRM1, vo.getObservacao().toUpperCase());
		params.addValue(PRM2, vo.getCodigoResponsavelUltimaAtualizacao());
		
		int count = getJdbcTemplate().update(QuerysDepi.CONTACORRENTEAUTORIZADA_REATIVAR, params);
		
		if (count != 1) {
			throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_INCLUSAO);
		}
		
	}

	private void queryInsert(ContaCorrenteAutorizadaVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(PRM1, vo.getBanco().getCdBancoExterno());
		params.addValue(PRM2, vo.getCodigoAgencia());
		params.addValue(PRM3, vo.getContaCorrente());
		params.addValue(PRM4, vo.getCia().getCodigoCompanhia());
		params.addValue(PRM5, vo.getContaCorrente());
		params.addValue(PRM6, vo.getDigitoAgencia());
		params.addValue(PRM7, vo.getPrimeiroDigitoConta());
		params.addValue(PRM8, vo.getSegundoDigitoConta());
		params.addValue(PRM9, vo.getObservacao());
		params.addValue(PRM10, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(PRM11, vo.getTrps());
		
		int count = getJdbcTemplate().update(QuerysDepi.CONTACORRENTEAUTORIZADA_INSERT, params);
		if (count != 1) {
			throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_INCLUSAO);
		}
	}

	private void queryAlterar(ContaCorrenteAutorizadaVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, vo.getBanco().getCdBancoExterno());
		params.addValue(WHR2, vo.getCodigoAgencia());
		params.addValue(WHR3, vo.getContaCorrente());
		params.addValue(PRM1, vo.getObservacao());
		params.addValue(PRM2, vo.getCodigoResponsavelUltimaAtualizacao());
		
		int count = getJdbcTemplate().update(QuerysDepi.CONTACORRENTEAUTORIZADA_UPDATE, params);
		
		if (count != 1) {
			throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_ALTERACAO);
		}
		
	}

	private void queryInativar(ContaCorrenteAutorizadaVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue(WHR1, vo.getBanco().getCdBancoExterno());
		params.addValue(WHR2, vo.getCodigoAgencia());
		params.addValue(WHR3, vo.getContaCorrente());
		params.addValue(PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		
		int count = getJdbcTemplate().update(QuerysDepi.CONTACORRENTEAUTORIZADA_INATIVAR, params);
		
		if (count != 1) {
			throw new DEPIIntegrationException(ConstantesDEPI.Geral.ERRO_EXCLUSAO);
		}
		
	}

}
