/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.model.relatorio.EnvioRetornoBancoSinteticoVO;

/**
 * 
 * @author Marcelo Damasceno
 *
 */
@Controller
public class RelatorioEnvioRetornoBancoSinteticoAction extends
		RelatorioAction<EnvioRetornoBancoSinteticoVO> {

	private static final long serialVersionUID = -2649298132564429810L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public EnvioRetornoBancoSinteticoVO getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.bradseg.depi.depositoidentificado.relatorio.action.RelatorioAction#validateConsultar()
	 */
	@Override
	public void validateConsultar() {
		// TODO Auto-generated method stub

	}

}
