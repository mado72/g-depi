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

	private static final String STR1 = "Identifica��o Dep�sitos";
    protected static final Logger LOGGER = Logger.getLogger(AssociarMotivoDepositoFacadeImpl.class);
    
	@Autowired
	private AssociarMotivoDepositoDAO associarMotivoDeposito;

    /**
     * Excluir AssociarMotivoDepositos
     * @param vos VO's de AssociarMotivoDeposito
     * @throws IntegrationException Exce��o de aplica��o
     */
    public void excluir(List<AssociarMotivoDepositoVO> vos) throws IntegrationException {
    	
        if (vos == null || vos.isEmpty()) {
            throw new IntegrationException("Lista inv�lida!");
        }

        StringBuilder codsAssocMotivos = new StringBuilder();

        for (AssociarMotivoDepositoVO vo : vos) {
            if (associarMotivoDeposito.isReferenciado(vo)) {
                if (codsAssocMotivos.length() > 0) {
                    codsAssocMotivos.append("; ");
                }
                codsAssocMotivos.append(" Associa��o de Motivo: [Cia: ").append(vo.getCia().getCodigoCompanhia());
                codsAssocMotivos.append("; Departamento: ").append(vo.getDepartamento().getCodigoDepartamento());
                codsAssocMotivos.append("; Motivo: ").append(vo.getMotivoDeposito().getCodigoMotivoDeposito());
                codsAssocMotivos.append(" Banco: ").append(vo.getBanco().getCdBancoExterno());
                codsAssocMotivos.append(", Ag�ncia: ").append(vo.getCodigoAgencia());
                codsAssocMotivos.append(", Conta Corrente: ").append(vo.getContaCorrente()).append("]");
            } else {
            	associarMotivoDeposito.excluir(vo);
            }
        }
        
        if (codsAssocMotivos.length() > 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA + " - " + codsAssocMotivos.toString() + " - " + STR1);
        }
    }

    /**
     * Inserir AssociarMotivoDeposito
     * @param vo - AssociarMotivoDepositoVO.
     * @throws IntegrationException - Integra��o.
     */
    public void inserir(AssociarMotivoDepositoVO vo) throws IntegrationException {

    	associarMotivoDeposito.inserir(vo);
    	
    }

    /**
     * M�todo de obter por filtro
     * @param filtro par�metro dep�sito com o c�digo do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @throws IntegrationException - trata erro de neg�cio
     * @return List<AssociarMotivoDepositoVO>
     */
    public List<AssociarMotivoDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario) throws IntegrationException {

    	return associarMotivoDeposito.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codigoUsuario);
    	
    }

    /**
     * m�todo que retorna o motivo dep�sito pelo c�digo
     * @param vo - conta conrrente com o c�digo
     * @return - retona o conta conrrente referente ao c�digo passado
     * @throws IntegrationException - trata erros de nog�cio
     */
    public AssociarMotivoDepositoVO obterPorChave(AssociarMotivoDepositoVO vo) throws IntegrationException {
    	
        return associarMotivoDeposito.obterPorChave(vo);
        
    }

    /**
     * Obter AssociarMotivoDepositos
     * @param filtro VO de filtro de AssociarMotivoDeposito
     * @return VO de AssociarMotivoDeposito
     * @throws IntegrationException Exce��o de aplica��o
     */
    public List<AssociarMotivoDepositoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {

    	return associarMotivoDeposito.obterPorFiltro(filtro);
    	
    }

}
