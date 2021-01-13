package br.com.bradseg.depi.depositoidentificado.model.cadastro;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.MotivoDepositoCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;

public class MotivoDepositoFiltroItemModel implements Serializable {

	private static final long serialVersionUID = 7925433459093605616L;

	private MotivoDepositoCampo campo;
	
	private TipoOperacao operacao;
	
	private String valor;

	public MotivoDepositoCampo getCampo() {
		return campo;
	}

	public void setCampo(MotivoDepositoCampo campo) {
		this.campo = campo;
	}

	public TipoOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(TipoOperacao operacao) {
		this.operacao = operacao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
