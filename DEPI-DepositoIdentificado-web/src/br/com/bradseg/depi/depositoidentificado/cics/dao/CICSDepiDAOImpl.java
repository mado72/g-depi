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
import br.com.bradseg.depi.depositoidentificado.cics.vo.GTAB0030VO;
import br.com.bradseg.depi.depositoidentificado.cics.vo.GTAB1411VO;
import br.com.bradseg.depi.depositoidentificado.cics.vo.GTAB1412VO;
import br.com.bradseg.depi.depositoidentificado.cics.vo.STES0512VO;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsExecutor;
import br.com.bradseg.depi.depositoidentificado.util.annotations.Program;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
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
	private CicsExecutor cicsUtil;


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
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterContaCorrente(br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO)
	 */
	@Override
	public ContaCorrenteAutorizadaVO obterContaCorrente(ContaCorrenteAutorizadaVO cc) {
		
		STES0512VO book = new STES0512VO();
        book.setCodigoCia(cc.getCia().getCodigoCompanhia());
        book.setCodigoAgencia(cc.getCodigoAgencia());
        book.setCodigoBanco(cc.getBanco().getCdBancoExterno());
        book.setConta(String.format("%013d", cc.getContaCorrente()));
        
		Program<STES0512VO> program = cicsUtil.construir(javaGateway, STES0512VO.class);
		List<STES0512VO> lista = cicsUtil.execute(program, book);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		STES0512VO retorno = lista.get(0);
		
		if (retorno.getRetCode() > 0) {
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CICS_CUSTOM, retorno.getMensagem());
		}
		
		if (retorno.getConta().trim().isEmpty() || retorno.getContaCorrenteInterna() == null) {
			StringBuilder erro = new StringBuilder("Conta corrente n\u00E3o encontrada! ");
			if (retorno.getMensagem() != null) {
				erro.append(retorno.getMensagem());
			}
				
			throw new DEPIIntegrationException(ConstantesDEPI.ERRO_CICS_CUSTOM, erro.toString());
		}
		long conta = Long.parseLong(retorno.getConta());
		ContaCorrenteAutorizadaVO vo = new ContaCorrenteAutorizadaVO(new BancoVO(retorno.getCodigoBanco()), retorno.getCodigoAgencia(), conta);
		
		vo.setCodigoInternoCC(Integer.parseInt(retorno.getContaCorrenteInterna()));
		
		return vo;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterContasCorrente(java.util.Collection)
	 */
	@Override
	public List<ContaCorrenteAutorizadaVO> obterContasCorrente(Collection<ContaCorrenteAutorizadaVO> contas) {
		// TODO Implementar
		return null;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterBanco(br.com.bradseg.depi.depositoidentificado.vo.BancoVO)
	 */
	@Override
	public BancoVO obterBanco(BancoVO banco) {
		
		String cdExterno = String.format("%04d", banco.getCdBancoExterno());
		
		GTAB0030VO input = new GTAB0030VO();
		input.setCdBancoExterno(cdExterno);
		
		Program<GTAB0030VO> program = cicsUtil.construir(javaGateway, GTAB0030VO.class);
		List<GTAB0030VO> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		GTAB0030VO retorno = lista.get(0);
		
		BancoVO vo = new BancoVO();
		vo.setCdBancoExterno(Integer.valueOf(retorno.getCdBancoExterno()));
		vo.setCdBancoInterno(Integer.valueOf(retorno.getCdBancoInterno()));
		vo.setCdExcluido(retorno.getCodigoExcluido());
		vo.setCdPessoa(Float.valueOf(retorno.getCodigoPessoa()));
		vo.setCdExiste(retorno.getExiste());
		vo.setCnpjBanco(retorno.getCnpjBanco());
		vo.setDvBanco(retorno.getDvBanco());
		vo.setDescricaoBanco(retorno.getNomeBanco());
		vo.setSiglaBanco(retorno.getSiglaBanco());
		
		return vo;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterBancos(java.util.Collection)
	 */
	@Override
	public List<BancoVO> obterBancos(Collection<BancoVO> bancos) {
		HashMap<Integer, BancoVO> cache = new HashMap<>();
		
		ArrayList<BancoVO> retorno = new ArrayList<>();
		
		for (BancoVO item : bancos) {
			final BancoVO banco;
			
			int codigo = item.getCdBancoExterno();
			
			if (cache.containsKey(codigo)) {
				banco = cache.get(codigo);
			}
			else {
				banco = obterBanco(item);
				cache.put(codigo, banco);
			}
			
			retorno.add(banco);
		}
		
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterAgencia(int, int)
	 */
	@Override
	public String obterAgencia(int codigoBanco, int codigoAgencia) {
		GTAB1411VO input = new GTAB1411VO();
		input.setCdBancoExterno(codigoBanco);
		input.setCdAgenciaExterno(codigoAgencia);
		
		Program<GTAB1411VO> program = cicsUtil.construir(javaGateway, GTAB1411VO.class);
		List<GTAB1411VO> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		GTAB1411VO retorno = lista.get(0);
		return retorno.getNomeAgencia();
	}

}
