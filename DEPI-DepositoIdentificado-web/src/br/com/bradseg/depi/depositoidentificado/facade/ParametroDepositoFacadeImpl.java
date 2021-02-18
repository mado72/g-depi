package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO;
import br.com.bradseg.depi.depositoidentificado.dao.CompanhiaSeguradoraDAO;
import br.com.bradseg.depi.depositoidentificado.dao.DepartamentoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.MotivoDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ParametroDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIBusinessException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.Geral;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Bean implementation class for Enterprise Bean: ParametroDepositoFacadeImpl
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ParametroDepositoFacadeImpl implements ParametroDepositoFacade {

    /** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ParametroDepositoFacadeImpl.class);
    
	@Autowired
	private ParametroDepositoDAO parametroDepositoDAO;
	
	@Autowired
	private CICSDepiDAO cicsDepiDAO;
	
	@Autowired
	private CompanhiaSeguradoraDAO ciaDAO;
	
	@Autowired
	private DepartamentoDAO deptoDAO;
	
	@Autowired
	private MotivoDepositoDAO motivoDAO;

    /**
     * alterar
     * @param vo ParametroDepositoVO
     * @throws IntegrationException IntegrationException
     */
	@Override
    public void alterar(ParametroDepositoVO vo) throws IntegrationException {
        validarChave(vo);

        /**
         * É utilizado no Depósito ?
         */
        boolean referenciadoDeposito = parametroDepositoDAO.isReferenciadoDeposito(vo);
        vo.setReferenciadoDeposito(referenciadoDeposito);

        if (!vo.isReferenciadoDeposito()) {
            validaOperacao(vo);
        }
        try {
        	parametroDepositoDAO.alterar(vo, referenciadoDeposito);
        } catch (IntegrationException e) {
        	LOGGER.error("alterar(ParametroDepositoVO vo)", e);
            throw new IntegrationException(e);
        }
    }

    /**
     * excluir
     * @param vo ParametroDepositoVO
     * @throws IntegrationException IntegrationException
     */
    @Override
    public void excluir(ParametroDepositoVO vo) throws IntegrationException {
        try {
        	parametroDepositoDAO.excluir(vo);
        } catch (IntegrationException e) {
        	LOGGER.error("excluir(ParametroDepositoVO vo)",e);
            throw new IntegrationException(e);
        }
    }

    /**
     * excluir
     * @param lista List<ParametroDepositoVO>
     * @throws IntegrationException IntegrationException
     */
    @Override
    public void excluir(List<ParametroDepositoVO> lista) throws IntegrationException {
        StringBuilder sb = new StringBuilder();

        for (ParametroDepositoVO vo : lista) {
            if (!parametroDepositoDAO.isReferenciado(vo)) {
            	parametroDepositoDAO.excluir(vo);
            } else {
                if (sb.length() > 0) {
                    sb.append("; ");
                }
                ParametroDepositoVO param = parametroDepositoDAO.obterPorChave(vo);
                sb.append(" Par�metro de Dep�sito: [Cia: ").append(param.getCompanhia().getCodigoCompanhia()).append(
                    " Departamento: ").append(param.getDepartamento().getSiglaDepartamento()).append(" Motivo: ").append(
                    param.getMotivoDeposito().getDescricaoBasica()).append("]");
            }
        }
        if (sb.length() > 0) {
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA_MODULO + " - " + sb.toString() + " - " +  "Associa��o de Motivos");
        }
    }

    /**
     * inserir
     * @param vo ParametroDepositoVO
     * @throws IntegrationException IntegrationException
     */
    @Override
    public void inserir(ParametroDepositoVO vo) throws IntegrationException {
        validaOperacao(vo);
        
/*
        if (parametroDepositoDAO.obterPorChave(vo) != null) {
        	
        	DepartamentoVO depto = deptoDAO.obterPorChave(vo.getDepartamento());
        	MotivoDepositoVO motv = motivoDAO.obterPorChave(vo.getMotivoDeposito());
        	
        	throw new DEPIBusinessException(ConstantesDEPI.ParametroDeposito.DUPLICADO, 
        			String.valueOf(vo.getCompanhia().getCodigoCompanhia()),
        			depto.getSiglaDepartamento(),
        			motv.getDescricaoBasica());
        }
*/
        parametroDepositoDAO.inserir(vo);
    }

    /**
     * Método de obter por filtro
     * @param codigoUsuario - BigDecimal.
     * @param filtro par�metro depósito com o código do objeto requisitado
     * @throws IntegrationException - trata erro de negócio
     * @return List<ParametroDepositoVO>
     */
    @Override
	public List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(
			int codigoUsuario, FiltroUtil filtro)
			throws IntegrationException {
		
    	List<ParametroDepositoVO> retorno = parametroDepositoDAO
				.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codigoUsuario);
		
//		for (ParametroDepositoVO p : retorno) {
//			p.setReferenciadoDeposito(parametroDepositoDAO
//					.isReferenciadoDeposito(p));
//		}
		return retorno;
    }

    /**
     * Obter por chave
     * @param vo ParametroDepositoVO
     * @return List<ParametroDepositoVO>
     * @throws IntegrationException IntegrationException
     */
    @Override
    public ParametroDepositoVO obterPorChave(ParametroDepositoVO vo) throws IntegrationException {
        validarChave(vo);
        vo = parametroDepositoDAO.obterPorChave(vo);
        vo.setReferenciadoDeposito(parametroDepositoDAO.isReferenciadoDeposito(vo));
        return vo;
    }

    /**
     * método que valida ias informações do vo
     * @param vo - parâmetros de depósito que serão validados
     * @throws IntegrationException - trata erros
     */
    private void validaOperacao(ParametroDepositoVO vo) throws IntegrationException {
        validarChave(vo);
        if (!BaseUtil.isSorN(vo.getCodigoBancoVencimento())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Retira do Banco Após Vencimento?");
        } else if (ConstantesDEPI.CODIGO_SIM.equals(vo.getCodigoBancoVencimento())) {
            if (BaseUtil.isGreater(vo.getNumeroDiasAposVencimento(), 99)) { // 3 meses
                throw new DEPIBusinessException(Geral.ERRO_CAMPO_EXCESSO, "Dias Após Vencido: 99");
            } else if (BaseUtil.isNZB(vo.getNumeroDiasAposVencimento())) {
                throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Dias Após Vencido");
            }
        }
        if (!BaseUtil.isSorN(vo.getCodigoSucursal())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Sucursal");
        }
        if (!BaseUtil.isSorN(vo.getCodigoBloqueto())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Bloqueto");
        }
        if (!BaseUtil.isSorN(vo.getCodigoTipo())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Tipo");
        }
        if (!BaseUtil.isSorN(vo.getCodigoApolice())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Apólice");
        }
        if (!BaseUtil.isSorN(vo.getCodigoCpfCnpj())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Cpf/Cnpj");
        }
        if (!BaseUtil.isSorN(vo.getCodigoRamo())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Ramo");
        }
        if (!BaseUtil.isSorN(vo.getCodigoEndosso())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Endosso");
        }
        if (!BaseUtil.isSorN(vo.getCodigoDossie())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Dossiê");
        }
        if (!BaseUtil.isSorN(vo.getCodigoParcela())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Parcela");
        }
        if (!BaseUtil.isSorN(vo.getCodigoItem())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Item Contábil");
        }
        if (!BaseUtil.isSorN(vo.getCodigoProtocolo())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Protocolo");
        }
        boolean b = !(BaseUtil.isNZB(vo.getOutrosDocumentosNecessarios()))
            && BaseUtil.isGreater(vo.getOutrosDocumentosNecessarios(), 200);
        if (b) {
            throw new DEPIBusinessException(Geral.ERRO_CAMPO_EXCESSO,  "Outros Documentos Necess�rios: 200");
        }
    }

    /**
     * m�todo que v�lida a chave
     * @param vo - objeto que será validado
     * @throws IntegrationException - trata erros
     */
    private void validarChave(ParametroDepositoVO vo) throws IntegrationException {
        if (BaseUtil.isNZB(vo.getCompanhia())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Cia");
        }
        if (BaseUtil.isNZB(vo.getDepartamento())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Departamento");
        }
        if (BaseUtil.isNZB(vo.getMotivoDeposito())) {
            throw new DEPIBusinessException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO, "Motivo de Depósito");
        }
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade#obterCompanhias(int)
     */
    @Override
    public List<CompanhiaSeguradoraVO> obterCompanhias(int codUsuario) {
    	List<CompanhiaSeguradoraVO> cias = ciaDAO.obterComRestricaoDeGrupoAcesso(codUsuario);
    	
    	if (cias.isEmpty()) {
    		throw new DEPIBusinessException(ConstantesDEPI.ParametroDeposito.ERRO_USUARIO_SEM_GRUPO_ASSOCIADO);
    	}
    	
		return cicsDepiDAO.obterCias(cias);
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade#obterCompanhia(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
     */
    @Override
    public CompanhiaSeguradoraVO obterCompanhia(CompanhiaSeguradoraVO companhia) {
    	return cicsDepiDAO.obterCiaPorCodigo(companhia.getCodigoCompanhia());
    }

    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade#obterComRestricaoGrupoAcesso(int, br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
     */
    @Override
    public List<DepartamentoVO> obterComRestricaoGrupoAcesso(int codUsuario,
    		CompanhiaSeguradoraVO companhia) {
    	return deptoDAO.obterComRestricaoDeGrupoAcesso(companhia.getCodigoCompanhia(), codUsuario, Tabelas.GRUPO_ACESSO);
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade#obterDepartamento(br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO)
     */
    @Override
    public DepartamentoVO obterDepartamento(DepartamentoVO departamento) {
    	return deptoDAO.obterPorChave(departamento);
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade#obterMotivos()
     */
    @Override
    public List<MotivoDepositoVO> obterMotivos() {
    	return motivoDAO.obterTodos();
    }
    
    /* (non-Javadoc)
     * @see br.com.bradseg.depi.depositoidentificado.facade.ParametroDepositoFacade#obterMotivo(br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO)
     */
    @Override
    public MotivoDepositoVO obterMotivo(MotivoDepositoVO motivoDeposito) {
    	return motivoDAO.obterPorChave(motivoDeposito);
    }

}