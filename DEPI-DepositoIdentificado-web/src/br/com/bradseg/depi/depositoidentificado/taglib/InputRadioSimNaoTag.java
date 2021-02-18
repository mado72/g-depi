/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;

/**
 * Tag para controlar quando deve imprimir radio buttons para selecionar Sim ou
 * Não, ou quando deve apenas imprimir um dos valores associados ao parâmetro
 * definido em name. Quando detalhar é true, imprime os valores. Quando é false,
 * imprime os radio buttons.
 * 
 * @author Marcelo Damasceno
 */
public class InputRadioSimNaoTag extends StrutsBodyTagSupport {

	private static final long serialVersionUID = -1933185831519767783L;
	
	private String name;
	
	private String detalhar;
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		String value = findString(name);
		String expr = findString(new StringBuilder("%{").append(detalhar).append("}").toString());
		boolean paraDetalhar = "true".equalsIgnoreCase(expr);
		
		if (! (value.startsWith("%{") && value.endsWith("}"))) {
			StringBuilder sb = new StringBuilder("%{").append(value).append('}');
			value = findString(sb.toString());
		}
		
		StringBuilder sb;
		if (paraDetalhar) {
			if ("S".equals(value)) {
				sb = new StringBuilder("Sim");
			}
			else {
				sb = new StringBuilder("Não");
			}
		}
		else {
			sb = new StringBuilder("<div id=\"")
				.append(id)
				.append("\">")
				.append(radio(name, value, "S", "Sim"))
				.append(radio(name, value, "N", "N\u00E3o")) // Não
				.append("</div>");
		}
		
		try {
			pageContext.getOut().print(sb.toString());
		} catch (IOException e) {
			throw new DEPIBusinessException(e, "erro.interno");
		}
		
		return super.doEndTag();
	}
	
	private String radio(String name, String value, String defaultValue, String label) {
		
		String id = new StringBuilder("AcaoForm_")
			.append(name)
			.append('_')
			.append(defaultValue)
			.toString();
		
		StringBuilder sb = new StringBuilder("<input type=\"radio\" value=\"")
			.append(defaultValue)
			.append("\" name=\"")
			.append(name)
			.append("\" id=\"")
			.append(id)
			.append("\"");
		
		if (defaultValue.equals(value)) {
			sb.append(" checked=\"checked\"");
		}
		
		sb.append("/><label for=\"")
			.append(id)
			.append("\">")
			.append(label)
			.append("</label>");
		
		return sb.toString();
		
	}
	
	/**
	 * Define name
	 * @param name valor name a ser definido
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Define detalhar
	 * @param detalhar valor detalhar a ser definido
	 */
	public void setDetalhar(String detalhar) {
		this.detalhar = detalhar;
	}
}
