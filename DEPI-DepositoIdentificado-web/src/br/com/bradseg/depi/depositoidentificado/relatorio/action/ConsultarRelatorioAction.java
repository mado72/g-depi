package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.relatorio.facade.ConsultarRelatorioFacade;
import br.com.bradseg.depi.depositoidentificado.relatorio.util.RelogioUtil;
import br.com.bradseg.depi.depositoidentificado.relatorio.vo.DadosRelatorioVO;
import br.com.bradseg.depi.depositoidentificado.relatorio.vo.FiltroVO;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ManutencoesSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioDadosComplementaresVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;

/**
 * Ação que trata da geração dos relatórios 
 * @author Globality
 */
@Controller
@Scope("session")
public class ConsultarRelatorioAction extends BaseModelAction<FiltroVO>  {
	private static final String ZERO_STR = "0";
	private static final long serialVersionUID = 3407809247264650489L;
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConsultarRelatorioAction.class); 

	private static final String DATA_FINAL = "Data Final";
	private static final String DATA_INICIAL = "Data Inicial";
	private static final String VISUALIZACAO = "VISUALIZACAO";
	private static final String HEADER = "HEADER";
	private static final String SITUACAO = "SITUACAO";
	private static final String DATA_MOVIMENTO = "DATA_MOVIMENTO";
	private static final String DATA_HORA = "DATA_HORA";
	private static final String DATA_INICIO = "DATA_INICIO";
	private static final String DATA_FIM = "DATA_FIM";
	private static final String SEQUENCIAL = "SEQUENCIAL";
	private static final String VALOR_TOTAL_PAGO = "VALOR_TOTAL_PAGO";
	private static final String VALOR_TOTAL_REGISTRADO = "VALOR_TOTAL_REGISTRADO";
	
    public static final String ESTRUTURA_PASTA_IMAGENS = "/padroes_web/intranet/imagens/";
    public static final String ESTRUTURA_PASTA_CSS = "/padroes_web/intranet/css/";
	
    private static final String TIPO_RELATORIO_ENVIO_RETORNO = "ER";
    private static final String TIPO_RELATORIO_EXTRATO = "EX";
    private static final String TIPO_RELATORIO_MANUTENCOES = "MN";
    private static final String TIPO_RELATORIO_DADOS_COMPLEMENTARES = "DC";
	private static final Object VISUALIZACAO_ANALITICO = "A";
    
	//Combos da tela
	private FiltroUtil filtro;
	
	private final FiltroVO model = new FiltroVO();
	
	@Autowired
	private transient ConsultarRelatorioFacade consultarRelatorioFacade;
	
	private transient InputStream fileInputStream;
	
	public String consultarRelatorio(){
		try {
			carregarComboCompanhia();
			carregarComboDepartamentos();
			carregarComboMotivos();
			
			model.setAbrirRelatorio(false);
			model.setDeposito(0);
			
			String tipoRelatorio = model.getTipoRelatorio();
			String visualizacao = model.getVisualizacao();
			
			if (TIPO_RELATORIO_ENVIO_RETORNO.equals(tipoRelatorio)) {
				if (VISUALIZACAO_ANALITICO.equals(visualizacao)) {
					exibirEnvioRetornoAnalitico();			
				}
				else {
					exibirEnvioRetornoSintetico();			
				}
			}
			else if (TIPO_RELATORIO_EXTRATO.equals(tipoRelatorio)) {
				if (VISUALIZACAO_ANALITICO.equals(visualizacao)) {
					exibirExtratoAnalitico();		
				}
				else {
					exibirExtratoSintetico();		
				}
			}
			else if (TIPO_RELATORIO_MANUTENCOES.equals(tipoRelatorio)) {
				if (VISUALIZACAO_ANALITICO.equals(visualizacao)) {
					exibirManutencoesAnalitico();			
				}
				else {
					exibirManutencoesSintetico();			
				}
			}
			else if (TIPO_RELATORIO_DADOS_COMPLEMENTARES.equals(tipoRelatorio)) {
				exibirDadosComplementares();			
			}
		}
		catch (Exception e) {
			addActionError(e.getMessage());
			LOGGER.error("Erro na exibição do formulário", e);
		}
		return SUCCESS;	
	}

	private void exibirEnvioRetornoAnalitico() throws DEPIIntegrationException {
		model.setSubtituloTela("Envio/Retorno Banco - Analítico");
		model.setTituloTabela("Dados de Envio/Retorno Banco - Analítio");
		model.setTipoRelatorio("ER");
		model.setVisualizacao("A");
	}
	
	private void exibirEnvioRetornoSintetico() throws DEPIIntegrationException {
    	model.setSubtituloTela("Envio/Retorno Banco - Sintético");
    	model.setTituloTabela("Dados de Envio/Retorno Banco - Sintético");
    	model.setTipoRelatorio("ER");
    	model.setVisualizacao("S");
    }

	private void exibirExtratoAnalitico() throws DEPIIntegrationException {
    	model.setSubtituloTela("Extrato Banco - Analítio");
    	model.setTituloTabela("Dados de Extrato Banco - Analítio");
    	model.setTipoRelatorio("EX");
    	model.setVisualizacao("A");
    }

	private void exibirExtratoSintetico() throws DEPIIntegrationException {
    	model.setSubtituloTela("Extrato Banco - Sintético");
    	model.setTituloTabela("Dados de Extrato Banco - Sintético");
    	model.setTipoRelatorio("EX");
    	model.setVisualizacao("S");
    }
	  
	private void exibirManutencoesAnalitico() throws DEPIIntegrationException {
    	model.setSubtituloTela("Manutenções - Analítio");
    	model.setTituloTabela("Dados de Manutenções - Analítio");
    	model.setTipoRelatorio("MN");
    	model.setVisualizacao("A");
    }

	private void exibirManutencoesSintetico() throws DEPIIntegrationException {
    	model.setSubtituloTela("Manutenções - Sintético");
    	model.setTituloTabela("Dados de Manutenções - Sintético");
    	model.setTipoRelatorio("MN");
    	model.setVisualizacao("S");
    }
	  
	private void exibirDadosComplementares() throws DEPIIntegrationException {
    	model.setSubtituloTela("Dados Complementares - Analítio");
    	model.setTituloTabela("Dados Complementares - Analítio");
    	model.setTipoRelatorio("DC");
    	model.setVisualizacao("A");
    	model.setSituacaoEnvioRetorno("A");
	}

	/**
	 * Retorna valores do form para um nova inst�ncia do tipo FiltroUtil
	 * @param FiltroUtil
	 * @return FiltroUtil
	 * @throws DEPIIntegrationException - DEPIIntegrationException
	 */	  	  
	private FiltroUtil montaFiltro(FiltroUtil filtro){
		if(filtro == null ){
			filtro = new FiltroUtil(); 
		}

		filtro.setCodigoCia(0);
		filtro.setCodigoDepartamento(0);
		filtro.setCodigoMotivo(0);
		filtro.setApolice(0);
		filtro.setSucursal(0);
		filtro.setEndosso(0);
		filtro.setTipoDeposito(0);
		filtro.setCodigoAutorizador(0);
		filtro.setSituacaoManutencao(0);
		filtro.setSituacaoManutencao(0);
		filtro.setValorInicial(0.0);
		filtro.setValorFinal(0.0); 
		filtro.setCpfCnpj("");
		filtro.setSituacaoArquivo(0);

		if (!BaseUtil.isNZB(model.getCodigoCompanhia() )) {
			filtro.setCodigoCia(new Integer(model.getCodigoCompanhia()));
		}

		if (!BaseUtil.isNZB(model.getCodigoAutorizador())) {
			filtro.setCodigoAutorizador(Integer.valueOf(model.getCodigoAutorizador()));
		}

		if (!BaseUtil.isNZB(model.getCodigoDepartamento())) {
			filtro.setCodigoDepartamento(new Integer(model.getCodigoDepartamento()));
		}

		if (!BaseUtil.isNZB(model.getCodigoMotivoDeposito())) {
			filtro.setCodigoMotivo(Integer.valueOf( model.getCodigoMotivoDeposito()));
		}

		if (!BaseUtil.isNZB(model.getApolice())) {
			filtro.setApolice(Integer.valueOf(model.getApolice()));
		}

		if (!BaseUtil.isNZB(model.getSucursal())) {
			filtro.setSucursal(Integer.valueOf(model.getSucursal()));
		}

		if (!BaseUtil.isNZB(model.getEndosso())) {
			filtro.setEndosso(Integer.valueOf(model.getEndosso()));
		}

		if (!BaseUtil.isNZB(model.getDeposito())) {
			filtro.setTipoDeposito(model.getDeposito());
		}

		if (!BaseUtil.isNZB(model.getCpfCnpj())) {
			filtro.setCpfCnpj(BaseUtil.retiraMascaraCNPJ(model.getCpfCnpj()));
		}

		if (!BaseUtil.isNZB(model.getDataInicial())) {
			filtro.setDataInicio(BaseUtil.parserStringToDate(model.getDataInicial().concat(" ").concat(ConstantesDEPI.HORA_INI),
					ConstantesDEPI.FORMATO_DATO_HORA2));
		}

		if (!BaseUtil.isNZB(model.getDataFinal())) {
			filtro.setDataFinal(BaseUtil.parserStringToDate(model.getDataFinal().concat(" ").concat(ConstantesDEPI.HORA_FIM),
					ConstantesDEPI.FORMATO_DATO_HORA2));
		}

		if (!BaseUtil.isNZB(model.getValorInicial())) {
			filtro.setValorInicial(new Double(model.getValorInicial()));
		}

		if (!BaseUtil.isNZB(model.getValorFinal())) {
			filtro.setValorFinal(new Double(model.getValorFinal()));
		}

		return filtro;
	}

	public String realizarConsulta() {
		LOGGER.error("XXXXXX="+model.toString());
		String retorno = SUCCESS;
		
		String tipoRelatorio = model.getTipoRelatorio();
		String visualizacao = model.getVisualizacao();
		
		if (TIPO_RELATORIO_ENVIO_RETORNO.equals(tipoRelatorio)) {
			if (VISUALIZACAO_ANALITICO.equals(visualizacao)) {
				retorno = this.consultarEnvioRetornoAnalitico();
			}
			else {
				retorno = this.consultarEnvioRetornoSintetico();
			}
		}
		else if (TIPO_RELATORIO_EXTRATO.equals(tipoRelatorio)) {
			if (VISUALIZACAO_ANALITICO.equals(visualizacao)) {
				retorno = this.consultarExtratoAnalitico();
			}
			else {
				retorno = this.consultarExtratoSintetico();
			}
		}
		else if (TIPO_RELATORIO_MANUTENCOES.equals(tipoRelatorio)) {
			if (VISUALIZACAO_ANALITICO.equals(visualizacao)) {
				retorno = this.consultarManutencoesAnalitico();				
			}
			else {
				retorno = this.consultarManutencoesSintetico();
			}
		}
		else if (TIPO_RELATORIO_DADOS_COMPLEMENTARES.equals(tipoRelatorio)) {
			retorno = this.consultarDadosComplementaresAnalitico();
		}
		
		return retorno;
	}
	
	@Override
	public String input() throws Exception {
		filtro = this.montaFiltro(filtro);
		
		return super.input();
	}
	
	public void validateRealizarConsulta() {
		filtro = montaFiltro(filtro);
		if (BaseUtil.isNZB(filtro.getDataInicio())) {
			addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{DATA_INICIAL}));
		}
		
		if (BaseUtil.isNZB(filtro.getDataFinal())) {
			addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{DATA_FINAL}));
		}
		
		if (BaseUtil.isNZB(model.getTipoRelatorio())) {
			addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{"tipoRelatorio"}));
		}
		else {
			if (! TIPO_RELATORIO_DADOS_COMPLEMENTARES.equals(model.getTipoRelatorio()) && BaseUtil.isNZB(model.getVisualizacao())) {
				addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{"visualizacao"}));
			}
		}
	}
	
	public String consultarEnvioRetornoAnalitico() throws DEPIIntegrationException {
	
		exibirEnvioRetornoAnalitico();

		if (! model.isAbrirRelatorio()) {
			model.setAbrirRelatorio(true);
			return INPUT;
		}
	
		try {
			List<RelatorioEnvioRetornoVO> dados = consultarRelatorioFacade
					.obterDadosEnvioRetornoAnalitico(filtro, getIp(),
							getCodUsuarioLogado());

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}
	
			Map<String, Serializable> params = prepararParametrosComunsPdf();
	
			model.setDadosRelatorio(new DadosRelatorioVO(
					"relEnvioRetornoBancoAnalitico.pdf",
					"relEnvioRetornoBancoAnalitico.jasper", params, dados));
			
		} catch (Exception e) {
			LOGGER.error("Erro ao gerar relat\u00f3rio", e);
			addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			return INPUT;
		}
		return SUCCESS;// exibirEnvioRetornoAnalitico();
	}

	public String consultarEnvioRetornoSintetico() throws DEPIIntegrationException {

		exibirEnvioRetornoSintetico();

		if (! model.isAbrirRelatorio()) {
			model.setAbrirRelatorio(true);
			return INPUT;
		}
	
		try {
	
			List<RelatorioEnvioRetornoVO> dados = consultarRelatorioFacade.obterDadosEnvioRetornoSintetico(filtro, getIp(),
					getCodUsuarioLogado());
//			List<RelatorioEnvioRetornoVO> dados = consultarRelatorioFacade.obterDadosEnvioRetornoAnalitico(filtro);

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}
	
			Map<String, Serializable> params = prepararParametrosComunsPdf();
			
			model.setDadosRelatorio(new DadosRelatorioVO(
					"relEnvioRetornoBancoSintetico.pdf",
					"relEnvioRetornoBancoSintetico.jasper", params, dados));
	
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Erro ao gerar relat\u00f3rio", e);
			addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			model.setAbrirRelatorio(false);
			return INPUT;
		}
		return SUCCESS;
	}

	public String consultarExtratoAnalitico() throws DEPIIntegrationException {
		
		exibirExtratoAnalitico();
		
		if (! model.isAbrirRelatorio()) {
			model.setAbrirRelatorio(true);
			return INPUT;
		}

		try {
			List<RelatorioExtratoAnaliticoVO> dados = consultarRelatorioFacade
					.obterDadosBancoExtratoAnalitico(filtro, getIp(),
							getCodUsuarioLogado());
			
    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}
	
			Map<String, Serializable> params = prepararParametrosComunsPdf();
			params.put(VISUALIZACAO, "Analítio");
			params.put(SITUACAO, "TODOS");
			
			model.setDadosRelatorio(new DadosRelatorioVO("relExtratoAnalitico.pdf",
					"relExtratoAnalitico.jasper", params, dados));
	
		} catch (Exception e) {
			LOGGER.error("Erro ao gerar relat\u00f3rio", e);
			addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			model.setAbrirRelatorio(false);
			return INPUT;
		}
	
		return SUCCESS;
	}

	public String consultarExtratoSintetico() throws DEPIIntegrationException {

		exibirExtratoSintetico();
		
		if (! model.isAbrirRelatorio()) {
			model.setAbrirRelatorio(true);
			return INPUT;
		}
	
		try {
			List<RelatorioExtratoSinteticoVO> dados = consultarRelatorioFacade.obterDadosBancoExtratoSintetico(filtro);

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}
	
			Map<String, Serializable> params = prepararParametrosComunsPdf();
			params.put(VISUALIZACAO, "Sintético");
			params.put(SITUACAO, "TODOS");
			
			model.setDadosRelatorio(new DadosRelatorioVO("relExtratoSintetico.pdf",
					"relExtratoSintetico.jasper", params, dados));

		} catch (Exception e) {
			LOGGER.error("Erro ao gerar relat\u00f3rio", e);
			addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			model.setAbrirRelatorio(false);
			return INPUT;
		}
	
		return SUCCESS;       
	}

	public String consultarManutencoesAnalitico(){      
	
		exibirManutencoesAnalitico();
		
		if (! model.isAbrirRelatorio()) {
			return INPUT;
		}
	
		try {
			List<ManutencoesAnaliticoVO> dados =  consultarRelatorioFacade.obterDadosManutencoesAnalitico(filtro);

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}
	
			Map<String, Serializable> params = prepararParametrosComunsPdf();
			params.put(VISUALIZACAO, "Analítio");
			params.put(SITUACAO, "TODOS");
			
			model.setDadosRelatorio(new DadosRelatorioVO(
					"relManutencoesAnalitico.pdf",
					"relManutencoesAnalitico.jasper", params, dados));
	
		} catch (IntegrationException e) {
			LOGGER.error("Erro ao gerar relat\u00f3rio", e);
			addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			model.setAbrirRelatorio(false);
			return INPUT;
		}
		return SUCCESS;
	}

	public String consultarManutencoesSintetico() throws DEPIIntegrationException {

		exibirManutencoesSintetico();
		
		if (! model.isAbrirRelatorio()) {
			model.setAbrirRelatorio(true);
			return INPUT;
		}
		
    	try {

    		List<ManutencoesSinteticoVO> dados = consultarRelatorioFacade.obterDadosManutencoesSintetico(filtro);

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}

			Map<String, Serializable> params = prepararParametrosComunsPdf();
    		
    		String situacao = "";//RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(c.getSituacaoManutencao());

    		if (BaseUtil.isNZB(situacao)) {
    			situacao = "Manutenções";
    		}

    		StringBuilder sb = new StringBuilder().append("DEPOSITOS IDENTIFICADOS - ").append(situacao)
    				.append(" - ").append("Sintético");
    		params.put(HEADER, sb.toString());

    		params.put(VISUALIZACAO, "Analítio");
    		
    		model.setDadosRelatorio(new DadosRelatorioVO(
					"relManutencoesSintetico.pdf",
					"relManutencoesSintetico.jasper", params, dados));

    	} catch (DEPIIntegrationException e) {
    		LOGGER.error("Erro ao gerar relat\u00f3rio", e);
    		addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			model.setAbrirRelatorio(false);
    		return INPUT;
    	}
    	return SUCCESS;
    }


    public String consultarDadosComplementaresAnalitico() throws DEPIIntegrationException {
		
		exibirDadosComplementares();
		
		if (! model.isAbrirRelatorio()) {
			model.setAbrirRelatorio(true);
			return INPUT;
		}
		
		try {
			List<RelatorioDadosComplementaresVO> dados = consultarRelatorioFacade.obterDadosComplementares(filtro);

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
    			model.setAbrirRelatorio(false);
    			return INPUT;
    		}

			BigDecimal valorTotalPago = BigDecimal.ZERO;
			BigDecimal valorTotalRegistrado =  BigDecimal.ZERO;
	
			//Itera lista para obter totais gerais
			for(RelatorioDadosComplementaresVO relatorio : dados){
				if(relatorio.getValorPago() != null){
					valorTotalPago = relatorio.getValorPago();
				}
				if(relatorio.getValorRegistrado() != null){
					valorTotalRegistrado = relatorio.getValorRegistrado();
				}
			}
			/*
	            if (BaseUtil.isNZB(dados)) {
	                addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));
	                return exibirDadosComplementares();
	            }
			 */
			Map<String, Serializable> params = prepararParametrosComunsPdf();
			params.put(VISUALIZACAO, "Analítio");
			params.put(SITUACAO, filtro.getSituacaoManutencao());
	
			params.put(VALOR_TOTAL_PAGO, valorTotalPago);
			params.put(VALOR_TOTAL_REGISTRADO, valorTotalRegistrado);
				
			model.setDadosRelatorio(new DadosRelatorioVO("relDadosComplementares.pdf",
					"relDadosComplementares.jasper", params, dados));
	
		} catch (DEPIIntegrationException e) {
			LOGGER.error("Erro ao gerar relat\u00f3rio", e);
			addActionError(getText(ConstantesDEPI.ERRO_CUSTOMIZADA, new String[]{e.getMessage()}));
			model.setAbrirRelatorio(false);
			return INPUT;
		}
		return SUCCESS;
	}

	private Map<String, Serializable> prepararParametrosComunsPdf() {
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		params.put(DATA_INICIO,  RelogioUtil.formataDate(filtro.getDataInicio()));
		params.put(DATA_FIM, RelogioUtil.formataDate(filtro.getDataFinal()));
	
		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());
		return params;
	}

	private void carregarComboCompanhia() throws DEPIIntegrationException {

    	LoginVo login = getUsuarioLogado();

    	List<CompanhiaSeguradoraVO> listaRetorno = 
    			consultarRelatorioFacade.carregarComboCompanhiaUsuLogado(login);
    	model.setListaCompanhia(listaRetorno);
    	
		TreeSet<CompanhiaSeguradoraVO> ciasOrdenadas = new TreeSet<CompanhiaSeguradoraVO>(
				ConsultarRelatorioAction.ordenaCompanhia);
		ciasOrdenadas.addAll(listaRetorno);

    	model.setListaCompanhiaOrd(new ArrayList<>(ciasOrdenadas));
    	
		if (BaseUtil.isNZB(model.getCodigoCompanhia())) {
			model.setCodigoCompanhia(ZERO_STR);
		}
    }
    
    private static Comparator<CompanhiaSeguradoraVO> ordenaCompanhia = new Comparator<CompanhiaSeguradoraVO>() {
    	@Override
    	public int compare(CompanhiaSeguradoraVO p1, CompanhiaSeguradoraVO p2) {
    		return p1.getDescricaoCompanhia().compareTo(p2.getDescricaoCompanhia());
    	};
    };

    private void carregarComboMotivos() throws DEPIIntegrationException {
    	LoginVo login = getUsuarioLogado();

    	if (!BaseUtil.isNZB(model.getCodigoCompanhia()) && !BaseUtil.isNZB(model.getCodigoDepartamento())) {
    		int codigoCompanhia = Integer.parseInt(model.getCodigoCompanhia());
			int codigoDepartamento = Integer.parseInt(model.getCodigoDepartamento());
			
			List<MotivoDepositoVO> listaRetorno = consultarRelatorioFacade.obterMotivoComRestricaoDeDeposito(
    				codigoCompanhia, codigoDepartamento, login);
			
    		if (!BaseUtil.isNZB(listaRetorno)) {
    			Collections.sort(listaRetorno, ordenaMotivoBasico);
    			model.setListaMotivosDepositos(listaRetorno);
    			model.setDescricaoDetalhada("");
    			
				if (!BaseUtil.isNZB(model.getCodigoMotivoDeposito())) {
					int codigoMotivoDeposito = Integer.parseInt(model.getCodigoMotivoDeposito());
    				
    				for (MotivoDepositoVO mot : listaRetorno) {
    					if (mot.getCodigoMotivoDeposito() == codigoMotivoDeposito) {
    						model.setDescricaoDetalhada(mot.getDescricaoDetalhada());
    						break;
    					}
    				}
    			}
				else {
					model.setCodigoMotivoDeposito(ZERO_STR);
				}
    		}
    	}
    }

    /**
     * Comparador de MotivoDepositoVO onde a ordena��o ser� realizada pela descri��o b�sica do motivo de dep�sito.
     */
    private static Comparator<MotivoDepositoVO> ordenaMotivoBasico = new Comparator<MotivoDepositoVO>() {
    	@Override
    	public int compare(MotivoDepositoVO p1, MotivoDepositoVO p2) {
    		return p1.getDescricaoBasica().compareTo(p2.getDescricaoBasica());
    	};
    };

    private void carregarComboDepartamentos() throws DEPIIntegrationException {
    	LoginVo login = getUsuarioLogado();

    	try {
    		if (!BaseUtil.isNZB(model.getCodigoCompanhia())) {
    			int codigoCompanhia = Integer.parseInt(model.getCodigoCompanhia());
    			
				List<DepartamentoVO> listaRetorno = consultarRelatorioFacade
						.obterComDepositoRestricaoDeDeposito(codigoCompanhia, login);
				
    			if (!BaseUtil.isNZB(listaRetorno)) {
    				List<DepartamentoVO> departamentosOrdenados = new ArrayList<DepartamentoVO>(listaRetorno);
    				Collections.sort(departamentosOrdenados, ordenaDepartamento);

    				model.setListaDepartamentos(departamentosOrdenados);
    				request.setAttribute("departamentosOrdenados", departamentosOrdenados);

					if (!BaseUtil.isNZB(model.getCodigoDepartamento())) {
						int codigoDepartamento = Integer.parseInt(model.getCodigoDepartamento());
						
    					for (DepartamentoVO dep : listaRetorno) {
    						if (dep.getCodigoDepartamento() == codigoDepartamento) {
    							break;
    						}
    					}
    				}
					else {
						model.setCodigoDepartamento(ZERO_STR);
					}
    			}
    		}

    	} catch (DEPIIntegrationException e) {
    		LOGGER.error("Falha carregamento departamento", e);
    		throw new DEPIIntegrationException(e.getMessage());
    	}
    }
    /**
     * Comparador de DepartamentoVO onde a ordena��o ser� realizada pelo c�digo do departamento.
     */
    private static Comparator<DepartamentoVO> ordenaDepartamento = new Comparator<DepartamentoVO>() {
    	@Override
    	public int compare(DepartamentoVO p1, DepartamentoVO p2) {
    		return p1.getSiglaDepartamento().compareTo(p2.getSiglaDepartamento());
    	};
    };   

    /**
     * Método utilizado para gerar relatório em PDF.
     * @throws DEPIIntegrationException - Integração.
     */
    public String gerarRelatorio() throws DEPIIntegrationException {
    	LOGGER.error("gerarPdf 1");  
    	try {
    		DadosRelatorioVO dadosRelatorio = model.getDadosRelatorio();
    		List<? extends Serializable> dados = dadosRelatorio.getDados();
    		Map<String, Serializable> parametros = dadosRelatorio.getParams();
    		String nomeRelatorio = dadosRelatorio.getRelatorio();
    		
    		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);
			File reportFile = new File(request.getSession().getServletContext()
					.getRealPath(ConstantesDEPI.DIR_RELATORIOS + nomeRelatorio));
    		
    		InputStream in = new FileInputStream(reportFile);
     		JasperReport jr = (JasperReport) JRLoader.loadObject(in);
     		JasperPrint jp = JasperFillManager.fillReport(jr, parametros, ds);
			
     		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024 * 1024);
     		JasperExportManager.exportReportToPdfStream(jp, outputStream);
    		
    		byte[] bytes = outputStream.toByteArray();

    		InputStream is = new ByteArrayInputStream(bytes);
    		LOGGER.error("Proposta Action - gerarCartaInterna - fim");
    		this.fileInputStream = is;
    		
    		String retorno = dadosRelatorio.getRetorno();
			model.setFileNameReport(retorno);
    		return retorno;

    	} catch (Exception e) {
    		LOGGER.error("Falha na geração do relatório", e);
    		
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CUSTOMIZADA,
					new String[] { e.getMessage() });
    	} finally {
    		// Para liberar o espaço em memória, depois de ter gerado o relatório.
    		model.setDadosRelatorio(null); 
    	}
    }

    public InputStream getFileInputStream() {
		return fileInputStream;
	}
    
    public FiltroVO getFiltroVO() {
    	return model;
    }

    @Override
    public FiltroVO getModel() {
    	return model;
    }
    
}
