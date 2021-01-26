<%@ page language="java" %><%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@
	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@
	taglib prefix="s" uri="/struts-tags" %><%@
	taglib prefix="depi" uri="/depi-tags" %>
<s:if test="hasActionMessages()">
	<!-- mensagens motivoDepositoForm -->
	<table class="tabela_sucesso" id="msgSucesso">
		<tbody>
			<tr>
				<td><ul><s:iterator value="actionMessages">
					<li><s:property /></li>
				</s:iterator></ul></td>
			</tr>
		</tbody>
	</table>
	<depi:clearMessages messages="true"/>
</s:if>
<s:if test="hasFieldErrors()">
	<!-- field errors motivoDepositoForm -->
	<table class="tabela_verm" id="msgErros">
		<tbody>
			<tr>
				<td><ul><s:iterator value="fieldErrors">
					<li><s:property value="value[0]" /></li>
				</s:iterator></ul></td>
			</tr>
		</tbody>
	</table>
	<depi:clearMessages fieldErrors="true"/>
</s:if>
<%-- 
<s:if test="hasActionErrors()">
	<!-- action errors motivoDepositoForm -->
	<table class="tabela_verm" id="msgErros">
		<tbody>
			<tr>
				<td><ul><s:actionerror/></ul></td>
			</tr>
		</tbody>
	</table>
	<depi:clearMessages actionErrors="true"/>
</s:if>
 --%>