package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Classe Anotada de Mapeamento com a tabela DEP_IDTFD - Identificação de Depósito.
 * @author Globality
 */
@XmlRootElement(name="DepositoVO")
public class DepositoVO implements Serializable {

    private static final long serialVersionUID = -5700923805279204904L;

    /**
     * Código seqüencial da identificação do depósito.
     */
    private long codigoDepositoIdentificado;

    /**
     * Dígito Verificador gerado a partir do Código seqüencial da identificação do depósito.
     */
    private int codigoDigitodeposito;

    /**
     * Código (FK) da associação Conta_corrente_motivo_deposito - Companhia Seguradora.
     */
    private CompanhiaSeguradoraVO cia = new CompanhiaSeguradoraVO();

    /**
     * Código (FK) da associação Conta_corrente_motivo_deposito - Departamento.
     */
    private DepartamentoVO departamento = new DepartamentoVO();

    /**
     * Código (FK) da associação Conta_corrente_motivo_deposito - Motivo Deposito.
     */
    private MotivoDepositoVO motivo = new MotivoDepositoVO();

    private BancoVO banco = new BancoVO();

    private int agencia;

    private String ramo;

    private long contaCorrente;
    
    /**
     * CPN/CNPJ
     */
    private String cpfCnpj;

    /**
     * Sucursal.
     */
    private int sucursal;

    /**
     * ItemContabil.
     */
    private int item;

    /**
     * Protocolo.
     */
    private int protocolo;

    /**
     * Tipo de Recebimento.
     */
    private int tipoDocumento;

    /**
     * Apolice.
     */
    private int apolice;

    /**
     * Código (FK) da tabela Pessoa-corporativo
     */
    private long pessoaDepositante;

    /**
     * Dossiê.
     */
    private String dossie;

    /**
     * Bloqueto.
     */
    private long bloqueto;

    /**
     * nome
     */
    private String nomePessoa;

    /**
     * Endosso.
     */
    private long endosso;

    /**
     * Parcela.
     */
    private long parcela;

    /**
     * Informações de Depósito (Histórico).
     */
    private int tipoGrupoRecebimento = 2;

    /**
     * Observacao Deposito Identificado.
     */
    private String observacaoDeposito;

    /**
     * Indicativo de Depósito: Prorrogado.
     */
    private String indicadorDepositoProrrogado;

    /**
     * Vencimento Depósito.
     */
    private Date dtVencimentoDeposito;

    /**
     * Indicativo de Deposito cancelado.
     */
    private String indicadorDepositoCancelado;

    private Date dataProrrogacao;

    /**
     * Valor do Depósito Registrado.
     */
    private BigDecimal vlrDepositoRegistrado;

    /**
     * Data de Cancelamento do Deposito Identificado.
     */
    private Date dtCancelamentoDepositoIdentificado;

    private int situacaoArquivoTransferencia;
    
    private long trps;
    
    private Integer codigoSituacaoDeposito;
    
    private List<ParcelaCobrancaVO> listaParcelas;
    
    private Integer codigoResponsavelUltimaAtualizacao;
    
    private String codigoIndicativoAtivo;
    
    private Date dataHoraAtualizacao;
    
    private Date dataInclusao;
    
    /**
     * Retorna o valor do atributo trps.
     * @return o valor do atributo trps
     */
    public long getTrps() {
        return trps;
    }

    /**
     * Especifica o valor do atributo trps.
     * @param trps - long do trps a ser configurado.
     */
    public void setTrps(long trps) {
        this.trps = trps;
    }

    /**
     * Método que retornará o código autorizador formatado para ser exibido nas telas e relatórios.
     * @return String.
     */
    public String getCodigoDepositoFormatado() {

        if (codigoDepositoIdentificado > 0) {
            return codigoDepositoIdentificado + " - " + getDv();
        } else {
            return "";
        }
    }

    /**
     * Método utilitário que fará o cálculo do digito verificador do código de autorização de um depósito.
     * @return Integer.
     */
    public long getDv() {
        if (codigoDepositoIdentificado > 0) {
            long lDivisao = codigoDepositoIdentificado / 7;
            lDivisao = lDivisao * 7;
            long lSubtracao = codigoDepositoIdentificado - lDivisao;
            if (lSubtracao < 0) {
                lSubtracao = lSubtracao * (-1);
            }
            return lSubtracao;
        } else {
            return 0;
        }
    }

    /**
     * Retorna o valor do atributo dIVISOR.
     * @return o valor do atributo dIVISOR
     */
    public int getDIVISOR() {
        return 7;
    }

    /**
     * Retorna o valor do atributo agencia.
     * @return o valor do atributo agencia
     */
    public int getAgencia() {
        return agencia;
    }

    /**
     * Especifica o valor do atributo agencia.
     * @param agencia - int do agencia a ser configurado.
     */
    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    /**
     * Retorna o valor do atributo apolice.
     * @return o valor do atributo apolice
     */
    public int getApolice() {
        return apolice;
    }

    /**
     * Especifica o valor do atributo apolice.
     * @param apolice - int do apolice a ser configurado.
     */
    public void setApolice(int apolice) {
        this.apolice = apolice;
    }

    /**
     * Retorna o valor do atributo banco.
     * @return o valor do atributo banco
     */
    public BancoVO getBanco() {
        return banco;
    }

    /**
     * Especifica o valor do atributo banco.
     * @param banco - BancoVO do banco a ser configurado.
     */
    public void setBanco(BancoVO banco) {
        this.banco = banco;
    }

    /**
     * Retorna o valor do atributo cia.
     * @return o valor do atributo cia
     */
    public CompanhiaSeguradoraVO getCia() {
        return cia;
    }

    /**
     * Especifica o valor do atributo cia.
     * @param cia - CompanhiaSeguradoraVO do cia a ser configurado.
     */
    public void setCia(CompanhiaSeguradoraVO cia) {
        this.cia = cia;
    }

    /**
     * Retorna o valor do atributo codigoDepositoIdentificado.
     * @return o valor do atributo codigoDepositoIdentificado
     */
    public long getCodigoDepositoIdentificado() {
        return codigoDepositoIdentificado;
    }

    /**
     * Especifica o valor do atributo codigoDepositoIdentificado.
     * @param codigoDepositoIdentificado - int do codigoDepositoIdentificado a ser configurado.
     */
    public void setCodigoDepositoIdentificado(long codigoDepositoIdentificado) {
        this.codigoDepositoIdentificado = codigoDepositoIdentificado;
    }

    /**
     * Retorna o valor do atributo codigoDigitodeposito.
     * @return o valor do atributo codigoDigitodeposito
     */
    public int getCodigoDigitodeposito() {
        return codigoDigitodeposito;
    }

    /**
     * Especifica o valor do atributo codigoDigitodeposito.
     * @param codigoDigitodeposito - int do codigoDigitodeposito a ser configurado.
     */
    public void setCodigoDigitodeposito(int codigoDigitodeposito) {
        this.codigoDigitodeposito = codigoDigitodeposito;
    }

    /**
     * Retorna o valor do atributo contaCorrente.
     * @return o valor do atributo contaCorrente
     */
    public long getContaCorrente() {
        return contaCorrente;
    }

    /**
     * Especifica o valor do atributo contaCorrente.
     * @param contaCorrente - long do contaCorrente a ser configurado.
     */
    public void setContaCorrente(long contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    /**
     * Retorna o valor do atributo cpfCnpj.
     * @return o valor do atributo cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * Especifica o valor do atributo cpfCnpj.
     * @param cpfCnpj - String do cpfCnpj a ser configurado.
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * Retorna o valor do atributo dataProrrogacao.
     * @return o valor do atributo dataProrrogacao
     */
    public Date getDataProrrogacao() {
        return (Date) dataProrrogacao.clone();
        
        
    }

    /**
     * Especifica o valor do atributo dataProrrogacao.
     * @param dataProrrogacao - Date do dataProrrogacao a ser configurado.
     */
    public void setDataProrrogacao(Date dataProrrogacao) {
        this.dataProrrogacao = (Date) dataProrrogacao.clone();
    }

    /**
     * Retorna o valor do atributo departamento.
     * @return o valor do atributo departamento
     */
    public DepartamentoVO getDepartamento() {
        return departamento;
    }

    /**
     * Especifica o valor do atributo departamento.
     * @param departamento - DepartamentoVO do departamento a ser configurado.
     */
    public void setDepartamento(DepartamentoVO departamento) {
        this.departamento = departamento;
    }

    /**
     * Retorna o valor do atributo dossie.
     * @return o valor do atributo dossie
     */
    public String getDossie() {
        return dossie;
    }

    /**
     * Especifica o valor do atributo dossie.
     * @param dossie - String do dossie a ser configurado.
     */
    public void setDossie(String dossie) {
        this.dossie = dossie;
    }

    /**
     * Retorna o valor do atributo dtCancelamentoDepositoIdentificado.
     * @return o valor do atributo dtCancelamentoDepositoIdentificado
     */
    public Date getDtCancelamentoDepositoIdentificado() {
        return (Date) dtCancelamentoDepositoIdentificado.clone();
    }

    /**
     * Especifica o valor do atributo dtCancelamentoDepositoIdentificado.
     * @param dtCancelamentoDepositoIdentificado - Date do dtCancelamentoDepositoIdentificado a ser configurado.
     */
    public void setDtCancelamentoDepositoIdentificado(Date dtCancelamentoDepositoIdentificado) {
        this.dtCancelamentoDepositoIdentificado = (Date) dtCancelamentoDepositoIdentificado.clone();
    }

    /**
     * Retorna o valor do atributo dtVencimentoDeposito.
     * @return o valor do atributo dtVencimentoDeposito
     */
    public Date getDtVencimentoDeposito() {
        return (Date) dtVencimentoDeposito.clone();
    }

    /**
     * Especifica o valor do atributo dtVencimentoDeposito.
     * @param dtVencimentoDeposito - Date do dtVencimentoDeposito a ser configurado.
     */
    public void setDtVencimentoDeposito(Date dtVencimentoDeposito) {
        this.dtVencimentoDeposito = (Date) dtVencimentoDeposito.clone();
    }

    /**
     * Retorna o valor do atributo indicadorDepositoCancelado.
     * @return o valor do atributo indicadorDepositoCancelado
     */
    public String getIndicadorDepositoCancelado() {
        return indicadorDepositoCancelado;
    }

    /**
     * Especifica o valor do atributo indicadorDepositoCancelado.
     * @param indicadorDepositoCancelado - String do indicadorDepositoCancelado a ser configurado.
     */
    public void setIndicadorDepositoCancelado(String indicadorDepositoCancelado) {
        this.indicadorDepositoCancelado = indicadorDepositoCancelado;
    }

    /**
     * Retorna o valor do atributo indicadorDepositoProrrogado.
     * @return o valor do atributo indicadorDepositoProrrogado
     */
    public String getIndicadorDepositoProrrogado() {
        return indicadorDepositoProrrogado;
    }

    /**
     * Especifica o valor do atributo indicadorDepositoProrrogado.
     * @param indicadorDepositoProrrogado - String do indicadorDepositoProrrogado a ser configurado.
     */
    public void setIndicadorDepositoProrrogado(String indicadorDepositoProrrogado) {
        this.indicadorDepositoProrrogado = indicadorDepositoProrrogado;
    }

    /**
     * Retorna o valor do atributo item.
     * @return o valor do atributo item
     */
    public int getItem() {
        return item;
    }

    /**
     * Especifica o valor do atributo item.
     * @param item - int do item a ser configurado.
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * Retorna o valor do atributo motivo.
     * @return o valor do atributo motivo
     */
    public MotivoDepositoVO getMotivo() {
        return motivo;
    }

    /**
     * Especifica o valor do atributo motivo.
     * @param motivo - MotivoDepositoVO do motivo a ser configurado.
     */
    public void setMotivo(MotivoDepositoVO motivo) {
        this.motivo = motivo;
    }

    /**
     * Retorna o valor do atributo nomePessoa.
     * @return o valor do atributo nomePessoa
     */
    public String getNomePessoa() {
        return nomePessoa;
    }

    /**
     * Especifica o valor do atributo nomePessoa.
     * @param nomePessoa - String do nomePessoa a ser configurado.
     */
    public void setNomePessoa(String nomePessoa) {
        this.nomePessoa = nomePessoa;
    }

    /**
     * Retorna o valor do atributo observacaoDeposito.
     * @return o valor do atributo observacaoDeposito
     */
    public String getObservacaoDeposito() {
        return observacaoDeposito;
    }

    /**
     * Especifica o valor do atributo observacaoDeposito.
     * @param observacaoDeposito - String do observacaoDeposito a ser configurado.
     */
    public void setObservacaoDeposito(String observacaoDeposito) {
        this.observacaoDeposito = observacaoDeposito;
    }

    /**
     * Retorna o valor do atributo bloqueto.
     * @return o valor do atributo bloqueto
     */
    public long getBloqueto() {
        return bloqueto;
    }

    /**
     * Especifica o valor do atributo bloqueto.
     * @param bloqueto - long do bloqueto a ser configurado.
     */
    public void setBloqueto(long bloqueto) {
        this.bloqueto = bloqueto;
    }

    /**
     * Retorna o valor do atributo endosso.
     * @return o valor do atributo endosso
     */
    public long getEndosso() {
        return endosso;
    }

    /**
     * Especifica o valor do atributo endosso.
     * @param endosso - long do endosso a ser configurado.
     */
    public void setEndosso(long endosso) {
        this.endosso = endosso;
    }

    /**
     * Retorna o valor do atributo parcela.
     * @return o valor do atributo parcela
     */
    public long getParcela() {
        return parcela;
    }

    /**
     * Especifica o valor do atributo parcela.
     * @param parcela - long do parcela a ser configurado.
     */
    public void setParcela(long parcela) {
        this.parcela = parcela;
    }

    /**
     * Retorna o valor do atributo pessoaDepositante.
     * @return o valor do atributo pessoaDepositante
     */
    public long getPessoaDepositante() {
        return pessoaDepositante;
    }

    /**
     * Especifica o valor do atributo pessoaDepositante.
     * @param pessoaDepositante - long do pessoaDepositante a ser configurado.
     */
    public void setPessoaDepositante(long pessoaDepositante) {
        this.pessoaDepositante = pessoaDepositante;
    }

    /**
     * Retorna o valor do atributo protocolo.
     * @return o valor do atributo protocolo
     */
    public int getProtocolo() {
        return protocolo;
    }

    /**
     * Especifica o valor do atributo protocolo.
     * @param protocolo - int do protocolo a ser configurado.
     */
    public void setProtocolo(int protocolo) {
        this.protocolo = protocolo;
    }

    /**
     * Retorna o valor do atributo ramo.
     * @return o valor do atributo ramo
     */
    public String getRamo() {
        return ramo;
    }

    /**
     * Especifica o valor do atributo ramo.
     * @param ramo - int do ramo a ser configurado.
     */
    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    /**
     * Retorna o valor do atributo situacaoArquivoTransferencia.
     * @return o valor do atributo situacaoArquivoTransferencia
     */
    public int getSituacaoArquivoTransferencia() {
        return situacaoArquivoTransferencia;
    }

    /**
     * Especifica o valor do atributo situacaoArquivoTransferencia.
     * @param situacaoArquivoTransferencia - int do situacaoArquivoTransferencia a ser configurado.
     */
    public void setSituacaoArquivoTransferencia(int situacaoArquivoTransferencia) {
        this.situacaoArquivoTransferencia = situacaoArquivoTransferencia;
    }

    /**
     * Retorna o valor do atributo sucursal.
     * @return o valor do atributo sucursal
     */
    public int getSucursal() {
        return sucursal;
    }

    /**
     * Especifica o valor do atributo sucursal.
     * @param sucursal - int do sucursal a ser configurado.
     */
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Retorna o valor do atributo tipoDocumento.
     * @return o valor do atributo tipoDocumento
     */
    public int getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Especifica o valor do atributo tipoDocumento.
     * @param tipoDocumento - int do tipoDocumento a ser configurado.
     */
    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Retorna o valor do atributo tipoGrupoRecebimento.
     * @return o valor do atributo tipoGrupoRecebimento
     */
    public int getTipoGrupoRecebimento() {
        return tipoGrupoRecebimento;
    }

    /**
     * Especifica o valor do atributo tipoGrupoRecebimento.
     * @param tipoGrupoRecebimento - int do tipoGrupoRecebimento a ser configurado.
     */
    public void setTipoGrupoRecebimento(int tipoGrupoRecebimento) {
        this.tipoGrupoRecebimento = tipoGrupoRecebimento;
    }

    /**
     * Retorna o valor do atributo vlrDepositoRegistrado.
     * @return o valor do atributo vlrDepositoRegistrado
     */
    public BigDecimal getVlrDepositoRegistrado() {
        return vlrDepositoRegistrado;
    }

    /**
     * Especifica o valor do atributo vlrDepositoRegistrado.
     * @param vlrDepositoRegistrado - BigDecimal do vlrDepositoRegistrado a ser configurado.
     */
    public void setVlrDepositoRegistrado(BigDecimal vlrDepositoRegistrado) {
        this.vlrDepositoRegistrado = vlrDepositoRegistrado;
    }

    /**
     * Construtor
     * @param codigoDepositoIdentificado - ong.
     */
    public DepositoVO(long codigoDepositoIdentificado) {
        super();
        this.codigoDepositoIdentificado = codigoDepositoIdentificado;
    }

    /**
     * Construtor
     */
    public DepositoVO() {
        super();
    }

    /**
     * Método que retorna o código da situação do depósito.
     * @return Integer - o código da situação do depósito.
     */
	public Integer getCodigoSituacaoDeposito() {
		return codigoSituacaoDeposito;
	}
	
	/**
	 * Método que define o código da situação do depósito.
	 * @param codigoSituacaoDeposito - o código da situação do depósito.
	 */
	public void setCodigoSituacaoDeposito(Integer codigoSituacaoDeposito) {
		this.codigoSituacaoDeposito = codigoSituacaoDeposito;
	}
	
	/**
	 * Método que retorna a lista de parcelas.
	 * @return List<ParcelaCobrancaVO> - a lista de parcelas.
	 */
	public List<ParcelaCobrancaVO> getListaParcelas() {
		return listaParcelas;
	}
	
	/**
	 * Método que define a lista de parcelas.
	 * @param listaParcelas - a lista de parcelas.
	 */
	public void setListaParcelas(List<ParcelaCobrancaVO> listaParcelas) {
		this.listaParcelas = listaParcelas;
	}

	public Integer getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}

	public void setCodigoResponsavelUltimaAtualizacao(Integer codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}

	public String getCodigoIndicativoAtivo() {
		return codigoIndicativoAtivo;
	}

	public void setCodigoIndicativoAtivo(String codigoIndicativoAtivo) {
		this.codigoIndicativoAtivo = codigoIndicativoAtivo;
	}

	public Date getDataHoraAtualizacao() {
		return (Date) dataHoraAtualizacao.clone();
	}

	public void setDataHoraAtualizacao(Date dataHoraAtualizacao) {
		this.dataHoraAtualizacao = (Date) dataHoraAtualizacao.clone();
	}

	public Date getDataInclusao() {
		return (Date) dataInclusao.clone();
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = (Date) dataInclusao.clone();
	}
	
	
}
