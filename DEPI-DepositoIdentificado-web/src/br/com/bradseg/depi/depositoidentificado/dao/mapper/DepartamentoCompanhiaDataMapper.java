/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoCompanhiaVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Faz o mapeamento para {@link DepartamentoCompanhiaVO}. Não recupera o nome da
 * companhia, pois esta informação está disponível apenas no CICS.
 */
public class DepartamentoCompanhiaDataMapper implements
		RowMapper<DepartamentoCompanhiaVO> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public DepartamentoCompanhiaVO mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		
		CompanhiaSeguradoraVO ciaVO = new CompanhiaSeguradoraVO();
		ciaVO.setCodigoCompanhia(rs.getInt("CINTRN_CIA_SEGDR"));
		
		DepartamentoVO departamentoVO = new DepartamentoVO();
		departamentoVO.setCodigoDepartamento(rs.getInt("CDEPTO_DEP_IDTFD"));
		departamentoVO.setNomeDepartamento(rs.getString("IDEPTO_DEP_IDTFD"));
		departamentoVO.setSiglaDepartamento(rs.getString("CSGL_DEPTO_DEP"));
		
		DepartamentoCompanhiaVO vo = new DepartamentoCompanhiaVO();
		vo.setCompanhia(ciaVO);
		vo.setDepartamento(departamentoVO);
		vo.setDataInclusao(rs.getDate("DHORA_INCL_REG"));
		vo.setDataHoraAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		vo.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		vo.setCodigoIndicativoAtivo(rs.getString("CIND_REG_ATIVO"));

		return vo;
	}

}
