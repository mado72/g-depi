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
if (!window.console) {
	(function (con) {
		con.log = con.profile = con.profileEnd = con.timeStamp = con.trace =
			con.debug = con.info = con.warn = con.error = con.dir = con.dirxml =
				con.group = con.groupCollapsed = con.groupEnd = con.time = con.timeEnd =
					con.assert = con.count = con.clear = function(){};
	})(window.console = window.console || {});
}
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
if (!Array.prototype.sort) {
	Array.prototype.sort = function(compareFn) {
	    log("Inside our Array.sort implementation :)")
	    return mergeSort(this)
	    // Split the array into halves and merge them recursively 
	    function mergeSort(arr) {
	        if (arr.length === 1) {
	            // return once we hit an array with a single item
	            return arr
	        }
	        var middle = Math.floor(arr.length / 2) // get the middle item of the array rounded down
	        var left = arr.slice(0, middle) // items on the left side
	        var right = arr.slice(middle) // items on the right side
	        return merge(
	            mergeSort(left),
	            mergeSort(right)
	        )
	    }
	    // compare the arrays item by item and return the concatenated result
	    function merge(left, right) {
	        var result = []
	        var indexLeft = 0
	        var indexRight = 0
	        while (indexLeft < left.length && indexRight < right.length) {
	            //compareFn ? compareFn =()=> left[indexLeft] < right[indexRight] : compareFn
	            var _left = left[indexLeft]
	            var _right = right[indexRight]
	            if (compareFn)
	                compareFn = composeCompareFn(compareFn(left, right));
	            compareFn = function(l, r) { return l < r; }
	            if (compareFn(_left, _right)) {
	                result.push(left[indexLeft])
	                indexLeft++
	            } else {
	                result.push(right[indexRight])
	                indexRight++
	            }
	        }
	        return result.concat(left.slice(indexLeft)).concat(right.slice(indexRight))
	    }
	    function composeCompareFn(compareResult) {
	        if (Math.sign(compareResult) < 0)
	            return false
	        if (Math.sign(compareResult) > 0)
	            return true
	        if (compareResult == 0)
	            return false
	    }
	};
}
if (typeof String.prototype.trim !== 'function') {
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, '');
	};
}
if (!Array.isArray) {
	Array.isArray = function(arg) {
		return Object.prototype.toString.call(arg) === '[object Array]';
	};
}

console.log("depi-cadastro.js");

var fnReady = function ($) {

	function getFormData($form){
	    var unindexed_array = $form.serializeArray();
	    var indexed_array = {};

	    $.map(unindexed_array, function(n, i){
	    	var value = n['value'];
			var name = n['name'];
			if (indexed_array[name] === undefined) {
	    		indexed_array[name] = value;
	    	}
	    	else {
	    		if (typeof indexed_array[name] === 'string') {
	    			indexed_array[name] = [indexed_array[name]];
	    		}
	    		indexed_array[name].push(value);
	    	}
	    });

	    return indexed_array;
	}

	function checkTodos() {
		var checkTodos = $(".checkTodos");
		checkTodos.change(function(ev){
			var form = checkTodos.closest("form");
			var items = form.find("input[type='checkbox']");
			var checked = checkTodos.prop("checked");
			items.prop("checked", checked);
		});
	};


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
	
	// dropboxCodDesc
	// ---------------------------------------------------------------------
	$.namespace( '$.dpcoddesc' );
	$.dpcoddesc.combinar = function(opcoes) {
		var dpcod = $(opcoes[0]),
			dpdesc = $(opcoes[1]);
		
		dpcod.change(function(ev){
			ev.stopPropagation();
			dpdesc.val(dpcod.val());
		});
		
		dpdesc.change(function(ev){
			ev.stopPropagation();
			dpcod.val(dpdesc.val());
		});
	};
	
	$.dpcoddesc.aninhar = function(opcoes) {
		var destino = [$(opcoes.destino[0]), $(opcoes.destino[1])],
			origem = [$(opcoes.origem[0]), $(opcoes.origem[1])];

		$(opcoes.destino.join(',')).on('carregando', function(ev) {
			$this = $(ev.target);
			if (! ev.concluiu) {
				$this.find('option').remove().end();
				$this.append($('<option>', {text: 'Carregando...', value: ''}));
			}
		});
		
		var onChange = function(ev){
			var v = $(ev.currentTarget).val();

			if (typeof opcoes.onStart === 'function') {
				opcoes.onStart(origem[0], origem[1], destino[0], destino[1]);
			}

			var url = typeof opcoes.url === 'function' ? opcoes.url(v) : opcoes.url.replace('%d', v);
			
			$(destino).each(function(i, v){
				$(v).trigger({type: 'carregando', origem: origem[0], concluiu: false });
			});
		
			if (!v) {
				$(destino).each(function(i, v){
					$(v).trigger({type: 'carregando', origem: origem[0], concluiu: true });
					$(destino).each(function(i, v){$(v).find('option').remove().end();});
				});
				return;
			}
			// Consulta Ajax para obter códigos e descrições
			$.ajax({
				url : url,
				type : "GET",
				dataType : "json",
				success : function(data) {
					var codigos = $(), nomes = $();

					$(data.response).each(function(i,v) {
						var item = opcoes.fn(v);
						codigos = codigos.add($('<option>', {value: item[0], text: item[1]}));
						if (item[2])
						    nomes = nomes.add($("<option>", {value: item[0], text: item[2]}));
					});

					$(destino).each(function(i, v){$(v).find('option').remove().end();});
					destino[0].append(codigos);
					destino[1] && destino[1].append(nomes);
					$(destino).each(function(i, v){
						$(v).trigger({type: 'carregando', origem: origem[0], concluiu: true });
					});
					if (typeof opcoes.onFinish === 'function') {
						opcoes.onFinish(origem[0], origem[1], destino[0], destino[1], data.response);
					}
				},
				error : function(data) {
					opcoes.error(data);
				}
			});
		};

		origem[0].change(onChange);
		origem[1].change(onChange);
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
			jqValor = jqForm.find("#ValorFiltro"),
			jqRecipiente = jqForm.find("#Lista"),
			btnIncluir = $(".btnIncluir"),
			btnPlus = jqForm.find("#BtnPlus"),
			btnMinus = jqForm.find("#BtnMinus");
		
		jqPrincipal.change(function(ev){
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
				
				$.filtro.arrumarValor(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
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
		
		jqValor.on("input keydown keyup mousedown mouseup select", function(ev){
				$.filtro.arrumarValor(jqPrincipal, jqSecundario, jqValor, jqRecipiente);
			});

		btnPlus.click(function(ev){
			ev.preventDefault();
			var optPrincipal = jqPrincipal.find("option:selected");
			var optSecundario = jqSecundario.find("option:selected");
			
			if (jqValor.val().trim().length == 0) {
				ev.stopPropagation();
				alert(MENSAGEM["msg.valor.invalido"]);
				return;
			}

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
			return false;
		});

		btnMinus.click(function(ev) {
			ev.preventDefault();
			var opt = jqRecipiente.find('option:selected');

			dados.recipiente.splice(opt.index());
			opt.remove();
			return false;
		});

		$("#BtnConsultar").click(function(ev) {
			ev.preventDefault();
			if ($.filtro.prepararConsultar(ev, jqForm, jqRecipiente)) {
    			$(this).closest("form").submit();
			}
		});
		
		btnIncluir.click(function(ev) {
			ev.preventDefault();
			ev.stopImmediatePropagation();
			$("#box_loading").show();
			setTimeout(function(){
				window.location.href = btnIncluir.attr('href');
			}, 250);
		});

		// Definir valores iniciais
		$(dados.principal).each(function(idx, item) {
			jqPrincipal.append($('<option>', {text: item.texto, value: item.valor, t: item.tipo, s: item.tam}));
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
	
	$.filtro.prepararConsultar = function(ev, jqForm, jqRecipiente) {
		var elements = $();

		var criterios = jqRecipiente.find("option");
		if (criterios.length == 0) {
			if (! window.confirm(MENSAGEM["msg.confirmacao.consulta"])) {
				ev.preventDefault();
				ev.stopImmediatePropagation();
				return false;
			};
		}

		if (criterios.length > 0) {
			criterios.each(function(idx, opt) {
				elements = elements.add($('<input>', { type:"hidden", name: "criteriosInformados" , value: $(opt).val()}));
			});
		}
		else {
			elements = elements.add($('<input>', { type:"hidden", name: "criteriosInformados" , value: null}));
		}
		
		$("#box_loading").show();
		
		jqForm.append(elements);
		return true;
	};

	$.filtro.adicionarCriterio = function(jqRecipiente, item) {
		var opt = jqRecipiente.append($('<option>', {text: item.texto, value: item.prop}));
		opt.data("dados", item);
	};

	$.filtro.arrumarValor = function(jqPrincipal, jqSecundario, jqValor, jqRecipiente) {
		var opt = jqPrincipal.find("option:selected");
		
		var validacao = {
				t: opt.attr("t"),
				s: parseInt(opt.attr("s")) || -1
		};
		
		var value = jqValor.val();

		if (validacao.t === "NUM") {
			value = value.replace(/[^0-9]+/g, '');
		} else {
			value = value.toLocaleUpperCase();
		}
		
		if (validacao.s > -1 && value.length > validacao.s) {
			value = value.substring(0, validacao.s);
		}
		
		jqValor.val(value);
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
			ev.preventDefault();
			ev.stopPropagation();
			
			var marcados = $.obterMarcados(jqForm);
			if (marcados.length == 0) {
				alert(MENSAGEM['msg.selecao.exclusao']);
			} else if (confirm(MENSAGEM['msg.confirmacao.exclusao'])) {
				$("#box_loading").show();
				var action = jqForm.attr("action");
				action = action.replace(/\/\w+.do/, "/excluir.do");
				jqForm.attr("action", action);
				jqForm.submit();
			}
		});
		
		btnAlterar.click(function(ev) {
			ev.preventDefault();
			ev.stopPropagation();
			
			var marcados = $.obterMarcados(jqForm);
			if (marcados.length != 1) {
				alert(MENSAGEM["msg.selecao.edicao"]);
			} else {
				$("#box_loading").show();
				var action = jqForm.attr("action");
				action = action.replace(/\/\w+.do/, "/alterar.do");
				jqForm.attr("action", action);
				jqForm.submit();
			}
		});

		checkTodos();
//		jqForm.find(".checkTodos").click(function(){
//			jqForm.find(".checkTodos")
//				.parents("table:first")
//				.find("input:checkbox")
//				.prop("checked", $(".checkTodos").prop("checked"));
//		});
	};
	
	$.obterMarcados = function(jqForm) {
		var marcados = jqForm.find(".checkTodos")
			.parents("table:first")
			.find('input:checkbox:checked[name="codigo"]');
		return marcados;
	};

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
						jqSecundario.append($('<option>', {text: op.descricao, value: op.descricao}));
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
	};
	
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
	    		&& validarCheckCobs()
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
	
	// grupoacesso
	// ---------------------------------------------------------------------
	$.namespace( '$.grupoacesso' );
	$.grupoacesso.preencherCompanhias = function(cias) {
		var codigos = $(), nomes = $();
		$(cias).each(function(i,v){
			codigos = codigos.add($('<option>', {text: v.codigoCompanhia, value: v.codigoCompanhia}));
			nomes = nomes.add($("<option>", {value: v.codigoCompanhia, text: v.descricaoCompanhia}));
		});
		
		$('.companhia-codigo-dropbox').append(codigos);
		$('.companhia-nome-dropbox').append(nomes);
	};
	$.grupoacesso.preencherDepartamentos = function(deptos) {
		var codigos = $(), nomes = $(), codDeptoCombo=$('.departamento-codigo-dropbox'), nomDeptoCombo=$('.departamento-nome-dropbox');
		codDeptoCombo.find("option").remove();
		nomDeptoCombo.find("option").remove();
		$(deptos).each(function(i,v){
			codigos = codigos.add($('<option>', {text: v.siglaDepartamento, value: v.siglaDepartamento}));
			nomes = nomes.add($("<option>", {value: v.siglaDepartamento, text: v.nomeDepartamento}));
		});
		
		codDeptoCombo.append(codigos);
		nomDeptoCombo.append(nomes);
		
		// IE 7 tem um bug que faz diminuir a largura da combo, quando altera os options. Código abaixo é para forçar a remodelagem da combo.
		codDeptoCombo.toggleClass("w-100", false).toggleClass("w-100", true);
		nomDeptoCombo.toggleClass("w-100", false).toggleClass("w-100", true);
	};

	$.grupoacesso.prepararEditar = function(opcoes) {
		checkTodos();
		$.dpcoddesc.combinar(['.companhia-codigo-dropbox','.companhia-nome-dropbox']);
		$.dpcoddesc.combinar(['.departamento-codigo-dropbox','.departamento-nome-dropbox']);
		$.dpcoddesc.aninhar({
			origem: ['.companhia-codigo-dropbox', '.companhia-nome-dropbox'],
			destino: ['.departamento-codigo-dropbox', '.departamento-nome-dropbox'],
			url: opcoes.urlDepto,
			fn: function(v) {
				return [v.siglaDepartamento, v.siglaDepartamento, v.nomeDepartamento];
			},
			error: opcoes.error
		});
		
		$('.btnRemover').click(function(ev){
			ev.stopPropagation();
		
			var checked = $('input[type="checkbox"][name="codFuncionarios"]:checked');
			var values = [];
			$(checked).each(function(i,v){values.push($(v).val());});
			if (values.length) {
				checked.closest('tr').remove();
				$(values).each(function(i,v){
					$('input[type="checkbox"][value="'+v+'"]').remove();
				});
			}
			else {
				alert(MENSAGEM["msg.selecao.exclusao"]);
			}
		});

		$("#AcaoForm").submit(function(){
			console.log("#AcaoForm.submit");
			$("input:disabled").prop("disabled", false);
			$("select:disabled").prop("disabled", false);
			$("input[type='checkbox']").prop("checked", true);
		});
	};
	
	// deptoCia
	// ---------------------------------------------------------------------
	$.namespace( '$.deptoCia' );
	$.deptoCia.preencherCompanhias = function(cias) {
		var codigos = $(), nomes = $();
		$(cias).each(function(i,v){
			codigos = codigos.add($('<option>', {text: v.codigoCompanhia, value: v.codigoCompanhia}));
			nomes = nomes.add($("<option>", {value: v.codigoCompanhia, text: v.descricaoCompanhia}));
		});
		
		$('.companhia-codigo-dropbox').append(codigos);
		$('.companhia-nome-dropbox').append(nomes);
	};
	$.deptoCia.prepararEditar = function(opcoes) {
		checkTodos();
		$.dpcoddesc.combinar(['.companhia-codigo-dropbox','.companhia-nome-dropbox']);
		
		$('.btnRemover').click(function(ev){
			ev.stopPropagation();
			
			var checked = $('input[type="checkbox"][name="siglaDepartamentos"]:checked');
			var values = [];
			$(checked).each(function(i,v){values.push($(v).val());});
			if (values.length) {
				checked.closest('tr').remove();
				$(values).each(function(i,v){
					$('input[type="checkbox"][value="'+v+'"]').remove();
				});
			}
			else {
				alert(MENSAGEM["msg.selecao.exclusao"]);
			}
		});
		
		$("#AcaoForm").submit(function(){
			console.log("#AcaoForm.submit");
			$("input:disabled").prop("disabled", false);
			$("select:disabled").prop("disabled", false);
			$("input[type='checkbox']").prop("checked", true);
		});
	};
	
	// popupDepto
	// ---------------------------------------------------------------------
	$.namespace( '$.popupDepto' );
	$.popupDepto.prepararOpener = function(opcoes) {
		window.name = "_opener";
		
		$(opcoes.btn).click(function(){
//			$("#box_loading").show();
			var codCompanhia = $("#AcaoForm_codCompanhia").val();
			var url = opcoes.url + '&codCompanhia=' + codCompanhia;
			window.open(url, 'SelDeptos', "height=550,width=800,resizable=no");
			
			var action = $("#AcaoForm").attr("action").replace(/\/\w+.do/, "/refrescar.do");
			var codDeptos=$("#AcaoForm").find("input[name='siglaDepartamentos']");
			codDeptos.prop("checked", true);
			var data = getFormData($("#AcaoForm"));
			codDeptos.prop("checked", false);
			data.codCompanhia = codCompanhia;
			
			$.ajax({
				url : action,
				type : "POST",
				dataType : "json",
				data: data,
				traditional: true,
				success : function(data) {
					console.log(data);
				},
				error : function(data) {
					console.error(data);
				}
			});
		});
	};
	
	// deptoCia
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.deptoCia' );
	
	$.deptoCia.prepararFormulario = function(opcoes) {
		$("#AcaoForm").submit(function(ev){
			setTimeout(function(){
				window.close();
			}, 500);
		});
	};
	
	// popupFuncionario
	// ---------------------------------------------------------------------
	$.namespace( '$.popupFuncionario' );
	$.popupFuncionario.prepararOpener = function(opcoes) {
		window.name = "_opener";
		
		$(opcoes.btn).click(function(){
//			$("#box_loading").show();
			var codCompanhia = $("#AcaoForm_codCompanhia").val();
			var siglaDepartamento = $("#AcaoForm_siglaDepartamento").val();
			var url = opcoes.url + '&codCompanhia=' + codCompanhia + '&siglaDepartamento=' + siglaDepartamento;
			window.open(url, 'SelFuncionarios', "height=550,width=800,resizable=no");
			
			var action = $("#AcaoForm").attr("action").replace(/\/\w+.do/, "/refrescar.do");
//			$("#AcaoForm").attr("action", action);
//			$("#AcaoForm").submit();
			var codFuncionarios=$("#AcaoForm").find("input[name='codFuncionarios']");
			codFuncionarios.prop("checked", true);
			var data = getFormData($("#AcaoForm"));
			codFuncionarios.prop("checked", false);
			data.codCompanhia = codCompanhia;
			data.siglaDepartamento = siglaDepartamento;
			
			$.ajax({
				url : action,
				type : "POST",
				dataType : "json",
				data: data,
				traditional: true,
				success : function(data) {
					console.log(data);
				},
				error : function(data) {
					console.error(data);
				}
			});
		});
	};
	
	// funcionario
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.funcionario' );
	
	$.funcionario.prepararFormulario = function(opcoes) {
		$("#AcaoForm").submit(function(ev){
			setTimeout(function(){
				window.close();
			}, 500);
		});
	};
	
	// parametro
	// ---------------------------------------------------------------------
	$.namespace('$.parametro');
	
	$.parametro.prepararFormulario = function(opcoes) {
		$.dpcoddesc.combinar(['.companhia-codigo-dropbox','.companhia-nome-dropbox']);
		$.dpcoddesc.combinar(['.departamento-codigo-dropbox','.departamento-nome-dropbox']);
		$.dpcoddesc.aninhar({
			origem: ['.companhia-codigo-dropbox', '.companhia-nome-dropbox'],
			destino: ['.departamento-codigo-dropbox', '.departamento-nome-dropbox'],
			url: opcoes.urlDepto,
			fn: function(v) {
				return [v.codigoDepartamento, v.siglaDepartamento, v.nomeDepartamento];
			},
			error: opcoes.error
		});
	 	$("#AcaoForm_codigoMotivoDeposito").change(function(){
	 		var item = $("#AcaoForm_codigoMotivoDeposito").val();
			$("#AcaoForm_descricaoDetalhadaMotivo").val(opcoes.motivos[item]);
	 	});
	 	$("#AcaoForm_codigoMotivoDeposito").change();
	 	$("#AcaoForm input[type=\"radio\"][name=\"codigoBancoVencimento\"]").change(function() {
	 		var v = $("#AcaoForm input[type=\"radio\"][name=\"codigoBancoVencimento\"]:checked").val(),
	 			n = $("#AcaoForm input[name=\"numeroDiasAposVencimento\"]");
	 		n.prop("disabled", v == "N");
	 		if (v == "N")
	 			n.val("");
	 	});
	 	$("#AcaoForm input[type=\"radio\"][name=\"codigoBancoVencimento\"]").change();
	 	
	 	var numeroDiasAposVencimento=$("#AcaoForm_numeroDiasAposVencimento");
		numeroDiasAposVencimento.on("input keydown keyup mousedown mouseup select", function(ev){
	 		var value = numeroDiasAposVencimento.val();
	 		value = value.replace(/[^0-9]+/g, '');
	 		numeroDiasAposVencimento.val(value);
	 	});
	 	
	};
	
	// contaCorrente
	// ---------------------------------------------------------------------
	$.namespace('$.contaCorrente');
	
	$.contaCorrente.buscarInfoContaInterna = function(url) {
		$("#contaInternaCC").text("");
		var data = {
			codigoCia: $("#AcaoForm_codigoCompanhia").val(),
			codigoBanco: $("#AcaoForm_codigoBanco").val(),
			codigoAgencia: $("#AcaoForm_agencia").val(),
			contaCorrente: $("#AcaoForm_contaCorrente").val()
		};
		if (! data.codigoCia || ! data.codigoBanco || ! data.codigoAgencia || ! data.contaCorrente)
			return;
		$("#box_loading").show();
		$.ajax({
			url : url,
			type : "POST",
			dataType : "json",
			data: data,
			success : function(data) {
				$("#box_loading").hide();
				if (data.response) {
					if (data.response.contaInterna)
						$("#contaInternaCC").text(data.response.contaInterna);
				}
				else
					$.alertas.aviso((data.erro && data.erro.erro) || 'Conta não encontrada', $.alertas.ERRO);
			},
			error : function(data) {
				$("#box_loading").hide();
				console.error(data);
			}
		});
	};
	
	$.contaCorrente.buscarInfoAgencia = function(url) {
		$("#descricaoAgencia").text("");
		$("#box_loading").show();

		var data = {
			codigoBanco: $("#AcaoForm_codigoBanco").val(),
			codigoAgencia: $("#AcaoForm_agencia").val()
		};
		$.ajax({
			url : url,
			type : "POST",
			dataType : "json",
			data: data,
			success : function(data) {
				$("#box_loading").hide();
				if (data.response)
					if (data.response.agencia)
						$("#descricaoAgencia").text(data.response.agencia);
					else
						$.alertas.aviso('Agência não encontrada', $.alertas.ERRO);
			},
			error : function(data) {
				$("#box_loading").hide();
				console.error(data);
			}
		});
	};
	
	$.contaCorrente.prepararFormulario = function(opcoes) {
		$.dpcoddesc.combinar(['.companhia-codigo-dropbox','.companhia-nome-dropbox']);
		
		$("#AcaoForm_codigoBanco,#AcaoForm_agencia,#AcaoForm_contaCorrente,#AcaoForm_trps").on("input keydown keyup mousedown mouseup select", function(ev, t){
			$(this).val($(this).val().replace(/[^0-9]+/g,''));
		});
		
		$("#AcaoForm_agencia").change(function(ev){
			$.contaCorrente.buscarInfoAgencia(opcoes.urlAgencia);
		});
		
		$("#AcaoForm_codigoCompanhia,AcaoForm_codigoBanco,#AcaoForm_agencia,#AcaoForm_contaCorrente").change(function(ev) {
			$.contaCorrente.buscarInfoContaInterna(opcoes.urlContaInterna);
		});
		
	};
	
	// associarMotivos
	// ---------------------------------------------------------------------
	$.namespace( '$.associarMotivos' );
	$.associarMotivos.preencherCompanhias = function(cias) {
		var codigos = $(), nomes = $();
		$(cias).each(function(i,v){
			codigos = codigos.add($('<option>', {text: v.codigoCompanhia, value: v.codigoCompanhia}));
			nomes = nomes.add($("<option>", {value: v.codigoCompanhia, text: v.descricaoCompanhia}));
		});
		
		$('.companhia-codigo-dropbox').append(codigos);
		$('.companhia-nome-dropbox').append(nomes);
	};
	$.associarMotivos.prepararEditar = function(opcoes) {
		opcoes.error = opcoes.error || function(erro) { console.error(erro); };
		var afterInit = false;

		$("#AcaoForm_contaCorrente").change(function() {
			var conta = $("#AcaoForm_contaCorrente").val(),
				contaInterna = opcoes.contas[conta];
			console.log('mudou conta');
			$("#AcaoForm_contaInterna").val(contaInterna);
		});
	 	$("#AcaoForm_codigoMotivoDeposito").change(function(){
	 		var item = $("#AcaoForm_codigoMotivoDeposito").val();
	 		console.log('mudou motivo');
			$("#AcaoForm_descricaoDetalhadaMotivo").val(opcoes.motivos[item]);
	 	});

		$('.companhia-codigo-dropbox').change(function(){
			if (! afterInit) return;
			console.log('mudou companhia');			$('.agencia-codigo-dropbox').change();
	 		/*
			$('.agencia-codigo-dropbox, .agencia-descricao-dropbox, #AcaoForm_contaCorrente').find("option").remove();
			$('.agencia-codigo-dropbox, .agencia-descricao-dropbox, #AcaoForm_contaCorrente').each(function(i,v){
				$(v).add($("<option>", {text: 'Carregando...'}));
			});
			*/
		});
		$('.banco-codigo-dropbox, .banco-descricao-dropbox').change(function(){
			if (! afterInit) return;
			console.log('mudou banco');
			/*
			$('#AcaoForm_contaCorrente').find("option").remove();
			$('#AcaoForm_contaCorrente').each(function(i,v){
				$(v).add($("<option>", {text: 'Carregando...'}));
			});
			*/
		});
		$('.banco-codigo-dropbox').on('carregando', function(ev){
			if (! afterInit) return;
			console.log('mudou banco');
			if (ev && ev.concluiu) {
				console.log('banco carregado');
				$('.banco-codigo-dropbox').change();
			}
			else {
				console.log('banco carregando', ev);
			}
		});
		
		$.dpcoddesc.combinar(['.companhia-codigo-dropbox','.companhia-nome-dropbox']);
		$.dpcoddesc.combinar(['.departamento-codigo-dropbox','.departamento-nome-dropbox']);
		$.dpcoddesc.combinar(['.banco-codigo-dropbox','.banco-descricao-dropbox']);
		$.dpcoddesc.combinar(['.agencia-codigo-dropbox', '.agencia-descricao-dropbox']);
		$.dpcoddesc.aninhar({
			origem: ['.companhia-codigo-dropbox', '.companhia-nome-dropbox'],
			destino: ['.departamento-codigo-dropbox', '.departamento-nome-dropbox'],
			url: opcoes.urlDepto,
			fn: function(v) {
				return [v.codigoDepartamento, v.siglaDepartamento, v.nomeDepartamento];
			},
			error: opcoes.error,
			onFinish: function() {console.log('definiu departamento');}
		});
		$.dpcoddesc.aninhar({
			origem: ['.companhia-codigo-dropbox', '.companhia-nome-dropbox'],
			destino: ['.banco-codigo-dropbox', '.banco-descricao-dropbox'],
			url: opcoes.urlBancos,
			fn: function(v) {
				return [v.cdBancoExterno, v.cdBancoExterno, v.descricaoBanco];
			},
			error: opcoes.error,
			onFinish: function() {
				console.log('definiu banco');
				$('.banco-codigo-dropbox').change();
			}
		});
		$.dpcoddesc.aninhar({
			origem: ['.banco-codigo-dropbox', '.banco-descricao-dropbox'],
			destino: ['.agencia-codigo-dropbox', '.agencia-descricao-dropbox'],
			url: function(v) { return opcoes.urlAgencias.replace('%d', $(".companhia-codigo-dropbox").val()).replace('%d', v);},
			fn: function(v) {
				return [v.cdAgenciaExterno, v.cdAgenciaExterno, v.descricaoAgencia];
			},
			error: opcoes.error,
			onFinish: function() {
				console.log('definiu agencia');
				$('.agencia-codigo-dropbox').change();
			}
		});
		$.dpcoddesc.aninhar({
			origem: ['.agencia-codigo-dropbox', '.agencia-descricao-dropbox'],
			destino: ['#AcaoForm_contaCorrente'],
			url: function(v) { 
				var url = opcoes.urlContas.replace('%d', $(".companhia-codigo-dropbox").val())
					.replace('%d', $('.banco-codigo-dropbox').val())
					.replace('%d', $('.agencia-codigo-dropbox').val());
				return url;
			},
			fn: function(v) {
				return [v.contaCorrente, v.contaCorrente, null];
			},
			error: opcoes.error,
			onStart: function() {
				$('#AcaoForm_contaInterna').val('');
			},
			onFinish: function(o1,o2,d1,d2,data) {
				console.log('definiu agencia');
				opcoes.contas = {};
				$(data).each(function(i,v){
					opcoes.contas[v.contaCorrente] = v.codigoInternoCC;
				});
				$("#AcaoForm_contaCorrente").change();
			}
		});
	 	$("#AcaoForm_codigoMotivoDeposito").change();
	 	afterInit = true;
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
		opcoes.pgs =  Math.floor((opcoes.jqTrs.length - 1)/ opcoes.registros) + 1;

		var pages = [];
		pages.push("<span class='Continue Before'>...</span>");
		for (var i = 1; i <= opcoes.pgs; i++) {
			var epg = "<a class='GoPage' page='"+(i-1)+"'>"+i+"</a>";
			pages.push(epg);
		}
		pages.push("<span class='Continue After'>...</span>");

		pagSel.find('.nav_pages').append($(pages.join('')));
		pagSel.find('.GoPage').click(function(ev){
			var page = parseInt($(this).attr("page"));
			$.paginacao.irPara(opcoes, page);
		});

		pagSel.find('.GoFirst').click(function(){$.paginacao.goFirst(opcoes);});
		pagSel.find('.GoPrev').click(function(){$.paginacao.goPrev(opcoes);});
		pagSel.find('.GoNext').click(function(){$.paginacao.goNext(opcoes);});
		pagSel.find('.GoLast').click(function(){$.paginacao.goLast(opcoes);});
		pagSel.find('.Continue').hide();

		$.paginacao.goFirst(opcoes);
	};
	
	$.paginacao.irPara = function(opcoes, pagina) {
		if (pagina < 0 || pagina >= opcoes.pgs) {
			return;
		}
		opcoes.corrente = pagina;
		opcoes.idxInicial = opcoes.registros * pagina;
		opcoes.idxFinal = opcoes.idxInicial + opcoes.registros - 1;

		opcoes.jqTrs.hide();
		var pgTrs = opcoes.jqTrs.slice(opcoes.idxInicial, opcoes.idxFinal + 1);
		pgTrs.show();

		var idxi = opcoes.idxInicial+1;
		var idxf = Math.min(opcoes.idxFinal + 1, opcoes.jqTrs.length);
		
		var pagSel = $(opcoes.pagSeletor);
		var ultPagina = pagina + 1 >= opcoes.pgs;
		pagSel.find('.GoLast').toggleClass("disabled", ultPagina);
		pagSel.find('.GoNext').toggleClass("disabled", ultPagina);
		pagSel.find('.GoFirst').toggleClass("disabled", pagina < 1);
		pagSel.find('.GoPrev').toggleClass("disabled", pagina < 1);
		
		opcoes.info.text(function(){
			var txt = opcoes.pattern.replace(/:reg/, opcoes.jqTrs.length).replace(/:idxIni/, idxi).replace(/:idxFin/, idxf);
			return txt;
		});
		
		if (opcoes.pgs > 10) {
			var gos = pagSel.find('.nav_pages .GoPage');
			gos.hide();
			var start = Math.max(0, pagina - 5);
			var finish = Math.min(opcoes.pgs, start + 10);
			gos.slice(start, finish).show();
			pagSel.find('.Continue.Before').toggle(start > 1);
			pagSel.find('.Continue.After').toggle(finish < opcoes.pgs - 1);
		}
		
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
	
	$(".sortable th").click(function(ev) {
		ev.stopPropagation();
		var th = $(this),
			thIndex = th.index(),
			table = th.parents('table')[0];
		
		if (thIndex == 0) {
			return;
		}
		
		table = $(table);
		
		table.find('td').filter(function(){
			
			return $(this).index() === thIndex;
			
		}).sort(function(a, b){
			
			if( $.text([a]) == $.text([b]) )
				return 0;
			
			return $.text([a]) > $.text([b]) ?
					1 : -1;
			
		}, function(){
			return this.parentNode; 
			
		});

	});
	
	// alertas
	// ---------------------------------------------------------------------
	// definition
	$.namespace( '$.alertas' );
	
	$.alertas.quantos = 0;
	$.alertas.INFO = 'INFO';
	$.alertas.ERRO = 'ERRO';
	
	$.alertas.templates = {
			INFO: '<table class="tabela_sucesso info_temp" id="AVISO_%d"><tr><td>%s</td></tr></table>',
			ERRO: '<table class="tabela_verm info_temp" id="AVISO_%d"><tr><td>%s</td></tr></table>'
	};
	
	$.alertas.aviso = function(mensagem, tipo, timeout) {
		var template = tipo == $.alertas.ERRO ? $.alertas.templates.ERRO : $.alertas.templates.INFO,
				id = ++$.alertas.quantos;
		
		template = $(template.replace('%s', mensagem).replace('%d', id));
		
		$("#Mensagens").append(template);

		setTimeout(function(){
			$("#Mensagens #AVISO_"+id).fadeOut("slow", function() {
				$("#Mensagens #AVISO_"+id).remove();
			});
		}, timeout || 3500);
	};
	
};

jQuery(document).ready(fnReady(jQuery));

console.log("FIM");

