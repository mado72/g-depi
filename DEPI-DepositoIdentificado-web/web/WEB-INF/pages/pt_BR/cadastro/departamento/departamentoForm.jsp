<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url scope="request" var="namespaceBase" value="/cadastro/departamento"></c:url>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="4"><s:text name="label.cadastro.departamento.tabela" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="td_label" ><s:text name="label.grid.departamento.siglaDepartamento" /><span class="obrigatorio">*</span></td>
			<td colspan="3" >
				<s:textfield tabindex="1" key="siglaDepartamento" styleClass="input" style="text-transform: uppercase;"  maxlength="3" size="26" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.grid.departamento.nomeDepartamento" /><span class="obrigatorio">*</span></td>
			<td colspan="3">
				<s:textarea key="nomeDepartamento" rows="5" tabindex="2" cols="70" style="text-transform: uppercase;" readonly="detalhar"/>
			</td>
		</tr>
	</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>