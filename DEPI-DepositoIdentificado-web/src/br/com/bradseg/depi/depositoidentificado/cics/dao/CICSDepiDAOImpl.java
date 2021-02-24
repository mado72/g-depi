/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.ctg.programapi.support.gateway.CTGJavaGateway;
import br.com.bradseg.depi.depositoidentificado.cics.vo.GTAB1412VO;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsUtil;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsUtil.Program;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;

/**
 * Repositório que reúne operações ao CICS.
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
		vo.setCodigoCompanhia(Integer.parseInt(retorno.getCiaInterno()));
		vo.setDescricaoCompanhia(retorno.getNome());
		return vo;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterCias(java.util.Collection)
	 */
	@Override
	public List<CompanhiaSeguradoraVO> obterCias(
			Collection<CompanhiaSeguradoraVO> cias) {
		HashMap<Integer, CompanhiaSeguradoraVO> cache = new HashMap<>();
		
		ArrayList<CompanhiaSeguradoraVO> retorno = new ArrayList<>();
		
		for (CompanhiaSeguradoraVO item : cias) {
			final CompanhiaSeguradoraVO cia;
			
			int codigoCompanhia = item.getCodigoCompanhia();
			if (cache.containsKey(codigoCompanhia)) {
				cia = cache.get(codigoCompanhia);
			}
			else {
				cia = obterCiaPorCodigo(codigoCompanhia);
				cache.put(codigoCompanhia, cia);
			}
			
			retorno.add(cia);
		}
		
		return retorno;
	}
	
	@Override
	public ContaCorrenteAutorizadaVO obterContaCorrente(ContaCorrenteAutorizadaVO cc) {
		// TODO Implementar
		return null;
	}
	
	@Override
	public List<ContaCorrenteAutorizadaVO> obterContasCorrente(Collection<ContaCorrenteAutorizadaVO> contas) {
		// TODO Implementar
		return null;
	}

}
