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
	


	private static final String CLAUSULA_CIA = " AND G.CINTRN_CIA_SEGDR = :param1";

	private static final String PARAM_SUBST = "%s";
	private static final String PARAM1 = "param1";
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
	 * @see br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO#obterCias()
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterCias() {
		List<CompanhiaSeguradoraVO> cias = getJdbcTemplate().query(
				QuerysDepi.DEPARTAMENTOCOMPANHIA_OBTERCIAS,
				new MapSqlParameterSource(),
				new CompanhiaSeguradoraDataMapper());
		return cias;
	}
	

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO#obterComRestricaoDeGrupoAcesso(java.lang.Integer)
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterComRestricaoDeGrupoAcesso(
			int codUsuario) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(WHR1, codUsuario);
		
		List<CompanhiaSeguradoraVO> lista = getJdbcTemplate().query(
				QuerysDepi.CIA_OBTERCOMRESTRICAODEGRUPOACESSO.replaceAll(PARAM_SUBST, ""), params,
				new CompanhiaSeguradoraDataMapper());
		
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO#obterComRestricaoDeGrupoAcesso(int, int)
	 */
	@Override
	public CompanhiaSeguradoraVO obterPorChave(CompanhiaSeguradoraVO vo) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue(WHR1, vo.getCodigoCompanhia());
		
		return getJdbcTemplate().queryForObject(
				QuerysDepi.CIA_OBTERCIA_PORCHAVE, params,
				new CompanhiaSeguradoraDataMapper());
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO#obterComRestricaoDeGrupoAcesso(int, int)
	 */
	@Override
	public CompanhiaSeguradoraVO obterComRestricaoDeGrupoAcesso(
			int codUsuario, int codCompanhia) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		String query = QuerysDepi.CIA_OBTERCOMRESTRICAODEGRUPOACESSO.replaceAll(PARAM_SUBST, CLAUSULA_CIA);
		
		params.addValue(WHR1, codUsuario);
		params.addValue(PARAM1, codCompanhia);
		
		CompanhiaSeguradoraVO instancia = getJdbcTemplate().queryForObject(
				query, params, new CompanhiaSeguradoraDataMapper());
		return instancia;
	}
}
