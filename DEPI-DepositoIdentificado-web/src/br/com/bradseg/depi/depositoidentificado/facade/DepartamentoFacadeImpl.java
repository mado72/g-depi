package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;


/**
 * Bean implementation class for Enterprise Bean: DepartamentoFacade
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class DepartamentoFacadeImpl implements DepartamentoFacade{

    private static final String CODIGO_RESPONSAVEL = "C�digo do Respons�vel";
    
    protected static final Logger LOGGER = Logger.getLogger(DepartamentoFacadeImpl.class);
    
	@Autowired
	private DepartamentoDAO departamentoDAO;

    /**
     * Alterar Departamento
     * @param vo VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
	@Override
    public void alterar(DepartamentoVO vo) throws IntegrationException {
       
    	validarAlterar(vo);
    	
    	departamentoDAO.alterar(vo);
    	
    }

    /**
     * Validar o VO de altera��o
     * @param vo VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    private void validarAlterar(DepartamentoVO vo) throws IntegrationException {
        
    	validarInserir(vo);
    	
    }

    /**
     * Exclui Departamento
     * @param vo VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    @Override
    public void excluir(DepartamentoVO vo) throws IntegrationException {

    	departamentoDAO.excluir(vo);
    
    }

    /**
     * Excluir Departamentos
     * @param vos VO's de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    @Override
    public void excluir(List<DepartamentoVO> vos) throws IntegrationException {
        StringBuilder sb = new StringBuilder();

        for (DepartamentoVO vo : vos) {
            validarExcluir(vo);
            
            if (departamentoDAO.isReferenciado(vo)) {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                sb.append(" Departamento: ").append(departamentoDAO.obterPorChave(vo).getSiglaDepartamento());
            } else {
            	departamentoDAO.excluir(vo);
            }
        }
        if (sb.length() > 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA + " - " +  sb.toString() + " - " +  "Associa��o Departamentos x Companhia");
        }
    }

    /**
     * Validar o VO de exclus�o
     * @param vo VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    private void validarExcluir(DepartamentoVO vo) throws IntegrationException {
        if (BaseUtil.isNZB(vo.getCodigoResponsavelUltimaAtualizacao())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + CODIGO_RESPONSAVEL);
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao() > 9999999) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO + " - " + CODIGO_RESPONSAVEL + " - " + "9999999");
        }
    }

    /**
     * Inserir Departamento
     * @param vo VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    @Override
    public void inserir(DepartamentoVO vo) throws IntegrationException {
        validarInserir(vo);

        departamentoDAO.inserir(vo);

    }

    /**
     * Validar o VO de inser��o
     * @param vo VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    private void validarInserir(DepartamentoVO vo) throws IntegrationException {
    	
        if (BaseUtil.isNZB(vo.getSiglaDepartamento())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " +  "Sigla do Departamento");
        }
        if (vo.getSiglaDepartamento().length() > ConstantesDEPI.MAX_SIZE_SIGLA_DEPARTAMENTO) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO + " - " +  "Sigla do Departamento" + " - " +  String.valueOf(ConstantesDEPI.MAX_SIZE_SIGLA_DEPARTAMENTO));
        }
        if (BaseUtil.isNZB(vo.getNomeDepartamento())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " +  "Nome do Departamento");
        }
        if (vo.getNomeDepartamento().length() > ConstantesDEPI.MAX_SIZE_DEFAULT_NOME) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO + " - " +  "Nome do Departamento" + " - " +  String.valueOf(ConstantesDEPI.MAX_SIZE_DEFAULT_NOME));
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao() == 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " +  CODIGO_RESPONSAVEL);
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao() > ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO + " - " +  CODIGO_RESPONSAVEL + " - " + String.valueOf(ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO));
        }
    }

    /**
     * Obter Departamento
     * @param vo VO de Departamento
     * @return VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    @Override
    public DepartamentoVO obterPorChave(DepartamentoVO vo) throws IntegrationException {
        return departamentoDAO.obterPorChave(vo);
    }

    /**
     * Obter Departamentos
     * @param filtro VO de filtro de Departamento
     * @return VO de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    public List<DepartamentoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
        return departamentoDAO.obterPorFiltro(filtro);
    }

    /**
     * Obter Departamentos
     * @return Lista de VO's de Departamento
     * @throws IntegrationException Exce��o de aplica��o
     */
    @Override
    public List<DepartamentoVO> obterTodos() throws IntegrationException {
        return departamentoDAO.obterTodos();
    }

    /**
     * Obter Departamentos por Cia e Usu�rio.
     * @param codigoCia - int.
     * @param codigoUsuario - BigDecimal.
     * @param e Tabelas.
     * @return List<DepartamentoVO>.
     * @throws IntegrationException - IntegrationException.
     */
    @Override
    public List<DepartamentoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, Double codigoUsuario, Tabelas e) throws IntegrationException {
        
    	return departamentoDAO.obterComRestricaoDeGrupoAcesso(codigoCia,codigoUsuario, e);
    	
    }

    /**
     * m�todo que obtem lista de departamentos por companhia
     * @param vo - CompanhiaSeguradoraVO
     * @return List de VO's de departamentos
     * @throws IntegrationException trata exce��o
     */
    @Override
    public List<DepartamentoVO> obterPorCompanhiaSeguradora(CompanhiaSeguradoraVO vo) throws IntegrationException {
        return departamentoDAO.obterPorCompanhiaSeguradora(vo);

    }

}
