package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;

import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;

/**
 * Classe que possui dados de uma Agencia
 */
public class AgenciaVO implements Serializable {

    private static final long serialVersionUID = 7180782368361327816L;

    @CicsField(order = 12, size = 4, pattern = "0000")
    private Integer cdBancoExterno;

    @CicsField(order = 13, size = 5, pattern = "00000")
    private Integer cdAgenciaExterno;

    @CicsField(order = 14, size = 4, pattern = "0000")
    private Integer cdBancoInterno;

    @CicsField(order = 15, size = 9, pattern = "000000000")
    private Integer cdAgenciaInterno;

    @CicsField(order = 17, size = 50)
    private String descricaoAgencia;

    /**
     * cdAgenciaExterno
     * @return cdAgenciaExterno
     */
    public Integer getCdAgenciaExterno() {
        return cdAgenciaExterno;
    }

    /**
     * cdAgenciaExterno
     * @param cdAgenciaExterno Integer
     */
    public void setCdAgenciaExterno(Integer cdAgenciaExterno) {
        this.cdAgenciaExterno = cdAgenciaExterno;
    }

    /**
     * cdAgenciaInterno
     * @return cdAgenciaInterno
     */
    public Integer getCdAgenciaInterno() {
        return cdAgenciaInterno;
    }

    /**
     * cdAgenciaInterno
     * @param cdAgenciaInterno Long
     */
    public void setCdAgenciaInterno(Integer cdAgenciaInterno) {
        this.cdAgenciaInterno = cdAgenciaInterno;
    }

    /**
     * cdBancoExterno
     * @return cdBancoExterno
     */

    public Integer getCdBancoExterno() {
        return cdBancoExterno;
    }

    /**
     * cdBancoExterno
     * @param cdBancoExterno Integer
     */
    public void setCdBancoExterno(Integer cdBancoExterno) {
        this.cdBancoExterno = cdBancoExterno;
    }

    /**
     * cdBancoInterno
     * @return cdBancoInterno
     */

    public Integer getCdBancoInterno() {
        return cdBancoInterno;
    }

    /**
     * cdBancoInterno
     * @param cdBancoInterno Integer
     */
    public void setCdBancoInterno(Integer cdBancoInterno) {
        this.cdBancoInterno = cdBancoInterno;
    }

    /**
     * nomeAgencia
     * @return nomeAgencia String
     */
    public String getDescricaoAgencia() {
        return descricaoAgencia;
    }

    /**
     * descricaoAgencia
     * @param descricaoAgencia String
     */
    public void setDescricaoAgencia(String descricaoAgencia) {
        this.descricaoAgencia = descricaoAgencia;
    }

    /**
     * cdAgenciaInterno
     * @return cdAgenciaInterno String
     */
    public String getCodigoAgencia() {
        if (cdAgenciaInterno != null) {
            return cdAgenciaInterno + "-" + "I";
        } else {
            return cdAgenciaExterno + "-" + "E";
        }
    }

    /**
     * cdAgenciaInterno
     * @return cdAgenciaInterno Integer
     */
    public Integer getCodigoAgenciaLabel() {
        if (cdAgenciaInterno != null) {
            return cdAgenciaInterno;
        } else {
            return cdAgenciaExterno;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return this.cdBancoExterno;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AgenciaVO other = (AgenciaVO) obj;
        if (cdAgenciaExterno == null) {
            if (other.cdAgenciaExterno != null) {
                return false;
            }
        } else if (!cdAgenciaExterno.equals(other.cdAgenciaExterno)) {
            return false;
        }
        return true;
    }

    /**
     * Construtor
     */
    public AgenciaVO() {
        super();
    }

    /**
     * Construtor
     * @param cdAgenciaExterno - int
     */
    public AgenciaVO(int cdAgenciaExterno) {
        super();
        this.cdAgenciaExterno = cdAgenciaExterno;
    }

}
