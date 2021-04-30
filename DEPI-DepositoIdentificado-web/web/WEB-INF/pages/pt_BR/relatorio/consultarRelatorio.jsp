<%@ page contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:include value="/WEB-INF/pages/pt_BR/includes/include-consultarRelatorio.jsp"/>
<s:include value="/WEB-INF/pages/pt_BR/includes/include-arquivos.jsp"/>

    <s:set name="vacao" value="acao"/>
    <s:set name="vtpcCias" value="tpcCias"/>
    <s:set name="vtpcCiasOrdenadas" value="tpcCiasOrdenadas"/>
		
<s:form id="formConsultarRelatorio" action="gerarRelatorio.do" onload="loadPage()"   >
<%-- <s:form id="formConsultarRelatorio" action="gerarRelatorio.do" target="_blank"  onload="loadPage()"   > --%>
    	<input type="hidden" id="tituloTabela" name="tituloTabela" value="${tituloTabela}" />
    	<input type="hidden" id="acaoOriginal" name="acaoOriginal" value="${acao}" />

       	<input type="hidden" id="acaoFrm" name="filtroVO.acao" value="${acao}" />

	<table  class="tabela_principal" align="center">
		<tr>
			<td>
				<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center">
					<tr>
						<td>
							<span class="obrigatorio">*Campos de preenchimento obrigatório</span>
						</td>
					</tr>
				</table>
				<table width="95%" class="tabela_interna" align="center">
					<tr>
						<th colspan="7">
						<div id="titulo_tabela">
							 ${tituloTabela}
							
							</div>
						</th>
					</tr>	
					
					<tr>				
						<td class="td_label" width="17%">
							Tipo de Relatório
							<span class="obrigatorio">*</span>
						</td>
					
							<td colspan="6">
								<!-- checked="checked"  -->
								<s:radio name="filtroVO.tipoRelatorio" 
									list="#{'ER': 'Envio/Retorno Banco', 'EX': 'Extrato-Banco', 'MN': 'Manutenções', 'DC': 'Dados Complementares'}"
									cssClass="optionButton"
									onclick="ajustarPagina()"/>
							</td>
					</tr>
					
					<tr>
						<td class="td_label">
							Visualização
							<span class="obrigatorio">*</span>
						</td>
						<td colspan="6">
							<s:if test="%{#vacao=='exibirDadosComplementares'}">
								<s:set var="visualizacaoRadio">disabled</s:set>
							</s:if>
							<s:radio name="filtroVO.visualizacao" 
								list="#{'A': 'Analítico', 'S': 'Sintético'}"
								cssClass="optionButton"
								onclick="ajustarPagina()"
								disabled="%{#vacao=='exibirDadosComplementares'}"/>
							<%--
					    	<input type="radio" name="filtroVO.visualizacao" value="A" checked="checked" class="optionbutton" onclick = "ajustarPagina();" #{visualizacaoRadio}>Analítico
					    	<input type="radio" name="filtroVO.visualizacao" value="S" class="optionbutton" onclick = "ajustarPagina();" #{visualizacaoRadio}>Sintético
							 --%>
						</td>
					</tr>	
					<tr>

						<td class="td_label" align="left">Cia</td>
							<td align="left">
<c:url value='/json/ciaDeptosComRestricao.do?codigoCia=%d' var="urlDepto"></c:url>
<c:set var="scriptCompanhia">
urlDepto = '${urlDepto}';
$.dpcoddesc.combinar(['#listaCompanhia','#listaCompanhiaOrd']);
$.dpcoddesc.aninhar({
	origem: ['#listaCompanhia', '#listaCompanhiaOrd'],
	destino: ['#listaDepartamentos', '#listaDepartamentosOrd'],
	todos: {
		value: 0,
		text: '-- Todos --'
	},
	url: urlDepto,
	fn: function(v) {
		return [v.codigoDepartamento, v.siglaDepartamento, v.nomeDepartamento];
	},
	error: void(0)
});
</c:set>
								<s:select 
									list="listaCompanhia" 
									id="listaCompanhia" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoCompanhia" 
						  			listValue="codigoCompanhia" 
						  			name="filtroVO.codigoCompanhia" 
						  			style="width: 100%;"  />
							</td>
							
							<td align="left" colspan="5">
								<s:select 
									list="listaCompanhiaOrd" 
									id="listaCompanhiaOrd" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoCompanhia" 
						  			listValue="descricaoCompanhia" 
						  			name="companhia.listaCompanhiaOrd"
						  			style="width: 100%;"  />
							</td>
						
					</tr>
					<tr>
						<td class="td_label">Departamento</td>
							<td align="left" >
<c:url value='/json/relatorioComboMotivo.do?codigo.cia=%d&codigo.depto=%d' var="urlMotivo"></c:url>
<c:set var="scriptDepto">
urlMotivo = '${urlMotivo}';
$.dpcoddesc.combinar(['#listaDepartamentos','#listaDepartamentosOrd']);
$.dpcoddesc.aninhar({
	origem: ['#listaDepartamentos', '#listaDepartamentosOrd'],
	destino: ['#listaMotivosDepositos', '#listaMotivosDepositosOrd'],
	todos: {
		value: 0,
		text: '-- Todos --'
	},
	url: function() {
		return urlMotivo.replace("%d", $('#listaCompanhia').val()).replace("%d", $('#listaDepartamentos').val())
	},
	fn: function(v) {
		return [v.codigoMotivoDeposito, v.descricaoBasica, v.descricaoDetalhada];
	},
	error: void(0)
});
</c:set>
								<s:select 
									list="listaDepartamentos" 
									id="listaDepartamentos" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoDepartamento" 
						  			listValue="codigoDepartamento" 
						  			name="filtroVO.codigoDepartamento"
						  			style="width: 100%;"
						            onkeydown="return tabEnter(event);"
						            /> 
							</td>
							<td align="left" colspan="5">
								<s:select 
									list="listaDepartamentos" 
									id="listaDepartamentosOrd" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoDepartamento" 
						  			listValue="nomeDepartamento" 
						  			name="departamento.listaDepartamentosOrd"
						  			style="width: 100%;"
						           /> 
							</td>
						
						
					</tr>
					<tr>
						<td class="td_label">Motivo</td>
						<td>
							<s:select 
									list="listaMotivosDepositos" 
									id="listaMotivosDepositos" 
									onchange="changeCombo('listaMotivosDepositosOrd',this.value);"
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoMotivoDeposito" 
						  			listValue="codigoMotivoDeposito" 
						  			name="filtroVO.codigoMotivoDeposito"
						  			style="width: 100%;" 
						  			/>
						  	<s:select 
									list="listaMotivosDepositos" 
									id="listaMotivosDepositosOrd" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoMotivoDeposito" 
						  			listValue="descricaoBasica" 
						  			name="motivoDeposito.listaMotivosDepositos"
						  			style="width: 100%; display:none;" 
						  			/>		
						</td>
						<td align="left" colspan="5">
								<textarea name="descricaoDetalhada" id="descricao_motivo" rows="2" cols="50" class="descricao_motivo" onkeydown="return tabEnter(event);" style="width: 100%; wrap:virtual;" disabled ></textarea>
						</td>
					</tr>	
				<tr>	
					<td class="td_label" style="height: 18px;">
						Sucursal
					</td>
					<td>
					     <input type="text" id="filtroVO.sucursal" size="5" onkeypress="return permitirApenasInteiros(event);" maxlength="5"  />
					<td class="td_label" width="13%">&nbsp; Apólice </td>
					<td colspan="4">&nbsp;
						<input type="text" name="filtroVO.apolice"  id="apolice"  size="12" onkeypress="return permitirApenasInteiros(event);" maxlength="9"  />
					</td>					
				</tr>
					
				<tr>
					<td class="td_label">Depósito<span class="obrigatorio">*</span></td>
					
					<td>
						<input type="radio" name="deposito" id="deposito1" value="1" checked="checked"  onkeypress="return permitirApenasInteiros(event)">Prêmios
					    <input type="radio" name="deposito" id="deposito2" value="2" checked="checked"  onkeypress="return permitirApenasInteiros(event)">Diversos
					    <input type="radio" name="deposito" id="deposito0" value="0" checked="checked" class="optionbutton" onkeypress="return permitirApenasInteiros(event)">Todos
					</td>
					<td class="td_label">&nbsp;Código Autorizador</td>
					<td colspan="4">&nbsp;
						<input type="text" name="codigoAutorizador" id="codigoAutorizador" size="12" onkeypress="return permitirApenasInteiros(event);" maxlength="5"  />
					</td>
				</tr>
				<tr>
					<td class="td_label">CPF/CNPJ</td>
					<td>&nbsp;
						<input type="text" id="cpfCnpj" class="cpfCnpj" size="28" name="cpfCnpj" 
							onkeypress="return permitirApenasInteiros(event) && formatarCPFCNPJ(this, event)" 
							maxlength="18"/>
					</td>							
					<td class="td_label" width="13%">&nbsp;Endosso</td>
					<td colspan="4">&nbsp;
						<input type="text" id="endosso" name="endosso" class="input" size="12"  onkeypress="return permitirApenasInteiros(event);" maxlength="9">							
					</td>
				</tr>	
				<tr>	
					<td class="td_label" width="8%">Período<span class="obrigatorio">*</span></td>

					
					   <td>&nbsp;De&nbsp;
							<input type="text" id="dataInicial" name="filtroVO.dataInicial" size="13" maxlength="10" onkeypress="formatarData(this, event);" style="margin-left: 15px;" >							
							<img src="${caminhoImagens}ic_sbox_calendario.gif" style="cursor: pointer;" title="Data"  
								id="inicio" style="cursor: pointer;" title="Data"
								onmouseover="this.style.background='';"
								onmouseout="this.style.background='';" />

						<script>
				    		Calendar.setup({
					        inputField     : "dataInicial",
					        ifFormat       : "%d/%m/%Y",
					        button         : "inicio",
				    	    align          : "bR",
							showsTime      : true,
				       		singleClick    : true
					    	});
						</script>

					   </td>
					   <td colspan="5">
							Até &nbsp;<input type="text" id="dataFinal" name="filtroVO.dataFinal" size="13" maxlength="10" onkeypress="formatarData(this, event);">
							&nbsp;&nbsp;&nbsp;<img src="${caminhoImagens}ic_sbox_calendario.gif" style="cursor: pointer;" title="Data"
								id="fim" style="cursor: pointer;" title="Data"
								onmouseover="this.style.background='';"
								onmouseout="this.style.background='';" />
						<script>
				    		Calendar.setup({
					        inputField     : "dataFinal",
					        ifFormat       : "%d/%m/%Y",
					        button         : "fim",
				    	    align          : "bR",
							showsTime      : true,
				       		singleClick    : true
					    	});
						</script>
					   </td>
							
				   
				</tr>		
					
				<tr>  
					<td class="td_label" style="height: 18px;">	Valor Inicial</td>
					<td>&nbsp;De 
					    <input type="text" name="valorInicial" id="valorInicial" class="input" size="35" maxlength="18" maxlength="13" style="margin-left: 15px;" 
				        onkeypress="return mascararValor(this, event, 13)"  >					
					</td>
					<td colspan="5">&nbsp;Valor Final
					    <input type="text" name="valorFinal" id="valorFinal" class="input" size="35" maxlength="18" maxlength="13" style="margin-left: 15px;" 
				        onkeypress="return mascararValor(this, event, 13)"  >					
					</td>
				</tr>		
					
				<tr>
					<td class="td_label">Situação Relatório Envio/Retorno Banco<span class="obrigatorio">*</span></td>
					<td colspan="6" id="id_envio_retorno">
					<s:if test="%{#vacao=='exibirEnvioRetornoAnalitico' or #vacao=='exibirEnvioRetornoSintetico' }">

						<input type="radio" name="situacaoEnvioRetorno"  value="E" class="optionbutton" >Enviados  
						<input type="radio" name="situacaoEnvioRetorno"  value="A" class="optionbutton" >Aceitos
						<input type="radio" name="situacaoEnvioRetorno"  value="R" class="optionbutton" >Rejeitados  
						<input type="radio" name="situacaoEnvioRetorno"  value="C" class="optionbutton" >Cancelados
						<input type="radio" name="situacaoEnvioRetorno"  value="" checked="checked" class="optionbutton" >Todos
					</s:if>
					
					<s:if test="%{#vacao!='exibirEnvioRetornoAnalitico' and #vacao!='exibirEnvioRetornoSintetico' }">
						<input type="radio" name="situacaoEnvioRetorno"  value="E" class="optionbutton" disabled>Enviados  
						<input type="radio" name="situacaoEnvioRetorno"  value="A" class="optionbutton" disabled>Aceitos
						<input type="radio" name="situacaoEnvioRetorno"  value="R" class="optionbutton" disabled>Rejeitados  
						<input type="radio" name="situacaoEnvioRetorno"  value="C" class="optionbutton" disabled>Cancelados
						<input type="radio" name="situacaoEnvioRetorno"  value="" checked="checked" class="optionbutton" disabled>Todos
				
					</s:if>
					
					</td>
				</tr>					
					
				<tr>
					<td class="td_label">Situação Relatório Manutenções<span class="obrigatorio">*</span></td>
					<td colspan="6" id="id_envio_retorno">
					<!-- 
					'exibirEnvioRetornoAnalitico_OR_exibirEnvioRetornoSintetico'
					'NOT_exibirManutencoesAnalitico_and_NOT_exibirManutencoesSintetico
					 -->
					<s:radio list="#{'A':'Aceitos', 'T':'Transferidos', 'D':'Devolvidos', 'R':'Rejeitados' }"
						name="situacaoManutencoes" cssClass="optionbutton" onclick="ajustarPagina" id="situacaoManutencoes" 
						disabled="%{#vacao!='exibirManutencoesAnalitico' and #vacao!='exibirManutencoesSintetico' }"
						/>
					</td>
				</tr>					
			</table>
				</td>	
		    </tr>
	</table>
	
	<table align="center">
				<tr>
					<td align="center" valign="middle" colspan="3">
					<div id="tabela_botoes">
					    <a href="#" title="Consultar"
						onclick="gerarRelatorio();">
						<img src="${caminhoImagens}bt_consultar.jpg" class="margem_botoes"  />
						</a> 
						<a href="#"	title="Limpar" onclick="limpar();"> 
						<img src="${caminhoImagens}bt_limpar.gif" class="margem_botoes"  />
						</a> 
						
						<a href="#" title="Voltar"
						onclick="voltar();"> 
						<img src="${caminhoImagens}bt_voltar.gif" class="margem_botoes"  />
						</a>
					</div>
					</td>
				</tr>
			</table>
	
	
	
</s:form>

<c:set var="scriptPage" scope="request">
<script>
jQuery(document).ready(function($){
	${scriptCompanhia};
	${scriptDepto};
}(jQuery));
</script>
</c:set>