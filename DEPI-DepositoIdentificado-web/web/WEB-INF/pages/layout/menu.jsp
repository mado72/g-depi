<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %>
<style>	#pop{display:none;position:absolute;top:40%;left:50%;margin-left:-150px;margin-top:-100px;padding:10px;width:235px;height:100px;border:1px solid #bc0000}</style>
<div id="menu_horizontal">
	<div class="itemmenu" onmouseover="over(1)" onmouseout="out(1)">
		<a href="<s:url value="/index.do"/>" class="menu_horizontal">Cadastros</a>
		<div class="dropdown" id="s1">
			<a href="<s:url value="/cadastro/motivoDeposito/consultar/index.do?acao=firstOpening"/>">Motivos de Depósito</a>
			<a href="<s:url value="/cadastro/departamento/consultar/index.do?acao=firstOpening"/>">Departamento</a>
			<a href="<s:url value="/cadastro/ConsultarDepartamentoCompanhia.do?acao=firstOpening"/>">Associação Departamentos x Cia</a>
			<a href="<s:url value="/cadastro/ConsultarGrupoAcesso.do?acao=firstOpening"/>">Grupo de Acesso</a>
			<a href="<s:url value="/cadastro/ConsultarParametroDeposito.do?acao=firstOpening"/>">Parâmetros de Depósito</a>
			<a href="<s:url value="/cadastro/ConsultarContaCorrenteAutorizada.do?acao=firstOpening"/>">Conta Corrente</a>	
			<a href="<s:url value="/cadastro/ConsultarAssociarMotivoDeposito.do?acao=firstOpening"/>">Associação de Motivos</a>
		</div>
	</div>
	<div class="itemmenu"><a href="<s:url value="/deposito/ConsultarDeposito.do?acao=firstOpening"/>" class="menu_horizontal">Identificação Depósitos</a></div>
	<div class="itemmenu" onmouseover="over(2)" onmouseout="out(2)">
		<a href="<s:url value="/index.do"/>" class="menu_horizontal">Relatórios</a>
		<div class="dropdown" id="s2">
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirEnvioRetornoAnalitico"/>"  >Envio/Retorno Banco - Analítico</a>
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirEnvioRetornoSintetico"/>">Envio/Retorno Banco - Sintético</a>
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirExtratoAnalitico"/>">Extrato Banco - Analítico</a>
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirExtratoSintetico"/>">Extrato Banco - Sintético</a>
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirManutencoesAnalitico"/>">Manutenções - Analítico</a>
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirManutencoesSintetico"/>">Manutenções - Sintético</a>
			<a href="<s:url value="/relatorio/ConsultarRelatorio.do?acao=exibirDadosComplementares"/>">Dados Complementares - Analítico</a>
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

