<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceBase" scope="request">/cadastro/grupo-acesso/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceBase}/alterar.do" var="actionForm"></c:url>

	<form action="${actionForm}" id="AcaoForm" method="post">
		<table id="tabela_interna" class="sortable GrupoAcesso Consulta">
			<thead>
				<tr>
					<th class="selecao"><s:text name="label.todos" /> <br /> <input
						type="checkbox" class="optionbutton checkTodos" /></th>
					<th class="codigoGrupoAcesso"><s:text
							name="label.grid.grupoacesso.codigoGrupoAcesso" /></th>
					<th class="nomeGrupoAcesso"><s:text
							name="label.grid.grupoacesso.nomeGrupoAcesso" /></th>
					<th class="codigoCompanhia"><s:text
							name="label.grid.departamentocompanhia.codigoCompanhia" /></th>
					<th class="descricaoCompanhia"><s:text
							name="label.grid.departamentocompanhia.descricaoCompanhia" /></th>
					<th class="codigoDepartamento"><s:text
							name="label.grid.grupoacesso.siglaDepartamento" /></th>
					<th class="nomeDepartamento"><s:text
							name="label.grid.grupoacesso.nomeDepartamento" /></th>
					<th class="responsavel"><s:text
							name="label.grid.grupoacesso.responsavelAtualizacao" /></th>
					<th class="atualizacao"><s:text
							name="label.grid.grupoacesso.dataHoraAtualizacao" /></th>
				</tr>
			</thead>
			<tbody class="lista">
				<s:iterator value="colecaoDados" var="item" status="status">
					<tr>
						<td class="text-center"><input type="checkbox"
							class="optionbutton" name="codigo"
							value="<c:out value="${item.codigoGrupoAcesso}"/>" /></td>
						<td class="text-center"><s:url action="exibir"
								namespace="/cadastro/grupo-acesso/editar" var="linkExibir">
								<s:param name="codigo">${item.codigoGrupoAcesso}</s:param>
							</s:url> <s:a href="%{linkExibir}">
					${item.codigoGrupoAcesso}
				</s:a></td>
						<td class="text-center">${item.nomeGrupoAcesso}</td>
						<td class="text-center">${item.cia.codigoCompanhia}</td>
						<td>${item.cia.descricaoCompanhia}</td>
						<td class="text-center">${item.depto.siglaDepartamento}</td>
						<td>${item.depto.nomeDepartamento}</td>
						<td class="text-center">${item.codigoResponsavelUltimaAtualizacao}</td>
						<td class="text-center"><fmt:formatDate type="both"
								dateStyle="medium" timeStyle="medium"
								value="${item.dataHoraAtualizacao}" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<div class="paginacao"></div>

		<s:include
			value="/WEB-INF/pages/pt_BR/comum/incluir-alterar-excluir.jsp"></s:include>
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