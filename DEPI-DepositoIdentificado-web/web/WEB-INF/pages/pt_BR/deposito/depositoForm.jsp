<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/deposito" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar" id="AcaoForm">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
<input name="estado" type="hidden" value="${estado}">
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="7"><s:text name="label.cadastro.departamentocompanhia.tabela" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="td_label" width="8%" ><s:text name="label.cadastro.parametrodeposito.cia" /><span class="obrigatorio">*</span>
			</td>
			<td>
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.codigoCompanhia-->
				<s:select list="cias" value="codigoCompanhia" listValue="codigoCompanhia" 
					listKey="codigoCompanhia" cssClass="dropbox w-100 companhia-codigo-dropbox" name="codigoCompanhia"
					disabled="%{detalhar}"/>
			</td>
			<td colspan="5">
				<!-- combo codCompanhia listaCodigosCompanhiaSeguradora.nome-->
				<s:select list="cias" value="codigoCompanhia" listValue="descricaoCompanhia" listKey="codigoCompanhia" 
					cssClass="dropbox w-100 companhia-nome-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
 		<tr>
			<!-- combo departamentos -->
			<td class="td_label" ><s:text name="label.cadastro.parametrodeposito.departamento" /><span class="obrigatorio">*</span></td>
			<td>
				<s:select list="deptos" key="codigoDepartamento" listValue="siglaDepartamento" listKey="codigoDepartamento" 
					cssClass="dropbox w-100 departamento-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td colspan="5">
				<s:select list="deptos" value="codigoDepartamento" listValue="nomeDepartamento" listKey="codigoDepartamento" 
					cssClass="dropbox w-100 departamento-nome-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.motivo" /><span class="obrigatorio">*</span></td>
			<td>
				<!-- combo motivo -->
				<s:select key="codigoMotivoDeposito" list="motivos" listValue="descricaoBasica" listKey="codigoMotivoDeposito" 
					cssClass="dropbox w-100 codigo-motivo" disabled="%{detalhar}"/>
			</td>
			<td colspan="5">
				<s:textarea key="descricaoDetalhadaMotivo" rows="5" cols="70" value="%{descricaoDetalhadaMotivo}" readonly="true" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.banco" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:select list="bancos" key="codBanco" listValue="cdBancoExterno" listKey="cdBancoExterno" 
					cssClass="dropbox w-100 banco-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td colspan="5">
				<s:select list="bancos" value="codBanco" listValue="descricaoBanco" listKey="cdBancoExterno" 
					cssClass="dropbox w-100 banco-descricao-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.agencia" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:select list="agencias" key="codigoAgencia" listValue="cdAgenciaExterno" listKey="cdAgenciaExterno" 
					cssClass="dropbox w-100 agencia-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td colspan="5">
				<s:select list="agencias" value="codigoAgencia" listValue="descricaoAgencia" listKey="cdAgenciaExterno" 
					cssClass="dropbox w-100 agencia-descricao-dropbox" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.contaCorrente" />
				<span class="obrigatorio">*</span>
			</td>
			<td>
				<s:select list="contas" key="contaCorrente" listValue="contaCorrente" listKey="contaCorrente"
					cssClass="dropbox w-100 contacorrente-codigo-dropbox" disabled="%{detalhar}"/>
			</td>
			<td>
				<s:textfield size="6" key="contaInterna" disabled="true"/>
			</td>
			<td class="td_label">
				<s:text name="label.cadastro.contacorrenteautorizada.trps" />
			</td>
			<td>
				<s:textfield size="6" key="trps" disabled="true"/>
			</td>
			<td colspan="2">
			</td>
		</tr>
		
		<tr>
			<td class="td_label">
				<s:text name="label.deposito.depositante" />
				<span class="obrigatorio">*</span>
			</td>
			<td class="td_label">
				<s:text name="label.deposito.cpfCnpj" />
			</td>
			<td>
				<s:textfield size="20" key="cpfCnpj" id="cpfCnpj" class="cpfOuCnpj"/>
			</td>
			<td class="td_label">
				<s:text name="label.deposito.pessoaDepositante" />
			</td>
			<td colspan="3">
				<s:if test="%{pessoasCorporativas.size lt 2}">
					
				</s:if>
				<s:else>
				</s:else>
				<s:textfield size="16" maxlength="50" id="nomePessoa" key="nomePessoa" cssClass="w-90" disabled="true"/>
				<s:select key="pessoaDepositante" id="pessoaDepositante" list="pessoasCorporativas" listValue="nomePessoa" listKey="codigoPessoa" cssClass="w-100"></s:select>
			</td>
		</tr>
		
		<tr>
			<td class="td_label" rowspan="4"><s:text name="label.deposito.docsNecessarios"/></td>
			<td class="td_label"><s:text name="label.deposito.sucursal"/></td>
			<td>
				<s:textfield maxlength="4" key="sucursal" id="codigoSucursal" cssClass="w-90 text-right"/>
			</td>
			<td class="td_label"><s:text name="label.deposito.bloqueto"/></td>
			<td>
				<s:textfield maxlength="4" key="bloqueto" id="codigoBloqueto" cssClass="w-90 text-right"/>
			</td>
			<td class="td_label"><s:text name="label.deposito.tipoDocumento"/></td>
			<td>
				<s:textfield maxlength="4" key="tipoDocumento" id="codigoTipoDocumento" cssClass="w-90 text-right"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.deposito.apolice"/></td>
			<td>
				<s:textfield maxlength="4" key="apolice" id="codigoApolice" cssClass="w-90 text-right" />
			</td>
			<td class="td_label"><s:text name="label.deposito.protocolo"/></td>
			<td>
				<s:textfield maxlength="4" key="protocolo" id="codigoProtocolo" cssClass="w-90 text-right"/>
			</td>
			<td class="td_label"><s:text name="label.deposito.ramo"/></td>
			<td>
				<s:textfield maxlength="4" key="ramo" id="codigoRamo" cssClass="w-90 text-right"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.deposito.endosso"/></td>
			<td>
				<s:textfield maxlength="4" key="endosso" id="codigoEndosso" cssClass="w-90 text-right"/>
			</td>
			<td class="td_label"><s:text name="label.deposito.dossie"/></td>
			<td>
				<s:textfield maxlength="4" key="dossie" id="codigoDossie" cssClass="w-90 text-right"/>
			</td>
			<td class="td_label"><s:text name="label.deposito.parcela"/></td>
			<td>
				<s:textfield maxlength="4" key="parcela" id="codigoParcela" cssClass="w-90 text-right"/>
			</td>
		</tr>
		<s:if test="detalhar"><s:set var="rowspanDisp" value="8"/></s:if><s:else><s:set var="rowspanDisp" value="5"/></s:else>
		<tr>
			<td class="td_label"><s:text name="label.deposito.item"/></td>
			<td>
				<s:textfield maxlength="4" key="item" id="codigoItem" cssClass="w-90 text-right"/>
			</td>
			<td  colspan="2">&nbsp;</td>
			<td rowspan="<s:property value="rowspanDisp"/>" colspan="2" class="td_label">&nbsp;</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.deposito.outrosDocs" />
			</td>
			<td colspan="4">
				<c:choose><c:when test="${detalhar}"><c:set var="disabled">disabled</c:set></c:when><c:otherwise><c:set var="disabled" value=""/></c:otherwise></c:choose>
				<textarea readonly="readonly" rows="5" cols="70" ${disabled}></textarea>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.deposito.observacao" /></td>
			<td colspan="4">
				<s:textarea key="observacaoDeposito" rows="5" cols="70" value="%{observacaoDeposito}" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label" rowspan="2"><s:text name="label.deposito.info"/></td>
			<td class="td_label"><s:text name="label.deposito"/></td>
			<td>
				<s:text name="label.deposito.premio"/>
				<s:text name="label.deposito.diversos"/>
			</td>
			<td class="td_label"><s:text name="label.deposito.vencimento"/></td>
			<td>
				<s:textfield maxlength="4" key="dtVencimentoDeposito" cssClass="w-90 text-right" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.deposito.valor"/></td>
			<td>
				<s:textfield maxlength="4" key="vlrDepositoRegistrado" cssClass="w-90 text-right" disabled="detalhar"/>
			</td>
			<td colspan="2"></td> 
		</tr>
<s:if test="detalhar">
		<tr>
			<td class="td_label"><s:text name="label.deposito.codAutorizador"/></td>
			<td>
				<s:textfield key="codigoDepositoIdentificado" cssClass="w-90 text-right" disabled="detalhar"/>
			</td>
			<td colspan="2" class="td_label"><s:text name="label.deposito.digAutorizador"/></td>
			<td>
				<s:textfield key="dv" cssClass="w-90 text-right" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td rowspan="2" class="td_label"><s:text name="label.deposito.acao"/></td>
			<td class="td_label"><s:text name="label.deposito.dtProrrogacao"/></td>
			<td>
				<s:textfield key="dataProrrogacao" cssClass="w-90 text-right" disabled="detalhar"/>
			</td>
			<td colspan="2"></td> 
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.deposito.dtCancelamento"/></td>
			<td>
				<s:textfield key="dtCancelamentoDepositoIdentificado" cssClass="w-90 text-right" disabled="detalhar"/>
			</td>
			<td colspan="2"></td> 
		</tr>
</s:if>		
		</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
<c:set var="motivos">{<c:forEach items="${motivos}" var="item">"${item.codigoMotivoDeposito}":"${item.descricaoDetalhada}",</c:forEach>"NULL": "NULL"}</c:set>
<c:set var="contas">{<c:forEach items="${contas}" var="item">"${item.contaCorrente}":"${item.codigoInternoCC}",</c:forEach>"NULL": "NULL"}</c:set>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<c:if test="${! detalhar}">
	<c:url value="/cadastro/associar-motivodeposito/editar/selecionar.do" var="urlSelecionar"/>
	<c:url value="/filtro/departamento/consultar/index.do" var="urlDepartamento">
		<c:param name="action">${urlSelecionar}</c:param>
	</c:url>
<script>
jQuery(document).ready(function($){
		$.deposito.prepararEditar({
			contas : ${contas},
	 		motivos : ${motivos},
			urlCias : '<c:url value="/json/ciaListar.do"/>',
			urlDepto : '<c:url value="/json/ciaDeptosComRestricao.do?codigoCia=%d"></c:url>',
			urlBancos : '<c:url value="/json/ciaBancos.do?codigo.cia=%d"></c:url>',
			urlAgencias : '<c:url value="/json/ciaBancoAgencias.do?codigo.cia=%d&codigo.banco=%d"></c:url>',
			urlContas : '<c:url value="/json/ciaBancoAgenciaConta.do?codigo.cia=%d&codigo.banco=%d&codigo.agencia=%d"></c:url>',
			urlPessoasCorporativas: '<c:url value="/json/pessoasCorporativas.do?codigo.cpfCnpj=%d"></c:url>',
			urlParametro: '<c:url value="/json/parametro.do?codigo.cia=%d&codigo.depto=%d&codigo.motivo=%d"></c:url>',
			parametros: <depi:json value="%{parametro}"/>, 
		});
	}(jQuery));
</script>
</c:if>
</c:set>

