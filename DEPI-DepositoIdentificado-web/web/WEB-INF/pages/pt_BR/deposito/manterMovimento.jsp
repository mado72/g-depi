<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:set var="formAction"><s:property value="salvarAction"/>.do</s:set>
<c:set var="namespaceBase" scope="request">/deposito/movimento</c:set>

<s:form id="AcaoForm" action="%{formAction}"  namespace="namespaceBase">
<s:hidden key="codigoMovimento"/>

	<table id="tabela_interna">
		<tr>
			<th colspan="6" class="tabela_topo">Dados de Depósito</th>
		</tr>
		<tr>
			<td rowspan="5" class="td_label" style="width: 15%;">Informações Gerais</td>
			<td class="td_label" style="width: 15%;">Código Autorizador/Dígito</td>
			<td style="width: 22%;">
				<s:textfield key="codigoMovimento" disabled="true" size="15" cssClass="text-right"/> - 
				<s:textfield key="divAutorizador" disabled="true" size="4" cssClass="text-right"/>
			</td>
			
			<td class="td_label" style="width: 15%;">Código Funcionário</td>
			<td style="width: 22%;">
				<s:textfield key="codFuncionario" disabled="true" size="15" cssClass="text-right"/>
			</td>
			<td style="width: 11%;"></td>
		</tr>
		<tr>
			<td class="td_label" >Motivo</td>
			<td colspan="2" >
				<s:textfield key="motivo" disabled="true" size="25"/>
			</td>
			<td class="td_label" colspan="2"></td>
		</tr>
		<tr>
			<td class="td_label">Banco/AG/Conta</td>
			<td align="left">
				<s:textfield key="codBanco" disabled="true" size="4" cssClass="text-right"/> -
				<s:textfield key="codAgencia" disabled="true" size="6" cssClass="text-right"/> -
				<s:textfield key="conta" disabled="true" size="15" cssClass="text-right"/>
			</td>
			<td>CPF/CNPJ</td>
			<td>
				<s:textfield key="cpfCnpj" disabled="true" size="18"/>
			</td>
			<td class="td_label"></td>
		</tr>
		<tr>
			<td class="td_label">Nome</td>
			<td colspan="2" >
				<s:textfield key="nomePessoa" disabled="true" size="60"/>
			</td>
			<td class="td_label" colspan="2"></td>
		</tr>
		
		<tr>
			<td class="td_label">Data Depósito</td>
			<td >
				<s:textfield key="dataDeposito" disabled="true" size="12"/>
			</td>
			<td class="td_label">Data Movimento</td>
			<td>
				<s:textfield key="dataMovimento" disabled="true" size="12"/>
			</td>
			<td class="td_label"></td>
		</tr>
		<tr>
			<td class="td_label">Valores</td>
			<td class="td_label">Registro Banco</td>
			<td>
				<s:textfield key="vlrDepositoRegistrado" disabled="true" size="15"/>
			</td>
			<td class="td_label">Depositado</td>
			<td>
				<s:textfield key="vlrDepositado" disabled="true" size="18"/>
			</td>
			<td class="td_label"></td>
		</tr>
		<tr>
			<td rowspan="2" class="td_label">Ações<span class="obrigatorio">*</span></td>
			<td>
				<label>
					<input type="radio" name="codMovimentoAcao" value="A" class="codMovimentoAcao" tabindex="1"> Aceite
				</label>
			</td>
			<td>
				<label>
					<input type="radio" name="codMovimentoAcao" value="T" class="codMovimentoAcao" tabindex="2"> Transferência
				</label>
			</td>
			<td class="td_label">Banco/AG/Conta</td>
			<td>
				<s:textfield key="bancoMovimento" disabled="true" size="4" maxlength="3" cssClass="text-right movConta" placeholder="Banco" tabindex="5"/> - 
				<s:textfield key="agenciaMovimento" disabled="true" size="8" maxlength="6" cssClass="text-right movConta" placeholder="Agência" tabindex="6"/> -
				<s:textfield key="contaMovimento" disabled="true" size="15" maxlength="8" cssClass="text-right movConta" placeholder="Conta" tabindex="7"/>
			</td>
			<td class="td_label"></td>
		</tr>
		<tr>
			<td>
				<label>
					<input type="radio" name="codMovimentoAcao" value="R" class="codMovimentoAcao" tabindex="3"> Receita
				</label>
			</td>
			<td>
				<label>
					<input type="radio" name="codMovimentoAcao" value="D" class="codMovimentoAcao" tabindex="4"> Devolução
				</label>
			</td>
			
			<td class="td_label">Cheque Número</td>
			<td>
				<s:textfield key="chequeMovimento" disabled="true" size="15" maxlength="9" cssClass="text-right movCheque" placeholder="Cheque Nº" tabindex="8"/>
			</td>
			<td class="td_label"></td>
		</tr>
		<tr>
			<td class="td_label" valign="middle">Histórico</td>
			<td colspan="3">
				<s:textarea key="historico" rows="5" cols="70" style="text-transform: uppercase;"/>
			</td>
			<td class="td_label" colspan="2"></td>
		</tr>		

	</table>
	<div class="text-center tabela_botoes">
		<a href="javascript:void(0)" id="BtnSalvar" title="Salvar"><img src="${www3}/padroes_web/intranet/imagens/bt_salvar.gif" /></a> 
		<a href="javascript:void(0)" id="BtnCancelar" title="Cancelar" class="ml2"><img src="${www3}/padroes_web/intranet/imagens/bt_cancelar.gif" /></a>
	</div>
</s:form>

<c:set var="scriptPage" scope="request">
<script>
	$.movimento.prepararFormulario({idForm: "#AcaoForm", tipoAcao: '<s:property value="%{codMovimentoAcao}"/>'});
</script>
</c:set>
