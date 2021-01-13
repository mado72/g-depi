package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class ParametroDepositosDataMapper implements RowMapper<ParametroDepositoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public ParametroDepositoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		ParametroDepositoVO complementaresVO = new ParametroDepositoVO();

		
		return complementaresVO;
	}

}
