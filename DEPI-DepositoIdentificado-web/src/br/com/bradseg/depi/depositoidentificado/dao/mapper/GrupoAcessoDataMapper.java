package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.CodigoIndicativoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.GrupoAcessoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class GrupoAcessoDataMapper implements RowMapper<GrupoAcessoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public GrupoAcessoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		GrupoAcessoVO grupoAcessoVO = new GrupoAcessoVO();

        grupoAcessoVO.setCodigoGrupoAcesso(rs.getInt("CGRP_DEPTO_DEP"));
        grupoAcessoVO.setCia(new CompanhiaSeguradoraVO(rs.getInt("CINTRN_CIA_SEGDR")));

        grupoAcessoVO.setDepto(new DepartamentoVO(rs.getInt("CDEPTO_DEP_IDTFD"), 
						rs.getString("IDEPTO_DEP_IDTFD"), 
						rs.getString("CSGL_DEPTO_DEP")));
		
        grupoAcessoVO.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));
        grupoAcessoVO.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
        grupoAcessoVO.setDataInclusao(rs.getTimestamp("DHORA_INCL_REG"));
        grupoAcessoVO.setDataHoraAtualizacao(rs.getTimestamp("DHORA_ULT_ATULZ"));

		return grupoAcessoVO;
	}
	
	/**
	 * Mapeia o resultado de uma query que recupera apenas o código e o
	 * indicativo do Grupo.
	 */
	public static class CodigoIndicativo implements RowMapper<CodigoIndicativoVO> {

		/* (non-Javadoc)
		 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
		 */
		@Override
		public CodigoIndicativoVO mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			
			CodigoIndicativoVO vo = new CodigoIndicativoVO();
			
			vo.setCodigo(rs.getInt("CGRP_DEPTO_DEP"));
			vo.setIndicativo(rs.getString("CIND_REG_ATIVO"));
			
			return vo;
		}
		
	}

}


