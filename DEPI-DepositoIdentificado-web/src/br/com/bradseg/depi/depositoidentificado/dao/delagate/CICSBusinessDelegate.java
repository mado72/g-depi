package br.com.bradseg.depi.depositoidentificado.dao.delagate;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Classe CICSBusinessDelegate.
 */
public class CICSBusinessDelegate {
	
	/** Constante responsavel por armazenar LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CICSBusinessDelegate.class);

    private static final CICSBusinessDelegate INSTANCE = new CICSBusinessDelegate();
    private static final String UNCHECKED = "unchecked";

    private static List<CiaVO> listaCias = null;
    private static Collection<GTAB1412> listaCiasCompleta = null;
    private GTAB1412 cia = null;

    /**
     * getInstance.
     * @return Singleton CICSBusiness Instance.
     */
    @Log(levelOnException = LogLevel.FATAL)
    public static CICSBusinessDelegate getInstance() {
        return INSTANCE;
    }

    /**
     * M�todo utilizado para retornar uma cole��o de todas as cias (GTAB1412).
     * @return Collection<GTAB1412>.
     * @throws DEPIIntegrationException - Integra��o.
     */
    @Log(levelOnException = LogLevel.FATAL)
    public synchronized Collection<GTAB1412> obterCiasCompleta() throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterCiasCompleta()");
    	try {
        if (listaCiasCompleta == null) {
            listaCiasCompleta = getLocalFacade().obterCiasCompleta();
        }
    	} finally {
        	endMethod(LOGGER, "obterCiasCompleta()");
    	}
        return listaCiasCompleta;
    }

    /**
     * m�todo que retorna cias
     * @param pNome - valores que ser�o utilizados como par�metro
     * @param pOperacao - valores que ser�o utilizados como par�metro
     * @return cias
     * @throws DEPIIntegrationException trata exce��o
     */
    @Log(levelOnException = LogLevel.FATAL)
    public Collection<CiaVO> obterCiasPorNome(String pNome, TipoOperacao pOperacao) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterCiasPorNome(String pNome, TipoOperacao pOperacao)");
    	try {
    		return getLocalFacade().obterCiasPorNome(pNome, pOperacao);        
    	} finally {
        	endMethod(LOGGER, "obterCiasPorNome(String pNome, TipoOperacao pOperacao)");
    	}
    }

    /**
     * Obtem uma Conta Corrente a partir do seu c�digo interno.
     * @param codigoCia - int.
     * @param codigoBanco - int
     * @param codigoAgencia - int.
     * @param conta - long.
     * @return ContaCorrenteAutorizadaVO - Conta Corrente Autorizada.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    @Log(levelOnException = LogLevel.FATAL)
    @SuppressWarnings(UNCHECKED)
    public ContaCorrenteAutorizadaVO obterConta(int codigoCia, int codigoBanco, int codigoAgencia, long conta)
        throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterConta(int codigoCia, int codigoBanco, int codigoAgencia, long conta)");
    	try {
            return getLocalFacade().obterConta(codigoCia, codigoBanco, codigoAgencia, conta);     
    	} finally {
        	endMethod(LOGGER, "obterConta(int codigoCia, int codigoBanco, int codigoAgencia, long conta)");
    	}

    }

    /**
     * obterCompanhia
     * @param pArg valores que ser�o utilizados como par�metro
     * @return List<CiaVO>
     * @throws DEPIIntegrationException trata exce��o
     */
    @SuppressWarnings(UNCHECKED)
    @Log(levelOnException = LogLevel.FATAL)
    public synchronized List<CiaVO> obterCias(GTAB1426 pArg) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterCias(GTAB1426 pArg)");
    	try {
    		if (listaCias == null) {
                listaCias = (List<CiaVO>) getLocalFacade().obterCias(pArg);
            }     
    	} finally {
        	endMethod(LOGGER, "obterCias(GTAB1426 pArg)");
    	}
        return listaCias;
    }

    /**
     * obterCompanhia por chave
     * @param pArg valores que ser�o utilizados como par�metro
     * @throws DEPIIntegrationException - Excecao
     */
    @Log(levelOnException = LogLevel.FATAL)
    @SuppressWarnings(UNCHECKED)
    public void obterCia(GTAB1412 pArg) throws DEPIIntegrationException {
        // Singleton para objeto unit�rio, usando outro Singleton de cole��o.
        if (cia != null && cia.getCiaInternoInt() == pArg.getCiaInternoInt()) {
            try {
                AssemblerUtil.copy(cia, pArg);
            } catch (AssemblerException e) {
            	LOGGER.error(e);
                throw new DEPIIntegrationException(e);
            }
        } else {
            obterCiasCompleta(); // singleton
            for (GTAB1412 obj : listaCiasCompleta) {
                if (obj.getCiaInternoInt() == pArg.getCiaInternoInt()) {
                    try {
                        AssemblerUtil.copy(obj, pArg);
                        cia = new GTAB1412();
                        AssemblerUtil.copy(obj, cia);
                    } catch (AssemblerException e) {
                    	LOGGER.error(e);
                        throw new DEPIIntegrationException(e);
                    }
                    break;
                }
            }
        }
    }

    /**
     * obterCia
     * @param codigo - int
     * @return CompanhiaSeguradoraVO - cia
     * @throws DEPIIntegrationException - DEPIIntegrationException
     */
    @Log(levelOnException = LogLevel.FATAL)
    @SuppressWarnings(UNCHECKED)
    public CompanhiaSeguradoraVO obterCiaPorCodigo(int codigo) throws DEPIIntegrationException {
    	
    	beginMethod(LOGGER, "obterCiaPorCodigo(int codigo)");
    	try {
    		GTAB1412 book = GTAB1412.getInstancePesquisaPorCodigoInterno(codigo);
            obterCia(book);
            if (book.getCodigoRetorno() == 0) {
                CICSBusinessDelegate.getInstance().obterCia(book);
                CompanhiaSeguradoraVO cia = new CompanhiaSeguradoraVO();
                cia.setCodigoCompanhia(book.getCiaInternoInt());
                cia.setDescricaoCompanhia(book.getNome());
                return cia;
            }
    	} finally { 
        	endMethod(LOGGER, "obterCiaPorCodigo(int codigo)");
    	}
        return null;
    }

    /**
     * obterCompanhia por chave
     * @param codigoCia - int.
     * @return CiaVO.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    @Log(levelOnException = LogLevel.FATAL)
    @SuppressWarnings(UNCHECKED)
    public CiaVO obterCiaPorCodigoExterno(int codigoCia) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterCiaPorCodigoExterno(int codigoCia");
    	try {
    		return getLocalFacade().obterCiaPorCodigoExterno(codigoCia);
    	} finally {
        	endMethod(LOGGER, "obterCiaPorCodigoExterno(int codigoCia)");
    	}
    }

    /**
     * Obter Banco.
     * @param codigoBanco - int.
     * @return BancoVO
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    @SuppressWarnings(UNCHECKED)
    @Log(levelOnException = LogLevel.FATAL)
    public BancoVO obterBanco(int codigoBanco) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterBanco(int codigoBanco)");
    	try {
    		return getLocalFacade().obterBanco(codigoBanco);
    	} finally {
        	endMethod(LOGGER, "obterBanco(int codigoBanco)");
    	}
    }

    /**
     * M�todo repons�vel por retornar uma AgenciaVO (GTAB1411) a partir do codigo (interno ou externo) de um BancoVO e do seu codigo
     * (interno ou externo). Nunca informar os dois c�digos, apenas ou os externos ou os internos, tanto para AgenciaVO quanto para
     * BancoVO.
     * @param codigoBanco - int.
     * @param codigoAgencia - int.
     * @return AgenciaVO - Agencia.
     * @exception DEPIIntegrationException - DEPIIntegrationException.
     */
    @Log(levelOnException = LogLevel.FATAL)
    public AgenciaVO obterAgencia(int codigoBanco, int codigoAgencia) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterAgencia(int codigoBanco, int codigoAgencia)");
    	try {
    		return getLocalFacade().obterAgencia(codigoBanco, codigoAgencia);
    	} finally {
        	endMethod(LOGGER, "obterAgencia(int codigoBanco, int codigoAgencia)");
    	}
    }

    /**
     * getEJBType
     * @return EJBTypes.CICS_SESSION_FACADE_HOME_JNDI_NAME
     */
    @Override
    @Log(levelOnException = LogLevel.FATAL)
    public EJBTypes getEJBType() {
    	beginMethod(LOGGER, "getEJBType()");
    	try {
    		return EJBTypes.CICS_SESSION_FACADE_HOME_JNDI_NAME;
    	} finally {
        	endMethod(LOGGER, "getEJBType()");
    	}
    }

    /**
     * Obter Eventos - Eventos Cont�beis.
     * @return List<EventoContabilVO>
     * @throws DEPIIntegrationException - DEPIIntegrationException
     */
    @Log(levelOnException = LogLevel.FATAL)
    public List<EventoContabilVO> obterEventosContabeis() throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterEventosContabeis()");
    	try {
    		return getLocalFacade().obterEventosContabeis();
    	} finally {
        	endMethod(LOGGER, "obterEventosContabeis()");
    	}
    }

    /**
     * Obter Itens - Itens Cont�beis.
     * @return List<ItemContabilVO>
     * @param codigoTipoEventoNegocio - int.
     * @throws DEPIIntegrationException - DEPIIntegrationException
     */
    @Log(levelOnException = LogLevel.FATAL)
    public List<ItemContabilVO> obterItensContabeis(int codigoTipoEventoNegocio) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterItensContabeis(int codigoTipoEventoNegocio)");
    	try {
    		return getLocalFacade().obterItensContabeis(codigoTipoEventoNegocio);
    	} finally {
        	endMethod(LOGGER, "obterItensContabeis(int codigoTipoEventoNegocio)");
    	}
    }

    /**
     * Obt�m Eventos Cont�beis por ID
     * @param codigoEvento - int
     * @return EventoContabilVO.
     * @throws DEPIIntegrationException - DEPIIntegrationException.
     */
    @Log(levelOnException = LogLevel.FATAL)
    public EventoContabilVO obterEventoContabil(int codigoEvento) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterEventoContabil(int codigoEvento)");
    	try {
    		return getLocalFacade().obterEventoContabil(codigoEvento);
    	} finally {
        	endMethod(LOGGER, "obterEventoContabil(int codigoEvento)");
    	}
    }

    /**
     * Obt�m Item Cont�bil
     * @param codigoEvento - int.
     * @param codigoItem - int.
     * @return ItemContabilVO
     * @throws DEPIIntegrationException - DEPIIntegrationException
     */
    @Log(levelOnException = LogLevel.FATAL)
    public ItemContabilVO obterItemContabil(int codigoEvento, int codigoItem) throws DEPIIntegrationException {
    	beginMethod(LOGGER, "obterItemContabil(int codigoEvento, int codigoItem)");
    	try {
    		return getLocalFacade().obterItemContabil(codigoEvento, codigoItem);
    	} finally {
        	endMethod(LOGGER, "obterItemContabil(int codigoEvento, int codigoItem)");
    	}
    }
}
