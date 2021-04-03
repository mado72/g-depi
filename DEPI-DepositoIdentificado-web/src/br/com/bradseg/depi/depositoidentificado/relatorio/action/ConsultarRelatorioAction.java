package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.jasper.JasperException;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;


import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.bsad.framework.web.struts.support.BsadExceptionInterceptor;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseAction;
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
@Scope("request")
public class ConsultarRelatorioAction extends BaseAction  {


	private static final long serialVersionUID = 3407809247264650489L;
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConsultarRelatorioAction.class); 

	private static final String DATA_FINAL = "Data Final";
	private static final String DATA_INICIAL = "Data Inicial";
	private static final String FORWARD_CONSULTAR = "consultar.relatorio";
	// private static final String FORWARD_APRESENTAR = "apresentar.relatorio";
	private static final String FORWARD_MENU = "dp06.menu";
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
	
	private static final String JRXML = ".jrxml";
	private static final String JASPER = ".jasper";
	private static final String PDF = ".pdf";
	
	
	private static final String EXIBIRENVIORETORNOANALITICO = "exibirEnvioRetornoAnalitico";
	private static final String EXIBIRENVIORETORNOSINTETICO = "exibirEnvioRetornoSintetico";
	private static final String EXIBIREXTRATOANALITICO      = "exibirExtratoAnalitico";
	private static final String EXIBIREXTRATOSINTETICO      = "exibirExtratoSintetico";
	private static final String EXIBIRMANUTENCOESANALITICO  = "exibirManutencoesAnalitico";
	private static final String EXIBIRMANUTENCOESSINTETICO  = "exibirManutencoesSintetico";
	private static final String EXIBIRDADOSCOMPLEMENTARES   = "exibirDadosComplementares";
	private static final String SUCESS = null;
	
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
	
	private String fileNameReport ;
	private String contentTypeReport = "application/pdf"  ;
	private transient InputStream fileInputStream;
	
	private String acao; 
	private String acaoAnterior;
	private String tpcCias = "TRUE";
	private String tpcCiasOrdenadas = "TRUE";
	private String subtituloTela;
	private String tituloTabela;
	
	
    private String tipoRelatorio;
    private String visualizacao;
    private int deposito;
    private String situacaoEnvioRetorno;
    private String situacaoManutencoes;
    private String sucursal;
    private String apolice;
    private String endosso;
    private String codigoAutorizador;
    private String cpfCnpj;
    private String codigoContaCorrente;
    private String dataInicial;
    private String dataFinal;
    private String valorInicial;
    private String valorFinal;
    private String descricaoDetalhada;
    private String subtitulo;
    private FiltroVO filtroVO;
	
	@Autowired
	private ConsultarRelatorioFacade consultarRelatorioFacade;
    
	
	public String consultar(){
   	  
		
	    //List<CompanhiaSeguradoraVO> listaRetorno = //  CompanhiaSeguradoraBusinessDelegate.getInstance().obterComRestricaoDeDeposito(getUsuarioLogado());
		
		
		carregarComboCompanhia();
		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();
		
		CompanhiaSeguradoraVO cia  = new CompanhiaSeguradoraVO();
		cia.setCodigoCompanhia(0);
		cia.setDescricaoCompanhia("Todos");
		listaCompanhia.add(cia);
		cia.setCodigoCompanhia(686);
		cia.setDescricaoCompanhia("Bardesco Seguros");
		listaCompanhia.add(cia);
		tpcCiasOrdenadas = "FALSE";
		
		
		DepartamentoVO departamento = new DepartamentoVO();
		departamento.setCodigoDepartamento(1);
		departamento.setNomeDepartamento("Juridico");
		listaDepartamentos.add(departamento);
		
		MotivoDepositoVO motivoDeposito = new MotivoDepositoVO();
		motivoDeposito.setCodigoMotivoDeposito(1);
		motivoDeposito.setDescricaoBasica("Motivo1");	
		
		listaMotivosDepositos.add(motivoDeposito);
		if (acao.equals(EXIBIRENVIORETORNOANALITICO)){
			exibirEnvioRetornoAnalitico();			
		}		
		if (acao.equals(EXIBIRENVIORETORNOSINTETICO)){
		  	exibirEnvioRetornoSintetico();			
		}
		if (acao.equals(EXIBIREXTRATOANALITICO)){
			exibirExtratoAnalitico();		
		}
		if (acao.equals(EXIBIREXTRATOSINTETICO)){
			exibirExtratoSintetico();		
		}		
		if (acao.equals(EXIBIRMANUTENCOESANALITICO)){
		    exibirManutencoesAnalitico();			
		}		
		if (acao.equals(EXIBIRMANUTENCOESSINTETICO)){
			exibirManutencoesSintetico();			
		}					
		if (acao.equals(EXIBIRDADOSCOMPLEMENTARES)){
			exibirDadosComplementares();			
		}					
		return SUCCESS;	
	}

	public void exibirEnvioRetornoAnalitico() throws DEPIIntegrationException {
	        try {
	            setSubtitulo("Envio/Retorno BancoVO - Analítico");
	            setTituloTabela("Dados de Envio/Retorno BancoVO - Analítico");
	            setTipoRelatorio("ER");
	            setVisualizacao("A");
	            carregarComboCompanhia();
	            carregarComboDepartamentos();
	            carregarComboMotivos();
//	    		listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
//	    		listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

	        } catch (DEPIIntegrationException e) {
	            LOGGER.error(e.getMessage());
	            //tratarExcecao(e);
	        }
	       
	}
	
    public void exibirEnvioRetornoSintetico() throws DEPIIntegrationException {

            try {
                setSubtitulo("Envio/Retorno BancoVO - Sintético");
                setTituloTabela("Dados de Envio/Retorno BancoVO - Sintético");
                setTipoRelatorio("ER");
                setVisualizacao("S");
	    		carregarComboCompanhia();
	    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();
             } catch (DEPIIntegrationException e) {
            	LOGGER.error(e.getMessage());
            	throw new DEPIIntegrationException(e.getMessage());
            }
            
        }

    public void exibirExtratoAnalitico() throws DEPIIntegrationException {

            try {

                setSubtitulo("Extrato BancoVO - Analítico");
                setTituloTabela("Dados de Extrato BancoVO - Analítico");
                setTipoRelatorio("EX");
                setVisualizacao("A");
	    		carregarComboCompanhia();
	    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

            } catch (DEPIIntegrationException e) {
            	LOGGER.error(e.getMessage());
            	throw new DEPIIntegrationException(e.getMessage());
            }
        }

    
	  public void exibirExtratoSintetico() throws DEPIIntegrationException {

		        try {
		            setSubtitulo("Extrato BancoVO - Sintético");
		            setTituloTabela("Dados de Extrato BancoVO - Sintético");
		            setTipoRelatorio("EX");
		            setVisualizacao("S");
		    		carregarComboCompanhia();
		    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
		    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

		        
		        } catch (DEPIIntegrationException e) {
	            	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }

		    }
	  
	  public void exibirManutencoesAnalitico() throws DEPIIntegrationException {
		    try {
		    	
		        setSubtitulo("Manutenções - Analítico");
		        setTituloTabela("Dados de Manutenções - Analítico");
		        setTipoRelatorio("MN");
		        setVisualizacao("A");
		        setAcaoAnterior("exibirManutencoesAnalitico");
				carregarComboCompanhia();
	    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();
		    
		        } catch (DEPIIntegrationException e) {
	            	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }

		       
	  }

	  public void exibirManutencoesSintetico() throws DEPIIntegrationException {
	        try {

		        setSubtitulo("Manutenções - Sintético");
		        setTituloTabela("Dados de Manutenções - Sintético");
		        setTipoRelatorio("MN");
		        setVisualizacao("S");

		        carregarComboCompanhia();
  				//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
  				//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

		        } catch (DEPIIntegrationException e) {
		        	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }
		
	  }
	  
	  public void exibirDadosComplementares() throws DEPIIntegrationException {
	        try {
	        	setSubtitulo("Dados Complementares - Analítico");
		        setTituloTabela("Dados Complementares - Analítico");
		        setTipoRelatorio("DC");
		        setVisualizacao("A");
		        setSituacaoEnvioRetorno("A");
		        

			  		carregarComboCompanhia();
	  				//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	  				//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

		        } catch (DEPIIntegrationException e) {
		        	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }

	  }	  
	  
	  private FiltroUtil montaFiltro(FiltroUtil filtro){
	         if(filtro == null ){
	        	  filtro = new FiltroUtil(); 
	         }
	    	
	         //
	         filtro.setDataInicio(RelogioUtil.validaData("01/01/2013"));
	    	 //filtro.setDataInicio(BaseUtil.parserStringToDate(filtro.getDataInicio()+ " "+ConstantesDEPI.HORA_INI,ConstantesDEPI.FORMATO_DATO_HORA2));
	    	// LOGGER.error("setDataInicio2");
	    	 filtro.setDataFinal(RelogioUtil.validaData("30/12/2020"));
	    	 //filtro.setDataFinal(BaseUtil.parserStringToDate(filtro.getDataFinal()+ " "+ConstantesDEPI.HORA_FIM,ConstantesDEPI.FORMATO_DATO_HORA2));

	    	 
			 
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
		    	/*
				
		        if (!BaseUtil.isNZB(filtroVO.getCodigoMotivoDeposito())) {
		            filtro.setCodigoMotivo(filtroVO.getCodigoMotivoDeposito());
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

		        filtro.setTipoDeposito(filtroVO.getDeposito());

		        if (!BaseUtil.isNZB(filtroVO.getCpfCnpj())) {
		            filtro.setCpfCnpj(BaseUtil.retiraMascaraCNPJ(filtroVO.getCpfCnpj()));
		        }
		    
             if (!BaseUtil.isNZB(filtroVO.getDataFinal())) {
                 filtro.setDataFinal(BaseUtil.parserStringToDate(filtroVO.getDataFinal().concat(" ").concat(ConstantesDEPI.HORA_FIM),
                 		ConstantesDEPI.FORMATO_DATO_HORA2));
             }

             if (!BaseUtil.isNZB(filtroVO.getValorInicial())) {
                 filtro.setValorInicial(new Double(getValorInicial()));
             }

		        if (!BaseUtil.isNZB(filtroVO.getValorFinal())) {
		            filtro.setValorFinal(new Double(filtroVO.getValorFinal()));
		        }
		        
		        if (!BaseUtil.isNZB(filtroVO.getCodigoContaCorrente())) {
		            filtro.setContaCorrente(Integer.valueOf(filtroVO.getCodigoContaCorrente()));
		        }

		        filtro.setSituacaoArquivo(RelatorioEnvioRetornoUtil.obterCodigoSituacaoPorLetra(filtroVO.getSituacaoEnvioRetorno()));
               
		        filtro.setSituacaoManutencao(filtroVO.getSituacaoManutencoes());
				 */			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
	         return filtro;		 
			 
		}	
	  
	public String gerarRelatorio() {
		LOGGER.error("XXXXXX="+filtroVO.toString());
		acao = this.filtroVO.getAcao();
		String retorno = SUCCESS;
		if(acao.equals(EXIBIRENVIORETORNOANALITICO)){
			retorno = this.consultarEnvioRetornoAnalitico();
		}
		if(acao.equals(EXIBIRENVIORETORNOSINTETICO)){
			retorno = this.consultarEnvioRetornoSintetico();
		}
		if(acao.equals(EXIBIREXTRATOANALITICO)){
			retorno = this.consultarExtratoAnalitico();
		}			
		if(acao.equals(EXIBIREXTRATOSINTETICO)){
			retorno = this.consultarEnvioRetornoSintetico();
		}				
		if(acao.equals(EXIBIRMANUTENCOESANALITICO)){
			retorno = this.consultarManutencoesAnalitico();				
		}					     
		if(acao.equals(EXIBIRMANUTENCOESSINTETICO)){
			retorno = this.consultarManutencoesSintetico();
		}					      			     
		if(acao.equals(EXIBIRDADOSCOMPLEMENTARES)){		
			retorno = this.consultarDadosComplementaresAnalitico();
		}					     
		
		return retorno;
	}
	
	/**
     * Processa a ação consultar
     * @param pMapping ActionMapping
     * @param pForm ActionForm
     * @param pRequest HttpServletRequest
     * @param pResponse HttpServletResponse
     * @return ActionForward
     * @throws DEPIIntegrationException Exception
     */
    public String consultarManutencoesAnalitico(){      
    	                 
        //RelatorioForm frm = (RelatorioForm) pForm;

        //GeneralFiltroVO c = montaFiltro(new Object());
        filtro = this.montaFiltro(filtro);
        if (BaseUtil.isNZB(filtro.getDataInicio())) {
            //addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_INICIAL);
            exibirManutencoesAnalitico();
            return ERROR;
        }

        if (BaseUtil.isNZB(filtro.getDataFinal())) {
            //addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
            exibirManutencoesAnalitico();
            return ERROR;
        }
        
        try {
        	 
        	 filtro = montaFiltro(filtro);
        	 
             if (filtro.getDataInicio() == null  ){
 	            addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_INICIAL);
 	            return ERROR;
             }
             if (filtro.getDataFinal() == null  ){
  	            addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
  	            return ERROR;
             }
        	 
            
             List<ManutencoesAnaliticoVO> dados =  consultarRelatorioFacade.obterDadosManutencoesAnalitico(filtro); //RelatorioBusinessDelegate.getInstance().obterDadosManutencoesAnalitico(c);
             if (dados.size() == 0){
    	            addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
    	            return ERROR;
             }
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(VISUALIZACAO, "ANALÍTICO");
            
            params.put( SITUACAO, filtro.getSituacaoManutencao() );
            params.put(DATA_INICIO, filtro.getDataInicio() );
            params.put(DATA_FIM, filtro.getDataFinal() );
            params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
            params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
            params.put(SEQUENCIAL, RelogioUtil.obterSequencial());
            
            gerarPdf("relManutencoesAnalitico.jasper", params, dados);
            
        } catch (IntegrationException e) {
            LOGGER.error(e.getMessage());
            return ERROR;
        }
        return "relManutencoesAnalitico.pdf";//exibirManutencoesAnalitico();//();
    }
	
	
    public String consultarDadosComplementaresAnalitico() throws DEPIIntegrationException {

   	 		filtro = montaFiltro(filtro);
   	 		/*
            RelatorioForm frm = (RelatorioForm) pForm;

            GeneralFiltroVO c = montaFiltro(frm);

            if (BaseUtil.isNZB(filtro.getDataInicio())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_INICIAL);
                return exibirDadosComplementares();
            }

            if (BaseUtil.isNZB(filtro.getDataFinal())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
                return exibirDadosComplementares();
            }
            */
    	
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
                Map<String, Object> params = new HashMap<String, Object>();
                params.put(VISUALIZACAO, "ANALÍTICO");
                params.put(SITUACAO, filtro.getSituacaoManutencao());
                params.put(DATA_INICIO, filtro.getDataInicio());
                params.put(DATA_FIM, filtro.getDataFinal());
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
    		
    		filtro = montaFiltro(filtro);
 
            if (BaseUtil.isNZB(filtro.getDataInicio())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_INICIAL);
                exibirManutencoesSintetico();
                return ERROR;
            }

            if (BaseUtil.isNZB(filtro.getDataFinal())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
                exibirManutencoesSintetico();
                return ERROR;
            }

            try {

                List<ManutencoesSinteticoVO> dados = consultarRelatorioFacade.obterDadosManutencoesSintetico(filtro);
             
                
                if (BaseUtil.isNZB(dados)) {
                    addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                     exibirManutencoesSintetico();
                     return ERROR;
                }

                Map<String, Object> params = new HashMap<String, Object>();
                String situacao = "";//RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(c.getSituacaoManutencao());
                
                if (BaseUtil.isNZB(situacao)) {
                    situacao = "MANUTENÇÕES";
                }
                
                StringBuilder sb = new StringBuilder().append("DEPOSITOS IDENTIFICADOS - ").append(situacao)
                    .append(" - ").append("SINTÉTICO");
                params.put(HEADER, sb.toString());
                params.put(DATA_INICIO, filtro.getDataInicio());
                params.put(DATA_FIM, filtro.getDataFinal());
                
                params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
                params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
                params.put(SEQUENCIAL, RelogioUtil.obterSequencial());
                                        
                gerarPdf("relManutencoesSintetico.jasper", params, dados);
            } catch (DEPIIntegrationException e) {
                LOGGER.error(e.getMessage());
                return ERROR;
            }
            return "relManutencoesSintetico.pdf";// exibirManutencoesSintetico();
        }


    public String consultarEnvioRetornoAnalitico() throws DEPIIntegrationException {

        //RelatorioForm frm = (RelatorioForm) pForm;

        try {
        	filtro = montaFiltro(filtro);

            if (BaseUtil.isNZB(filtro.getDataInicio())) {
            	addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " " + DATA_INICIAL);
                 exibirEnvioRetornoAnalitico();
                 return ERROR;
            }

            if (BaseUtil.isNZB(filtro.getDataFinal())) {
            	addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO  + " " + DATA_FINAL);
                exibirEnvioRetornoAnalitico();
                return ERROR;
            }

            List<RelatorioEnvioRetornoAnaliticoVO> colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoAnalitico(filtro);
            
            for(RelatorioEnvioRetornoAnaliticoVO t: colRelatorio) {
            	LOGGER.error("RelatorioEnvioRetornoAnaliticoVO:"+t.toString());
            }
         	
            
            
            if (BaseUtil.isNZB(colRelatorio)) {
                addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                 
                exibirEnvioRetornoAnalitico();
                return ERROR;
            }
            
            
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(DATA_INICIO,  RelogioUtil.formataDate(filtro.getDataInicio()));
            params.put(DATA_FIM, RelogioUtil.formataDate(filtro.getDataFinal()));

            params.put(DATA_MOVIMENTO, RelogioUtil.obterDataCorrenteFormatada());
            params.put(DATA_HORA, RelogioUtil.obterHoraCorrenteFormatada());
            params.put(SEQUENCIAL, RelogioUtil.obterSequencial());
            
           gerarPdf( "relEnvioRetornoBancoAnalitico.jasper", params,  colRelatorio);

       
        } catch (DEPIIntegrationException e) {
            LOGGER.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
        	LOGGER.error(e.getMessage());
            throw new DEPIIntegrationException(e.getMessage());
        }
        return "relEnvioRetornoBancoAnalitico.pdf";// exibirEnvioRetornoAnalitico();
    }
    public String consultarEnvioRetornoSintetico() throws DEPIIntegrationException {

      		filtro = montaFiltro(filtro);
  
            try {

                if (BaseUtil.isNZB(filtro.getDataInicio())) {
                    addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+DATA_INICIAL);
                    exibirEnvioRetornoSintetico();
                    return ERROR;
                }

                if (BaseUtil.isNZB(filtro.getDataFinal())) {
                    addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
                    exibirEnvioRetornoSintetico();
                    return ERROR;
                }

                List<RelatorioEnvioRetornoSinteticoVO> colRelatorio = new ArrayList<RelatorioEnvioRetornoSinteticoVO>();
                colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoSintetico(filtro);
                
                if (BaseUtil.isNZB(colRelatorio)) {
                    addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                     exibirEnvioRetornoSintetico();
                    return ERROR;
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

    	filtro = montaFiltro(filtro);   
    	List<RelatorioExtratoAnaliticoVO> dados = new ArrayList<RelatorioExtratoAnaliticoVO>();
    	try {

    		if (BaseUtil.isNZB(filtro.getDataInicio())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_INICIAL);
                exibirExtratoAnalitico();
                return ERROR;
            }

            if (BaseUtil.isNZB(filtro.getDataFinal())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
                exibirExtratoAnalitico();
                return ERROR;
                		
            }
            
            List<RelatorioEnvioRetornoAnaliticoVO> dadosER = consultarRelatorioFacade.obterDadosBancoExtratoAnalitico(filtro);              
            if (dadosER == null || dadosER.isEmpty()) {
                addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO); 
                exibirExtratoAnalitico();
                return ERROR;
            }
	
            
            // Adiciona dados obtidos de envio retorno na lista de Extrato após conversão
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

            // // Adiciona dados obtidos de manutenções na lista de Extrato apoós conversão
            for (ManutencoesAnaliticoVO vo : dadosManut) {
                RelatorioExtratoAnaliticoVO extratoVO = new RelatorioExtratoAnaliticoVO();
                copyProperties(extratoVO, vo);
                extratoVO.setNomeGrupo("MANUTENÇÕES");
                extratoVO.setSituacaoManutencao(vo.getCodigoTipoAcao());
                extratoVO.setVencimento(vo.getVencimento());
                extratoVO.setSituacao(RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(vo.getCodigoTipoAcao()));
                dados.add(extratoVO);
            }*/
            
	            consultarRelatorioFacade.obterTotais(dados);
            //List<RelatorioExtratoAnaliticoVO> dados
	            consultarRelatorioFacade.ordenarDadosAnalitico(dados);

	            Map<String, Object> params = new HashMap<String, Object>();
	            params.put(VISUALIZACAO, "ANALÍTICO");
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
         
            if (BaseUtil.isNZB(filtro.getDataInicio())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_INICIAL);
                exibirExtratoSintetico();
                return ERROR;
            }

            if (BaseUtil.isNZB(filtro.getDataFinal())) {
                addActionError(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO +" "+ DATA_FINAL);
                exibirExtratoSintetico();
                return ERROR;
            }
		
            try {
                List<RelatorioExtratoSinteticoVO> dados = consultarRelatorioFacade.obterDadosExtratoSintetico(filtro);
                
                if (BaseUtil.isNZB(dados)) {
                    addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                } else
                	
                		{

                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put(VISUALIZACAO, "SINTÉTICO");
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
    	//request.setAttribute("cias", new ArrayList<CompanhiaSeguradoraVO>());
        //request.setAttribute("ciasOrdenadas", new ArrayList<CompanhiaSeguradoraVO>());
    
    	List<CompanhiaSeguradoraVO> listaRetorno = 
        		consultarRelatorioFacade.carregarComboCompanhiaUsuLogado(getUsuarioLogado());
        List<CompanhiaSeguradoraVO> ciasOrdenadas = new ArrayList<CompanhiaSeguradoraVO>(listaRetorno);
        
        
        //ordenaCompanhia = Collections.sort(ciasOrdenadas);
        
    	tpcCias = "TRUE";
    	tpcCiasOrdenadas = "TRUE";
        
    	//request.setAttribute("cias", listaRetorno);
        //request.setAttribute("ciasOrdenadas", ciasOrdenadas);
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
        public int compare(CompanhiaSeguradoraVO p1, CompanhiaSeguradoraVO p2) {
            return p1.getDescricaoCompanhia().compareTo(p2.getDescricaoCompanhia());
        };
    };   
    
    
    
    
    
    private void carregarComboMotivos() throws DEPIIntegrationException {
        //request.setAttribute("motivos", new ArrayList<MotivoDepositoVO>());
        if (!BaseUtil.isNZB(this.codigoCompanhia) && !BaseUtil.isNZB(this.codigoDepartamento)) {
            List<MotivoDepositoVO> listaRetorno = consultarRelatorioFacade.obterMotivoComRestricaoDeDeposito(
            		this.codigoCompanhia, this.codigoDepartamento, getUsuarioLogado());
            if (!BaseUtil.isNZB(listaRetorno)) {
                Collections.sort(listaRetorno, ordenaMotivoBasico);
                request.setAttribute("motivos", listaRetorno);
                if (!BaseUtil.isNZB(this.codigoMotivoDeposito)) {
                    for (MotivoDepositoVO mot : listaRetorno) {
                        if (mot.equals(new MotivoDepositoVO(this.codigoMotivoDeposito, null, null))) {
                            setDescricaoDetalhada(mot.getDescricaoDetalhada());
                            return;
                        }
                    }
                }
                
                this.listaMotivosDepositos = listaRetorno;
                this.codigoMotivoDeposito =0;
                this.setDescricaoDetalhada("");
            }
        }
    }

    /**
     * Comparador de MotivoDepositoVO onde a ordenação será realizada pela descrição básica do motivo de depósito.
     */
    private static Comparator<MotivoDepositoVO> ordenaMotivoBasico = new Comparator<MotivoDepositoVO>() {
        public int compare(MotivoDepositoVO p1, MotivoDepositoVO p2) {
            return p1.getDescricaoBasica().compareTo(p2.getDescricaoBasica());
        };
    };
    
    private void carregarComboDepartamentos() throws DEPIIntegrationException {
        //request.setAttribute("departamentos", new ArrayList<DepartamentoVO>());
        //request.setAttribute("departamentosOrdenados", new ArrayList<DepartamentoVO>());
    	
    	try {
			
			
	    	if (!BaseUtil.isNZB(this.codigoCompanhia)) {
	            List<DepartamentoVO> listaRetorno = consultarRelatorioFacade.obterComDepositoRestricaoDeDeposito(
	                Integer.valueOf( this.codigoCompanhia) , getUsuarioLogado());
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
     * Comparador de DepartamentoVO onde a ordenação será realizada pelo código do departamento.
     */
    private static Comparator<DepartamentoVO> ordenaDepartamento = new Comparator<DepartamentoVO>() {
        public int compare(DepartamentoVO p1, DepartamentoVO p2) {
            return p1.getSiglaDepartamento().compareTo(p2.getSiglaDepartamento());
        };
    };   
    
 
    /**
     * Retorna valores do form para um nova instância do tipo GeneralFiltroVO
     * @param frm RelatorioForm
     * @return GeneralFiltroVO
     * @throws DEPIIntegrationException - DEPIIntegrationException
     */
    private Object montaFiltro_lod(Object frm) throws IntegrationException {

    	
        //GeneralFiltroVO  filtro = new GeneralFiltroVO();
        /*
        if (!BaseUtil.isNZB(frm.getCodigoCompanhia())) {
            filtro.setCodigoCia(frm.getCodigoCompanhia());
        }

        if (!BaseUtil.isNZB(frm.getCodigoAutorizador())) {
            filtro.setCodigoAutorizador(Long.valueOf(frm.getCodigoAutorizador()));
        }

        if (!BaseUtil.isNZB(frm.getCodigoDepartamento())) {
            filtro.setCodigoDepartamento(frm.getCodigoDepartamento());
        }

        if (!BaseUtil.isNZB(frm.getCodigoMotivoDeposito())) {
            filtro.setCodigoMotivo(frm.getCodigoMotivoDeposito());
        }

        if (!BaseUtil.isNZB(frm.getApolice())) {
            filtro.setApolice(Integer.valueOf(frm.getApolice()));
        }

        if (!BaseUtil.isNZB(frm.getSucursal())) {
            filtro.setSucursal(Integer.valueOf(frm.getSucursal()));
        }

        if (!BaseUtil.isNZB(frm.getEndosso())) {
            filtro.setEndosso(Integer.valueOf(frm.getEndosso()));
        }

        filtro.setTipoDeposito(frm.getDeposito());

        if (!BaseUtil.isNZB(frm.getCpfCnpj())) {
            filtro.setCpfCnpj(Long.valueOf(BaseUtil.retiraMascaraCNPJ(frm.getCpfCnpj())));
        }

        if (!BaseUtil.isNZB(filtro.getDataInicio())) {
            filtro.setDtIni(BaseUtil.parserStringToDate(filtro.getDataInicio().concat(" ").concat(ConstantesModel.HORA_INI),
                ConstantesModel.FORMATO_DATO_HORA2));
        }

        if (!BaseUtil.isNZB(filtro.getDataFinal())) {
            filtro.setDtFim(BaseUtil.parserStringToDate(filtro.getDataFinal().concat(" ").concat(ConstantesModel.HORA_FIM),
                ConstantesModel.FORMATO_DATO_HORA2));
        }

        if (!BaseUtil.isNZB(frm.getValorInicial())) {
            filtro.setValorIni(BaseUtil.stringToBigDecimal(frm.getValorInicial()));
        }

        if (!BaseUtil.isNZB(frm.getValorFinal())) {
            filtro.setValorFim(BaseUtil.stringToBigDecimal(frm.getValorFinal()));
        }
        if (!BaseUtil.isNZB(frm.getCodigoContaCorrente())) {
            filtro.setContaCorrente(Long.valueOf(frm.getCodigoContaCorrente()));
        }

        filtro.setSituacaoArquivo(RelatorioEnvioRetornoUtil.obterCodigoSituacaoPorLetra(frm.getSituacaoEnvioRetorno()));

        filtro.setSituacaoManutencao(frm.getSituacaoManutencoes());
        */
        return filtro;
    }
	
	
	
	
    /**
     * Método utilizado para gerar relatório em PDF.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param nomeRelatorio Nome do Relatorio
     * @param parametros Parametros do Relatorio.
     * @throws IntegrationException - Integração.
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
		} catch (JRException e) {
			// TODO Auto-generated catch block 
			LOGGER.error(e.getMessage());
		}
		InputStream is = new ByteArrayInputStream(bytes);
		LOGGER.error("Proposta Action - gerarCartaInterna - fim");
		ouputStream = is;
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

  /*
   * 
	private InputStream gerarCartaInterna(CartaVO cartaAux) throws JRException  {
		LOGGER.error("Proposta Action - gerarCartaInterna - inicio");
		
		//=>Gera o PDF a partir do relatório JASPER
		String pathRel = request.getSession().getServletContext().getRealPath(PATH_REPORT + cartaAux.getFormulario().getiArqAnexoDocto());
		pathRel = pathRel.replace(PDF, JASPER);

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(beanCartas);  
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("SUBREPORT_DIR",  request.getSession().getServletContext().getRealPath(PATH_REPORT)+File.separator);
		JasperPrint jasperPrint = JasperFillManager.fillReport(pathRel, parametros, ds);  
		byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);  

		InputStream is = new ByteArrayInputStream(pdf);
		LOGGER.error("Proposta Action - gerarCartaInterna - fim");
		return is;					        

	}
   * 
   */
    
    
    
    
    /**
     * Método utilizado para gerar relatório em PDF.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param nomeRelatorio Nome do Relatorio
     * @param parametros Parametros do Relatorio
     * @param dados Collection
     * @throws DEPIIntegrationException - Integração.
     */
    public void gerarPdf( String nomeRelatorio,Map<String, Object> parametros, Collection dados) throws DEPIIntegrationException {
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
   

	
	private InputStream geraEnvioRetornoSintetico()throws JRException  {
		
	   return null;
	}
	
	private InputStream geraExtratoAnalitico()throws JRException  {
		
		return null;
	}
	
	private InputStream geraExtratoSintetico()throws JRException  {
	     return null;	
	}
	
	private InputStream geraManutencoesAnalitico()throws JRException  {
		return null;
	}
	
	private InputStream geraDadosComplementares()throws JRException  {
		return null;
	}


	public String getAcao() {
		return acao;
	}


	public void setAcao(String acao) {
		this.acao = acao;
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

	

	public String getTpcCias() {
		return tpcCias;
	}

	public void setTpcCias(String tpcCias) {
		this.tpcCias = tpcCias;
	}

	public String getTpcCiasOrdenadas() {
		return tpcCiasOrdenadas;
	}

	public void setTpcCiasOrdenadas(String tpcCiasOrdenadas) {
		this.tpcCiasOrdenadas = tpcCiasOrdenadas;
	}


	public String getTituloTabela() {
		return tituloTabela;
	}


	public void setTituloTabela(String tituloTabela) {
		this.tituloTabela = tituloTabela;
	}


	public String getSubtituloTela() {
		return subtituloTela;
	}


	public void setSubtituloTela(String subtituloTela) {
		this.subtituloTela = subtituloTela;
	}




	public String getTipoRelatorio() {
		return tipoRelatorio;
	}




	public void setTipoRelatorio(String tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}




	public String getVisualizacao() {
		return visualizacao;
	}




	public void setVisualizacao(String visualizacao) {
		this.visualizacao = visualizacao;
	}




	public int getDeposito() {
		return deposito;
	}




	public void setDeposito(int deposito) {
		this.deposito = deposito;
	}




	public String getSituacaoEnvioRetorno() {
		return situacaoEnvioRetorno;
	}




	public void setSituacaoEnvioRetorno(String situacaoEnvioRetorno) {
		this.situacaoEnvioRetorno = situacaoEnvioRetorno;
	}




	public String getSituacaoManutencoes() {
		return situacaoManutencoes;
	}




	public void setSituacaoManutencoes(String situacaoManutencoes) {
		this.situacaoManutencoes = situacaoManutencoes;
	}




	public String getSucursal() {
		return sucursal;
	}




	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}




	public String getApolice() {
		return apolice;
	}




	public void setApolice(String apolice) {
		this.apolice = apolice;
	}




	public String getEndosso() {
		return endosso;
	}




	public void setEndosso(String endosso) {
		this.endosso = endosso;
	}




	public String getCodigoAutorizador() {
		return codigoAutorizador;
	}




	public void setCodigoAutorizador(String codigoAutorizador) {
		this.codigoAutorizador = codigoAutorizador;
	}




	public String getCpfCnpj() {
		return cpfCnpj;
	}




	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}




	public String getCodigoContaCorrente() {
		return codigoContaCorrente;
	}




	public void setCodigoContaCorrente(String codigoContaCorrente) {
		this.codigoContaCorrente = codigoContaCorrente;
	}




	public String getDataInicial() {
		return dataInicial;
	}




	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}




	public String getDataFinal() {
		return dataFinal;
	}




	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}




	public String getValorInicial() {
		return valorInicial;
	}




	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}




	public String getValorFinal() {
		return valorFinal;
	}




	public void setValorFinal(String valorFinal) {
		this.valorFinal = valorFinal;
	}




	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}




	public void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public String getAcaoAnterior() {
		return acaoAnterior;
	}

	public void setAcaoAnterior(String acaoAnterior) {
		this.acaoAnterior = acaoAnterior;
	}

	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
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

	public String getFileNameReport() {
		return fileNameReport;
	}

	public void setFileNameReport(String fileNameReport) {
		this.fileNameReport = fileNameReport;
	}

	public String getContentTypeReport() {
		return contentTypeReport;
	}

	public void setContentTypeReport(String contentTypeReport) {
		this.contentTypeReport = contentTypeReport;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
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

		
	
	
}
