package br.com.bradseg.depi.depositoidentificado.cics;

import org.apache.log4j.Logger;

import br.com.bradseg.bsad.framework.ctg.programapi.field.FieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IntegerFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.StringFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramImpl;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CommonAreaMetaData;

/**
 * Classe responsável por mapear o book da entidade CIA para ser utilizada nas chamadas ao CICS.
 */
//CicsTransaction(program = "GTAB0032", transaction = "GT50", maxLength = 602)
public final class GTAB1412 extends CTGProgramImpl  {
	
	public static final String TIPO_ERRO_GSBS = "tipoErroGSBS";
	public static final String SQL_CODE_GSBS = "sqlCodeGSBS";
	public static final String CODIGO_RETORNO = "codigoRetorno";
	public static final String SINAL_SQL_CODE = "sinalSqlCode";
	public static final String SQL_CODE = "sqlCode";
	public static final String SQL_ERR_ML = "sqlErrML";
	public static final String SQL_ERR_MC = "sqlErrMC";
	public static final String TABELA_ERRO = "tabelaErro";
	public static final String CODIGO_EXCLUIDO = "codigoExcluido";
	public static final String EXISTE = "existe";
	public static final String CIA_EXTERNO = "ciaExterno";
	public static final String CIA_INTERNO = "ciaInterno";
	public static final String CODIGO_PESSOA = "codigoPessoa";
	public static final String NOME = "nome";
	public static final String CNPJ = "cnpj";
	public static final String NOME_LOGRADOURO = "nomeLogradouro";
	public static final String COMPLEMENTO = "complemento";
	public static final String NUMERO_ENDERECO = "numeroEndereco";
	public static final String BAIRRO = "bairro";
	public static final String CIDADE = "cidade";
	public static final String CEP = "cep";
	public static final String UF = "uf";
	public static final String DDD_FAX = "dddFax";
	public static final String DDD_FONE = "dddFone";
	public static final String FONE = "fone";
	public static final String FAX = "fax";
	public static final String EMAIL = "email";
	public static final String CAIXA_POSTAL = "caixaPostal";
	public static final String CEP_CAIXA_POSTAL = "cepCaixaPostal";
	public static final String CD_BANCO_COSSEGURO_CIA = "cdBancoCosseguroCia";
	public static final String CD_AGENCIA_COSSEGURO_CIA = "cdAgenciaCosseguroCia";
	public static final String CONTA_COSSEGURO = "contaCosseguro";
	public static final String SIGLA = "sigla";
	public static final String OBS = "obs";
	public static final String LEI = "lei";
	public static final String DATA_PUBLICACAO_LEI = "dataPublicacaoLei";
	public static final String SE_GRUPO_BRADESCO = "seGrupoBradesco";
	public static final String CD_RESPONSAVEL = "cdResponsavel";
	public static final String DT_PROG_GRUPO_BRADESCO = "dtProgGrupoBradesco";
	public static final String CD_PROC_FENASEG = "cdProcFenaseg";
	public static final String ULTIMA_ATUALIZACAO_COMPLETA = "ultimaAtualizacaoCompleta";
	public static final String ULTIMA_ATUALIZACAO = "ultimaAtualizacao";
	public static final String ERRO_GSBS = "erroGSBS";

	private static final String PROGRAM_NAME = "GTAB0032";
	private static final String TRANSACTION_NAME = "GT50";
	private static final Integer COMM_LENGTH = 602;
	protected static final Logger LOGGER = Logger.getLogger(GTAB1412.class);

	public static final String QUATRO = "0000";
	
	protected static final FieldType[] COMMON_FIELDS = new FieldType[] {
		new IntegerFieldType(ERRO_GSBS, 4),   //QUATRO
		new IntegerFieldType(TIPO_ERRO_GSBS,1), 
		new StringFieldType(SQL_CODE_GSBS, 8), // pattern = "00000000"
		new StringFieldType("filler", 7), // pattern = "0000000"
		new IntegerFieldType(CODIGO_RETORNO, 3), // pattern = "000"
		new StringFieldType(SINAL_SQL_CODE, 1), //
		new IntegerFieldType(SQL_CODE, 4), // pattern = QUATRO
		new IntegerFieldType(SQL_ERR_ML, 4), // pattern = QUATRO
		new StringFieldType(SQL_ERR_MC, 70), //
		new StringFieldType(TABELA_ERRO, 18), //
		new StringFieldType(CODIGO_EXCLUIDO, 1), //
		new StringFieldType(EXISTE, 1), //
		new StringFieldType(CIA_EXTERNO, 4), //QUATRO
		new StringFieldType(CIA_INTERNO, 4), //QUATRO
		//================================================
		new StringFieldType(CODIGO_PESSOA, 18),//pattern = "000000000000000000", use = UseType.Out)
		new StringFieldType(NOME, 50),//
		new StringFieldType(CNPJ, 14),// pattern = "00000000000000", use = UseType.Out)
		new StringFieldType(CNPJ, 14),// pattern = "00000000000000", use = UseType.Out)
		new IntegerFieldType("dv", 1),
		new StringFieldType(NOME_LOGRADOURO, 30),
		new StringFieldType(COMPLEMENTO, 20),
		new StringFieldType(NUMERO_ENDERECO, 6),
		new StringFieldType(BAIRRO, 20),
		new StringFieldType(CIDADE, 20),
		new StringFieldType(CEP, 8),
		new StringFieldType(UF, 2),
		new StringFieldType(DDD_FONE, 2),
		new StringFieldType(FONE, 10),
		new StringFieldType(DDD_FAX, 2),
		new StringFieldType(FAX, 10),
		new StringFieldType(EMAIL, 50),
		new StringFieldType(CAIXA_POSTAL, 7),
		new StringFieldType(CEP_CAIXA_POSTAL, 8),
		new StringFieldType(CD_BANCO_COSSEGURO_CIA, 4),
		new StringFieldType(CD_AGENCIA_COSSEGURO_CIA, 5),
		new StringFieldType(CONTA_COSSEGURO, 14),
		new StringFieldType(SIGLA, 20),
		new StringFieldType(OBS, 75),
		new StringFieldType(LEI, 6),			
		new StringFieldType(DATA_PUBLICACAO_LEI, 10),
		new StringFieldType(SE_GRUPO_BRADESCO, 1),	
		new StringFieldType(CD_PROC_FENASEG, 5),	
		new StringFieldType(DT_PROG_GRUPO_BRADESCO, 10),
		new StringFieldType(CD_RESPONSAVEL, 8),
		new StringFieldType(ULTIMA_ATUALIZACAO_COMPLETA, 26),
		new StringFieldType(ULTIMA_ATUALIZACAO,10)
	};
	
    private static final CommonAreaMetaData COMMAREAIN = new CommonAreaMetaData( COMMON_FIELDS );

    private static final CommonAreaMetaData COMMAREAOUT = new CommonAreaMetaData( COMMON_FIELDS );	
	
	public GTAB1412() {
		super(PROGRAM_NAME, TRANSACTION_NAME, COMM_LENGTH, COMMAREAIN, COMMAREAOUT);
	}
    
}
