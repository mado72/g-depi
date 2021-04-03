<%@ page language="java" %><%@
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><%@
	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%@
	taglib prefix="s" uri="/struts-tags" %><%@
	taglib prefix="depi" uri="/depi-tags" %><%@
	taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %><%@
	taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" 
%><!DOCTYPE HTML>
<!--[if lt IE 7]> 
<html class="lt-ie9 lt-ie8 lt-ie7">
<![endif]-->
<!--[if IE 7]>
<html class="lt-ie9 lt-ie8">
<![endif]-->
<!--[if IE 8]>
<html class="lt-ie9">
<![endif]-->
<!--[if gt IE 8]><!-->
<html>
<!--<![endif]-->
<head>
	<META http-equiv="X-UA-Compatible" content="IE=9; IE=10; IE=11">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title><decorator:title default="DEPI-DepositoIdentificado"/></title>
	<link href="${www3}padroes_web/intranet/css/tabs.css" rel="stylesheet" type="text/css"/>
	<link href="${www3}padroes_web/intranet/css/css.css" rel="stylesheet" type="text/css"/>
	<link href="<s:url value="/includes/calendar/theme.css"/>" rel="stylesheet" type="text/css" />
	<link href="${www3}padroes_web/intranet/css/Menu_horizontal.css" rel="stylesheet" type="text/css" />
	<link href="<s:url value="/includes/css/depi.css"/>" rel="stylesheet" type="text/css" />
 	
	<script type="text/javascript" src="<s:url value="/includes/js/jquery-1.9.1.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery-ui.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery.mask.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery.dateFormat-1.0.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar-br.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar-setup.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/tabs.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/mensagens.js"/>"></script>
	<script type="text/javascript" src="${www3}padroes_web/intranet/js/bradesco_menu.js"></script>

<style>
#box_loading {
	background-image: url("${www3}padroes_web/intranet/imagens/carregando.gif");
}
</style>
	<decorator:head />
</head>
<body id="depi-app">

<table id="tabela_principal">
	<tr>
		<td>
			<s:include value="/WEB-INF/pages/layout/menu.jsp"/>
<%--
			<div id="Mensagens">
			<s:if test="hasActionMessages()">
				<!-- mensagens layout -->
				<table class="tabela_sucesso" id="msgSucesso">
					<tbody>
						<tr>
							<td><ul><s:iterator value="actionMessages">
								<li><s:property escapeHtml="false"/></li>
							</s:iterator></ul></td>
						</tr>
					</tbody>
				</table>
				<depi:clearMessages messages="true"/>
			</s:if>
			<s:if test="hasFieldErrors()">
				<!-- field errors layout -->
				<table class="tabela_verm" id="msgErros">
					<tbody>
						<tr>
							<td><ul><s:iterator value="fieldErrors">
								<li><s:property escapeHtml="false" value="value[0]" /></li>
							</s:iterator></ul></td>
						</tr>
					</tbody>
				</table>
				<depi:clearMessages fieldErrors="true"/>
			</s:if>
			<s:if test="hasActionErrors()">
				<!-- action errors layout -->
				<table class="tabela_verm" id="msgErros">
					<tbody>
						<tr>
							<td><ul><s:actionerror escape="false"/></ul></td>
						</tr>
					</tbody>
				</table>
				<depi:clearMessages actionErrors="true"/>
			</s:if>
			</div>
 --%>
			<div id="logoSistema">
			<s:include value="/WEB-INF/pages/pt_BR/includes/logo-sistema.jsp"/>
			</div>
			<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
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
	<script type="text/javascript" src="<c:url value="/includes/js/depi-cadastro.js"/>" charset="utf-8"></script>
	<c:out value="${scriptPage}" default="" escapeXml="false" />
</c:if>

</body>
</html>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
