$.ajaxSetup ({cache: false});

String.prototype.replaceAll = function(de, para){
    var str = this;
    var pos = str.indexOf(de);
    while (pos > -1){
		str = str.replace(de, para);
		pos = str.indexOf(de);
	}
    return (str);
};

$.namespace = function() {
    var a=arguments, o=null, i, j, d;
    for (i=0; i<a.length; i=i+1) {
        d=a[i].split(".");
        o=window;
        for (j=0; j<d.length; j=j+1) {
            o[d[j]]=o[d[j]] || {};
            o=o[d[j]];
        }
    }
    return o;
};

$.namespace( 'jQuery.debug' );

$(function() {
	
	var acao = $("input[name='acao']").val();
	var departamento = $("select[id='codigo_departamento'] option:selected").text();

	//S� EXECUTA EM CASO DE SER A��O DETALHAR E PARA OS DEPARTAMENTOS ESPEC�FICOS.
	if((departamento == "GRV" || departamento == "GRC") && acao != "novo") {
		carregarParcelasConsulta();	
	}
	
	$("#dialog-parcelas-pendentes").dialog({
		  autoOpen: false,
		  show: "blind",
		  hide: "blind",
		  width: 800,
		  modal: true,
		  	buttons: {
				Voltar: function() {
					$(this).dialog("close");
				},		
				Ok: function() {
					//S� TEM A��O QUANDO � A A��O DE INSERIR NOVO REGISTRO.
					if(acao == "novo" || acao == "inserir") {
						var checked = [];
						$("input[name='parcela_check[]']:checked").each(function () {
							checked.push($(this).val());
						});
						
						$('#valor_parcelas').val(checked);
                        //EN SAP
						$('#tipoGrupoRecebimento').val(1);						
						
						if(checked.length != 0) {
							if($('#codigo_cia').val() == 244) {
								$("#dtVencimentoDeposito").val($("#dataVencimentoParcela244").val());
								$("#dtVencimentoDeposito").attr("readonly", true);
								$("#img_vencimentoDeposito").attr("disabled", true);
							}
							$("#valor_deposito").val($("#total_selecao").val());
							$("#valor_deposito").attr("readonly", true);
							$("#valor_deposito").attr("onkeypress", "");
							
							$('#sucursal').attr("readonly", true);
							$('#codigo_cia').attr("readonly", true);
							$('#ramo').attr("readonly", true);
							$('#apolice').attr("readonly", true);
							$('#item').attr("readonly", true);
							$('#endosso').attr("readonly", true);

							
							populaListaParcelas();
						} else {
							if($('#codigo_cia').val() == 244) {
								$("#dtVencimentoDeposito").val("");
								$("#dtVencimentoDeposito").attr("readonly", false);
								$("#img_vencimentoDeposito").attr("disabled", false);
							}
							$("#valor_deposito").val('');
							$("#valor_deposito").attr("readonly", false);
							$("#valor_deposito").attr("onkeypress", "mascararValor(this, event, 13)");
							
							$('#sucursal').attr("readonly", false);
							$('#codigo_cia').attr("readonly", false);
							$('#ramo').attr("readonly", false);
							$('#apolice').attr("readonly", false);
							$('#item').attr("readonly", false);
							$('#endosso').attr("readonly", false);
							
							limpaListaParcelas();
						}						
					}

					$(this).dialog("close");
				}
			},
		  close: function(event, ui) { 
		  	
		  }
	});
	
});

function populaListaParcelas() {
	var arrayIndicesMarcados = [];
	var indiceCheck = $('#valor_parcelas').val().split(",");
	
	var parcela;
	
	for(j = 0; j < indiceCheck.length; j++) {
		parcela = indiceCheck[j].split("|");
		if(parcela[2] != "") {
			arrayIndicesMarcados.push(parcela[2]);	
		}
	}
	
	var tabela_parcelas = '';
	tabela_parcelas += '<table width="100%" class="tabela_interna">';
		tabela_parcelas += '<tr>';
			tabela_parcelas += '<th>Parcelas Pendentes Associadas<span style="float: right;"><img src="../includes/images/lupa.gif" onclick="carregarParcelasPendetes();" style="cursor: pointer;"></span></th>';
		tabela_parcelas += '</tr>';
		for(var i = 0; i < arrayIndicesMarcados.length; i++) {
			tabela_parcelas += '<tr>';
				tabela_parcelas += '<td align="center">'+arrayIndicesMarcados[i]+'</td>';
			tabela_parcelas += '</tr>';
		}	
	tabela_parcelas += '</table>';
	
	$("#tabela_parcelas").html(tabela_parcelas);	
}

function limpaListaParcelas() {
	var tabela_parcelas = '';
	tabela_parcelas += '<table width="100%" class="tabela_interna">';
		tabela_parcelas += '<tr>';
			tabela_parcelas += '<th>Parcelas Pendentes Associadas<span style="float: right;"><img src="../includes/images/lupa.gif" onclick="carregarParcelasPendetes();" style="cursor: pointer;"></span></th>';
		tabela_parcelas += '</tr>';
	tabela_parcelas += '</table>';
	
	$("#tabela_parcelas").html(tabela_parcelas);		
}

function loading(id) {
	var carregando = '';
	carregando += '<TABLE width="100%" align="center">';
	carregando += '<THEAD><TR><TD valign="top" align="center"><img src="../includes/images/carregando.gif"></TD></TR></THEAD>';
	carregando += '</TABLE>';
    $(id).html(carregando);
    $(id).show();
}


function validaCheckBox(indice) {
	//S� valida "janelas" para CIA 244.
	if($('#codigo_cia').val() == "244") {
		var total = $("#total_parcelas").val();
		
		if($('#' + indice).is(":checked")) {
			
			var proximoIndice = indice +1;
			$('#' + proximoIndice).attr('disabled', false);
		} else {
			if(indice == 0) {
				$('.checkParcela').attr("checked", false);
				$('.checkParcela').attr('disabled', true);
				$('#0').attr('disabled', false);
			} else {
				for(i = indice+1; i < total; i++) {
					$('#' + i).attr('disabled', true);
					$('#' + i).attr('checked', false);
				}
			}
		}
	}
}

function calculaValorTotal() {
	var qtdParcelas = $("#total_parcelas").val();
	
	var valorTotal = 0;
	
	for(i = 0; i < qtdParcelas; i++) {
		if($('#' + i).is(":checked")) {
			var splitValor = $('#' + i).val().split("|");
			valorTotal += parseFloat(splitValor[1]);
		}
	}
	
	var valor_total_selecionado = $().number_format(valorTotal, {
	     numberOfDecimals:2,
	     precision: 2,
	     decimalSeparator: ',',
	     thousandSeparator: '.',
	     symbol: ''});
	
	$("#total_selecao").attr("value", valor_total_selecionado);
}

function validaCamposObrigatorios(sucursal, ramo, apolice, item, endosso, cpfCnpj, dataVencimento, cia) {
	
	var contemErros = 'N';
	var erros = 'Chave Informada Inv�lida:\n';

	if(sucursal == 0 || sucursal == "" || sucursal == undefined) {
		erros += '- Sucursal obrigat�ria\n';
		contemErros = 'S';
	}
		
	if(ramo == "0" || ramo == "" || ramo == undefined) {
		erros += '- Ramo obrigat�rio\n';
		contemErros = 'S';
	}
	if (cia != "186" && cia != "144") {
		if (!$.isNumeric(ramo))	{
		  erros += '- Para empresas diferente de 186 e 144 o ramo tem que ser num�rico\n';
		  contemErros = 'S';
		}
	}
	if(ramo.length <4 ) {
		erros += '- Ramo tem que ter 4 caracteres\n';
		contemErros = 'S';
	}	
	if(apolice == 0 || apolice == "" || apolice == undefined) {
		erros += '- Ap�lice obrigat�ria\n';
		contemErros = 'S';
	}
	if(item == "" || item == undefined) {
		erros += '- Item obrigat�rio\n';
		contemErros = 'S';
	}
	if(endosso == "" || endosso == undefined) {
		erros += '- Endosso obrigat�rio';
		contemErros = 'S';
	}
	if(cpfCnpj == 0 || cpfCnpj == "" || cpfCnpj == undefined) {
		erros += '- CPF/CNPJ obrigat�rio\n';
		contemErros = 'S';
	}
	if($('#codigo_cia').val() != "244" && dataVencimento == "") {
		erros += '- Data de Vencimento obrigat�ria\n';
		contemErros = 'S';
	}
	
	if(contemErros == 'S') {
		alert(erros);
		return false;
	} else {
		return true;
	}
	
}

function carregarParcelasPendetes() {
	
	loading("#div_parcelas");
	
	var sucursal = $('#sucursal').val();
	var cia = $('#codigo_cia').val();
	var ramo = $('#ramo').val().toUpperCase();
	var apolice = $('#apolice').val();
	var item = $('#item').val();
	var endosso = $('#endosso').val();
	var cpfCnpj = $('#cpfCnpj').val().replaceAll(".","").replaceAll("-","").replaceAll("/", "");
	var dataVencimento = $('#dtVencimentoDeposito').val();
	var valorDepositado = $('#valor_deposito').val();
	
	if(validaCamposObrigatorios(sucursal, ramo, apolice, item, endosso, cpfCnpj, dataVencimento, cia) ) {
		var arrayIndicesMarcados = [];
		var indiceCheck = $('#valor_parcelas').val().split(",");
		
		var indice;
		
		for(j = 0; j < indiceCheck.length; j++) {
			indice = indiceCheck[j].split("|");
			if(indice[0] != "") {
				arrayIndicesMarcados.push(indice[0]);	
			}
		}
		
		$.ajax({	type:     "POST",
		  			cache:    false ,  
		  			async: 	  true, 
		  			dataType: 'json', 
		  			url: 	  "parcelasPendentes.do", 
		  			data:	  {
								'acao': 'consultarParcelasCbbs',
								'sucursal': sucursal,
								'cia':cia,
								'ramo':ramo,
			  					'apolice':apolice,
			  					'item':item,
			  					'endosso':endosso,
			  					'cpfCnpj': cpfCnpj
							  },	

		  			success: function(obj_retorno_ajax) {
						if(obj_retorno_ajax.statusOperacao == "SUCCESS") {
							var objList = obj_retorno_ajax.objetoRetornado;
			 				
							if (objList.length != 0) {
			 					
			 					var listaConteudo = '';
			 					
			 					listaConteudo += '<table width="100%" class="tabela_interna">';
			 						listaConteudo += '<thead>';
				 						listaConteudo += '<tr>';
				 						   if ($('#codigo_cia').val() == 186 || 
				 							   $('#codigo_cia').val() == 144  ) {
				 							  listaConteudo += '<th colspan="9">Parcelas SAP</th>';
				 						   } else {
				 							  listaConteudo += '<th colspan="9">Parcelas CBBS</th>';
				 						   }
				 						listaConteudo += '</tr>';
				 						listaConteudo += '<tr>';
				 							listaConteudo += '<th>Sele��o</th>';
				 							listaConteudo += '<th>Bloquete</th>';
				 							listaConteudo += '<th>Segurado</th>';
				 							listaConteudo += '<th>Parcela</th>';
				 							listaConteudo += '<th>Sucursal</th>';
				 							listaConteudo += '<th>Cia</th>';
				 							listaConteudo += '<th>Ramo</th>';
				 							listaConteudo += '<th>Ap�lice</th>';
				 							listaConteudo += '<th>Valor</th>';
				 						listaConteudo += '</tr>';
				 					listaConteudo += '</thead>';
			 							
				 					listaConteudo += '<tbody>';
			 						for (var i = 0; i < objList.length; i++) {
			 							listaConteudo += '<tr>';
			 								listaConteudo += '<td align="center"><input type="checkbox" id="'+i+'" name="parcela_check[]" class="checkParcela" onclick="validaCheckBox('+i+');calculaValorTotal()" value="'+i+'|'+objList[i].valorParcelaCobrado+'|'+objList[i].codigoParcela+'|'+objList[i].valorIofCobrado+'|'+$.format.date(objList[i].dataVencimento, "dd.MM.yyyy")+'|'+objList[i].codigoIdentificadorOrigem+'"></td>';
			 								listaConteudo += '<input type="hidden" id="dataVencimentoParcela244" value="'+$.format.date(objList[i].dataVencimento, "dd/MM/yyyy")+'">';
			 								listaConteudo += '<td>'+objList[i].codigoParcela+'</td>';
			 								listaConteudo += '<td>'+objList[i].nomePessoa+'</td>';
			 								listaConteudo += '<td align="center">'+objList[i].codigoIdentificadorOrigem+'</td>';
			 								listaConteudo += '<td>'+sucursal+'</td>';
			 								listaConteudo += '<td align="center">'+cia+'</td>';
			 								listaConteudo += '<td align="center">'+ramo+'</td>';
			 								listaConteudo += '<td align="center">'+apolice+'</td>';
			 								
											var valor_parcela_cobrada = $().number_format(objList[i].valorParcelaCobrado, {
											     numberOfDecimals:2,
											     decimalSeparator: ',',
											     thousandSeparator: '.',
											     symbol: ''});	
											listaConteudo += '<td align="right">'+valor_parcela_cobrada+'</td>';
			 							listaConteudo += '</tr>';
			 						}
			 						listaConteudo += '<input type="hidden" id="total_parcelas" value="'+objList.length+'">';
			 						listaConteudo += '</tbody>';
								listaConteudo += '</table>';
								listaConteudo += '<table width="100%" align="right" border="0">';
									listaConteudo += '<tr>';
										listaConteudo += '<td width="90%" align="right">Total Sele��o</td>';
										listaConteudo += '<td><input type="text" id="total_selecao" readonly="readonly" align="right"></td>';
									listaConteudo += '</tr>';
								listaConteudo += '</table>';					
			 					
								$("#div_parcelas").html(listaConteudo);
								
								for(var k = 0; k < arrayIndicesMarcados.length; k++) {
									$("#"+arrayIndicesMarcados[k]).attr("checked", true);	
								}
								
								validaCheckBox(arrayIndicesMarcados.length);
								calculaValorTotal();

			 				} else {
			 					$("#div_parcelas").html("<span class='tabela_verm'>Bloquetes não encontrados para a chave informada.</span>");
			 				}							
						} else if(obj_retorno_ajax.statusOperacao == "ERROR"){
							var erro = obj_retorno_ajax.exceptionRetornada;
							$("#div_parcelas").html("<span class='tabela_verm'>"+erro+"</span>");
						}
		 			},
	                error: function(XMLHttpRequest, textStatus, errorThrown) {
		 				var erro = XMLHttpRequest.responseText;
		 				$("#div_parcelas").html("<span class='tabela_verm'>"+erro+"</span>");
		 			}
		});
		
		$("#dialog-parcelas-pendentes").dialog("open");		
	}
}

function carregarParcelasConsulta() {
	
	var codigoDepositoAutorizado = $("input[name='deposito.codigoDepositoIdentificado']").val();
	
	$.ajax({type:     "POST",
			cache:    false,  
			async: 	  true, 
			dataType: 'json', 
			url: 	  "parcelasPendentes.do", 
  			data:	  {
						'acao': 'listarParcelas',
						'detalhar': 'N',
						'codigoDepositoAutorizado':codigoDepositoAutorizado
	  		  		  },	

				  success: function(obj_retorno_ajax) {
					  if(obj_retorno_ajax.statusOperacao == "SUCCESS") {
						  var objList = obj_retorno_ajax.objetoRetornado;
 				
						  if (objList.length != 0) {
							  var tabela_parcelas = '';
							  tabela_parcelas += '<table width="100%" class="tabela_interna">';
							  	tabela_parcelas += '<tr>';
							  		tabela_parcelas += '<th>Parcelas Pendentes Associadas<span style="float: right;"><img src="../includes/images/lupa.gif" onclick="carregarDetalhesParcelasAssociadas();" style="cursor: pointer;"></span></th>';
								tabela_parcelas += '</tr>';
								for (var i = 0; i < objList.length; i++) {
									tabela_parcelas += '<tr>';
										tabela_parcelas += '<td align="center">'+objList[i].codigoParcela+'</td>';
									tabela_parcelas += '</tr>';
								}	
								tabela_parcelas += '</table>';

								$("#tabela_parcelas").html(tabela_parcelas);
						  } 
					  } else if(obj_retorno_ajax.statusOperacao == "ERROR"){
						  		var erro = obj_retorno_ajax.exceptionRetornada;
						  		$("#tabela_parcelas").html("<span class='tabela_verm'>"+erro+"</span>");
					  }
				  },
				  error: function(XMLHttpRequest, textStatus, errorThrown) {
					  var erro = XMLHttpRequest.responseText;
					  $("#tabela_parcelas").html("<span class='tabela_verm'>"+erro+"</span>");
				  }
		});		
}

function carregarDetalhesParcelasAssociadas() {
	
	loading("#div_parcelas");
	
	var codigoDepositoAutorizado = $("input[name='deposito.codigoDepositoIdentificado']").val();
	var sucursal = $("input[name='deposito.sucursal']").val();
	var cia = $("select[name='deposito.cia.codigoCompanhia'] option:selected").val();
	var ramo = $("input[name='deposito.ramo']").val();
	var apolice = $("input[name='deposito.apolice']").val();
	var item = $("input[name='deposito.item']").val();
	var endosso = $("input[name='deposito.endosso']").val();
	var cpfCnpj = $('#cpfCnpj').val().replaceAll(".","").replaceAll("-","").replaceAll("/", "");
	
	$.ajax({type:     "POST",
			cache:    false ,  
			async: 	  true, 
			dataType: 'json', 
			url: 	  "parcelasPendentes.do", 
  			data:	  {
						'acao': 'listarParcelas',
						'detalhar': 'S',
						'codigoDepositoAutorizado':codigoDepositoAutorizado,
						'sucursal': sucursal,
						'cia': cia,
						'ramo': ramo,
						'apolice': apolice,
						'item': item,
						'endosso': endosso,
						'cpfCnpj': cpfCnpj
	  		  		  },	

		  			success: function(obj_retorno_ajax) {
						if(obj_retorno_ajax.statusOperacao == "SUCCESS") {
							var objList = obj_retorno_ajax.objetoRetornado;
			 				
							if (objList.length != 0) {
			 					
			 					var listaConteudo = '';
			 					
			 					listaConteudo += '<table width="100%" class="tabela_interna">';
			 						listaConteudo += '<thead>';
				 						listaConteudo += '<tr>';
				 						   if ($('#codigo_cia').val() == 186 || 
				 							   $('#codigo_cia').val() == 144  ) {
					 							  listaConteudo += '<th colspan="9">Parcelas SAP</th>';
					 						   } else {
					 							  listaConteudo += '<th colspan="9">Parcelas CBBS</th>';
					 						   }
				 						listaConteudo += '</tr>';
				 						listaConteudo += '<tr>';
				 							listaConteudo += '<th>Bloquete</th>';
				 							listaConteudo += '<th>Segurado</th>';
				 							listaConteudo += '<th>Parcela</th>';
				 							listaConteudo += '<th>Sucursal</th>';
				 							listaConteudo += '<th>Cia</th>';
				 							listaConteudo += '<th>Ramo</th>';
				 							listaConteudo += '<th>Ap�lice</th>';
				 							listaConteudo += '<th>Valor</th>';
				 						listaConteudo += '</tr>';
				 					listaConteudo += '</thead>';
			 							
				 					listaConteudo += '<tbody>';
			 						for (var i = 0; i < objList.length; i++) {
			 							listaConteudo += '<tr>';
			 								listaConteudo += '<td>'+objList[i].codigoParcela+'</td>';
			 								listaConteudo += '<td>'+objList[i].nomePessoa+'</td>';
			 								listaConteudo += '<td align="center">'+objList[i].codigoIdentificadorOrigem+'</td>';
			 								listaConteudo += '<td>'+sucursal+'</td>';
			 								listaConteudo += '<td align="center">'+cia+'</td>';
			 								listaConteudo += '<td align="center">'+ramo+'</td>';
			 								listaConteudo += '<td align="center">'+apolice+'</td>';
			 								
											var valor_parcela_cobrada = $().number_format(objList[i].valorParcelaCobrado, {
											     numberOfDecimals:2,
											     decimalSeparator: ',',
											     thousandSeparator: '.',
											     symbol: ''});	
											listaConteudo += '<td align="right">'+valor_parcela_cobrada+'</td>';
			 							listaConteudo += '</tr>';
			 						}
			 						listaConteudo += '</tbody>';
								listaConteudo += '</table>';
			 					
								$("#div_parcelas").html(listaConteudo);

			 				} else {
			 					$("#div_parcelas").html("<span class='tabela_verm'>Bloquetes não encontrados para a chave informada.</span>");
			 				}							
						} else if(obj_retorno_ajax.statusOperacao == "ERROR"){
							var erro = obj_retorno_ajax.exceptionRetornada;
							$("#div_parcelas").html("<span class='tabela_verm'>"+erro+"</span>");
						}
		 			},
		 			error: function(XMLHttpRequest, textStatus, errorThrown) {
					  var erro = XMLHttpRequest.responseText;
					  $("#tabela_parcelas").html("<span class='tabela_verm'>"+erro+"</span>");
				  }
		});
	
		$("#dialog-parcelas-pendentes").dialog("open");
}

function desabilitarProrrogacao() {
	if (document.getElementById('hdd-indicadorDepositoProrrogado').value != 'S') {
		document.getElementById('dtVencimentoProrrogacao').value='';
	}
	document.getElementById('img_dtVencimentoProrrogacao').disabled=true;
	document.getElementById('dtVencimentoProrrogacao').disabled=true;
	document.getElementById('dtCancelamentoDepositoIdentificado').value = document.getElementById('hdd-dataAtual').value;
}

function habilitarProrrogacao() {
	document.getElementById('img_dtVencimentoProrrogacao').disabled=false;
	document.getElementById('dtVencimentoProrrogacao').disabled=false;
	if(document.getElementById('hdd-indicadorDepositoCancelado').value != 'S'){
		document.getElementById('dtCancelamentoDepositoIdentificado').value='';
	}
}