<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<s:form action="consultar.do" id="FiltroForm">
	<table id="tabela_interna">
		<tr>
			<td colspan="2" width="50%"></td>
			<td>
				<s:text name="label.filtro.criterio"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" width="20%">
				<s:text name="label.filtro.consultaPor"/>
			</td>
			<td>
				<select id="DropboxPrincipal" class="select"></select>
			</td>
			<td style="width: 400px" rowspan="4">
				<select tabindex="4" id="Lista" multiple size=6>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.filtro.tipoOperacao"/>
			</td>
			<td>
				<select id="DropboxSecundario" class="select" tabindex="6">
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.filtro.valor"/>
			</td>
			<td>
				<INPUT id="Valor" tabIndex=3 style="WIDTH: 150px; TEXT-TRANSFORM: uppercase" maxLength=50 size=40>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
			</td>
			<td>
				<a class="button" id="BtnPlus"><img src="<c:url value="${www3}padroes_web/intranet/imagens/plus.gif"/>"></a>
				<a class="button" id="BtnMinus"><img src="<c:url value="${www3}padroes_web/intranet/imagens/minus.gif"/>"></a>
			</td>
		</tr>
	</table>
</s:form>
<c:set var="scriptPage" scope="request">
<script>
jQuery(document).ready(function($){
	var parametros = <c:out value="${parametrosFiltroJson}" escapeXml="false"/>;
	
	var principal = [];
	$.each(parametros, function(idx, item) {
		var operacoes = [];
		$.each(item.operacoes, function(idxOp, subitem) {
			operacoes[idxOp] = {texto: subitem.d, valor: subitem.n};
		});
		principal[idx] = {
			texto: item.entidade.descricao,
			valor: item.entidade.campo,
			sublista: operacoes
		};
	});
	
	$.filtro.prepararFormulario(
		"#FiltroForm", 
		{
			principal: principal,
			recipiente: <c:out value="${recipienteListJson}" default="[]" escapeXml="false"/>
		}
	);
}(jQuery));
</script>
</c:set>
