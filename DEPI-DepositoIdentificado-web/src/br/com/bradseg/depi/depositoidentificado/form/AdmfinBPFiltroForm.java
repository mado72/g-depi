package br.com.bradseg.depi.depositoidentificado.form;

import java.util.List;

/**
 * Classe do form geral de filtro
 * @author Marcelo Damasceno
 */
public abstract class AdmfinBPFiltroForm extends BaseForm {

    private static final long serialVersionUID = 1L;

    /**
     * Todos os filtros usados na página.
     */
    private List<String> campo;

    private List<String> operacao;

    private List<String> valor;

    private List<?> colecaoDados;

    /**
     * Obter campo
     * @return Campo
     */
    public List<String> getCampo() {
		return campo;
	}

    public void setCampo(List<String> campo) {
		this.campo = campo;
	}

    public List<String> getOperacao() {
		return operacao;
	}
    
    public void setOperacao(List<String> operacao) {
		this.operacao = operacao;
	}
    
    public List<String> getValor() {
		return valor;
	}
    
    public void setValor(List<String> valor) {
		this.valor = valor;
	}
    
    public List<?> getColecaoDados() {
		return colecaoDados;
	}
    
    public void setColecaoDados(List<?> colecaoDados) {
		this.colecaoDados = colecaoDados;
	}
}
