<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:include value="/WEB-INF/pages/pt_BR/cadastro/motivoDeposito/motivoDepositoFiltro.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<c:if test="${catchException != null}">
<p>The exception is : ${catchException} <br />
There is an exception: ${catchException.message}</p>
</c:if>
<%-- 
<pre style="overflow:auto; width: 600px; overflow-wrap:break-word; background-color: menu;">
COLECAO DADOS: ${colecaoDados}

JSON: ${json}
</pre>
 --%>

<c:if test="${colecaoDados != null}">
<s:form action="acao.do" id="AcaoForm">

<table id="tabela_interna" class="MotivoDeposito">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos"/>
		</th>
		<th class="descricao">
			<s:url action="ordenar" namespace="/consulta/motivoDeposito" var="linkSort">
				<s:param name="campo" value="descricao"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.motivodeposito.descricaoMotivoDeposito"/>
			</s:a>
		</th>
		<th class="responsavel">
			<s:url action="ordenar" namespace="/consulta/motivoDeposito" var="linkSort">
				<s:param name="campo" value="responsavel"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.motivodeposito.responsavelAtualizacao"/>
			</s:a>
		</th>
		<th class="atualizacao">
			<s:url action="ordenar" namespace="/consulta/motivoDeposito" var="linkSort">
				<s:param name="campo" value="atualizacao"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.departamento.dataHoraAtualizacao"/>
			</s:a>
		</th>
		</tr>
	</thead>
	<tbody>
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
		<td class="selecao">
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoMotivoDeposito}"/>"/>
		</td>
		<td class="descricao">
			<s:url action="exibir" namespace="/consulta/motivoDeposito" var="linkExibir">
				<s:param name="codigo">${item.codigoMotivoDeposito}</s:param>
			</s:url>
			<s:a href="%{linkExibir}">
				${item.descricaoDetalhada}
			</s:a>
		</td>
		<td class="responsavel">${item.codigoResponsavelUltimaAtualizacao}</td>
		<td class="atualizacao">
		<%--
		 <c:out value="${item.atualizacao}" default="" /></td>
		 --%>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<br/>
	<br/>
	<table width="90%" style="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div id="tabela_botoes">
					<a role="button" class="button" id="BtnIncluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></a>
					<a role="button" class="button" id="BtnAlterar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_alterar.jpg"/>"></a>
					<a role="button" class="button" id="BtnExcluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_excluir.jpg"/>"></a>
				</div>
			</td>
		</tr>
	</table>

</s:form>
<br/>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.consulta.prepararFormulario("#AcaoForm");
}(jQuery));
</script>
</c:set>
</c:if>