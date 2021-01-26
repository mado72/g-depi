//Detecting IE
var oldIE;
if ($('html').is('.lt-ie7, .lt-ie8, .lt-ie9')) {
    oldIE = true;
}

if (oldIE) {
	if (!Array.prototype.indexOf) {
		Array.prototype.indexOf = function(a) {
			for (var b = this, c = 0, d = b.length; d > c; ++c)
				if (b[c] === a) return c;
			return -1;
		};
	}
	if (! Array.prototype.map) {
		Array.prototype.map = function(a, b) {
		    for (var g, c = this, d = [], e = 0, f = c.length; f > e; ++e) {
		    	g = c[e], d.push(a.call(b || window, c[e], e, c));
		    }
		    return d;
		};	
	}
	if (! Function.prototype.bind) {
		Function.prototype.bind = function(a) {
		    var b = this,
		        c = Array.prototype.slice.call(arguments, 1),
		        d = function() {},
		        e = function() {
		            return b.apply(this instanceof d && a ? this : a, Array.prototype.concat.apply(c, arguments))
		        };
		    return d.prototype = e.prototype = b.prototype, e;
		};		
	}
	if (!Object.keys) {
		Object.keys = function(a) {
		    var c, b = [];
		    for (c in a) Object.prototype.hasOwnProperty.call(a, c) && b.push(c);
		    return b
		};		
	}
}
(function (con) {
	con.log = con.profile = con.profileEnd = con.timeStamp = con.trace =
		con.debug = con.info = con.warn = con.error = con.dir = con.dirxml =
		con.group = con.groupCollapsed = con.groupEnd = con.time = con.timeEnd =
		con.assert = con.count = con.clear = function(){};
})(window.console = window.console || {});
if (! Array.prototype.find) {
	Array.prototype.find = function(predicate) {
		  if (this === null) {
		  throw new TypeError('Array.prototype.find called on null or undefined');
	  }
	  if (typeof predicate !== 'function') {
		  throw new TypeError('predicate must be a function');
	  }
	  var list = Object(this);
	  var length = list.length >>> 0;
	  var thisArg = arguments[1];
	  var value;

	  for (var i = 0; i < length; i++) {
		  value = list[i];
		  if (predicate.call(thisArg, value, i, list)) {
			  return value;
		  }
	  }
	  return undefined;
  };
}

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
	        };
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

		var jqForm = $(formSeletor);
		jqForm.data("dados", dados);

		var jqPrincipal = jqForm.find("#DropboxPrincipal"),
			jqSecundario = jqForm.find("#DropboxSecundario"),
			jqValor = jqForm.find("#Valor"),
			jqRecipiente = jqForm.find("#Lista"),
			btnConsultar = $("#BtnConsultar"),
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
						jqSecundario.append($('<option>', {text: op.texto, value: op.valor}));
					});
				};
			});

		jqSecundario
			.change(function(ev){
				$.filtro.arrumarValor(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});

		jqRecipiente
			.change(function(ev) {
				var opt = jqRecipiente.find("option:selected");
				
				if (opt.length == 1) {
					var value = opt.val().split(';');
					jqPrincipal.val(value[0]);
					jqPrincipal.change();
					jqSecundario.val(value[1]);
					jqValor.val(value[2]);
				};
			});
		
		jqValor
			.keypress(function(ev){
				$.filtro.arrumarValor(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});

		btnPlus.click(function(ev){
			var optPrincipal = jqPrincipal.find("option:selected");
			var optSecundario = jqSecundario.find("option:selected");

			var item = {
				principal: optPrincipal.text(),
				secundario: optSecundario.val(),
				valor: jqValor.val().toLocaleUpperCase()
			};

			item.texto = [optPrincipal.text(), optSecundario.text(), item.valor].join(' ');
		    item.prop = [optPrincipal.val(), optSecundario.val(), item.valor].join(';');
			
			dados.recipiente.push(item);

			$.filtro.adicionarCriterio(jqRecipiente, item);

			jqValor.val('');
			jqPrincipal.prop("selectedIndex", 0);
			jqPrincipal.change();
			jqSecundario.prop("selectedIndex", 0);
		});

		btnMinus.click(function(ev) {
			var opt = jqRecipiente
				.find('option:selected');

			dados.recipiente.splice(opt.index());
			opt.remove();
		});

		btnConsultar.click(function(ev) {
			var elements = $();

			var criterios = jqRecipiente.find("option");
			if (criterios.length == 0) {
				if (! window.confirm(MENSAGEM["msg.confirmacao.consulta"])) {
					ev.stopPropagation();
					return;
				};
			}

			criterios.each(function(idx, opt) {
				elements = elements.add($('<input>', { type:"hidden", name: "criteriosInformados" , value: $(opt).val()}));
			});
			
			$("#box_loading").show();
			
			jqForm.append(elements);

			jqForm.submit();

			jqForm.find("input:hidden").remove().end();
		});

		// Definir valores iniciais
		$(dados.principal).each(function(idx, item) {
			var opt = jqPrincipal.append($('<option>', {text: item.texto, value: item.valor}));
			opt.data("sublista", item.sublista);
		});

		if (dados.recipiente) {
			$(dados.recipiente).each(function(idx, criterio) {
				var valores = criterio.split(';');
				var pri = dados.principal.find(function(v){
					return v.valor === valores[0];
				});
				var sec = pri.sublista.find(function(v){
					return v.valor === valores[1];
				});
				
				var item = {
					prop: criterio,
					texto: [pri.texto, sec.texto, valores[2]].join(' ')
				};

				$.filtro.adicionarCriterio(jqRecipiente, item);
			});
		}
		
		jqPrincipal.val($(jqPrincipal.find("option")[0]).val());
		jqPrincipal.change();

		jqRecipiente.val(null);
	};

	$.filtro.adicionarCriterio = function(jqRecipiente, item) {
		var opt = jqRecipiente.append($('<option>', {text: item.texto, value: item.prop}));
		opt.data("dados", item);
	};

	$.filtro.arrumarValor = function(jqPrincipal, jqSecundario, jqValor, jqRecipiente) {
		var opt = jqPrincipal.find("option:selected");

		if (opt.val() === "NUM") {
			jqValor.val(jqValor.val().replace(/D+//g));
		} else {
			jqValor.val(jqValor.val().toLocaleUpperCase());
		}
	};

	// consulta
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.consulta' );

	$.consulta.prepararFormulario = function(formSeletor) {
		var jqForm = $(formSeletor);

		var btnExcluir = jqForm.find("#BtnExcluir"),
			btnAlterar = jqForm.find("#BtnAlterar");
		
		btnExcluir.click(function(ev) {
			var marcados = $.obterMarcados(jqForm);
			if (marcados.length == 0) {
				alert(MENSAGEM['msg.selecao.exclusao']);
			} else if (confirm(MENSAGEM['msg.confirmacao.exclusao'])) {
				var action = jqForm.attr("action");
				action = action.replace(/\/\w+.do/, "/excluir.do");
				jqForm.attr("action", action);
				jqForm.submit();
			}
		});
		
		btnAlterar.click(function(ev) {
			var marcados = $.obterMarcados(jqForm);
			if (marcados.length != 1) {
				alert(MENSAGEM["msg.selecao.edicao"]);
			} else {
				var action = jqForm.attr("action");
				action = action.replace(/\/\w+.do/, "/alterar.do");
				jqForm.attr("action", action);
				jqForm.submit();
			}
		});

		jqForm.find(".checkTodos").click(function(){
			jqForm.find(".checkTodos")
				.parents("table:first")
				.find("input:checkbox")
				.prop("checked", $(".checkTodos").prop("checked"));
		});
	};

	$.obterMarcados = function(jqForm) {
		var marcados = jqForm.find(".checkTodos")
			.parents("table:first")
			.find('input:checkbox:checked[name="codigo"]');
		return marcados;
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

		if (opt.val() === "NUM") {
			jqValor.val(jqValor.val().replace(/D+//g));
		} else {
			jqValor.val(jqValor.val().toLocaleUpperCase());
		}
	};

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


	// paginacao
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.paginacao' );

	$.paginacao.paginar = function(opcoes) {
		var jqTbl = opcoes.jqTbl = $(opcoes.tblSeletor);
		var jqTrs = opcoes.jqTrs = jqTbl.find("TBODY TR");
		
		jqTbl.data("pagcorrente", 0);
		jqTbl.data("total", jqTrs.length);
		jqTrs.hide();

		var ctrls = $(["<span class='paginacao_info'></span>",
			"<span class='pre_pages'><a class='GoFirst'>Primeiro</a>/<a class='GoPrev'>Anterior</a></span>",
			"<span class='nav_pages'></span>",
			"<span class='pos_pages'><a class='GoNext'>Próximo</a>/<a class='GoLast'>Último</a></span>|"].join('|'));
		var pagSel = $(opcoes.pagSeletor);
		pagSel.append(ctrls);
		
		opcoes.info = pagSel.find(".paginacao_info");
		opcoes.pgs =  Math.floor(opcoes.jqTrs.length / opcoes.registros) + 1;

		var pages = [];
		for (var i = 1; i <= opcoes.pgs; i++) {
			var epg = "<a class='GoPage' page='"+(i-1)+"'>"+i+"</a>";
			pages.push(epg);
		}

		pagSel.find('.nav_pages').append($(pages.join(',')));
		pagSel.find('.GoPage').click(function(ev){
			var page = parseInt($(this).attr("page"));
			$.paginacao.irPara(opcoes, page);
		});

		pagSel.find('.GoFirst').click(function(){$.paginacao.goFirst(opcoes)});
		pagSel.find('.GoPrev').click(function(){$.paginacao.goPrev(opcoes)});
		pagSel.find('.GoNext').click(function(){$.paginacao.goNext(opcoes)});
		pagSel.find('.GoLast').click(function(){$.paginacao.goLast(opcoes)});

		$.paginacao.goFirst(opcoes);
	};
	
	$.paginacao.irPara = function(opcoes, pagina) {
		opcoes.corrente = pagina;
		opcoes.idxInicial = opcoes.registros * pagina;
		opcoes.idxFinal = opcoes.idxInicial + opcoes.registros - 1;

		opcoes.jqTrs.hide();
		var pgTrs = opcoes.jqTrs.slice(opcoes.idxInicial, opcoes.idxFinal);
		pgTrs.show();

		var idxi = opcoes.idxInicial+1;
		var idxf = Math.min(opcoes.idxFinal + 1, opcoes.jqTrs.length);

		opcoes.info.text(function(){
			var txt = opcoes.pattern.replace(/:reg/, opcoes.jqTrs.length).replace(/:idxIni/, idxi).replace(/:idxFin/, idxf);
			return txt;
		});
	};

	$.paginacao.goFirst = function(opcoes) {
		$.paginacao.irPara(opcoes, 0);
	};

	$.paginacao.goPrev = function(opcoes) {
		$.paginacao.irPara(opcoes, opcoes.corrente - 1);
	};

	$.paginacao.goNext = function(opcoes) {
		$.paginacao.irPara(opcoes, opcoes.corrente + 1);
	};

	$.paginacao.goLast = function(opcoes) {
		$.paginacao.irPara(opcoes, opcoes.pgs - 1 );
	};
};

jQuery(document).ready(fnReady(jQuery));

console.log("FIM");

