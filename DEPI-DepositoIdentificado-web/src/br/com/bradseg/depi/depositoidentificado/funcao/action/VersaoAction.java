package br.com.bradseg.depi.depositoidentificado.funcao.action;

import java.io.File;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Action para controlar e versionar o sistema. 
 */
@Controller
@Scope("request")
public class VersaoAction extends BaseAction {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VersaoAction.class);

	private static final long serialVersionUID = 6647505076264386999L;
	
	private String dataAlteracao;
	
	@Override
	public String execute()  {
		try {
			ClassLoader classLoader = VersaoAction.class.getClassLoader();
			File f  = new File(classLoader.getResource("br/com/bradseg/depi/depositoidentificado/funcao/action/VersaoAction.class").getFile());
			Date dataUltimaAlteracao = new Date(f.lastModified());
			
			this.dataAlteracao = DateFormatUtils.format(dataUltimaAlteracao, "dd/MM/yyyy HH:mm");
			
			return SUCCESS;
		}
		catch (Exception e) {
			addActionError(e.getMessage());
			LOGGER.error("Falha ao obter dados", e);
			return ERROR;
		}
	}
	
	/**
	 * Retorna a data de alteração da classe VersaoAction
	 * 
	 * @return Data da alteração
	 */
	public String getDataAlteracao() {
		return dataAlteracao;
	}
	
}
