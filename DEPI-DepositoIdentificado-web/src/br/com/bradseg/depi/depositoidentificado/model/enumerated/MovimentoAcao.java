package br.com.bradseg.depi.depositoidentificado.model.enumerated;

public enum MovimentoAcao {
	
	ACEITE("A"),
    TRANSFERENCIA("T"),
    RECEITA("R"),
    DEVOLUCAO("D");
	
	private final String codigo;
	
	private MovimentoAcao(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}
	
	public static MovimentoAcao obterPorCodigo(String codigo) {
		for (MovimentoAcao item : values()) {
			if (item.getCodigo().equals(codigo)) {
				return item;
			}
		}
		return null;
	}
}
