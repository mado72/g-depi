package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * RowMapper para consultas utilizadas em queries tipo <code>SELECT 1 FROM ...</code> 
 * @author Marcelo Damasceno
 */
public class BooleanDataHelper implements RowMapper<Boolean> {
	
	@Override
	public Boolean mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getBoolean(1);
	}

}
