

/**function setContaInterna(value) {
	if (value != undefined) {
		var txt_contaInterna = document.getElementById('txt_contaInterna');
		if (txt_contaInterna != undefined) {
			txt_contaInterna.value = '';
			var hdd_contaInterna = document.getElementById('hdd_contaInterna_' + value);
			if (hdd_contaInterna != undefined)  {
				txt_contaInterna.value = hdd_contaInterna.value;
			}
		}
	}
}**/


function setContaInterna(value) {
	if (value != undefined) {
		Alert('passei1');
		var txt_contaInterna = document.getElementById('txt_contaInterna');
		Alert('passei primeiro getElementById.');
		if (txt_contaInterna != undefined) {
			txt_contaInterna.value = '';
			Alert('vou tentar executar o segundo getElementById.');
			var hdd_contaInterna = document.getElementById('hdd_contaInterna_' + value);
			Alert('hdd_contaInterna_' + value);
			if (hdd_contaInterna != undefined)  {
				txt_contaInterna.value = hdd_contaInterna.value;
			}
		}
	}
}
	
	