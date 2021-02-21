<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceBase" scope="request">/cadastro/parametro/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceBase}/alterar.do" var="actionForm"></c:url>
<form action="${actionForm}" id="AcaoForm" method="post">

<table id="tabela_interna" class="sortable Parametro Consulta">
	<thead>
		<tr>
		<th class="selecao" style="width: 10%">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th style="width: 20%">
			<s:text name="label.grid.parametrodeposito.motivo"/>
		</th>
		<th style="width: 7%">
			<s:text name="label.grid.parametrodeposito.cia"/>
		</th>
		<th style="width: 20%">
			<s:text name="label.grid.parametrodeposito.departamento"/>
		</th>
		<th style="width: 13%">
			<s:text name="label.grid.parametrodeposito.retiradaVencimento"/>
		</th>
		<th style="width: 13%">
			<s:text name="label.grid.parametrodeposito.responsavelAtualizacao"/>
		</th>
		<th style="width: 13%">
			<s:text name="label.grid.departamento.dataHoraAtualizacao"/>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
		<td class="text-center"><c:set var="pk">${item.departamento.codigoDepartamento};${item.motivoDeposito.codigoMotivoDeposito};${item.companhia.codigoCompanhia}</c:set>
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${pk}"/>"/>
		</td>
		<td>
			<s:url action="exibir" namespace="/cadastro/parametro/editar" var="linkExibir">
				<s:param name="codigo">${pk}</s:param>
			</s:url>
			<s:a href="%{linkExibir}">
				${item.motivoDeposito.descricaoBasica}
			</s:a>
		</td>
		<td class="text-center">${item.companhia.codigoCompanhia}</td>
		<td>${item.departamento.nomeDepartamento}</td>
		<td class="text-center"><c:choose><c:when test="${item.codigoBancoVencimento eq 'S'}">${item.numeroDiasAposVencimento} dia(s)</c:when><c:otherwise>Não</c:otherwise></c:choose></td>
		<td class="text-center">${item.codigoResponsavelUltimaAtualizacao}</td>
		<td class="text-center"><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.ultimaAtualizacao}"/></td>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>
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
		tblSeletor: ".Parametro",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery));
</script>
</c:set>
</s:if>