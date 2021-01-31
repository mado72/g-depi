/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.ctg.programapi.support.gateway.CTGJavaGateway;
import br.com.bradseg.depi.depositoidentificado.cics.vo.GTAB1412VO;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsUtil;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsUtil.Program;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;

/**
 * 
 * @author Marcelo Damasceno
 */
@Repository
public class CICSDepiDAOImpl implements CICSDepiDAO {
	
	@Autowired
	private CTGJavaGateway javaGateway;
	
	@Autowired
	private CicsUtil cicsUtil;


	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterCiaPorCodigo(int)
	 */
	@Override
	public CompanhiaSeguradoraVO obterCiaPorCodigo(int codigoCompanhia) {
		
		String ciaInterno = String.format("%04d", codigoCompanhia);
		
		GTAB1412VO input = new GTAB1412VO();
		input.setCiaInterno(ciaInterno);
		
		Program<GTAB1412VO> program = cicsUtil.construir(javaGateway, GTAB1412VO.class);
		List<GTAB1412VO> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		GTAB1412VO retorno = lista.get(0);
		CompanhiaSeguradoraVO vo = new CompanhiaSeguradoraVO();
		vo.setCodigoCompanhia(codigoCompanhia);
		vo.setDescricaoCompanhia(retorno.getNome());
		return vo;
	}
	
	

}
