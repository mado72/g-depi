package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.LancamentoDepositoVO;

/**
 * Mapeia LancamentoDepositoVO 
 * @author Globality
 */
public class LancamentoDepositoDataMapper implements RowMapper<LancamentoDepositoVO> {

	@Override
	public LancamentoDepositoVO mapRow(ResultSet rs, int index)
			throws SQLException {
		
		LancamentoDepositoVO vo = new LancamentoDepositoVO();
		vo.setCodigoLancamentoDeposito(rs.getLong("CDEP_IDTFD"));
		
		vo.setAgenciaAcolhedora(getAgencia(rs, "CAG_BCRIA_ACOLH"));
		vo.setValorDinheiro(rs.getBigDecimal("VDEP_IDTFD_DINHE"));
		vo.setValorCheq(rs.getBigDecimal("VDEP_IDTFD_CHEQ"));
		vo.setValorTotalDeposito(rs.getBigDecimal("VTOT_DEP_IDTFD"));
		
		vo.setBancoCheque(getBanco(rs, "CBCO_CHEQ_LCTO"));
		vo.setAgenciaCheque(getAgencia(rs, "CAG_BCRIA_CHEQ"));
		
		vo.setCheque(rs.getLong("CCHEQ_LCTO_DEP"));
		vo.setContaCheque(rs.getLong("CCTA_CORR_CHEQ"));
		
		return vo;
	}
	
	private AgenciaVO getAgencia(ResultSet rs, String codigo) throws SQLException {
		int codAgencia = rs.getInt(codigo);
		if (rs.wasNull() || codAgencia == 0) {
			return null;
		}
		
		return new AgenciaVO(codAgencia);
	}
	
	private BancoVO getBanco(ResultSet rs, String codigo) throws SQLException {
		int codBanco = rs.getInt(codigo);
		if (rs.wasNull() || codBanco == 0) {
			return null;
		}
		
		return new BancoVO(codBanco);
	}
}
