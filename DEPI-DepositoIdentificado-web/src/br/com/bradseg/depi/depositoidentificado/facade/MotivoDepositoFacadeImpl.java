package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.enums.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * XXXXXXX
 * @Service
 * */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class MotivoDepositoFacadeImpl implements MotivoDepositoFacade {

	@Autowired
	private MotivoDepositoDAO motivoDepositoDAO;
	
	@Override
	public MotivoDepositoVO obterPorChave(MotivoDepositoVO vo) {

		return motivoDepositoDAO.obterPorChave(vo); 
		
	}

	@Override
	public void alterar(MotivoDepositoVO vo) {
		
        validaOperacao(vo);

        validaChave(vo);

        motivoDepositoDAO.obterPorChave(vo);

		
		motivoDepositoDAO.alterar(vo);
		
	}

	@Override
	public void excluir(MotivoDepositoVO vo) {

		motivoDepositoDAO.excluir(vo);
						
	}
	
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
		
		return motivoDepositoDAO.obterPorFiltro(filtro);
		
	}

	@Override
	public List<MotivoDepositoVO> obterTodosMotivoDepositvo() {
		
		return motivoDepositoDAO.obterTodos();
		
	}

	@Override
	public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(int codigoCia,  int codigoDep, Double codigoUsuario, Tabelas e) {

		return motivoDepositoDAO.obterComRestricaoDeGrupoAcesso(codigoCia, codigoDep, codigoUsuario, e);
		
	}
	
    /**
     * m�todo que valida as informa��es do vo
     * @param vo - motivo dep�sito que ser� validado
     * @throws IntegrationException - trata erros
     */
    private void validaOperacao(MotivoDepositoVO vo) throws IntegrationException {
        if (vo.getDescricaoBasica() == null || vo.getDescricaoBasica().equals(ConstantesDEPI.VAZIO)) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " +  "Descri��o B�sica");
        } else if (vo.getDescricaoBasica().length() > 20) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO  + " - " +  "Descri��o B�sica" + " - " +  "20");
        }
        if (vo.getDescricaoDetalhada() == null || vo.getDescricaoDetalhada().equals(ConstantesDEPI.VAZIO)) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO  + " - " +  "Descri��o Detalhada");
        } else if (vo.getDescricaoDetalhada().length() > 200) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO  + " - " +  "Descri��o Detalhada" + " - " +  "200");
        }
        validaResponsavel(vo);
    }

    /**
     * m�todo que valida o respons�vel do vo
     * @param vo - motivo dep�sito que ser� validado
     * @throws IntegrationException - trata erros
     */
    private void validaResponsavel(MotivoDepositoVO vo) throws IntegrationException {
        if (BaseUtil.isNZB(vo.getCodigoResponsavelUltimaAtualizacao())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO  + " - " + "C�digo do Respons�vel");
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() > ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_EXCESSO + " - " + "C�digo do Respons�vel" + " - " + String.valueOf(ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO));
        }
    }

    /**
     * m�todo que v�lida a chave prim�ria
     * @param vo - objeto que ser� validado
     * @throws IntegrationException - trata erros
     */
    private void validaChave(MotivoDepositoVO vo) throws IntegrationException {
        if (vo.getCodigoMotivoDeposito() == 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO  + " - " + "C�digo de Motivo de Dep�sito");
        }
    }

}
