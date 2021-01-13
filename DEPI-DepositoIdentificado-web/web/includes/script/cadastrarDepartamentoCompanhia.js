	function setCombo(id, valor){
	if ((combo = document.getElementById(id))!= null) {combo.value = valor;}
		//var img = document.getElementsByTagName("frw:image");
		//img.focus();
	}

	function abreJanelaDepartamento(url){ 
		window.open(url + '/cadastro/PopupSelecionarDepartamento.do?acao=exibir','SelecaoDepartamento','status=yes,menubar=no,scrollbars=yes,height=500,width=800');
    }

    //Essa rotina não é chamada!
   	function detalharDepartamento(labelEdicao){ 
   		if(verificarBoxEdicao('codDepto', '<bean:message key="msg.selecao.edicao" />')){
				 var checks =  document.forms[0].elements['codDepto'];
				 if(checks.length == undefined){
				 	codigo = checks.value;
				 }else{
				 	for(var i=0; i<checks.length;i++){
				 		if(checks[i].checked){
						 	codigo = checks[i].value;
						 	break;				 		
				 		}
				 	}
				 }
	           window.open('<c:out value="${pageContext.request.contextPath}" />/cadastro/CadastrarDepartamento.do?acao=detalhar&codigoDepartamento='+codigo+'&popup=true','DetalharDepartamento');   
           }
           return false;
    }
    
    function selecionarDepartamento(codigos, siglas, nomes){

		tabl = document.getElementById('departamentos');
		var codigosCad = document.getElementsByName('codDepto');

		for(var i=0;i<codigosCad.length;i++){
			for(var j=codigos.length;j>=0;j--){
				if(codigosCad[i].value == codigos[j]){
						 codigos.splice(j,1);
				}
			}
		}
		for(var j=0;j<codigos.length;j++){
			var linh = tabl.insertRow(1);
			var coln = linh.insertCell(0);
			coln.innerHTML = nomes[j];
			coln = linh.insertCell(0);
			coln.innerHTML = siglas[j];
			coln = linh.insertCell(0);
			coln.innerHTML = '<input type="checkbox" name="codDepto" value="'+codigos[j]+'"/>' + 
							 '<input type="hidden" name="listCodDepartamento" value="'+codigos[j]+'"/>';
		}	
    }
    
    function excluiDepartamento(){
  		tabl = document.getElementById('departamentos');
  		var chks = document.forms[0].elements['codDepto'];
  		if (chks != undefined) {
			if	(chks.length==undefined){
				if(chks.checked){
					tabl.deleteRow(1);
				}
			}else {
				for(var i =chks.length; i>0; i--){
					if(chks[i-1].checked){
						tabl.deleteRow(i);
					}
				}
			}
  		}
	}
	
	function checkTodos(checkAll) {
		checkGridTodos(checkAll, 'codDepto');
	}
	
	function validarSalvar(msg_ciaDep, msg_dep, msg_cia){
		var msg = '';
		var cia = document.forms[0].elements['codigoCompanhiaSeguradora'];
		var chks = document.forms[0].elements['codDepto'];
		
		if(cia.value == '' && chks == undefined){
			alert(msg_ciaDep);
			cia.focus();
			return false;
		} else if (cia.value != '' && chks == undefined) {
			alert(msg_dep);
			var check = document.getElementsByTagName('input type="checkbox"');
			cia.focus();
			return false;
		} else if (cia.value == '' && chks != undefined) {
			alert(msg_cia);
			cia.focus();
			return false;
		} 
		return true;
	}