package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.MovimentoDepositoVO;

/**
 * Mapeia MovimentoDepositoVO 
 * @author Globality
 */
public class MovimentoDepositoDataMapper implements RowMapper<MovimentoDepositoVO> {

	@Override
	public MovimentoDepositoVO mapRow(ResultSet rs, int index)
			throws SQLException {
		
		MovimentoDepositoVO vo = new MovimentoDepositoVO();
		vo.setCodigoMovimento(rs.getLong("CDEP_IDTFD"));
		vo.setIndicacaoAcao(rs.getString("CTPO_ACAO_MOVTO"));
		vo.setNroCheque(rs.getLong("CCHEQ_DEVLC"));
		vo.setBancoMovimento(rs.getLong("CBCO"));
		vo.setAgenciaMovimento(rs.getLong("CAG_MOVTO"));
		vo.setObservacao(rs.getString("ROBS_MOVTO_FINCR"));
		vo.setContaMovimento(rs.getLong("CCTA_MOVTO"));
		vo.setDataHoraAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		vo.setCodigoResponsavelUltimaAtualizacao(rs.getLong("CUSUAR_RESP_ATULZ"));
		
		return vo;
	}
}
