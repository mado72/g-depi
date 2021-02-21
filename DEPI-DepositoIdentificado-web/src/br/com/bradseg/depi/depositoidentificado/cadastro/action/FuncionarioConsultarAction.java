package br.com.bradseg.depi.depositoidentificado.cadastro.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.cadastro.helper.CrudHelper;
import br.com.bradseg.depi.depositoidentificado.cadastro.helper.FuncionarioCrudHelper;
import br.com.bradseg.depi.depositoidentificado.facade.UsuarioFacade;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction;
import br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroConsultarForm;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.FuncionarioCampo;
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
public class FuncionarioConsultarAction extends FiltroAction<FuncionarioCampo, FiltroConsultarForm<FuncionarioCampo>> {

	protected static final Logger LOGGER = LoggerFactory.getLogger(FuncionarioConsultarAction.class);

	private static final long serialVersionUID = -7675543657126275320L;

	private static final String LABEL_GRID_USUARIO_MATRICULA = "label.grid.usuario.codigoUsuario";
	
	private static final String LABEL_GRID_USUARIO_NOME = "label.grid.usuario.nomeUsuario";

	private transient FuncionarioCrudHelper filtroHelper;
	
	private String action;
	
	private String codCompanhia;
	
	private String siglaDepartamento;
	
	@Override
	protected CrudHelper<FuncionarioCampo, ?, ?> getFiltroHelper() {
		if (filtroHelper == null) {
			filtroHelper = new FuncionarioCrudHelper();
		}
		return filtroHelper;
	}
	
	@Autowired
	public void setFacade(UsuarioFacade facade) {
		filtroHelper.setFacade(facade);
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.funcao.action.FiltroAction#validarCriterio(br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO)
	 */
	@Override
	protected void validarCriterio(CriterioConsultaVO<FuncionarioCampo> criterio) {
		super.validarCriterio(criterio);
		
		FuncionarioCampo campo = criterio.getCampo();
		String valor = criterio.getValor();

		switch (campo) {
		case Matricula:
			validaMatricula(valor);
			break;
		case Nome:
			validaNome(valor);
			break;
		default:
			break;
		}
	}

	private void validaMatricula(String valor) {
		if (valor == null || valor.isEmpty()) {
			addFieldError("matricula", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					LABEL_GRID_USUARIO_MATRICULA));
		}
	}

	private void validaNome(String valor) {
		if (valor == null || valor.isEmpty()) {
			addFieldError("nome", BaseUtil.getTextoFormatado(
					ConstantesDEPI.Geral.ERRORS_REQUIRED,
					LABEL_GRID_USUARIO_NOME));
		}
		else if (valor.length() > 40){
			addFieldError("nome", BaseUtil.getTextoFormatado(
					Geral.ERRO_CAMPO_EXCESSO,
					LABEL_GRID_USUARIO_NOME, "40"));
		}
	}
	
	/**
	 * Retorna action
	 * @return o action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * Define action
	 * @param action valor action a ser definido
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Retorna codCompanhia
	 * @return o codCompanhia
	 */
	public String getCodCompanhia() {
		return codCompanhia;
	}
	
	/**
	 * Define codCompanhia
	 * @param codCompanhia valor codCompanhia a ser definido
	 */
	public void setCodCompanhia(String codCompanhia) {
		this.codCompanhia = codCompanhia;
	}
	
	/**
	 * Retorna siglaDepartamento
	 * @return o siglaDepartamento
	 */
	public String getSiglaDepartamento() {
		return siglaDepartamento;
	}
	
	/**
	 * Define siglaDepartamento
	 * @param siglaDepartamento valor siglaDepartamento a ser definido
	 */
	public void setSiglaDepartamento(String siglaDepartamento) {
		this.siglaDepartamento = siglaDepartamento;
	}
}
