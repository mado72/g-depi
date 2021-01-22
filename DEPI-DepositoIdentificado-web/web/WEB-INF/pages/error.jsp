<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta name="titulo" content="Erro interno">
</head>
<body>
<table class="tabela_verm" id="msgErros">
	<tbody>
		<tr>
			<td>
				<ul>
					<li>
						Ocorreu um erro interno. Tente novamente em alguns minutos. 
						Caso o erro persista, favor entrar em contato com o analista responsável e informar o código do erro:
						<p>"<s:text name="exception.message" />".</p>
					</li>
				</ul>
<pre style="display:none" class="error">
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