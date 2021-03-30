<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@
	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- incluir-alterar-excluir namespaceBase = ${namespaceBase} -->
<div class="botoes_editar">
	<table class="tabela_botoes">
		<tr>
			<td align="center">
				<div id="tabela_botoes"><c:url var="btnIncluirUrl" value="${namespaceBase}/incluir.do"/><c:url 
						var="btnProrrogarUrl" value="${namespaceBase}/prorrogar.do"/><c:url 
						var="btnMovimentoUrl" value="${namespaceBase}/movimento.do"/><c:url 
						var="btnLancamentoUrl" value="${namespaceBase}/lancamento.do"/>
					<button class="abtn btnIncluir" id="BtnIncluirRodape" href="${btnIncluirUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_incluir.gif"/>"></button>
					<button class="abtn" id="BtnAlterar"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_alterar.jpg"/>"></button>
					<button class="abtn" id="BtnProrrogar" style="width:auto" href="${btnProrrogarUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_prorrogar-cancelar.gif"/>"></button>
					<button class="abtn" id="BtnMovimento" style="width:auto" href="${btnMovimentoUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_manter-movimento.gif"/>"></button>
					<button class="abtn" id="BtnLancamento" style="width:auto" href="${btnLancamentoUrl}"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_consultar_lancamento.gif"/>"></button>
					<button class="abtn" id="BtnExcluir"><img src="<c:url value="${www3}padroes_web/intranet/imagens/bt_excluir.jpg"/>"></button>
				</div>
			</td>
		</tr>
	</table>
</div>
<c:if test="${empty namespaceBase}"><span style="background-color:red">namespaceBase=${namespaceBase}</span></c:if>
