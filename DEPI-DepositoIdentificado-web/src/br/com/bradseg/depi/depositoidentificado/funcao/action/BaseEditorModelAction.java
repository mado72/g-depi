package br.com.bradseg.depi.depositoidentificado.funcao.action;

/**
 * Superclasse das ações que fazem manutenção dos cadastros do sistema.
 * 
 * Estabelece assinatura obrigatória de métodos para visualizar, alterar e
 * remover registros.
 * 
 * @author Marcelo Damasceno
 * 
 * @param <T>
 */
public abstract class BaseEditorModelAction<T> extends BaseModelAction<T> {

	private static final long serialVersionUID = -2950966233176711616L;

	/**
	 * Prepara a saída para INPUT.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public String exibir() {
		return INPUT;
	}
	
	/**
	 * Prepara a saída para INPUT e define o subtítulo para detalhar. Usado para apresentar o
	 * formulário com detalhes de um registro.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public abstract String detalhar();
	
	/**
	 * Prepara a saída para INPUT e define o subtítulo para novo. Usado para apresentar o
	 * formulário para inclusão de um novo registro.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public abstract String novo();
	
	/**
	 * Prepara a saída para INPUT e define o subtítulo para alterar. Usado para apresentar o
	 * formulário para a edição de um registro.
	 * 
	 * @return {@link com.opensymphony.xwork2.Action#INPUT}
	 */
	public abstract String alterar();
	
	protected void preencherFormulario(String subtituloChave) {
		String subtitulo = getText(subtituloChave);
		setSubtitulo(subtitulo);
	}
	
	/**
	 * Método que deve ser implementado para persistir os dados enviados pelo
	 * formulário.
	 */
	protected abstract void persistirDados();

}
