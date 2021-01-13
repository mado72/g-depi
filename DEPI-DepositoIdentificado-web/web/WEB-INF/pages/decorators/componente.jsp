<%@ page language="java" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" 
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${param.scriptOff != 'true'}">
<head>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery-1.9.1.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery-ui.min.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/jquery.dateFormat-1.0.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar-br.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/calendar-setup.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/js/tabs.js"/>"></script>
	<script type="text/javascript" src="<s:url value="/includes/dinamico/mensagens.jsp"/>" charset="utf-8"></script>
</head>
</c:if>
<decorator:body />
<c:if test="${param.scriptOff != 'true'}">
	<script type="text/javascript" src="<s:url value="/includes/script/depi-cadastro.js"/>" charset="utf-8"></script>
	<c:out value="${scriptPage}" default="" escapeXml="false" />
</c:if>
