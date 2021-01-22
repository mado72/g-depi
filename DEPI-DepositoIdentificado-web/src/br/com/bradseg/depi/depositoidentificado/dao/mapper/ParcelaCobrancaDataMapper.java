package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class ParcelaCobrancaDataMapper implements RowMapper<ParcelaCobrancaVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public ParcelaCobrancaVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		ParcelaCobrancaVO parcelaCobrancaVO = new ParcelaCobrancaVO();

		
		return parcelaCobrancaVO;
	}

}