
<%@ page contentType="text/html; charset=UTF-8" %>
<%@	taglib prefix="s" uri="/struts-tags" %>
<%@	taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/includes/js/script/arquivo.js"></script>
<script type="text/javascript" src="/includes/js/script/consultarRelatorio.js"></script>

<s:include value="/WEB-INF/pages/pt_BR/includes/include-consultarRelatorio.jsp"/>

    <s:set name="vacao" value="acao"/>
    <s:set name="vtpcCias" value="tpcCias"/>
    <s:set name="vtpcCiasOrdenadas" value="tpcCiasOrdenadas"/>
    
    
		
<s:form id="formConsultarRelatorio" action="gerarRelatorio.do" target="_blank">
    <input type="hidden" id="acao" name="acao" value="${acao}" />
    <input type="hidden" id="tituloTabela" name="tituloTabela" value="${tituloTabela}" />
    <input type="hidden" id="acaoOriginal" name="acaoOriginal" value="${acao}" />
    
    
    


       
    	<input type="hidden" id="hdntipoRelatorio" name="codigoAutorizador" value="" />
		<input type="hidden" id="hdnvisualizacao" name="visualizacao" value="" />
	    <input type="hidden" id="hdndeposito" name="deposito" value="1"/>
	    <input type="hidden" id="hdnsituacaoEnvioRetorno" name="situacaoEnvioRetorno" value="ASC"/>
	    <input type="hidden" id="hdnsituacaoManutencoes" name="situacaoManutencoes" value=""/>
	    <input type="hidden" id="hdnsucursal" name="sucursal" value="0"/>
	    
	    <input type="hidden" id="hdnapolice" name="apolice" value=""/>
	    <input type="hidden" id="hdnendosso" name="endosso" value=""/>
	    
	    <input type="hidden" id="hdncodigoAutorizador" name="codigoAutorizador" value = "" />
	    <input type="hidden" id="hdncpfCnpj" name="cpfCnpj" value = "" />
	    <input type="hidden" id="hdncodigoContaCorrente" name="codigoContaCorrente" value = "" />	
	        	    
	    <input type="hidden" id="hdndataInicial" name="dataInicial" value = "" />
	    <input type="hidden" id="hdndataFinal" name="dataFinal" value = "" />
	    <input type="hidden" id="hdnvalorInicial" name="valorInicial" value = "" />		
	    <input type="hidden" id="hdnvalorFinal" name="valorFinal" value = "" />
	    <input type="hidden" id="hdndescricaoDetalhada" name="descricaoDetalhada" value = "" />	
 		


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
								<input type="radio" name="tipoRelatorio" id="tipoRelatorio" value="ER" checked="checked" class="optionbutton" onclick = "ajustarPagina();">Dados de Envio/Retorno Banco   
							    <input type="radio" name="tipoRelatorio" id="tipoRelatorio" value="EX" class="optionbutton" onclick = "ajustarPagina();">Extrato Banco 
								<input type="radio" name="tipoRelatorio" id="tipoRelatorio" value="MN" class="optionbutton"onclick = "ajustarPagina();">Manutenções  
							    <input type="radio" name="tipoRelatorio" id="tipoRelatorio" value="DC" class="optionbutton" onclick = "ajustarPagina();">Dados Complementares 
							</td>
					</tr>
					
					<s:if test="%{#vacao=='exibirDadosComplementares'}">
						<tr>
							<td class="td_label">
								Visualização
								<span class="obrigatorio">*</span>
							</td>
							<td colspan="6">
								<input type="radio" name="tipoVisualizacao" id="tipoRelatorioA" value="A" checked="checked" class="optionbutton" onclick = "ajustarPagina();">Analítico   
							</td>
						</tr>
					</s:if>
				    <s:else>
						<tr>
							<td class="td_label">
								Visualização
								<span class="obrigatorio">*</span>
							</td>
							<td colspan="6">					
						    	<input type="radio" name="tipoVisualizacao" id="tipoRelatorioA" value="A" checked="checked" class="optionbutton" onclick = "ajustarPagina();">Analítico
						    	<input type="radio" name="tipoVisualizacao" id="tipoRelatorioS" value="S" class="optionbutton" onclick = "ajustarPagina();">Sintético
							</td>
						</tr>	
					</s:else>
					
					<tr>

						<td class="td_label" align="left">Cia</td>
							<td align="left">
								<s:if test="%{#vtpcCias=='TRUE'}">
								<s:select 
									list="listaCompanhia" 
									id="listaCompanhia" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoCompanhia" 
						  			listValue="codigoCompanhia" 
						  			name="companhia.listaCompanhia" 
						  			style="width: 100%;"/>
						  		</s:if>
							</td>
							
							<td align="left" colspan="5">
							<s:if test="%{#vtpcCiasOrdenadas=='TRUE'}">
								<s:select 
									list="listaCompanhiaOrd" 
									id="listaCompanhiaOrd" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoCompanhia" 
						  			listValue="codigoCompanhia" 
						  			name="companhia.listaCompanhiaOrd"
						  			style="width: 100%;"/>
						  	</s:if>
							</td>
						
					</tr>
					<tr>
						<td class="td_label">Departamento</td>
							<td align="left" >
								<s:select 
									list="listaDepartamentos" 
									id="listaDepartamentos" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoDepartamento" 
						  			listValue="nomeDepartamento" 
						  			name="departamento.listaDepartamentos"
						  			style="width: 100%;"/>
						            <!-- onkeydown="return tabEnter(event);" onchange="changeCombo('descricao_departamento',this.value); refresh();"> -->
							</td>
							<td align="left" colspan="5">
								<s:select 
									list="listaDepartamentos" 
									id="listaDepartamentos" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoDepartamento" 
						  			listValue="nomeDepartamento" 
						  			name="departamento.listaDepartamentosOrd"
						  			style="width: 100%;"/>
						            <!-- onkeydown="return tabEnter(event);" onchange="changeCombo('descricao_departamento',this.value); refresh();"> -->
							</td>
						
						
					</tr>
					<tr>
						<td class="td_label">Motivo</td>
						<td>
							<s:select 
									list="listaMotivosDepositos" 
									id="listaMotivosDepositos" 
									headerKey="0" 
									headerValue=" -- Todos  -- " 
						  			listKey="codigoMotivoDeposito" 
						  			listValue="descricaoBasica" 
						  			name="motivoDeposito.listaMotivosDepositos"
						  			style="width: 100%;"/>  
						  			<!-- onkeydown="return tabEnter(event);" onchange="refresh();"> -->
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
					     <input type="text" id="sucursal" size="5" onkeypress="return permitirApenasInteiros(event);" maxlength="5"  />
					<td class="td_label" width="13%">&nbsp; Apólice </td>
					<td colspan="4">&nbsp;
						<input type="text" id="apolice"  size="12" onkeypress="return permitirApenasInteiros(event);" maxlength="9"  />
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
						<input type="text" id="codigoAutorizador" size="12" onkeypress="return permitirApenasInteiros(event);" maxlength="5"  />
					</td>
				</tr>
				<tr>
					<td class="td_label">CPF/CNPJ</td>
					<td>&nbsp;
						<input type="hidden" id="hdd_cpfCnpj" value="" /> 
						<input type="text" id="cpfCnpj" class="cpfCnpj" size="28" onkeypress="return permitirApenasInteiros(event);" maxlength="18" 
						onfocus="document.getElementById('hdd_cpfCnpj').value = this.value;" onkeypress="return formatarCPFCNPJ(this, event);" />
					</td>							
					<td class="td_label" width="13%">&nbsp;Endosso</td>
					<td colspan="4">&nbsp;
						<input type="text" id="endosso" class="input" size="12"  onkeypress="return permitirApenasInteiros(event);" maxlength="9">							
					</td>
				</tr>	
				<tr>	
					<td class="td_label" width="8%">Período<span class="obrigatorio">*</span></td>

					
					   <td>
							De  &nbsp;
							<input type="text" id="dataInicial" name="dataInicial" size="13" maxlength="10" onkeypress="formatarData(this, event);" >
							&nbsp;&nbsp;&nbsp;
							<img src="${caminhoImagens}ic_sbox_calendario.gif" style="cursor: pointer;" title="Data" >


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
							Até &nbsp;<input type="text" id="dataFinal" name="filtro.propostaVO.dataEntradaAte" size="13" maxlength="10" onkeypress="formatarData(this, event);">
							&nbsp;&nbsp;&nbsp;<img src="${caminhoImagens}ic_sbox_calendario.gif" style="cursor: pointer;" title="Data" />
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
					    <input type="text" id="valorInicial" class="input" size="35" maxlength="18" maxlength="13" style="margin-left: 15px;" 
				        onkeypress="return mascararValor(this, event, 13)"  >					
					</td>
					<td colspan="5">&nbsp;Valor Final
					    <input type="text" id="valorFinal" class="input" size="35" maxlength="18" maxlength="13" style="margin-left: 15px;" 
				        onkeypress="return mascararValor(this, event, 13)"  >					
					</td>
				</tr>		
					
				<tr>
					<td class="td_label">Situação Relatório Envio/Retorno Banco<span class="obrigatorio">*</span></td>
					<td colspan="6" id="id_envio_retorno">
					<s:if test="%{#vacao=='exibirEnvioRetornoAnalitico' or #vacao=='exibirEnvioRetornoSintetico' }">

						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoE" value="E" class="optionbutton" onclick = "ajustarPagina();" >Enviados  
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoA" value="A" class="optionbutton" onclick = "ajustarPagina();">Aceitos
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoR" value="R" class="optionbutton"onclick = "ajustarPagina();">Rejeitados  
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoC" value="C" class="optionbutton" onclick = "ajustarPagina();">Cancelados
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoVZ" value="" checked="checked" class="optionbutton" onclick = "ajustarPagina();">Todos
					</s:if>
					
					<s:if test="%{#vacao!='exibirEnvioRetornoAnalitico' and #vacao!='exibirEnvioRetornoSintetico' }">
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoE"  value="E" class="optionbutton" onclick = "ajustarPagina();" disabled>Enviados  
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoA"  value="A" class="optionbutton" onclick = "ajustarPagina();" disabled>Aceitos
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoR"  value="R" class="optionbutton"onclick = "ajustarPagina();"  disabled>Rejeitados  
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoC"  value="C" class="optionbutton" onclick = "ajustarPagina();" disabled>Cancelados
						<input type="radio" name="situacaoEnvioRetorno" id="situacaoEnvioRetornoVZ" value="" checked="checked" class="optionbutton" onclick = "ajustarPagina();"disabled>Todos
				
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
					<s:if test="%{#vacao=='exibirManutencoesAnalitico' or #vacao=='exibirManutencoesSintetico' }">
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesA" value="A" class="optionbutton" onclick = "ajustarPagina();">Aceitos  
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesT" value="T" class="optionbutton" onclick = "ajustarPagina();">Transferidos
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesD" value="D" class="optionbutton"onclick = "ajustarPagina();">Devolvidos  
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesR" value="R" class="optionbutton" onclick = "ajustarPagina();">Rejeitados
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesVZ" value=""   checked="checked" class="optionbutton" onclick = "ajustarPagina();">Todos
					</s:if>
					<s:if test="%{#vacao!='exibirManutencoesAnalitico' and #vacao!='exibirManutencoesSintetico' }">
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesA" value="A" class="optionbutton" onclick = "ajustarPagina();" disabled>Aceitos  
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesT" value="T" class="optionbutton" onclick = "ajustarPagina();" disabled>Transferidos
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesD" value="D" class="optionbutton"onclick = "ajustarPagina();" disabled>Devolvidos  
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesR" value="R" class="optionbutton" onclick = "ajustarPagina();" disabled>Rejeitados
						<input type="radio" name="situacaoManutencoes" id="situacaoManutencoesVZ" value=""   checked="checked" class="optionbutton" onclick = "ajustarPagina();"  disabled>Todos
					</s:if>
	


					
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
						onclick="document.getElementById('formConsultarRelatorio').submit();">
						<img src="${caminhoImagens}bt_consultar.jpg" class="margem_botoes"  />
						</a> 
						<a href="#"	title="Limpar" onclick="limpar()"> 
						<img src="${caminhoImagens}bt_limpar.gif" class="margem_botoes"  />
						</a> 
						
						<a href="#" title="Voltar"
						onclick="window.location.href('/DEPI-DepositoIdentificado/index.do');"> 
						<img src="${caminhoImagens}bt_voltar.gif" class="margem_botoes"  />
						</a>
					</div>
					</td>
				</tr>
			</table>
	
	
	
</s:form>
