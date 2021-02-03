<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- incluir-alterar-excluir namespaceBase = ${namespaceBase} -->
<c:choose><c:when test="${namespaceBase}">
<div class="botoes_editar">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div id="tabela_botoes"><c:url var="btnIncluirUrl" value="${namespaceBase}/incluir.do"/>btnIncluirUrl=${btnIncluirUrl}
					<a class="abtn btnIncluir" id="BtnIncluirRodape" href="${btnIncluirUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.gif"/>"></a>
					<a class="abtn" id="BtnAlterar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_alterar.jpg"/>"></a>
					<a class="abtn" id="BtnExcluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_excluir.jpg"/>"></a>
				</div>
			</td>
		</tr>
	</table>
</div>
</c:when><c:otherwise><span style="background-color:red">namespaceBase=${namespaceBase}</span></c:otherwise></c:choose>
