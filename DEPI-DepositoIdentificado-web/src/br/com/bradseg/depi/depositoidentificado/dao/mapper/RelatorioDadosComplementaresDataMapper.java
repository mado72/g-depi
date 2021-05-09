package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class RelatorioDadosComplementaresDataMapper implements RowMapper<RelatorioDadosComplementaresVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public RelatorioDadosComplementaresVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RelatorioDadosComplementaresVO vo = new RelatorioDadosComplementaresVO();
		vo.setCodigoAutorizador(rs.getLong("CDEP_IDTFD"));
		vo.setDigitoCodigoAutorizador(rs.getInt("CDIG_DEP_IDTFD"));
	    vo.setCodigoBanco(rs.getInt("CBCO"));
	    vo.setCodigoAgencia(rs.getInt("CAG_BCRIA"));
	    vo.setCodigoConta(rs.getLong("CCTA_CORR"));
	    vo.setCodigoTipoGrupoRecebimento(rs.getInt("CTPO_GRP_RECEB"));
	    vo.setCodigoSituacao(rs.getInt("CSIT_DEP_ARQ_TRNSF"));
	    vo.setDataHoraInclusaoDeposito(rs.getTimestamp("DHORA_INCL_DEP"));
	    vo.setDataHoraInclusaoLancamento(rs.getTimestamp("DHORA_INCL_LCTO"));
	    vo.setValorRegistrado(rs.getBigDecimal("VDEP_IDTFD_ORIGN"));
	    vo.setValorPago(rs.getBigDecimal("VTOT_DEP_IDTFD"));
	    vo.setCodigoCia(rs.getInt("CINTRN_CIA_SEGDR"));
	    vo.setCodigoDepartamentoDeposito(rs.getInt("CDEPTO_DEP_IDTFD"));
	    vo.setCodigoMotivoDeposito(rs.getInt("CMOTVO_DEP_IDTFD"));
	    vo.setObservacaoParametrizacaoDeposito(rs.getString("ROBS_PARMZ_DEP"));
	    vo.setObservacaoDeposito(rs.getString("ROBS_DEP_IDTFD"));

	    int codDigVerificador = rs.getInt("CDIG_DEP_IDTFD");
		vo.setCodigoAutorizadorComDv(String.format(
				"%d - %d", vo.getCodigoAutorizador(),
				codDigVerificador));
		
		switch (vo.getCodigoTipoGrupoRecebimento()) {
		case 1:
			vo.setDescricaoTipoGrupoRecebimento("PR\u00CAMIO");
			break;
		case 2:
			vo.setDescricaoTipoGrupoRecebimento("DIVERSOS");
			break;
		default:
			vo.setDescricaoTipoGrupoRecebimento("OUTROS");
			break;
		}

	    return vo;
	}

}

