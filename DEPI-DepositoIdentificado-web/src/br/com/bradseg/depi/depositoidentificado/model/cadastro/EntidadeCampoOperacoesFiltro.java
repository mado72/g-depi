package br.com.bradseg.depi.depositoidentificado.model.cadastro;

import java.io.Serializable;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.json.IEntidadeCampoSerializer;
import br.com.bradseg.depi.depositoidentificado.util.json.TipoOperacaoSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Classe utilitária para associar uma entidade com as operações disponíveis.
 * 
 * @author Marcelo Damasceno
 * 
 * @param <T> A entidade
 */
public class EntidadeCampoOperacoesFiltro<T extends IEntidadeCampo> implements Serializable {

	private static final long serialVersionUID = 2953514533982138657L;

	@JsonSerialize( using=IEntidadeCampoSerializer.class)
	@JsonProperty("entidade")
	private T entidadeCampo;
	
	@JsonSerialize( contentUsing=TipoOperacaoSerializer.class)
	private List<TipoOperacao> operacoes;

	public T getEntidadeCampo() {
		return entidadeCampo;
	}

	public void setEntidadeCampo(T parametroConsulta) {
		this.entidadeCampo = parametroConsulta;
	}

	public List<TipoOperacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<TipoOperacao> operacoes) {
		this.operacoes = operacoes;
	}
	
}
