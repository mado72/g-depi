package br.com.bradseg.depi.depositoidentificado.enums;

/**
 * Classe responsável por representar dos tipos de relatórios gerados no Jasper Boleto/Recibo
 * 
 * @author CPM Braxis Capgemini em 15/07/2017
 */
public enum TipoReportEnum {

	BOLETO_ERRO	(0,"erro.jasper"),
	BOLETO		(1,"boleto.jasper"), 
	RECIBO		(2,"recibo.jasper"),	
	MASTER		(3,"master.jasper")
	;

	private Integer codigo;
	private String  arquivoJasper;

	private TipoReportEnum(Integer codigo, String arquivoJasper) {
		this.setCodigo(codigo);
		this.setArquivoJasper(arquivoJasper);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getArquivoJasper() {
		return arquivoJasper;
	}

	public void setArquivoJasper(String arquivoJasper) {
		this.arquivoJasper = arquivoJasper;
	}

	
	public static TipoReportEnum getArquivoJasper(Integer codigo){
		TipoReportEnum enumEncontrado = BOLETO_ERRO;
		for (TipoReportEnum e : TipoReportEnum.values()) {
			if (e.getCodigo().equals(codigo)) {
				enumEncontrado = e;
				break;
			}
		}		
		return enumEncontrado;
	}

}