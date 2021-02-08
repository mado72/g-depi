package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
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

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.funcao.action.BaseAction;
import br.com.bradseg.depi.depositoidentificado.relatorio.facade.ConsultarRelatorioFacade;
import br.com.bradseg.depi.depositoidentificado.relatorio.util.RelogioUtil;
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
public class ConsultarRelatorioAction extends BaseAction implements Serializable {


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
	private static final String SUCESS = null;
	
    private int codigoCompanhia = 0;
    private int codigoDepartamento = 0;
    private int codigoMotivoDeposito = 0;
	
	//Combos da tela

	private List<CompanhiaSeguradoraVO> listaCompanhia = new ArrayList<CompanhiaSeguradoraVO>();
	private List<CompanhiaSeguradoraVO> ordenaCompanhia  = new ArrayList<CompanhiaSeguradoraVO>();
	
	private List<DepartamentoVO> listaDepartamentos = new ArrayList<DepartamentoVO>();
	private List<MotivoDepositoVO> listaMotivosDepositos = new ArrayList<MotivoDepositoVO>();
	
	
	private FiltroUtil filtro;
	
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
	
	@Autowired
	private ConsultarRelatorioFacade consultarRelatorioFacade;
    
	
	public String consultar(){
   	   // System.out.println("acao "+ this.acao);
		
	    //List<CompanhiaSeguradoraVO> listaRetorno = //  CompanhiaSeguradoraBusinessDelegate.getInstance().obterComRestricaoDeDeposito(getUsuarioLogado());
//		System.out.println("consultar");
		
		//carregarComboCompanhia();
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
	            setSubtitulo("Envio/Retorno BancoVO - Anal�tico");
	            setTituloTabela("Dados de Envio/Retorno BancoVO - Anal�tico");
	            setTipoRelatorio("ER");
	            setVisualizacao("A");
//carregarComboCompanhia();
//	    		listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
//	    		listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

	        } catch (DEPIIntegrationException e) {
	            LOGGER.error(e.getMessage());
	            //tratarExcecao(e);
	        }
	       
	}
	
    public void exibirEnvioRetornoSintetico() throws DEPIIntegrationException {

            try {
                setSubtitulo("Envio/Retorno BancoVO - Sint�tico");
                setTituloTabela("Dados de Envio/Retorno BancoVO - Sint�tico");
                setTipoRelatorio("ER");
                setVisualizacao("S");
	    		//carregarComboCompanhia();
	    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();
             } catch (DEPIIntegrationException e) {
            	LOGGER.error(e.getMessage());
            	throw new DEPIIntegrationException(e.getMessage());
            }
            
        }

    public void exibirExtratoAnalitico() throws DEPIIntegrationException {

            try {

                setSubtitulo("Extrato BancoVO - Anal�tico");
                setTituloTabela("Dados de Extrato BancoVO - Anal�tico");
                setTipoRelatorio("EX");
                setVisualizacao("A");
	    		//carregarComboCompanhia();
	    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

            } catch (DEPIIntegrationException e) {
            	LOGGER.error(e.getMessage());
            	throw new DEPIIntegrationException(e.getMessage());
            }
        }

    
	  public void exibirExtratoSintetico() throws DEPIIntegrationException {

		        try {
		            setSubtitulo("Extrato BancoVO - Sint�tico");
		            setTituloTabela("Dados de Extrato BancoVO - Sint�tico");
		            setTipoRelatorio("EX");
		            setVisualizacao("S");
		    		//carregarComboCompanhia();
		    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
		    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

		        
		        } catch (DEPIIntegrationException e) {
	            	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }

		    }
	  
	  public void exibirManutencoesAnalitico() throws DEPIIntegrationException {
		    try {
		    	
		        setSubtitulo("Manuten��es - Anal�tico");
		        setTituloTabela("Dados de Manuten��es - Anal�tico");
		        setTipoRelatorio("MN");
		        setVisualizacao("A");
		        setAcaoAnterior("exibirManutencoesAnalitico");
				//carregarComboCompanhia();
	    		//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
	    		//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();
		    
		        } catch (DEPIIntegrationException e) {
	            	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }

		       
	  }

	  public void exibirManutencoesSintetico() throws DEPIIntegrationException {
	        try {

		        setSubtitulo("Manuten��es - Sint�tico");
		        setTituloTabela("Dados de Manuten��es - Sint�tico");
		        setTipoRelatorio("MN");
		        setVisualizacao("S");

		        //carregarComboCompanhia();
  				//listaDepartamentos    = consultarRelatorioFacade.carregarComboDepartamentos();
  				//listaMotivosDepositos = consultarRelatorioFacade.carregarComboMotivos();

		        } catch (DEPIIntegrationException e) {
		        	LOGGER.error(e.getMessage());
	            	throw new DEPIIntegrationException(e.getMessage());
		        }
		
	  }
	  
	  public void exibirDadosComplementares() throws DEPIIntegrationException {
	        try {
	        	setSubtitulo("Dados Complementares - Anal�tico");
		        setTituloTabela("Dados Complementares - Anal�tico");
		        setTipoRelatorio("DC");
		        setVisualizacao("A");
		        setSituacaoEnvioRetorno("A");
		        

			  		//carregarComboCompanhia();
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
	    	// System.out.println("setDataInicio");        	 		 
	    	 filtro.setDataInicio(RelogioUtil.validaData("01/01/2018"));
	    	// System.out.println("setDataInicio2");
	    	 filtro.setDataFinal(RelogioUtil.validaData("01/11/2018"));


			 
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

	         return filtro;		 
			 
		}	
	  
	public String gerarRelatorio() {
		System.out.println("gerarRelatorio");
		//acao = EXIBIRENVIORETORNOANALITICO;
		if(acao.equals(EXIBIRENVIORETORNOANALITICO)){
			this.consultarEnvioRetornoAnalitico();
		}
		if(acao.equals(EXIBIRENVIORETORNOSINTETICO)){
			this.consultarEnvioRetornoSintetico();
		}
		if(acao.equals(EXIBIREXTRATOANALITICO)){
			this.consultarExtratoAnalitico();
		}			
		if(acao.equals(EXIBIREXTRATOSINTETICO)){
			this.consultarEnvioRetornoSintetico();
		}				
		if(acao.equals(EXIBIRMANUTENCOESANALITICO)){
			this.consultarManutencoesAnalitico();				
		}					     
		if(acao.equals(EXIBIRMANUTENCOESSINTETICO)){
			this.consultarManutencoesSintetico();
		}					      			     
		if(acao.equals(EXIBIRDADOSCOMPLEMENTARES)){		
			this.consultarDadosComplementaresAnalitico();
		}					     
		
		return SUCCESS;
	}
	
	/**
     * Processa a a��o consultar
     * @param pMapping ActionMapping
     * @param pForm ActionForm
     * @param pRequest HttpServletRequest
     * @param pResponse HttpServletResponse
     * @return ActionForward
     * @throws DEPIIntegrationException Exception
     */
    public String consultarManutencoesAnalitico(){      
//    	System.out.println("consultarManutencoesAnalitico");                   
        //RelatorioForm frm = (RelatorioForm) pForm;

        //GeneralFiltroVO c = montaFiltro(new Object());
        
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
        	 
            // System.out.println("obterDadosManutencoesAnalitico 1");
             List<ManutencoesAnaliticoVO> dados =  consultarRelatorioFacade.obterDadosManutencoesAnalitico(filtro); //RelatorioBusinessDelegate.getInstance().obterDadosManutencoesAnalitico(c);
             if (dados.size() == 0){
    	            addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
    	            return ERROR;
             }
            // System.out.println("obterDadosManutencoesAnalitico 2");
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(VISUALIZACAO, "ANAL�TICO");
            
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
        return SUCESS;//exibirManutencoesAnalitico();//();
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
                
                BigDecimal valorTotalPago = null;
                BigDecimal valorTotalRegistrado = null;
                
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
                params.put(VISUALIZACAO, "ANAL�TICO");
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
                return ERROR;
            }
            return SUCESS;//exibirDadosComplementares();
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
                    situacao = "MANUTEN��ES";
                }
                
                StringBuilder sb = new StringBuilder().append("DEPOSITOS IDENTIFICADOS - ").append(situacao)
                    .append(" - ").append("SINT�TICO");
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
            return SUCESS;// exibirManutencoesSintetico();
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

            List<RelatorioEnvioRetornoAnaliticoVO> colRelatorio = new ArrayList<RelatorioEnvioRetornoAnaliticoVO>();
            colRelatorio = consultarRelatorioFacade.obterDadosEnvioRetornoAnalitico(filtro);
            
            if (BaseUtil.isNZB(colRelatorio)) {
                addActionError(ConstantesDEPI.MSG_CONSULTA_RETORNO_VAZIO);
                 
                exibirEnvioRetornoAnalitico();
                return ERROR;
            }
            
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(DATA_INICIO, filtro.getDataInicio());
            params.put(DATA_FIM, filtro.getDataFinal());

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
        return SUCESS;// exibirEnvioRetornoAnalitico();
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
            return SUCESS;// exibirEnvioRetornoSintetico();
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
	
            
            // Adiciona dados obtidos de envio retorno na lista de Extrato ap�s convers�o
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

            // // Adiciona dados obtidos de manuten��es na lista de Extrato apo�s convers�o
            for (ManutencoesAnaliticoVO vo : dadosManut) {
                RelatorioExtratoAnaliticoVO extratoVO = new RelatorioExtratoAnaliticoVO();
                copyProperties(extratoVO, vo);
                extratoVO.setNomeGrupo("MANUTEN��ES");
                extratoVO.setSituacaoManutencao(vo.getCodigoTipoAcao());
                extratoVO.setVencimento(vo.getVencimento());
                extratoVO.setSituacao(RelatorioManutencoesUtil.getInstance().obterDescricaoSituacao(vo.getCodigoTipoAcao()));
                dados.add(extratoVO);
            }*/
            
	            consultarRelatorioFacade.obterTotais(dados);
            //List<RelatorioExtratoAnaliticoVO> dados
	            consultarRelatorioFacade.ordenarDadosAnalitico(dados);

	            Map<String, Object> params = new HashMap<String, Object>();
	            params.put(VISUALIZACAO, "ANAL�TICO");
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
            
            return SUCESS;// exibirExtratoAnalitico();
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
                    params.put(VISUALIZACAO, "SINT�TICO");
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
    	
        return SUCESS;//       
    }
    
    /**
     * M�todo utilizado para gerar relat�rio em PDF.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param nomeRelatorio Nome do Relatorio
     * @param parametros Parametros do Relatorio.
     * @throws IntegrationException - Integra��o.
     */
    public InputStream gerarPdf(String nomeRelatorio, Map<String, Object> parametros) throws IntegrationException, IOException {
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
			e.printStackTrace();
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
		
		//=>Gera o PDF a partir do relat�rio JASPER
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
     * M�todo utilizado para gerar relat�rio em PDF.
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param nomeRelatorio Nome do Relatorio
     * @param parametros Parametros do Relatorio
     * @param dados Collection
     * @throws DEPIIntegrationException - Integra��o.
     */
    public void gerarPdf( String nomeRelatorio,Map<String, Object> parametros, Collection dados) throws IntegrationException {
//    	System.out.println("gerarPdf 1");  
        try {
            File reportFile = new File(request.getSession().getServletContext().getRealPath(ConstantesDEPI.DIR_RELATORIOS + nomeRelatorio));
            String pathImg = this.getWww3()+ESTRUTURA_PASTA_IMAGENS;
            parametros.put(ConstantesDEPI.PARAM_LOGO, pathImg + ConstantesDEPI.DP06_LOGO_JPG);
           // System.out.println("pathImg:"+pathImg);
           // System.out.println("reportFile.getPath():"+reportFile.getPath());
           // System.out.println("ConstantesDEPI.DP06_LOGO_JPG:"+ ConstantesDEPI.DP06_LOGO_JPG);
           // System.out.println("PARAM_LOGO:"+ConstantesDEPI.PARAM_LOGO);
           // System.out.println("pathImg:"+pathImg);
            byte[] bytes = null;
            bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parametros, new JRBeanCollectionDataSource(dados));

            InputStream is = new ByteArrayInputStream(bytes);
    		LOGGER.error("Proposta Action - gerarCartaInterna - fim");

        } catch (JRException e) {
            LOGGER.error(e.getMessage());
            throw new IntegrationException(e.getMessage());
        } 
       
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

	public List<CompanhiaSeguradoraVO> getOrdenaCompanhia() {
		return ordenaCompanhia;
	}


	public void setOrdenaCompanhia(List<CompanhiaSeguradoraVO> ordenaCompanhia) {
		this.ordenaCompanhia = ordenaCompanhia;
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

		
	
	
}
