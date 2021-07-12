package br.com.bradseg.depi.depositoidentificado.dao;

import static br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.AMBIENTE_SAP;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.w3c.dom.NodeList;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.cbbs.pagamento.canonico.ConsultaParcelasPgtosRequest;
import br.com.bradseg.cbbs.pagamento.webservice.GerenciaPagamentoPortTypeProxy;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.vo.ParcelaCobrancaVO;

/**
 * Acesso ao SAP
 * @author Globality
 */
@Repository
public class ParcelasPendentesSAPDAOImpl implements ParcelasPendentesSAPDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParcelasPendentesSAPDAOImpl.class);
	
	private static final String TIPO_CONSULTA_ABERTO = "1"; 
	private static final String TIPO_CONSULTA_ABERTO_PEND = "3";
	private static final String COD_ORIGEM = "DP06";
	private static final String BUSCA_PAGTO = "X";
	private static final String TIPO_CONSULTA = "1";
	private static final String CIA_EXTERNA_186 = "6866";
	private static final String CIA_EXTERNA_144 = "5444";
	
	@Autowired
	@Qualifier("IBusc2")
	private URL iBusc2;
	
	@Override
	public List<ParcelaCobrancaVO> listarParcelasSAP(ParcelaCobrancaVO parcelaCobranca) throws IntegrationException {
		LOGGER.debug("listarParcelasSAP(ParcelaCobrancaVO parcelaCobranca)");
		
		ConsultaParcelasPgtosRequest requestSOAP = new ConsultaParcelasPgtosRequest();
		
		requestSOAP.setEnvironment(AMBIENTE_SAP);
		requestSOAP.setBusca_Pagto(BUSCA_PAGTO);
		requestSOAP.setTipo_consulta(TIPO_CONSULTA);
		requestSOAP.setCod_Origem(COD_ORIGEM);
		requestSOAP.setCod_doc("");
		requestSOAP.setEv_Bolext("");
		requestSOAP.setNr_docto_sap("");
		requestSOAP.setReferencia("");
		requestSOAP.setTipo_doc("");
		
		final int codigoCompanhia = parcelaCobranca.getDeposito().getCia().getCodigoCompanhia();

		final String dgobj = String.format("%s%04d%s%08d",
				getCodigoCompanhiaExterna(codigoCompanhia),
				String.format("%04d",parcelaCobranca.getDeposito().getSucursal()),
				parcelaCobranca.getDeposito().getRamo(),		                       
				String.format("%08d",parcelaCobranca.getDeposito().getApolice()));
		
		requestSOAP.setDg_Obj_Seg(dgobj);
		
		try {
			List<ParcelaCobrancaVO> todasParc = new ArrayList<ParcelaCobrancaVO>();
			requestSOAP.setStatus_Compensasao(TIPO_CONSULTA_ABERTO);   // Em aberto
			SOAPElement responseSOAP = obterGerenciaPagamentoPortTypeProxy().consultarParcelasPgto(requestSOAP);
			todasParc.addAll(formaConsultaParcelasPgtosResponse(responseSOAP));
			
			requestSOAP.setStatus_Compensasao(TIPO_CONSULTA_ABERTO_PEND);   // Em aberto Vencidas
			responseSOAP = obterGerenciaPagamentoPortTypeProxy().consultarParcelasPgto(requestSOAP);
			todasParc.addAll(formaConsultaParcelasPgtosResponse(responseSOAP));
			
			return todasParc;
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
	}

	protected String getCodigoCompanhiaExterna(final int codigoCompanhia) {
		final String ciaExterna;
		
		switch (codigoCompanhia) {
		case 186:
			ciaExterna = CIA_EXTERNA_186;
			break;
		case 144:
			ciaExterna = CIA_EXTERNA_144;
		default:
			throw new IntegrationException(String.format("Código inválido: %d", codigoCompanhia));
		}
		return ciaExterna;
	}
	
	private GerenciaPagamentoPortTypeProxy obterGerenciaPagamentoPortTypeProxy() throws IntegrationException {
		LOGGER.debug("GerenciaPagamentoPortTypeProxy obterGerenciaPagamentoPortTypeProxy()");

		String endPoint = null; 
		
		GerenciaPagamentoPortTypeProxy proxy = new GerenciaPagamentoPortTypeProxy();
		
		StringBuilder sb = new StringBuilder(iBusc2.toString());
		if (sb.charAt(sb.length() - 1) != '/') {
			sb.append('/');
		}
		endPoint = sb.append("CBBS-Pagamento").toString();
		
		proxy.setEndpoint(endPoint);
		
		return proxy;
	}	
	
	private List<ParcelaCobrancaVO> formaConsultaParcelasPgtosResponse(SOAPElement resp) throws IntegrationException {	
		LOGGER.debug("formaConsultaParcelasPgtosResponse(SOAPElement resp)");
		List<ParcelaCobrancaVO> lista = new ArrayList<ParcelaCobrancaVO>();
		NodeList nl = resp.getChildNodes();
		
		for (int index1=0; index1<nl.getLength(); index1++){
			Node node1 = (Node) nl.item(index1);
			for (int index2=0 ; index2<node1.getChildNodes().getLength() ; index2++) {
				Node node2 = (Node) node1.getChildNodes().item(index2);
				if ("out2:Itens".equals(node2.getNodeName())) {
					ParcelaCobrancaVO parc = setConsultaParcelasPgtosSAPResponse(node2);
					if (parc != null){
						lista.add(parc);
					}
				} 			    
			}
		}
		return lista;
	}

	private ParcelaCobrancaVO setConsultaParcelasPgtosSAPResponse(Node node) throws IntegrationException {	
		LOGGER.debug("setConsultaParcelasPgtosSAPResponse(Node node)");
		
		ParcelaCobrancaVO parcela = new ParcelaCobrancaVO();
		String formapagto = null, codagrupamento = null, tipodoc = null; 
		
		for (int index=0 ; index<node.getChildNodes().getLength() ; index++) {
			Node sucesso = (Node) node.getChildNodes().item(index);
			final String nodeName = sucesso.getNodeName();
			
			if ("out2:Num_Parcela".equals(nodeName)) { 
				parcela.setCodigoIdentificadorOrigem(sucesso.getValue().substring(1, 4));
			}
			if ("out2:Vl_Total_Parcela".equals(nodeName)) {
				parcela.setValorParcelaCobrado(Double.parseDouble(sucesso.getValue()));
			}
			if ("out2:Vl_Iof".equals(nodeName)) { 
				parcela.setValorIofCobrado(Double.parseDouble(sucesso.getValue()));
				parcela.setValorIofRecebido(Double.parseDouble(sucesso.getValue()));
			}
			if ("out2:Vencimento".equals(nodeName)) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String dt = sucesso.getValue();
				try {
					parcela.setDataVencimento(formatter.parse(dt));
				} catch (Exception e) {
					throw new DEPIIntegrationException(e);
				}	    	   
			}
			if ("out2:Dt_Pagto".equals(nodeName)) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String dt = sucesso.getValue();
				try {
					parcela.setDataPagamentoBanco(formatter.parse(dt));
				} catch (Exception e) {
					throw new DEPIIntegrationException(e);
				}	    	   
			}
			if ("out2:Vl_Pagto".equals(nodeName)) { 
				parcela.setValorParcelaRecebido(Double.parseDouble(sucesso.getValue()));
			}	
			if ("out2:Nr_Docto_Sap".equals(nodeName)) { 
				parcela.setCodigoParcela(Long.parseLong(sucesso.getValue()));				   	  
			}	
			if ("out2:Nome_Seg".equals(nodeName)) { 
				parcela.setNomePessoa(sucesso.getValue());
			}
			if ("out2:Forma_Pagto".equals(nodeName)) { 
				formapagto = sucesso.getValue();
			}
			if ("out2:Cod_Agrupamento".equals(nodeName)) { 
				codagrupamento = sucesso.getValue();
			}
			if ("out2:Tp_Doc".equals(nodeName)) { 
				if ( "".equals(sucesso.getValue()) || sucesso.getValue() == null )  {
					return null; 
				} else { 
					tipodoc = sucesso.getValue();
				}
			}
		}
		
		// Verifica restrições de retorno 
		if (  ( "9".equals(tipodoc) || "09".equals(tipodoc) || "10".equals(tipodoc) || "11".equals(tipodoc) ) &&
				("A".equals(formapagto) || "B".equals(formapagto)) &&
				( "".equals(codagrupamento) || codagrupamento == null ) ) {
			return parcela;
		} else {
			return null;
		}
	}
	
}
