/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.UsuarioDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;
import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * A(O) Class EventoDAOImpl.
 */
@Repository
public class UsuarioDAOImpl extends JdbcDao implements UsuarioDAO {

	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioDAOImpl.class);
	
	
	/** A(O) data source. */
	@Autowired
	private DataSource dataSource;
	
	/** A(O) map sql parameter source. */
	private MapSqlParameterSource mapSqlParameterSource;
	

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
    public List<UsuarioVO> obterPorGrupoAcesso(GrupoAcessoVO vo) {
		
    	List<UsuarioVO> usuarios = null;
    	
		try{
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("usrgrp", vo.getCodigoGrupoAcesso());
			usuarios = (List<UsuarioVO>) getJdbcTemplate().query(QuerysDepi.USUARIOS_OBTERPORGRUPOACESSO, params, new UsuarioDataMapper());
			
	
		} catch(DataAccessException e){
			LOGGER.error("UsuarioDAOImpl - obterPorGrupoAcesso", e);
		} catch(Exception e){
			LOGGER.error("UsuarioDAOImpl - obterPorGrupoAcesso", e);
		}
        return usuarios;
    }

}