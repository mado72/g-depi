<%@ page language="java" contentType="text/html; charset=UTF-8"%><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
	taglib prefix="s" uri="/struts-tags" %>
<%-- <img src="<c:url value="${www3}padroes_web/intranet/imagens/carregando.gif"/>" class="carregandoImg"/>--%>
<div id="depi-breadcrumb">
<s:url value="/includes/images/logo-depi-14.png" var="logoImg"/><img src="${logoImg}" class="logo"/>
<!--subtitulo da funcionalidade-->
<span id="subtitulo" class="subtitulo"><c:out value="${subtitulo}" /></span><br/>
</div>
