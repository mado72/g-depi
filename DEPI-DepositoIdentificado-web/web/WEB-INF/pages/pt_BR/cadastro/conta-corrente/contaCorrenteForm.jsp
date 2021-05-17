<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/cadastro/conta-corrente" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar" id="AcaoForm">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
<c:if test="${desabilitaInputAgenciaConta}">
<s:hidden key="agencia"/><s:hidden key="contaCorrente"/><s:hidden key="trps"/>
</c:if>
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="7"><s:text name="label.cadastro.contacorrenteautorizada.tabela" /></th>
		</tr>
	</thead>
	<tbody>

		<tr>
			<td class="td_label" width="15%" >
				<s:text name="label.cadastro.contacorrenteautorizada.cia" /><span class="obrigatorio">*</span>
			</td>
			<td width="10%">
				
				<s:select list="cias" value="codigoCompanhia" listValue="codigoCompanhia" 
					listKey="codigoCompanhia" cssClass="dropbox w-100 companhia-codigo-dropbox" name="codigoCompanhia"
					disabled="%{detalhar}"/>
			</td>
			<td width="40%">
				
				<s:select list="cias" value="codigoCompanhia" listValue="descricaoCompanhia" listKey="codigoCompanhia" 
					cssClass="dropbox w-100 companhia-nome-dropbox" disabled="%{detalhar}"/>
			</td>
			<td colspan="4" rowspan="3">
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.banco" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:textfield key="codigoBanco" readonly="true" size="4" maxlength="3" disabled="detalhar"/>
			</td>
			<td>
				${descricaoBanco}
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.agencia" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:textfield key="agencia" size="7" maxlength="5" disabled="desabilitaInputAgenciaConta"/>
			</td>
			<td>
				<span id="descricaoAgencia">${descricaoAgencia}</span>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.contaCorrente" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:textfield key="contaCorrente" size="13" maxlength="15" disabled="desabilitaInputAgenciaConta"/>
			</td>
			<td></td>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.contaInternaCC" />
			</td>
			<td>
				<span id="contaInternaCC">${contaInterna}</span>
			</td>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.trps" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:textfield key="trps" size="10" maxlength="10" disabled="desabilitaInputAgenciaConta"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.historico" />
			</td>
			<td colspan="3">
				<s:textarea key="observacao" rows="5" cols="70" readonly="detalhar" style="TEXT-TRANSFORM: uppercase"/>
			</td>
			<td colspan="3"></td>
		</tr>
		</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
<%--
<c:set var="motivos">{<c:forEach items="${motivos}" var="item">"${item.codigoMotivoDeposito}":"${item.descricaoDetalhada}",</c:forEach>"NULL": "NULL"}</c:set>
 --%>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
 	$.contaCorrente.prepararFormulario({
		urlAgencia : '<c:url value="/json/agencia.do"/>',
		urlContaInterna : '<c:url value="/json/contaInterna.do"></c:url>'
 	});
}(jQuery));
</script>
</c:set>
