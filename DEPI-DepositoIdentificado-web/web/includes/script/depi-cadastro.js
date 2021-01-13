console.log("depi-cadastro.js");

var fnReady = function ($) {

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

	$.namespace('$.generico');

	$.generico.verificarCheckbox = function(checkname, key) {
		verificarBoxEdicao(checkname, MENSAGEM[key]);
	};

	// filtro
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.filtro' );
	
	/**
	 * info - objeto contendo a estrutura:
	 * {
	 * 	principal: seletor para o formulário de filtro
	 *	dados: {
	 *		principal: contém os dados para preencher o dropbox principal e secundário
	 *      recipiente: contém os critérios já selecionados
	 *	}
	});
	 * }
	 */
	$.filtro.prepararFormulario = function(formSeletor, dados) {

		console.log("Dados: ", dados);

		var jqForm = $(formSeletor)
		jqForm.data("dados", dados);

		var jqPrincipal = jqForm.find("#DropboxPrincipal"),
			jqSecundario = jqForm.find("#DropboxSecundario"),
			jqValor = jqForm.find("#Valor"),
			jqRecipiente = jqForm.find("#Lista"),
			btnConsultar = jqForm.find("#BtnConsultar"),
			btnIncluir = jqForm.find("#BtnIncluir"),
			btnPlus = jqForm.find("#BtnPlus"),
			btnMinus = jqForm.find("#BtnMinus");

		jqPrincipal
			.change(function(ev){
				var opt = jqPrincipal.find("option:selected");
				
				jqSecundario
					.find('option')
					.remove()
					.end();
				
				if (opt.length) {
					var sublista = dados.principal[opt.index()].sublista;

					$(sublista).each(function(idx, op){
						jqSecundario.append($('<option>', {text: op.texto, value: op.valor}))
					});
				}
			});

		jqSecundario
			.change(function(ev){
				$.filtro.arrumarValor(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});

		jqRecipiente
			.change(function(ev) {
				var opt = jqRecipiente.find("option:selected");
				
				if (opt.length == 1) {
					var recp = dados.recipiente[opt.index()];
					jqPrincipal.val(recp.principal);
					jqPrincipal.change();
					jqSecundario.val(recp.secundario);
					jqValor.val(recp.valor);
				}
			});
		
		jqValor
			.keypress(function(ev){
				$.filtro.arrumarValor(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});

		btnPlus.click(function(ev){
			var optPrincipal = jqPrincipal.find("option:selected");
			var optSecundario = jqSecundario.find("option:selected");

			var item = {
				principal: jqPrincipal.val(),
				secundario: jqSecundario.val(),
				valor: jqValor.val().toLocaleUpperCase(),
			}

			item.texto = [optPrincipal.text(), optSecundario.text(), item.valor].join(' ')
			
			dados.recipiente.push(item);

			$.filtro.adicionarCriterio(jqRecipiente, item);

			jqValor.val('');
			jqPrincipal.prop("selectedIndex", 0);
			jqPrincipal.change();
			jqSecundario.prop("selectedIndex", 0);
		})

		btnMinus.click(function(ev) {
			var opt = jqRecipiente
				.find('option:selected');

			dados.recipiente.splice(opt.index());
			opt.remove();
		})

		btnConsultar.click(function(ev) {
			var elements = $();
			var data = [];

			jqRecipiente.find("option").each(function(idx) {
					var item = dados.recipiente[idx];
					$([
						$('<input>', { name: "campo[" + idx + "]" , value: item.principal}),
						$('<input>', { name: "operacao[" + idx + "]" , value: item.secundario}),
						$('<input>', { name: "valor[" + idx + "]" , value: item.valor}),
					])
					.each(function(i, item){
						elements = elements.add(item);
					});
					
					data.push(item);
				});
			
			jqForm.append(elements);

			jqForm.submit();

			jqForm.find("input:hidden").remove().end();
		})

		btnIncluir.click(function(ev) {
			alert('Incluir');
		})
		
		// Definir valores iniciais
		$(dados.principal).each(function(idx, item) {
			var opt = jqPrincipal.append($('<option>', {text: item.texto, value: item.valor}));
			opt.data("sublista", item.sublista);
		});

		if (dados.recipiente) {
			$(dados.recipiente).each(function(idx, item) {
				$.filtro.adicionarCriterio(jqRecipiente, item);
			});
		}
		
		jqPrincipal.val(dados.principal[0].valor);
		jqPrincipal.change();

		jqRecipiente.val(null);
	};

	$.filtro.adicionarCriterio = function(jqRecipiente, item) {
		var opt = jqRecipiente.append($('<option>', {text: item.texto}));
		opt.data("dados", item);
	};

	$.filtro.arrumarValor = function(jqPrincipal, jqSecundario, jqValor, jqRecipiente) {
		var opt = jqPrincipal.find("option:selected");

		if (opt.val() === "NUM") {
			jqValor.val(jqValor.val().replace(/D+//g));
		}
		else {
			jqValor.val(jqValor.val().toLocaleUpperCase());
		}
	}

	// motivoDeposito
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.motivoDeposito' );
	
	/**
	 * info - objeto contendo a estrutura:
	 * {
	 * 	principal: seletor para o dropbox principal,
	 * 	secundario: seletor para o dropbox secundario,
	 * 	valor: seletor para o campo de valor,
	 * 	recipiente: seletor para a lista que contém os critérios de consulta,
	 *	dados: {
	 *		principal: contém os dados para preencher o dropbox principal e secundário
	 *      recipiente: contém os critérios já selecionados
	 *	}
	});
	 * }
	 */
	$.motivoDeposito.prepararFormulario = function(info) {

		var jqPrincipal = $(info.principal),
			jqSecundario = $(info.secundario),
			jqValor = $(info.valor),
			jqRecipiente = $(info.recipiente);

		jqPrincipal
			.change(function(ev){
				var opt = jqPrincipal.find("option:selected");
				var sel = dados.principal[opt.index()];

				jqSecundario
					.find('option')
					.remove()
					.end();
				
				if (!!sel) {
					$(sel.operacoes).each(function(idx, op){
						jqSecundario.append($('<option>', {text: op.descricao, value: op.descricao}))
					});
				}
			});

		jqSecundario
			.change(function(ev){
				$.motivoDeposito.arrumarFiltro(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});
			
		jqValor
			.keypress(function(ev){
				$.motivoDeposito.arrumarFiltro(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});
		
		// Definir valores iniciais
		$(dados.principal).each(function(idx, item) {
			jqPrincipal
			.append($('<option>', {text: item.motivo.descricao, value: item.motivo.tipo}));
		});
		
		jqPrincipal.val(dados.principal[0].motivo.tipo);
		jqPrincipal.change();
	};

	$.motivoDeposito.arrumarFiltro = function(jqPrincipal, jqSecundario, jqValor, jqRecipiente) {
		var opt = jqPrincipal.find("option:selected");
		var sel = dados.principal[opt.index()];

		if (opt.val() === "NUM") {
			jqValor.val(jqValor.val().replace(/D+//g));
		}
		else {
			jqValor.val(jqValor.val().toLocaleUpperCase());
		}
	}

	// checkbox obrigatório
	// Pré condições: deve ser chamada após a rotina "verificarBoxEdicao" que valida
	// se a quantidade de checks marcados é igual a um.
	// Pós condições: o usuário pode alterar, ou prorrogar um registro.
	$.motivoDeposito.verificarBoxEdicaoRegras = function() {
	}
	
	$.motivoDeposito.validarCheckCobs = function() {
	    var checkboxes = document.forms[0].elements['codigoAutorizador'];
	    var situacao = {};
	    if (checkboxes.length != undefined) {
	        for (var i = 0; i < checkboxes.length; i++) {
	            if (checkboxes[i].checked) {
	                situacao = document.getElementById('cod_situacao_' + checkboxes[i].value);
	            }
	        }
	    } else if (checkboxes.checked) {
	        situacao = document.getElementById('cod_situacao_' + checkboxes.value);
	    }

	    if (situacao.value != 0) {
	        alert("O depósito selecionado possui parcelas associadas ao sistema COBS e não pode ser alterado.");
	        return false;
	    } else {
	        return true;
	    }
	};

	$.motivoDeposito.novo = function() {
	    submitForm('novo', PAGE_CONTEXT + '/deposito/CadastrarDeposito.do');
	    return true;
	};

	$.motivoDeposito.editar = function() {
	    return ($.generico.verificarCheckbox('codigoAutorizador', 'msg.selecao.edicao') 
	    		&& verificarBoxEdicaoRegras() 
	    		&& validarCheckCobs() 
	    		&& submitForm('editar', PAGE_CONTEXT + '/deposito/CadastrarDeposito.do'));
	};

	$.motivoDeposito.prorrogar = function() {
	    return ($.generico.verificarCheckbox('codigoAutorizador', 'msg.selecao.edicao') 
	    		&& validarCheckCobs() /* && verificarBoxEdicaoRegras() */
	    		&& submitForm('prorrogar', PAGE_CONTEXT + '/deposito/CadastrarDeposito.do'));
	};

	$.motivoDeposito.manter = function() {
	    return ($.generico.verificarCheckbox('codigoAutorizador', 'msg.selecao.edicao') 
	    		&& verificarBoxEdicaoRegras() 
	    		&& validarCheckCobs() 
	    		&& submitForm('manter', PAGE_CONTEXT + '/deposito/ManterMovimento.do'));
	};

	$.motivoDeposito.controlar = function() {
	    return ($.generico.verificarCheckbox('codigoAutorizador', 'msg.selecao.edicao')
	    		&& submitForm('controlar', PAGE_CONTEXT + '/deposito/ControlarDeposito.do'));
	};

	$.motivoDeposito.consultarLancamento = function() {
	    return ($.generico.verificarCheckbox('codigoAutorizador', 'msg.selecao.edicao') 
	    		&& submitForm('consultarLancamento', PAGE_CONTEXT + '/deposito/ConsultarLancamentoDeposito.do'));
	};

	$.motivoDeposito.excluir = function() {
	    return (verificarBoxExclusao('codigoAutorizador', 'msg.confirmacao.exclusao', 'msg.selecao.exclusao') 
	    		&& submitForm('excluir', PAGE_CONTEXT + '/deposito/CadastrarDeposito.do'));
	};

};

jQuery(document).ready(fnReady(jQuery));

console.log("FIM");


