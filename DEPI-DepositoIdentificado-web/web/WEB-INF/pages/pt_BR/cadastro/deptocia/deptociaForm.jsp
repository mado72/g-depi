<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/cadastro/departamento-companhia" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar" id="AcaoForm">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="4"><s:text name="label.cadastro.departamentocompanhia.tabela" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="td_label" width="8%" ><s:text name="label.cadastro.departamentocompanhia.companhia" /><span class="obrigatorio">*</span>
			</td>
			<td width="10%">
				<s:select list="cias" value="codCompanhia" listValue="codigoCompanhia" 
					listKey="codigoCompanhia" cssClass="dropbox w-100 companhia-codigo-dropbox" name="codCompanhia"
					disabled="desabiltarChave"/>
			</td>
			<td colspan="2" width="82%">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<s:select list="cias" value="codCompanhia" listValue="descricaoCompanhia" listKey="codigoCompanhia" 
					cssClass="dropbox w-100 companhia-nome-dropbox" disabled="desabiltarChave"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.departamentocompanhia.departamento" /><span class="obrigatorio">*</span></td>
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
				<!-- DEPARTAMENTOS -->
<c:set value="${deptos}" var="deptos" scope="request"/>
<div class="tblDepartamento">
<s:include value="/WEB-INF/pages/pt_BR/filtro/tblDepartamento.jsp"/>
</div>
<div id="dvCodigos"></div>
			</td>
		</tr>
		</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<c:if test="${! detalhar}">
	<c:url value="/cadastro/departamento-companhia/editar/selecionar.do" var="urlSelecionar"/>
	<c:url value="/filtro/departamento-companhia/consultar/index.do" var="urlDepartamento">
		<c:param name="action">${urlSelecionar}</c:param>
	</c:url>
<script>
jQuery(document).ready(function($){
		$.deptoCia.prepararEditar({
			urlCias : '<c:url value="/json/ciaListar.do"/>',
		});
		
		$.popupDepto.prepararOpener({
			btn: ".btnPesquisar",
			url: '${urlDepartamento}'
		});
	}(jQuery));
</script>
</c:if>
</c:set>
