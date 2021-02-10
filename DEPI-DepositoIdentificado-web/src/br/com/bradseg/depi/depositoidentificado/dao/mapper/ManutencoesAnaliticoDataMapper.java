package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;

/**
 * A(O) Class ManutencoesAnaliticoDataMapper.
 */
public class ManutencoesAnaliticoDataMapper implements RowMapper<ManutencoesAnaliticoVO> {

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public ManutencoesAnaliticoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		ManutencoesAnaliticoVO manutencoesAnaliticoVO = new ManutencoesAnaliticoVO();

		
		manutencoesAnaliticoVO.setCodigoSituacao(rs.getInt("CSIT_DEP_ARQ_TRNSF"));
		manutencoesAnaliticoVO.setCodigoDigitoIdentificador(rs.getLong("CDIG_DEP_IDTFD"));
		manutencoesAnaliticoVO.setBloquete(rs.getLong("NBLETO_COBR"));
		manutencoesAnaliticoVO.setSucursal(rs.getInt("CSUCUR_EMISR"));
		manutencoesAnaliticoVO.setRamoSegur(rs.getString("CRAMO_SEGUR"));
		manutencoesAnaliticoVO.setApolice(rs.getInt("NAPOLC"));
		manutencoesAnaliticoVO.setItem(rs.getInt("NITEM_APOLC"));
		manutencoesAnaliticoVO.setTipoDoc(rs.getInt("CTPO_DOCTO"));
		manutencoesAnaliticoVO.setEndosso(rs.getInt("NENDSS"));
		manutencoesAnaliticoVO.setCodigoCia(rs.getInt("CINTRN_CIA_SEGDR"));
		manutencoesAnaliticoVO.setParcelaPremio(rs.getInt("NPCELA_PRMIO"));
//		manutencoesAnaliticoVO.set(rs.getInt("CDEP_IDTFD")); #Verificar
		manutencoesAnaliticoVO.setCodigoPessoa(rs.getLong("CPSSOA_DEPST"));
		manutencoesAnaliticoVO.setVencimento(rs.getDate("DVCTO_DEP_IDTFD"));
		manutencoesAnaliticoVO.setMatricResp(rs.getLong("CUSUAR_RESP_ATULZ"));
		manutencoesAnaliticoVO.setValorPago(rs.getDouble("VDEP_IDTFD_ORIGN"));
//		manutencoesAnaliticoVO.setValorRegistrado(rs.getDouble("LCTO_VTOT_DEP_IDTFD"));
		manutencoesAnaliticoVO.setCodigoBanco(rs.getInt("CBCO"));
		manutencoesAnaliticoVO.setCodigoAgencia(rs.getInt("CAG_BCRIA"));
		manutencoesAnaliticoVO.setCodigoConta(rs.getLong("CCTA_CORR"));

		return manutencoesAnaliticoVO;
	}

}