package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
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

        depositoVO.setCodigoDepositoIdentificado(rs.getLong("CDEP_IDTFD"));
        depositoVO.setCodigoDigitoDeposito(rs.getInt("CDIG_DEP_IDTFD"));
        depositoVO.setCia(new CompanhiaSeguradoraVO(rs.getInt("CINTRN_CIA_SEGDR")));
        depositoVO.setDepartamento(new DepartamentoVO(rs.getInt("CDEPTO_DEP_IDTFD"), null, rs.getString("CSGL_DEPTO_DEP")));
        depositoVO.setMotivoDeposito(new MotivoDepositoVO(rs.getInt("CMOTVO_DEP_IDTFD"), rs.getString("RMOTVO_DEP_IDTFD"), null));
        depositoVO.setBanco(new BancoVO(rs.getInt("CBCO")));
        depositoVO.setAgencia(new AgenciaVO(rs.getInt("CAG_BCRIA")));
        depositoVO.setContaCorrente(rs.getLong("CCTA_CORR"));
        depositoVO.setPessoaDepositante(rs.getLong("CPSSOA_DEPST"));
        depositoVO.setDtVencimentoDeposito(rs.getTimestamp("DVCTO_DEP_IDTFD"));
        depositoVO.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
        depositoVO.setSituacaoArquivoTransferencia(rs.getInt("CSIT_DEP_ARQ_TRNSF"));
        depositoVO.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ")); 
        depositoVO.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
        depositoVO.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
        depositoVO.setCodigoSituacaoDeposito(rs.getInt("CSIT_DEP_IDTFD"));

        depositoVO.setDataProrrogacao(rs.getDate("DPRROG_DEP_IDTFD"));
		depositoVO.setDtCancelamentoDepositoIdentificado(rs.getDate("DCANCT_DEP_IDTFD"));
		
		return depositoVO;
	}

}