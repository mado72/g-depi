/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.ctg.programapi.support.gateway.CTGJavaGateway;
import br.com.bradseg.depi.depositoidentificado.cics.book.CTEV0020;
import br.com.bradseg.depi.depositoidentificado.cics.book.CTEV0021;
import br.com.bradseg.depi.depositoidentificado.cics.book.GTAB0030;
import br.com.bradseg.depi.depositoidentificado.cics.book.GTAB1411;
import br.com.bradseg.depi.depositoidentificado.cics.book.GTAB1412;
import br.com.bradseg.depi.depositoidentificado.cics.book.STES0512;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.util.ConstantesDEPI;
import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsExecutor;
import br.com.bradseg.depi.depositoidentificado.util.annotations.Program;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.ContaCorrenteAutorizadaVO;
import br.com.bradseg.depi.depositoidentificado.vo.EventoContabilVO;
import br.com.bradseg.depi.depositoidentificado.vo.ItemContabilVO;

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
		
		GTAB1412 input = new GTAB1412();
		input.setCiaInterno(ciaInterno);
		
		Program<GTAB1412> program = cicsUtil.construir(javaGateway, GTAB1412.class);
		List<GTAB1412> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		GTAB1412 retorno = lista.get(0);
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
		
		STES0512 book = new STES0512();
        book.setCodigoCia(cc.getCia().getCodigoCompanhia());
        book.setCodigoAgencia(cc.getCodigoAgencia());
        book.setCodigoBanco(cc.getBanco().getCdBancoExterno());
        book.setConta(String.format("%013d", cc.getContaCorrente()));
        
		Program<STES0512> program = cicsUtil.construir(javaGateway, STES0512.class);
		List<STES0512> lista = cicsUtil.execute(program, book);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		STES0512 retorno = lista.get(0);
		
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
		
		GTAB0030 input = new GTAB0030();
		input.setCdBancoExterno(cdExterno);
		
		Program<GTAB0030> program = cicsUtil.construir(javaGateway, GTAB0030.class);
		List<GTAB0030> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		GTAB0030 retorno = lista.get(0);
		
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
		GTAB1411 input = new GTAB1411();
		input.setCdBancoExterno(codigoBanco);
		input.setCdAgenciaExterno(codigoAgencia);
		
		Program<GTAB1411> program = cicsUtil.construir(javaGateway, GTAB1411.class);
		List<GTAB1411> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return null;
		}
		
		GTAB1411 retorno = lista.get(0);
		return retorno.getNomeAgencia();
	}

	public List<EventoContabilVO> obterEventosContabeis(int tipoEvento) {
		CTEV0020 input = new CTEV0020();
		input.setCodigoTipoObjetoNegocio(tipoEvento);
		
		Program<CTEV0020> program = cicsUtil.construir(javaGateway, CTEV0020.class);
		List<CTEV0020> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return Collections.emptyList();
		}
		
		CTEV0020 retorno = lista.get(0);
		return retorno.getLista();
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterEventoContabil(int, int)
	 */
	@Override
	public EventoContabilVO obterEventoContabil(int tipoEvento, int codigoEvento) {
		List<EventoContabilVO> eventos = obterEventosContabeis(tipoEvento);
		
		for (EventoContabilVO vo : eventos) {
			if (vo.getCodigoTipo() == codigoEvento) {
				return vo;
			}
		}
		
		return null;
	}
	
	public List<ItemContabilVO> obterItensContabeis(int tipoEvento) {
		CTEV0021 input = new CTEV0021();
		input.setCodigoTipoEventoNegocio(tipoEvento);
		
		Program<CTEV0021> program = cicsUtil.construir(javaGateway, CTEV0021.class);
		List<CTEV0021> lista = cicsUtil.execute(program, input);
		
		if (lista.isEmpty()) {
			return Collections.emptyList();
		}
		
		CTEV0021 retorno = lista.get(0);
		return retorno.getLista();
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.cics.dao.CICSDepiDAO#obterItemContabil(int, int)
	 */
	@Override
	public ItemContabilVO obterItemContabil(int codigoTipoEventoNegocio,
			int codigoItemContabil) {
		List<ItemContabilVO> itens = obterItensContabeis(codigoTipoEventoNegocio);
		for (ItemContabilVO vo : itens) {
			if (vo.getCodigoTipo() == codigoItemContabil) {
				return vo;
			}
		}
		return null;
	}

}
