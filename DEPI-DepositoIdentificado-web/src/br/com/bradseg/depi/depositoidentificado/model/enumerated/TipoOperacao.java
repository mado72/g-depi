package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Enumerator TipoOperacao
 * @author Marcelo Damasceno
 */
public enum TipoOperacao {
    /**
     * Alfanumérico Obrigatório
     */
    IgualAlfanumericoObrigatorio(TipoCampo.ALFA_OBRIG, true, "UPPER({0}) = :{1}", null), 
    DiferenteAlfanumericoObrigatorio(TipoCampo.ALFA_OBRIG, true, "UPPER({0}) <> :{1}", null), 
    ContemObrigatorio(TipoCampo.ALFA_OBRIG, true,"UPPER({0}) LIKE :{1}", "%{0}%"), 
    NaoContemObrigatorio(TipoCampo.ALFA_OBRIG, true, "UPPER({0}) NOT LIKE :{1}", "%{0}%"), 
    IniciaComObrigatorio(TipoCampo.ALFA_OBRIG, true, "UPPER({0}) LIKE :{1}", "{0}%"), 
    TerminaComObrigatorio(TipoCampo.ALFA_OBRIG, true, "RTRIM (UPPER({0})) LIKE :{1}", "%{0}"),

    /**
     * Alfanumérico Opcional
     */
    IgualAlfanumericoOpcional(TipoCampo.ALFA_OPT ,  IgualAlfanumericoObrigatorio), 
    DiferenteAlfanumericoOpcional(TipoCampo.ALFA_OPT, DiferenteAlfanumericoObrigatorio), 
    ContemOpcional(TipoCampo.ALFA_OPT, ContemObrigatorio), 
    NaoContemOpcional(TipoCampo.ALFA_OPT, NaoContemObrigatorio),
    IniciaComOpcional(TipoCampo.ALFA_OPT, IniciaComObrigatorio),
    TerminaComOpcional(TipoCampo.ALFA_OPT, TerminaComObrigatorio), 
    EmBrancoOpcional(TipoCampo.ALFA_OPT, false, "RTRIM ({0}) = ''''", ""),

    /**
     * Alfanumérico Obrigatório Big
     */
    ContemOpcionalBig(TipoCampo.ALFA_OBRIG_BIG, ContemObrigatorio), 
    NaoContemOpcionalBig(TipoCampo.ALFA_OBRIG_BIG, NaoContemObrigatorio), 
    IniciaComOpcionalBig(TipoCampo.ALFA_OBRIG_BIG, IniciaComObrigatorio), 
    TerminaComOpcionalBig(TipoCampo.ALFA_OBRIG_BIG, TerminaComObrigatorio),

    /**
     * Numérico
     */
    IgualNumerico(TipoCampo.NUM, true, "{0} = :{1}", null), 
    DiferenteNumerico(TipoCampo.NUM, true, "{0} <> :{1}", null), 
    MenorQue(TipoCampo.NUM, true, "{0} < :{1}", null), 
    MaiorQue(TipoCampo.NUM, true, "{0} > :{1}", null), 
    MenorIgual(TipoCampo.NUM, true, "{0} <= :{1}", null), 
    MaiorIgual(TipoCampo.NUM, true, "{0} >= :{1}", null);

    private final String clausula;
    
    private final String formatoValor;

    private final TipoCampo tipoCampo;

    private final boolean temValor;

    /**
     * Construtor
     * @param tipoCampo TipoCampo
     * @param temValor Se tem valor operando
     * @param clausula Cláusula associada
     */
    TipoOperacao(TipoCampo tipoCampo, boolean temValor, String clausula, String formatoValor) {
        this.tipoCampo = tipoCampo;
        this.temValor = temValor;
        this.clausula = clausula;
        this.formatoValor = formatoValor;
    }

    /**
     * Construtor
     * @param tipoOperacao - TipoOperacao
     * @param tipoCampo - TipoCampo 
     */
    TipoOperacao(TipoCampo tipoCampo, TipoOperacao tipoOperacao) {
        this.tipoCampo = tipoCampo;
        this.temValor = tipoOperacao.temValor;
        this.clausula = tipoOperacao.clausula;
        this.formatoValor = tipoOperacao.formatoValor;
    }

    /**
     * Obter TipoCampo
     * @return TipoCampo
     */
    public TipoCampo getTipoCampo() {
        return tipoCampo;
    }

    /**
     * Obter se tem valor
     * @return Retorna se tem valor
     */
    public boolean isTemValor() {
        return temValor;
    }

    /**
     * Obter clausula
     * @return Cláusula
     */
    public String getClausula() {
        return clausula;
    }
    
    /**
     * Obter formato do valor
     * @return Formato Valor
     */
    public String getFormatoValor() {
		return formatoValor;
	}
    
    /**
     * Utiliza o pattern da cl�usula para inserir campo e parâmetro
     * @param campo Campo a ser inclu�do na cl�usula
     * @param nomeParametro parâmetro a ser utilizado na cl�usula
     * @return Cl�usula formatada.
     */
    public String formatarClausula(String campo, String nomeParametro) {
    	return MessageFormat.format(clausula, campo, nomeParametro);
    }
    
    /**
     * Inclui % para os operadores LIKE, quando necess�rio.
     * @param valor Valor a ser formatado
     * @return Valor formatado
     */
    public String formatarValor(String valor) {
    	if (this.formatoValor == null) {
    		return valor;
    	}
    	
    	return MessageFormat.format(formatoValor, valor);
    }

    /**
     * Obter por TipoCampo
     * @param pTipoCampo TipoCampo
     * @return Lista de TipoOperacao
     */
    public static List<TipoOperacao> obterPorTipoCampo(TipoCampo pTipoCampo) {
        List<TipoOperacao> tipos = new ArrayList<TipoOperacao>();
        for (TipoOperacao tipoOperacao : TipoOperacao.values()) {
            if (tipoOperacao.getTipoCampo() != null && tipoOperacao.getTipoCampo().equals(pTipoCampo)) {
                tipos.add(tipoOperacao);
            }
        }
        return tipos;
    }

    /**
     * Obter TipoOperacao por código
     * @param codigo Código do TipoOperacao
     * @return TipoOperacao
     */
    public static TipoOperacao obterPorCodigo(String codigo) {

        for (TipoOperacao operacao : TipoOperacao.values()) {
            if (operacao.toString().equals(codigo)) {
                return operacao;
            }
        }
        return null;
    }

}
