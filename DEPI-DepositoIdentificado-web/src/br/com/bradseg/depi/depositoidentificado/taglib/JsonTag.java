package br.com.bradseg.depi.depositoidentificado.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts2.views.jsp.StrutsBodyTagSupport;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTag extends StrutsBodyTagSupport {

	private static final long serialVersionUID = 5909196572454704144L;
	
	private String value;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public JsonTag() {
		mapper.setSerializationInclusion(Include.NON_NULL);
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public int doEndTag() throws JspException {

		try {
			Object object = findValue(this.value);
			String json = mapper.writeValueAsString(object);
			JspWriter out = pageContext.getOut();
			out.print(json);
		} catch (Exception e) {
			throw new JspException(e);
		}
		
		return super.doEndTag();
	}

}
