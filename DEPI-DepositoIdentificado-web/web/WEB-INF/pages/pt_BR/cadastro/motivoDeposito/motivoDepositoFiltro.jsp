<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

FILTRO

<!--mensagem de erro de negocio-->
<s:if test="hasActionMessages()">
<span id="box_msg_erro">
<br />

<table class="tabela_verm" >
<tr>
	<td align="left" >
	<ul><s:actionerror/></ul>
	</td>
</tr>
</table>
</span>
</s:if>

<s:form action="consultar.do" id="FiltroForm">
<s:hidden key="model.acao"></s:hidden>
<s:hidden key="model.acaoAnterior"></s:hidden>
	<table width="90%" id="tabela_interna" border="0" align="center">
		<tr>
			<td colspan="2" width="50%"></td>
			<td>Critério(s) de Consulta</td>
		</tr>
		<tr>
			<td class="td_label" width="20%">Consultar por </td>
			<td>
				<select id="DropboxPrincipal" class="select"></select>
			</td>
			<td style="width: 400px" rowspan="4">
				<select tabindex="4" id="Lista" multiple size=6>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">Tipo de Operação </td>
			<td>
				<select id="DropboxSecundario" class="select" tabindex="6">
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">Valor </td>
			<td>
				<INPUT id="Valor" tabIndex=3 style="WIDTH: 150px; TEXT-TRANSFORM: uppercase" maxLength=50 size=40>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
			</td>
			<td>
				<a class="button" id="BtnPlus"><img src="<c:url value="/includes/images/plus.gif"/>"></a>
				<a class="button" id="BtnMinus"><img src="<c:url value="/includes/images/minus.gif"/>"></a>
			</td>
		</tr>
	</table>
	<br />
	<table width="95%" class="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
					<a class="button" id="BtnConsultar"><img src="<c:url value="/includes/images/bt_consultar.jpg"/>"></a>
				</div>
			</td>
		</tr>
	</table>
	
	<table style="tabela_botoes" align="center" width="90%">
		<tr>
			<td align="center"><div id="box_loading"></div></td>
		</tr>
	</table>
</s:form>
<c:set var="scriptPage" scope="request">
<script>
jQuery(document).ready(function($){
	var motivos = <c:out value="${model.motivoOperacaoJson}" escapeXml="false"/>;
	
	$.filtro.prepararFormulario(
		"#FiltroForm", 
		{
			principal: motivos.map(function(item){
				return {
					texto: item.motivo.descricao,
					valor: item.motivo.tipo,
					sublista: item.operacoes.map(function(subitem){
						return {
							texto: subitem.descricao,
							valor: subitem.descricao
						};
					})
				};
			}),
			recipiente: []
		}
	);
}(jQuery));
</script>
</c:set>
