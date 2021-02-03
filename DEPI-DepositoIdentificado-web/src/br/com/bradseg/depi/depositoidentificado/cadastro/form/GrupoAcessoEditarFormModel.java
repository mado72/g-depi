package br.com.bradseg.depi.depositoidentificado.cadastro.form;

import java.util.ArrayList;
import java.util.List;

import br.com.bradseg.depi.depositoidentificado.funcao.action.CrudForm;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;

/**
 * Representa o modelo do formulário para Grupo de Acesso 
 * @author Marcelo Damasceno
 */
public class GrupoAcessoEditarFormModel extends CrudForm {

	private static final long serialVersionUID = 957768938376772158L;

	private String codigoGrupoAcesso;
	
	private String codDepartamento;
	
	private String descDepartamento;
	
	private int codCompanhia;
	
	private List<String> codFuncionarios = new ArrayList<>();
	
	private List<CompanhiaSeguradoraVO> cias = new ArrayList<>();
	
	private List<DepartamentoVO> deptos = new ArrayList<>();
	
	@Override
	public boolean isDetalhar() {
		return getEstado() == EstadoCrud.EXIBIR;
	}

	@Override
	public void limparDados() {
		codigoGrupoAcesso = "";
		codDepartamento = "";
		descDepartamento = "";
		if (cias.isEmpty()) {
			codCompanhia = -1;
		}
		else {
			codCompanhia = cias.get(0).getCodigoCompanhia();
		}
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

	public int getCodCompanhia() {
		return codCompanhia;
	}

	public void setCodCompanhia(int codCompanhia) {
		this.codCompanhia = codCompanhia;
	}

	public List<String> getCodFuncionarios() {
		return codFuncionarios;
	}

	public void setCodFuncionarios(List<String> codFuncionarios) {
		this.codFuncionarios = codFuncionarios;
	}

	/**
	 * Retorna cias
	 * @return o cias
	 */
	public List<CompanhiaSeguradoraVO> getCias() {
		return cias;
	}

	/**
	 * Define cias
	 * @param cias valor cias a ser definido
	 */
	public void setCias(List<CompanhiaSeguradoraVO> cias) {
		this.cias = cias;
	}

	/**
	 * Retorna dptos
	 * @return o dptos
	 */
	public List<DepartamentoVO> getDeptos() {
		return deptos;
	}

	/**
	 * Define dptos
	 * @param dptos valor dptos a ser definido
	 */
	public void setDeptos(List<DepartamentoVO> dptos) {
		this.deptos = dptos;
	}
	
}
