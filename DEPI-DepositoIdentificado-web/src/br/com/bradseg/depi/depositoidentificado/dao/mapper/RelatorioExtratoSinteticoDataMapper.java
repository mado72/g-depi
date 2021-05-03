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

		RelatorioExtratoSinteticoVO vo = new RelatorioExtratoSinteticoVO();
		vo.setSituacaoEnvioRetorno(rs.getInt("CSIT_DEP_ARQ_TRNSF"));
		vo.setCodigoAutorizador(rs.getLong("CDEP_IDTFD"));
		vo.setValorRegistrado(rs.getBigDecimal("VDEP_IDTFD_ORIGN"));
		vo.setValorPago(rs.getBigDecimal("VTOT_DEP_IDTFD"));
		vo.setCodigoBanco(rs.getInt("CBCO"));
		vo.setCodigoAgencia(rs.getInt("CAG_BCRIA"));
		vo.setCodigoConta(rs.getLong("CCTA_CORR"));
		vo.setCodigoCia(rs.getInt("CINTRN_CIA_SEGDR"));

		return vo;
	}
}