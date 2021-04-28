package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.cadastro.form.RelatorioFormModel;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseModelAction;
import br.com.bradseg.depi.depositoidentificado.relatorio.facade.ConsultarRelatorioFacade;
import br.com.bradseg.depi.depositoidentificado.relatorio.util.RelogioUtil;
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
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoSinteticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;





@Controller
@Scope("session")
public class ConsultarRelatorioAction extends BaseModelAction<RelatorioFormModel>  {
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
	
	private static final String EXIBIRENVIORETORNOANALITICO = "exibirEnvioRetornoAnalitico";
	private static final String EXIBIRENVIORETORNOSINTETICO = "exibirEnvioRetornoSintetico";
	private static final String EXIBIREXTRATOANALITICO      = "exibirExtratoAnalitico";
	private static final String EXIBIREXTRATOSINTETICO      = "exibirExtratoSintetico";
	private static final String EXIBIRMANUTENCOESANALITICO  = "exibirManutencoesAnalitico";
	private static final String EXIBIRMANUTENCOESSINTETICO  = "exibirManutencoesSintetico";
	private static final String EXIBIRDADOSCOMPLEMENTARES   = "exibirDadosComplementares";
	private final RelatorioFormModel model = new RelatorioFormModel();
	
	private int codigoCompanhia = 0;
    private int codigoDepartamento = 0;
    private int codigoMotivoDeposito = 0;
	
	//Combos da tela

	private List<CompanhiaSeguradoraVO> listaCompanhia = new ArrayList<CompanhiaSeguradoraVO>();
	private List<CompanhiaSeguradoraVO> listaCompanhiaOrd  = new ArrayList<CompanhiaSeguradoraVO>();
	
	private List<DepartamentoVO> listaDepartamentos = new ArrayList<DepartamentoVO>();
	private List<DepartamentoVO> listaDepartamentosOrd = new ArrayList<DepartamentoVO>();
	private List<MotivoDepositoVO> listaMotivosDepositos = new ArrayList<MotivoDepositoVO>();
	
	private FiltroUtil filtro;
	
	private FiltroVO filtroVO = new FiltroVO();
    
	@Autowired
	private ConsultarRelatorioFacade consultarRelatorioFacade;
	private InputStream fileInputStream;
    
	
	public String consultarRelatorio(){
		carregarComboCompanhia();
		
		CompanhiaSeguradoraVO cia  = new CompanhiaSeguradoraVO();
		cia.setCodigoCompanhia(0);
		cia.setDescricaoCompanhia("Todos");
		listaCompanhia.add(cia);
		cia.setCodigoCompanhia(686);
		cia.setDescricaoCompanhia("Bardesco Seguros");
		listaCompanhia.add(cia);
		model.setTpcCiasOrdenadas("FALSE");
		
		String acao = model.getAcao();
		
		try {
			if (EXIBIRENVIORETORNOANALITICO.equals(acao)){
				exibirEnvioRetornoAnalitico();			
			}
			else if (EXIBIRENVIORETORNOSINTETICO.equals(acao)){
				exibirEnvioRetornoSintetico();			
			}
			else if (EXIBIREXTRATOANALITICO.equals(acao)){
				exibirExtratoAnalitico();		
			}
			else if (EXIBIREXTRATOSINTETICO.equals(acao)){
				exibirExtratoSintetico();		
			}
			else if (EXIBIRMANUTENCOESANALITICO.equals(acao)){
				exibirManutencoesAnalitico();			
			}
			else if (EXIBIRMANUTENCOESSINTETICO.equals(acao)){
				exibirManutencoesSintetico();			
			}
			else if (EXIBIRDADOSCOMPLEMENTARES.equals(acao)){
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
		model.setSubtitulo("Envio/Retorno Banco - Analítico");
		model.setTituloTabela("Dados de Envio/Retorno Banco - Analítio");
		model.setTipoRelatorio("ER");
		model.setVisualizacao("A");
		model.setAcao(EXIBIRENVIORETORNOANALITICO);
		model.setAcaoAnterior(EXIBIRENVIORETORNOANALITICO);

		carregarComboCompanhia();
		carregarComboDepartamentos();
		carregarComboMotivos();
	}
	
	private void exibirEnvioRetornoSintetico() throws DEPIIntegrationException {
    	model.setSubtitulo("Envio/Retorno Banco - Sintético");
    	model.setTituloTabela("Dados de Envio/Retorno Banco - Sintético");
    	model.setTipoRelatorio("ER");
    	model.setVisualizacao("S");
    	model.setAcao(EXIBIRENVIORETORNOSINTETICO);
    	model.setAcaoAnterior(EXIBIRENVIORETORNOSINTETICO);
    	
    	carregarComboCompanhia();
    }

	private void exibirExtratoAnalitico() throws DEPIIntegrationException {

    	model.setSubtitulo("Extrato Banco - Analítio");
    	model.setTituloTabela("Dados de Extrato Banco - Analítio");
    	model.setTipoRelatorio("EX");
    	model.setVisualizacao("A");
    	model.setAcao(EXIBIREXTRATOANALITICO);
    	model.setAcaoAnterior(EXIBIREXTRATOANALITICO);

    	carregarComboCompanhia();
    }

	private void exibirExtratoSintetico() throws DEPIIntegrationException {

    	model.setSubtitulo("Extrato Banco - Sintético");
    	model.setTituloTabela("Dados de Extrato Banco - Sintético");
    	model.setTipoRelatorio("EX");
    	model.setVisualizacao("S");
    	model.setAcao(EXIBIREXTRATOSINTETICO);
    	model.setAcaoAnterior(EXIBIREXTRATOSINTETICO);
    	
    	carregarComboCompanhia();
    }
	  
	private void exibirManutencoesAnalitico() throws DEPIIntegrationException {
    	model.setSubtitulo("Manutenções - Analítio");
    	model.setTituloTabela("Dados de Manutenções - Analítio");
    	model.setTipoRelatorio("MN");
    	model.setVisualizacao("A");
    	model.setAcaoAnterior("exibirManutencoesAnalitico");
    	model.setAcao(EXIBIRMANUTENCOESANALITICO);
    	model.setAcaoAnterior(EXIBIRMANUTENCOESANALITICO);

    	carregarComboCompanhia();
    }

	private void exibirManutencoesSintetico() throws DEPIIntegrationException {
    	model.setSubtitulo("Manutenções - Sintético");
    	model.setTituloTabela("Dados de Manutenções - Sintético");
    	model.setTipoRelatorio("MN");
    	model.setVisualizacao("S");
    	model.setAcaoAnterior("exibirManutencoesAnalitico");  
    	model.setAcao(EXIBIRMANUTENCOESSINTETICO);
    	model.setAcaoAnterior(EXIBIRMANUTENCOESSINTETICO);

    	carregarComboCompanhia();
    }
	  
	private void exibirDadosComplementares() throws DEPIIntegrationException {
    	model.setSubtitulo("Dados Complementares - Analítio");
    	model.setTituloTabela("Dados Complementares - Analítio");
    	model.setTipoRelatorio("DC");
    	model.setVisualizacao("A");
    	model.setSituacaoEnvioRetorno("A");
    	model.setAcao(EXIBIRDADOSCOMPLEMENTARES);
    	model.setAcaoAnterior(EXIBIRDADOSCOMPLEMENTARES);

    	carregarComboCompanhia();
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

		if (!BaseUtil.isNZB(filtroVO.getCodigoCompanhia() )) {
			filtro.setCodigoCia(new Integer(filtroVO.getCodigoCompanhia()));
		}

		if (!BaseUtil.isNZB(filtroVO.getCodigoAutorizador())) {
			filtro.setCodigoAutorizador(Integer.valueOf(filtroVO.getCodigoAutorizador()));
		}

		if (!BaseUtil.isNZB(filtroVO.getCodigoDepartamento())) {
			filtro.setCodigoDepartamento(new Integer(filtroVO.getCodigoDepartamento()));
		}

		if (!BaseUtil.isNZB(filtroVO.getCodigoMotivoDeposito())) {
			filtro.setCodigoMotivo(Integer.valueOf( filtroVO.getCodigoMotivoDeposito()));
		}

		if (!BaseUtil.isNZB(filtroVO.getApolice())) {
			filtro.setApolice(Integer.valueOf(filtroVO.getApolice()));
		}

		if (!BaseUtil.isNZB(filtroVO.getSucursal())) {
			filtro.setSucursal(Integer.valueOf(filtroVO.getSucursal()));
		}

		if (!BaseUtil.isNZB(filtroVO.getEndosso())) {
			filtro.setEndosso(Integer.valueOf(filtroVO.getEndosso()));
		}

		if (!BaseUtil.isNZB(filtroVO.getDeposito())) {
			filtro.setTipoDeposito(filtroVO.getDeposito());
		}

		if (!BaseUtil.isNZB(filtroVO.getCpfCnpj())) {
			filtro.setCpfCnpj(BaseUtil.retiraMascaraCNPJ(filtroVO.getCpfCnpj()));
		}

		if (!BaseUtil.isNZB(filtroVO.getDataInicial())) {
			filtro.setDataInicio(BaseUtil.parserStringToDate(filtroVO.getDataInicial().concat(" ").concat(ConstantesDEPI.HORA_INI),
					ConstantesDEPI.FORMATO_DATO_HORA2));
		}

		if (!BaseUtil.isNZB(filtroVO.getDataFinal())) {
			filtro.setDataFinal(BaseUtil.parserStringToDate(filtroVO.getDataFinal().concat(" ").concat(ConstantesDEPI.HORA_FIM),
					ConstantesDEPI.FORMATO_DATO_HORA2));
		}

		if (!BaseUtil.isNZB(filtroVO.getValorInicial())) {
			filtro.setValorInicial(new Double(model.getValorInicial()));
		}

		if (!BaseUtil.isNZB(filtroVO.getValorFinal())) {
			filtro.setValorFinal(new Double(filtroVO.getValorFinal()));
		}

		return filtro;		 
	}	

	public String gerarRelatorio() {
		LOGGER.error("XXXXXX="+filtroVO.toString());
		String acao = this.filtroVO.getAcao();
		String retorno = SUCCESS;
		if(EXIBIRENVIORETORNOANALITICO.equals(acao)){
			retorno = this.consultarEnvioRetornoAnalitico();
		}
		else if(EXIBIRENVIORETORNOSINTETICO.equals(acao)){
			retorno = this.consultarEnvioRetornoSintetico();
		}
		else if(EXIBIREXTRATOANALITICO.equals(acao)){
			retorno = this.consultarExtratoAnalitico();
		}
		else if(EXIBIREXTRATOSINTETICO.equals(acao)){
			retorno = this.consultarEnvioRetornoSintetico();
		}
		else if(EXIBIRMANUTENCOESANALITICO.equals(acao)){
			retorno = this.consultarManutencoesAnalitico();				
		}
		else if(EXIBIRMANUTENCOESSINTETICO.equals(acao)){
			retorno = this.consultarManutencoesSintetico();
		}
		else if(EXIBIRDADOSCOMPLEMENTARES.equals(acao)){		
			retorno = this.consultarDadosComplementaresAnalitico();
		}					     
		
		return retorno;
	}
	
	@Override
	public String input() throws Exception {
		filtro = this.montaFiltro(filtro);
		
		return super.input();
	}
	
	public void validateGerarRelatorio() {
		filtro = montaFiltro(filtro);
		if (BaseUtil.isNZB(filtro.getDataInicio())) {
			addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{DATA_INICIAL}));
		}
		
		if (BaseUtil.isNZB(filtro.getDataFinal())) {
			addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{DATA_FINAL}));
		}
		
		if (BaseUtil.isNZB(filtroVO.getAcao())) {
			addActionError(getText(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, new String[]{"acao"}));
		}
	}
	
	public String consultarManutencoesAnalitico(){      

		exibirManutencoesAnalitico();

		try {
			List<ManutencoesAnaliticoVO> dados =  consultarRelatorioFacade.obterDadosManutencoesAnalitico(filtro);
			if (dados.size() == 0){
				addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
				return INPUT;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put(VISUALIZACAO, "Analítio");

			params.put( SITUACAO, filtro.getSituacaoManutencao() );
			params.put(DATA_INICIO, filtro.getDataInicio() );
			params.put(DATA_FIM, filtro.getDataFinal() );
			params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
			params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
			params.put(SEQUENCIAL, RelogioUtil.obterSequencial());

			gerarPdf("relManutencoesAnalitico.jasper", params, dados);

		} catch (IntegrationException e) {
			LOGGER.error(e.getMessage());
			return INPUT;
		}
		return "relManutencoesAnalitico.pdf";//exibirManutencoesAnalitico();//();
	}
	
    public String consultarDadosComplementaresAnalitico() throws DEPIIntegrationException {

    	try {
    		List<RelatorioDadosComplementaresVO> dados = consultarRelatorioFacade.obterDadosComplementares(filtro);

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
                    addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                    return exibirDadosComplementares();
                }
    		 */
    		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put(VISUALIZACAO, "Analítio");
    		params.put(SITUACAO, filtro.getSituacaoManutencao());
    		params.put(DATA_INICIO, sdf.format(filtro.getDataInicio()));
    		params.put(DATA_FIM, sdf.format(filtro.getDataFinal()));
    		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
    		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
    		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());
    		params.put(VALOR_TOTAL_PAGO, valorTotalPago);
    		params.put(VALOR_TOTAL_REGISTRADO, valorTotalRegistrado);

    		gerarPdf("relDadosComplementares.jasper", params, dados);

    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		return "relDadosComplementares.pdf";
    	}
    	return "relDadosComplementares.pdf";//exibirDadosComplementares();
    }

    public String consultarManutencoesSintetico() throws DEPIIntegrationException {

    	try {

    		List<ManutencoesSinteticoVO> dados = consultarRelatorioFacade.obterDadosManutencoesSintetico(filtro);


    		if (BaseUtil.isNZB(dados)) {
    			addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
    			exibirManutencoesSintetico();
    			return INPUT;
    		}

    		Map<String, Object> params = new HashMap<String, Object>();
    		String situacao = "";//RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(c.getSituacaoManutencao());

    		if (BaseUtil.isNZB(situacao)) {
    			situacao = "Manutenções";
    		}

    		StringBuilder sb = new StringBuilder().append("DEPOSITOS IDENTIFICADOS - ").append(situacao)
    				.append(" - ").append("Sintético");
    		params.put(HEADER, sb.toString());
    		params.put(DATA_INICIO, filtro.getDataInicio());
    		params.put(DATA_FIM, filtro.getDataFinal());

    		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
    		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
    		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());

    		gerarPdf("relManutencoesSintetico.jasper", params, dados);
    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		return INPUT;
    	}
    	return "relManutencoesSintetico.pdf";// exibirManutencoesSintetico();
    }


    public String consultarEnvioRetornoAnalitico() throws DEPIIntegrationException {

    	//RelatorioForm frm = (RelatorioForm) pForm;

    	try {
    		List<RelatorioEnvioRetornoAnaliticoVO> colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoAnalitico(filtro);

    		if (BaseUtil.isNZB(colRelatorio)) {
    			addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);

    			exibirEnvioRetornoAnalitico();
    			return INPUT;
    		}

    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put(DATA_INICIO,  RelogioUtil.formataDate(filtro.getDataInicio()));
    		params.put(DATA_FIM, RelogioUtil.formataDate(filtro.getDataFinal()));

    		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
    		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
    		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());

    		gerarPdf( "relEnvioRetornoBancoAnalitico.jasper", params,  colRelatorio);
    		
    	} catch (Exception e) {
    		LOGGER.error("Erro ao gerar relat\u00f3rio", e);
    		throw new DEPIIntegrationException(ConstantesDEPI.ERRO_GENERICO, e.getMessage());
    	}
    	return "relEnvioRetornoBancoAnalitico.pdf";// exibirEnvioRetornoAnalitico();
    }
    public String consultarEnvioRetornoSintetico() throws DEPIIntegrationException {

    	try {

    		List<RelatorioEnvioRetornoSinteticoVO> colRelatorio = new ArrayList<RelatorioEnvioRetornoSinteticoVO>();
    		colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoSintetico(filtro);

    		if (BaseUtil.isNZB(colRelatorio)) {
    			addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
    			exibirEnvioRetornoSintetico();
    			return INPUT;
    		}

    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put(DATA_INICIO, filtro.getDataInicio());
    		params.put(DATA_FIM, filtro.getDataFinal());
    		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
    		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
    		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());

    		gerarPdf( "relEnvioRetornoBancoSintetico.jasper", params, colRelatorio);

    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	}
    	return "relEnvioRetornoBancoSintetico.pdf";// exibirEnvioRetornoSintetico();
    }

    public String consultarExtratoAnalitico() throws DEPIIntegrationException {        

    	List<RelatorioExtratoAnaliticoVO> dados = new ArrayList<RelatorioExtratoAnaliticoVO>();
    	try {
    		List<RelatorioEnvioRetornoAnaliticoVO> dadosER = consultarRelatorioFacade.obterDadosBancoExtratoAnalitico(filtro);              
    		if (dadosER == null || dadosER.isEmpty()) {
    			addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO); 
    			exibirExtratoAnalitico();
    			return INPUT;
    		}

    		for (RelatorioEnvioRetornoAnaliticoVO vo : dadosER) {
    			RelatorioExtratoAnaliticoVO extratoVO = new RelatorioExtratoAnaliticoVO();

    			extratoVO = (RelatorioExtratoAnaliticoVO) BaseUtil.copyProperties(extratoVO, vo);

    			extratoVO.setVencimento(vo.getVencimento());
    			extratoVO.setSituacaoEnvioRetorno(vo.getSituacao());
    			extratoVO.setSituacao(vo.getSituacao());
    			extratoVO.setCodigoAutorizadorComDv(vo.getCodigoAutorizadorComDv());
    			// extratoVO.setNomeGrupo("ENVIO E RETORNO");
    			dados.add(extratoVO);
    		}

    		/* List<ManutencoesAnaliticoVO> dadosManut = RelatorioBusinessDelegate.getInstance().obterDadosManutencoesAnalitico(c);
            if (BaseUtil.isNZB(dadosManut) && BaseUtil.isNZB(dadosER)) {
                addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                return exibirExtratoAnalitico();
            }

            // // Adiciona dados obtidos de Manutenções na lista de Extrato apo�s convers�o
            for (ManutencoesAnaliticoVO vo : dadosManut) {
                RelatorioExtratoAnaliticoVO extratoVO = new RelatorioExtratoAnaliticoVO();
                copyProperties(extratoVO, vo);
                extratoVO.setNomeGrupo("Manutenções");
                extratoVO.setSituacaoManutencao(vo.getCodigoTipoAcao());
                extratoVO.setVencimento(vo.getVencimento());
                extratoVO.setSituacao(RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(vo.getCodigoTipoAcao()));
                dados.add(extratoVO);
            }*/

    		consultarRelatorioFacade.obterTotais(dados);
    		//List<RelatorioExtratoAnaliticoVO> dados
    		consultarRelatorioFacade.ordenarDadosAnalitico(dados);

    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put(VISUALIZACAO, "Analítio");
    		params.put(SITUACAO, "TODOS");
    		params.put(DATA_INICIO, filtro.getDataInicio());
    		params.put(DATA_FIM, filtro.getDataFinal());

    		params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
    		params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
    		params.put(SEQUENCIAL, RelogioUtil.obterSequencial());

    		gerarPdf("relExtratoAnalitico.jasper", params, dados);

    	} catch (IllegalAccessException | InvocationTargetException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	}

    	return "relExtratoAnalitico.pdf";// exibirExtratoAnalitico();
    }


    public String consultarExtratoSintetico() throws DEPIIntegrationException {
    	filtro = montaFiltro(filtro);   

    	try {
    		List<RelatorioExtratoSinteticoVO> dados = consultarRelatorioFacade.obterDadosExtratoSintetico(filtro);

    		if (BaseUtil.isNZB(dados)) {
    			addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
    		} else {

    			Map<String, Object> params = new HashMap<String, Object>();
    			params.put(VISUALIZACAO, "Sintético");
    			params.put(SITUACAO, "TODOS");
    			params.put(DATA_INICIO, filtro.getDataInicio());
    			params.put(DATA_FIM, filtro.getDataFinal());

    			params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
    			params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
    			params.put(SEQUENCIAL, RelogioUtil.obterSequencial());

    			gerarPdf( "relExtratoSintetico.jasper", params, dados);

    		}
    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	}

    	return "relExtratoSintetico.pdf";//       
    }

    private void carregarComboCompanhia() throws DEPIIntegrationException {

    	LoginVo login = getUsuarioLogado();

    	List<CompanhiaSeguradoraVO> listaRetorno = 
    			consultarRelatorioFacade.carregarComboCompanhiaUsuLogado(login);
    	List<CompanhiaSeguradoraVO> ciasOrdenadas = new ArrayList<CompanhiaSeguradoraVO>(listaRetorno);
//    	ciasOrdenadas.sort(ordenaCompanhia);

    	model.setTpcCias("TRUE");
    	model.setTpcCiasOrdenadas("TRUE");

    	this.listaCompanhia = listaRetorno; 
    	this.setListaCompanhiaOrd(ciasOrdenadas);
    	if (!BaseUtil.isNZB(this.codigoCompanhia)) {
    		for (CompanhiaSeguradoraVO cia : listaRetorno) {
    			if (cia.equals(new CompanhiaSeguradoraVO(this.codigoCompanhia))) {
    				return;
    			}
    		}

    	}

    	this.codigoCompanhia = 0;
    }

    private static Comparator<CompanhiaSeguradoraVO> ordenaCompanhia = new Comparator<CompanhiaSeguradoraVO>() {
    	@Override
    	public int compare(CompanhiaSeguradoraVO p1, CompanhiaSeguradoraVO p2) {
    		return p1.getDescricaoCompanhia().compareTo(p2.getDescricaoCompanhia());
    	};
    };

    private void carregarComboMotivos() throws DEPIIntegrationException {
    	LoginVo login = getUsuarioLogado();

    	//request.setAttribute("motivos", new ArrayList<MotivoDepositoVO>());
    	if (!BaseUtil.isNZB(this.codigoCompanhia) && !BaseUtil.isNZB(this.codigoDepartamento)) {
    		List<MotivoDepositoVO> listaRetorno = consultarRelatorioFacade.obterMotivoComRestricaoDeDeposito(
    				this.codigoCompanhia, this.codigoDepartamento, login);
    		if (!BaseUtil.isNZB(listaRetorno)) {
    			Collections.sort(listaRetorno, ordenaMotivoBasico);
    			request.setAttribute("motivos", listaRetorno);
    			if (!BaseUtil.isNZB(this.codigoMotivoDeposito)) {
    				for (MotivoDepositoVO mot : listaRetorno) {
    					if (mot.equals(new MotivoDepositoVO(this.codigoMotivoDeposito, null, null))) {
    						model.setDescricaoDetalhada(mot.getDescricaoDetalhada());
    						return;
    					}
    				}
    			}

    			this.listaMotivosDepositos = listaRetorno;
    			this.codigoMotivoDeposito =0;
    			model.setDescricaoDetalhada("");
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
    		if (!BaseUtil.isNZB(this.codigoCompanhia)) {
    			List<DepartamentoVO> listaRetorno = consultarRelatorioFacade.obterComDepositoRestricaoDeDeposito(
    					Integer.valueOf( this.codigoCompanhia) , login);
    			if (!BaseUtil.isNZB(listaRetorno)) {
    				List<DepartamentoVO> departamentosOrdenados = new ArrayList<DepartamentoVO>(listaRetorno);
    				Collections.sort(departamentosOrdenados, ordenaDepartamento);

    				this.listaDepartamentos = listaRetorno;
    				this.listaDepartamentosOrd = departamentosOrdenados;
    				request.setAttribute("departamentosOrdenados", departamentosOrdenados);

    				if (!BaseUtil.isNZB(this.codigoDepartamento)) {
    					for (DepartamentoVO dep : listaRetorno) {
    						if (dep.equals(new DepartamentoVO(this.codigoDepartamento , null, null))) {
    							return;
    						}
    					}
    				}
    				this.codigoDepartamento =0;
    			}
    		}

    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		//throw new DEPIIntegrationException(e.getMessage());
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
     * @param parametros Parametros do Relatorio.
     * @throws IntegrationException - Integra��o.
     */
    public InputStream gerarPdf(String nomeRelatorio, Map<String, Object> parametros) throws DEPIIntegrationException, IOException {
    	Connection conn = null;
    	InputStream ouputStream = null;
    	conn = null; // Falta obter conn new  dao.getDataSource().getConnection();
    	File reportFile = new File(  request.getSession().getServletContext().getRealPath(ConstantesDEPI.DIR_RELATORIOS + nomeRelatorio));
    	String pathImg = this.getWww3()+ESTRUTURA_PASTA_IMAGENS;
    	//String url = this.getWww3()+ESTRUTURA_PASTA_IMAGENS;            
    	parametros.put(ConstantesDEPI.PARAM_LOGO, pathImg + ConstantesDEPI.DP06_LOGO_JPG);
    	byte[] bytes = null;
    	try {
    		bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, conn);
    		
    		try (InputStream is = new ByteArrayInputStream(bytes)) {
    			LOGGER.error("Proposta Action - gerarCartaInterna - fim");
    			ouputStream = is;
    		}
    	} catch (JRException e) {
    		// TODO Auto-generated catch block 
    		LOGGER.error(e.getMessage());
    	}
    	/*
        } catch (JRException e) {
           LOGGER.error(e.getMessage());
           throw new IntegrationException(e.getMessage());
        }
        } catch (SQLException e) {
           LOGGER.error(e.getMessage());
            throw new IntegrationException(e.getMessage());
        }
    	 */
    	return ouputStream;
    }

    /**
     * Método utilizado para gerar relatório em PDF.
     * @param nomeRelatorio Nome do Relatorio
     * @param parametros Parametros do Relatorio
     * @param dados Collection
     * @throws DEPIIntegrationException - Integração.
     */
    public void gerarPdf( String nomeRelatorio,Map<String, Object> parametros, Collection<?> dados) throws DEPIIntegrationException {
    	LOGGER.error("gerarPdf 1");  
    	try {
    		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dados);
    		File reportFile = new File(request.getSession().getServletContext().getRealPath(ConstantesDEPI.DIR_RELATORIOS + nomeRelatorio));
    		String pathImg = this.getWww3()+ESTRUTURA_PASTA_IMAGENS;
    		parametros.put(ConstantesDEPI.PARAM_LOGO, pathImg + ConstantesDEPI.DP06_LOGO_JPG);
    		// LOGGER.error("pathImg:"+pathImg);
    		// LOGGER.error("reportFile.getPath():"+reportFile.getPath());
    		// LOGGER.error("ConstantesDEPI.DP06_LOGO_JPG:"+ ConstantesDEPI.DP06_LOGO_JPG);
    		// LOGGER.error("PARAM_LOGO:"+ConstantesDEPI.PARAM_LOGO);
    		// LOGGER.error("pathImg:"+pathImg);
    		byte[] bytes = null;
    		bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, ds);

    		InputStream is = new ByteArrayInputStream(bytes);
    		LOGGER.error("Proposta Action - gerarCartaInterna - fim");
    		setFileInputStream(is);

    	} catch (JRException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	}
    }



    public int getCodigoCompanhia() {
    	return codigoCompanhia;
    }

    public void setCodigoCompanhia(int codigoCompanhia) {
    	this.codigoCompanhia = codigoCompanhia;
    }

    public int getCodigoDepartamento() {
    	return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
    	this.codigoDepartamento = codigoDepartamento;
    }

    public int getCodigoMotivoDeposito() {
    	return codigoMotivoDeposito;
    }

    public void setCodigoMotivoDeposito(int codigoMotivoDeposito) {
    	this.codigoMotivoDeposito = codigoMotivoDeposito;
    }

    public List<CompanhiaSeguradoraVO> getListaCompanhia() {
    	return listaCompanhia;
    }


    public void setListaCompanhia(List<CompanhiaSeguradoraVO> listaCompanhia) {
    	this.listaCompanhia = listaCompanhia;
    }


    public List<DepartamentoVO> getListaDepartamentos() {
    	return listaDepartamentos;
    }


    public void setListaDepartamentos(List<DepartamentoVO> listaDepartamentos) {
    	this.listaDepartamentos = listaDepartamentos;
    }


    public List<MotivoDepositoVO> getListaMotivosDepositos() {
    	return listaMotivosDepositos;
    }


    public void setListaMotivosDepositos(List<MotivoDepositoVO> listaMotivosDepositos) {
    	this.listaMotivosDepositos = listaMotivosDepositos;
    }



    public List<CompanhiaSeguradoraVO> getListaCompanhiaOrd() {
    	return listaCompanhiaOrd;
    }

    public void setListaCompanhiaOrd(List<CompanhiaSeguradoraVO> listaCompanhiaOrd) {
    	this.listaCompanhiaOrd = listaCompanhiaOrd;
    }

    public List<DepartamentoVO> getListaDepartamentosOrd() {
    	return listaDepartamentosOrd;
    }

    public void setListaDepartamentosOrd(List<DepartamentoVO> listaDepartamentosOrd) {
    	this.listaDepartamentosOrd = listaDepartamentosOrd;
    }

    public FiltroVO getFiltroVO() {
    	return filtroVO;
    }

    public void setFiltroVO(FiltroVO filtroVO) {

    	try {
    		this.filtroVO = filtroVO;	
    	} catch (Exception e) {
    		LOGGER.error("XXXX-"+e.getMessage());
    		if (filtroVO != null) {
    			LOGGER.error(filtroVO.toString());
    		}	
    		this.filtroVO = new FiltroVO();
    	}

    }

    public InputStream getFileInputStream() {
		return fileInputStream;
	}
    
    public void setFileInputStream(InputStream fileInputStream) {
    	this.fileInputStream = fileInputStream;
    }

    @Override
    public RelatorioFormModel getModel() {
    	return model;
    }

}
