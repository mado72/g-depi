<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
<%@page import="br.com.bradseg.depi.dp06.model.util.enumerator.MotivoDepositoCampo"%>
<%@page import="br.com.bradseg.depi.dp06.controller.action.cadastro.MotivoDepositoAction;"%> 
<script src='<html:rewrite page="/includes/script/arquivo.js"/>'></script>
<jsp:include page="/WEB-INF/jsp/pt_BR/includes/include-consultarMotivoDeposito.jsp"></jsp:include>
<script type="text/javascript">
<!--
function checkTodos(checkAll) {
	checkGridTodos(checkAll, '<%= MotivoDepositoAction.NOME_CHECKBOX %>');
}
//-->
</script>

<jsp:include page="/WEB-INF/jsp/pt_BR/includes/logo-sistema.jsp"></jsp:include>

<!--mensagem de erro de negocio-->
<logic:messagesPresent message="true">
<br />
<table width="90%" border="0" class="tabela_verm" align="center">
<tr>
	<td align="left">
	<ul>
	<html:messages id="erro" message="true">
	<br><li><bean:write name="erro" /></li>
	</html:messages>
	</ul>
	</td>
</tr>
</table>
</logic:messagesPresent>

<html:form action="/cadastro/ConsultarMotivoDeposito.do" focus="campoFlt">
<html:hidden property="acaoAnterior" />
<html:hidden property="acao" />
<html:hidden property="codigoAutorizador" />
	<fsu:containerFiltro nomeForm="MotivoDepositoForm"  opcoes="<%= MotivoDepositoCampo.valuesForCriteria() %>">
	<table width="90%" id="tabela_interna" border="0" align="center">
		<tr>
			<td colspan="2" width="50%"/>
			<td><bean:message key="label.filtro.criterios" /></td>
		</tr>
		<tr>
			<td class="td_label" width="20%"><bean:message key="label.filtro.campo" /></td>
			<td>
				<fsu:campoFiltro/>
			</td>
			<td style="width: 400px" rowspan="4">
				<fsu:criteriosFiltro/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><bean:message key="label.filtro.operacao" /></td>
			<td>
				<fsu:operacaoFiltro/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><bean:message key="label.filtro.valor" /></td>
			<td>
				<fsu:valorFiltro/>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
			</td>
			<td>
				<fsu:adicionarFiltro midiaAcesso="intranet" complement="/imagens/plus.gif" />&nbsp;
				<fsu:removerFiltro midiaAcesso="intranet" complement="/imagens/minus.gif" />
			</td>
		</tr>
	</table>
	</fsu:containerFiltro>
	
	<table width="90%" class="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
					<logic:empty name="colecaoDados">
	
					<c:set value="${pageContext.request.contextPath}" var="urlIntranet"/>
	
					<a href="#" onclick="submitForm('novo', '<bean:write name="urlIntranet"/>/cadastro/CadastrarMotivoDeposito.do')" title="<bean:message key='label.incluir' />">
						<frw:image midiaAcesso="intranet" 
						styleClass="margem_botoes" complement="/imagens/bt_incluir.jpg" /></a>
					<a href="#" onclick="return (confirmarConsulta('<bean:message key="msg.confirmacao.consulta" />') && submitForm('consultar'))" title="<bean:message key='label.consultar' />">	
						<frw:image midiaAcesso="intranet" 
						styleClass="margem_botoes" complement="/imagens/bt_consultar.jpg" />
					</a>
					</logic:empty>
					<logic:notEmpty name="colecaoDados">
					<br />
					<a href="#" onclick="return (confirmarConsulta('<bean:message key="msg.confirmacao.consulta" />') && submitForm('consultar'))" title="<bean:message key='label.consultar' />">	
						<frw:image midiaAcesso="intranet" 
						styleClass="margem_botoes" complement="/imagens/bt_consultar.jpg" />
					</a>
					</logic:notEmpty>
				</div>
			</td>
		</tr>
	</table>
	<logic:present name="colecaoDados">
	<logic:notEmpty name="colecaoDados">
	<br/>
	<table width="90%" cellpadding="0" cellspacing="0" border="0" align="center">
		<tr>
			<td align="center">
				<display:table name="colecaoDados" style="width: 100%;" 
					pagesize="7" 
					requestURI="ConsultarMotivoDeposito.do" id="tabela_interna" 
					decorator="br.com.bradseg.depi.dp06.view.util.decorator.cadastro.MotivoDepositoDecorator">
					<display:column property="checkbox" titleKey="label.todos"
						style="text-align: center; width: 10%; valign: middle;" />
					<c:if test="${param.acao == 'exibir'}">
					<display:column property="descricaoBasica" titleKey="label.grid.motivodeposito.descricaoMotivoDeposito"
						style="text-align: left; width: 29%; valign: middle;" sortable="true" 
						href="CadastrarMotivoDeposito.do?acao=detalhar&acaoAnterior=exibir" paramId="codigoMotivoDeposito" paramProperty="codigoMotivoDeposito" />
					</c:if>
					<c:if test="${param.acao != 'exibir'}">
					<display:column property="descricaoBasica" titleKey="label.grid.motivodeposito.descricaoMotivoDeposito"
						style="text-align: left; width: 29%; valign: middle;" sortable="true" 
						href="CadastrarMotivoDeposito.do?acao=detalhar&acaoAnterior=visualizar" paramId="codigoMotivoDeposito" paramProperty="codigoMotivoDeposito" />
					</c:if>
					<display:column property="codigoResponsavelUltimaAtualizacao" titleKey="label.grid.motivodeposito.responsavelAtualizacao" 
						style="text-align: center; width: 18%; valign: middle;" sortable="true"/>
					<display:column property="dataHoraAtualizacaoFormatada" sortProperty="dataHoraAtualizacao" titleKey="label.grid.departamento.dataHoraAtualizacao"		
						style="text-align: center; width: 18%; valign: middle;" sortable="true"/>
				</display:table>
			</td>
		</tr>
	</table>
	<br/>
	<table width="90%" style="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div id="tabela_botoes">
					<a onclick="return novo()" href="#" title="<bean:message key='label.incluir' />">
						<frw:image midiaAcesso="intranet" styleClass="margem_botoes" complement="/imagens/bt_incluir.jpg" /></a>
					<a onclick="return editar()" href="#" title="<bean:message key='label.alterar' />">
						<frw:image midiaAcesso="intranet" complement="/imagens/bt_alterar.jpg" styleClass="margem_botoes" /></a>
					<a onclick="return excluir()" href="#" title="<bean:message key='label.excluir' />">
						<frw:image midiaAcesso="intranet" complement="/imagens/bt_excluir.jpg" styleClass="margem_botoes" /></a>
				</div>
			</td>
		</tr>
	</table>
	</logic:notEmpty>
	</logic:present>
	<table style="tabela_botoes" align="center" width="90%">
		<tr>
			<td align="center"><div id="box_loading"></div></td>
		</tr>
	</table>
	
	<script>
		configuraObjetos();  
	</script>	
</html:form>
<c:import url="motivoDepositoFiltro.jsp"></c:import>
--%>
<s:include value="/WEB-INF/pages/pt_BR/cadastro/motivoDeposito/motivoDepositoFiltro.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<c:if test="${catchException != null}">
<p>The exception is : ${catchException} <br />
There is an exception: ${catchException.message}</p>
</c:if>
DEPOSITO

<pre style="overflow:auto; width: 600px; overflow-wrap:break-word">
COLECAO DADOS: ${colecaoDados}

JSON: ${json}
</pre>
