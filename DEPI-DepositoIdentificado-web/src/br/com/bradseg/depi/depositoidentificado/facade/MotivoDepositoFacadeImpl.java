package br.com.bradseg.depi.depositoidentificado.facade;

import static br.com.bradseg.depi.depositoidentificado.util.BaseUtil.concatenarComHifen;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.Geral;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Implementação de MotivoDepositoFacade
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class MotivoDepositoFacadeImpl implements MotivoDepositoFacade {
	
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(MotivoDepositoFacadeImpl.class);

	@Autowired
	private MotivoDepositoDAO motivoDepositoDAO;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade#obterPorChave(br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO)
	 */
	@Override
	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo) {
		return motivoDepositoDAO.obterPorChave(vo); 
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade#alterar(br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO)
	 */
	@Override
	public void alterar(MotivoDepositoVO vo) {
		
    	LOGGER.error("Inicio - alterar(MotivoDepositoVO vo)"); 
		
        validaOperacao(vo);
        validaChave(vo);

        motivoDepositoDAO.obterPorChave(vo);
		motivoDepositoDAO.alterar(vo);
		
    	LOGGER.error("Fim - alterar(MotivoDepositoVO vo)"); 
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade#excluir(br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO)
	 */
	@Override
	public void excluir(MotivoDepositoVO vo) {

    	LOGGER.error("Inicio - excluir(MotivoDepositoVO vo)"); 
		
		motivoDepositoDAO.excluir(vo);
		
    	LOGGER.error("Fim - excluir(MotivoDepositoVO vo)"); 
						
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.MotivoDepositoFacade#excluirLista(java.util.List)
	 */
	@Override
	public void excluirLista(List<MotivoDepositoVO> vo) {

		motivoDepositoDAO.excluirLista(vo);
						
	}

	@Override
	public boolean isReferenciado(MotivoDepositoVO vo) {

		return motivoDepositoDAO.isReferenciado(vo);
		
	}

	@Override
	public void inserir(MotivoDepositoVO vo) {
		
		validaOperacao(vo);

		motivoDepositoDAO.inserir(vo);
		
	}

	@Override
	public List<MotivoDepositoVO> obterPorFiltroMotivoDepositvo(FiltroUtil filtro) {
		
    	LOGGER.error("Inicio - obterPorFiltroMotivoDepositvo(FiltroUtil filtro)"); 
		
		return motivoDepositoDAO.obterPorFiltro(filtro);
		
	}

	@Override
	public List<MotivoDepositoVO> obterTodosMotivoDepositvo() {
		
    	LOGGER.error("Inicio - obterTodosMotivoDepositvo()"); 
		
		return motivoDepositoDAO.obterTodos();
		
	}

	@Override
	public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(int codigoCia,int codigoDep, Double codigoUsuario, Tabelas e) {
		
		LOGGER.error("Inicio - obterComRestricaoDeGrupoAcesso(int codigoCia,int codigoDep, Double codigoUsuario, Tabelas e)"); 

		return motivoDepositoDAO.obterComRestricaoDeGrupoAcesso(codigoCia, codigoDep, codigoUsuario, e);
		
	}
	
    /**
     * método que valida as informações do vo
     * @param vo - motivo depósito que será validado
     * @throws IntegrationException - trata erros
     */
    private void validaOperacao(MotivoDepositoVO vo) throws IntegrationException {
        if (vo.getDescricaoBasica() == null || vo.getDescricaoBasica().equals(ConstantesDEPI.VAZIO)) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Descrição Básica"));
        } else if (vo.getDescricaoBasica().length() > 20) {
            throw new IntegrationException(concatenarComHifen(Geral.ERRO_CAMPO_EXCESSO, "Descrição Básica", "20"));
        }
        if (vo.getDescricaoDetalhada() == null || vo.getDescricaoDetalhada().equals(ConstantesDEPI.VAZIO)) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO,  "Descrição Detalhada"));
        } else if (vo.getDescricaoDetalhada().length() > 200) {
            throw new IntegrationException(concatenarComHifen(Geral.ERRO_CAMPO_EXCESSO, "Descrição Detalhada", "200"));
        }
        validaResponsavel(vo);
    }

    /**
     * método que valida o responsável do vo
     * @param vo - motivo depósito que será validado
     * @throws IntegrationException - trata erros
     */
    private void validaResponsavel(MotivoDepositoVO vo) throws IntegrationException {
    	
    	LOGGER.error("Inicio - validaResponsavel(MotivoDepositoVO vo)"); 
    	
        if (BaseUtil.isNZB(vo.getCodigoResponsavelUltimaAtualizacao())) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Código do Responsável"));
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() > ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO) {
			throw new IntegrationException(concatenarComHifen(
					Geral.ERRO_CAMPO_EXCESSO, "Código do Responsável",
					String.valueOf(ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO)));
        }
    }

    /**
     * método que válida a chave primária
     * @param vo - objeto que será validado
     * @throws IntegrationException - trata erros
     */
    private void validaChave(MotivoDepositoVO vo) throws IntegrationException {
    	
    	LOGGER.error("Inicio - validaResponsavel(MotivoDepositoVO vo)"); 
    	
        if (vo.getCodigoMotivoDeposito() == 0) {
			throw new DEPIIntegrationException(
					ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO,
					"Código de Motivo de Depósito");
        }
    }

}
