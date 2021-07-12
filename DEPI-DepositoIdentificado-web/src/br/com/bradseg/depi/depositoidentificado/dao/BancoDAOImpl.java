/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import br.com.bradseg.bsad.framework.core.jdbc.JdbcDao;
import br.com.bradseg.depi.depositoidentificado.dao.mapper.AgenciaDataMapper;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;
import br.com.bradseg.depi.depositoidentificado.model.enumerated.Tabelas;
import br.com.bradseg.depi.depositoidentificado.util.BaseUtil;
import br.com.bradseg.depi.depositoidentificado.util.QueriesDepi;
import br.com.bradseg.depi.depositoidentificado.vo.AgenciaVO;
import br.com.bradseg.depi.depositoidentificado.vo.BancoVO;
import br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO;
import br.com.bradseg.depi.depositoidentificado.vo.DepartamentoVO;
import br.com.bradseg.depi.depositoidentificado.vo.MotivoDepositoVO;

/**
 * Fornece métodos para acessar dados de banco 
 * @author Marcelo Damasceno
 */
@Repository
public class BancoDAOImpl extends JdbcDao implements BancoDAO {
	
	@Autowired
	private DataSource datasource;
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.bsad.framework.core.jdbc.JdbcDao#getDataSource()
	 */
	@Override
	public DataSource getDataSource() {
		return datasource;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.BancoDAO#obterBancos(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO)
	 */
	@Override
	public List<BancoVO> obterBancos(CompanhiaSeguradoraVO cia) {
		
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("whr1", cia.getCodigoCompanhia());

			List<Integer> codigosBanco = getJdbcTemplate().queryForList(
					QueriesDepi.CONTACORRENTEAUTORIZADA_OBTERBANCOS, params,
					Integer.class);
			
			ArrayList<BancoVO> bancos = new ArrayList<>();
			for (Integer codBancoExterno : codigosBanco) {
				BancoVO banco = new BancoVO(codBancoExterno);
				bancos.add(banco);
			}
			
			return bancos;
			
		}
		catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
		
	}
	
	@Override
	public List<BancoVO> obterBancosMotivoDeposito(CompanhiaSeguradoraVO cia, DepartamentoVO depto, MotivoDepositoVO motivo) {
		
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			
			BaseUtil.prepararQuery(params, BaseUtil.PARAM_WHR, 
					cia.getCodigoCompanhia(),
					depto.getCodigoDepartamento(),
					motivo.getCodigoMotivoDeposito());
			
			List<Integer> codigosBanco = getJdbcTemplate().queryForList(
					QueriesDepi.CONTACORRENTEAUTORIZADA_OBTERBANCOSCOMRESTRICAODEASSOCIACAODEMOTIVOS, params,
					Integer.class);
			
			ArrayList<BancoVO> bancos = new ArrayList<>();
			for (Integer codBancoExterno : codigosBanco) {
				BancoVO banco = new BancoVO(codBancoExterno);
				bancos.add(banco);
			}
			
			return bancos;
			
		}
		catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.dao.BancoDAO#obterAgencias(br.com.bradseg.depi.depositoidentificado.vo.CompanhiaSeguradoraVO, br.com.bradseg.depi.depositoidentificado.vo.BancoVO)
	 */
	@Override
	public List<AgenciaVO> obterAgencias(CompanhiaSeguradoraVO cia,
			BancoVO bancoVO) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("whr1", cia.getCodigoCompanhia());
		params.addValue("whr2", bancoVO.getCdBancoExterno());
		
		return getJdbcTemplate().query(
				QueriesDepi.CONTACORRENTEAUTORIZADA_OBTERAGENCIAS, params,
				new AgenciaDataMapper());
	}
	
	@Override
	public List<AgenciaVO> obterAgenciasComRestricaoMotivo(
			CompanhiaSeguradoraVO ciaVO, DepartamentoVO depto, BancoVO bancoVO,
			MotivoDepositoVO motivoVO, Tabelas contaCorrenteMotivoDeposito) {
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		BaseUtil.prepararQuery(params, BaseUtil.PARAM_WHR, 
				ciaVO.getCodigoCompanhia(),
				depto.getCodigoDepartamento(),
				motivoVO.getCodigoMotivoDeposito(),
				bancoVO.getCdBancoExterno());
		
		return getJdbcTemplate().query(
				QueriesDepi.CONTACORRENTEAUTORIZADA_OBTERAGENCIASCOMRESTRICAODEASSOCIACAODEMOTIVOS, params,
				new AgenciaDataMapper());
	}
}
