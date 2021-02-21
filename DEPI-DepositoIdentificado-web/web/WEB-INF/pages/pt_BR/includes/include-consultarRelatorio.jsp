<%@ page contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
function ajustarPagina() {
	var titulo = ''; var subtitulo = ''; 
	//<s:text name="label.relatorio.consultar.cliente"/>
	var janela = '<s:text name="label.relatorio.consultar.projeto"/>.'; 
	var acao = 'consultar';
	var visualizacao = document.forms[0].elements['visualizacao'];
	var tipoRelatorio = document.forms[0].elements['tipoRelatorio'];
	var situacaoEnvioRetorno = document.forms[0].elements['situacaoEnvioRetorno'];
	var situacaoManutencoes = document.forms[0].elements['situacaoManutencoes'];
	var sucu = document.forms[0].sucursal;
	var apol = document.forms[0].apolice;
	var cpfC = document.forms[0].cpfCnpj;
	var end = document.forms[0].endosso;	
	
	
	for (var i = 0; i < tipoRelatorio.length; i++) {
		if (tipoRelatorio[i].checked && tipoRelatorio[i].value == 'ER') {
			titulo += 'Dados de Envio/Retorno Banco';
			subtitulo += 'Envio/Retorno Banco';
			janela += 'Envio/RetornoBanco'
			acao += 'EnvioRetorno'
			situacaoEnvioRetorno[4].checked = true;
			for (var i = 0; i < situacaoEnvioRetorno.length; i++) 
				situacaoEnvioRetorno[i].disabled = false;
			for (var i = 0; i < situacaoManutencoes.length; i++) 
				situacaoManutencoes[i].disabled = true;
			
			for (var i = 0; i < visualizacao.length; i++) {
				visualizacao[i].disabled = false;
			}	
			apol.disabled = false;
			end.disabled = false;
			cpfC.disabled = false;
			sucu.disabled = false;	
			break;					
		} else if (tipoRelatorio[i].checked && tipoRelatorio[i].value == 'EX') {
			titulo += 'Dados de Extrato Banco';
			subtitulo += 'Extrato Banco';
			janela += 'ExtratoBanco'
			acao += 'Extrato'
			situacaoEnvioRetorno[1].checked = true;
			for (var i = 0; i < situacaoEnvioRetorno.length; i++) {
				situacaoEnvioRetorno[i].disabled = true;			
			}				
			for (var i = 0; i < situacaoManutencoes.length; i++) {
				situacaoManutencoes[i].disabled = true;
			}
			for (var i = 0; i < visualizacao.length; i++) {
				visualizacao[i].disabled = false;
			}	
			apol.disabled = false;
			end.disabled = false;
			cpfC.disabled = false;
			sucu.disabled = false;
			break;				
		} else if (tipoRelatorio[i].checked && tipoRelatorio[i].value == 'MN') {
			titulo += 'Dados de Manutenções';
			subtitulo += 'Manutenções';
			janela += 'Manutenções'
			acao += 'Manutencoes'
			situacaoEnvioRetorno[4].checked = true;
			for (var i = 0; i < situacaoEnvioRetorno.length; i++) 
				situacaoEnvioRetorno[i].disabled = true;
			for (var i = 0; i < situacaoManutencoes.length; i++){ 
				situacaoManutencoes[i].disabled = false;
		    }
		    for (var i = 0; i < visualizacao.length; i++) {
				visualizacao[i].disabled = false;
			}	
			apol.disabled = false;
			end.disabled = false;
			cpfC.disabled = false;
			sucu.disabled = false;
			break;				
		} else if (tipoRelatorio[i].checked && tipoRelatorio[i].value == 'DC') {					
			titulo += 'Dados Complementares';									
			subtitulo += 'Dados Complementares';									
			janela += 'Dados Complementares'
			acao += 'DadosComplementares'						
			situacaoEnvioRetorno[1].checked = true;	
			visualizacao[0].checked = true;						
			for (var i = 0; i < situacaoEnvioRetorno.length; i++) {
				situacaoEnvioRetorno[i].disabled = true;			
			}			
			for (var i = 0; i < situacaoManutencoes.length; i++) {
				situacaoManutencoes[i].disabled = true;		
			}			
			for (var i = 0; i < visualizacao.length; i++) {
				visualizacao[i].disabled = true;
			}	
			
			apol.value = "";			
			end.value = "";
			cpfC.value = "";
			sucu.value = "";	
					
			apol.disabled = true;
			end.disabled = true;
			cpfC.disabled = true;
			sucu.disabled = true;			
			break;				
		}		
	}
	for (var i = 0; i < visualizacao.length; i++) {
		if (visualizacao[i].checked && visualizacao[i].value == 'A') {
			titulo += ' - Analítico';
			subtitulo += ' - Analítico';
			janela += '-Analítico';
			acao += 'Analitico';
			break;
		} else if (visualizacao[i].checked && visualizacao[i].value == 'S') {
			if (subtitulo == 'Manutenções') {
				for (var i = 0; i < situacaoManutencoes.length; i++) 
					situacaoManutencoes[i].disabled = false;
				var situ = situacaoManutencoes[situacaoManutencoes.length - 1];
				situ.checked = true; situ.disabled = false; 
			} else if (subtitulo == 'Envio/Retorno Banco') {
				for (var i = 0; i < situacaoEnvioRetorno.length; i++) 
					situacaoEnvioRetorno[i].disabled = false;
				var situ = situacaoEnvioRetorno[situacaoEnvioRetorno.length - 1];
				situ.checked = true; situ.disabled = false; 
			}
			titulo += ' - Sintético';
			subtitulo += ' - Sintético';
			janela += '-Sintético';
			acao += 'Sintetico';
			break;
		}
	}
	
	
	    Calendar.setup({
        inputField     : "dataInicial",
        ifFormat       : "%d/%m/%Y",
        button         : "inicio",
        align          : "bR",
		showsTime      : true,
        singleClick    : true
    });
    Calendar.setup({
        inputField     : "dataFinal",
        ifFormat       : "%d/%m/%Y",
        button         : "fim",
        align          : "bR",
		showsTime      : true,
        singleClick    : true
    });
	
	
	document.getElementById('subtitulo').innerHTML = subtitulo;
	document.getElementById('titulo_tabela').innerHTML = titulo;
	document.forms[0].acao.value = acao;
	document.title = janela;
	
	
}

function validarCpfCnpj() { 
	
	var campo = document.getElementById("cpfCnpj"); 
	var cpfCnpj = document.getElementById("cpfCnpj").value; 
	
  //não é obrigatório
  if (cpfCnpj == "") 
  { 
    return (true); 
  } 
   cpfCnpj = cpfCnpj.replace('/','');
   cpfCnpj = cpfCnpj.replace('.','');
   cpfCnpj = cpfCnpj.replace('.','');
   cpfCnpj = cpfCnpj.replace('-','');
   
   var msgErro = '<bean:message key="msg.relatorio.erro.cpfCnpj"/>'
  
  if (((cpfCnpj.length == 11) && (cpfCnpj == 11111111111) || (cpfCnpj == 22222222222) || (cpfCnpj == 33333333333) || (cpfCnpj.value == 44444444444) || (cpfCnpj.value == 55555555555) || (cpfCnpj == 66666666666) || (cpfCnpj == 77777777777) || (cpfCnpj == 88888888888) || (cpfCnpj == 99999999999) || (cpfCnpj == 00000000000))) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 


  if (!((cpfCnpj.length == 11) || (cpfCnpj.length == 14))) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 

  var checkOK = "0123456789"; 
  var checkStr = cpfCnpj; 
  var allValid = true; 
  var allNum = ""; 
  for (i = 0;  i < checkStr.length;  i++) 
  { 
    ch = checkStr.charAt(i); 
    for (j = 0;  j < checkOK.length;  j++) 
      if (ch == checkOK.charAt(j)) 
        break; 
    if (j == checkOK.length) 
    { 
      allValid = false; 
      break; 
    } 
    allNum += ch; 
  } 
  if (!allValid) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 

  var chkVal = allNum; 
  var prsVal = parseFloat(allNum); 
  if (chkVal != "" && !(prsVal > "0")) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 

if (cpfCnpj.length == 11) 
{ 
  var tot = 0; 

  for (i = 2;  i <= 10;  i++) 
    tot += i * parseInt(checkStr.charAt(10 - i)); 

  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(9))) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 
  
  tot = 0; 
  
  for (i = 2;  i <= 11;  i++) 
    tot += i * parseInt(checkStr.charAt(11 - i)); 

  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(10))) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 
} 
else 
{ 
  var tot  = 0; 
  var peso = 2; 
  
  for (i = 0;  i <= 11;  i++) 
  { 
    tot += peso * parseInt(checkStr.charAt(11 - i)); 
    peso++; 
    if (peso == 10) 
    { 
        peso = 2; 
    } 
  } 

  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(12))) 
  { 
    alert(msgErro);   
    campo.focus(); 
    return (false); 
  } 
  
  tot  = 0; 
  peso = 2; 
  
  for (i = 0;  i <= 12;  i++) 
  { 
    tot += peso * parseInt(checkStr.charAt(12 - i)); 
    peso++; 
    if (peso == 10) 
    { 
        peso = 2; 
    } 
  } 

  if ((tot * 10 % 11 % 10) != parseInt(checkStr.charAt(13))) 
  { 
    alert(msgErro);  
    campo.focus(); 
    return (false); 
  } 
} 
  return(true); 
} 
function selecionarCombobox(comp, nome) { 
	document.getElementById(nome).value = comp.value; 
	document.getElementById('codigoContaCorrente').value = ''; 
	document.getElementById('codigoMotivo').value = ''; 
	return true;
}
function refresh() {
	var acao = document.forms[0].acaoOriginal.value;
	submitForm(acao, undefined, '');
	return true;
}
function limpar() {
	var acao = document.forms[0].acaoOriginal.value;
	limpaTela(acao);
	//document.location.href = document.forms[0].action + '?acao=' + acao;
	return true;
}
function consultar() {
	return submitForm2(undefined, '<c:out value="${pageContext.request.contextPath}" />/relatorio/ConsultarRelatorio.do');
}
function formatarCPFCNPJ(pCampo, pEvento){
	if(pCampo.value.length < 14){
		return mascararCPF(pCampo, pEvento);
	} else{
		return mascararCNPJ(pCampo, pEvento);
	}
}
function validarPeriodo() { 

	var data1 = document.getElementById("dataInicial").value; 
	var data2 = document.getElementById("dataFinal").value; 
	var msgErro = '<bean:message key="msg.relatorio.erro.periodoAteMenor"/>';
  
	if(data1 != '' && data2 != ''){
		if (parseInt( data2.split( "/" )[2].toString() + data2.split( "/" )[1].toString() + 
		 	data2.split( "/" )[0].toString() ) >= parseInt( data1.split( "/" )[2].toString() + 
		 	data1.split( "/" )[1].toString() + data1.split( "/" )[0].toString())){
			return true;
		}else{
			alert(msgErro);
			document.getElementById("dataFinal").focus();
			return false;
		}
	}
}	

function validarValores() { 

	var v1 = document.getElementById("valorInicial").value; 
	var v2 = document.getElementById("valorFinal").value; 
	
	v1.replace('.','');
	v1.replace(',','.');
	v2.replace('.','');
	v2.replace(',','.');
	
	var f1 = '';
	var f2 = '';
	
	if(v1 == '' && v2 == ''){
		return true;
	}else if(v1 == '' && v2 != ''){
			f2 = parseFloat(v2).toString();
	}else if(v1 != '' && v2 == ''){
			f1 = parseFloat(v1).toString();
	}else{
			f1 = parseFloat(v1).toString();
			f2 = parseFloat(v2).toString();
	}
  
  	var msgErro1 = '<bean:message key="msg.relatorio.erro.valorDe"/>';
  	var msgErro2 = '<bean:message key="msg.relatorio.erro.valorAte"/>';
  	var msgErro3 = msgErro1 + '\n' + msgErro2;
  	var msgErro4 = '<bean:message key="msg.relatorio.erro.valorAteMenor"/>';
  
	if(f1 == 'NaN' && f2 == 'NaN'){
		alert(msgErro3);
		document.getElementById("valorInicial").focus();
		return false;
	}else if(f1 == 'NaN'){
			alert(msgErro1);
			document.getElementById("valorFinal").focus();
			return false;
	}else if(f2 == 'NaN'){
			alert(msgErro2);
			document.getElementById("valorFinal").focus();
			return false;
	}
	
	if(v1 != '' && v2 != ''){
		if(parseFloat(f1) <= parseFloat(f2)){
			return true;
		}else {
			alert(msgErro4);
			document.getElementById("valorFinal").focus();
			return false;
		}
	}
	return true;
}

function gerarRelatorio(){
	var acao = document.forms[0].acaoOriginal.value;	
	document.location.href = document.forms[0].action + '?acao=' + acao;
	//document.getElementById('formConsultarRelatorio').submit();
}


function limpaTela(acao){

		if (acao=="exibirEnvioRetornoAnalitico"){
			exibirEnvioRetornoAnalitico();			
		}		
		if (acao=="exibirEnvioRetornoSintetico"){
		  	exibirEnvioRetornoSintetico();			
		}
		if (acao=="exibirExtratoAnalitico"){
			exibirExtratoAnalitico();		
		}
		if (acao=="exibirExtratoSintetico"){
			exibirExtratoSintetico();		
		}		
		if (acao=="exibirManutencoesAnalitico"){
		    exibirManutencoesAnalitico();			
		}		
		if (acao=="exibirManutencoesSintetico"){
			exibirManutencoesSintetico();			
		}					
		if (acao=="exibirDadosComplementares"){
			exibirDadosComplementares();			
		}					
		
}


function  exibirEnvioRetornoAnalitico() {
/*
	var janela = '<s:text name="label.relatorio.consultar.projeto"/>.'; 

	var acao = 'consultar';
	var visualizacao = document.forms[0].elements['visualizacao'];
	 document.forms[0].elements['tipoRelatorio'];
	var situacaoEnvioRetorno = document.forms[0].elements['situacaoEnvioRetorno'];
	var situacaoManutencoes = document.forms[0].elements['situacaoManutencoes'];
	var sucu = document.forms[0].sucursal;
	var apol = document.forms[0].apolice;
	var cpfC = document.forms[0].cpfCnpj;
	var end = document.forms[0].endosso;	
	
	            var subtitulo = "Envio/Retorno BancoVO - Analítico";
	           	var subtitulo = "Dados de Envio/Retorno BancoVO - Analítico");
	            var tipoRelatorio ="ER";
	            setVisualizacao("A");
	            carregarComboCompanhia();
	            carregarComboDepartamentos();
	            carregarComboMotivos();

*/	       
}
	
function  exibirEnvioRetornoSintetico() {
/*
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
  */          
}

function  exibirExtratoAnalitico() {
/*
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
  */
  }

function  exibirExtratoSintetico() {
     /*
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
*/
}
	  
function  exibirManutencoesAnalitico() {
/*
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

*/		       
	  }

	  function  exibirManutencoesSintetico() {
/*	
	
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
*/	
	  }
	  
	  function  exibirDadosComplementares() {
/*	
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
*/
	  }	  





</script>
