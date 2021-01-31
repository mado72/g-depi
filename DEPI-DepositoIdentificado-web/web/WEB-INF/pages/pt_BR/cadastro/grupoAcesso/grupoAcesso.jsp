<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceEditar">/cadastro/grupo-acesso/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-consultar.jsp">
	<s:param name="namespaceEditar" >${namespaceEditar}</s:param>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceEditar}/alterar.do" var="actionForm"></c:url>

<form action="${actionForm}" id="AcaoForm" method="post">
<table id="tabela_interna" class="GrupoAcesso Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="codigoGrupoAcesso">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="codigoGrupoAcesso"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.codigoGrupoAcesso"/>
			</s:a>
		</th>
		<th class="nomeGrupoAcesso">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="nome"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.nomeGrupoAcesso"/>
			</s:a>
		</th>
		<th class="codigoCompanhia">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="cia.codigoCompanhia"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.departamentocompanhia.codigoCompanhia"/>
			</s:a>
		</th>
		<th class="descricaoCompanhia">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="cia.descricaoCompanhia"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.departamentocompanhia.descricaoCompanhia"/>
			</s:a>
		</th>
		<th class="codigoDepartamento">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="depto.siglaDepartamento"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.siglaDepartamento"/>
			</s:a>
		</th>
		<th class="nomeDepartamento">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="depto.nomeDepartamento"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.nomeDepartamento"/>
			</s:a>
		</th>
		<th class="responsavel">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="responsavel"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.responsavelAtualizacao"/>
			</s:a>
		</th>
		<th class="atualizacao">
			<s:url action="ordenar" namespace="/consulta/grupo-acesso" var="linkSort">
				<s:param name="campo" value="atualizacao"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.dataHoraAtualizacao"/>
			</s:a>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
			<td class="text-center">
				<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoGrupoAcesso}"/>"/>
			</td>
			<td class="text-center">
				<s:url action="exibir" namespace="/cadastro/grupo-acesso/editar" var="linkExibir">
					<s:param name="codigo">${item.codigoGrupoAcesso}</s:param>
				</s:url>
				<s:a href="%{linkExibir}">
					${item.codigoGrupoAcesso}
				</s:a>
			</td>
			<td class="text-center">${item.nomeGrupoAcesso}</td>
			<td class="text-center">${item.cia.codigoCompanhia}</td>
			<td>${item.cia.descricaoCompanhia}</td>
			<td class="text-center">${item.depto.siglaDepartamento}</td>
			<td>${item.depto.nomeDepartamento}</td>
			<td class="text-center">${item.codigoResponsavelUltimaAtualizacao}</td>
			<td class="text-center">
				<fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.dataHoraAtualizacao}"/>
			</td>
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>

<s:include value="/WEB-INF/pages/pt_BR/comum/incluir-alterar-excluir.jsp">
	<s:param name="namespaceEditar" >${namespaceEditar}</s:param>
</s:include>
</form>
<br/>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.consulta.prepararFormulario("#AcaoForm");
	<s:if test="colecaoDados">
	$.paginacao.paginar({
		tblSeletor: ".GrupoAcesso",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery));
</script>
</c:set>
</s:if>