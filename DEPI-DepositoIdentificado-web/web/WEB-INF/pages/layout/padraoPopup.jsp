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
<frw:css midiaAcesso="includes/css/" complement="css.css"></frw:css>
<frw:css midiaAcesso="includes/css/" complement="menu.css"></frw:css>
<frw:css midiaAcesso="includes/css/" complement="tabs.css"></frw:css>
<frw:javascript midiaAcesso="includes/js/" complement="funcoes.js"></frw:javascript>
<frw:javascript midiaAcesso="includes/js/" complement="bradesco-menu.js"></frw:javascript>
<script type="text/javascript" src="<c:out value="${pageContext.request.contextPath}" />/includes/script/arquivo.js"></script>
<c:if test="${empty requestScope.titulo}">
<title>
<tiles:getAsString name="documentTitle" />
</title>
</c:if>
<c:if test="${not empty requestScope.titulo}">
<title>BS.DepositoIdentificado.<c:out value="${requestScope.titulo}" /></title>
</c:if>
</head>
<body>
<script type="text/javascript">
var webRoot = '<c:out value="${pageContext.request.contextPath}" />';
</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#e42d27"><frw:image midiaAcesso="includes/imagens/" complement="logomarca.jpg" width="284" height="44" /></td>
    <td align="right" bgcolor="#e42d27"><frw:image midiaAcesso="includes/imagens/" complement="trama1.jpg" width="333" height="44" /></td>
  </tr>
  <tr>
    <td bgcolor="#a80000">&nbsp;</td>
    <td align="right" bgcolor="#a80000"><frw:image midiaAcesso="includes/imagens/" complement="tronco.jpg" width="56" height="24" /></td>
  </tr>
</table>
<tiles:insert attribute="param-include-menu" />
<%--
<!--subtitulo da funcionalidade-->
<tiles:insert attribute="param-include-subtitulo" />
<!--mensagem de erro de negocio-->
<logic:messagesPresent message="true">
<br />
<table width="90%" border="0" class="tabela_verm">
	<tr>
		<td align="left">
		<ul>
		<html:messages id="erro" message="true">
		<br><li><bean:write name="erro" /></li>
		</html:messages>
		</ul>
		</td>
	</tr>
</table>
</logic:messagesPresent>
--%>
<table width="100%" id="tabela_principal" align="center">
<tr>
<td>
<!-- instrucoes -->
<p><tiles:getAsString name="param-string-instrucoes" /> </p>
<tiles:insert attribute="param-include-corpo" />
</td>
</tr>
</table>
<%--
<logic:messagesPresent message="true">
<html:messages id="erro" message="true">
<script type="text/javascript">
setTimeout('alert(\'<bean:write name="erro" />\')', 100);
</script>
</html:messages>
</logic:messagesPresent>
--%>
</body>
</html>
