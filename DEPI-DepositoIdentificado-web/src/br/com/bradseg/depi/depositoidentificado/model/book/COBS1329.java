package br.com.bradseg.depi.depositoidentificado.model.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.ctg.programapi.annotation.CicsGateway;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DoubleFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.FieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IndexedFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IntegerFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.LongFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.StringFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramImpl;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CommonAreaMetaData;
import br.com.bradseg.bsad.framework.ctg.programapi.program.InputSet;
import br.com.bradseg.bsad.framework.ctg.programapi.program.ResultSet;
import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * Classe que representa a book do serviço COBS0329.
 * @author Globality
 */
@CicsGateway
public class COBS1329 extends CTGProgramImpl {
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("dd.MM.yyyy");
	
	private static final String PGMNAME = "COBS0329";
	private static final String TRANNAME = "BCD6";
	private static final int COMMLENGTH = 20000;

	public static final CommonAreaMetaData LISTA = new CommonAreaMetaData(new FieldType[] {
	    new LongFieldType("B1329-CPCELA-COBR-S01", 11),
		new StringFieldType("B1329-CIDTFD-PCELA-ORIGE-S01", 3),
		new DoubleFieldType("B1329-VPCELA-COBR-S01", 15, 2),
		new DoubleFieldType("B1329-VIOF-PCELA-COBR-S01", 15, 2),
		new StringFieldType("B1329-DVCTO-PCELA-COBR-S01", 10),
		new StringFieldType("B1329-IPSSOA-COBR-S14", 80)
	});	

	private static final FieldType[] COMMAREAIN_FIELDS = {
	    new IntegerFieldType("B1329-CRETOR-PROG-SCC", 3),
		new StringFieldType("B1329-RMSGEM-RETOR-SCC", 70),
		new StringFieldType("B1329-CERRO-OCOR-SCC", 10),
		new StringFieldType("B1329-RERRO-OCOR-SCC", 80),
		new StringFieldType("B1329-CSUB-PROG-CANCD-SCC", 8),
		new StringFieldType("B1329-RPROCE-PROG-SCC", 30),
		new StringFieldType("B1329-RARQ-CANCD-SCC", 20),
		new StringFieldType("B1329-RCHAVE-ACSSO-ARQ-SCC", 200),
		
		new StringFieldType("B1329-CMATR-USUAR-ECC", 8),
		new IntegerFieldType("B1329-CSUCUR-SEGDR-E01", 4),
		new IntegerFieldType("B1329-CINTRN-CIA-SEGDR-E01", 4),
		new IntegerFieldType("B1329-CRAMO-SEGUR-E01", 4),
		new LongFieldType("B1329-CNRO-APOLC-SEGRD-E01", 9),
		new IntegerFieldType("B1329-CNRO-ITEM-APOLC-E01", 4),
		new LongFieldType("B1329-CNRO-ENDSS-APOLC-E01", 9),
		new LongFieldType("B1329-CPCELA-COBR-ULT-E01", 11),
		new StringFieldType("B1329-IND-SEL-COM-E01", 1),
		new StringFieldType("B1329-CNRO-DOCTO-ID-E01", 15),
		new StringFieldType("B1329-FILLER-ENTRADA", 184)
	};

	private static final FieldType[] COMMAREAOUT_FIELDS = {
		new IntegerFieldType("B1329-QDE-PCELA-LIST-SCC" ,3),
		new IntegerFieldType("B1329-QDE-PCELA-TOTAL-SCC" ,3),
		new StringFieldType("B1329-CIND-MAIS-PCELA-LIST-SCC" ,1),
		new IndexedFieldType("B1329-LISTA", 50, LISTA),
		new StringFieldType("B1329-FILLER-SAIDA", 12619)
	};
	
	private static final CommonAreaMetaData COMMAREAIN = new CommonAreaMetaData(
			COMMAREAIN_FIELDS);
	private static final CommonAreaMetaData COMMAREAOUT = new CommonAreaMetaData(
			COMMAREAIN_FIELDS, COMMAREAOUT_FIELDS);
		
	/**
	 * Construtor padrão
	 */
	public COBS1329() {
		super(PGMNAME, TRANNAME, COMMLENGTH, COMMAREAIN, COMMAREAOUT);
	}
	
	/**
	 * Método que popula a área de entrada da book.
	 * @param is - o inputset do cics.
	 * @param parcelaCobranca - objeto com os dados de parâmetro.
	 * @param matricula - a matrícula do usuário.
	 * @param acao - A ação executada pelo servi�o.
	 * @throws IntegrationException - exceção.
	 */
	public void populateProgram(InputSet is, ParcelaCobrancaVO parcelaCobranca, String matricula, String acao) throws IntegrationException {
		is.setString("B1329-CMATR-USUAR-ECC", matricula);
		is.setInteger("B1329-CSUCUR-SEGDR-E01", parcelaCobranca.getDeposito().getSucursal());
		is.setInteger("B1329-CINTRN-CIA-SEGDR-E01", parcelaCobranca.getDeposito().getCia().getCodigoCompanhia());
		is.setInteger("B1329-CRAMO-SEGUR-E01", Integer.valueOf(parcelaCobranca.getDeposito().getRamo()));
		is.setLong("B1329-CNRO-APOLC-SEGRD-E01", Long.valueOf(parcelaCobranca.getDeposito().getApolice()));
		is.setInteger("B1329-CNRO-ITEM-APOLC-E01", parcelaCobranca.getDeposito().getItem());
		is.setLong("B1329-CNRO-ENDSS-APOLC-E01", parcelaCobranca.getDeposito().getEndosso());
		is.setLong("B1329-CPCELA-COBR-ULT-E01", parcelaCobranca.getCodigoParcela());
		is.setString("B1329-IND-SEL-COM-E01", acao);
		is.setString("B1329-CNRO-DOCTO-ID-E01", parcelaCobranca.getDeposito().getCpfCnpj()); 
	}
	
	/**
	 * Método que retorna a lista de parcelas pendentes.
	 * @param resultSet - objeto result set.
	 * @throws IntegrationException - exceção.
	 * @throws ParseException  - exception.
	 * @return List<ParcelaCobrancaVO> - lista de retorno.
	 */
	public List<ParcelaCobrancaVO> retornaListaParcelas(ResultSet resultSet) throws IntegrationException, ParseException {
		List<ParcelaCobrancaVO> listaParcelas = new ArrayList<ParcelaCobrancaVO>();
		
		ResultSet rs = resultSet.getIndexed("B1329-LISTA");
		
		while(rs.next()) {
			ParcelaCobrancaVO parcelaCobranca = new ParcelaCobrancaVO();
			parcelaCobranca.setCodigoParcela(rs.getLong("B1329-CPCELA-COBR-S01"));
			parcelaCobranca.setCodigoIdentificadorOrigem(rs.getString("B1329-CIDTFD-PCELA-ORIGE-S01"));
			parcelaCobranca.setValorParcelaCobrado(rs.getDouble("B1329-VPCELA-COBR-S01"));
			parcelaCobranca.setValorIofCobrado(rs.getDouble("B1329-VIOF-PCELA-COBR-S01"));
			if(!"".equals(rs.getString("B1329-DVCTO-PCELA-COBR-S01"))) {
				synchronized (this) {
					parcelaCobranca.setDataVencimento(SDF.parse(rs.getString("B1329-DVCTO-PCELA-COBR-S01")));
				}
			}
			parcelaCobranca.setNomePessoa(rs.getString("B1329-IPSSOA-COBR-S14"));
			
			if(parcelaCobranca.getCodigoParcela() != 0) {
				listaParcelas.add(parcelaCobranca);	
			} else {
				break;
			}
		}
		
		return listaParcelas;
	}
	
	public void verificaErrosCics(ResultSet rs) throws IntegrationException {
		if(rs.getInteger("B1329-CRETOR-PROG-SCC") != 0) {
			throw new IntegrationException("Código Retorno:" + rs.getInteger("B1329-CRETOR-PROG-SCC") + "-" + rs.getString("B1329-RMSGEM-RETOR-SCC")
										 + "Código Erro:" + rs.getString("B1329-CERRO-OCOR-SCC") + "-" + rs.getString("B1329-RERRO-OCOR-SCC"));
		}
	}
}
