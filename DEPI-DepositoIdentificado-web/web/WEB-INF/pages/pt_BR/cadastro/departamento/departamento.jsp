<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<c:set var="namespaceBase" scope="request">/cadastro/departamento/editar</c:set>

<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceBase}/alterar.do" var="actionForm"></c:url>

<form action="${actionForm}" id="AcaoForm" method="post">
<table id="tabela_interna" class="sortable Departamento Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="sigla">
			<s:text name="label.grid.departamento.siglaDepartamento"/>
		</th>
		<th class="nome">
			<s:text name="label.grid.departamento.nomeDepartamento"/>
		</th>
		<th class="responsavel">
			<s:text name="label.grid.departamento.responsavelAtualizacao"/>
		</th>
		<th class="atualizacao">
			<s:text name="label.grid.departamento.dataHoraAtualizacao"/>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
		<td class="selecao">
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoDepartamento}"/>"/>
		</td>
		<td class="sigla">
			<s:url action="exibir" namespace="/cadastro/departamento/editar" var="linkExibir">
				<s:param name="codigo">${item.codigoDepartamento}</s:param>
			</s:url>
			<s:a href="%{linkExibir}">
				${item.siglaDepartamento}
			</s:a>
		</td>
		<td class="nome">${item.nomeDepartamento}</td>
		<td class="responsavel">${item.codigoResponsavelUltimaAtualizacao}</td>
		<td class="atualizacao"><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.ultimaAtualizacao}"/></td>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>

<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-alterar-excluir.jsp"></s:include>
</form>

<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.consulta.prepararFormulario("#AcaoForm");
	<s:if test="colecaoDados">
	$.paginacao.paginar({
		tblSeletor: ".Departamento",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery));
</script>
</c:set>
</s:if>
