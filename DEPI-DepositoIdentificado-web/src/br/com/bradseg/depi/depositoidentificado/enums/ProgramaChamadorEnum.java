package br.com.bradseg.depi.depositoidentificado.enums;


/**
 * Classe Enum do RSAP Boleto com os códigos e descrições dos sites.
 */
public enum ProgramaChamadorEnum {
	
	EMISSAO_EXPRESSA(2,"Emissao Expressa","eeem-emissaoexpressa"),
	CCB_ONLINE(3,"RSAP BOLETO","rsap-boleto"),
	SITE_ESTIPULANTE(4,"Site Estipulante","estipulante"),
	SITE_SEGURADO(5,"Site Segurado","segurado"),
	PCBS(6,"PCBS ConsPosApolice","pcbs-consposapolice");

	private final Integer codigo;
	private final String descricao;
	private final String centroCusto;
	
	
	/**
	 * Retorna o codigo
	 * @return the codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}


	/**
	 * Retorna a descricao
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}


	/**
	 * Retorna o centro de custo
	 * @return the centroCusto
	 */
	public String getCentroCusto() {
		return centroCusto;
	}


	private ProgramaChamadorEnum(Integer codigo, String descricao,String centroCusto) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.centroCusto = centroCusto;
	}
	
}
