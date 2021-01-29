package br.com.bradseg.depi.depositoidentificado.model.book;


import br.com.bradseg.bsad.component.ctggateway.CTGProgramImpl;
import br.com.bradseg.bsad.component.ctggateway.CommonAreaMetaData;
import br.com.bradseg.bsad.component.ctggateway.InputSet;
import br.com.bradseg.bsad.component.ctggateway.ResultSet;
import br.com.bradseg.bsad.component.ctggateway.field.FieldType;
import br.com.bradseg.bsad.component.ctggateway.field.IntegerFieldType;
import br.com.bradseg.bsad.component.ctggateway.field.StringFieldType;
import br.com.bradseg.bsad.exception.IntegrationException;

/**
 * Classe de interface para a commarea GTAB0038.
 * Respons�vel por obter os dados do Ramo Interno SUSEP
 * 
 *  Book GTAB1418 - dispon�vel no fim desta Classe
 */
public class GTAB0038B extends CTGProgramImpl {
    
    private static final String PROGRAM_NAME = "GTAB0038";
    private static final String TRANSACTION_NAME = "GT38";
    private static final int COMMON_AREA_LENGTH = 1730;
    
    /**
     * Construtor.
     */
    public GTAB0038B() {
    	super(PROGRAM_NAME, TRANSACTION_NAME, COMMON_AREA_LENGTH, COMMAREAIN, COMMAREAOUT);
    }
    
    private static final CommonAreaMetaData COMMAREAIN = new CommonAreaMetaData(new FieldType[] {
	    //WS-GSBS-RANI
    	new IntegerFieldType("WS-GSBS-ERR-RAMO", 4),
	    new IntegerFieldType("WS-GSBS-TIP-ERR-RAMO", 1),
	    new IntegerFieldType("WS-GSBS-SQLCODE-RAMO", 8),
	    new StringFieldType("FILLER1", 7), 
	    // WS-CONTROLE-RAMO.
	    new IntegerFieldType("WS-CODIGO-RETORNO", 3),
	    new StringFieldType("WS-SINAL-SQLCODE", 1), 
	    new IntegerFieldType("WS-SQLCODE", 4),
	    new IntegerFieldType("WS-SQLERRML", 4),
	    new StringFieldType("WS-SQLERRMC", 70),
	    new StringFieldType("WS-NM-TABELA-ERR", 18),
	    new StringFieldType("WS-CD-EXCLUIDO", 1),
	    new StringFieldType("WS-CD-EXISTE", 1),
	    //WS-CHAVE-RAMO
	    new IntegerFieldType("WS-CD-RAMO", 4),
	    //WS-DADOS-RAMO.
	    new StringFieldType("WS-NOME-RAMO", 40),
	    new StringFieldType("WS-NOME-RAMO-ABREV", 15),
	    new IntegerFieldType("WS-CD-RAMO-SUSEP", 5),
	    new StringFieldType("FILLER2", 24), 
        new IntegerFieldType("WS-GRUPO-SUSEP", 2),	       
	    new StringFieldType("OUTROS_DADOS_DA_BOOK", 124),
	    //WS-CHAVE-COMPL-RAMO.
	    new StringFieldType("WS-DT-PESQ-RAMO",10),
	    new StringFieldType("FILLER3", 1000), 
	    
	    // WS-GRUPO-SUSEP[2]  +  WS-CD-RAMO-SUSEP[2] 
	    
    });
    
    private static final CommonAreaMetaData COMMAREAOUT = COMMAREAIN;
    
	public void populateProgram(InputSet is, int codRamo) throws IntegrationException {
		is.setInteger("WS-CD-RAMO", codRamo);
	}
	
	public void verificaErrosCics(ResultSet rs) throws IntegrationException {
		if(rs.getInteger("WS-CODIGO-RETORNO") != 0) {
			throw new IntegrationException("C�digo Retorno:" + rs.getInteger("BWS-CODIGO-RETORNO") + "-" + rs.getString("WS-CODIGO-RETORNO")
										 + "C�digo Erro:" + rs.getString("WS-SQLCODE") + "-" + rs.getString("WS-SQLCODE"));
		}
	}

	public int getCodRamoSusep(ResultSet rs) throws IntegrationException {
		return rs.getInteger("WS-CD-RAMO-SUSEP");
	}
	
	public int getGrupoRamoSusep(ResultSet rs) throws IntegrationException {
		return rs.getInteger("WS-GRUPO-SUSEP");
	}
	   
  /**  BOOK GTAB1418
    *  
   01  WS-AREA-TRANSF-RAMO.
    03 WS-GSBS-RAMO.
       05 WS-GSBS-ERR-RAMO             PIC  9(0004).
       05 WS-GSBS-TIP-ERR-RAMO         PIC  9(0001).
       05 WS-GSBS-SQLCODE-RAMO         PIC  9(0008).
       05 FILLER                       PIC  X(0007).
    03 WS-CONTROLE-RAMO.
       05 WS-CODIGO-RETORNO            PIC  9(0003).
       05 WS-SINAL-SQLCODE             PIC  X(0001).
       05 WS-SQLCODE                   PIC  9(0004).
       05 WS-SQLERRML                  PIC  9(0004).
       05 WS-SQLERRMC                  PIC  X(0070).
       05 WS-NM-TABELA-ERR             PIC  X(0018).
       05 WS-CD-EXCLUIDO               PIC  X(0001).
       05 WS-CD-EXISTE                 PIC  X(0001).
    03 WS-CHAVE-RAMO.
       05 WS-CD-RAMO                   PIC  9(0004).
    03 WS-DADOS-RAMO.
       05 WS-NOME-RAMO                 PIC  X(0040).
       05 WS-NOME-RAMO-ABREV           PIC  X(0015).
       05 WS-CD-RAMO-SUSEP             PIC  9(0005).
       05 WS-COMIS-MAX-AG              PIC  9(0003)V99.
       05 WS-COMIS-MIN-AG              PIC  9(0003)V99.
       05 WS-COMIS-MAX-NAO-AG          PIC  9(0003)V99.
       05 WS-COMIS-MIN-NAO-AG          PIC  9(0003)V99.
       05 WS-HR-INICIO-VIG-RAMO        PIC  9(0002).
       05 WS-HR-FIM-VIG-RAMO           PIC  9(0002).
       05 WS-GRUPO-SUSEP               PIC  9(0002).
       05 WS-NOME-RAMO-SUSEP           PIC  X(0080).
       05 WS-CD-RESP-RAMO              PIC  X(0008).
       05 WS-DT-HR-ULT-ATU-RAMO        PIC  X(0026).
       05 WS-DT-ATIVACAO-REG           PIC  X(0010).
    03 WS-CHAVE-COMPL-RAMO.
       05 WS-DT-PESQ-RAMO              PIC  X(0010).
    03 FILLER                          PIC  X(1000). 

       
    */
  
}
