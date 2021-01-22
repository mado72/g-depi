package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;

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

		AssociarMotivoDepositoVO associarMotivoDepositoVO = new AssociarMotivoDepositoVO();
		
/*		associarMotivoDepositoVO.setCodigoDepartamento(rs.getInt("CDEPTO_DEP_IDTFD"));
		associarMotivoDepositoVO.setNomeDepartamento(rs.getString("IDEPTO_DEP_IDTFD"));
		associarMotivoDepositoVO.setSiglaDepartamento(rs.getString("CSGL_DEPTO_DEP"));
		associarMotivoDepositoVO.setIndicadoRegistroAtivo(rs.getString("CIND_REG_ATIVO"));
		associarMotivoDepositoVO.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ")); 
		
		DepositoVO deposito = new DepositoVO();
	    deposito.setCodigoDepositoIdentificado(rs.getLong("CDEPTO_DEP_IDTFD"));
				
		departamentoVO.setDeposito(deposito);
*/	
		
		
		return associarMotivoDepositoVO;
	}

}