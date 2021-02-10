<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- incluir-consultar namespaceBase = ${namespaceBase} -->
<div class="botoes_filtro">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
				<s:if test="!colecaoDados || colecaoDados.isEmpty()"><c:url var="btnIncluirUrl" value="${namespaceBase}/incluir.do"/>
					<button class="abtn btnIncluir" id="BtnIncluirTopo" style="width:70px" href="${btnIncluirUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.gif"/>"></button>
				</s:if>
					<button class="abtn btnConsultar" id="BtnConsultar" style="width:70px"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_consultar.gif"/>"></button>
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
<c:if test="${empty namespaceBase}"><span style="background-color:red">namespaceBase=${namespaceBase}</span></c:if>
