package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;

/**
 * Classe Base de Log 
 * table = "LOG_DEP_IDTFD"
 */
public class LogDepositoVO implements Serializable {

	private static final long serialVersionUID = 5930225696559109557L;
	/**
	 * Logs do Depósito CLOG_DEP_IDTFD
	 */
	private long idLog;
	/**
	 * Nome do atributo de depósito que foi alterado. RNOME_ATRIB_ALT
	 */
	private String fieldName;
	/**
	 * Valor antigo do atributo de depósito que foi alterado. RCONTD_ANTIG
	 */
	private String valorAntigo;
	/**
	 * Valor atual do atributo de depósito que foi alterado. RCONTD_NOVO
	 */
	private String valorNovo;
	/**
	 * Nome do usuario que atualizou o atributo novo. CUSUAR_ATULZ_NOVO
	 */
	private Integer usuarioNovo;
	/**
	 * Nome do usuario que atualizou o atributo antigo. CUSUAR_ATULZ_ANTIG
	 */
	private Integer usuarioAntigo;
	/**
	 * Data da alteração do registro de depósito. DHORA_INCL_REG
	 */
	private Date dtInclusaoRegistro;

	/**
	 * CDEP_IDTFD
	 */
	private long codigo;

	/**
	 * Retorna idLog
	 * 
	 * @return o idLog
	 */
	public long getIdLog() {
		return idLog;
	}

	/**
	 * Define idLog
	 * 
	 * @param idLog
	 *            valor idLog a ser definido
	 */
	public void setIdLog(long idLog) {
		this.idLog = idLog;
	}

	/**
	 * Retorna fieldName
	 * 
	 * @return o fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Define fieldName
	 * 
	 * @param fieldName
	 *            valor fieldName a ser definido
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * Retorna valorAntigo
	 * 
	 * @return o valorAntigo
	 */
	public String getValorAntigo() {
		return valorAntigo;
	}

	/**
	 * Define valorAntigo
	 * 
	 * @param valorAntigo
	 *            valor valorAntigo a ser definido
	 */
	public void setValorAntigo(String valorAntigo) {
		this.valorAntigo = valorAntigo;
	}

	/**
	 * Retorna valorNovo
	 * 
	 * @return o valorNovo
	 */
	public String getValorNovo() {
		return valorNovo;
	}

	/**
	 * Define valorNovo
	 * 
	 * @param valorNovo
	 *            valor valorNovo a ser definido
	 */
	public void setValorNovo(String valorNovo) {
		this.valorNovo = valorNovo;
	}

	/**
	 * Retorna usuarioNovo
	 * 
	 * @return o usuarioNovo
	 */
	public Integer getUsuarioNovo() {
		return usuarioNovo;
	}

	/**
	 * Define usuarioNovo
	 * 
	 * @param usuarioNovo
	 *            valor usuarioNovo a ser definido
	 */
	public void setUsuarioNovo(Integer usuarioNovo) {
		this.usuarioNovo = usuarioNovo;
	}

	/**
	 * Retorna usuarioAntigo
	 * 
	 * @return o usuarioAntigo
	 */
	public Integer getUsuarioAntigo() {
		return usuarioAntigo;
	}

	/**
	 * Define usuarioAntigo
	 * 
	 * @param usuarioAntigo
	 *            valor usuarioAntigo a ser definido
	 */
	public void setUsuarioAntigo(Integer usuarioAntigo) {
		this.usuarioAntigo = usuarioAntigo;
	}

	/**
	 * Retorna dtInclusaoRegistro
	 * 
	 * @return o dtInclusaoRegistro
	 */
	public Date getDtInclusaoRegistro() {
		return BaseUtil.getDate(dtInclusaoRegistro);
	}

	/**
	 * Define dtInclusaoRegistro
	 * 
	 * @param dtInclusaoRegistro
	 *            valor dtInclusaoRegistro a ser definido
	 */
	public void setDtInclusaoRegistro(Date dtInclusaoRegistro) {
		this.dtInclusaoRegistro = BaseUtil.getDate(dtInclusaoRegistro);
	}

	/**
	 * Retorna codigo
	 * 
	 * @return o codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * Define codigo
	 * 
	 * @param codigo
	 *            valor codigo a ser definido
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

}