package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("request")
public class MenuAction extends ActionSupport implements SessionAware, ServletRequestAware {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuAction.class);

	private static final long serialVersionUID = 8616227301933704827L;


	@Resource
	private transient String www3;

	private String nome;
	private String numeroDocumento;
	private String numeroProposta;
	private String numeroPrefixo;
	private Long cia;
	private Long codigoSucursal;
	private Long codigoApolice;
	private Long chaveNegocio;
	private String browser;
	private Map<String, Object> session;
	private Long subgrupo;
	private HttpServletRequest request;

	private String banco;
	private String agencia;
	private String conta;
	private String cpfCnpjCorretor;

	private transient InputStream inputStream;
	private Long contentLength;
	private String contentDisposition;

	public String menu() {
		
		try {
			URL url = new URL(www3);
			session.put("www3", url);
		} catch (MalformedURLException e) {
			LOGGER.warn("Falha na construção da URL", e);
		}

		return SUCCESS;
	}

	public String consultar() {
		try {
			if (!isValido()) {
				return INPUT;
			}
		} catch (BusinessException e) {

			if ("Fault occurred while processing.".equals(e.getMessage())) {
				e.setMessage("LIMITE MAX. DE 500 LINHAS FOI EXCEDIDO, REFINAR A PESQUISA (412)");
			}
			addActionError(e.getMessage());
			LOGGER.error(e.getMessage(), e);
			return INPUT;
		} catch (IntegrationException e) {
			LOGGER.error(e.getMessage(), e);
			throw new IntegrationException(e.getMessage(), e);
		}

		return "listar";
	}

	public String listar() {
		return "listar";
	}

	public String imprimirTituloCapitalizacao() {

		contentDisposition = "inline; filename=TituloCapitalizacao.pdf";
		try {
			contentLength = Long.valueOf(inputStream.available());
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new IntegrationException(e.getMessage(), e);
		}

		return "tituloCapitalizacao";
	}

	public String buscaCertificadoPDF() {


		try {
			//this.inputStream = new ByteArrayInputStream(
			//		consultacertificadopdfWebService.consultaCertificadoPDF(apoliceVO.getChaveNegocio()));

//			inputStream = new ByteArrayInputStream(
//					consultacertificadopdfWebService.obterAtualCertificadoPDF(apoliceVO.getChaveNegocio()));

		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage(), e);
		}

		contentDisposition = "attachment; filename=certificado-impressao.pdf";

		try {
			contentLength = Long.valueOf(inputStream.available());
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new IntegrationException(e.getMessage(), e);
		}

		return "certificadoPDF";
	}

	private boolean isValido() {

		numeroDocumento = numeroDocumento.replace(".", "");
		numeroDocumento = numeroDocumento.replace("-", "");
		numeroDocumento = numeroDocumento.replace("//", "");

		return true;
	}

	public String detalhar() {
		//obtém as informações da sessão
			return "detalhar";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getNumeroProposta() {
		return numeroProposta;
	}

	public void setNumeroProposta(String numeroProposta) {
		this.numeroProposta = numeroProposta;
	}

	public String getNumeroPrefixo() {
		return numeroPrefixo;
	}

	public void setNumeroPrefixo(String numeroPrefixo) {
		this.numeroPrefixo = numeroPrefixo;
	}

	public Long getCia() {
		return cia;
	}

	public void setCia(Long cia) {
		this.cia = cia;
	}

	public Long getCodigoSucursal() {
		return codigoSucursal;
	}

	public void setCodigoSucursal(Long codigoSucursal) {
		this.codigoSucursal = codigoSucursal;
	}

	public Long getCodigoApolice() {
		return codigoApolice;
	}

	public void setCodigoApolice(Long codigoApolice) {
		this.codigoApolice = codigoApolice;
	}

	public Long getChaveNegocio() {
		return chaveNegocio;
	}

	public void setChaveNegocio(Long chaveNegocio) {
		this.chaveNegocio = chaveNegocio;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public Long getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(Long subgrupo) {
		this.subgrupo = subgrupo;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public void setCpfCnpjCorretor(String cpfCnpjCorretor) {
		this.cpfCnpjCorretor = cpfCnpjCorretor;
	}

	public String getCpfCnpjCorretor() {
		return cpfCnpjCorretor;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentType() {
		return "application/pdf";
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public String getWww3() {
		return www3;
	}

	public void setWww3(String www3) {
		this.www3 = www3;
	}

}
