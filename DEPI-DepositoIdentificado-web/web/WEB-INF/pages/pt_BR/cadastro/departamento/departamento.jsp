<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:include value="/WEB-INF/pages/pt_BR/cadastro/departamento/departamentoFiltro.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados">
<s:form action="acao.do" id="AcaoForm">

<table id="tabela_interna" class="Departamento">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos"/>
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
			<s:url action="detalhar" namespace="/cadastro/departamento/editar" var="linkExibir">
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
<br/>
	<br/>
	<table width="90%" style="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div id="tabela_botoes">
					<s:a action="incluir" class="button" id="BtnIncluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></s:a>
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
</s:if>