package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe ManutencoesAnaliticoVO
 * @author Globality
 */
public class ManutencoesAnaliticoVO implements Serializable{

    
    /**
     * A(O)serialVersionUID.
     */
    private static final long serialVersionUID = -1451718024062829076L;

    private String codigoTipoAcao;

    private Integer codigoSituacao;
    private String situacao;
    private Long bloquete;
    private Integer sucursal;
    private String ramoSegur;
    private Integer apolice;
    private Integer item;
    private Integer tipoDoc;
    private Integer endosso;
    private Integer parcelaPremio;
    private String cpfCnpj;
    private Long codigoAutorizador;
    private Long codigoDigitoIdentificador;
    private Long codigoPessoa;
    private Date vencimento;
    private Long matricResp;
    private Double valorRegistrado = 0.00 ;
    private Double valorPago = 0.00 ;
    private Integer codigoBanco;
    private Integer codigoAgencia;
    private Long codigoConta;
    private Integer codigoCia;
    private String descrBanco;
    private String descrCia;
    private String descrConta;
    private String codigoAutorizadorComDv;

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
     * Retorna o valor do atributo cpfCnpj.
     * @return o valor do atributo cpfCnpj
     */
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * Especifica o valor do atributo descricaoBanco.
     * @param descrBanco - String do descricaoBanco a ser configurado.
     */
    public void setDescricaoBanco(String descrBanco) {
        this.descrBanco = descrBanco;
    }

    /**
     * Retorna o valor do atributo descricaoCia.
     * @return o valor do atributo descrCia
     */
    public String getDescricaoCia() {
        return descrCia;
    }
    /**
     * Especifica o valor do atributo cpfCnpj.
     * @param cpfCnpj - String do cpfCnpj a ser configurado.
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * Retorna o valor do atributo descricaoBanco.
     * @return o valor do atributo descricaoBanco
     */
    public String getDescricaoBanco() {
        return descrBanco;
    }

    /**
     * Especifica o valor do atributo descricaoCia.
     * @param descrCia - String do descricaoCia a ser configurado.
     */
    public void setDescricaoCia(String descrCia) {
        this.descrCia = descrCia;
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
     * @param endosso - String do endosso a ser configurado.
     */
    public void setEndosso(Integer endosso) {
        this.endosso = endosso;
    }
    /**
     * Retorna o valor do atributo descricaoConta.
     * @return o valor do atributo descrConta
     */
    public String getDescricaoConta() {
        return descrConta;
    }

    /**
     * Especifica o valor do atributo descricaoConta.
     * @param descrConta - String do descricaoConta a ser configurado.
     */
    public void setDescricaoConta(String descrConta) {
        this.descrConta = descrConta;
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
     * Retorna o valor do atributo parcela.
     * @return o valor do atributo parcela
     */
    public Integer getParcela() {
        return parcelaPremio;
    }

    /**
     * Especifica o valor do atributo parcela.
     * @param parcelaPremio - Integer do parcela a ser configurado.
     */
    public void setParcela(Integer parcelaPremio) {
        this.parcelaPremio = parcelaPremio;
    }
    /**
     * Retorna o valor do atributo matricula.
     * @return o valor do atributo matricula
     */
    public Long getMatricula() {
        return matricResp;
    }

    /**
     * Especifica o valor do atributo matricula.
     * @param matricResp - Long do matricula a ser configurado.
     */
    public void setMatricula(Long matricResp) {
        this.matricResp = matricResp;
    }
    /**
     * Retorna o valor do atributo ramo.
     * @return o valor do atributo ramo
     */
    public String getRamo() {
        return ramoSegur;
    }

    /**
     * Especifica o valor do atributo ramo.
     * @param ramo - Integer do ramo a ser configurado.
     */
    public void setRamo(String ramo) {
        this.ramoSegur = ramo;
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
     * Retorna o valor do atributo tipo.
     * @return o valor do atributo tipo
     */
    public Integer getTipo() {
        return tipoDoc;
    }

    /**
     * Especifica o valor do atributo tipo.
     * @param tipoDoc - Integer do tipo a ser configurado.
     */
    public void setTipo(Integer tipoDoc) {
        this.tipoDoc = tipoDoc;
    }
    /**
     * Retorna o valor do atributo vencimento.
     * @return o valor do atributo vencimento
     */
    public Date getVencimento() {
        return (Date) vencimento.clone();
    }

    /**
     * Especifica o valor do atributo vencimento.
     * @param vencimento - Date do vencimento a ser configurado.
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = (Date) vencimento.clone();
    }

    /**
     * Retorna o valor do atributo valorRegistrado.
     * @return o valor do atributo valorRegistrado
     */
    public Double getValorRegistrado() {
        return valorRegistrado;
    }

    /**
     * Especifica o valor do atributo valorRegistrado.
     * @param valorRegistrado - java.math.BigDecimal do valorRegistrado a ser configurado.
     */
    public void setValorRegistrado(Double valorRegistrado) {
        this.valorRegistrado = valorRegistrado;
    }

    /**
     * Retorna o valor do atributo valorPago.
     * @return o valor do atributo valorPago
     */
    public Double getValorPago() {
        return valorPago;
    }

    /**
     * Especifica o valor do atributo valorPago.
     * @param valorPago - java.math.BigDecimal do valorPago a ser configurado.
     */
    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
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

        /**
     * Retorna o valor do atributo codigoTipoAcao.
     * @return o valor do atributo codigoTipoAcao
     */
    public String getCodigoTipoAcao() {
        return codigoTipoAcao;
    }

    /**
     * Especifica o valor do atributo codigoTipoAcao.
     * @param codigoTipoAcao - String do codigoTipoAcao a ser configurado.
     */
    public void setCodigoTipoAcao(String codigoTipoAcao) {
        this.codigoTipoAcao = codigoTipoAcao;
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
	 * obtem ramoSegur.
	 * @return the ramoSegur
	 */
	public String getRamoSegur() {
		return ramoSegur;
	}

	/**
	 * Define ramoSegur.
	 * @param ramoSegur the ramoSegur to set
	 */
	public void setRamoSegur(String ramoSegur) {
		this.ramoSegur = ramoSegur;
	}

	/**
	 * Obtem tipoDoc.
	 * @return the tipoDoc
	 */
	public Integer getTipoDoc() {
		return tipoDoc;
	}

	/**
	 * Define tipoDoc.
	 * @param tipoDoc the tipoDoc to set
	 */
	public void setTipoDoc(Integer tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	/**
	 * Obtem parcelaPremio.
	 * @return the parcelaPremio
	 */
	public Integer getParcelaPremio() {
		return parcelaPremio;
	}

	/**
	 * Define parcelaPremio
	 * @param parcelaPremio the parcelaPremio to set
	 */
	public void setParcelaPremio(Integer parcelaPremio) {
		this.parcelaPremio = parcelaPremio;
	}

	/**
	 * Obtem matricResp.
	 * @return the matricResp
	 */
	public Long getMatricResp() {
		return matricResp;
	}

	/**
	 * Define matricResp.
	 * @param matricResp the matricResp to set
	 */
	public void setMatricResp(Long matricResp) {
		this.matricResp = matricResp;
	}

	/**
	 * Obtem descrBanco.
	 * @return the descrBanco
	 */
	public String getDescrBanco() {
		return descrBanco;
	}

	/**
	 * Define descrBanco.
	 * @param descrBanco the descrBanco to set
	 */
	public void setDescrBanco(String descrBanco) {
		this.descrBanco = descrBanco;
	}

	/**
	 * Obtem descrCia.
	 * @return the descrCia
	 */
	public String getDescrCia() {
		return descrCia;
	}

	/**
	 * Define descrCia.
	 * @param descrCia the descrCia to set
	 */
	public void setDescrCia(String descrCia) {
		this.descrCia = descrCia;
	}

	/**
	 * Obtem descrConta.
	 * @return the descrConta
	 */
	public String getDescrConta() {
		return descrConta;
	}

	/**
	 * Define descrConta.
	 * @param descrConta the descrConta to set
	 */
	public void setDescrConta(String descrConta) {
		this.descrConta = descrConta;
	}


}
