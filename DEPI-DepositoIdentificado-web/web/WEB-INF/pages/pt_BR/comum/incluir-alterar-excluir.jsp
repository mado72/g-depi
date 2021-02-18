<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- incluir-alterar-excluir namespaceBase = ${namespaceBase} -->
<div class="botoes_editar">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div id="tabela_botoes"><c:url var="btnIncluirUrl" value="${namespaceBase}/incluir.do"/>
					<button class="abtn btnIncluir" id="BtnIncluirRodape" href="${btnIncluirUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.gif"/>"></button>
					<c:if test="${! ocultarAlterar}"><button class="abtn" id="BtnAlterar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_alterar.jpg"/>"></button></c:if>
					<button class="abtn" id="BtnExcluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_excluir.jpg"/>"></button>
				</div>
			</td>
		</tr>
	</table>
</div>
<c:if test="${empty namespaceBase}"><span style="background-color:red">namespaceBase=${namespaceBase}</span></c:if>
