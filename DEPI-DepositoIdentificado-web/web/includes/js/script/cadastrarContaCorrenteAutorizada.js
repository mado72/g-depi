
	function habilitarCombo(){
	
		var flag = document.getElementById('flagCombo').value;
		var undefined;
	
		if(document.getElementById('acao').value != 'detalhar') {
	
			/**
			var codCia = document.forms[0].elements['codigoCompanhia'];
			var descCia = document.forms[0].elements['descricaoCompanhia'];
			var codBanco = document.forms[0].elements['codigoBanco'];
			var descBanco = document.forms[0].elements['descricaoBanco'];
			var codAg = document.forms[0].elements['codigoAgencia'];
			var descAg = document.forms[0].elements['descricaoAgencia'];
			var codCtaCor = document.forms[0].elements['codigoContaCorrenteInterna'];
		
			if(codCia.options[codCia.selectedIndex].value != ""){
				document.getElementById('descricaoCompanhia').disabled = false;
				document.getElementById('codigoBanco').disabled = false;
				document.getElementById('descricaoBanco').disabled = false;	
			}
			if(codBanco.options[codBanco.selectedIndex].value != ""){
				document.getElementById('descricaoBanco').disabled = false;	
				document.getElementById('codigoAgencia').disabled = false;	
				document.getElementById('descricaoAgencia').disabled = false;	
			}
			if(codAg.options[codAg.selectedIndex].value != ""){
				document.getElementById('descricaoAgencia').disabled = false;	
				document.getElementById('codigoContaCorrenteInterna').disabled = false;	
			}
		**/
			if(!(flag===undefined || flag=='')) {
			
				if((flag==1 || flag==2) && codCia.options[codCia.selectedIndex].value != "")
					document.getElementById('codigoBanco').focus();
				else if((flag==3 || flag==4) && codBanco.options[codBanco.selectedIndex].value != "")
					document.getElementById('codigoAgencia').focus();
				else if((flag==5 || flag==6) && codAg.options[codAg.selectedIndex].value != "")
					document.getElementById('codigoContaCorrenteInterna').focus();
				
			}
			
			
		
		}
		
	}
	
	function validarSalvar(){
		//document.getElementById('codigoContaCorrente').disabled = false;	
		habilitarCombo();
	}
	
	function setCombo(id, valor, flag){
		document.getElementById(id).value = valor;
		document.getElementById('flagCombo').value = flag;
		habilitarCombo();
		
	}
	
	function setComboCtaCor(id){
		
		//document.getElementById('codigoContaCorrente').value = document.getElementById('codigoContaCorrenteInterna').value;
		
		habilitarCombo();
	}
