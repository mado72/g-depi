package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;

import com.opensymphony.xwork2.ModelDriven;

/**
 * Superclasse para Action que utiliza um Model, implementando {@link ModelDriven}.
 * <p>
 * Fornece suporte a dados em sessão e método para retirar da sessão os dados do formulário.
 * </p>
 * @author Marcelo Damasceno
 *
 * @param <T> Tipo do Model
 */
@Controller
public abstract class BaseModelAction<T> extends BaseAction implements ModelDriven<T>,
		SessionAware {

	private static final long serialVersionUID = 4671957667151873914L;

	protected Map<String, Object> sessionData;

	@Override
	public void setSession(Map<String, Object> sessionData) {
		this.sessionData = sessionData;
	}
	
	private StringBuilder listToString(Collection<String> list, String separator) {
		StringBuilder sb = new StringBuilder();
		for (String string : list) {
			sb.append(string).append(separator);
		}
		return sb;
	}
	
	protected String dumpErros() {
		StringBuilder dump = new StringBuilder();
		if (hasActionErrors()) {
			dump.append("Action Errors:")
					.append(System.lineSeparator())
					.append(listToString(getActionErrors(),
							System.lineSeparator()));
		}
		if (hasFieldErrors()) {
			dump.append("Field Errors:").append(System.lineSeparator());
			final String fieldSeparator = new StringBuilder(System.lineSeparator()).append("\t").toString();
			for (String field : getFieldErrors().keySet()) {
				dump.append("* ")
						.append(field)
						.append(listToString(getFieldErrors().get(field),
								fieldSeparator));
			}
		}
		if (hasActionMessages()) {
			dump.append("Action Messages:")
					.append(System.lineSeparator())
					.append(listToString(getActionMessages(),
							System.lineSeparator()));
		}
		return dump.toString();
	}

	/**
	 * @param valor
	 * @param campo
	 */
	protected void validarValor(String valor, IEntidadeCampo campo) {
		
		if (valor == null || valor.isEmpty()) {
			addFieldError(campo.name(), BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					BaseUtil.getTexto(campo.getClass(), campo.name())));
		}
		else if (campo.getSize() > 0 && valor.length() > campo.getSize()){
			addFieldError(campo.name(), BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRO_CAMPO_EXCESSO,
					BaseUtil.getTexto(campo.getClass(), campo.name()),
					String.valueOf(campo.getSize())));
		}
	}

}
