package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;


/**
 * Globality
 */
//@Table(schema = ConstantesDEPI.SCHEMA_BANCO, table = "LOG_DEP_IDTFD")
public class LogDepositoVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

 //   @TableField(name = "CDEP_IDTFD")
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

}
