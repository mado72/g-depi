<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceEditar">/cadastro/departamento/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-consultar.jsp">
	<s:param name="namespaceEditar" >${namespaceEditar}</s:param>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">

<s:form action="acao.do" namespace="/cadastro/departamento/editar" id="AcaoForm">

<table id="tabela_interna" class="Departamento Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="sigla">
			<s:url action="ordenar" namespace="/consulta/departamento" var="linkSort">
				<s:param name="campo" value="sigla"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.departamento.siglaDepartamento"/>
			</s:a>
		</th>
		<th class="nome">
			<s:url action="ordenar" namespace="/consulta/departamento" var="linkSort">
				<s:param name="campo" value="nome"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.departamento.nomeDepartamento"/>
			</s:a>
		</th>
		<th class="responsavel">
			<s:url action="ordenar" namespace="/consulta/departamento" var="linkSort">
				<s:param name="campo" value="responsavel"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.departamento.responsavelAtualizacao"/>
			</s:a>
		</th>
		<th class="atualizacao">
			<s:url action="ordenar" namespace="/consulta/departamento" var="linkSort">
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
		<td class="atualizacao">
			<%--
				FIXME Adicionar campo atualização em DepartamentoVO 
				<fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.ultimaAtualizacao}"/> 
			--%>
		</td>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>
<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-alterar-excluir.jsp">
	<s:param name="namespaceEditar" >${namespaceEditar}</s:param>
</s:include>
</s:form>

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