package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class DepartamentoDataMapper implements RowMapper<DepartamentoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public DepartamentoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		DepartamentoVO departamentoVO = new DepartamentoVO();
		
		departamentoVO.setCodigoDepartamento(rs.getInt("CDEPTO_DEP_IDTFD"));
		departamentoVO.setNomeDepartamento(rs.getString("IDEPTO_DEP_IDTFD"));
		departamentoVO.setSiglaDepartamento(rs.getString("CSGL_DEPTO_DEP"));
		departamentoVO.setIndicadoRegistroAtivo(rs.getString("CIND_REG_ATIVO"));
		departamentoVO.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		
		DepositoVO deposito = new DepositoVO();
	    deposito.setCodigoDepositoIdentificado(rs.getLong("CDEPTO_DEP_IDTFD"));
				
		departamentoVO.setDeposito(deposito);
	
		
		
		return departamentoVO;
	}

}

