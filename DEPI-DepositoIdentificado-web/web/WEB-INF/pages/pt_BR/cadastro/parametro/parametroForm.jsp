<%@ page contentType="text/html; charset=UTF-8" %><%@ 
	taglib prefix="s" uri="/struts-tags" %><%@ 
	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<s:form action="processar">
<s:hidden name="codigo"/>
	<table id="tabela_interna">
	<caption>
		<span class="obrigatorio"><s:text name="label.campos.obrigatorios" /></span>
	</caption>
	<thead>
		<tr>
			<th colspan="4"><s:text name="label.cadastro.motivodeposito.tabela" /></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td class="td_label" ><s:text name="label.cadastro.motivodeposito.descricaoBasica" /><span class="obrigatorio">*</span></td>
			<td colspan="3" >
				<s:textfield tabindex="1" name="descricaoBasica" styleClass="input" style="text-transform: uppercase;"  maxlength="20" size="26" disabled="detalhar"/>
			</td>
		</tr>
		<tr>
			<td class="td_label"><s:text name="label.cadastro.motivodeposito.descricaoDetalhada" /><span class="obrigatorio">*</span></td>
			<td colspan="3">
				<s:textarea name="model.descricaoDetalhada" rows="5" tabindex="2" cols="70" style="text-transform: uppercase;" readonly="detalhar"/>
			</td>
		</tr>
	</tbody>
	</table>
	<table class="tabela_botoes">
		<tbody>
			<tr>
				<td align="center" valign="middle" colspan="3">
					<c:choose>
						<c:when test="${detalhar}">
							<button class="btn-img" id="BtnVoltar" type="submit" name="acao" value="voltar">
								<img src="${www3}/padroes_web/intranet/imagens/bt_voltar.gif"/>
							</button>
						</c:when>
						<c:otherwise>
							<button class="btn-img" id="BtnSalvar" type="submit" name="acao" value="salvar">
								<img src="${www3}/padroes_web/intranet/imagens/bt_salvar.gif"/>
							</button>
							<button class="btn-img" id="BtnCancelar" type="submit" name="acao" value="cancelar">
								<img src="${www3}/padroes_web/intranet/imagens/bt_cancelar.gif"/>
							</button>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
		<tbody>
			<tr>
				<td align="center"><div id="box_loading"></div></td>
			</tr>
		</tbody>
	</table>
</s:form>
