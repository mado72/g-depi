<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/cadastro/grupo-acesso" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar">
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
			<td class="td_label" ><s:text name="label.cadastro.grupoacesso.cia" /><span class="obrigatorio">*</span></td>
			<td>
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.codigoCompanhia-->
				<select name="cia.codigoCompanhia" class="dropbox companhia-codigo-dropbox">
					<s:iterator var="cia" value="cias"><option>${cia.codigoCompanhia}</option></s:iterator>
				</select>
			</td>
			<td colspan="2">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<select class="dropbox companhia-nome-dropbox">
					<s:iterator var="cia" value="cias"><option value="${cia.codigoCompanhia}">${cia.descricaoCompanhia}</option></s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label" ><s:text name="label.cadastro.grupoacesso.departamento" /><span class="obrigatorio">*</span></td>
			<td>
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.codigoCompanhia-->
				<select name="depto.codigoDepartamento" class="dropbox departamento-codigo-dropbox">
					<s:iterator var="depto" value="deptos"><option>${depto.siglaDepartamento}</option></s:iterator>
				</select>
			</td>
			<td colspan="2">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<select class="dropbox departamento-nome-dropbox">
					<s:iterator var="depto" value="deptos"><option value="${depto.siglaDepartamento}">${depto.nomeDepartamento}</option></s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.grupoacesso.funcionario" /><span class="obrigatorio">*</span></td>
			<td colspan="3">
				<div class="btn-control">
					<input type="checkbox" class="optionbutton checkTodos" disabled="${detalhar}" />
					<s:text name="label.todos"/>
					<img src="${www3}padroes_web/intranet/imagens/ic_sbox_consultar.gif" alt="Pesquisar">
					<s:if test="detalhar">
						<s:else>
							<img src="${www3}padroes_web/intranet/imagens/ic_sbox_sair.gif" alt="Excluir"/>
						</s:else>
					</s:if>
				</div>
				<!-- TODO INCLUIR LISTA FUNCIONARIOS -->
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.grupoacesso.nomeGrupo" /><span
				class="obrigatorio">*</span></td>
			<td width="30%"><s:textfield styleId="nomeGrupo"
					property="nomeGrupoAcesso" maxlength="15" disabled="detalhar" /></td>
			<td><s:text name="label.cadastro.grupoacesso.codigoGrupo" /><span
				class="obrigatorio">*</span>&nbsp;</td>
			<td align="left"><s:textfield styleId="codigoGrupo"
					property="codigoGrupoAcesso" 
					maxlength="9" size="9" disabled="detalhar" /></td>
		</tr>
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
</c:if>
}(jQuery));
</script>
</c:set>