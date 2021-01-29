<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="botoes_editar">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div id="tabela_botoes"><c:url var="btnIncluirUrl" value="${namespaceEditar}/incluir.do"/>
					<a class="abtn btnIncluir" id="BtnIncluirRodape" href="${btnIncluirUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></a>
					<a class="abtn" id="BtnAlterar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_alterar.jpg"/>"></a>
					<a class="abtn" id="BtnExcluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_excluir.jpg"/>"></a>
				</div>
			</td>
		</tr>
	</table>
</div>
