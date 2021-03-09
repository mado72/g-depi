/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;

/**
 * Mapeia Agencia
 * @author Marcelo Damasceno
 */
public class AgenciaDataMapper implements RowMapper<AgenciaVO> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public AgenciaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AgenciaVO vo = new AgenciaVO();
		vo.setCdBancoExterno(rs.getInt("CBCO"));
		vo.setCdAgenciaExterno(rs.getInt("CAG_BCRIA"));
		
		return vo;
	}

}
