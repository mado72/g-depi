package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class RelatorioDadosComplementaresDataMapper implements RowMapper<RelatorioDadosComplementaresVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public RelatorioDadosComplementaresVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RelatorioDadosComplementaresVO complementaresVO = new RelatorioDadosComplementaresVO();

		
		return complementaresVO;
	}

}

