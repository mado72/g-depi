<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:include value="/WEB-INF/pages/pt_BR/cadastro/parametro/parametroFiltro.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados">

<s:form action="acao.do" namespace="/cadastro/parametro/editar" id="AcaoForm">

<table id="tabela_interna" class="sortable Parametro Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="descricao">
			<s:text name="label.grid.parametro.descricaoMotivoDeposito"/>
		</th>
		<th class="responsavel">
			<s:text name="label.grid.parametro.responsavelAtualizacao"/>
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
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoMotivoDeposito}"/>"/>
		</td>
		<td class="descricao">
			<s:url action="exibir" namespace="/cadastro/parametro/editar" var="linkExibir">
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
<br/>
	<br/>
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div id="tabela_botoes">
					<s:a id="BtnIncluir2" class="btnIncluir" action="incluir" namespace="/cadastro/parametro/editar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></s:a>
					<a class="button" id="BtnAlterar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_alterar.jpg"/>"></a>
					<a class="button" id="BtnExcluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_excluir.jpg"/>"></a>
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