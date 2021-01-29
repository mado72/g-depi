/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramProvider;
import br.com.bradseg.bsad.framework.ctg.programapi.program.ResultSet;
import br.com.bradseg.depi.depositoidentificado.cics.GTAB1412;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * 
 * @author Marcelo Damasceno
 */
@Service
public class CICSDepiDAOImpl implements CICSDepiDAO {
	
	@Autowired
	private CTGProgramProvider ctgProgramProvider;

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterCiaPorCodigo(int)
	 */
	@Override
	public CompanhiaSeguradoraVO obterCiaPorCodigo(int codigoCompanhia) {
		GTAB1412 gtab1412 = ctgProgramProvider.get(GTAB1412.class);
		gtab1412.getInputSet().setInteger(GTAB1412.CIA_INTERNO, codigoCompanhia);
		ResultSet rs = gtab1412.execute();
		while (rs.next()) {
		}
		return null;
	}
	
	

}
