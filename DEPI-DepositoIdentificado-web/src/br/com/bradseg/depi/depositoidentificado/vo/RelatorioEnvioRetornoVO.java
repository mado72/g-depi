package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe RelatorioEnvioRetornoAnaliticoVO.
 */
public class RelatorioEnvioRetornoVO implements Serializable{

   private static final long serialVersionUID = -4759118749891158867L;
    
    private Integer codigoSituacao;

    private String situacao;

    private Long bloquete;

    private Integer sucursal;

    private String ramo;

    private Integer apolice;

    private Integer item;

    private Integer tipo;

    private Integer endosso;

    private Integer parcela;

    private String cpfCnpj;

    private Long codigoAutorizador;
    
    private Long codigoDigitoIdentificador;
    
    private Long codigoPessoa;

    private Date vencimento;

    private Long matricula;
    
    private java.math.BigDecimal valorRegistrado;

    private java.math.BigDecimal valorPago;

    private Integer codigoBanco;

    private Integer codigoAgencia;

    private Long codigoConta;

    private Integer codigoCia;
    
    private String descricaoBanco;
    
    private String descricaoCia;
    
    private String descricaoConta;
    
    private String codigoAutorizadorComDv;

    private Integer qtdEnviados;
    private Integer qtdAceitos;
    private Integer qtdRejeitados;
    private Integer qtdCancelados;

    private BigDecimal totalEnviados;
    private BigDecimal totalAceitos;
    private BigDecimal totalRejeitados;
    private BigDecimal totalCancelados;
    
    /**
     * Retorna o valor do atributo codigoConta.
     * @return o valor do atributo codigoConta
     */
    public Long getCodigoConta() {
        return codigoConta;
    }

    /**
     * Especifica o valor do atributo codigoConta.
     * @param codigoConta - Long do codigoConta a ser configurado.
     */
    public void setCodigoConta(Long codigoConta) {
        this.codigoConta = codigoConta;
    }

    
    /**
     * Retorna o valor do atributo codigoAutorizador.
     * @return o valor do atributo codigoAutorizador
     */
    public Long getCodigoAutorizador() {
        return codigoAutorizador;
    }

    /**
     * Especifica o valor do atributo codigoAutorizador.
     * @param codigoAutorizador - Long do codigoAutorizador a ser configurado.
     */
    public void setCodigoAutorizador(Long codigoAutorizador) {
        this.codigoAutorizador = codigoAutorizador;
    }

    /**
     * Retorna o cpfCnpj.
     * @return O atributo cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * Especifica o cpfCnpj.
     * @param cpfCnpj String do cpfCnpj a ser setado
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * Especifica o descricaoCia.
     * @param descricaoCia String do descricaoCia a ser setado
     */
    public void setDescricaoCia(String descricaoCia) {
        this.descricaoCia = descricaoCia;
    }

    /**
     * Retorna o descricaoConta.
     * @return O atributo descricaoConta
     */
    public String getDescricaoConta() {
        return descricaoConta;
    }

    /**
     * Especifica o descricaoConta.
     * @param descricaoConta String do descricaoConta a ser setado
     */
    public void setDescricaoConta(String descricaoConta) {
        this.descricaoConta = descricaoConta;
    }

    /**
     * Retorna o descricaoCia.
     * @return O atributo descricaoCia
     */
    public String getDescricaoCia() {
        return descricaoCia;
    }

    /**
     * Retorna o descricaoBanco.
     * @return O atributo descricaoBanco
     */
    public String getDescricaoBanco() {
        return descricaoBanco;
    }

    /**
     * Especifica o descricaoBanco.
     * @param descricaoBanco String do descricaoBanco a ser setado
     */
    public void setDescricaoBanco(String descricaoBanco) {
        this.descricaoBanco = descricaoBanco;
    }

    
    /**
     * Retorna o valor do atributo endosso.
     * @return o valor do atributo endosso
     */
    public Integer getEndosso() {
        return endosso;
    }

    /**
     * Especifica o valor do atributo endosso.
     * @param endosso - Integer do endosso a ser configurado.
     */
    public void setEndosso(Integer endosso) {
        this.endosso = endosso;
    }



    /**
     * Retorna o ramo.
     * @return O atributo ramo
     */
    public String getRamo() {
        return ramo;
    }

    /**
     * Especifica o ramo.
     * @param ramo Integer do ramo a ser setado
     */
    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    
    /**
     * Retorna o valor do atributo matricula.
     * @return o valor do atributo matricula
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * Especifica o valor do atributo matricula.
     * @param matricula - Long do matricula a ser configurado.
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }
    /**
     * Retorna o valor do atributo situacao.
     * @return o valor do atributo situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Especifica o valor do atributo situacao.
     * @param situacao - String do situacao a ser configurado.
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    /**
     * Retorna o valor do atributo codigoSituacao.
     * @return o valor do atributo codigoSituacao
     */
    public Integer getCodigoSituacao() {
        return codigoSituacao;
    }

    /**
     * Especifica o valor do atributo codigoSituacao.
     * @param codigoSituacao - Integer do codigoSituacao a ser configurado.
     */
    public void setCodigoSituacao(Integer codigoSituacao) {
        this.codigoSituacao = codigoSituacao;
    }
    /**
     * Retorna o valor do atributo apolice.
     * @return o valor do atributo apolice
     */
    public Integer getApolice() {
        return apolice;
    }

    /**
     * Especifica o valor do atributo apolice.
     * @param apolice - BigDecimal do apolice a ser configurado.
     */
    public void setApolice(Integer apolice) {
        this.apolice = apolice;
    }

    /**
     * Retorna o valor do atributo item.
     * @return o valor do atributo item
     */
    public Integer getItem() {
        return item;
    }

    /**
     * Especifica o valor do atributo item.
     * @param item - Integer do item a ser configurado.
     */
    public void setItem(Integer item) {
        this.item = item;
    }
    /**
     * Retorna o valor do atributo bloquete.
     * @return o valor do atributo bloquete
     */
    public Long getBloquete() {
        return bloquete;
    }

    /**
     * Especifica o valor do atributo bloquete.
     * @param bloquete - Long do bloquete a ser configurado.
     */
    public void setBloquete(Long bloquete) {
        this.bloquete = bloquete;
    }

    /**
     * Retorna o valor do atributo parcela.
     * @return o valor do atributo parcela
     */
    public Integer getParcela() {
        return parcela;
    }

    /**
     * Especifica o valor do atributo parcela.
     * @param parcela - Integer do parcela a ser configurado.
     */
    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    /**
     * Retorna o tipo.
     * @return O atributo tipo
     */
    public Integer getTipo() {
        return tipo;
    }

    /**
     * Especifica o tipo.
     * @param tipo Integer do tipo a ser setado
     */
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    /**
     * Retorna o valor do atributo sucursal.
     * @return o valor do atributo sucursal
     */
    public Integer getSucursal() {
        return sucursal;
    }

    /**
     * Especifica o valor do atributo sucursal.
     * @param sucursal - Integer do sucursal a ser configurado.
     */
    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }


    /**
     * Retorna o valorPago.
     * @return O atributo valorPago
     */
    public java.math.BigDecimal getValorPago() {
        return valorPago;
    }

    /**
     * Especifica o valorPago.
     * @param valorPago java.math.BigDecimal do valorPago a ser setado
     */
    public void setValorPago(java.math.BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    /**
     * Retorna o vencimento.
     * @return O atributo vencimento
     */
    public Date getVencimento() {
         return BaseUtil.getDate(vencimento);
    }

    /**
     * Retorna o valorRegistrado.
     * @return O atributo valorRegistrado
     */
    public java.math.BigDecimal getValorRegistrado() {
        return valorRegistrado;
    }

    /**
     * Especifica o valorRegistrado.
     * @param valorRegistrado java.math.BigDecimal do valorRegistrado a ser setado
     */
    public void setValorRegistrado(java.math.BigDecimal valorRegistrado) {
        this.valorRegistrado = valorRegistrado;
    }

    /**
     * Especifica o vencimento.
     * @param vencimento Date do vencimento a ser setado
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = (Date) vencimento.clone();
    }

    /**
     * Retorna o valor do atributo codigoBanco.
     * @return o valor do atributo codigoBanco
     */
    public Integer getCodigoBanco() {
        return codigoBanco;
    }

    /**
     * Especifica o valor do atributo codigoBanco.
     * @param codigoBanco - Integer do codigoBanco a ser configurado.
     */
    public void setCodigoBanco(Integer codigoBanco) {
        this.codigoBanco = codigoBanco;
    }

    /**
     * Retorna o valor do atributo codigoAgencia.
     * @return o valor do atributo codigoAgencia
     */
    public Integer getCodigoAgencia() {
        return codigoAgencia;
    }

    /**
     * Especifica o valor do atributo codigoAgencia.
     * @param codigoAgencia - Integer do codigoAgencia a ser configurado.
     */
    public void setCodigoAgencia(Integer codigoAgencia) {
        this.codigoAgencia = codigoAgencia;
    }


    
    /**
     * Retorna o valor do atributo codigoCia.
     * @return o valor do atributo codigoCia
     */
    public Integer getCodigoCia() {
        return codigoCia;
    }

    /**
     * Especifica o valor do atributo codigoCia.
     * @param codigoCia - Integer do codigoCia a ser configurado.
     */
    public void setCodigoCia(Integer codigoCia) {
        this.codigoCia = codigoCia;
    }

    /**
     * Retorna o valor do atributo codigoPessoa.
     * @return o valor do atributo codigoPessoa
     */
    public Long getCodigoPessoa() {
        return codigoPessoa;
    }

    /**
     * Especifica o valor do atributo codigoPessoa.
     * @param codigoPessoa - Long do codigoPessoa a ser configurado.
     */
    public void setCodigoPessoa(Long codigoPessoa) {
        this.codigoPessoa = codigoPessoa;
    }

    /**
     * Retorna o codigoDigitoIdentificador.
     * @return O atributo codigoDigitoIdentificador
     */
    public Long getCodigoDigitoIdentificador() {
        return codigoDigitoIdentificador;
    }

    /**
     * Especifica o codigoDigitoIdentificador.
     * @param codigoDigitoIdentificador Long do codigoDigitoIdentificador a ser setado
     */
    public void setCodigoDigitoIdentificador(Long codigoDigitoIdentificador) {
        this.codigoDigitoIdentificador = codigoDigitoIdentificador;
    }
    /**
     * Retorna o codigoAutorizadorComDv.
     * @return O atributo codigoAutorizadorComDv
     */
    public String getCodigoAutorizadorComDv() {
        return codigoAutorizadorComDv;
    }

    /**
     * Especifica o codigoAutorizadorComDv.
     * @param codigoAutorizadorComDv String do codigoAutorizadorComDv a ser setado
     */
    public void setCodigoAutorizadorComDv(String codigoAutorizadorComDv) {
        this.codigoAutorizadorComDv = codigoAutorizadorComDv;
    }

    @Override
	public String toString() {
    		
		return new StringBuilder("RelatorioEnvioRetornoAnaliticoVO [")
				.append(toString("codigoSituacao", codigoSituacao, true))
				.append(toString("situacao", situacao, true))
				.append(toString("bloquete", bloquete, true))
				.append(toString("sucursal", sucursal, true))
				.append(toString("apolice", apolice, true))
				.append(toString("tipo", tipo, true))
				.append(toString("parcela", parcela, true))
				.append(toString("cpfCnpj", cpfCnpj, true))
				.append(toString("codigoAutorizador", codigoAutorizador, true))
				.append(toString("codigoDigitoIdentificador", codigoDigitoIdentificador, true))
				.append(toString("codigoPessoa", codigoPessoa, true))
				.append(toString("vencimento", vencimento, true))
				.append(toString("matricula", matricula, true))
				.append(toString("valorRegistrado", valorRegistrado, true))
				.append(toString("valorPago", valorPago, true))
				.append(toString("codigoBanco", codigoBanco, true))
				.append(toString("codigoAgencia", codigoAgencia, true))
				.append(toString("codigoConta", codigoConta, true))
				.append(toString("codigoCia", codigoCia, true))
				.append(toString("descricaoBanco", descricaoBanco, true))
				.append(toString("descricaoCia", descricaoCia, true))
				.append(toString("descricaoConta", descricaoConta, true))
				.append(toString("codigoAutorizadorComDv", codigoAutorizadorComDv, false))
				.append("]")
				.toString();
    }
    
    private String toString(String property, Object value, boolean sufix) {
    	StringBuilder sb = new StringBuilder(property).append('=');
    	if (value != null) {
    		sb.append(value);
    	}
    	if (sufix) {
    		sb.append(", ");
    	}
    	return sb.toString();
    }

	/**
	 * Retorna qtdEnviados
	 * @return o qtdEnviados
	 */
	public Integer getQtdEnviados() {
		return qtdEnviados;
	}

	/**
	 * Define qtdEnviados
	 * @param qtdEnviados o qtdEnviados a ser configurado
	 */
	public void setQtdEnviados(Integer qtdEnviados) {
		this.qtdEnviados = qtdEnviados;
	}

	/**
	 * Retorna qtdAceitos
	 * @return o qtdAceitos
	 */
	public Integer getQtdAceitos() {
		return qtdAceitos;
	}

	/**
	 * Define qtdAceitos
	 * @param qtdAceitos o qtdAceitos a ser configurado
	 */
	public void setQtdAceitos(Integer qtdAceitos) {
		this.qtdAceitos = qtdAceitos;
	}

	/**
	 * Retorna qtdRejeitados
	 * @return o qtdRejeitados
	 */
	public Integer getQtdRejeitados() {
		return qtdRejeitados;
	}

	/**
	 * Define qtdRejeitados
	 * @param qtdRejeitados o qtdRejeitados a ser configurado
	 */
	public void setQtdRejeitados(Integer qtdRejeitados) {
		this.qtdRejeitados = qtdRejeitados;
	}

	/**
	 * Retorna qtdCancelados
	 * @return o qtdCancelados
	 */
	public Integer getQtdCancelados() {
		return qtdCancelados;
	}

	/**
	 * Define qtdCancelados
	 * @param qtdCancelados o qtdCancelados a ser configurado
	 */
	public void setQtdCancelados(Integer qtdCancelados) {
		this.qtdCancelados = qtdCancelados;
	}

	/**
	 * Retorna totalEnviados
	 * @return o totalEnviados
	 */
	public BigDecimal getTotalEnviados() {
		return totalEnviados;
	}

	/**
	 * Define totalEnviados
	 * @param totalEnviados o totalEnviados a ser configurado
	 */
	public void setTotalEnviados(BigDecimal totalEnviados) {
		this.totalEnviados = totalEnviados;
	}

	/**
	 * Retorna totalAceitos
	 * @return o totalAceitos
	 */
	public BigDecimal getTotalAceitos() {
		return totalAceitos;
	}

	/**
	 * Define totalAceitos
	 * @param totalAceitos o totalAceitos a ser configurado
	 */
	public void setTotalAceitos(BigDecimal totalAceitos) {
		this.totalAceitos = totalAceitos;
	}

	/**
	 * Retorna totalRejeitados
	 * @return o totalRejeitados
	 */
	public BigDecimal getTotalRejeitados() {
		return totalRejeitados;
	}

	/**
	 * Define totalRejeitados
	 * @param totalRejeitados o totalRejeitados a ser configurado
	 */
	public void setTotalRejeitados(BigDecimal totalRejeitados) {
		this.totalRejeitados = totalRejeitados;
	}

	/**
	 * Retorna totalCancelados
	 * @return o totalCancelados
	 */
	public BigDecimal getTotalCancelados() {
		return totalCancelados;
	}

	/**
	 * Define totalCancelados
	 * @param totalCancelados o totalCancelados a ser configurado
	 */
	public void setTotalCancelados(BigDecimal totalCancelados) {
		this.totalCancelados = totalCancelados;
	}
   
    
}