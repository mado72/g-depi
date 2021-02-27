/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Mapeia colunas para conta corrente autorizada. 
 * @author Marcelo Damasceno
 */
public class ContaCorrenteRowMapper implements RowMapper<ContaCorrenteAutorizadaVO> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public ContaCorrenteAutorizadaVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		ContaCorrenteAutorizadaVO vo = new ContaCorrenteAutorizadaVO();
		
		vo.setBanco(new BancoVO(rs.getInt("CBCO")));
		vo.setCodigoAgencia(rs.getInt("CAG_BCRIA"));
		vo.setContaCorrente(rs.getLong("CCTA_CORR"));
		vo.setObservacao(rs.getString("ROBS_CTA_AUTRZ"));
		vo.setDataInclusao(rs.getDate("DHORA_INCL_REG"));
		vo.setDataHoraAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		vo.setCodigoInternoCC(rs.getInt("CCTA_CORR_AUTRZ"));
		vo.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		vo.setCodigoAtivo(rs.getString("CIND_REG_ATIVO"));
		vo.setDigitoAgencia(rs.getString("CDIG_AG"));
		vo.setPrimeiroDigitoConta(rs.getString("CPRIM_DIG_CTA"));
		vo.setSegundoDigitoConta(rs.getString("CSEGDA_DIG_CTA"));
		vo.setCia(new CompanhiaSeguradoraVO(rs.getInt("CINTRN_CIA_SEGDR")));
		vo.setTrps(rs.getLong("NCLI_TRANS_PERSO"));
		
		return vo;
	}

}
