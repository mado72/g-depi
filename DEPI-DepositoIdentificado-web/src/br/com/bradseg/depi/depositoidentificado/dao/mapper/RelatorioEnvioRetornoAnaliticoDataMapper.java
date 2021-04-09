package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;

/**
 * A(O) Class RelatorioEnvioRetornoAnaliticoDataMapper.
 */
public class RelatorioEnvioRetornoAnaliticoDataMapper implements RowMapper<RelatorioEnvioRetornoAnaliticoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public RelatorioEnvioRetornoAnaliticoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		RelatorioEnvioRetornoAnaliticoVO vo = new RelatorioEnvioRetornoAnaliticoVO();

		vo.setCodigoSituacao(rs.getInt("CSIT_DEP_ARQ_TRNSF"));
		//private String situacao
		vo.setCodigoDigitoIdentificador(rs.getLong("CDIG_DEP_IDTFD"));  // FIXME Mesmo campo sendo chamado várias vezes
		vo.setDigitoCodigoAutorizador (rs.getInt("CDIG_DEP_IDTFD"));  // FIXME Mesmo campo sendo chamado várias vezes
		vo.setBloquete(rs.getLong("NBLETO_COBR"));
		vo.setSucursal (rs.getInt("CSUCUR_EMISR"));
		vo.setRamo (rs.getString("CRAMO_SEGUR"));  
		vo.setApolice (rs.getInt("NAPOLC"));
		vo.setItem (rs.getInt("NITEM_APOLC")); 
		vo.setTipo (rs.getInt("CTPO_DOCTO"));
		vo.setEndosso (rs.getInt("NENDSS"));
		vo.setParcela (rs.getInt("CDIG_DEP_IDTFD")); // FIXME Mesmo campo sendo chamado várias vezes
		vo.setCodigoAutorizador(rs.getLong("CDEP_IDTFD"));  
		vo.setCodigoPessoa (rs.getLong("CPSSOA_DEPST")); 
		vo.setVencimento (rs.getDate("DVCTO_DEP_IDTFD"));  
		vo.setMatricula (rs.getLong("CUSUAR_RESP_ATULZ")); 
		vo.setValorRegistrado(rs.getBigDecimal("VDEP_IDTFD_ORIGN"));
				
		vo.setValorRegistrado (rs.getBigDecimal("VDEP_IDTFD_ORIGN"));  
		vo.setValorPago(rs.getBigDecimal("VTOT_DEP_IDTFD"));  
		vo.setCodigoBanco (rs.getInt("CBCO"));  
		vo.setCodigoAgencia (rs.getInt("CAG_BCRIA"));  
		vo.setCodigoConta (rs.getLong("CCTA_CORR"));  
		vo.setCodigoCia(rs.getInt("CINTRN_CIA_SEGDR"));  


//    private String cpfCnpj;

		
		return vo;
	}
}