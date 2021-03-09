<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceBase" scope="request">/cadastro/conta-corrente/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceBase}/alterar.do" var="actionForm"></c:url>
<form action="${actionForm}" id="AcaoForm" method="post">

<table id="tabela_interna" class="sortable ContaCorrente Consulta">
	<thead>
		<tr>
		<th class="selecao" style="width: 10%">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.contacorrenteautorizada.codigoCia"/>
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.contacorrenteautorizada.banco"/>
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.contacorrenteautorizada.agencia"/>
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.contacorrenteautorizada.contaCorrente"/>
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.contacorrenteautorizada.contaInterna"/>
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.contacorrenteautorizada.trps"/>
		</th>
		<th style="width: 5%">
			<s:text name="label.grid.parametrodeposito.responsavelAtualizacao"/>
		</th>
		<th style="width: 10%">
			<s:text name="label.grid.departamento.dataHoraAtualizacao"/>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
		<td class="text-center"><c:set var="pk">${item.cia.codigoCompanhia};${item.banco.cdBancoExterno};${item.codigoAgencia};${item.contaCorrente}</c:set>
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${pk}"/>"/>
		</td>
		<td class="text-center">
			<s:url action="exibir" namespace="/cadastro/conta-corrente/editar" var="linkExibir">
				<s:param name="codigo">${pk}</s:param>
			</s:url>
			<s:a href="%{linkExibir}">
				${item.cia.codigoCompanhia}
			</s:a>
		</td>
		<td class="text-center">
			<s:text name="format.banco"><s:param name="value" value="%{banco.cdBancoExterno}"/></s:text>
		</td>
		<td class="text-center">
			<s:text name="format.agencia"><s:param name="value" value="%{codigoAgencia}"/></s:text>
		</td>
		<td class="text-center">
			<s:text name="format.contaCorrente"><s:param name="value" value="%{contaCorrente}"/></s:text>
		</td>
		<td class="text-center">
			<s:text name="format.contaInterna"><s:param name="value" value="%{codigoInternoCC}"/></s:text>
		</td>
		<td class="text-center">
			<s:text name="format.trps"><s:param name="value" value="%{trps}"/></s:text>
		</td>
		<td class="text-center">${item.codigoResponsavelUltimaAtualizacao}</td>
		<td class="text-center"><fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.dataHoraAtualizacao}"/></td>
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
		tblSeletor: "table.Consulta",
		pagSeletor: ".paginacao",
		registros: 8,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery)); 

</script>
</c:set>
</s:if>
