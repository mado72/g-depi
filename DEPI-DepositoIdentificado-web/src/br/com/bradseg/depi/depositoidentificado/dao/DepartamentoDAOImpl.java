package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
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
    public List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, double codigoUsuario, Tabelas e)    {

		try {
			
			final String query;
			final String msg;

			if (e.equals(Tabelas.GRUPO_ACESSO)) {
				query = QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEGRUPOACESSO;
				msg = " um Grupo de Acesso vinculado ao usuário.";
			} else if (e.equals(Tabelas.PARAMETRO_DEPOSITO)) {
				query = QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEPARAMETRODEPOSITO;
				msg = " um Parametro de Depósito ou Grupo de Acesso vinculado ao usuário.";
			} else if (e.equals(Tabelas.CONTA_CORRENTE_MOTIVO_DEPOSITO)) {
				query = QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODECONTACORRENTEMOTIVODEPOSITO;
				msg = " uma Associação de Motivo ou Grupo de Acesso vinculado ao usuário.";
			} else if (e.equals(Tabelas.DEPOSITO)) {
				query = QuerysDepi.DEPARTAMENTO_OBTERCOMRESTRICAODEDEPOSITO;
				msg = " um Depósito ou Grupo de Acesso vinculado ao usuário.";
			} else {
				throw new IntegrationException("Enum inválido.");
			}

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

    /**
	 * {@inheritDoc}
	 */
	@Override
	public DepartamentoVO obterPorChave(DepartamentoVO vo) {
	
	    try {
			
	    	MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
			
			List<DepartamentoVO> departamentoVO = getJdbcTemplate().query(
					QuerysDepi.DEPARTAMENTO_OBTERPORCHAVE, params,
					new DepartamentoDataMapper());
			
			return departamentoVO.get(0);
			 
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
			
	    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERTODOS);
	    	
	    	MapSqlParameterSource params = null;
	    	
	        /**
	         * Parametros.
	         */
			if (!filtro.getCriterios().isEmpty()) {
				query.append(filtro.getClausaWhereFiltro());
				params = filtro.getMapParamFiltro();
			} 
			
			return getJdbcTemplate().query(query.toString(), params, new DepartamentoDataMapper());
			 
	    } finally {
	    	LOGGER.info("obterPorChave(DepartamentoVO vo) "); 
	    }
	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DepartamentoVO> obterTodos() {
	
	    try {
			
			return getJdbcTemplate().query(QuerysDepi.DEPARTAMENTO_OBTERTODOS,
					new MapSqlParameterSource(), new DepartamentoDataMapper());
			 
	    } finally {
	    	LOGGER.info("obterPorTodos() "); 
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
				QuerysDepi.DEPARTAMENTO_EXISTS, params, new DepartamentoDataMapper());
		 
		if (!lista.isEmpty()) {
			
			return lista.get(0);
		}
		
		return null;
	}

    /**
     * Obtém registros de Departamento por filtro
     * @param vo CompanhiaSeguradoraVO
     * @return List Retorna um lista de VO de Departamentos filtrados.
     * @Exceção de aplicação
     */
    @Override
    public List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) {
    	
    	StringBuilder query = new StringBuilder(QuerysDepi.DEPARTAMENTO_OBTERPORCOMPANHIASEGURADORA);
        
        try {
        	
        	MapSqlParameterSource params = new MapSqlParameterSource();
        	params.addValue(PARAM_WHR1, vo.getCodigoCompanhia());
        	
        	List<DepartamentoVO> departamentoVO = getJdbcTemplate() .query(query.toString(), params, new DepartamentoDataMapper());       
        	
        	return departamentoVO;

        } finally {
        	LOGGER.info( "obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo)"); 
        }

    }
 
	private void queryAtivar(DepartamentoVO vo) {
		MapSqlParameterSource paramsIns = new MapSqlParameterSource();
		paramsIns.addValue(PARAM_PRM1, vo.getSiglaDepartamento());        	
		paramsIns.addValue(PARAM_PRM2, vo.getNomeDepartamento());
		paramsIns.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		paramsIns.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
						
		getJdbcTemplate().update(QuerysDepi.DEPARTAMENTO_ATIVAR, paramsIns);
	}

	private void queryInativar(DepartamentoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(PARAM_PRM1, vo.getCodigoResponsavelUltimaAtualizacao());
		params.addValue(PARAM_WHR1, vo.getCodigoDepartamento());

		getJdbcTemplate().update(QuerysDepi.DEPARTAMENTO_INATIVAR, params);
	}
	
	private void queryInsert(DepartamentoVO vo) {
		MapSqlParameterSource paramsIns = new MapSqlParameterSource();
		paramsIns.addValue(PARAM_PRM1, vo.getSiglaDepartamento());        	
		paramsIns.addValue(PARAM_PRM2, vo.getNomeDepartamento());
		paramsIns.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		
		getJdbcTemplate().update(QuerysDepi.DEPARTAMENTO_INSERT, paramsIns);
	}
	
	private Integer queryUpdate(DepartamentoVO vo) {
		MapSqlParameterSource paramsUpd = new MapSqlParameterSource();

		paramsUpd.addValue(PARAM_PRM1, vo.getSiglaDepartamento());
		paramsUpd.addValue(PARAM_PRM2, vo.getNomeDepartamento());
		paramsUpd.addValue(PARAM_PRM3, vo.getCodigoResponsavelUltimaAtualizacao());
		paramsUpd.addValue(PARAM_WHR1, vo.getCodigoDepartamento());
		
		return getJdbcTemplate().update(QuerysDepi.DEPARTAMENTO_UPDATE, paramsUpd);
	}
	
	private boolean queryDepartamentoReferenciadoCompanhia(
			DepartamentoVO vo) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		// Tratamento para evitar erro -4460
		// Ref.: https://www.ibm.com/support/pages/db2-sql-query-select-gives-sql-errorcode-4460
		String query = QuerysDepi.DEPARTAMENTO_REFERENCIADO_COMPANHIADEPARTAMENTO
				.replaceAll(String.format(":%s", PARAM_WHR1),
						String.valueOf(vo.getCodigoDepartamento()));
		
		List<Boolean> exists = getJdbcTemplate().query(
				query,
				params, new BooleanDataHelper());
		return ! exists.isEmpty() && exists.get(0);
	}
	
}