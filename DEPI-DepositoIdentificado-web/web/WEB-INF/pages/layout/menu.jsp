<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %>
<style>	#pop{display:none;position:absolute;top:40%;left:50%;margin-left:-150px;margin-top:-100px;padding:10px;width:235px;height:100px;border:1px solid #bc0000}</style>
<div id="menu_horizontal">
	<div class="itemmenu" onmouseover="over(1)" onmouseout="out(1)">
		<a href="<s:url value="/index.do"/>" class="menu_horizontal">Cadastros</a>
		<div class="dropdown" id="s1">
			<a href="<s:url value="/cadastro/motivoDeposito/consultar/index.do"/>">Motivos de Depósito</a>
			<a href="<s:url value="/cadastro/departamento/consultar/index.do"/>">Departamento</a>
			<a href="<s:url value="/cadastro/departamento-companhia/consultar/index.do"/>">Associação Departamentos x Cia</a>
			<a href="<s:url value="/cadastro/grupo-acesso/consultar/index.do"/>">Grupo de Acesso</a>
			<a href="<s:url value="/cadastro/parametro/consultar/index.do"/>">Parâmetros de Depósito</a>
			<a href="<s:url value="/cadastro/contaCorrenteAutorizada/consultar/index.do"/>">Conta Corrente</a>	
			<a href="<s:url value="/cadastro/associarMotivoDeposito/consultar/index.do"/>">Associação de Motivos</a>
		</div>
	</div>
	<div class="itemmenu"><a href="<s:url value="/deposito/ConsultarDeposito.do"/>" class="menu_horizontal">Identificação Depósitos</a></div>
	<div class="itemmenu" onmouseover="over(2)" onmouseout="out(2)">
		<a href="<s:url value="/index.do"/>" class="menu_horizontal">Relatórios</a>
		<div class="dropdown" id="s2">
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirEnvioRetornoAnalitico"/>"  >Envio/Retorno Banco - Analítico</a>
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirEnvioRetornoSintetico"/>">Envio/Retorno Banco - Sintético</a>
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirExtratoAnalitico"/>">Extrato Banco - Analítico</a>
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirExtratoSintetico"/>">Extrato Banco - Sintético</a>
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirManutencoesAnalitico"/>">Manutenções - Analítico</a>
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirManutencoesSintetico"/>">Manutenções - Sintético</a>
			<a href="<s:url value="/relatorio/consultarRelatorio.do?acao=exibirDadosComplementares"/>">Dados Complementares - Analítico</a>
		</div>
	</div>
	<div class="itemmenu">

	<div class="itemmenu">
		<a href="<s:url value="/sobre.do"/>" class="menu_horizontal">Sobre</a>
	</div>
	<div class="itemmenu">
		<a href="#" onclick="window.close();" class="menu_horizontal">Sair</a>
	</div>
	</div>
</div>	

