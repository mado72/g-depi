//Fábio Henrique - JavaScript para habilitar textbox de Manter Movimento
function verificarParaHabilitar(acao) {
	desabilitar((!acao == 'D'));
	if (acao == 'T') {
		habilitarTransferencia();
	}
}
function desabilitar(b) {
	document.getElementById('bancoMovimento').disabled = b;
	document.getElementById('agenciaMovimento').disabled = b;
	document.getElementById('contaMovimento').disabled = b;
	document.getElementById('nroCheque').disabled = b;
}

function habilitarTransferencia() {
	desabilitar(false);
	document.getElementById('nroCheque').disabled = true;
}
//fim