package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.AssociarMotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.AssociarMotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 *  implementation class for Enterprise Bean: AssociarMotivoDepositoSessionFacade
 */
/**
 * XXXXXXX
 * @Service
 * */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AssociarMotivoDepositoFacadeImpl implements AssociarMotivoDepositoFacade {

	private static final String STR1 = "Identificação Depósitos";
    protected static final Logger LOGGER = Logger.getLogger(AssociarMotivoDepositoFacadeImpl.class);
    
	/**
	 * Associação Motivo Deposito DAO
	 */
	@Autowired
	private AssociarMotivoDepositoDAO amdDAO;

    /**
     * Excluir AssociarMotivoDepositos
     * @param vos VO's de AssociarMotivoDeposito
     * @throws IntegrationException Exceção de aplicação
     */
    public void excluir(List<AssociarMotivoDepositoVO> vos) throws IntegrationException {
    	
        if (vos == null || vos.isEmpty()) {
            throw new IntegrationException("Lista inválida!");
        }

        StringBuilder codsAssocMotivos = new StringBuilder();

        for (AssociarMotivoDepositoVO vo : vos) {
            if (amdDAO.isReferenciado(vo)) {
                if (codsAssocMotivos.length() > 0) {
                    codsAssocMotivos.append("; ");
                }
                codsAssocMotivos.append(vo.toString());
            } else {
            	amdDAO.excluir(vo);
            }
        }
        
        if (codsAssocMotivos.length() > 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO + " - " + codsAssocMotivos.toString() + " - " + STR1);
        }
    }

    /**
     * Inserir AssociarMotivoDeposito
     * @param vo - AssociarMotivoDepositoVO.
     * @throws IntegrationException - Integração.
     */
    @Override
	public void inserir(AssociarMotivoDepositoVO vo) throws IntegrationException {

    	amdDAO.inserir(vo);
    	
    }

    /**
     * Método de obter por filtro
     * @param codUsuario - BigDecimal.
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @return List<AssociarMotivoDepositoVO>
     */
	@Override
	public List<AssociarMotivoDepositoVO> obterPorFiltro(int codUsuario,
			FiltroUtil filtro) {

    	return amdDAO.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codUsuario);
    	
    }

    /**
     * método que retorna o motivo depósito pelo código
     * @param vo - conta conrrente com o código
     * @return - retona o conta conrrente referente ao código passado
     * @throws IntegrationException - trata erros de negócio
     */
    @Override
	public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo) throws IntegrationException {
    	
        return amdDAO.obterPorChave(vo);
        
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#excluirLista(java.util.List)
	 */
	@Override
	public void excluirLista(List<AssociarMotivoDepositoVO> voList) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterCompanhias(int)
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterDepartamentos(int, br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public List<DepartamentoVO> obterDepartamentos(int codUsuario,
			CompanhiaSeguradoraVO ciaVO) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterMotivosDeposito(int, br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
	 */
	@Override
	public List<MotivoDepositoVO> obterMotivosDeposito(int codUsuario,
			DepartamentoVO depto) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterBancos(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.AssociarMotivoDepositoFacade#obterBanco(br.com.bradseg.depi.depositoidentificado.vo.BancoVO)
	 */
	@Override
	public BancoVO obterBanco(BancoVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
