<%@ page contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
function loadPage(){
   ajustarPagina();
}

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
	var acao = document.getElementById("acaoOriginal").value;//document.forms[0].acaoOriginal.value;     
	submitForm(acao, undefined, '');
	return true;
}
function limpar() {
   var acaoOriginal = document.getElementById("acaoOriginal").value;// document.forms[0].acaoOriginal.value;
   var acao = document.getElementById("acaoOriginal").value;// document.forms[0].acaoOriginal.value;
   //alert("acao:"+acao +"  -   acaoOriginal:"+acaoOriginal);
   //document.location.href = '<c:out value="${pageContext.request.contextPath}" />/relatorio/ConsultarRelatorio.do?acao='+acaoOriginal;
   //submitForm2(undefined,  "/DEP-DepositoIdentificado/relatorio/consultarRelatorio.do?acao="+acaoOriginal);
    limparCampos();
	limpaTela(acaoOriginal);
	
	document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;
	
	
	ajustarPagina();
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


//===========================Obter dados form=================================================// 
function getTipoRelatorioVal() {
   var value = '';
   var tipoRelatorio = document.forms[0].elements['tipoRelatorio'];
   
   for (var i = 0; i < tipoRelatorio.length; i++) {
     if (tipoRelatorio[i].checked ){
        value = tipoRelatorio[i].value;
     }
   }
   return value;
}


function getVisualizacaoVal(){
   var visualizacao = document.forms[0].elements['visualizacao'];
   var value = '';
   for (var i = 0; i < visualizacao.length; i++) {
     if (visualizacao[i].checked ){
        value = visualizacao[i].value;
     }
   }
   return value;
}

function getSituacaoEnvioRetornoVal() {
   var situacaoEnvioRetorno = document.forms[0].elements['situacaoEnvioRetorno'];

   var value = '';
   for (var i = 0; i < situacaoEnvioRetorno.length; i++) {
     if (situacaoEnvioRetorno[i].checked ){
        value = situacaoEnvioRetorno[i].value;
     }
   }
   return value;
}

function getDepositoVal() {
   var deposito = document.forms[0].elements['deposito'];
   
   var value = '';
   for (var i = 0; i < deposito.length; i++) {
     if (deposito[i].checked ){
        value = deposito[i].value;
     }
   }
   return value;
}


function getSituacaoManutencoesVal() {
   var situacaoManutencoes = document.forms[0].elements['situacaoManutencoes'];
   
   var value = '';
   for (var i = 0; i < situacaoManutencoes.length; i++) {
     if (situacaoManutencoes[i].checked ){
        value = situacaoManutencoes[i].value;
     }
   }
   return value;
}

function getCodigoCompanhiaVal(){
    var option ='0';
    var select = document.getElementById('listaCompanhia');
    option = select.options[select.selectedIndex];
	return option.value;
}

function getCodigoDepartamentoVal(){
    var option ='0';
    var select = document.getElementById('listaDepartamentos');
    option = select.options[select.selectedIndex];
	return option.value;
}

function getCodigoMotivoDepositoVal(){
    var option ='0';
    var select = document.getElementById('listaMotivosDepositos');
    option = select.options[select.selectedIndex];
	return option.value; 
}

function getDepositoVal(){
   var deposito = document.getElementsByName('deposito');
   var value = '0';
   for (var i = 0; i < deposito.length; i++) {
     if (deposito[i].checked ){
        value = deposito[i].value;
     }
   }
   return value; 
}

function getSucursalVal(){
    var text ='';
    var sucursal = document.getElementById('sucursal');
    if(sucursal.value){
    	text = sucursal.value;
    }
 	return text; 
}

function getApoliceVal(){
 	var text ='';
 	var apolice = document.getElementById('apolice');
    if(apolice.value){
    	text = apolice.value;
    }
 	return text; 
}

function getEndossoVal(){
	var text = '';
 	var endosso = document.getElementById('endosso');
    if(endosso.value){
    	text = endosso.value;
    }
 	return text;
}

function getCodigoAutorizadorVal(){
	var text = '';
	var codigoAutorizador = document.getElementById('codigoAutorizador');
    if(codigoAutorizador.value){
    	text = codigoAutorizador.value;
    }
 	return text;
}

function getCpfCnpjVal(){
	var text = '';
  	var cpfCnpj = document.getElementsByName('cpfCnpj');
    if(cpfCnpj.value){
    	text = cpfCnpj.value;
    }
    if(!text){
        var cpfCnpj = document.getElementsByName('hdd_cpfCnpj');
        if(cpfCnpj.value){        
    	    text = cpfCnpj.value;
    	}
    }
    
    
 	return text;
}

function getDataInicialVal(){
  	var text = '';
  	var dataInicial = document.getElementsByName('dataInicial');
    if(dataInicial.text){
    	text = dataInicial.text;
    }
 	return text;
}

function getDataFinalVal(){
    var text = '';
  	var dataFinal = document.getElementsByName('dataFinal');
    if(dataFinal.value){
    	text = dataFinal.value;
    }
 	return text;
}

function getValorInicialVal(){
  	var text = '';
  	var valorInicial = document.getElementsByName('valorInicial');
    if(valorInicial.text){
    	text = valorInicial.text;
    }
 	return text;
}

function getValorFinalVal(){
    var text = '';
  	var valorFinal = document.getElementsByName('valorFinal');
    if(valorFinal.text){
    	text = valorFinal.text;
    }
 	return text;
}

function getDescricaoDetalhadaVal(){
   return '';
}

function getCodigoContaCorrenteVal(){
  return '';
}



function ObtemDadosForm(){
 	var acao = document.forms[0].acaoOriginal.value;
 	
 	
 	document.getElementById('acaoFrm').value = acao;
    
    document.getElementById('tipoRelatorioFrm').value = getTipoRelatorioVal();
   
    document.getElementById('visualizacaoFrm').value = getVisualizacaoVal();
     
    document.getElementById('codigoCompanhiaFrm').value = getCodigoCompanhiaVal();
    document.getElementById('codigoDepartamentoFrm').value = getCodigoDepartamentoVal();
    document.getElementById('codigoMotivoDepositoFrm').value = getCodigoMotivoDepositoVal();

   
    document.getElementById('depositoFrm').value = getDepositoVal();
    document.getElementById('situacaoEnvioRetornoFrm').value = getSituacaoEnvioRetornoVal();
    document.getElementById('situacaoManutencoesFrm').value = getSituacaoManutencoesVal();

    document.getElementById('sucursalFrm').value = getSucursalVal();
    document.getElementById('apoliceFrm').value = getApoliceVal();
    document.getElementById('endossoFrm').value = getEndossoVal();
    document.getElementById('codigoAutorizadorFrm').value = getCodigoAutorizadorVal();
    document.getElementById('cpfCnpjFrm').value = getCpfCnpjVal();
    document.getElementById('codigoContaCorrenteFrm').value = getCodigoContaCorrenteVal();
   
//    document.getElementById('dataInicialFrm').value = getDataInicialVal();
//    document.getElementById('dataFinalFrm').value = getDataFinalVal();
    document.getElementById('valorInicialFrm').value = getValorInicialVal();
    document.getElementById('valorFinalFrm').value = getValorFinalVal();
   // document.getElementById('descricaoDetalhadaFrm').value = getDescricaoDetalhadaVal;
   
}

function gerarRelatorio(){
    // ObtemDadosForm();
	var acao = document.forms[0].acaoOriginal.value;	
	//document.location.href = document.forms[0].action + '?acao=' + acao;

    //ObtemDadosForm();
    document.getElementById('formConsultarRelatorio').submit();
}


function voltar(){
	document.location.href = '/DEPI-DepositoIdentificado/index.do';
	// window.location.href('/DEPI-DepositoIdentificado/index.do');
}

//=================================================================================================================


function limparCampos(){

    document.getElementById('acaoFrm').value = document.forms[0].acaoOriginal.value;
    document.getElementById('codigoCompanhiaFrm').value       = '0';
    document.getElementById('codigoDepartamentoFrm').value    = '0'; 
    document.getElementById('codigoMotivoDepositoFrm').value  = '0'; 

    document.getElementById('sucursalFrm').value = '';
    document.getElementById('apoliceFrm').value = '';
    document.getElementById('endossoFrm').value = '';
    document.getElementById('codigoAutorizadorFrm').value = '';
    document.getElementById('cpfCnpjFrm').value = '';
    document.getElementById('codigoContaCorrenteFrm').value = '';
    document.getElementById('dataInicialFrm').value = '';
    document.getElementById('dataFinalFrm').value = '';
    document.getElementById('valorInicialFrm').value = '';
    document.getElementById('valorFinalFrm').value = '';
    document.getElementById('descricaoDetalhadaFrm').value = '';
     
    document.getElementById('acao').value = document.forms[0].acaoOriginal.value;
    document.getElementById('listaCompanhia').selectedIndex = 0;
    document.getElementById('listaDepartamentos').selectedIndex = 0;
    document.getElementById('listaMotivosDepositos').selectedIndex = 0;
    document.getElementById('sucursal').value = '';
    document.getElementById('apolice').value = '';
    document.getElementById('endosso').value = '';
    document.getElementById('codigoAutorizador').value = '';
    document.getElementById('cpfCnpj').value = '';
    //document.getElementById('codigoContaCorrente').value = '';
    document.getElementById('dataInicial').value = '';
    document.getElementById('dataFinal').value = '';
    document.getElementById('valorInicial').value = '';
    document.getElementById('valorFinal').value = '';
    //document.getElementById('descricaoDetalhada').value = ''; 

}






function limpaTela(acao){

		if (acao=="exibirEnvioRetornoAnalitico"){
			exibirEnvioRetornoAnalitico(acao);			
		}		
		if (acao=="exibirEnvioRetornoSintetico"){
		  	exibirEnvioRetornoSintetico(acao);			
		}
		if (acao=="exibirExtratoAnalitico"){
			exibirExtratoAnalitico(acao);		
		}
		if (acao=="exibirExtratoSintetico"){
			exibirExtratoSintetico(acao);		
		}		
		if (acao=="exibirManutencoesAnalitico"){
		    exibirManutencoesAnalitico(acao);			
		}		
		if (acao=="exibirManutencoesSintetico"){
			exibirManutencoesSintetico(acao);			
		}					
		if (acao=="exibirDadosComplementares"){
			exibirDadosComplementares(acao);			
		}					
		ajustarPagina();	
}

function setTipoRelatorio(tp){
    var tipoRelatorio = document.forms[0].elements['tipoRelatorio'];
	for (var i = 0; i < tipoRelatorio.length; i++) {
		if (tipoRelatorio[i].value == tp) {
		   tipoRelatorio[i].checked = true;
		}		
	}	
}

function setVisualizacao(tp){
	var visualizacao = document.forms[0].elements['visualizacao'];
	for (var i = 0; i < visualizacao.length; i++) {
		if (visualizacao[i].value == tp) {
		   visualizacao[i].checked = true;
		}		
	}	
}

function setSituacaoManutencoes(tp){
	var situacaoManutencoes = document.forms[0].elements['situacaoManutencoes'];
	for (var i = 0; i < situacaoManutencoes.length; i++) {
		if (situacaoManutencoes[i].id == tp) {
		   situacaoManutencoes[i].checked = true;
		}		
	}	
}

function setSituacaoEnvioRetorno(tp){
	var situacaoEnvioRetorno = document.forms[0].elements['situacaoEnvioRetorno'];
	for (var i = 0; i < situacaoEnvioRetorno.length; i++) {
		if (situacaoEnvioRetorno[i].id == tp) {
		   situacaoEnvioRetorno[i].checked = true;
		}		
	}	
}

function setSubtitulo(subtitulo){
	document.getElementById('subtitulo').innerHTML = subtitulo;
}


function setTituloTabela(titulo){
	document.getElementById('titulo_tabela').innerHTML = titulo;
}


function  exibirEnvioRetornoAnalitico(acao) {
    //alert(acao);

    var subtitulo = "Envio/Retorno Banco - Analítico";
    var titulo    = "Dados de Envio/Retorno Banco - Analítico";
	setTipoRelatorio("ER");
	setVisualizacao("A");
	setSituacaoEnvioRetorno("");
	setSituacaoManutencoes("");
	
	            
	carregarComboCompanhia();
	carregarComboDepartamentos();
	carregarComboMotivos();
	document.getElementById('subtitulo').innerHTML = subtitulo;
	document.getElementById('titulo_tabela').innerHTML = titulo;
	document.forms[0].acao.value = acao;
	document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;
            
	       
}
	
function  exibirEnvioRetornoSintetico(acao) {
    document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;

    setSubtitulo("Envio/Retorno Banco - Sintético");
	setTituloTabela("Dados de Envio/Retorno Banco - Sintético");
    setTipoRelatorio("ER");
    setVisualizacao("S");
	carregarComboCompanhia();
	carregarComboDepartamentos();
	carregarComboMotivos();
}

function  exibirExtratoAnalitico(acao) {

      setSubtitulo("Extrato Banco - Analítico");
      setTituloTabela("Dados de Extrato Banco - Analítico");
      setTipoRelatorio("EX");
      setVisualizacao("A");
      carregarComboCompanhia();
	  carregarComboDepartamentos();
	  carregarComboMotivos();

    document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;

  }

function  exibirExtratoSintetico(acao) {

	  setSubtitulo("Extrato BancoVO - Sintético");
	  setTituloTabela("Dados de Extrato Banco - Sintético");
	  setTipoRelatorio("EX");
	  setVisualizacao("S");
      carregarComboCompanhia();
	  carregarComboDepartamentos();
	  carregarComboMotivos();

    document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;

}
	  
function  exibirManutencoesAnalitico(acao) {

	  setSubtitulo("Manutenções - Analítico");
	  setTituloTabela("Dados de Manutenções - Analítico");
	  setTipoRelatorio("MN");
	  setVisualizacao("A");
	  setAcaoAnterior("exibirManutencoesAnalitico");
      carregarComboCompanhia();
	  carregarComboDepartamentos();
	  carregarComboMotivos();

    document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;
	
}

 function  exibirManutencoesSintetico(acao) {

	  setSubtitulo("Manutenções - Sintético");
	  setTituloTabela("Dados de Manutenções - Sintético");
	  setTipoRelatorio("MN");
	  setVisualizacao("S");
      carregarComboCompanhia();
	  carregarComboDepartamentos();
	  carregarComboMotivos();

    document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;

}
	  
function  exibirDadosComplementares(acao) {

	setSubtitulo("Dados Complementares - Analítico");
	setTituloTabela("Dados Complementares - Analítico");
	setTipoRelatorio("DC");
	setVisualizacao("A");
	setSituacaoEnvioRetorno("A");
    carregarComboCompanhia();
	carregarComboDepartamentos();
	carregarComboMotivos();
    document.forms[0].acao.value = acao;
	document.getElementById("acaoOriginal").value = acao;

}	  

function carregarComboCompanhia(){
}
function carregarComboDepartamentos(){
}

function carregarComboMotivos(){

}



</script>
