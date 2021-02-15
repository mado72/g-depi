package br.com.bradseg.depi.depositoidentificado.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * A(O) Class RelatorioDadosComplementaresDataMapper.
 */
public class ParametroDepositosDataMapper implements RowMapper<ParametroDepositoVO> {

	private final boolean incluiDescricaoMotivo;
	
	private final boolean incluiNomeDepartamento;
	
	/**
	 * @param incluiDescricaoMotivo
	 * @param incluiNomeDepartamento
	 */
	public ParametroDepositosDataMapper(boolean incluiDescricaoMotivo,
			boolean incluiNomeDepartamento) {
		super();
		this.incluiDescricaoMotivo = incluiDescricaoMotivo;
		this.incluiNomeDepartamento = incluiNomeDepartamento;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */ 
	@Override
	public ParametroDepositoVO mapRow(ResultSet rs, int rowNum) throws SQLException {

		int codigoDepartamento = rs.getInt("CDEPTO_DEP_IDTFD");
		int codigoMotivo = rs.getInt("CMOTVO_DEP_IDTFD");
		int codigoCompanhia = rs.getInt("CINTRN_CIA_SEGDR");
		
		ParametroDepositoVO vo = new ParametroDepositoVO(codigoDepartamento,
				codigoMotivo, codigoCompanhia);

		vo.setCodigoBancoVencimento(rs.getString("CIND_DEP_APOS_VCTO"));
		vo.setNumeroDiasAposVencimento(rs.getInt("QDIA_APOS_VCTO"));
		vo.setCodigoItem(rs.getString("CIND_ITEM_APOLC"));
		vo.setCodigoRamo(rs.getString("CIND_RAMO_OBRIG"));
		vo.setCodigoTipo(rs.getString("CIND_TPO_DOCTO"));
		vo.setCodigoSucursal(rs.getString("CIND_SUCUR_OBRIG"));
		vo.setCodigoProtocolo(rs.getString("CIND_PROT"));
		vo.setCodigoApolice(rs.getString("CIND_APOLC_OBRIG"));
		vo.setCodigoCpfCnpj(rs.getString("CIND_CPF_CNPJ"));
		vo.setCodigoDossie(rs.getString("CIND_PROCS_OBRIG"));
		vo.setCodigoBloqueto(rs.getString("CIND_BLETO_OBRIG"));
		vo.setCodigoEndosso(rs.getString("CIND_ENDSS_OBRIG"));
		vo.setCodigoParcela(rs.getString("CIND_PCELA_PRMIO"));
		vo.setOutrosDocumentosNecessarios(rs.getString("ROBS_PARMZ_DEP"));
		vo.setDataHoraInclusao(rs.getDate("DHORA_INCL_REG"));
		vo.setUltimaAtualizacao(rs.getDate("DHORA_ULT_ATULZ"));
		vo.setCodigoResponsavelUltimaAtualizacao(rs.getInt("CUSUAR_RESP_ATULZ"));
		vo.setCodigoAtivo(rs.getString("CIND_REG_ATIVO"));
		vo.setRefereciadoDeposito(rs.getBoolean("REFERENCIADO_DEPOSITO"));
		
		if (isIncluiDescricaoMotivo()) {
			vo.getMotivoDeposito().setDescricaoBasica(rs.getString("RMOTVO_DEP_IDTFD"));
		}
		
		if (isIncluiNomeDepartamento()) {
			vo.getDepartamento().setNomeDepartamento(rs.getString("IDEPTO_DEP_IDTFD"));
		}
		return vo;
	}
	
	/**
	 * Retorna incluiDescricaoMotivo
	 * @return o incluiDescricaoMotivo
	 */
	public boolean isIncluiDescricaoMotivo() {
		return incluiDescricaoMotivo;
	}
	
	/**
	 * Retorna incluiNomeDepartamento
	 * @return o incluiNomeDepartamento
	 */
	public boolean isIncluiNomeDepartamento() {
		return incluiNomeDepartamento;
	}

}
