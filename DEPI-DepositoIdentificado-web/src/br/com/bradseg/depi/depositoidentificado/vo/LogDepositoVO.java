package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Globality
 */
//@Table(schema = ConstantesDEPI.SCHEMA_BANCO, table = "LOG_DEP_IDTFD")
public class LogDepositoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    /**
     * Logs do Dep�sito
     */
    //@TableField(name = "CLOG_DEP_IDTFD")
    private long idLog;
    /**
     * Nome do atributo de dep�sito que foi alterado.
     */
    //@TableField(name = "RNOME_ATRIB_ALT")
    private String fieldName;
    /**
     * Valor antigo do atributo de dep�sito que foi alterado.
     */
    //@TableField(name = "RCONTD_ANTIG")
    private String valorAntigo;
    /**
     * Valor atual do atributo de dep�sito que foi alterado.
     */
    //@TableField(name = "RCONTD_NOVO")
    private String valorNovo;
    /**
     * Nome do usuario que atualizou o atributo novo.
     */
    //@TableField(name = "CUSUAR_ATULZ_NOVO", converter = BigDecimalToLongPersistenceConverter.class)
    private BigDecimal usuarioNovo;
    /**
     * Nome do usuario que atualizou o atributo antigo.
     */
    //@TableField(name = "CUSUAR_ATULZ_ANTIG", converter = BigDecimalToLongPersistenceConverter.class)
    private BigDecimal usuarioAntigo;
    /**
     * Data da altera��o do registro de dep�sito.
     */
    //@TableField(name = "DHORA_INCL_REG")
    private Date dtInclusaoRegistro;
    
    
    //@TableField(name = "CDEP_IDTFD")
    private long codigo;

    /**
     * Retorna o valor do atributo codigo.
     * @return o valor do atributo codigo
     */
    public long getCodigo() {
        return codigo;
    }

    /**
         * Especifica o valor do atributo codigo.
         * @param codigo - long do codigo a ser configurado.
         */
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

	public long getIdLog() {
		return idLog;
	}

	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValorAntigo() {
		return valorAntigo;
	}

	public void setValorAntigo(String valorAntigo) {
		this.valorAntigo = valorAntigo;
	}

	public String getValorNovo() {
		return valorNovo;
	}

	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}

	public BigDecimal getUsuarioNovo() {
		return usuarioNovo;
	}

	public void setUsuarioNovo(BigDecimal usuarioNovo) {
		this.usuarioNovo = usuarioNovo;
	}

	public BigDecimal getUsuarioAntigo() {
		return usuarioAntigo;
	}

	public void setUsuarioAntigo(BigDecimal usuarioAntigo) {
		this.usuarioAntigo = usuarioAntigo;
	}

	public Date getDtInclusaoRegistro() {
		return BaseUtil.getDate(dtInclusaoRegistro);
	}

	public void setDtInclusaoRegistro(Date dtInclusaoRegistro) {
		this.dtInclusaoRegistro = BaseUtil.getDate(dtInclusaoRegistro);
	}

}
