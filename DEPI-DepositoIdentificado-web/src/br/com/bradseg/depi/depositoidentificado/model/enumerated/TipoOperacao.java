package br.com.bradseg.depi.depositoidentificado.model.enumerated;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumerator TipoOperacao
 * @refactor Marcelo Damasceno
 */
public enum TipoOperacao {
    /**
     * Alfanumérico Obrigatório
     */
    IgualAlfanumericoObrigatorio("Igual", TipoCampo.ALFA_OBRIG, true, "UPPER({0}) = ''{1}''"), 
    DiferenteAlfanumericoObrigatorio("Diferente", TipoCampo.ALFA_OBRIG, true, "UPPER({0}) <> ''{1}''"), 
    ContemObrigatorio("Contém", TipoCampo.ALFA_OBRIG, true,"UPPER({0}) LIKE ''%{1}%''"), 
    NaoContemObrigatorio("Não contém", TipoCampo.ALFA_OBRIG, true, "UPPER({0}) NOT LIKE ''%{1}%''"), 
    IniciaComObrigatorio("Inicia com", TipoCampo.ALFA_OBRIG, true, "UPPER({0}) LIKE ''{1}%''"), 
    TerminaComObrigatorio("Termina com", TipoCampo.ALFA_OBRIG, true, "RTRIM (UPPER({0})) LIKE ''%{1}''"),

    /**
     * Alfanumérico Opcional
     */
    IgualAlfanumericoOpcional(TipoCampo.ALFA_OPT ,  IgualAlfanumericoObrigatorio), 
    DiferenteAlfanumericoOpcional(TipoCampo.ALFA_OPT, DiferenteAlfanumericoObrigatorio), 
    ContemOpcional(TipoCampo.ALFA_OPT, ContemObrigatorio), 
    NaoContemOpcional(TipoCampo.ALFA_OPT, NaoContemObrigatorio),
    IniciaComOpcional(TipoCampo.ALFA_OPT, IniciaComObrigatorio),
    TerminaComOpcional(TipoCampo.ALFA_OPT, TerminaComObrigatorio), 
    EmBrancoOpcional("Em branco", TipoCampo.ALFA_OPT, false, "RTRIM (UPPER({0})) = ''''"),

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
    IgualNumerico("Igual", TipoCampo.NUM, true, "{0} = {1}"), 
    DiferenteNumerico("Diferente", TipoCampo.NUM, true, "{0} <> {1}"), 
//    ContemNumerico("Contém", TipoCampo.NUM, true, "{0} IN ({1})"), 
    MenorQue("Menor que", TipoCampo.NUM, true, "{0} < {1}"), 
    MaiorQue("Maior que", TipoCampo.NUM, true, "{0} > {1}"), 
    MenorIgual("Menor igual", TipoCampo.NUM, true, "{0} <= {1}"), 
    MaiorIgual("Maior igual", TipoCampo.NUM, true, "{0} >= {1}");

    private String descricao;

    private String clausula;

    private TipoCampo tipoCampo;

    private boolean temValor;

    /**
     * Construtor
     * @param descricao Descrição da operação
     * @param tipoCampo TipoCampo
     * @param temValor Se tem valor operando
     * @param clausula Clausula associada
     */
    TipoOperacao(String descricao, TipoCampo tipoCampo, boolean temValor, String clausula) {
        this.descricao = descricao;
        this.tipoCampo = tipoCampo;
        this.temValor = temValor;
        this.clausula = clausula;
    }

    /**
     * Construtor
     * @param tipoOperacao - TipoOperacao
     * @param tipoCampo - TipoCampo 
     */
    TipoOperacao(TipoCampo tipoCampo, TipoOperacao tipoOperacao) {
        this.tipoCampo = tipoCampo;
        this.descricao = tipoOperacao.descricao;
        this.temValor = tipoOperacao.temValor;
        this.clausula = tipoOperacao.clausula;
    }

    /**
     * Obter descricao
     * @return Descricao
     */
    public String getDescricao() {
        return descricao;
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
     * @return Clausula
     */
    public String getClausula() {
        return clausula;
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
     * @param codigo Codigo do TipoOperacao
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
