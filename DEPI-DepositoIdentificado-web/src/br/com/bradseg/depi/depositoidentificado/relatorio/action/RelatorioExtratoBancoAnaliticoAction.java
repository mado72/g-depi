/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.relatorio.action;

import org.springframework.stereotype.Controller;

import br.com.bradseg.depi.depositoidentificado.relatorio.vo.EnvioRetornoBancoAnaliticoVO;

/**
 * 
 * @author Marcelo Damasceno
 *
 */
@Controller
public class RelatorioExtratoBancoAnaliticoAction extends
		RelatorioAction<EnvioRetornoBancoAnaliticoVO> {

	private static final long serialVersionUID = 4945415909397523653L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public EnvioRetornoBancoAnaliticoVO getModel() {
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
