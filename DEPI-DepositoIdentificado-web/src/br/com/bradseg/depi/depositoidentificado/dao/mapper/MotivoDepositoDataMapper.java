package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class MotivoDepositoDataMapper implements RowMapper<MotivoDepositoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public MotivoDepositoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		MotivoDepositoVO motivoDepositoVO = new MotivoDepositoVO();
		
		motivoDepositoVO.setCodigoMotivoDeposito(rs.getInt("CMOTVO_DEP_IDTFD"));
		motivoDepositoVO.setDescricaoBasica(rs.getString("RMOTVO_DEP_IDTFD"));
		motivoDepositoVO.setDescricaoDetalhada(rs.getString("RDETLH_MOTVO_DEP"));
		motivoDepositoVO.setIndicadorAtivo(rs.getString("CIND_REG_ATIVO"));
		motivoDepositoVO.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		motivoDepositoVO.setCodigoEventoContabil(rs.getInt("CTPO_EVNTO_NEGOC"));
		motivoDepositoVO.setCodigoItemContabil(rs.getInt("CTPO_ITEM_NEGOC"));
		motivoDepositoVO.setUltimaAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		
		return motivoDepositoVO;
		
	}

}

