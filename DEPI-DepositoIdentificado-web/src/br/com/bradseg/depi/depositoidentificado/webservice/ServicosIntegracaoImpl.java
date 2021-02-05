package br.com.bradseg.depi.depositoidentificado.webservice;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bradseg.bsad.filtrologin.filters.LoginUtils;
import br.com.bradseg.bsad.filtrologin.vo.LoginVo;
import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.facade.DepartamentoFacade;
import br.com.bradseg.depi.depositoidentificado.facade.GrupoAcessoFacade;
import br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Autowired
	private GrupoAcessoFacade grupoAcessoFacade;
	
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
		// TODO Não implementado
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
    public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(int codigoCia, int codigoDep, int codigoUsuario, Tabelas e) throws IntegrationException {

    	return motivoDepositoFacade.obterComRestricaoDeGrupoAcesso(codigoCia, codigoDep, codigoUsuario, e);
    
    }

	@GET
	@Path("/cia/departamentos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DepartamentoVO> obterCiaDepartamentos(int codigoCia, @Context ServletRequest request) {
		List<DepartamentoVO> lista = grupoAcessoFacade.obterDepartamentos(new CompanhiaSeguradoraVO(codigoCia));
		return lista;		
	}
	
	protected LoginVo getUsuarioLogado(ServletRequest request) {

		// FIXME retirar este log da publicação final
		LOGGER.error("Tentando recuperar o usuário logado usando LoginUtils.getLoginObject(this.request)");
		LoginVo loginVO = LoginUtils.getLoginObject(request);
		LOGGER.error("Usuário logado {}", loginVO);
		
        if (BaseUtil.isNZB(loginVO) || BaseUtil.isNZB(loginVO.getId())) {
        	LOGGER.error("Não encontrou usuário logado");
            throw new IntegrationException(BaseUtil.getTexto(ConstantesDEPI.Geral.ERRO_USUARIO_LOGADO));
        }
        LOGGER.error("Sucesso: usuário logado!!! id: {}, nome: {}", loginVO.getId(), loginVO.getNome());
		
		return loginVO;

	}

	


}
