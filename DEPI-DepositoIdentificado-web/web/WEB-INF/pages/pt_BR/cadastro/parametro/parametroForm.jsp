<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="depi" uri="/depi-tags" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/cadastro/parametro" var="namespaceBase" scope="request"/>
<s:include value="/WEB-INF/pages/pt_BR/comum/action-messages.jsp"/>
<s:form action="salvar" id="AcaoForm">
<input name="codigo" type="hidden" value="${codigo.isEmpty() ? '' : codigo }">
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="4"><s:text name="label.cadastro.parametrodeposito.tabela" /></th>
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
			<td class="td_label" rowspan="2"><s:text name="label.cadastro.parametrodeposito.motivo" /><span class="obrigatorio">*</span></td>
			<td class="td_label">
				<s:text name="label.cadastro.motivodeposito.descricaoBasica" />
			</td>
			<td colspan="5">
				<!-- combo motivo -->
				<s:select key="codigoMotivoDeposito" list="motivos" listValue="descricaoBasica" listKey="codigoMotivoDeposito" 
					cssClass="dropbox w-100" disabled="%{detalhar}"/>
			</td>
		</tr>
		<tr>
			<td class="td_label">
				<s:text name="label.cadastro.parametrodeposito.descricao" />
			</td>
			<td colspan="5">
				<s:textarea key="descricaoDetalhadaMotivo" rows="5" cols="70" value="%{descricaoDetalhadaMotivo}" readonly="true" disabled="detalhar"/>
			</td>
		</tr>
<c:if test="${estado != 'INSERIR' }">
		<tr>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.parametros.controle" /></td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.vencto"/><span class="obrigatorio"></span></td>
			<td class="control">
				<depi:radioSimNao name="codigoBancoVencimento"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.dias" /></td>
			<td class="control">
				<c:set var="numeroDiasDisabled" value="${codigoBancoVencimento eq 'N'}" scope="request"/>
				<s:textfield key="numeroDiasAposVencimento" maxlength="3" disabled="%{numeroDiasDisabled}"/>
			</td>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td rowspan="4" class="td_label"><s:text name="label.cadastro.parametrodeposito.documentos" /><span class="obrigatorio"></span></td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.sucursal"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoSucursal"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.ramo"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoRamo"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.apolice"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoApolice"  detalhar="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.tipo"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoTipo"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.cpf"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoCpfCnpj"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.bloqueto"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoBloqueto"  detalhar="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.endosso"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoEndosso"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.dossie"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoDossie"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.parcela"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoParcela"  detalhar="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.item"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoItem"  detalhar="detalhar"/>
			</td>
			<td class="td_label"><s:text name="label.cadastro.parametrodeposito.protocolo"></s:text></td>
			<td class="control">
				<depi:radioSimNao name="codigoProtocolo"  detalhar="detalhar"/>
			</td>
			<td colspan="2">&nbsp;</td>
		</tr>
</c:if>
		</tbody>
	</table>
<s:include value="/WEB-INF/pages/pt_BR/comum/voltar-salvar-cancelar.jsp"/>
</s:form>
<depi:clearMessages actionErrors="true" fieldErrors="true" messages="true"/>
<c:set var="motivos">{<c:forEach items="${motivos}" var="item">"${item.codigoMotivoDeposito}":"${item.descricaoDetalhada}",</c:forEach>"NULL": "NULL"}</c:set>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
/* 	$.parametro.prepararEditar({
		urlCias : '<c:url value="/json/ciaListar.do"></c:url>',
		urlDepto : '<c:url value="/json/ciaDepartamentos.do?codigoCia=%d"></c:url>'
	});
 */
 	$.parametro.prepararFormulario({
 		motivos : ${motivos},
		urlDepto : '<c:url value="/json/ciaDeptosComRestricao.do?codigoCia=%d"></c:url>'
 	});
}(jQuery));
</script>
</c:set>
