<%@ page language="java" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@ 
	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %><%@ 
	taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" 
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><decorator:title default="DEPI-DepositoIdentificado"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="${www3}padroes_web/intranet/css/tabs.css" rel="stylesheet" type="text/css"/>
	<link href="${www3}padroes_web/intranet/css/css.css" rel="stylesheet" type="text/css"/>
	<link href="${www3}padroes_web/intranet/css/Menu_horizontal.css" rel="stylesheet" type="text/css" />
	<link href="<s:url value="/includes/css/depi.css"/>" rel="stylesheet" type="text/css" />
 	
	<script type="text/javascript" src="<s:url value="/includes/js/jquery-1.9.1.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery-ui.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery.dateFormat-1.0.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar-br.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar-setup.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/tabs.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/dinamico/mensagens.jsp"/>"></script>
	<script type="text/javascript" src="${www3}padroes_web/intranet/js/bradesco_menu.js"></script>

 <!--
    	<script type="text/javascript" src="<s:url value="/includes/js/lib.js"/>"></script>
	<script type="text/javascript" src="<s:url value='/includes/js/jquery-1.10.2.min.js'/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery.numberLetters.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/comum.js"/>"></script>
 -->	
	<decorator:head />
</head>
<body id="depi-app">
<table id="tabela_principal">
	<tr>
		<td>
			<br />
			<s:if test="hasFieldErrors()">
				<table width="100%" border="0" cellpadding="5" cellspacing="0" class="tabela_verm" id="msgErros">
					<tbody>
						<tr>
							<td><s:iterator value="fieldErrors">
								<li><s:property value="value[0]" /></li>
							</s:iterator></td>
						</tr>
					</tbody>
				</table>
			</s:if>
			<s:if test="hasActionErrors()">
				<table width="100%" border="0" cellpadding="5" cellspacing="0" class="tabela_verm" id="msgErros">
					<tbody>
						<tr>
							<td><s:iterator value="actionErrors">
								<li><s:property /></li>
							</s:iterator></td>
						</tr>
					</tbody>
				</table>
			</s:if>
			<s:if test="hasActionMessages()">
				<table width="100%" border="0" cellpadding="5" cellspacing="0" class="tabela_sucesso" id="msgSucesso">
					<tbody>
						<tr>
							<td><s:iterator value="actionMessages">
								<li><s:property /></li>
							</s:iterator></td>
						</tr>
					</tbody>
				</table>
			</s:if>
			<br />
			<s:include value="/WEB-INF/pages/layout/menu.jsp"/>
			<br />
			<decorator:body />
		</td>
	</tr>
</table>
<s:if test="hasActionMessages()">
	<script>
		$("#msgSucesso").fadeOut(7000);
	</script>
</s:if>
<c:if test="${param.scriptOff != 'true'}">
	<script type="text/javascript" src="<s:url value="/includes/script/depi-cadastro.js"/>" charset="utf-8"></script>
	<c:out value="${scriptPage}" default="" escapeXml="false" />
</c:if>
</body>
</html>
