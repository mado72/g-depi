<%@ page contentType="text/html; charset=UTF-8"%><%@ 
	taglib
	prefix="s" uri="/struts-tags"%><%@ 
	taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%><%@
	taglib prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>

<table class="tabela_botoes">
	<tbody>
		<tr>
			<td align="center" valign="middle" colspan="3"><c:choose>
					<c:when test="${detalhar}">
						<a href="${namespaceBase}/consultar/listar.do" class="abtn">
							<img src="${www3}/padroes_web/intranet/imagens/bt_voltar.gif" />
						</a>
					</c:when>
					<c:otherwise>
						<button class="btn-img" id="BtnSalvar" type="submit"
							value="salvar">
							<img src="${www3}/padroes_web/intranet/imagens/bt_salvar.gif" />
						</button>
						<a href="${namespaceBase}/consultar/listar.do"
							class="abtn">
							<img src="${www3}/padroes_web/intranet/imagens/bt_cancelar.gif" />
						</a>
					</c:otherwise>
				</c:choose></td>
		</tr>
	</tbody>
	<tbody>
		<tr>
			<td align="center"><div id="box_loading"></div></td>
		</tr>
	</tbody>
</table>
<c:if test="${empty namespaceBase}"><span style="background-color:red">namespaceBase=${namespaceBase}</span></c:if>
