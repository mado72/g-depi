package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;


/**
 * Classe Anotada de Mapeamento com a tabela PARMZ_DEP_IDTFD.
 * @author Globality
 */
//@Table(schema = ConstantesDAO.SCHEMA_BANCO, table = "PARMZ_DEP_IDTFD")
public class ParametroDepositoVO implements Serializable{

    /**
	 * 
	 */
	private static final String NULL_VALUE = "null";

	private static final long serialVersionUID = 5987719912367700030L;

    /**
     * Código da Companhia - obtido atraves da classe
     */
//    @TableId
//    @TableField(name = ConstantesDAO.TABELA_COMPANHIA_DEPARTAMENTO_ID, converter = CompanhiaSeguradoraVOPersistenceConverter.class)
    private CompanhiaSeguradoraVO companhia = new CompanhiaSeguradoraVO();

    /**
     * Código do departamento - DepartamentoVO obtido atraves da classe DepartamentoConverter
     */
//    @TableId
//    @TableField(name = ConstantesDAO.TABELA_DEPARTAMENTO_ID, converter = DepartamentoConverter.class)
    private DepartamentoVO departamento = new DepartamentoVO();

    /**
     * Código do motivo - MotivoVO obtido através da classe MotivoDepositoConverter.
     */
//    @TableId
//    @TableField(name = ConstantesDAO.TABELA_MOTIVO_ID, converter = MotivoDepositoConverter.class)
    private MotivoDepositoVO motivoDeposito = new MotivoDepositoVO();

    /**
     * Construtor Vazio
     */
    public ParametroDepositoVO() {
        super();
    }

    /**
     * Construtor.
     * @param codigoDepartamento - int.
     * @param codigoMotivo - int.
     * @param codigoCompanhia - int.
     */
    public ParametroDepositoVO(int codigoDepartamento, int codigoMotivo, int codigoCompanhia) {
        this.departamento.setCodigoDepartamento(codigoDepartamento);
        this.motivoDeposito.setCodigoMotivoDeposito(codigoMotivo);
        this.companhia.setCodigoCompanhia(codigoCompanhia);

    }

    /**
     * Construtor.
     * @param departamento - int.
     * @param motivoDeposito - int.
     */
    public ParametroDepositoVO(int departamento, int motivoDeposito) {
        this.departamento.setCodigoDepartamento(departamento);
        this.motivoDeposito.setCodigoMotivoDeposito(motivoDeposito);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder("ParametroDepositoVO: Cia: ");
    	if (getCompanhia() != null) {
    		sb.append(getCompanhia().getCodigoCompanhia());
    	}
    	else {
    		sb.append(NULL_VALUE);
    	}
    	
    	sb.append(", Depto: ");
    	if (getDepartamento() != null) {
    		sb.append(getDepartamento().getCodigoDepartamento());
    	}
    	else {
    		sb.append(NULL_VALUE);
    	}
    	
    	sb.append(", MDep: ");
    	if (getMotivoDeposito() != null) {
    		sb.append(getMotivoDeposito().getCodigoMotivoDeposito());
    	}
    	else {
    		sb.append(NULL_VALUE);
    	}
    	return sb.toString();
    }

    /**
     * Código Retirada BancoVO Após Vencimento.
     */
//    @TableField(name = "CIND_DEP_APOS_VCTO")
    private String codigoBancoVencimento = "S";

    /**
     * Descrição Retirada BancoVO Após Vencimento.
     */
    private String descricaoBancoVencimento;

    /**
     * Numero de Dias apos Vencimento.
     */
//    @TableField(name = "QDIA_APOS_VCTO", converter = ZeroIntegerWhenNullPersistenceConverter.class)
    private int numeroDiasAposVencimento;

    /**
     * Código ItemContabil
     */
//    @TableField(name = "CIND_ITEM_APOLC")
    private String codigoItem = "S";

    /**
     * Código Ramo
     */
//    @TableField(name = "CIND_RAMO_OBRIG")
    private String codigoRamo = "S";

    /**
     * Código Tipo
     */
//    @TableField(name = "CIND_TPO_DOCTO")
    private String codigoTipo = "S";

    /**
     * Código Sucursal
     */
//    @TableField(name = "CIND_SUCUR_OBRIG")
    private String codigoSucursal = "S";

    /**
     * Código Protocolo
     */
//    @TableField(name = "CIND_PROT")
    private String codigoProtocolo = "S";

    /**
     * Código Apólice
     */
//    @TableField(name = "CIND_APOLC_OBRIG")
    private String codigoApolice = "S";

    /**
     * CPF/CNPJ
     */
//    @TableField(name = "CIND_CPF_CNPJ")
    private String codigoCpfCnpj = "S";

    /**
     * Código Dossie
     */
//    @TableField(name = "CIND_PROCS_OBRIG")
    private String codigoDossie = "S";

    /**
     * Código Bloqueto
     */
//    @TableField(name = "CIND_BLETO_OBRIG")
    private String codigoBloqueto = "S";

    /**
     * Código Endosso
     */
//    @TableField(name = "CIND_ENDSS_OBRIG")
    private String codigoEndosso = "S";

    /**
     * Código Parcela
     */
//    @TableField(name = "CIND_PCELA_PRMIO")
    private String codigoParcela = "S";

    /**
     * Outros Documentos Necessários maxlength = 200
     */
//    @TableField(name = "ROBS_PARMZ_DEP")
    private String outrosDocumentosNecessarios;
    
    /**
     * Se o parâmetro é referenciado
     */
    private boolean referenciadoDeposito;

    /**
     * Descrição do Tipo de Deposito
     */
    private String descricaoDeposito;

    /**
     * Descrição básica do motivo.
     */
    private String descricaoBasicaMotivo;

    /**
     * Descrição detalhada do motivo.
     */
    private String descricaoDetalhadaMotivo;

	/**
	 * Código do responsável pela atualzação 
	 */
	private int codigoResponsavelUltimaAtualizacao;
	
	private String codigoAtivo;
	
	private Date dataHoraInclusao;
	
	private Date ultimaAtualizacao;
	
    /**
     * Retorna o campo código da Apólice
     * @return codigoApolice - Código da apólice
     */
    public String getCodigoApolice() {
        return codigoApolice;
    }

    /**
     * Especifica o campo codigoApolice
     * @param codigoApolice Código da apólice
     */
    public void setCodigoApolice(String codigoApolice) {
        this.codigoApolice = codigoApolice;
    }

    /**
     * Retorna o campo codigoBancoVencimento
     * @return codigoBancoVencimento - Deposito após vencimento
     */
    public String getCodigoBancoVencimento() {
        return codigoBancoVencimento;
    }

    /**
     * Especifica o campo codigoBancoVencimento
     * @param codigoBancoVencimento - String.
     */
    public void setCodigoBancoVencimento(String codigoBancoVencimento) {
        this.codigoBancoVencimento = codigoBancoVencimento;
    }

    /**
     * retorna o campo codigoBloqueto
     * @return codigoBloqueto
     */
    public String getCodigoBloqueto() {
        return codigoBloqueto;
    }

    /**
     * retorna o campo codigoCpfCnpj
     * @return codigoCpfCnpj
     */
    public String getCodigoCpfCnpj() {
        return codigoCpfCnpj;
    }

    /**
     * retorna o campo codigoDossie
     * @return codigoDossie - codigo do processo jurídico obrigatório
     */
    public String getCodigoDossie() {
        return codigoDossie;
    }

    /**
     * especifica o CodigoDossie
     * @param codigoDossie - codigo do processo jurídico obrigatório
     */
    public void setCodigoDossie(String codigoDossie) {
        this.codigoDossie = codigoDossie;
    }

    /**
     * retorna o codigoEndosso
     * @return codigoEndosso
     */
    public String getCodigoEndosso() {
        return codigoEndosso;
    }

    /**
     * Especifica o valor do atributo codigoBloqueto.
     * @param codigoBloqueto - String do codigoBloqueto a ser configurado.
     */
    public void setCodigoBloqueto(String codigoBloqueto) {
        this.codigoBloqueto = codigoBloqueto;
    }

    /**
     * Especifica o valor do atributo codigoCpfCnpj.
     * @param codigoCpfCnpj - String do codigoCpfCnpj a ser configurado.
     */
    public void setCodigoCpfCnpj(String codigoCpfCnpj) {
        this.codigoCpfCnpj = codigoCpfCnpj;
    }

    /**
     * Especifica o valor do atributo codigoEndosso.
     * @param codigoEndosso - String do codigoEndosso a ser configurado.
     */
    public void setCodigoEndosso(String codigoEndosso) {
        this.codigoEndosso = codigoEndosso;
    }

    /**
     * Especifica o valor do atributo codigoProtocolo.
     * @param codigoProtocolo - String do codigoProtocolo a ser configurado.
     */
    public void setCodigoProtocolo(String codigoProtocolo) {
        this.codigoProtocolo = codigoProtocolo;
    }

    /**
     * Especifica o valor do atributo codigoRamo.
     * @param codigoRamo - String do codigoRamo a ser configurado.
     */
    public void setCodigoRamo(String codigoRamo) {
        this.codigoRamo = codigoRamo;
    }

    /**
     * Especifica o valor do atributo codigoSucursal.
     * @param codigoSucursal - String do codigoSucursal a ser configurado.
     */
    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    /**
     * Especifica o valor do atributo companhia.
     * @param companhia - CompanhiaSeguradoraVO do companhia a ser configurado.
     */
    public void setCompanhia(CompanhiaSeguradoraVO companhia) {
        this.companhia = companhia;
    }

    /**
     * Especifica o valor do atributo descricaoBancoVencimento.
     * @param descricaoBancoVencimento - String do descricaoBancoVencimento a ser configurado.
     */
    public void setDescricaoBancoVencimento(String descricaoBancoVencimento) {
        this.descricaoBancoVencimento = descricaoBancoVencimento;
    }

    /**
     * Retorna o campo codigoItem
     * @return codigoItem
     */
    public String getCodigoItem() {
        return codigoItem;
    }

    /**
     * especifica o campo codigoItem
     * @param codigoItem - codigoItem
     */
    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    /**
     * Retorna o campo codigoParcela
     * @return codigoParcela - codigoParcela
     */
    public String getCodigoParcela() {
        return codigoParcela;
    }

    /**
     * Especifica o campo codigoParcela
     * @param codigoParcela - codigoParcela
     */
    public void setCodigoParcela(String codigoParcela) {
        this.codigoParcela = codigoParcela;
    }

    /**
     * Retorna o campo codigoProtocolo
     * @return codigoProtocolo
     */
    public String getCodigoProtocolo() {
        return codigoProtocolo;
    }

    /**
     * Retorna o codigoRamo
     * @return codigoRamo - codigoRamo
     */
    public String getCodigoRamo() {
        return codigoRamo;
    }

    /**
     * Retorna o campo codigoSucursal
     * @return codigoSucursal - codigoSucursal
     */
    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    /**
     * retorna o campo codigoTipo
     * @return codigoTipo - Referencia ao campo tipo de documento
     */
    public String getCodigoTipo() {
        return codigoTipo;
    }

    /**
     * Especifica o campo codigoTipo
     * @param codigoTipo - referencia ao campo tipo de documento
     */
    public void setCodigoTipo(String codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    /**
     * Retorna o valor do atributo companhia.
     * @return companhia
     */
    public CompanhiaSeguradoraVO getCompanhia() {
        return companhia;
    }

    /**
     * Retorna o valor do atributo departamento
     * @return departamento
     */
    public DepartamentoVO getDepartamento() {
        return departamento;
    }

    /**
     * Especifica o valor do atributo departamento
     * @param departamento - departamento
     */
    public void setDepartamento(DepartamentoVO departamento) {
        this.departamento = departamento;
    }

    /**
     * Retorna o valor do atributo descricaoBancoVencimento
     * @return descricaoBancoVencimento - descricaoBancoVencimento
     */
    public String getDescricaoBancoVencimento() {
        return this.descricaoBancoVencimento;
    }

    /**
     * Retorna o valor do campo descricaoBasicaMotivo
     * @return descricaoBasicaMotivo - descricaoBasicaMotivo
     */
    public String getDescricaoBasicaMotivo() {
        return descricaoBasicaMotivo;
    }

    /**
     * Especifica o valor do campo descricaoBasicaMotivo
     * @param descricaoBasicaMotivo - descricaoBasicaMotivo
     */
    public void setDescricaoBasicaMotivo(String descricaoBasicaMotivo) {
        this.descricaoBasicaMotivo = descricaoBasicaMotivo;
    }

    /**
     * retorna o valor do campo descricaoDeposito
     * @return descricaoDeposito - descricaoDeposito
     */
    public String getDescricaoDeposito() {
        return descricaoDeposito;
    }

    /**
     * Especifica o valor do atributo descricaoDeposito
     * @param descricaoDeposito - descricaoDeposito
     */
    public void setDescricaoDeposito(String descricaoDeposito) {
        this.descricaoDeposito = descricaoDeposito;
    }

    /**
     * retorna o valor do atributo descricaoDetalhadaMotivo
     * @return descricaoDetalhadaMotivo - descricaoDetalhadaMotivo
     */
    public String getDescricaoDetalhadaMotivo() {
        return descricaoDetalhadaMotivo;
    }

    /**
     * Especifica o valor do atributo descricaoDetalhadaMotivo
     * @param descricaoDetalhadaMotivo - descricaoDetalhadaMotivo
     */
    public void setDescricaoDetalhadaMotivo(String descricaoDetalhadaMotivo) {
        this.descricaoDetalhadaMotivo = descricaoDetalhadaMotivo;
    }

    /**
     * Retorna o valor do atributo motivoDeposito
     * @return motivoDeposito - motivoDeposito
     */
    public MotivoDepositoVO getMotivoDeposito() {
        return motivoDeposito;
    }

    /**
     * Especifica o valor do atributo motivoDeposito
     * @param motivoDeposito - motivoDeposito
     */
    public void setMotivoDeposito(MotivoDepositoVO motivoDeposito) {
        this.motivoDeposito = motivoDeposito;
    }

    /**
     * Retorna o valor do atributo numeroDiasAposVencimento
     * @return numeroDiasAposVencimento - numeroDiasAposVencimento
     */
    public int getNumeroDiasAposVencimento() {
        return numeroDiasAposVencimento;
    }

    /**
     * Especifica o valor do atributo numeroDiasAposVencimento
     * @param numeroDiasAposVencimento - int.
     */
    public void setNumeroDiasAposVencimento(int numeroDiasAposVencimento) {
        this.numeroDiasAposVencimento = numeroDiasAposVencimento;
    }

    /**
     * Retorna o valor do atributo outrosDocumentosNecessarios
     * @return outrosDocumentosNecessarios - Referencia ao campo observações
     */
    public String getOutrosDocumentosNecessarios() {
        return outrosDocumentosNecessarios;
    }

    /**
     * Especifica o valor do atributo outrosDocumentosNecessarios
     * @param outrosDocumentosNecessarios - Referencia ao campo obsevações (tamanho max 200)
     */
    public void setOutrosDocumentosNecessarios(String outrosDocumentosNecessarios) {
        this.outrosDocumentosNecessarios = outrosDocumentosNecessarios;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode() * motivoDeposito.getCodigoMotivoDeposito() * 777;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ParametroDepositoVO)
            && ((ParametroDepositoVO) obj).getDepartamento().equals(this.getDepartamento())
            && ((ParametroDepositoVO) obj).getMotivoDeposito().equals(this.getMotivoDeposito());
    }

    /**
     * Retorna o serialVersionUID.
     * @return O atributo serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * Retorna o valor do atributo referenciadoDeposito.
     * @return o valor do atributo referenciadoDeposito
     */
    public boolean isReferenciadoDeposito() {
        return referenciadoDeposito;
    }

    /**
     * Especifica o valor do atributo referenciadoDeposito.
     * @param referenciadoDeposito - boolean do referenciadoDeposito a ser configurado.
     */
    public void setReferenciadoDeposito(boolean referenciadoDeposito) {
        this.referenciadoDeposito = referenciadoDeposito;
    }

    /**
	 * Retorna codigoResponsavelUltimaAtualizacao
	 * @return o codigoResponsavelUltimaAtualizacao
	 */
	public int getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}
	
	/**
	 * Define codigoResponsavelUltimaAtualizacao
	 * @param codigoResponsavelUltimaAtualizacao valor codigoResponsavelUltimaAtualizacao a ser definido
	 */
	public void setCodigoResponsavelUltimaAtualizacao(
			int codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}
	
	/**
	 * Retorna codigoAtivo
	 * @return o codigoAtivo
	 */
	public String getCodigoAtivo() {
		return codigoAtivo;
	}
	
	/**
	 * Define codigoAtivo
	 * @param codigoAtivo valor codigoAtivo a ser definido
	 */
	public void setCodigoAtivo(String codigoAtivo) {
		this.codigoAtivo = codigoAtivo;
	}
	
	/**
	 * Retorna dataHoraInclusao
	 * @return o dataHoraInclusao
	 */
	public Date getDataHoraInclusao() {
		return BaseUtil.getDate(dataHoraInclusao);
	}
	
	/**
	 * Define dataHoraInclusao
	 * @param dataHoraInclusao valor dataHoraInclusao a ser definido
	 */
	public void setDataHoraInclusao(Date dataHoraInclusao) {
		this.dataHoraInclusao = BaseUtil.getDate(dataHoraInclusao);
	}
	
	/**
	 * Retorna dataHoraAtualizacao
	 * @return o dataHoraAtualizacao
	 */
	public Date getUltimaAtualizacao() {
		return BaseUtil.getDate(ultimaAtualizacao);
	}
	
	/**
	 * Define dataHoraAtualizacao
	 * @param dataHoraAtualizacao valor dataHoraAtualizacao a ser definido
	 */
	public void setUltimaAtualizacao(Date dataHoraAtualizacao) {
		this.ultimaAtualizacao = BaseUtil.getDate(dataHoraAtualizacao);
	}
}
