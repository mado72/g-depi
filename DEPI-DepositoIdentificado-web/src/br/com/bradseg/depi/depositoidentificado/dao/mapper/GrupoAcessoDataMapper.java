package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class GrupoAcessoDataMapper implements RowMapper<GrupoAcessoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public GrupoAcessoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		GrupoAcessoVO grupoAcessoVO = new GrupoAcessoVO();
		
		
		return grupoAcessoVO;
	}

}


