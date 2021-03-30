package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class DepositoDataMapper implements RowMapper<DepositoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public DepositoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		DepositoVO depositoVO = new DepositoVO();

        depositoVO.setCodigoDepositoIdentificado(Long.valueOf(rs.getString("CDEP_IDTFD")));
        depositoVO.setCodigoDigitoDeposito(Integer.valueOf(rs.getString("CDIG_DEP_IDTFD")));
        depositoVO.setCia(new CompanhiaSeguradoraVO(Integer.valueOf(rs.getString("CINTRN_CIA_SEGDR"))));
        depositoVO.setDepartamento(new DepartamentoVO(Integer.valueOf(rs.getString("CDEPTO_DEP_IDTFD")), null, rs.getString("CSGL_DEPTO_DEP")));
        depositoVO.setMotivoDeposito(new MotivoDepositoVO(Integer.valueOf(rs.getString("CMOTVO_DEP_IDTFD")), rs.getString("RMOTVO_DEP_IDTFD"), null));
        depositoVO.setBanco(new BancoVO(Integer.valueOf(rs.getString("CBCO"))));
        depositoVO.setCodigoAgencia(Integer.valueOf(rs.getString("CAG_BCRIA")));
        depositoVO.setContaCorrente(Long.valueOf(rs.getString("CCTA_CORR")));
        depositoVO.setPessoaDepositante(Long.valueOf(rs.getString("CPSSOA_DEPST")));
        depositoVO.setDtVencimentoDeposito(rs.getTimestamp("DVCTO_DEP_IDTFD"));
        depositoVO.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
        depositoVO.setSituacaoArquivoTransferencia(Integer.valueOf(rs.getString("CSIT_DEP_ARQ_TRNSF")));
        depositoVO.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ")); 
        depositoVO.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
        depositoVO.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
        depositoVO.setCodigoSituacaoDeposito(rs.getInt("CSIT_DEP_IDTFD"));
		
		
		return depositoVO;
	}

}