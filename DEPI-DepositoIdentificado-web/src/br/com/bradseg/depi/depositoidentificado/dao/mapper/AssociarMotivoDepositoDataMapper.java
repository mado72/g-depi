package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * A(O) Class AssociarMotivoDepositoDataMapper.
 */
public class AssociarMotivoDepositoDataMapper implements RowMapper<AssociarMotivoDepositoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public AssociarMotivoDepositoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		AssociarMotivoDepositoVO vo = new AssociarMotivoDepositoVO();
		vo.setCia(new CompanhiaSeguradoraVO(rs.getInt("CINTRN_CIA_SEGDR")));
		vo.setDepartamento(new DepartamentoVO(rs.getInt("CDEPTO_DEP_IDTFD"),
				null, rs.getString("CSGL_DEPTO_DEP")));
		vo.setMotivoDeposito(new MotivoDepositoVO(rs.getInt("CMOTVO_DEP_IDTFD")));
		vo.setBanco(new BancoVO(rs.getInt("CBCO")));
		vo.setCodigoAgencia(rs.getInt("CAG_BCRIA"));
		vo.setContaCorrente(rs.getLong("CCTA_CORR"));
		vo.setDataInclusao(rs.getDate("DHORA_INCL_REG"));
		vo.setDataHoraAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		vo.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		vo.setIndicadoRegistroAtivo(rs.getString("CIND_REG_ATIVO"));
		
		return vo;
	}

}