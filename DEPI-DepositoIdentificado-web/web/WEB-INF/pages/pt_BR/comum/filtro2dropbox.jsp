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
				<select id="DropboxPrincipal" tabindex="1" class="select"></select>
			</td>
			<td style="width: 400px" rowspan="4">
				<select tabindex="5" id="Lista" multiple size=6>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.filtro.tipoOperacao"/>
			</td>
			<td>
				<select id="DropboxSecundario" class="select" tabindex="2" style="WIDTH: 150px" >
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.filtro.valor"/>
			</td>
			<td>
				<INPUT id="ValorFiltro" tabIndex="3" style="WIDTH: 150px; TEXT-TRANSFORM: uppercase" maxLength=50 size=40>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
			</td>
			<td>
				<button class="abtn abtn-xs" id="BtnPlus" tabindex="4"><img src="<c:url value="${www3}padroes_web/intranet/imagens/plus.gif"/>"></button>
				<button class="abtn abtn-xs" id="BtnMinus" tabindex="6"><img src="<c:url value="${www3}padroes_web/intranet/imagens/minus.gif"/>"></button>
			</td>
		</tr>
	</table>

${btnAction}<c:if test="${empty btnAction}"><s:include value="/WEB-INF/pages/pt_BR/comum/incluir-consultar.jsp"></s:include></c:if>

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
			tipo: item.entidade.tipo,
			tam: item.entidade.s,
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
