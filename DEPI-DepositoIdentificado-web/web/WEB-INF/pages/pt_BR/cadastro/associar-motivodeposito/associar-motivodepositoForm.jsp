<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/cadastro/associar-motivodeposito" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar" id="AcaoForm">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
<input name="estado" type="hidden" value="${estado}">
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
			<td class="td_label" width="8%" ><s:text name="label.cadastro.parametrodeposito.cia" /><span class="obrigatorio">*</span>
			</td>
			<td>
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.codigoCompanhia-->
				<s:select list="cias" value="codigoCompanhia" listValue="codigoCompanhia" 
					listKey="codigoCompanhia" cssClass="dropbox w-100 companhia-codigo-dropbox" name="codigoCompanhia"
					disabled="%{detalhar}"/>
			</td>
			<td colspan="2">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<s:select list="cias" value="codigoCompanhia" listValue="descricaoCompanhia" listKey="codigoCompanhia" 
					cssClass="dropbox w-100 companhia-nome-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
 		<tr>
			<!-- combo departamentos -->
			<td class="td_label" ><s:text name="label.cadastro.parametrodeposito.departamento" /><span class="obrigatorio">*</span></td>
			<td>
				<s:select list="deptos" key="codigoDepartamento" listValue="siglaDepartamento" listKey="codigoDepartamento" 
					cssClass="dropbox w-100 departamento-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td colspan="2">
				<s:select list="deptos" value="codigoDepartamento" listValue="nomeDepartamento" listKey="codigoDepartamento" 
					cssClass="dropbox w-100 departamento-nome-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.motivo" /><span class="obrigatorio">*</span></td>
			<td>
				<!-- combo motivo -->
				<s:select key="codigoMotivoDeposito" list="motivos" listValue="descricaoBasica" listKey="codigoMotivoDeposito" 
					cssClass="dropbox w-100" disabled="%{detalhar}"/>
			</td>
			<td>
				<s:textarea key="descricaoDetalhadaMotivo" rows="5" cols="70" value="%{descricaoDetalhadaMotivo}" readonly="true" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.motivodeposito.codigoEventoContabil" /><span class="obrigatorio">*</span></td>
			<td>
				<s:textfield size="6" key="codigoEventoContabil" disabled="true"/>
			</td>
			<td>
				<s:textfield size="50" key="descricaoEventoContabil" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.motivodeposito.itemEventoContabil" /><span class="obrigatorio">*</span></td>
			<td>
				<s:textfield size="6" key="codigoItemContabil" disabled="true"/>
			</td>
			<td>
				<s:textfield size="50" key="descricaoItemContabil" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.banco" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:select list="bancos" key="codBanco" listValue="cdBancoExterno" listKey="cdBancoExterno" 
					cssClass="dropbox w-100 banco-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td>
				<s:select list="bancos" value="codBanco" listValue="descricaoBanco" listKey="cdBancoExterno" 
					cssClass="dropbox w-100 banco-descricao-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.agencia" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:select list="agencias" key="codigoAgencia" listValue="cdAgenciaExterno" listKey="cdAgenciaExterno" 
					cssClass="dropbox w-100 agencia-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td>
				<s:select list="agencias" value="codigoAgencia" listValue="descricaoAgencia" listKey="cdAgenciaExterno" 
					cssClass="dropbox w-100 agencia-descricao-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.contaCorrente" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:select list="contas" key="contaCorrente" listValue="contaCorrente" listKey="contaCorrente"
					cssClass="dropbox w-100 contacorrente-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td>
				<s:textfield size="6" key="contaInterna" disabled="true"/>
			</td>
		</tr>
		
		</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
<c:set var="motivos">{<c:forEach items="${motivos}" var="item">"${item.codigoMotivoDeposito}":"${item.descricaoDetalhada}",</c:forEach>"NULL": "NULL"}</c:set>
<c:set var="contas">{<c:forEach items="${contas}" var="item">"${item.contaCorrente}":"${item.codigoInternoCC}",</c:forEach>"NULL": "NULL"}</c:set>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<c:if test="${! detalhar}">
	<c:url value="/cadastro/associar-motivodeposito/editar/selecionar.do" var="urlSelecionar"/>
	<c:url value="/filtro/associar-motivodeposito/consultar/index.do" var="urlDepartamento">
		<c:param name="action">${urlSelecionar}</c:param>
	</c:url>
<script>
jQuery(document).ready(function($){
		$.associarMotivos.prepararEditar({
			contas : ${contas},
	 		motivos : ${motivos},
			urlCias : '<c:url value="/json/ciaListar.do"/>',
			urlDepto : '<c:url value="/json/ciaDeptosComRestricao.do?codigoCia=%d"></c:url>',
			urlBancos : '<c:url value="/json/ciaBancos.do?codigo.cia=%d"></c:url>',
			urlAgencias : '<c:url value="/json/ciaBancoAgencias.do?codigo.cia=%d&codigo.banco=%d"></c:url>',
			urlContas : '<c:url value="/json/ciaBancoAgenciaConta.do?codigo.cia=%d&codigo.banco=%d&codigo.agencia=%d"></c:url>',
		});
	}(jQuery));
</script>
</c:if>
</c:set>
