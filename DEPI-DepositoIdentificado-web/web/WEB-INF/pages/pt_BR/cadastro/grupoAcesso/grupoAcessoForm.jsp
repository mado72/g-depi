<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/cadastro/grupo-acesso" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar" id="AcaoForm">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="4"><s:text name="label.cadastro.grupoAcesso.tabela" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="td_label" ><s:text name="label.cadastro.grupoacesso.cia" /><span class="obrigatorio">*</span>
			</td>
			<td>
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.codigoCompanhia-->
				<s:select list="cias" value="codCompanhia" listValue="codigoCompanhia" 
					listKey="codigoCompanhia" cssClass="dropbox w-100 companhia-codigo-dropbox" name="codCompanhia"
					disabled="%{codigoGrupoAcesso gt 0}"/>
			</td>
			<td colspan="2">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<s:select list="cias" value="codCompanhia" listValue="descricaoCompanhia" listKey="codigoCompanhia" 
				cssClass="dropbox w-100 companhia-nome-dropbox" disabled="%{codigoGrupoAcesso gt 0}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" ><s:text name="label.cadastro.grupoacesso.departamento" /><span class="obrigatorio">*</span></td>
			<td>
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.codigoCompanhia-->
				<select name="siglaDepartamento" class="dropbox w-100 departamento-codigo-dropbox" disabled="%{codigoGrupoAcesso gt 0}">
					<s:iterator var="depto" value="deptos"><option>${depto.siglaDepartamento}</option></s:iterator>
				</select>
			</td>
			<td colspan="2">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<select class="dropbox w-100 departamento-nome-dropbox" disabled="%{codigoGrupoAcesso gt 0}">
					<s:iterator var="depto" value="deptos"><option value="${depto.siglaDepartamento}">${depto.nomeDepartamento}</option></s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.grupoacesso.funcionario" /><span class="obrigatorio">*</span></td>
			<td colspan="3">
				<div class="btn-control ml4 mt4">
					<c:if test="${detalhar}"><c:set var="btnDesabilitar" value="disabled"/><c:set var="chkDisabled" value="disabled" scope="request"/></c:if>
					<span class="tab">
						<img src="${www3}padroes_web/intranet/imagens/ic_sbox_consultar.gif" alt="Pesquisar" class="pointer btnPesquisar btn ${btnDesabilitar}">
					</span>
					<span class="tab">
						<img src="${www3}padroes_web/intranet/imagens/ic_sbox_sair.gif" alt="Excluir" class="pointer btnRemover btn ${btnDesabilitar}"/>
					</span>
				</div>
				<!-- FUNCIONARIOS -->
<c:set var="matriculaNome" value="true" scope="request"/>
<div class="tblFuncionarios">
<s:include value="/WEB-INF/pages/pt_BR/filtro/tblFuncionario.jsp"/>
</div>
<div id="dvCodigos"></div>
			</td>
		</tr>
<c:if test="${codigoGrupoAcesso gt 0}">
		<tr>
			<td class="td_label"><s:text name="label.cadastro.grupoacesso.nomeGrupo" /><span
				class="obrigatorio">*</span></td>
			<td width="30%"><s:textfield styleId="nomeGrupo"
					name="nomeGrupoAcesso" maxlength="15" disabled="true" /></td>
			<td><s:text name="label.cadastro.grupoacesso.codigoGrupo" /><span
				class="obrigatorio">*</span>&nbsp;</td>
			<td align="left" width="30%"><s:textfield styleId="codigoGrupo"
					name="codigoGrupoAcesso" 
					maxlength="9" size="9" disabled="true" /></td>
		</tr>
</c:if>

		</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>

<script>
jQuery(document).ready(function($){
<c:if test="${! detalhar}">
	$.grupoacesso.prepararEditar({
		urlCias : '<c:url value="/json/ciaListar.do"></c:url>',
		urlDepto : '<c:url value="/json/ciaDepartamentos.do?codigoCia=%d"></c:url>'
	});
	
	$.popupFuncionario.prepararOpener({
		btn: ".btnPesquisar",
		url: '<c:url value="/filtro/funcionario/consultar/listar.do"/>'
	});
</c:if>
}(jQuery));
</script>
</c:set>
