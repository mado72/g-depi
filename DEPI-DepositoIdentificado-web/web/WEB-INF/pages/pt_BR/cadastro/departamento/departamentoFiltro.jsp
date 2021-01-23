<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
				<a class="button" id="BtnPlus"><img src="<c:url value="${www3}padroes_web/intranet/imagens/plus.gif"/>"></a>
				<a class="button" id="BtnMinus"><img src="<c:url value="${www3}padroes_web/intranet/imagens/minus.gif"/>"></a>
			</td>
		</tr>
	</table>
	<br />
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
				<s:if test="!colecaoDados">
					<s:a action="incluir" class="button" id="BtnIncluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.jpg"/>"></s:a>
				</s:if>
					<a role="button" class="button" id="BtnConsultar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_consultar.jpg"/>"></a>
				</div>
			</td>
		</tr>
	</table>
	
	<table class="tabela_botoes">
		<tr>
			<td align="center"><div id="box_loading"></div></td>
		</tr>
	</table>
</s:form>
<c:set var="scriptPage" scope="request">
<script>
jQuery(document).ready(function($){
	var parametros = <c:out value="${parametrosFiltroJson}" escapeXml="false"/>;
	
	$.filtro.prepararFormulario(
		"#FiltroForm", 
		{
			principal: parametros.map(function(item){
				return {
					texto: item.entidade.descricao,
					valor: item.entidade.campo,
					sublista: item.operacoes.map(function(subitem){
						return {
							texto: subitem.d,
							valor: subitem.n
						};
					})
				};
			}),
			recipiente: <c:out value="${recipienteListJson}" default="[]" escapeXml="false"/>
		}
	);
}(jQuery));
</script>
</c:set>
