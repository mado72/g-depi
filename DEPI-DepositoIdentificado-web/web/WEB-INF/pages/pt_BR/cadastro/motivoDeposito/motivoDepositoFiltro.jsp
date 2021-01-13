<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

FILTRO

<!--mensagem de erro de negocio-->
<s:if test="hasActionMessages()">
<span id="box_msg_erro">
<br />

<table class="tabela_verm" >
<tr>
	<td align="left" >
	<ul><s:actionerror/></ul>
	</td>
</tr>
</table>
</span>
</s:if>

<s:form action="/cadastro/motivoDeposito/consultar.do" id="FiltroForm">
<s:hidden key="model.acao"></s:hidden>
<s:hidden key="model.acaoAnterior"></s:hidden>
	<table width="90%" id="tabela_interna" border="0" align="center">
		<tr>
			<td colspan="2" width="50%"></td>
			<td>Critério(s) de Consulta</td>
		</tr>
		<tr>
			<td class="td_label" width="20%">Consultar por </td>
			<td>
				<select id="DropboxPrincipal" class="select"></select>
			</td>
			<td style="width: 400px" rowspan="4">
				<select tabindex="4" id="Lista" multiple size=6>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">Tipo de Operação </td>
			<td>
				<select id="DropboxSecundario" class="select" tabindex="6">
				</select>
			</td>
		</tr>
		<tr>
			<td class="td_label">Valor </td>
			<td>
				<INPUT id="Valor" tabIndex=3 style="WIDTH: 150px; TEXT-TRANSFORM: uppercase" maxLength=50 size=40>
			</td>
		</tr>
		<tr>
			<td style="width: 200px">
			</td>
			<td>
				<a class="button" id="BtnPlus"><img src="<c:url value="/includes/images/plus.gif"/>"></a>
				<a class="button" id="BtnMinus"><img src="<c:url value="/includes/images/minus.gif"/>"></a>
			</td>
		</tr>
	</table>
	<br />
	<table width="95%" class="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div class="tabela_botoes">
					<a class="button" id="BtnIncluir"><img src="<c:url value="/includes/images/bt_incluir.jpg"/>"></a>
					<a class="button" id="BtnConsultar"><img src="<c:url value="/includes/images/bt_consultar.jpg"/>"></a>
				</div>
			</td>
		</tr>
	</table>
<%--
	<logic:notEmpty name="colecaoDados">
	<table width="90%" cellpadding="0" cellspacing="0" border="0" align="center">
		<tr>
			<td align="center">
				<display:table name="colecaoDados" style="width: 100%;" defaultorder="descending" pagesize="7" defaultsort="11" requestURI="ConsultarDeposito.do" htmlId="tabela_interna" id="deposito"  decorator="br.com.bradseg.depi.dp06.view.util.decorator.deposito.DepositoDecorator">
					<display:column property="checkbox" titleKey="label.todos" style="text-align: center; width: 9%; valign: center;" />
					<display:column sortProperty="codigoDepositoIdentificado" titleKey="label.grid.codigoAutorizador" style="text-align: center; valign: center;" sortable="true"> 
						<input type="hidden" id="hdd_indicativoCancelado_<c:out value="${deposito.codigoDepositoIdentificado}"></c:out>" name="hdd_indicativoCancelado" value="<c:out value="${deposito.indicadorDepositoCancelado}"></c:out>"/>
						<input type="hidden" id="hdd_tramite_<c:out value="${deposito.codigoDepositoIdentificado}"></c:out>" name="hdd_tramite" value="<c:out value="${deposito.situacaoArquivoTransferencia}"></c:out>"/>
						<input type="hidden" id="cod_situacao_<c:out value="${deposito.codigoDepositoIdentificado}"></c:out>" name="cod_situacao" value="<c:out value="${deposito.codigoSituacaoDeposito}"></c:out>"/>						
						<a href="CadastrarDeposito.do?acao=detalhar&codigoAutorizador=<c:out value="${deposito.codigoDepositoIdentificado}"></c:out>"><c:out value="${deposito.codigoDepositoIdentificado}"></c:out></a>
					</display:column>
					<display:column property="codigoDigitodeposito" titleKey="label.grid.deposito.codigoDigito" style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="cia.codigoCompanhia" titleKey="label.grid.cia" style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="departamento.siglaDepartamento" titleKey="label.grid.departamento" style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="motivo.descricaoBasica" titleKey="label.grid.motivo" style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="banco.cdBancoExterno" titleKey="label.grid.banco" style="text-align: center; valign: center;" sortable="true"/>						
					<display:column property="agencia" titleKey="label.grid.agencia" style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="contaCorrente" titleKey="label.grid.contaCorrente" style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="codigoResponsavelUltimaAtualizacao" titleKey="label.grid.responsavelAtualizacao" 	style="text-align: center; valign: center;" sortable="true"/>
					<display:column property="dataHoraAtualizacaoFormatada" sortProperty="dataHoraAtualizacao" titleKey="label.grid.dataHoraAtualizacao" style="text-align: center; valign: center;" sortable="true"/>
				</display:table>
			</td>
		</tr>
	</table>
	<br/>
	</logic:notEmpty>
	<logic:notEmpty name="colecaoDados">
	<br/>
	<table style="tabela_botoes" align="center">
		<tr>
			<td align="center">
				<div id="tabela_botoes">
					<a onclick="return novo()" href="#">
						<frw:image midiaAcesso="intranet" alt="Incluir" styleClass="margem_botoes" complement="/imagens/bt_incluir.jpg" /></a>
					<a onclick="return editar()" href="#">
						<frw:image midiaAcesso="intranet" alt="Alterar" complement="/imagens/bt_alterar.jpg" styleClass="margem_botoes" /></a>
					<a onclick="return prorrogar()" href="#">
						<frw:image midiaAcesso="intranet" alt="Prorrogar/Cancelar" complement="/imagens/bt_prorrogar-cancelar.gif" styleClass="margem_botoes" /></a>
					<a onclick="return manter()" href="#">
						<frw:image midiaAcesso="intranet" alt="Manter Movimento" complement="/imagens/bt_manter-movimento.gif" styleClass="margem_botoes" /></a>						
					<a onclick="return consultarLancamento();" href="#">
						<frw:image midiaAcesso="intranet" alt="Consultar Lan�amento" complement="/imagens/bt_consultar_lancamento.gif" styleClass="margem_botoes" /></a>						
					<a onclick="return excluir()" href="#">
						<frw:image midiaAcesso="intranet" alt="Excluir" complement="/imagens/bt_excluir.jpg" styleClass="margem_botoes" /></a>
				</div>
			</td>
		</tr>
	</table>
	</logic:notEmpty>
 --%>
	
	<table style="tabela_botoes" align="center" width="90%">
		<tr>
			<td align="center"><div id="box_loading"></div></td>
		</tr>
	</table>
</s:form>
<c:set var="scriptPage" scope="request">
<script>
jQuery(document).ready(function($){
	var motivos = <c:out value="${model.motivoOperacaoJson}" escapeXml="false"/>;
	
	$.filtro.prepararFormulario(
		"#FiltroForm", 
		{
			principal: motivos.map(function(item){
				return {
					texto: item.motivo.descricao,
					valor: item.motivo.tipo,
					sublista: item.operacoes.map(function(subitem){
						return {
							texto: subitem.descricao,
							valor: subitem.descricao
						};
					})
				};
			}),
			recipiente: []
		}
	);
}(jQuery));
</script>
</c:set>
