//versão 1.1
	function onLoad(){
		//setFocus('codigoCompanhiaSeguradora');
		//carregaDias();
	}
	function setDias(vencimento){
		if(vencimento == 'S'){
			enable('numeroDiasAposVencimento');
			document.getElementById('numeroDiasAposVencimento').value = '';
			document.getElementById('numeroDias').value = '';
			setFocus('numeroDiasAposVencimento');
		}else{
			document.getElementById('numeroDiasAposVencimento').value = 0;
			document.getElementById('numeroDias').value = 0;
			disable('numeroDiasAposVencimento');
			
		}
	}
	
		function habilitarCombo(){
		
		var codCia = document.forms[0].elements['codigoCompanhiaSeguradora'];
		
		if(codCia.options[codCia.selectedIndex].value != ""){
			document.getElementById('codigoDepartamento').disabled = false;
			document.getElementById('codDepartamento').disabled = false;
		}
		else
		{
			document.getElementById('codigoDepartamento').disabled = true;
			document.getElementById('codDepartamento').disabled = true;
		}

	}
	
	function setNumeroDias(){
		document.getElementById('numeroDias').value = document.getElementById('numeroDiasAposVencimento').value;
	}	
	
	
	function desabilita(){
			document.getElementById('numeroDias').value = 0;
			document.getElementById('numeroDiasAposVencimento').value = 0;
			disable('numeroDiasAposVencimento');
	}
	
	function setCombo(id, valor){
		document.getElementById(id).value = valor;
		
	}

	function setCampos(valor){
		if(valor != ''){
			var hdd = document.getElementById('hdd-' + valor);
			document.getElementsByName('motivoDetalhado')[0].value = hdd.value;
		}else{
			document.getElementsByName('motivoDetalhado')[0].value = '';
			//setFocus(id);
		}
		//setFocus("codigoBancoVencimento");
	}
	
	function disable(id){
		document.getElementById(id).disabled = true;
	}
	
	function enable(id){
		document.getElementById(id).disabled = false;
	}
	
	function carregaDias(){
		
		document.getElementById('numeroDiasAposVencimento').value = '';
		
		var flag = '<bean:write name="parametroDepositoForm" property="codigoBancoVencimento"/>';
		if(flag == 'N'){
			desabilita();
		}
	}	
//-->