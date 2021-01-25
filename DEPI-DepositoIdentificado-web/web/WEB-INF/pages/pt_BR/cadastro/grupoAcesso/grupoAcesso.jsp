<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:include value="/WEB-INF/pages/pt_BR/cadastro/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>
<br />
<s:include value="/WEB-INF/pages/pt_BR/cadastro/comum/incluir-consultar.jsp" />

<s:if test="colecaoDados">
<s:form action="acao.do" namespace="/cadastro/grupoAcesso/editar" id="AcaoForm">

<table id="tabela_interna" class="GrupoAcesso Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th class="codigoGrupoAcesso">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="codigoGrupoAcesso"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.codigoGrupoAcesso"/>
			</s:a>
		</th>
		<th class="nomeGrupoAcesso">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="nome"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.nomeGrupoAcesso"/>
			</s:a>
		</th>
		<th class="codigoCompanhia">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="cia.codigoCompanhia"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.codigoCompanhia"/>
			</s:a>
		</th>
		<th class="descricaoCompanhia">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="cia.descricaoCompanhia"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.descricaoCompanhia"/>
			</s:a>
		</th>
		<th class="codigoDepartamento">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="depto.codigoDepartamento"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.codigoDepartamento"/>
			</s:a>
		</th>
		<th class="nomeDepartamento">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="depto.nomeDepartamento"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.nomeDepartamento"/>
			</s:a>
		</th>
		<th class="responsavel">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
				<s:param name="campo" value="responsavel"/>
			</s:url>
			<s:a href="%{linkSort}">
				<s:text name="label.grid.grupoAcesso.responsavelAtualizacao"/>
			</s:a>
		</th>
		<th class="atualizacao">
			<s:url action="ordenar" namespace="/consulta/grupoAcesso" var="linkSort">
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
		<td>
			<input type="checkbox" class="optionbutton" name="codigo" value="<c:out value="${item.codigoGrupoAcesso}"/>"/>
		</td>
		<td>
			<s:url action="exibir" namespace="/cadastro/grupoAcesso/editar" var="linkExibir">
				<s:param name="codigo">${item.codigoGrupoAcesso}</s:param>
			</s:url>
			<s:a href="%{linkExibir}">
				${item.codigoGrupoAcesso}
			</s:a>
		</td>
		<td>${item.nomeGrupoAcesso}</td>
		<td>${item.cia.codigoCompanhia}</td>
		<td>${item.cia.descricaoCompanhia}</td>
		<td>${item.depto.codigoDepartamento}</td>
		<td>${item.depto.nomeDepartamento}</td>
		<td class="responsavel">${item.codigoResponsavelUltimaAtualizacao}</td>
		<td class="atualizacao">
			<%--
				FIXME Adicionar campo atualização em GrupoAcessoVO 
				<fmt:formatDate type = "both" dateStyle = "medium" timeStyle = "medium" value="${item.ultimaAtualizacao}"/> 
			--%>
		</td>
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
					<s:a id="BtnIncluir2" class="button btnIncluir" action="incluir" namespace="/cadastro/grupoAcesso/editar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></s:a>
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