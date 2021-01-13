package br.com.bradseg.depi.depositoidentificado.model.cadastro;

import java.util.List;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.json.ListTipoOperacaoSerializer;
import br.com.bradseg.depi.depositoidentificado.util.json.MotivoDepositoCampoSerializer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
public class MotivoDepositoTipoOperacao {
	
	@JsonSerialize( using=MotivoDepositoCampoSerializer.class)
	@JsonProperty("motivo")
	private MotivoDepositoCampo motivoDeposito;
	
	@JsonSerialize( using=ListTipoOperacaoSerializer.class)
	private List<TipoOperacao> operacoes;

	public MotivoDepositoCampo getMotivoDeposito() {
		return motivoDeposito;
	}

	public void setMotivoDeposito(MotivoDepositoCampo motivoDeposito) {
		this.motivoDeposito = motivoDeposito;
	}

	public List<TipoOperacao> getOperacoes() {
		return operacoes;
	}

	public void setOperacoes(List<TipoOperacao> operacoes) {
		this.operacoes = operacoes;
	}

}
