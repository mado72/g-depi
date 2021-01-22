<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<frw:css midiaAcesso="intranet" complement="/css/tabs.css"></frw:css>
<frw:css midiaAcesso="intranet" complement="/css/css.css"></frw:css>
<frw:css midiaAcesso="intranet" complement="/css/Menu_horizontal.css"></frw:css>
<script src='<html:rewrite page="/includes/js/funcoes.js"/>'></script>
<script src='<html:rewrite page="/includes/script/arquivo.js"/>'></script>
<c:if test="${empty requestScope.titulo}">
	<title>BS.DP06.IdentificarDepositos.MenuPrincipal</title>
</c:if>
<c:if test="${not empty requestScope.titulo}">
	<title>BS.DP06.<c:out value="${requestScope.titulo}" /></title>
</c:if>
</head>
<body>
<script type="text/javascript">
	var webRoot = '<c:out value="${pageContext.request.contextPath}" />';
</script>
<table width="100%" id="tabela_principal" align="center">
	<tr>
		<td>
			<tiles:insert attribute="param-include-menu" />
			<frw:javascript midiaAcesso="intranet" complement="/js/bradesco_menu.js"></frw:javascript>
		</td>
	</tr>
	<tr>
		<td><tiles:getAsString name="param-string-instrucoes" /></td>
	</tr>
	<tr>
		<td><tiles:insert attribute="param-include-corpo" /></td>
	</tr>
</table>
</body>
</html>
