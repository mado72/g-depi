package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;

/**
 * Representa o modelo do formulário para Grupo de Acesso 
 * @author Marcelo Damasceno
 */
public class GrupoAcessoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	private String codigoGrupoAcesso;
	
	private String codDepartamento;
	
	private String descDepartamento;
	
	private String codCompanhia;
	
	private String descCompanhia;
	
	private List<String> codFuncionarios = new ArrayList<>();
	
	@Override
	public boolean isDetalhar() {
		return getEstado() == EstadoCrud.EXIBIR;
	}

	@Override
	public void limparDados() {
		codigoGrupoAcesso = "";
		codDepartamento = "";
		descDepartamento = "";
		codCompanhia = "";
		descCompanhia = "";
		codFuncionarios.clear();
	}

	public String getCodigoGrupoAcesso() {
		return codigoGrupoAcesso;
	}

	public void setCodigoGrupoAcesso(String codigoGrupoAcesso) {
		this.codigoGrupoAcesso = codigoGrupoAcesso;
	}

	public String getCodDepartamento() {
		return codDepartamento;
	}

	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}

	public String getDescDepartamento() {
		return descDepartamento;
	}

	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}

	public String getCodCompanhia() {
		return codCompanhia;
	}

	public void setCodCompanhia(String codCompanhia) {
		this.codCompanhia = codCompanhia;
	}

	public String getDescCompanhia() {
		return descCompanhia;
	}

	public void setDescCompanhia(String descCompanhia) {
		this.descCompanhia = descCompanhia;
	}

	public List<String> getCodFuncionarios() {
		return codFuncionarios;
	}

	public void setCodFuncionarios(List<String> codFuncionarios) {
		this.codFuncionarios = codFuncionarios;
	}
	
}
