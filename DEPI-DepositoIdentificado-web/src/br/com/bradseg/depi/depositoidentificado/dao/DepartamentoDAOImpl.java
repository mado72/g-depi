package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.BooleanDataHelper;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.DepartamentoDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Dao que representa a entidade Departamento
 * @author Globality
 */
@Repository
public class DepartamentoDAOImpl extends JdbcDao implements DepartamentoDAO {

	private static final String PARAM_PRM1 = "prm1";
	
	private static final String PARAM_PRM2 = "prm2";
	
	private static final String PARAM_PRM3 = "prm3";

	private static final String PARAM_WHR1 = "whr1";
	
	private static final String PARAM_WHR2 = "whr2";

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoDAOImpl.class);
	
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
	 * {@inheritDoc}
	 */
	@Override
	public void inserir(DepartamentoVO vo) {
	
		try {
			
			DepartamentoVO existente = obterRegistroPorSiglaDepartamento(vo);
			
			if (existente == null) {
				LOGGER.info("Inserindo novo registro para a sigla {}", vo.getSiglaDepartamento());
				queryInsert(vo);
			}
			else {
				
				if (existente.getIndicadoRegistroAtivo().equals(ConstantesDEPI.INDICADOR_ATIVO)) {
					throw new DEPIIntegrationException(
							ConstantesDEPI.ERRO_REGISTRO_JA_CADASTRADO2,
							"Sigla", vo.getSiglaDepartamento());
				}

				LOGGER.info("Reativando registro com a sigla {} para ativar e atualizar.", vo.getSiglaDepartamento());
				vo.setCodigoDepartamento(existente.getCodigoDepartamento());
				queryAtivar(vo);
			}
	    	
		} finally {
	    	LOGGER.info( "inserir(DepartamentoVO vo)"); 
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(DepartamentoVO vo){
	    	
	   	try {
	   		DepartamentoVO existente = obterRegistroPorSiglaDepartamento(vo);
	   		
	   		if (existente != null) {
	   			
				if (existente.getIndicadoRegistroAtivo().equals(ConstantesDEPI.INDICADOR_INATIVO)) {
					throw new DEPIIntegrationException(
							ConstantesDEPI.Departamento.ERRO_CADASTRADO_INATIVO,
							vo.getSiglaDepartamento());
				}
				
				if (existente.getCodigoDepartamento() != vo.getCodigoDepartamento()) {
					throw new DEPIIntegrationException(
							ConstantesDEPI.ERRO_REGISTRO_JA_CADASTRADO2,
							vo.getSiglaDepartamento(), "Sigla");
				}
	   		}
			
			Integer count = queryUpdate(vo);
			
			if (count == 0) {
				throw new DEPIBusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
			}
	
	    } finally {
	    	LOGGER.info("alterar(DepartamentoVO vo)"); 
	    }
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(DepartamentoVO vo) {
	    try {
	    	queryInativar(vo);
	    } finally {
	    	LOGGER.info("excluir(DepartamentoVO vo)"); 
	    } 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(List<DepartamentoVO> vos) {
		try { 
	        for (DepartamentoVO dep : vos) {
	        	excluir(dep);
	        }
		} finally {
			LOGGER.info("excluir(List<DepartamentoVO> vos)"); 
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isReferenciado(DepartamentoVO vo) {
	    try {
			
	    	return queryDepartamentoReferenciadoCompanhia(vo);
			 
	    } finally {
	    	LOGGER.info("isReferenciado(DepartamentoVO vo)"); 
	    }
	}

	/**
     * {@inheritDoc}
     */
	@Override
    public List<DepartamentoVO> obterComRestricao(int codigoCia, int codigoUsuario, Tabelas tabela)    {

		try {
			
			final String query = montarDaQuery(tabela, "");
			final String msg = escolhaMensagem(tabela);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, codigoCia);
			params.addValue(PARAM_WHR2, codigoUsuario);

			List<DepartamentoVO> departamentoVO = getJdbcTemplate().query(
					query, params, new DepartamentoDataMapper());

			if (departamentoVO == null || departamentoVO.isEmpty()) {
				throw new DEPIIntegrationException(
						ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS,
						msg);
			}
			
			return departamentoVO;
			
		} finally {
			LOGGER.info("obterComRestricaoDeGrupoAcesso(int codigoCia, BigDecimal codigoUsuario, Tabelas e)");
		}

	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO#obterComRestricaoDeGrupoAcesso(int, int, br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas, java.lang.String)
	 */
	@Override
	public DepartamentoVO obterComRestricaoDeGrupoAcesso(int codigoCia,
			int codigoUsuario, Tabelas tabela, String siglaDepto) {

		try {
			
			final String condicao = montarRestricao(tabela);
			final String query = montarDaQuery(tabela, condicao);
			final String msg = escolhaMensagem(tabela);

			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, codigoCia);
			params.addValue(PARAM_WHR2, codigoUsuario);
			params.addValue(PARAM_PRM1, siglaDepto);

			DepartamentoVO departamentoVO = getJdbcTemplate().queryForObject(
					query, params, new DepartamentoDataMapper());

			if (departamentoVO == null) {
				throw new DEPIIntegrationException(
						ConstantesDEPI.ERRO_RELACIONAMENTOS_NAO_CADASTRADOS, msg);
			}
			
			return departamentoVO;
			
		} finally {
			LOGGER.info("obterComRestricaoDeGrupoAcesso(int codigoCia, BigDecimal codigoUsuario, Tabelas e)");
		}

	}
	
	/**
	 * Obtém registros de Departamento por filtro
	 * @param vo CompanhiaSeguradoraVO
	 * @return List Retorna um lista de VO de Departamentos filtrados.
	 * @Exceção de aplicação
	 */
	@Override
	public List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) {
		
	    try {
	    	
	    	MapSqlParameterSource params = new MapSqlParameterSource();
	    	params.addValue(PARAM_WHR1, vo.getCodigoCompanhia());
	    	
			List<DepartamentoVO> departamentoVO = getJdbcTemplate().query(
					QueriesDepi.DEPARTAMENTO_OBTERPORCOMPANHIASEGURADORA,
					params, new DepartamentoDataMapper());
	    	
	    	return departamentoVO;
	
	    } finally {
	    	LOGGER.info( "obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo)"); 
	    }
	
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO#obterPorCompanhiaSeguradora(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
	 */
	@Override
	public DepartamentoVO obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo,
			DepartamentoVO deptoVO) {
		
		StringBuilder query = new StringBuilder(QueriesDepi.DEPARTAMENTO_OBTERPORCOMPANHIASEGURADORA);
		query.append(" AND DBPROD.DEPTO_DEP_IDTFD.CSGL_DEPTO_DEP = :prm1");
	    
	    try {
	    	
	    	MapSqlParameterSource params = new MapSqlParameterSource();
	    	params.addValue(PARAM_WHR1, vo.getCodigoCompanhia());
	    	params.addValue(PARAM_PRM1, deptoVO.getSiglaDepartamento());
	    	
	    	DepartamentoVO departamentoVO = getJdbcTemplate().queryForObject(query.toString(), params, new DepartamentoDataMapper());       
	    	
	    	return departamentoVO;
	
	    } finally {
	    	LOGGER.info( "obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo)"); 
	    }
	}
	
	private String escolhaMensagem(Tabelas e) {
		final String msg;
		if (e.equals(Tabelas.GRUPO_ACESSO)) {
			msg = " um Grupo de Acesso vinculado ao usuário.";
		} else if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
			msg = " um Parametro de Depósito ou Grupo de Acesso vinculado ao usuário.";
		} else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
			msg = " uma Associação de Motivo ou Grupo de Acesso vinculado ao usuário.";
		} else if (e.equals(Tabelas.DEPOSITO)) {
			msg = " um Depósito ou Grupo de Acesso vinculado ao usuário.";
		} else {
			throw new IntegrationException("Enum inválido.");
		}
		return msg;
	}
	
	private String montarDaQuery(Tabelas e, String restricaoAdicional) {
		final String query;

		if (e.equals(Tabelas.GRUPO_ACESSO)) {
			query = QueriesDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEGRUPOACESSO.replaceAll("%s", restricaoAdicional);
		} else if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
			query = QueriesDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEPARAMETRODEPOSITO.replaceAll("%s", restricaoAdicional);
		} else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
			query = QueriesDepi.DEPARTAMENTO_OBTERCOMRESTRICAODECONTACORRENTEMOTIVODEPOSITO.replaceAll("%s", restricaoAdicional);
		} else if (e.equals(Tabelas.DEPOSITO)) {
			query = QueriesDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEDEPOSITO.replaceAll("%s", restricaoAdicional);
		} else {
			throw new IntegrationException("Enum inválido.");
		}
		return query;
	}
	
	private String montarRestricao(Tabelas e) {
		final String restricao;
		
		if (e.equals(Tabelas.GRUPO_ACESSO)) {
			restricao = " AND D.CSGL_DEPTO_DEP = :prm1";
		} else if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
			restricao = " AND G.CDEPTO_DEP_IDTFD = :prm1";
		} else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
			restricao = " AND MD.CMOTVO_DEP_IDTFD = :prm1";
		} else if (e.equals(Tabelas.DEPOSITO)) {
			restricao = " AND DEP.DEP_IDTFD = :prm1";
		} else {
			throw new IntegrationException("Enum inválido.");
		}
		return restricao;
	}

    /**
	 * {@inheritDoc}
	 */
	@Override
	public DepartamentoVO obterPorChave(DepartamentoVO vo) {
	
	    try {
			
	    	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
			
			DepartamentoVO departamentoVO = getJdbcTemplate().queryForObject(
					QueriesDepi.DEPARTAMENTO_OBTERPORCHAVE, params,
					new DepartamentoDataMapper());
			
			return departamentoVO;
	    } catch (EmptyResultDataAccessException e) {
	    	return null;
	    } finally {
	    	LOGGER.info("obterPorChave(DepartamentoVO vo) "); 
	    }
	
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DepartamentoVO> obterPorFiltro(FiltroUtil filtro) {
	
	    try {
			
	    	StringBuilder query = new StringBuilder(QueriesDepi.DEPARTAMENTO_OBTERTODOS);
	    	
	    	MapSqlParameterSource params = null;
	    	
	        /**
	         * Parametros.
	         */
			if (!filtro.getCriterios().isEmpty()) {
				query.append(filtro.getClausulaWhereFiltro());
				params = filtro.getMapParamFiltro();
			} 
			
			return getJdbcTemplate().query(query.toString(), params, new DepartamentoDataMapper());
			 
	    } finally {
	    	LOGGER.info("obterPorChave(DepartamentoVO vo) "); 
	    }
	
	}
	
	/**
     * Obtém registro por sigla departamento.
     * @param vo Contém a sigla a ser pesquisada
     * @return O primeiro item encontrado
     */
    private DepartamentoVO obterRegistroPorSiglaDepartamento(DepartamentoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
   
		params.addValue(PARAM_WHR1, vo.getSiglaDepartamento());

		List<DepartamentoVO> lista = getJdbcTemplate().query(
				QueriesDepi.DEPARTAMENTO_EXISTS, params, new DepartamentoDataMapper());
		 
		if (!lista.isEmpty()) {
			
			return lista.get(0);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO#obterDeListaSiglas(java.util.List)
	 */
	@Override
	public List<DepartamentoVO> obterDeListaSiglas(List<String> siglas) {
		MapSqlParameterSource params = new MapSqlParameterSource(PARAM_WHR1, siglas);
		
		return getJdbcTemplate().query(QueriesDepi.DEPARTAMENTO_OBTERPORSIGLAS,
				params, new DepartamentoDataMapper());
	}

    private void queryAtivar(DepartamentoVO vo) {
		MapSqlParameterSource paramsIns = new MapSqlParameterSource();
		paramsIns.addValue(PARAM_PRM1, vo.getSiglaDepartamento());        	
		paramsIns.addValue(PARAM_PRM2, vo.getNomeDepartamento());
		paramsIns.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		paramsIns.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
						
		getJdbcTemplate().update(QueriesDepi.DEPARTAMENTO_ATIVAR, paramsIns);
	}

	private void queryInativar(DepartamentoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(PARAM_PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());

		getJdbcTemplate().update(QueriesDepi.DEPARTAMENTO_INATIVAR, params);
	}
	
	private void queryInsert(DepartamentoVO vo) {
		MapSqlParameterSource paramsIns = new MapSqlParameterSource();
		paramsIns.addValue(PARAM_PRM1, vo.getSiglaDepartamento());        	
		paramsIns.addValue(PARAM_PRM2, vo.getNomeDepartamento());
		paramsIns.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		
		getJdbcTemplate().update(QueriesDepi.DEPARTAMENTO_INSERT, paramsIns);
	}
	
	private Integer queryUpdate(DepartamentoVO vo) {
		MapSqlParameterSource paramsUpd = new MapSqlParameterSource();

		paramsUpd.addValue(PARAM_PRM1, vo.getSiglaDepartamento());
		paramsUpd.addValue(PARAM_PRM2, vo.getNomeDepartamento());
		paramsUpd.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		paramsUpd.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
		
		return getJdbcTemplate().update(QueriesDepi.DEPARTAMENTO_UPDATE, paramsUpd);
	}
	
	private boolean queryDepartamentoReferenciadoCompanhia(
			DepartamentoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		// Tratamento para evitar erro -4460
		// Ref.: https://www.ibm.com/support/pages/db2-sql-query-select-gives-sql-errorcode-4460
		String query = QueriesDepi.DEPARTAMENTO_REFERENCIADO_COMPANHIADEPARTAMENTO
				.replaceAll(String.format(":%s", PARAM_WHR1),
						String.valueOf(vo.getCodigoDepartamento()));
		
		List<Boolean> exists = getJdbcTemplate().query(
				query,
				params, new BooleanDataHelper());
		return ! exists.isEmpty() && exists.get(0);
	}
	
}