package br.com.bradseg.depi.depositoidentificado.facade;

import static br.com.bradseg.depi.depositoidentificado.util.BaseUtil.concatenarComHifen;
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
	public List<MotivoDepositoVO> obterComRestricaoDeGrupoAcesso(int codigoCia,
			int codigoDep, Double codigoUsuario, Tabelas e) {

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
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_EXCESSO, "Descrição Básica", "20"));
        }
        if (vo.getDescricaoDetalhada() == null || vo.getDescricaoDetalhada().equals(ConstantesDEPI.VAZIO)) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO,  "Descrição Detalhada"));
        } else if (vo.getDescricaoDetalhada().length() > 200) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_EXCESSO, "Descrição Detalhada", "200"));
        }
        validaResponsavel(vo);
    }

    /**
     * método que valida o responsável do vo
     * @param vo - motivo depósito que será validado
     * @throws IntegrationException - trata erros
     */
    private void validaResponsavel(MotivoDepositoVO vo) throws IntegrationException {
        if (BaseUtil.isNZB(vo.getCodigoResponsavelUltimaAtualizacao())) {
            throw new IntegrationException(concatenarComHifen(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Código do Responsável"));
        }
        if (vo.getCodigoResponsavelUltimaAtualizacao().doubleValue() > ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO) {
			throw new IntegrationException(concatenarComHifen(
					ConstantesDEPI.ERRO_CAMPO_EXCESSO, "Código do Responsável",
					String.valueOf(ConstantesDEPI.MAX_SIZE_CODIGO_USUARIO)));
        }
    }

    /**
     * método que válida a chave primária
     * @param vo - objeto que será validado
     * @throws IntegrationException - trata erros
     */
    private void validaChave(MotivoDepositoVO vo) throws IntegrationException {
        if (vo.getCodigoMotivoDeposito() == 0) {
			throw new IntegrationException(concatenarComHifen(
					ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO,
					"Código de Motivo de Depósito"));
        }
    }

}
