/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.UsuarioDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * A(O) Class EventoDAOImpl.
 */
@Repository
public class UsuarioDAOImpl extends JdbcDao implements UsuarioDAO {

	private static final String PARAM_USRGRP = "usrgrp";
	private static final String PARAM_PRM1 = "prm1";


	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	
	
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
     * Método de obter por filtro um Usuário
     * @param vo - GrupoAcessoVO.
     * @return List<UsuarioVO>
     */
    @Override
	public List<UsuarioVO> obterPorGrupoAcesso(GrupoAcessoVO vo) {
		
		try{
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_USRGRP, vo.getCodigoGrupoAcesso());
			return getJdbcTemplate().query(QuerysDepi.USUARIOS_OBTERPORGRUPOACESSO, params, new UsuarioDataMapper());
			
		} catch(Exception e){
			LOGGER.error("UsuarioDAOImpl - obterPorGrupoAcesso", e);
			throw new DEPIIntegrationException(e);
		}
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.UsuarioDAO#obterPorFiltro(br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
	 */
	@Override
	public List<UsuarioVO> obterPorFiltro(FiltroUtil filtro) {
		
		LOGGER.debug("Iniciando a consulta por filtro");
		
		try {
	
	    	MapSqlParameterSource params = null;
	
	    	StringBuilder query = new StringBuilder(QuerysDepi.USUARIOS_OBTERPORFILTRO);
	    	
	        /**
	         * Parametros.
	         */
			if (!filtro.getCriterios().isEmpty()) {
				query.append(filtro.getClausulasParciais(" AND ", true));
				params = filtro.getMapParamFiltro();
			} 
			
			return getJdbcTemplate().query(query.toString(), params,
					new UsuarioDataMapper());

		} catch(Exception e){
			LOGGER.error("UsuarioDAOImpl - obterPorFiltro", e);
			throw new DEPIIntegrationException(e);
		}
		finally {
			LOGGER.debug("Realizada consulta por filtro");
		}
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.UsuarioDAO#obterPorCodigo(java.lang.Integer)
	 */
	@Override
	public UsuarioVO obterPorCodigo(Integer codigoUsuario) {
		
		LOGGER.debug("Iniciando a consulta por filtro");

		
		try {
			/**
			 * Parametros.
			 */
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue(PARAM_PRM1, codigoUsuario);
			
			UsuarioVO usuario;
			try {
				usuario = getJdbcTemplate().queryForObject(QuerysDepi.USUARIOS_OBTERPORCODIGO, params,
						new UsuarioDataMapper());
			} catch(Exception e){
				LOGGER.error("UsuarioDAOImpl - obterPorGrupoAcesso", e);
				throw new DEPIIntegrationException(e);
			}
			
			if (usuario == null) {
				throw new DEPIBusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
			}
			
			return usuario;
		}
		finally {
			LOGGER.debug("Realizada consulta por filtro");
		}
	}

}