package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

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
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioEnvioRetornoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoAnaliticoVO;
import br.com.bradseg.depi.depositoidentificado.vo.RelatorioExtratoSinteticoVO;





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
	
	private static final String EXIBIRENVIORETORNOANALITICO = "exibirEnvioRetornoAnalitico";
	private static final String EXIBIRENVIORETORNOSINTETICO = "exibirEnvioRetornoSintetico";
	private static final String EXIBIREXTRATOANALITICO      = "exibirExtratoAnalitico";
	private static final String EXIBIREXTRATOSINTETICO      = "exibirExtratoSintetico";
	private static final String EXIBIRMANUTENCOESANALITICO  = "exibirManutencoesAnalitico";
	private static final String EXIBIRMANUTENCOESSINTETICO  = "exibirManutencoesSintetico";
	private static final String EXIBIRDADOSCOMPLEMENTARES   = "exibirDadosComplementares";
	
	//Combos da tela
	private FiltroUtil filtro;
	
	private final FiltroVO model = new FiltroVO();
    
	@Autowired
	private ConsultarRelatorioFacade consultarRelatorioFacade;
	private InputStream fileInputStream;
	
	public String consultarRelatorio(){
		String acao = model.getAcao();
		try {
			carregarComboCompanhia();
			carregarComboDepartamentos();
			carregarComboMotivos();
			
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
    	model.setAcao(EXIBIRMANUTENCOESSINTETICO);
    }
	  
	private void exibirDadosComplementares() throws DEPIIntegrationException {
    	model.setSubtituloTela("Dados Complementares - Analítio");
    	model.setTituloTabela("Dados Complementares - Analítio");
    	model.setTipoRelatorio("DC");
    	model.setVisualizacao("A");
    	model.setSituacaoEnvioRetorno("A");
    	model.setAcao(EXIBIRDADOSCOMPLEMENTARES);
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

	public String gerarRelatorio() {
		LOGGER.error("XXXXXX="+model.toString());
		String acao = this.model.getAcao();
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
		
		if (BaseUtil.isNZB(model.getAcao())) {
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

			Map<String, Object> params = prepararParametrosComunsPdf();
			params.put(VISUALIZACAO, "Analítio");
			params.put(SITUACAO, filtro.getSituacaoManutencao() );

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
			Map<String, Object> params = prepararParametrosComunsPdf();
    		params.put(VISUALIZACAO, "Analítio");
    		params.put(SITUACAO, filtro.getSituacaoManutencao());

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

    		Map<String, Object> params = prepararParametrosComunsPdf();
    		
    		String situacao = "";//RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(c.getSituacaoManutencao());

    		if (BaseUtil.isNZB(situacao)) {
    			situacao = "Manutenções";
    		}

    		StringBuilder sb = new StringBuilder().append("DEPOSITOS IDENTIFICADOS - ").append(situacao)
    				.append(" - ").append("Sintético");
    		params.put(HEADER, sb.toString());

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
    		List<RelatorioEnvioRetornoVO> colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoAnalitico(filtro);

    		if (BaseUtil.isNZB(colRelatorio)) {
    			addActionError(getText(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO));

    			exibirEnvioRetornoAnalitico();
    			return INPUT;
    		}

    		Map<String, Object> params = prepararParametrosComunsPdf();

    		gerarPdf( "relEnvioRetornoBancoAnalitico.jasper", params,  colRelatorio);
    		
    	} catch (Exception e) {
    		LOGGER.error("Erro ao gerar relat\u00f3rio", e);
    		throw new DEPIIntegrationException(ConstantesDEPI.ERRO_GENERICO, e.getMessage());
    	}
    	return "relEnvioRetornoBancoAnalitico.pdf";// exibirEnvioRetornoAnalitico();
    }

	public String consultarEnvioRetornoSintetico() throws DEPIIntegrationException {

    	try {

    		List<RelatorioEnvioRetornoVO> colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoSintetico(filtro);

    		if (BaseUtil.isNZB(colRelatorio)) {
    			addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
    			
    			exibirEnvioRetornoSintetico();
    			return INPUT;
    		}

    		Map<String, Object> params = prepararParametrosComunsPdf();

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

			Map<String, Object> params = prepararParametrosComunsPdf();
    		params.put(VISUALIZACAO, "Analítio");
    		params.put(SITUACAO, "TODOS");

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

    			Map<String, Object> params = prepararParametrosComunsPdf();
    			params.put(VISUALIZACAO, "Sintético");
    			params.put(SITUACAO, "TODOS");

    			gerarPdf( "relExtratoSintetico.jasper", params, dados);

    		}
    	} catch (DEPIIntegrationException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	}

    	return "relExtratoSintetico.pdf";//       
    }

    private Map<String, Object> prepararParametrosComunsPdf() {
		Map<String, Object> params = new HashMap<String, Object>();
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

    	//request.setAttribute("motivos", new ArrayList<MotivoDepositoVO>());
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
    		this.fileInputStream = is;

    	} catch (JRException e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
    	} catch (Exception e) {
    		LOGGER.error(e.getMessage());
    		throw new DEPIIntegrationException(e.getMessage());
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
