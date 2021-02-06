/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * Faz o mapeamento para CompanhiaSeguradoraVO. Apenas recupera
 * o código da companhia, já que nome é acessível apenas pelo CICS. 
 *
 */
public class CompanhiaSeguradoraDataMapper implements
		RowMapper<CompanhiaSeguradoraVO> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public CompanhiaSeguradoraVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		CompanhiaSeguradoraVO vo = new CompanhiaSeguradoraVO();
		vo.setCodigoCompanhia(rs.getInt("CINTRN_CIA_SEGDR"));
		
		return vo;
	}

}
