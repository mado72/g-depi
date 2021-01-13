<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/WEB-INF/pages/pt_BR/includes/logo-sistema.jsp"></jsp:include>
<br>

<!--mensagem de erro de negocio-->
<logic:messagesPresent message="true">
	<br />
	<table width="90%" border="0" class="tabela_verm" align="center">
		<tr>
			<td align="left">
			<ul>
				<html:messages id="erro" message="true">
					<br>
					<li><bean:write name="erro" /></li>
				</html:messages>
			</ul>
			</td>
		</tr>
	</table>
</logic:messagesPresent>
<table style="width: 90%;" align="center">
	<tr>
		<td class="td_label" align="left">Detalhes do Erro:</td>
	</tr>
	<tr>
		<td align="center">
			<textarea rows="10" style="width:100%;">
				<bean:write	name="mensagem" />
			</textarea>
		</td>
	</tr>
</table>
