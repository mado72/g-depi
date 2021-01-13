<%@ page contentType="text/javascript; charset=UTF-8" 
                         pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%> 
const PAGE_CONTEXT = '<c:out value="${pageContext.request.contextPath}" />';

const MENSAGEM = {
	"msg.selecao.edicao": '<s:text name="msg.selecao.edicao"/>',
	"msg.confirmacao.exclusao": '<s:text name="msg.confirmacao.exclusao"/>',
	"msg.selecao.exclusao": '<s:text name="msg.selecao.exclusao"/>',
}
