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
    
	@Autowired
	private AssociarMotivoDepositoDAO associarMotivoDeposito;

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
            if (associarMotivoDeposito.isReferenciado(vo)) {
                if (codsAssocMotivos.length() > 0) {
                    codsAssocMotivos.append("; ");
                }
                codsAssocMotivos.append(" Associação de Motivo: [Cia: ").append(vo.getCia().getCodigoCompanhia());
                codsAssocMotivos.append("; Departamento: ").append(vo.getDepartamento().getCodigoDepartamento());
                codsAssocMotivos.append("; Motivo: ").append(vo.getMotivoDeposito().getCodigoMotivoDeposito());
                codsAssocMotivos.append(" Banco: ").append(vo.getBanco().getCdBancoExterno());
                codsAssocMotivos.append(", Agência: ").append(vo.getCodigoAgencia());
                codsAssocMotivos.append(", Conta Corrente: ").append(vo.getContaCorrente()).append("]");
            } else {
            	associarMotivoDeposito.excluir(vo);
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
    public void inserir(AssociarMotivoDepositoVO vo) throws IntegrationException {

    	associarMotivoDeposito.inserir(vo);
    	
    }

    /**
     * Método de obter por filtro
     * @param filtro parâmetro depósito com o código do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @throws IntegrationException - trata erro de negócio
     * @return List<AssociarMotivoDepositoVO>
     */
    public List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, int codigoUsuario) throws IntegrationException {

    	return associarMotivoDeposito.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codigoUsuario);
    	
    }

    /**
     * método que retorna o motivo depósito pelo código
     * @param vo - conta conrrente com o código
     * @return - retona o conta conrrente referente ao código passado
     * @throws IntegrationException - trata erros de negócio
     */
    public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo) throws IntegrationException {
    	
        return associarMotivoDeposito.obterPorChave(vo);
        
    }

    /**
     * Obter AssociarMotivoDepositos
     * @param filtro VO de filtro de AssociarMotivoDeposito
     * @return VO de AssociarMotivoDeposito
     * @throws IntegrationException Exceção de aplicação
     */
    public List<AssociarMotivoDepositoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {

    	return associarMotivoDeposito.obterPorFiltro(filtro);
    	
    }

}
