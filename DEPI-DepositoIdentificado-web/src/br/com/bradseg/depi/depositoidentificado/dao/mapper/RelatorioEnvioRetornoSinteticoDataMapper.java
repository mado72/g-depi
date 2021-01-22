package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;

/**
 * A(O) Class RelatorioEnvioRetornoAnaliticoDataMapper.
 */
public class RelatorioEnvioRetornoSinteticoDataMapper implements RowMapper<RelatorioEnvioRetornoSinteticoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public RelatorioEnvioRetornoSinteticoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RelatorioEnvioRetornoSinteticoVO analiticoVO = new RelatorioEnvioRetornoSinteticoVO();
		
		return analiticoVO;
	}
}

