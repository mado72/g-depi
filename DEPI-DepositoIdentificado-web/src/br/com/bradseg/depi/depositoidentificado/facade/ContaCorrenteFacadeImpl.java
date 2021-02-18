/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.facade;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementação de {@link ContaCorrenteFacade}
 * @author Marcelo Damasceno
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class ContaCorrenteFacadeImpl implements ContaCorrenteFacade {

}
