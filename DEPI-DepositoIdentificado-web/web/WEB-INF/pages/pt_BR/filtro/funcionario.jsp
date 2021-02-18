<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceBase" scope="request">/filtro/funcionario</c:set>
<c:set var="btnAction" scope="request">
<div class="botoes_filtro">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
					<button class="abtn btnConsultar" id="BtnConsultar" type="submit"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_consultar.gif"/>"></button>
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
</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>


<s:if test="!consultado">
<c:set var="AcaoFormStyle" value="style='display: none'"/>
</s:if>

<div ${AcaoFormStyle}>
<form action="${action}" id="AcaoForm" method="post" target="_opener">
<input type="hidden" name="codCompanhia" value="${codCompanhia}"/>
<input type="hidden" name="siglaDepartamento" value="${siglaDepartamento}"/>
<c:set var="funcionarios" value="${colecaoDados}" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/filtro/tblFuncionario.jsp"/>
<div class="paginacao"></div>
<div class="center">
	<button type="submit" id="BtnSelecionar" class="btn-img">
		<img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_selecionar.gif"/>" alt="<s:text name="label.selecionar"/>">
	</button>
</div>
</form>
</div>

<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.consulta.prepararFormulario("#AcaoForm");
	<s:if test="colecaoDados">
	$.paginacao.paginar({
		tblSeletor: ".Funcionario",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	
	$.funcionario.prepararFormulario({dest: "#AcaoForm", table: ".Funcionario", origem: "#AcaoForm"});
	</s:if>
}(jQuery));
</script>
</c:set>
