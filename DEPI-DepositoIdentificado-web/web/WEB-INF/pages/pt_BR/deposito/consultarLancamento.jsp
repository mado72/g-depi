<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:set var="formAction"><s:property value="salvarAction"/>.do</s:set>
<c:set var="namespaceBase" scope="request">/deposito/movimento</c:set>

<div class="obrigatorio" style="margin-left: 5%"><s:text name="label.campos.obrigatorios"/></div>

<s:form id="AcaoForm" action="%{formAction}"  namespace="namespaceBase">
	<table id="tabela_interna" >
		<tr>
			<th colspan="6" class="tabela_topo">Dados do Lançamento do Depósito</th>
		</tr>
		<tr>
			<td class="td_label" rowspan="3" >Detalhes do Depósito</td>
			<td class="td_label" >Cia</td>
			<td class="text-right">
				<s:textfield key="codigoCompanhia" cssClass="text-right uppercase" maxlength="4" size="4"/>
			</td>
			<td colspan="3">
				<s:textfield key="descricaoCompanhia" cssClass="uppercase" maxlength="50" size="60"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" >Departamento</td>
			<td>
				<s:textfield key="siglaDepartamento" cssClass="text-right uppercase" maxlength="3" size="3"/>
			</td>
			<td colspan="3">
				<s:textfield key="nomeDepartamento" cssClass="uppercase" maxlength="50" size="60"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" >Motivo</td>
			<td>
				<s:textfield key="codigoMotivoDeposito" cssClass="text-right uppercase" maxlength="3" size="3"/>
			</td>
			<td colspan="3">
				<s:textarea key="descricaoMotivoDetalhada" cssClass="uppercase" rows="4" cols="50" readonly="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" rowspan="4">Detalhes da Conta</td>
			<td class="td_label">
				Banco
			</td>
			<td>
				<s:textfield key="codBanco" cssClass="text-right uppercase" size="4" disabled="true"/>
			</td>			
			<td colspan="3">
				<s:textfield key="nomeBanco" cssClass="uppercase" size="60" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">Agência</td>
			<td>
				<s:textfield key="codAgencia" cssClass="text-right uppercase" size="5" disabled="true"/>
			</td>			
			<td colspan="3">
				<s:textfield key="nomeAgencia" cssClass="uppercase" size="60" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">Conta Corrente</td>
			<td colspan="4">
				<s:textfield key="contaCorrente" cssClass="uppercase" size="12" disabled="true"/>
			</td>		
			
		</tr>
		<tr>
			<td class="td_label">Agência Acolhedora</td>
			<td>
				<s:textfield key="agenciaAcolhedora" cssClass="uppercase" size="6" disabled="true"/>
			</td>
			<td colspan="3">
				<s:textfield key="nomeAgenciaAcolhedora" cssClass="uppercase" size="60" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" rowspan="8">Valor Depositado</td>
			<td class="td_label">Dinheiro</td>
			<td colspan="4">
				<s:textfield key="valorDinheiro" size="18" cssClass="text-right" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" rowspan="5">Cheque</td>
			<td class="td_label">Banco</td>
			<td style="width: 20%;" >
				<s:textfield key="bancoCheque" size="3" cssClass="text-right" disabled="true"/>
			</td>
			<td colspan="2">
				<s:textfield key="nomeBancoCheque" size="60" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">Agência</td>
			<td>
				<s:textfield key="agenciaCheque" size="5" cssClass="text-right" disabled="true"/>
			</td>
			<td colspan="2">
				<s:textfield key="nomeAgenciaCheque" size="60" cssClass="text-right" disabled="true"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">Nº Cheque</td>
			<td colspan="3">
				<s:textfield key="cheque" size="15" disabled="true"/>
				<%-- <input type="text" disabled="disabled"  value="<bean:write name="lancamento" property="cheque" format="###############"/>"/> --%>
			</td>			
		</tr>		
		<tr>
			<td class="td_label">Conta Corrente</td>
			<td colspan="3">
				<s:textfield key="contaCheque" size="15" disabled="true"/>
				<%-- <input type="text" disabled="disabled" value="<bean:write name="lancamento" property="contaCheque" format="############"/>"/> --%>
			</td>
		</tr>
		<tr>
			<td class="td_label">Valor do Cheque</td>
			<td colspan="3">
				<s:textfield key="valorCheque" cssClass="text-right" size="15" disabled="true"/>
				<%-- <input type="text" disabled="disabled"  value="<bean:write name="lancamento" property="valorCheq" format="#,###,##0.00"/>"/> --%>
			</td>		
		</tr>
		<tr>
			<td class="td_label">Total</td>
			<td colspan="4">
				<s:textfield key="valorTotalDeposito" cssClass="text-right" size="15" disabled="true"/>
				<%-- <input disabled="disabled" type="text" value="<bean:write name="lancamento" property="valorTotalDeposito" format="#,###,##0.00"/>"/> --%>
			</td>
		</tr>
		<tr>
			<td class="td_label">Código Autorizador</td>
			<td colspan="2" >
				<s:textfield key="codigoDepositoIdentificado" cssClass="text-right uppercase" size="10" disabled="true"/>
			</td>
			<td class="td_label">Dígito Verificador</td>
			<td colspan="1" >
				<s:textfield key="codigoDigitoDeposito" cssClass="text-right uppercase" size="4" disabled="true"/>
			</td>
		</tr>
	</table>
	<div class="text-center tabela_botoes">
	
		<a href="javascript:void(0)" id="BtnVoltar" title='<s:text name="label.cancelar"/>'><img src="${www3}/padroes_web/intranet/imagens/bt_voltar.gif" /></a> 
	</div>
</s:form>
