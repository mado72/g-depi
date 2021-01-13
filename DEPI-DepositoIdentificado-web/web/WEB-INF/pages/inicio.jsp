<%@ page language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta name="titulo" content="DEPI-DepositoIdentificado" />
<meta name="subtitulo" content="Início" />
</head>
<body>
<p class="destaque">Importante, esta funcionalidade é apenas uma
referência para o desenvolvimento, e deve ser removida da aplicação.</p>
<p class="destaque">O pacote br.com.bradseg.depi.depositoidentificado.funcao deve ser removido antes de uma entrega de EAR.</p>
<br/>
<p class="destaque">Para testar, preencha com os nomes: 'bsp', ou 'bsad', ou 'fulano' e veja os diferentes comportamentos da aplicação. Os nomes 'bsp' e 'bsad' geram respectivamente BusinessException e IntegrationException.</p>
<br/>
<table>
	<thead>
		<tr>
			<th>Nome</th><th>Efeito</th>
		</tr>
	</thead>
	<tbody>
		<tr><td>bsp</td><td>Ocorre de forma proposital, um erro de negócio na camada de serviço, mas que será tratada pela action e exibida como mensagem de erro. (BusinessException)</td></tr>
		<tr><td>bsad</td><td>Ocorre de forma proposital, um erro de integração na camada de serviço, que não será tratado por essa action, e será redirecionado para uma página de erro. (IntegrationException)</td></tr>
		<tr><td>fulano</td><td>Exibe uma mensagem específica para este nome.</td></tr>
		<tr><td>(qualquer outro nome)</td><td>Exibe uma mensagem padrão para qualquer outro nome não cadastrado.</td></tr>
	</tbody>
</table>
<br/>

<s:form action="consultarSaudacao">
	<table width="30%" class="tabela_interna">
		<tr>
			<th colspan="2">Saudação</th>
		</tr>
		<tr>
			<td width="120">Nome: <span class="obrigatorio">*</span></td>
			<td><s:textfield name="nome" maxlength="200" size="30" /></td>
		</tr>
	</table>
	<table id="tabela_botoes" cellspacing="0" cellpadding="0" border="0"
		width="30%">
		<tr align="center">
		<td>
			<s:submit value="Consultar" cssClass="margem_botoes button" />
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>