/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.BusinessException;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.dao.BancoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ContaCorrenteDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.ContaCorrenteAutorizadaCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.IEntidadeCampo;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.TipoOperacao;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.CriterioConsultaVO;

/**
 * Implementação de {@link ContaCorrenteFacade}
 * @author Marcelo Damasceno
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ContaCorrenteFacadeImpl implements ContaCorrenteFacade {

	private static final String AGENCIA_STRING = "Ag\u0206ncia";
    private static final String BANCO_STRING = "Banco";
    
    @Autowired
    private CICSDepiDAO cicsDAO;

	@Autowired
	private CompanhiaSeguradoraDAO ciaDAO;
	
	@Autowired
	private ContaCorrenteDAO ccDAO;
	
	@Autowired
	private BancoDAO bancoDAO;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterCompanhia(int, br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public CompanhiaSeguradoraVO obterCompanhia(int codUsuario, CompanhiaSeguradoraVO cia) {
		return ciaDAO.obterComRestricaoDeGrupoAcesso(codUsuario, cia.getCodigoCompanhia());
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterCompanhias(int)
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
		List<CompanhiaSeguradoraVO> cias = ciaDAO.obterComRestricaoDeGrupoAcesso(codUsuario);
		
		cias = cicsDAO.obterCias(cias);
		return cias;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterBanco(br.com.bradseg.depi.depositoidentificado.vo.BancoVO)
	 */
	@Override
	public BancoVO obterBanco(BancoVO vo) {
		return cicsDAO.obterBanco(vo);
	}

	@Override
    public List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia) {
        List<BancoVO> bancosDb = bancoDAO.obterBancos(cia);
        
        List<BancoVO> bancos = cicsDAO.obterBancos(bancosDb);
        
		return bancos;
    }

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterPorChave(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public ContaCorrenteAutorizadaVO obterPorChave(ContaCorrenteAutorizadaVO vo) {
		
		List<CriterioConsultaVO<?>> criterios = new ArrayList<CriterioConsultaVO<?>>();
		criterios.add(new CriterioConsultaVO<IEntidadeCampo>(
				ContaCorrenteAutorizadaCampo.CodigoCia,
				TipoOperacao.IgualNumerico,
				String.valueOf(vo.getCia().getCodigoCompanhia()),
				"param1"));
		criterios.add(new CriterioConsultaVO<IEntidadeCampo>(
				ContaCorrenteAutorizadaCampo.CodigoBanco,
				TipoOperacao.IgualNumerico,
				String.valueOf(vo.getBanco().getCdBancoExterno()),
				"param2"));
		criterios.add(new CriterioConsultaVO<IEntidadeCampo>(
				ContaCorrenteAutorizadaCampo.CodigoAgencia,
				TipoOperacao.IgualNumerico,
				String.valueOf(vo.getCodigoAgencia()),
				"param3"));
		criterios.add(new CriterioConsultaVO<IEntidadeCampo>(
				ContaCorrenteAutorizadaCampo.CodigoContaCorrente,
				TipoOperacao.IgualNumerico,
				String.valueOf(vo.getContaCorrente()),
				"param4"));
		
		FiltroUtil filtro = new FiltroUtil();
		filtro.setCriterios(criterios);
		
		List<ContaCorrenteAutorizadaVO> lista = ccDAO.obterPorFiltro(filtro);
		
		if (lista.isEmpty()) {
			throw new DEPIBusinessException(ConstantesDEPI.ERRO_REGISTRO_INEXISTENTE);
		}
		
		ContaCorrenteAutorizadaVO retorno = lista.get(0);
		CompanhiaSeguradoraVO cia = cicsDAO.obterCiaPorCodigo(retorno.getCia()
				.getCodigoCompanhia());
		retorno.setCia(cia);
		
		BancoVO banco = cicsDAO.obterBanco(retorno.getBanco());
		retorno.setBanco(banco);
		
		String descricaoAgencia = obterAgencia(retorno);
		retorno.setDescricaoAgencia(descricaoAgencia);
		
		retorno.setCodigoInternoCC(obterContaInterna(retorno));
		
		return retorno;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterAgencia(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public String obterAgencia(ContaCorrenteAutorizadaVO ccVO) {
		String descricaoAgencia = cicsDAO.obterAgencia(ccVO.getBanco()
				.getCdBancoExterno(), ccVO.getCodigoAgencia());
		return descricaoAgencia;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterContaInterna(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public Integer obterContaInterna(ContaCorrenteAutorizadaVO ccVO) {
		ContaCorrenteAutorizadaVO vo = cicsDAO.obterContaCorrente(ccVO);
		return vo.getCodigoInternoCC();
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#obterPorFiltro(int, br.com.bradseg.depi.depositoidentificado.util.FiltroUtil)
	 */
	@Override
	public List<ContaCorrenteAutorizadaVO> obterPorFiltro(int codUsuario,
			FiltroUtil filtro) {
		return ccDAO.obterPorFiltroComRestricao(codUsuario, filtro);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#inserir(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void inserir(ContaCorrenteAutorizadaVO vo) {
		
		validar(vo, true);
		ccDAO.incluir(vo);
		
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#alterar(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public void alterar(ContaCorrenteAutorizadaVO vo) {

		validar(vo, false);
		ccDAO.alterar(vo);
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.facade.ContaCorrenteFacade#excluirLista(java.util.List)
	 */
	@Override
	public void excluirLista(List<ContaCorrenteAutorizadaVO> voList) {
		
		StringBuilder sb = new StringBuilder();
		
		for (ContaCorrenteAutorizadaVO vo : voList) {
			if (ccDAO.isReferenciado(vo)) {
				sb.append(BaseUtil.getTextoFormatado(
						ConstantesDEPI.Geral.ERRO_EXCLUSAO_ITEM,
							BaseUtil.getTextoFormatado(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO, vo.toString(), "Motivo Dep\u00F3sito")));
			}
		}
		
		if (sb.length() > 0) {
			throw new DEPIBusinessException(
					ConstantesDEPI.Geral.ERRO_EXCLUSAO_LISTA, sb.toString());
		}
		
		for (ContaCorrenteAutorizadaVO vo : voList) {
			ccDAO.excluir(vo);
		}		
	}
	
	private void validar(ContaCorrenteAutorizadaVO vo, boolean inclusao)
			throws DEPIBusinessException {
    	
        try {
			BaseUtil.assertTrueThrowException(BaseUtil.isNZB(vo), ConstantesDEPI.MSG_CUSTOMIZADA,
			    "ContaCorrenteAutorizadaVO vo é null : validar");

			if (inclusao) {
				BaseUtil.validarParametro(vo.getCia(), "Cia");
				
				BaseUtil.validarParametro(vo.getCia().getCodigoCompanhia(), "Cia");
			}

			BaseUtil.validarParametro(vo.getBanco(), BANCO_STRING);

			BaseUtil.validarParametro(vo.getBanco().getCdBancoExterno(), BANCO_STRING);

			BaseUtil.validarParametro(vo.getCodigoAgencia(), AGENCIA_STRING);

			BaseUtil.validarParametro(vo.getContaCorrente(), "Conta Corrente");

			BaseUtil.validarParametro(vo.getCodigoResponsavelUltimaAtualizacao(), "Usu\u00E1rio Respons\u00E1vel pela Atualiza\u00E7\u00E3o");

			BaseUtil.assertTrueThrowException(BaseUtil.isGreater(vo.getObservacao(), 200), ConstantesDEPI.Geral.ERRO_CAMPO_EXCESSO, "Observa\u00E7\u00E3o");
			
		} catch (BusinessException e) {
			throw new DEPIBusinessException(e, "msg.erro.salvar", e.getMessage());
		}

    }

}
