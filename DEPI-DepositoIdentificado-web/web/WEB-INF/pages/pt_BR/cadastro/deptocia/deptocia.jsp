<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceBase" scope="request">/cadastro/departamento-companhia/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceBase}/alterar.do" var="actionForm"></c:url>

<form action="${actionForm}" id="AcaoForm" method="post">
<table id="tabela_interna" class="sortable DepartamentoCompanhia Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th>
			<s:text name="label.grid.departamentocompanhia.codigoCompanhia"/>
		</th>
		<th>
			<s:text name="label.grid.departamentocompanhia.descricaoCompanhia"/>
		</th>
		<th>
			<s:text name="label.grid.departamentocompanhia.siglaDepartamento"/>
		</th>
		<th>
			<s:text name="label.grid.departamentocompanhia.nomeDepartamento"/>
		</th>
		<th>
			<s:text name="label.grid.departamentocompanhia.codResplUltAtualizacao"/>
		</th>
		<th>
			<s:text name="label.grid.departamentocompanhia.dataHoraAtualizacao"/>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
			<td class="text-center">
				<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoComposto}"/>"/>
			</td>
			<td class="text-center">
				<s:url action="exibir" namespace="/cadastro/departamento-companhia/editar" var="linkExibir">
					<s:param name="codigo">${item.codigoComposto}</s:param>
				</s:url>
				<s:a href="%{linkExibir}">
					${item.companhia.codigoCompanhia}
				</s:a>
			</td>
			<td>${item.companhia.descricaoCompanhia}</td>
			<td class="text-center">${item.departamento.siglaDepartamento}</td>
			<td>${item.departamento.nomeDepartamento}</td>
			<td class="text-center">${item.codigoResponsavelUltimaAtualizacao}</td>
			<td class="text-center">
				<fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.dataHoraAtualizacao}"/>
			</td>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>
<c:set var="ocultarAlterar" value="true" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-alterar-excluir.jsp"></s:include>
</form>
<br/>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.consulta.prepararFormulario("#AcaoForm");
	<s:if test="colecaoDados">
	$.paginacao.paginar({
		tblSeletor: ".DepartamentoCompanhia",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery));
</script>
</c:set>
</s:if>