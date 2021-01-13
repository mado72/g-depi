package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;

/**
 * A(O) Class RelatorioEnvioRetornoAnaliticoDataMapper.
 */
public class RelatorioEnvioRetornoAnaliticoDataMapper implements RowMapper<RelatorioEnvioRetornoAnaliticoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public RelatorioEnvioRetornoAnaliticoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RelatorioEnvioRetornoAnaliticoVO analiticoVO = new RelatorioEnvioRetornoAnaliticoVO();
		
		return analiticoVO;
	}
}