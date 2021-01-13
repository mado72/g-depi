package br.com.bradseg.depi.depositoidentificado.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A(O) Class ConsultasServiceImpl.
 *
 * @author Globality
 */
@Service("servicos")
public class ServicosIntegracaoImpl {
	
	
	/** A(O) detalhe acompanhamento evento facade. */
	@Autowired(required=true)
	private MotivoDepositoFacade motivoDepositoFacade;
	
	@Autowired(required=true)
	private DepartamentoFacade departamentoFacade;
	
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServicosIntegracaoImpl.class);
	
    /**
     * método que altera o motivo depósito
     * @param vo - motivo depósito que será alterado
     * @throws IntegrationException - trata erros de nogócio
     */
	@POST
	@Path("/motivodeposito/alterar")
	@Consumes({ MediaType.APPLICATION_JSON} )
    public void alterarMotivoDeposito(MotivoDepositoVO vo) throws IntegrationException {
        try {
        	motivoDepositoFacade.alterar(vo);
        } catch (IntegrationException e) {
        	LOGGER.error("alterarMotivoDeposito(MotivoDepositoVO vo) : " + e.getMessage());
            throw new IntegrationException(e);
        }
    }

    /**
     * método que exclui o motivo depósito
     * @param vo - motivo depósito que será excluido
     * @throws IntegrationException - trata erros de nogócio
     */
	@POST
	@Path("/motivodeposito/excluir")
	@Consumes({ MediaType.APPLICATION_JSON} )	
    public void excluirMotivoDeposito(MotivoDepositoVO vo) throws IntegrationException {
    }

    /**
     * método que exclui os motivos depósitos
     * @param listvo - motivos depósitos que serão excluidos
     * @throws IntegrationException - trata erros de negócio
     */
	@POST
	@Path("/motivodeposito/excluirLista")
	@Consumes({ MediaType.APPLICATION_JSON} )		
    public void excluir(List<MotivoDepositoVO> listvo) throws IntegrationException {
       
        try {
        	motivoDepositoFacade.excluirLista(listvo);
        } catch (IntegrationException e) {
        	LOGGER.error("excluirMotivoDeposito(MotivoDepositoVO vo) : " + e.getMessage());
            throw new IntegrationException(e);
        }
   }

    /**
     * método que inclui o motivo depósito
     * @param vo - motivo depósito que será incuido
     * @throws IntegrationException - trata erros de nogócio
     */
	@POST
	@Path("/motivodeposito/inserir")
	@Consumes({ MediaType.APPLICATION_JSON} )		
    public void inserir(MotivoDepositoVO vo) throws IntegrationException {
       
        try {
        	motivoDepositoFacade.inserir(vo);
        } catch (IntegrationException e) {
           	LOGGER.error("inserirMotivoDeposito(MotivoDepositoVO vo) : " + e.getMessage());
            throw new IntegrationException(e);
 
        }
     }

    /**
     * método que retorna o motivo depósito pelo código
     * @param vo - motivo depósito com o código
     * @return - retona o motivo depósito referente ao código passado
     * @throws IntegrationException - trata erros de nogócio
     */
	@POST
	@Path("/motivodeposito/obterPorChave")
	@Consumes({ MediaType.APPLICATION_JSON} )	
	@Produces({ MediaType.APPLICATION_JSON} )
    public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo) throws IntegrationException {

		return motivoDepositoFacade.obterPorChave(vo);
		
    }

    /**
     * metódo que retorna os motivos depósito que possuem os dados passados no filtro
     * @param filtro - valores que serão utilizados como parâmetro
     * @return - motivos depósito que possuem os dados passados no filtro
     * @throws IntegrationException - trata erros de nogócio
     */
	@POST
	@Path("/motivodeposito/obterPorFiltro")
	@Consumes({ MediaType.APPLICATION_JSON} )	
	@Produces({ MediaType.APPLICATION_JSON} )
    public List<MotivoDepositoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
		
		return motivoDepositoFacade.obterPorFiltroMotivoDepositvo(filtro);

    }

    /**
     * metódo que retorna todos os motivos depósito
     * @return - todos os motivos depósito
     * @throws IntegrationException - trata erros de nogócio
     */
	@POST
	@Path("/motivodeposito/obterTodos")
	@Consumes({ MediaType.APPLICATION_JSON} )	
	@Produces({ MediaType.APPLICATION_JSON} )
    public List<MotivoDepositoVO> obterTodos() throws IntegrationException {

		return motivoDepositoFacade.obterTodosMotivoDepositvo();
		
    }
	
    /**
     * Obtém apenas os Motivos que estão associados a Parametros de Depósito.
     * @param codigoCia - int.
     * @param codigoDep - int.
     * @param codigoUsuario - String.
     * @param e - Tabelas.
     * @return List<MotivoDepositoVO>.
     * @throws IntegrationException - IntegrationException.
     * 
     */
	@POST
	@Path("/motivodeposito/obterComRestricaoDeGrupoAcesso")
	@Consumes({ MediaType.APPLICATION_JSON} )	
	@Produces({ MediaType.APPLICATION_JSON} )
    public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, int codigoDep, Double codigoUsuario, Tabelas e) throws IntegrationException {

    	return motivoDepositoFacade.obterComRestricaoDeGrupoAcesso(codigoCia, codigoDep, codigoUsuario, e);
    
    }


	


}
