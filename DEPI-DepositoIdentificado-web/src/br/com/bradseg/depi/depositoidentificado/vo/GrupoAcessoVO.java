package br.com.bradseg.depi.depositoidentificado.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Anotada de Mapeamento com a tabela GRP_DEPTO_DEP.
 * @author Globalitu
 */
//@Table(schema = ConstantesDEPI.SCHEMA_BANCO, table = "GRP_DEPTO_DEP")
public class GrupoAcessoVO implements Serializable{

	private static final long serialVersionUID = -7192188807301270330L;

	/**
	 * Construtor.
     * @param codigoGrupoAcesso - int.
     */
	public GrupoAcessoVO(int codigoGrupoAcesso) {
		super();
		this.codigoGrupoAcesso = codigoGrupoAcesso;
	}

	/**
     * construtor
     */
	public GrupoAcessoVO() {
		super();
	}

	/**
     * Código seq�encial do Grupo Acesso.
     */
//	@TableField(name = "CGRP_DEPTO_DEP")
	private int codigoGrupoAcesso;

	/**
     * codigo do departamento - DepartamentoVO obtido atraves da classe DepartamentoConverter
     */
//	@TableField(name = "CDEPTO_DEP_IDTFD")
	private DepartamentoVO depto = new DepartamentoVO();

	/**
     * codigo da Companhia - CompanhiaSeguradoraVO obtido atraves da classe CompanhiaSeguradoraPersistenceConverter
     */
//	@TableField(name = "CINTRN_CIA_SEGDR")
	private CompanhiaSeguradoraVO cia = new CompanhiaSeguradoraVO();

	private List<UsuarioVO> usuarios = new ArrayList<UsuarioVO>();
	
    /**
     * Codigo Responsavel Ultima Atualizacao.
     */
    private Integer codigoResponsavelUltimaAtualizacao;
    
	/**
     * Retorna o valor do atributo usuarios.
     * @return o valor do atributo usuarios
     */
	public List<UsuarioVO> getUsuarios() {
		return usuarios;
	}

	/**
     * Especifica o valor do atributo usuarios.
     * @param usuarios - List<UsuarioVO> do usuarios a ser configurado.
     */
	public void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}

	/**
     * codigoGrupoAcesso
     * @return codigoGrupoAcesso
     */
	public int getCodigoGrupoAcesso() {
		return codigoGrupoAcesso;
	}

	/**
     * codigoGrupoAcesso
     * @param codigoGrupoAcesso codigoGrupoAcesso
     */
	public void setCodigoGrupoAcesso(int codigoGrupoAcesso) {
		this.codigoGrupoAcesso = codigoGrupoAcesso;
	}

	/**
     * getNomeGrupoAcesso
     * @return nomeGrupoAcesso
     */
	public String getNomeGrupoAcesso() {
		return String.valueOf(this.getCia().getCodigoCompanhia()).concat(this.getDepto().getSiglaDepartamento()).concat(
		    String.valueOf(this.getCodigoGrupoAcesso()));
	}

	/**
     * getCia
     * @return deptoCia
     */

	public CompanhiaSeguradoraVO getCia() {
		return cia;
	}

	/**
     * getDepto
     * @return depto
     */
	public DepartamentoVO getDepto() {
		return depto;
	}

	/**
     * Método que retornar� o codigo do grupo usu�rio formatado.
     * @return String.
     */
	public String getCodigoFormatado() {
		return new StringBuilder().append(this.getDepto().getCodigoDepartamento())
		    .append(this.getCia().getCodigoCompanhia()).append(this.getCodigoGrupoAcesso()).toString();
	}

	/**
     * Especifica o cia.
     * @param cia CompanhiaSeguradoraVO do cia a ser setado
     */
	public void setCia(CompanhiaSeguradoraVO cia) {
		this.cia = cia;
	}

	/**
     * Especifica o depto.
     * @param depto DepartamentoVO do depto a ser setado
     */
	public void setDepto(DepartamentoVO depto) {
		this.depto = depto;
	}

    /**
     * Retorna o CodigoResponsavelUltimaAtualizacao.
     * @return O atributo CodigoResponsavelUltimaAtualizacao
     */
	public Integer getCodigoResponsavelUltimaAtualizacao() {
		return codigoResponsavelUltimaAtualizacao;
	}

    /**
     * Especifica o CodigoResponsavelUltimaAtualizacao.
     * @param codigoResponsavelUltimaAtualizacao Integer do codigoDepartamento a ser setado
     */
	public void setCodigoResponsavelUltimaAtualizacao(Integer codigoResponsavelUltimaAtualizacao) {
		this.codigoResponsavelUltimaAtualizacao = codigoResponsavelUltimaAtualizacao;
	}
	
}
