/* 
* Respons�vel pela submiss�o do form se referindo a action correta.
* F�bio Henrique.
*/	
	function executar(acao, submit) {
		var form = document.getElementById('form');
		form.action = acao;
		if(submit) form.submit();
	}
	
//Modifica o tipo da combo "Tipo de Operação" dependendo do campo selecionado na combo "Consultar por".
//Caso o tipo selecionado seja num�rico.

function carregarFiltroCampos(valor) {
	var selectOperadores = document.getElementById("operadores");

	while (selectOperadores.length > 0) {
		selectOperadores.remove(0);
	}
	if(valor == "numerico") {
		selectOperadores.options[0] = new Option("Igual", "numerico");
		selectOperadores.options[1] = new Option("Diferente", "numerico");
		selectOperadores.options[2] = new Option("Menor que", "numerico");
		selectOperadores.options[3] = new Option("Maior que", "numerico");
		selectOperadores.options[4] = new Option("Menor ou igual", "numerico");
		selectOperadores.options[5] = new Option("Maior ou igual", "numerico");
		document.form.textValor.disabled = 0;
		
//Caso seja alfanum�rico.
	} else if (valor == "alfanumerico") {
		selectOperadores.options[0] = new Option("Igual", "numerico");
		selectOperadores.options[1] = new Option("Diferente", "numerico");
		selectOperadores.options[2] = new Option("Cont�m", "numerico");
		selectOperadores.options[3] = new Option("N�o Cont�m", "numerico");
		selectOperadores.options[4] = new Option("Inicia com", "numerico");
		selectOperadores.options[5] = new Option("Termina com", "numerico");
		selectOperadores.options[6] = new Option("Em branco", "numerico");
		document.form.textValor.disabled = 0;

//caso seja deposito
	} else if (valor == "deposito") {
		selectOperadores.options[0] = new Option("Igual a Pr�mio", "deposito");
		selectOperadores.options[1] = new Option("Igual a Diversos", "deposito");
		document.all('textValor').value ="";
		document.form.textValor.disabled = 1;
	}
}

//Adiciona item no filtro
function adicionarItemFiltro() {
	document.forms["formFiltro"]
	
	strFiltro  = document.forms["formFiltro"].elements["listaCampos"].options[document.forms["formFiltro"].elements["listaCampos"].selectedIndex].text;
	strFiltro  = strFiltro + " " + document.forms["formFiltro"].elements["listaOperadores"].options[document.forms["formFiltro"].elements["listaOperadores"].selectedIndex].text;
	strFiltro  = strFiltro + " " + document.forms["formFiltro"].elements["textValor"].value;
		
	var itemFiltro = document.createElement("option");
	itemFiltro.text = strFiltro;
		
	document.forms["formFiltro"].elements["listaFiltros"].add(itemFiltro);
	return true;
}


//Remove um item do filtro
function removerItemFiltro() {
	document.forms["formFiltro"]
	var itemSelecionado = document.forms["formFiltro"].elements["listaFiltros"].selectedIndex;
	if (itemSelecionado >= 0) {
		document.forms["formFiltro"].elements["listaFiltros"].remove(itemSelecionado);
	}
	return true;
}

//op��o de exclus�o
function opcao() {
  confirm ("Confirma Exclus�o?");
}

//checkbox obrigatorio
function verificaBox() {
	//validacao de checkbox sem saber quantos sao
	marcado = -1
	for (i=0; i<document.forms[0].check.length; i++) {
		if (document.forms[0].check[i].checked) {
			marcado = i ;
		}
	}	
	if (marcado == -1) {
		alert("Obrigat�rio a sele��o de um registro.");
		return false;
	} 		
}

//checkbox obrigat�rio para exclus�o
function verificaBoxExclusao(opcao) {
	//validacao de checkbox sem saber quantos sao
	marcado = -1
	for (i=0; i<document.forms[0].check.length; i++) {
		if (document.forms[0].check[i].checked) {
			marcado = i ;
			confirm ("Confirma Exclus�o?");
			break;
		}
	}	
	if (marcado == -1) {
		alert("Pelo menos um registro deve ser selecionado para exclus�o.");
		return false;
	} 		
}

//Permite digita��o somente de n�meros.
function isIE() {

	var isIE;
	if (parseInt(navigator.appVersion.charAt(0)) >= 4) {
	  isIE = (navigator.appName.indexOf("Microsoft") != -1) ? true : false;
	}
	return isIE;
}

//Formata um campo data para o formato XX/XX/XXXX.
function formatarData(input, e){
	var keyNumber = (isIE()) ? e.keyCode : e.which;

	if ((keyNumber<48)||(keyNumber>57)){
		e.returnValue = false; 
	} else { 
	if ((input.value.length==2)||(input.value.length==5))
		input.value=input.value + "/" ;
	}
}

function habilitarRadio() {
	alert('OK');
	document.forms[0].radio4[0].disabled = true;
}

//Habilita e desabilita radioButtons.
function hbRadio() {
  	for(var i=0;i<document.forms[0].elements.length;i++) {
		if(document.forms[0].elements[i].name=="radio") {
     		if(document.forms[0].elements[i].value=="E") {
     		//Quando for Envio/Retorno
       			if(document.forms[0].elements[i].checked == true) {

       				 document.forms[0].rRadio[0].disabled=true;
        			 document.forms[0].rRadio[1].disabled=true;
       				 document.forms[0].rRadio[2].disabled=true;
       				 document.forms[0].rRadio[3].disabled=true;
       				 document.forms[0].rRadio[4].disabled=true;
       				 
       				 document.forms[0].sRadio[0].disabled=false;
        			 document.forms[0].sRadio[1].disabled=false;
       				 document.forms[0].sRadio[2].disabled=false;
       				 document.forms[0].sRadio[3].disabled=false;
       				 document.forms[0].sRadio[4].disabled=false;
       			}
     		} else if(document.forms[0].elements[i].value=="M") {
     		//Quando for Manuten��es
       			if(document.forms[0].elements[i].checked == true) {

       				 document.forms[0].sRadio[0].disabled=true;
        			 document.forms[0].sRadio[1].disabled=true;
       				 document.forms[0].sRadio[2].disabled=true;
       				 document.forms[0].sRadio[3].disabled=true;
       				 document.forms[0].sRadio[4].disabled=true;
       				 
       				 document.forms[0].rRadio[0].disabled=false;
        			 document.forms[0].rRadio[1].disabled=false;
       				 document.forms[0].rRadio[2].disabled=false;
       				 document.forms[0].rRadio[3].disabled=false;
       				 document.forms[0].rRadio[4].disabled=false;
       			}
     		}  else if(document.forms[0].elements[i].value=="EB") {
     		//Quando for Extrato-Banco
       			if(document.forms[0].elements[i].checked == true) {

       				 document.forms[0].sRadio[0].disabled=true;
        			 document.forms[0].sRadio[1].disabled=true;
       				 document.forms[0].sRadio[2].disabled=true;
       				 document.forms[0].sRadio[3].disabled=true;
       				 document.forms[0].sRadio[4].disabled=true;
       				 
       				 document.forms[0].rRadio[0].disabled=true;
        			 document.forms[0].rRadio[1].disabled=true;
       				 document.forms[0].rRadio[2].disabled=true;
       				 document.forms[0].rRadio[3].disabled=true;
       				 document.forms[0].rRadio[4].disabled=true;
       			}
     		} else if(document.forms[0].elements[i].value=="DC") {
     		//Quando for Dados Complementares
       			if(document.forms[0].elements[i].checked == true) {

       				 document.forms[0].sRadio[0].disabled=false;
        			 document.forms[0].sRadio[1].disabled=false;
       				 document.forms[0].sRadio[2].disabled=false;
       				 document.forms[0].sRadio[3].disabled=false;
       				 document.forms[0].sRadio[4].disabled=false;
       				 
       				 document.forms[0].rRadio[0].disabled=true;
        			 document.forms[0].rRadio[1].disabled=true;
       				 document.forms[0].rRadio[2].disabled=true;
       				 document.forms[0].rRadio[3].disabled=true;
       				 document.forms[0].rRadio[4].disabled=true;
       			}
     		}						
    	}	
  	}	
}