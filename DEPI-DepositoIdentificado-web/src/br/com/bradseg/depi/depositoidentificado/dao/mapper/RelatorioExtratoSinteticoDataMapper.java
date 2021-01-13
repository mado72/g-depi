package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * A(O) Class RelatorioEnvioRetornoSinteticoDataMapper.
 */
public class RelatorioExtratoSinteticoDataMapper implements RowMapper<RelatorioExtratoSinteticoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public RelatorioExtratoSinteticoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RelatorioExtratoSinteticoVO analiticoVO = new RelatorioExtratoSinteticoVO();
		
		return analiticoVO;
	}
}