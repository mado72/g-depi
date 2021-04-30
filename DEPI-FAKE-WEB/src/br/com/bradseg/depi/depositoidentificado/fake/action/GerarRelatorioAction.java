package br.com.bradseg.depi.depositoidentificado.fake.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("session")
public class GerarRelatorioAction extends ActionSupport implements ServletRequestAware {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(GerarRelatorioAction.class);
	
	private static final String DATA_MOVIMENTO = "DATA_MOVIMENTO";
	private static final String DATA_HORA = "DATA_HORA";
	private static final String DATA_INICIO = "DATA_INICIO";
	private static final String DATA_FIM = "DATA_FIM";
	private static final String SEQUENCIAL = "SEQUENCIAL";
	public static final int ARQUIVO_A_ENVIAR = 1;
	public static final int ARQUIVO_ENVIADO = 2;
	public static final int ARQUIVO_ACEITO = 3;
	public static final int ARQUIVO_RENVIAR = 4;
	public static final int ARQUIVO_REJEITADO = 5;
	public static final int ARQUIVO_CANCELADO = 6;
	
	private static final long serialVersionUID = 659211998125747676L;

	private InputStream fileInputStream;

	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@Override
	public void validate() {
	}
	
	@Override
	public String execute() throws Exception {
		String resName = "";
		InputStream stream = GerarRelatorioAction.class.getResourceAsStream(resName);
		Collection<?> retorno;
		retorno = relatorioEnvioSintetico(stream);
		
		Map<String, Object> params = prepararParametrosComunsPdf();
		gerarRelatorio(retorno, params, "relEnvioRetornoBancoSintetico");
		
		return "relEnvioRetornoBancoSintetico.pdf";
	}

	private void gerarRelatorio(Collection<?> dados, Map<?,?> parametros, String relatorio) throws Exception {
    	LOGGER.error("gerarPdf 1");  

		File reportFile = new File(request.getSession().getServletContext()
				.getRealPath("/WEB-INF/jasper/"), relatorio + ".jasper");
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);
		
    	// LOGGER.error("pathImg:"+pathImg);
    	// LOGGER.error("reportFile.getPath():"+reportFile.getPath());
    	// LOGGER.error("ConstantesDEPI.DP06_LOGO_JPG:"+ ConstantesDEPI.DP06_LOGO_JPG);
    	// LOGGER.error("PARAM_LOGO:"+ConstantesDEPI.PARAM_LOGO);
    	// LOGGER.error("pathImg:"+pathImg);
    	
    	byte[] bytes = null;
    	bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, ds);
    	
    	InputStream is = new ByteArrayInputStream(bytes);
    	LOGGER.error("Proposta Action - gerarCartaInterna - fim");
    	
    	this.fileInputStream = is;
	}

	private ArrayList<RelatorioEnvioRetornoSinteticoVO> relatorioEnvioSintetico(
			InputStream stream) throws IOException, JsonParseException,
			JsonMappingException {
		Class<RelatorioEnvioRetornoSinteticoVO[]> valueType = RelatorioEnvioRetornoSinteticoVO[].class;
		
		ObjectMapper mapper = new ObjectMapper();
		RelatorioEnvioRetornoSinteticoVO[] lista = mapper.readValue(stream, valueType);
		
		AtomicInteger qtdAceitos = new AtomicInteger();
		AtomicInteger qtdCancelados = new AtomicInteger();
		AtomicInteger qtdEnviados = new AtomicInteger();
		AtomicInteger qtdRejeitados = new AtomicInteger();
		
		BigDecimal totalAceitos = BigDecimal.ZERO;
		BigDecimal totalCancelados = BigDecimal.ZERO;;
		BigDecimal totalEnviados = BigDecimal.ZERO;;
		BigDecimal totalRejeitados = BigDecimal.ZERO;
		
		for (RelatorioEnvioRetornoSinteticoVO item : lista) {
			
			if (item.getCodigoSituacao() == ARQUIVO_ACEITO) {
				totalAceitos = totalAceitos.add(item.getValorRegistrado());
				qtdAceitos.incrementAndGet();
			}
			else if (item.getCodigoSituacao() == ARQUIVO_CANCELADO) {
				totalCancelados = totalCancelados.add(item.getValorRegistrado());
				qtdCancelados.incrementAndGet();
			}
			else if (item.getCodigoSituacao() == ARQUIVO_ENVIADO) {
				totalEnviados = totalEnviados.add(item.getValorRegistrado());
				qtdEnviados.incrementAndGet();
			}
			else if (item.getCodigoSituacao() == ARQUIVO_REJEITADO) {
				totalRejeitados = totalRejeitados.add(item.getValorRegistrado());
				qtdRejeitados.incrementAndGet();
			}
		}
		
		RelatorioEnvioRetornoSinteticoVO totais = new RelatorioEnvioRetornoSinteticoVO();
		totais.setTotalAceitos(totalAceitos);
		totais.setTotalCancelados(totalCancelados);
		totais.setTotalEnviados(totalEnviados);
		totais.setTotalRejeitados(totalRejeitados);
		totais.setQtdAceitos(qtdAceitos.get());
		totais.setQtdCancelados(qtdCancelados.get());
		totais.setQtdEnviados(qtdEnviados.get());
		totais.setQtdRejeitados(qtdRejeitados.get());
		
		ArrayList<RelatorioEnvioRetornoSinteticoVO> retorno = new ArrayList<>(Arrays.asList(lista));
		retorno.add(totais);
		return retorno;
	}

    private Map<String, Object> prepararParametrosComunsPdf() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(DATA_INICIO,  RelogioUtil.formataDate(new Date()));
		params.put(DATA_FIM, RelogioUtil.formataDate(new Date()));
	
		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());
		return params;
	}
    
    public InputStream getFileInputStream() {
		return fileInputStream;
	}

}
