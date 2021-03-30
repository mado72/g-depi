<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="namespaceBase" scope="request">/deposito/editar</c:set>
<s:include value="/WEB-INF/pages/pt_BR/comum/filtro2dropbox.jsp">
	<s:param name="scriptOff" value="true"/>
</s:include>

<s:if test="colecaoDados && !colecaoDados.isEmpty()">
<c:url value="${namespaceBase}/alterar.do" var="actionForm"></c:url>

<form action="${actionForm}" id="AcaoForm" method="post">
<table id="tabela_interna" class="sortable DepartamentoCompanhia Consulta">
	<thead>
		<tr>
		<th class="selecao">
			<s:text name="label.todos"/>
			<br/>
			<input type="checkbox" class="optionbutton checkTodos" />
		</th>
		<th>
			<s:text name="label.grid.deposito.codigoAutorizador"/>
		</th>
		<th>
			<s:text name="label.grid.deposito.codigoDigito"/>
		</th>
		<th>
			<s:text name="label.cadastro.departamentocompanhia.companhia"/>
		</th>
		<th>
			<s:text name="label.cadastro.departamentocompanhia.departamento"/>
		</th>
		<th>
			<s:text name="label.grid.deposito.motivo"/>
		</th>
		<th>
			<s:text name="label.grid.associarmotivodeposito.codigoBanco"/>
		</th>
		<th>
			<s:text name="label.grid.associarmotivodeposito.codigoAgencia"/>
		</th>
		<th>
			<s:text name="label.grid.associarmotivodeposito.contaCorrente"/>
		</th>
		<th>
			<s:text name="label.grid.associarmotivodeposito.responsavelIncAlt"/>
		</th>
		<th>
			<s:text name="label.grid.associarmotivodeposito.dataHoraIncAlt"/>
		</th>
		</tr>
	</thead>
	<tbody class="lista">
 	<s:iterator value="colecaoDados" var="item" status="status">
		<tr>
			<td class="text-center">
				<input type="checkbox" class="optionbutton" name="codigo" value="<s:property value="%{codigoDepositoIdentificado}"/>"/>
			</td>
			<td class="text-center">
				<s:url action="exibir" namespace="/deposito/editar" var="linkExibir">
					<s:param name="codigo" value="%{codigoDepositoIdentificado}"/>
				</s:url>
				<s:a href="%{linkExibir}">
					<s:property value="%{codigoDepositoIdentificado}"/>
				</s:a>
			</td>
			<td class="text-center">
				<s:property value="codigoDigitoDeposito"/>
			</td>
			<td class="text-center">
				<s:property value="cia.codigoCompanhia"/>
			</td>
			<td class="text-center">
				<s:property value="departamento.siglaDepartamento"/>
			</td>
			<td class="text-center">
				<s:property value="motivoDeposito.codigoMotivoDeposito"/>
			</td>
			<td class="text-center">
				<s:text name="format.banco"><s:param name="value" value="%{banco.cdBancoExterno}"/></s:text>
			</td>
			<td class="text-center">
				<s:text name="format.agencia"><s:param name="value" value="%{codigoAgencia}"/></s:text>
			</td>
			<td class="text-center">
				<s:text name="format.contaCorrente"><s:param name="value" value="%{contaCorrente}"/></s:text>
			</td>
			<td class="text-center">
				<s:property value="codigoResponsavelUltimaAtualizacao"/>
			</td>
			<td class="text-center">
				<s:date name="dataHoraAtualizacao" format="dd/MM/yyyy HH:mm:ss"/>
			</td> 
		</tr>
	</s:iterator>
 	</tbody>
</table>
<div class="paginacao"></div>
<c:set var="ocultarAlterar" value="true" scope="request"/>
<s:include value="botoes_deposito.jsp"></s:include>
</form>
<br/>
<c:set var="scriptPage" scope="request">
<c:out value="${scriptPage}" default="" escapeXml="false"/>
<script>
jQuery(document).ready(function($){
	$.deposito.prepararFiltro("#AcaoForm");
	<s:if test="colecaoDados">
	$.paginacao.paginar({
		tblSeletor: "#tabela_interna.Consulta",
		pagSeletor: ".paginacao",
		registros: 7,
		pattern: ":reg itens encontrados, mostrando :idxIni até :idxFin."
	});
	</s:if>
}(jQuery));
</script>
</c:set>
</s:if>