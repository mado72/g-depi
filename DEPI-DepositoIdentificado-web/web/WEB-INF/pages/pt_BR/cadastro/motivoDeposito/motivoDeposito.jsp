<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" 
%>
<c:set var="namespaceEditar">/cadastro/motivoDeposito/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-consultar.jsp">
	<s:param name="namespaceEditar" >${namespaceEditar}</s:param>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="/cadastro/motivoDeposito/editar/alterar.do" var="actionForm"></c:url>

<form action="${actionForm}" id="AcaoForm" method="post">
<table id="tabela_interna" class="MotivoDeposito Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
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
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
		<td class="selecao">
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoMotivoDeposito}"/>"/>
		</td>
		<td class="descricao">
			<s:url action="exibir" namespace="/cadastro/motivoDeposito/editar" var="linkExibir">
				<s:param name="codigo">${item.codigoMotivoDeposito}</s:param>
			</s:url>
			<s:a href="%{linkExibir}">
				${item.descricaoBasica}
			</s:a>
		</td>
		<td class="responsavel">${item.codigoResponsavelUltimaAtualizacao}</td>
		<td class="atualizacao"><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.ultimaAtualizacao}"/></td>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>

<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-alterar-excluir.jsp">
	<s:param name="namespaceEditar" >${namespaceEditar}</s:param>
</s:include>
</form>

<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.consulta.prepararFormulario("#AcaoForm");
	<s:if test="colecaoDados">
	$.paginacao.paginar({
		tblSeletor: ".MotivoDeposito",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery));
</script>
</c:set>
</s:if>
