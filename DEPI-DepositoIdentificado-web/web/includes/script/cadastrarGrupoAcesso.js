
	function setCombo(id, valor){
		document.getElementById(id).value = valor;
	}
	
	function habilitarCombo(){
		
		var codCia = document.forms[0].elements['sel-codCompanhia'];
		var codDep = document.getElementById('sel-codDepartamento');
		if(codCia.options[codCia.selectedIndex].value != ""){
			if (codigoDep != undefined) {
				codigoDep.disabled = false;
			}
			if (codigoDep != undefined) {
				codDep.disabled = false;
			}
		}
		else
		{
			if (codigoDep != undefined) {
				codigoDep.disabled = true;
			}
			if (codigoDep != undefined) {
				codDep.disabled = true;
			}
		}

	}
	
	function changeGrupo(){
		try {
			var sel = document.getElementById('sel-codDepartamento');
			var siglaDep = sel.options(sel.selectedIndex).text; 
			var codCia = document.getElementById('sel-codCia').value;
			var codGrupo = document.getElementById('codigoGrupo').value;
			if (sel != undefined && codCia != undefined && codGrupo != undefined) {
				document.getElementById('nomeGrupo').value = codCia + siglaDep + codGrupo;
			}
		} catch (e) {
			alert(e);
		}
	}
	function checkTodos(checkAll) {
		
		var codigosCad = document.getElementsByName('matFuncionario');
		if(codigosCad.length > 0)
		{
			checkGridTodos(checkAll, 'matFuncionario');
		}
	}
	
    
    function selecionarFuncionarios(codigos, nomes){

		tabl = document.getElementById('funcionarios');
		var codigosCad = document.getElementsByName('matFuncionario');

		for(var i=0;i<codigosCad.length;i++){
			for(var j=codigos.length;j>=0;j--){
				if(codigosCad[i].value == codigos[j]){
						 codigos.splice(j,1);
				}
			}
		}
		
		for(var i=0;i<codigos.length;i++){

			var linh = tabl.insertRow(1);
			var coln = linh.insertCell(0);
			coln.innerHTML = nomes[i];
			coln = linh.insertCell(0);
			coln.innerHTML = codigos[i];
			coln = linh.insertCell(0);
			coln.innerHTML = '<input type="checkbox" name="matFuncionario" value="'+codigos[i]+'"/>' + 
							 '<input type="hidden" name="listMatFuncionario" value="'+codigos[i]+'"/>';
			}	
    }
    
    function excluiFuncionario(){
  		tabl = document.getElementById('funcionarios');
  		var chks = document.forms[0].elements['matFuncionario'];
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