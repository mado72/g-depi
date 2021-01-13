package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.UsuarioVO;

/**
 * A(O) Class UsuarioDataMapper.
 */
public class UsuarioDataMapper implements RowMapper<UsuarioVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public UsuarioVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		UsuarioVO usuarioVO = new UsuarioVO();
		
		usuarioVO.setCodigoUsuario(rs.getInt("CUSUAR_DEP_IDTFD"));
		usuarioVO.setNomeUsuario(rs.getString("IUSUAR_DEP_IDTFD"));
		usuarioVO.setDataInclusao(rs.getDate("DHORA_INCL_REG"));
		usuarioVO.setDataUltimaAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		usuarioVO.setIndicadorRegistroAtivo(rs.getString("CIND_REG_ATIVO"));
		usuarioVO.setCodigoUsuarioAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		
		return usuarioVO;
	}

}