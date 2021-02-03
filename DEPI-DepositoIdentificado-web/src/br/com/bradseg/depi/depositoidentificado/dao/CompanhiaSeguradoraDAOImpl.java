/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.CompanhiaSeguradoraDataMapper;
import br.com.bradseg.depi.depositoidentificado.util.QuerysDepi;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * Implementação de {@link CompanhiaSeguradoraDAO}
 * @author Marcelo Damasceno
 */
@Repository
public class CompanhiaSeguradoraDAOImpl extends JdbcDao implements
		CompanhiaSeguradoraDAO {
	
	private final static String WHR1 = "whr1";
	
	@Autowired
	private DataSource datasource;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		return datasource;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO#obterComRestricaoDeGrupoAcesso(java.lang.Integer)
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterComRestricaoDeGrupoAcesso(
			double codUsuario) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(WHR1, codUsuario);
		
		List<CompanhiaSeguradoraVO> lista = getJdbcTemplate().query(
				QuerysDepi.CIA_OBTERCOMRESTRICAODEGRUPOACESSO, params,
				new CompanhiaSeguradoraDataMapper());
		
		return lista;
	}
}
