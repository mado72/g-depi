<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>

<!--mensagem de erro de negocio-->
<logic:messagesPresent message="true">
<span id="box_msg_erro">
<br />
<table width="90%" border="0" class="tabela_verm" align="center" >
<tr>
	<td align="left" >
	<ul>
	<html:messages id="erro" message="true">
	<br><li><bean:write name="erro" /></li>
	</html:messages>
	</ul>
	</td>
</tr>
</table>
</span>
</logic:messagesPresent>

	<table id="tabela_interna" align="center" width="30%">
		<tr><th align="center"><b>Bradesco Seguros e Previd�ncia</b></th></tr>
		<tr><td><b>Sistema: DEPI DepositoIdentificado</b></td></tr>
		<tr><td width="120px"><b>Vers�o: 1.2</b></td></tr>
		<tr><td><b>Última Atualização: <bean:write name="dataAlteracao" /></b></td></tr>
	</table>

