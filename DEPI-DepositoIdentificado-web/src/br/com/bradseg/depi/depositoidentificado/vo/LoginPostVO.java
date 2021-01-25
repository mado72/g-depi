package br.com.bradseg.depi.depositoidentificado.vo;

/**
 * Classe para coletar as informações de login quando recebe um POST. 
 * @author Marcelo Damasceno
 */
public class LoginPostVO {
	
	private String aplicacao;
	
	private String centroCusto;
	
	private String matricula;
	
	private String nome;
	
	private String sucursal;
	
	private String url;
	
	private String valor1;
	
	private String server;

	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getServer() {
		return server;
	}
	
	public void setServer(String server) {
		this.server = server;
	}
	
	public String getValor1() {
		return valor1;
	}
	
	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}

}
