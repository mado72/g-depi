<%@ page language="java" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
	taglib prefix="s" uri="/struts-tags" %>
<img src="<c:url value="/includes/images/carregando.gif"/>" id="carregandoImg"/>
<%-- 
	<frw:image midiaAcesso="intranet" complement="/imagens/tit_depi_dp06.gif"/><br>
--%>
<!--subtitulo da funcionalidade-->
<span id="subtitulo" class="subtitulo"><c:out value="${subtitulo}" /></span><br/>
<%--
	<frw:image style="visibility: hidden;" complement="/imagens/carregando.gif" midiaAcesso="intranet"/>
	<input type="hidden" id="url_servidor_img" value="<frw:image complement="/imagens" midiaAcesso="intranet" tagCompleta="false" />" />
--%>