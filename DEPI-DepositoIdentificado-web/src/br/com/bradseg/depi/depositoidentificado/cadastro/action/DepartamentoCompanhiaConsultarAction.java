package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.DepartamentoCompanhiaCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoCompanhiaFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.DepartamentoCompanhiaCampo;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.Geral;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Realiza consulta com base nos parâmetros de filtro passados
 * 
 * @author Marcelo Damasceno
 */
@Controller
@Scope("session")
public class DepartamentoCompanhiaConsultarAction extends FiltroAction<DepartamentoCompanhiaCampo, FiltroConsultarForm<DepartamentoCompanhiaCampo>> {

	private static final String LABEL_DEPARTAMENTO_SIGLA = "enum.DepartamentoCompanhiaCampo.Sigla";

	private static final String LABEL_DEPARTAMENTO_NOME = "enum.DepartamentoCompanhiaCampo.Nome";
	
	private static final String LABEL_DEPARTAMENTO_CODIGO = "enum.DepartamentoCampo.Codigo";
	
	private static final String LABEL_COMPANHIA_CODIGO = "enum.CompanhiaSeguradoraCampo.CodigoCompanhia";

	protected static final Logger LOGGER = LoggerFactory.getLogger(DepartamentoCompanhiaConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;
	
	private transient DepartamentoCompanhiaCrudHelper filtroHelper;
	
	@Override
	protected CrudHelper<DepartamentoCompanhiaCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new DepartamentoCompanhiaCrudHelper();
		}
		return filtroHelper;
	}
	
	@Autowired
	public void setFacade(DepartamentoCompanhiaFacade facade) {
		filtroHelper.setFacade(facade);
	}
	
	/**
	 * Sobrescreve o método para forçar a consulta com os critérios já registrados.
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction#validateRefrescar()
	 */
	@Override
	public void validateRefrescar() {
		// Sobreescreve para não limpar as mensagens e refaz a consulta.
		super.realizarConsulta();
	}
	
	/**
	 * Sobrescreve método para impedir que se limpe as mensagens.
	 * @return success
	 */
	@Override
	public String refrescar() {
		return SUCCESS;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction#validarCriterio(br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO)
	 */
	@Override
	protected void validarCriterio(CriterioConsultaVO<DepartamentoCompanhiaCampo> criterio) {
		DepartamentoCompanhiaCampo campo = criterio.getCampo();
		String valor = criterio.getValor();

		switch (campo) {
		case Sigla:
			validaSigla(valor);
			break;
		case NomeDepartamento:
			validaNome(valor);
			break;
		case CodigoCompanhia:
			validaCodigo(valor, "codigoCompanhia", LABEL_COMPANHIA_CODIGO);
			break;
		case CodigoDepartamento:
			validaCodigo(valor, "codigoDepartamento", LABEL_DEPARTAMENTO_CODIGO);
			break;
		default:
			break;
		}
	}

	private void validaCodigo(String valor, String field, String label) {
		if (valor == null || valor.isEmpty()) {
			addFieldError(field, BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					label));
		}
		else if (valor.length() > 4){
			addFieldError(label, BaseUtil.getTextoFormatado(
					Geral.ERRO_CAMPO_EXCESSO,
					label, "4"));
		}
	}

	private void validaSigla(String valor) {
		if (valor == null || valor.isEmpty()) {
			addFieldError("sigla", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					LABEL_DEPARTAMENTO_SIGLA));
		}
		else if (valor.length() > 3){
			addFieldError("sigla", BaseUtil.getTextoFormatado(
					Geral.ERRO_CAMPO_EXCESSO,
					LABEL_DEPARTAMENTO_SIGLA, "3"));
		}
	}

	private void validaNome(String valor) {
		if (valor == null || valor.isEmpty()) {
			addFieldError("nome", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					LABEL_DEPARTAMENTO_NOME));
		}
		else if (valor.length() > 40){
			addFieldError("nome", BaseUtil.getTextoFormatado(
					Geral.ERRO_CAMPO_EXCESSO,
					LABEL_DEPARTAMENTO_NOME, "40"));
		}
	}

}
