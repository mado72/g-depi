//Desabilita context menu do mouse
//Desabilita o menu com clic do bot�o direito do mouse em todas as paginas do sistema
//document.oncontextmenu=function(event) {return false;}

//Desabilita CTRL + V
//document.onkeypress=function(event) {return disableCtrlKeyCombination(event);}
//document.onkeydown=function(event) {return disableCtrlKeyCombination(event);}

function unformatNumber(pNum) {
	return String(pNum).replace(/\D/g, "").replace(/^0+/, "");
} //unformatNumber

//Verifica Data
function checkDate(obj) { 
  var expReg = /^((0[1-9]|[12]\d)\/(0[1-9]|1[0-2])|30\/(0[13-9]|1[0-2])|31\/(0[13578]|1[02]))\/(19|20)?\d{2}$/; 
  var aRet = true;
  if ((obj) && (obj.value.match(expReg)) && (obj.value != '')) { 
        var dia = obj.value.substring(0,2); 
        var mes = obj.value.substring(3,5); 
        var ano = obj.value.substring(6,10); 
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11 ) && dia > 30)  
          aRet = false; 
        else  
          if ((ano % 4) != 0 && mes == 2 && dia > 28)  
                aRet = false; 
          else 
                if ((ano%4) == 0 && mes == 2 && dia > 29) 
                  aRet = false; 
  }  else  
        aRet = false;   
  
  return aRet; 
}

//Método respons�vel por formatar um CNPJ ou CPF.
function formatarCPFCNPJ(pCampo, pEvento){
	if(pCampo.value.length < 14){
		return mascararCPF(pCampo, pEvento);
	} else{
		return mascararCNPJ(pCampo, pEvento);
	}
}
function mascararCPF(pCampo, pEvento){
 	 return  mascararNumeroRL(pCampo, pEvento, 9, 2, '.', '-', 3);	
}

 function mascararCNPJ(pCampo, pEvento){
	
   if (pEvento.which) { //Firefox 
          whichCode = pEvento.which;
   } else { //Internet explorer
          whichCode = pEvento.keyCode;
   }
   if (whichCode == 13 || whichCode == 9 || whichCode == 8) { //Enter ou Tab ou Delete
         return true;
   }
    pEvento.returnValue = false; 

    if (window.event) {
		//pEvento.keyCode = 0; acesso negado
    }
  
  	vr = pCampo.value + String.fromCharCode(whichCode);
    vr = vr.replace(/\D/g, '');
	re = new RegExp(/(\d{0,2})(\d{0,3})(\d{0,3})(\d{0,4})(\d{0,2})/);
	if (vr.length <= 2){
		pCampo.value = vr.replace(re, '$1.');
	}else if (vr.length <= 5){
		pCampo.value = vr.replace(re, '$1.$2.');
	}else if (vr.length <= 8){
		pCampo.value = vr.replace(re, '$1.$2.$3/');
	}else if (vr.length <= 12){
		pCampo.value = vr.replace(re, '$1.$2.$3/$4-');
	}else if (vr.length <= 14){
		pCampo.value = vr.replace(re, '$1.$2.$3/$4-$5');
	}
	return false;
}	

function mascararValor(pCampo, pEvento, pTam){
	if (pCampo != undefined && pCampo.value != undefined) {
		 if(pCampo.value.length < pTam){	
		 	 return mascararNumeroRL(pCampo, pEvento, 8, 2, '.', ',', 3);	
		 }
	}
	return false;
}
/**************************************************************************
Fun��o para simular um Tab quando for pressionado a tecla Enter
Exemplo: onKeyDown="tabEnter();"
Funciona em TEXT BOX,RADIO BUTTON, CHECK BOX e menu DROP-DOWN
**************************************************************************/
function tabEnter(e){
	var oEvent = (window.event)? event : e;
	var oTarget = (oEvent.target)? oEvent.target : oEvent.srcElement;
	if(oTarget.type=="text" && oEvent.keyCode==13) oEvent.keyCode = 9;
	if(oTarget.type=="radio" && oEvent.keyCode==13) oEvent.keyCode = 9;
	if(oEvent.keyCode==13) { 
		oEvent.keyCode = 9;
	}
  	return true;
}

//Usada para repassar o value quando outra de outra combo.
function changeCombo(id, valor){
	document.getElementById(id).value = valor;
}
function exibirImagemNaAcao() {
	try {
		var destino = document.getElementById('box_loading');
		var hdd = document.getElementById('url_servidor_img');
		if (hdd != undefined) {
			var html = "</br><img src='" + hdd.value + "/carregando.gif' />";
			if (destino != undefined) {
				destino.innerHTML += html;
			}
		}	
	} catch (e)	 {
		alert('exibirImagemNaAcao: ' + e.toString());
	}
}
function disableActions(acao) {
	try {
		var as = document.getElementsByTagName('a');
		for (var i = 0; i < as.length; i++) {
			as[i].onclick = function () {return false;};
		}
		var inputs = document.getElementsByTagName('input');
		for (var i = 0; i <inputs.length; i++) {
			inputs[i].readOnly = true;
		}
		var textArea = document.getElementsByTagName('textarea');
		for (var i = 0; i <textArea.length; i++) {
			textArea[i].readOnly = true;
		}
	} catch (e)	 {
		alert('disableActions: ' + e.toString());
	}	
	return true;
}
//Esta sendo utilizada na Display Tag internamente.
//altera par�metro 'acao' da url do link da display tag.
function replaceDisplayTagByName(elements) {
	var el;
	var url;
	var acao = 'acao=consultar';
	if (elements != undefined) {
		for (var i = 0; i < elements.length; i++) {
			el = elements[i];
				url = el.href;
				url = url.replace('acao=alterar', acao);
				url = url.replace('acao=inserir', acao);
				url = url.replace('acao=excluir', acao);
				el.href = url;
		}
	}
}
function removeExcesso(obj, tamanho) {
	var strOriginal = obj.value;
	var strLimitada = obj.value.substring(0, tamanho);
	obj.value = strLimitada;
	return (strOriginal.lenght > strLimitada.lenght); //cancela evento; 
}
function setFocusOnLoad(elementsId) {
	if (elementsId == undefined) {alert('parametro elementsId n�o fornecido.');}
	for (var i = 0; i < elementsId.length; i++) {
		var el = document.getElementById(elementsId[i])
		if (el != undefined) {
			if (el.value == '')	 {
				el.focus();
				break;
				return true;
			}
		}
	}
	return false;
}
//Coloca foco no proximo objeto com valor empty;
function setFocusAll(els) {
	for (var i = 0; i < els.length; i++) {
		var el = els[i];
		if (el.type=="text" || el.type=="textarea" || el.type=="select" || el.type=="select-one" || el.type=="checkbox" || el.type=="radio") {
			if ((el.value != undefined) && (el.value == '')) {
				try {
					el.focus();
					break;
					return true;
				}  catch (e) {
					//O elemento n�o pode receber foco. Ignorado.
				}
			}
		}
	}
	return false;
}
function setFocusOnLoad2(elementId) {
	if (elementId == undefined) {alert('parametro elementId n�o fornecido.');}
	var el = document.getElementById(elementId)
	if (el != undefined) {
		el.focus();
		return true;
	}
	return false;
}

//para IE usar keydown

function disableCtrlKeyCombination(e) {
		
        var key;
        var isCtrl;

        if(window.event)
        {
                key = window.event.keyCode;     //IE
				
                if(window.event.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }
        else
        {
                key = e.which;     //firefox
                if(e.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }

		
        //if ctrl is pressed check if other key is in forbidenKeys array
        if(isCtrl)
        {
		
						//list all CTRL + key combinations you want to disable
						//case-insensitive comparation
                        if("v" == String.fromCharCode(key).toLowerCase())
                        {
                                return false;
                        }
                
        }
        return true;
}

function isCTRLV(e) {
        var key;
        var isCtrl;

        if(window.event)
        {
                key = window.event.keyCode;     //IE
				
                if(window.event.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }
        else
        {
                key = e.which;     //firefox
                if(e.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }

		
        //if ctrl is pressed check if other key is in forbidenKeys array
        if(isCtrl)
        {
			//list all CTRL + key combinations you want to disable
			//case-insensitive comparation
            if("v" == String.fromCharCode(key).toLowerCase())
            {
            	return true;
			}
                
        }
        return false;
}


/**************************************************************************
Fun��o para simular um Tab quando for pressionado a tecla Enter
Exemplo: onKeyDown="tabEnter();"
Funciona em TEXT BOX,RADIO BUTTON, CHECK BOX e menu DROP-DOWN
**************************************************************************/
function tabEnter(e){
  var oEvent = (window.event)? event : e;
  var oTarget =(oEvent.target)? oEvent.target : oEvent.srcElement;
  if(oEvent.keyCode==13)   oEvent.keyCode = 9;
  if(oTarget.type=="text" && oEvent.keyCode==13)    oEvent.keyCode = 9;
  if (oTarget.type=="radio" && oEvent.keyCode==13)    oEvent.keyCode = 9;
    
    return true;
}

//Procura todos as urls da display tag que ser�o alteradas.
function replaceAllDisplayTag() {
	var allEls = new Array('full','first','last','number');
	for (var i = 0; i < allEls.length; i++) {
		replaceDisplayTagByName(document.getElementsByName(allEls[i]));
	}
	replaceDisplayTagByName(document.getElementsByTagName('a')); 
}

//Método respons�vel por mudar o parametro acao, action, target do form e submeter. 
function submitForm(acao, action, target) {
	exibirImagemNaAcao();
	disableActions(acao);
	try {
		if (action != undefined) document.forms[0].action = action;
		if (acao != undefined) document.forms[0].acao.value = acao;
		if (target != undefined) document.forms[0].target = target;
		document.forms[0].submit();
	} catch (e)	 {
		alert('submitForm: ' + e.toString());
	}	
	return true;
}

function submitForm2(acao, action, target) {
	try {
		if (action != undefined) document.forms[0].action = action;
		if (acao != undefined) document.forms[0].acao.value = acao;
		if (target != undefined) document.forms[0].target = target;
		document.forms[0].submit();
	} catch (e)	 {
		alert('submitForm2: ' + e.toString());
	}	
	return true;
}

//Método respons�vel por mudar o parametro acao do form e submeter. 
function setAcao(acao){
	document.forms[0].elements['acao'].value = acao;
	document.forms[0].submit();	
	return true;
}

//checkbox obrigatorio
function verificarBoxEdicao(checkname, msgselect) {
	//validacao de checkbox sem saber quantos sao
	var marcado = 0;
	var checkboxes = document.forms[0].elements[checkname];
	if (checkboxes.length != undefined) {
		for (var i=0; i<checkboxes.length; i++) {
			if (checkboxes[i].checked) {
				marcado++;
			}
		}	
	} else if (checkboxes.checked) {
		marcado++;
	}

	if (marcado == 0 || marcado > 1) {
		alert(msgselect);
		return false;
	}
	return true;
}

//remove espa�os
function trim(str){return str.replace(/^\s+|\s+$/g,"");}

//valida��o de cnpj
function validar_cnpj(obj)
      {
      var cnpj = obj.value.replace(/\D+/g, '');
      var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
      digitos_iguais = 1;
      if (cnpj.length != 14)
            return false;
      for (i = 0; i < cnpj.length - 1; i++)
            if (cnpj.charAt(i) != cnpj.charAt(i + 1))
                  {
                  digitos_iguais = 0;
                  break;
                  }
      if (!digitos_iguais)
            {
            tamanho = cnpj.length - 2
            numeros = cnpj.substring(0,tamanho);
            digitos = cnpj.substring(tamanho);
            soma = 0;
            pos = tamanho - 7;
            for (i = tamanho; i >= 1; i--)
                  {
                  soma += numeros.charAt(tamanho - i) * pos--;
                  if (pos < 2)
                        pos = 9;
                  }
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(0))
                  return false;
            tamanho = tamanho + 1;
            numeros = cnpj.substring(0,tamanho);
            soma = 0;
            pos = tamanho - 7;
            for (i = tamanho; i >= 1; i--)
                  {
                  soma += numeros.charAt(tamanho - i) * pos--;
                  if (pos < 2)
                        pos = 9;
                  }
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(1))
                  return false;
            return true;
            }
      else
            return false;
      } 


//valida��o de cpf
function validar_cpf(obj)
      {
      var numeros, digitos, soma, i, resultado, digitos_iguais;
      var cpf = obj.value.replace(/\D+/g, ''); 
      digitos_iguais = 1;
      if (cpf.length < 11)
            return false;
      for (i = 0; i < cpf.length - 1; i++)
            if (cpf.charAt(i) != cpf.charAt(i + 1))
                  {
                  digitos_iguais = 0;
                  break;
                  }
      if (!digitos_iguais)
            {
            numeros = cpf.substring(0,9);
            digitos = cpf.substring(9);
            soma = 0;
            for (i = 10; i > 1; i--)
                  soma += numeros.charAt(10 - i) * i;
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(0))
                  return false;
            numeros = cpf.substring(0,10);
            soma = 0;
            for (i = 11; i > 1; i--)
                  soma += numeros.charAt(11 - i) * i;
            resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
            if (resultado != digitos.charAt(1))
                  return false;
            return true;
            }
      else
            return false;
      }


function configuraObjetos()  {
	if (document.forms[0] != undefined && document.forms[0].elements != undefined) {
		for (i=0; i< document.forms[0].elements.length; i++) {
			var el = document.forms[0].elements[i];
			//Configura Eventos
			//Se o evento estiver 'livre' ele ser� usado para sempre limpar os zeros, 
		   //caso contr�rio a fun��o deve ser aplicada manualmente.
			if (el.type=="text" || el.type=="textarea" || el.type=="select" || el.type=="select-one" || el.type=="checkbox" || el.type=="radio") {
				var size = 200;
		   		if (el.type=="text") {
					size = el.size;
				} else if (el.type=="textarea") {
					size = 200;
				}
				
				if (size == undefined) {
					size = 20;
				}
				var re1 = new RegExp('');
		   		//onkeypress 
		   		//Substituir (value.replace) e return (cancelamento) depois do onkeypress e antes do onkeyup n�o funciona.
				if (el.type=="text") {		   		
					if (el.onkeypress == undefined) {
						el.onkeypress = function() {return limitarCharReservados(event);};
					} else {
						//Entende o aspecto do controle e define a��es padr�o para os eventos.
						re1 = new RegExp(/formatarCPFCNPJ/);
						if (re1.test(new String(el.onkeypress))) {
							if (el.onkeyup == undefined) {
								el.onkeyup = function () {replaceCharReservados_CNPJ(this);};
							}
						}

						re1 = new RegExp(/mascararValor/);
						if (re1.test(new String(el.onkeypress))) {
							if (el.onkeyup == undefined) {
								el.onkeyup = function () {replaceCharReservados_Monetario(this);};
							}					
						}
						re1 = new RegExp(/permitirApenasInteiros/);
						if (re1.test(new String(el.onkeypress))) {
							if (el.onkeyup == undefined) {
								el.onkeyup = function () {replaceCharReservados_Inteiros(this);};
							}					
						}
						re1 = new RegExp(/formatarData/);
						if (re1.test(new String(el.onkeypress))) {
							if (el.onblur == undefined) {
								el.onblur = function () {if(!checkDate(this) && this.value != '') {alert('Data inv�lida: ' + this.value); this.value = '';}};
						 	}
							if (el.onkeyup == undefined) {
								el.onkeyup = function () {replaceCharReservados_Data(this); formatarData(this, event);};
							}	
						}
					
						//Evita preechimento com valores zerados para datas, moeda etc.
						if (el.onchange == undefined) {
							el.onchange = function () {zeroToEmptySingle(this); replaceCharReservados_Texto(this);};
						}  else {
							if (el.onblur == undefined) {
								el.onblur = function () {zeroToEmptySingle(this); replaceCharReservados_Texto(this);};
							}
						}
					}
					if (el.onkeyup == undefined) {
						el.onkeyup = function () {replaceCharReservados_Texto(this);};
					}
					zeroToEmptySingle(el);
				} else if (el.type=="textarea") {
					if (el.onkeypress == undefined) {
						el.onkeypress = function() {return limitar(event, 200) && limitarCharReservados(event);};
					}
					if (el.onkeyup == undefined) {
						el.onkeyup = function () {replaceCharReservados_Texto(this); excluiExcesso(this, 200);};
					}
					zeroToEmptySingle(el);					
				}
				
				if (el.onkeydown == undefined) {
					el.onkeydown = function () {return tabEnter(event);};
				} 
				
				//Chama rotinas de verifica��o.
				el.tabindex = i + 1;
				
			} else {
				//Se n�o � objeto edit�vel, remova a configura��o de tabindex.
				el.tabindex = -1;
			}
		}
		setFocusAll(document.forms[0].elements);
	}
	return true;
}

function zeroToEmptySingle(el) {
	if (el.ignoreOnZeroToEmptySingle) {
		var valor = el.value; 
		var isSeqZero = 'S';
		for (x=0; x < valor.length; x++) {
			var c = valor.substring(x, x+1);
			if ((c != '0') &&  (c != ',') && (c != '.') && (c != '/') &&  (c != '-')) {
				isSeqZero = 'N';
				break;
			}
		}
		if (isSeqZero == 'S') {
			el.value = '';
		}
	}	
	return true;
}

//checkbox obrigat�rio para exclus�o
function verificarBoxExclusao(checkname, msgconfirm, msgselect) {
	//validacao de checkbox sem saber quantos sao
	var checkboxes = document.forms[0].elements[checkname];
	if (checkboxes.length != undefined) {
		for (i=0; i<checkboxes.length; i++) {
			if (checkboxes[i].checked) return confirm(msgconfirm);
		}	
	} else if (checkboxes.checked) return confirm(msgconfirm);

	alert(msgselect);
	return false;
}


//Método respons�vel desabilitar a tecla enter. 
function desabilitaEnter(event) {
	var ETR = 13;

	var letra = null;
	if (window.event) {
		letra = event.keyCode;
    } else {
    	letra = event.which;
    }    
    if (letra == ETR) {
    	return false;
    }
    return true;
}

//Método respons�vel por redirecionar para uma determinada tela. 
function redirecionar(tela){
	document.location.href = tela;
}

//Formata um campo CPF/CNPJ.
function FormataCNPJ(Campo){
	
	if(Campo.value == "")
	{
	  return false;
	}
	
	
   var vr = new String(Campo.value);
   
   vr = vr.replace(".", "");
   vr = vr.replace(".", "");
   vr = vr.replace("/", "");
   vr = vr.replace("-", "");

   tam = vr.length + 1;

   Campo.value = vr.substr(0, 2) + '.' + vr.substr(2, tam);
   Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '.' + vr.substr(5,tam-5);
   Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '.' + vr.substr(5,3) + '/' + vr.substr(8,tam-8);
   Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '.' + vr.substr(5,3) + '/' + vr.substr(8,4)+ '-' + vr.substr(12,tam-12);      
   return true;
}

//Retira a mascara do CPF/CNPJ.
function retiraMascara(pObjeto,arrSimbolos){
	
	var retMask = new String(pObjeto.value);
   	for(var i = 0; i < arrSimbolos.length; i++){
   		while(retMask.indexOf(arrSimbolos[i]) > -1){
   		 	retMask = retMask.replace(arrSimbolos[i],"");
   		}
   	}
	pObjeto.value =  retMask;
}

//Permite digita��o somente de n�meros.
function permitirApenasInteiros(e) {
	var e = (window.event)? event : e;
	var isIE = (window.event)? true : false;
	var keyNumber = (window.event) ? e.keyCode : e.which;

	if (keyNumber==9) {
		return true;
	}
	if (keyNumber==13) {
		event.keyCode=9;
		return true;
	}
	
	if (((keyNumber<48) || (keyNumber>57)) && (keyNumber!=13) && (keyNumber!="0") && (keyNumber!=8)) {
		if (isIE) event.keyCode=0;
		return false;
	}
}

//SOMENTE LETRAS
 
function somenteLetras(e){
	var tecla=(window.event)?event.keyCode:e.which;
	
	if((tecla > 65 && tecla < 90)||(tecla > 97 && tecla < 122)) { 
		return true;
	} else if (tecla != 8 && tecla != 9) {
		return false;
	}
	return false;
}
function somenteNumeros(e){
	var tecla=(window.event)?event.keyCode:e.which;
	if((tecla > 47 && tecla < 58) || (tecla > 95 && tecla < 106)) {
		return true;
	} else if (tecla != 8 && tecla != 9) {
		return false;
	}
	return false;
}
/*
* S� deixa digitar n�meros.
*/
function isNumberKey(evt) {
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
   }

   return true;
}

//Permite digita��o somente de letras.
function permitirApenasLetras(e) {
	var e = (window.event)? event : e;
	var isIE = (window.event)? true : false;
	var keyNumber = (isIE) ? event.keyCode : e.which;
	if (((keyNumber<65) || (keyNumber>90)) && ((keyNumber<97) || (keyNumber>122))) {
		if (isIE) event.keyCode=0;
		return false;
	}
	return true;
}

//Permite todos os caracteres com excess�o de caracteres que podem prejudicar cl�usulas de DB e Query String Web.
//F�bio Henrique - 25/03/2010
function limitarCharReservados(e) {
	var e = (window.event)? event : e;
	var isIE = (window.event)? true : false;
	var keyNumber = (isIE) ? event.keyCode : e.which;
	// [# 35] [& 38] [| 124] [% 37] [' 39] [" 34]
	if ((keyNumber==35) || (keyNumber==39)  || (keyNumber==34)  || (keyNumber==38) || (keyNumber==124) || (keyNumber==37)) {
		if (isIE) event.keyCode=0;
		return false;
	}
	return true;
}
function replaceCharReservados_Texto(el) {
	if ((el != undefined) && (el.value != undefined)){
		var aux = '';
		for (x=0; x < el.value.length; x++) {
			var c = el.value.substring(x, x+1);
			if ((c == '#') || (c == '|') || (c == '&') || (c == "'") || (c == '"') || (c == '<') || (c == '>')) {
				c = '';
			}
			aux = aux + c;
		}
		el.value = aux;
	}
	return true;
}

//Substitui valores inv�lidos em campos de valor de CNPJ/CPF
//Criada com a finalizada de limpar o campo apos CTRL V 
function replaceCharReservados_CNPJ(el) {
	if ((el != undefined) && (el.value != undefined)){
		var aux = '';
		var re1 = new RegExp(/[0-9]/);
		for (x=0; x < el.value.length; x++) {
			var c = el.value.substring(x, x+1);
			if (!re1.test(c)) {
				if ((c != '-') && (c != '/') && (c != '.')) {
					c = '';
				}
			}
			aux = aux + c;
		}
		el.value = aux;
	}
	return true;
}

//Substitui valores inv�lidos em campos que aceitam apenas inteiros.
//Criada com a finalizada de limpar o campo apos CTRL V 
function replaceCharReservados_Inteiros(el) {
	if ((el != undefined) && (el.value != undefined)){
		var aux = '';
		var re1 = new RegExp(/[0-9]/);
		for (x=0; x < el.value.length; x++) {
			var c = el.value.substring(x, x+1);
			if (!re1.test(c)) {
				c = '';
			}
			aux = aux + c;
		}
		el.value = aux;
	}
	return true;
}

//Substitui valores inv�lidos em campos de valor monet�rio
//Criada com a finalizada de limpar o campo apos CTRL V 
function replaceCharReservados_Monetario(el) {
	if ((el != undefined) && (el.value != undefined)){
		var aux = '';
		var re1 = new RegExp(/[0-9]/);
		for (x=0; x < el.value.length; x++) {
			var c = el.value.substring(x, x+1);
			if (!re1.test(c)) {
				if ((c != ',') && (c != '.')) {
					c = '';
				}
			}
			aux = aux + c;		
		}
		el.value = aux;
	}
	return true;
}

//Substitui valores inv�lidos em campos de Data
//Criada com a finalizada de limpar o campo apos CTRL V 
function replaceCharReservados_Data(el) {
	if ((el != undefined) && (el.value != undefined)){
		var aux = '';
		replaceCharReservados_Inteiros(el);
		for (x=0; x < el.value.length; x++) {
			var c = el.value.substring(x, x+1);
			aux = aux + c;
			if (aux.length == 10) {
				el.value = aux;
				return true;
			} else if (aux.length == 2 || aux.length == 5) {
				aux = aux + '/';
			}
		}
		el.value = aux;
	}
	return true;
}



//Permite digita��o da express�o
function permitirExpressao(e, expr) {
	var e = (window.event)? event : e;
	var isIE = (window.event)? true : false;
	var keyNumber = (isIE) ? event.keyCode : e.which;
	var re = new RegExp(expr);
	if (!re.test(String.fromCharCode(keyNumber))) {
		if (isIE) event.keyCode=0;
		return false;
	}
	return true;
}

//Formta um campo data para o formato XX/XX/XXXX.
function formatarData(input, event){
	if ((event.keyCode==7))	return false;
	if ((event.keyCode<48)||(event.keyCode>57)){
		event.returnValue = false; 
	} else { 
	if ((input.value.length==2)||(input.value.length==5))
		input.value=input.value + "/" ;
	}
	if (input.value.length == 10) {return false;}
}

//Permite a digita��o at� o tamanho m�ximo definido
//N�o precisa usar o return por causa da rotina excluiExcesso usada aqui.
//Cancela evento
function limitar (e, length) {
	var oEvent = (window.event)? event : e;
	var oTarget = (oEvent.target)? oEvent.target : oEvent.srcElement;
	if (oTarget != undefined && oTarget.value != undefined && oTarget.value.length > length) {
		excluiExcesso(oTarget, length);
		if (oEvent.keyCode != 13 && oEvent.keyCode != 9) {
			return false;
		}
	  } 
	return true;
}

//Exclui o que foi digitado al�m do tamanho m�ximo definido
//N�o cancela evento
function excluiExcesso (campo, length) {
	if (campo.value.length>length) {
		campo.value = campo.value.substring(0,length);
	}
	return true;
}

function checkGridTodos(checkAll, checkName) {
	if (checkName != undefined &&  checkAll != undefined) {
		var checks = checkAll.form.elements[checkName];
		//if (checks == undefined) {alert(checkName + ' n�o foi encontrado pelo getById!');}
		if (checks.length != undefined) {
			for (i = 0; i < checks.length; i++) {
				checks[i].checked = checkAll.checked;
			}
		} else  {
			checks.checked = checkAll.checked;
		}
	}
}


function confirmarConsulta(msgconsulta) {
	var campo = document.forms[0].elements['campo'];
	if (campo == undefined || campo.value == '') {
		return confirm(msgconsulta);
	}
	return true;
}


//Anula keypress tab.
function anulaTab(e) {
	var e = (window.event)? event : e;
	var isIE = (window.event)? true : false;
	var key = (isIE) ? event.keyCode : e.which;
	if(key=="9")
		return false;
	else
		return true; 	
}
function mascararNumeroRL(pCampo, pEvento, pQtdeDigInt, pQtdeDigFrac, pSimbAgrup, pSimbFrac, pQtdeDigAgrup) {

      if (pEvento.which) { //Firefox 
          whichCode = pEvento.which;
      } else { //Internet explorer
          whichCode = pEvento.keyCode;
      }
      if (whichCode == 13 || whichCode == 9 || whichCode == 8) { //Enter ou Tab ou Delete
          return true;
      }
      
      
      valorTmp = pCampo.value + String.fromCharCode(whichCode);
      valorTmp = valorTmp.replace(/\D/g, '');

	 pEvento.returnValue = false; 
	if (pEvento.cancelable) {
		pEvento.preventDefault();
	}	 
	   if (window.event) {
		   //pEvento.keyCode = 0; dando acesso negado.
	   }
      
    
      tamTotal = pQtdeDigInt + pQtdeDigFrac;
  
      tamValor = valorTmp.length;
      if (tamValor > tamTotal) valorTmp = valorTmp.substring(tamValor - tamTotal);
      tamValor = valorTmp.length;
  
      posInsDig = tamValor - pQtdeDigFrac;
      qtdeAgrup = parseInt(posInsDig / pQtdeDigAgrup);
      if (posInsDig % pQtdeDigAgrup == 0) qtdeAgrup--;
  
      if (pQtdeDigFrac > 0 && tamValor > pQtdeDigFrac) {
          valorTmp = valorTmp.substring(0, posInsDig) + pSimbFrac + valorTmp.substring(posInsDig);
      }
      for (i = 0; i < qtdeAgrup; i++) {
          posInsDig = posInsDig - pQtdeDigAgrup;
          valorTmp = valorTmp.substring(0, posInsDig) + pSimbAgrup + valorTmp.substring(posInsDig);
      }
	  pCampo.value = valorTmp; 
	  return false;
}
function mascararNumeroLR(pCampo, pEvento, pQtdeDigInt, pQtdeDigFrac, pSimbAgrup, pSimbFrac, pQtdeDigAgrup) {
	  if (pEvento.which) { //Firefox 
          whichCode = pEvento.which;
      } else { //Internet explorer
          whichCode = pEvento.keyCode;
      }
      if (whichCode == 13 || whichCode == 9 || whichCode == 8) { //Enter ou Tab ou Delete
          return true;
      } 
      
      valorTmp = pCampo.value + String.fromCharCode(whichCode);
      valorTmp = valorTmp.replace(/\D/g, '');
      
	   pEvento.returnValue = false; 
	   if (window.event) {
		   //pEvento.keyCode = 0;
	   }
      
      tamTotal = pQtdeDigInt + pQtdeDigFrac;
  
      tamValor = valorTmp.length;
      if (tamValor > tamTotal) valorTmp = valorTmp.substring(0, tamTotal);
      tamValor = valorTmp.length;
  
      if (tamValor > pQtdeDigInt) {
          valorTmp = valorTmp.substring(0, pQtdeDigInt) + pSimbFrac + valorTmp.substring(pQtdeDigInt);
          tamValor = pQtdeDigInt;
      }
      posInsDig = tamValor % pQtdeDigAgrup;
      qtdeAgrup = parseInt(tamValor / pQtdeDigAgrup);
      if (posInsDig == 0) qtdeAgrup--;
      posInsDig = tamValor - pQtdeDigAgrup;

      for (i = 0; i < qtdeAgrup; i++) {
          valorTmp = valorTmp.substring(0, posInsDig) + pSimbAgrup + valorTmp.substring(posInsDig);
          posInsDig = posInsDig - pQtdeDigAgrup;
      }
      pCampo.value = valorTmp;
      return false;
 }
