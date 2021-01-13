<%@ page language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<html>
<head>
<meta name="titulo" content="Erro interno">
</head>
<body>
<table width="100%" border="0" cellpadding="5" cellspacing="0" class="tabela_verm" id="msgErros">
	<tbody>
		<tr>
			<td>
				<ul>
					<li>Ocorreu um erro interno. Tente novamente em alguns minutos.<br/><br/>
					Caso o erro persista, favor entrar em contato com o analista responsável e informar o código do erro: "<s:text name="exception.requestId" />".</li>
				</ul>
				<pre style="display:none">
Mensagem:
---------
<s:text name="exception"/>

Stacktrace:
-----------
<s:text name="exceptionStack"/>


				</pre>
			</td>
		</tr>
	</tbody>
</table>
</body>
</html>