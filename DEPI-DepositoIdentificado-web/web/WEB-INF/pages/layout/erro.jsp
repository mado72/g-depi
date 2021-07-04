<%@ page language="java" contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%@ 
	taglib prefix="s" uri="/struts-tags"%><%@ 
	taglib prefix="depi" uri="/depi-tags" %>

<s:if test="hasActionErrors()">
	<table class="tabela_verm">
		<tr>
			<td align="left">
				<s:actionerror/>
			</td>
		</tr>
	</table>
	<depi:clearMessages actionErrors="true"/>
</s:if>
