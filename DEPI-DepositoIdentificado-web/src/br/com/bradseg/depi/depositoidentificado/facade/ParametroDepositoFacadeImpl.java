package br.com.bradseg.depi.depositoidentificado.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bradseg.bsad.framework.core.exception.IntegrationException;
import br.com.bradseg.depi.depositoidentificado.dao.ParametroDepositoDAO;
import br.com.bradseg.depi.depositoidentificado.dao.ParametroDepositoDAOImpl;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI.ERRO_GERAL;
import br.com.bradseg.depi.depositoidentificado.util.FiltroUtil;
import br.com.bradseg.depi.depositoidentificado.vo.ParametroDepositoVO;

/**
 * Bean implementation class for Enterprise Bean: ParametroDepositoFacadeImpl
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ParametroDepositoFacadeImpl implements ParametroDepositoFacade {

    private static final String CODIGO_RESPONSAVEL = "Código do Responsável";
    
	/** A Constante LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ParametroDepositoFacadeImpl.class);
    
	@Autowired
	private ParametroDepositoDAO parametroDepositoDAO;

    /**
     * alterar
     * @param vo ParametroDepositoVO
     * @throws IntegrationException IntegrationException
     */
	@Override
    public void alterar(ParametroDepositoVO vo) throws IntegrationException {
        validarChave(vo);

        /**
         * � utilizado no Dep�sito ?
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
            throw new IntegrationException(ConstantesDEPI.ERRO_DEPENDENCIA + " - " + sb.toString() + " - " +  "Associa��o de Motivos");
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
        try {
        	parametroDepositoDAO.inserir(vo);
        } catch (IntegrationException e) {
        	LOGGER.error("inserir(ParametroDepositoVO vo)",e);
            throw new IntegrationException(e);
        }
    }

    /**
     * Obter por Filtro
     * @param filtro CriterioFiltroUtil
     * @return List<ParametroDepositoVO>
     * @throws IntegrationException IntegrationException
     */
    @Override
    public List<ParametroDepositoVO> obterPorFiltro(FiltroUtil filtro) throws IntegrationException {
        List<ParametroDepositoVO> retorno = parametroDepositoDAO.obterPorFiltro(filtro);
        for (ParametroDepositoVO p : retorno) {
            p.setReferenciadoDeposito(parametroDepositoDAO.isReferenciadoDeposito(p));
        }
        return retorno;
    }

    /**
     * M�todo de obter por filtro
     * @param filtro par�metro dep�sito com o c�digo do objeto requisitado
     * @param codigoUsuario - BigDecimal.
     * @throws IntegrationException - trata erro de neg�cio
     * @return List<ParametroDepositoVO>
     */
    @Override
    public List<ParametroDepositoVO> obterPorFiltroComRestricaoDeGrupoAcesso(FiltroUtil filtro, Integer codigoUsuario)throws IntegrationException {
        List<ParametroDepositoVO> retorno = parametroDepositoDAO.obterPorFiltroComRestricaoDeGrupoAcesso(filtro, codigoUsuario);
        for (ParametroDepositoVO p : retorno) {
            p.setReferenciadoDeposito(parametroDepositoDAO.isReferenciadoDeposito(p));
        }
        return retorno;
    }

    /**
     * Obter todos
     * @return List<ParametroDepositoVO>
     * @throws IntegrationException IntegrationException
     */
    @Override
    public List<ParametroDepositoVO> obterTodos() throws IntegrationException {

    	FiltroUtil filtro = new FiltroUtil();
        List<ParametroDepositoVO> retorno = parametroDepositoDAO.obterPorFiltro(filtro);
        for (ParametroDepositoVO p : retorno) {
            p.setReferenciadoDeposito(parametroDepositoDAO.isReferenciadoDeposito(p));
        }
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
     * m�todo que valida ias informa��es do vo
     * @param vo - par�metros de dep�sito que ser�o validados
     * @throws IntegrationException - trata erros
     */
    private void validaOperacao(ParametroDepositoVO vo) throws IntegrationException {
        validarChave(vo);
        if (!BaseUtil.isSorN(vo.getCodigoBancoVencimento())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Retira do Banco Ap�s Vencimento?");
        } else if (ConstantesDEPI.CODIGO_SIM.equals(vo.getCodigoBancoVencimento())) {
            if (BaseUtil.isGreater(vo.getNumeroDiasAposVencimento(), 99)) { // 3 meses
                throw new IntegrationException(ERRO_GERAL.ERRO_CAMPO_EXCESSO + " - " + "Dias Ap�s Vencido: 2");
            } else if (BaseUtil.isNZB(vo.getNumeroDiasAposVencimento())) {
                throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Dias Ap�s Vencido");
            }
        }
        if (!BaseUtil.isSorN(vo.getCodigoSucursal())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Sucursal");
        }
        if (!BaseUtil.isSorN(vo.getCodigoBloqueto())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Bloqueto");
        }
        if (!BaseUtil.isSorN(vo.getCodigoTipo())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Tipo");
        }
        if (!BaseUtil.isSorN(vo.getCodigoApolice())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Ap�lice");
        }
        if (!BaseUtil.isSorN(vo.getCodigoCpfCnpj())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Cpf/Cnpj");
        }
        if (!BaseUtil.isSorN(vo.getCodigoRamo())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Ramo");
        }
        if (!BaseUtil.isSorN(vo.getCodigoEndosso())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Endosso");
        }
        if (!BaseUtil.isSorN(vo.getCodigoDossie())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Dossi�");
        }
        if (!BaseUtil.isSorN(vo.getCodigoParcela())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Parcela");
        }
        if (!BaseUtil.isSorN(vo.getCodigoItem())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "It�m Cont�bil");
        }
        if (!BaseUtil.isSorN(vo.getCodigoProtocolo())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Protocolo");
        }
        boolean b = !(BaseUtil.isNZB(vo.getOutrosDocumentosNecessarios()))
            && BaseUtil.isGreater(vo.getOutrosDocumentosNecessarios(), 200);
        if (b) {
            throw new IntegrationException(ERRO_GERAL.ERRO_CAMPO_EXCESSO + " - " +  "Outros Documentos Necess�rios: 200");
        }
    }

    /**
     * m�todo que v�lida a chave
     * @param vo - objeto que ser� validado
     * @throws IntegrationException - trata erros
     */
    private void validarChave(ParametroDepositoVO vo) throws IntegrationException {
        if (BaseUtil.isNZB(vo.getCompanhia())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Cia");
        }
        if (BaseUtil.isNZB(vo.getDepartamento())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Departamento");
        }
        if (BaseUtil.isNZB(vo.getMotivoDeposito())) {
            throw new IntegrationException(ConstantesDEPI.ERRO_CAMPO_OBRIGATORIO + " - " + "Motivo de Dep�sito");
        }
    }

}