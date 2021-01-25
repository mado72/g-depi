<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="tabela_botoes">
	<tr>
		<td align="center">
			<div class="tabela_botoes">
			<s:if test="!colecaoDados">
				<s:a class="button btnIncluir" id="BtnIncluir" action="incluir" namespace="/cadastro/motivoDeposito/editar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></s:a>
			</s:if>
				<a class="button btnConsultar" id="BtnConsultar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_consultar.jpg"/>"></a>
			</div>
		</td>
	</tr>
</table>

<table class="tabela_botoes">
	<tr>
		<td align="center"><div id="box_loading"></div></td>
	</tr>
</table>
