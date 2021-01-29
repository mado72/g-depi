<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<div class="botoes_filtro">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
				<s:if test="!colecaoDados || colecaoDados.isEmpty()"><c:url var="btnIncluirUrl" value="${namespaceEditar}/incluir.do"/>
					<a class="abtn btnIncluir" id="BtnIncluirTopo" href="${btnIncluirUrl}/incluir.do"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.gif"/>"></a>
				</s:if>
					<a class="abtn btnConsultar" id="BtnConsultar" href="javascript:void(0)"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_consultar.gif"/>"></a>
				</div>
			</td>
		</tr>
	</table>
	<table class="tabela_botoes">
		<tr>
			<td align="center"><div id="box_loading"></div></td>
		</tr>
	</table>
</div>


