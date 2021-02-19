package br.com.bradseg.depi.depositoidentificado.util;


/**
 * Classe com constantes do sistema.
 */
public final class ConstantesDEPI {

	/**
	 * Data Source.
	 */
	public static final String DEPI_DATASOURCE_JNDI_NAME = "java:comp/env/jdbc/DEPI-DepositoIdentificado";

	/**
	 * Esquema do banco.
	 */
	public static final String SCHEMA_BANCO = "DBPROD";
	public static final String DOT = ".";
	/**
	 * Motivo.
	 */
	public static final String TABELA_MOTIVO = "MOTVO_DEP_IDTFD";
	public static final String TABELA_MOTIVO_ID = "CMOTVO_DEP_IDTFD";
	/**
	 * Parâmetros.
	 */
	public static final String TABELA_PARAMETRIZACAO = "PARMZ_DEP_IDTFD";
	/**
	 * Departamento.
	 */
	public static final String TABELA_DEPARTAMENTO = "DEPTO_DEP_IDTFD";
	public static final String TABELA_DEPARTAMENTO_ID = "CDEPTO_DEP_IDTFD";
	/**
	 * Cia
	 */
	public static final String TABELA_CIA = "CIA_SEGDR";
	public static final String TABELA_CIA_ID = "CCIA_SEGDR";
	public static final String TABELA_CIA_NOME = "CSGL_CIA_SEGDR";

	/**
	 * Departamento com Cia.
	 */
	public static final String TABELA_COMPANHIA_DEPARTAMENTO = "CIA_DEPTO_DEP";
	public static final String TABELA_COMPANHIA_DEPARTAMENTO_ID = "CINTRN_CIA_SEGDR";

	/**
	 * Grupo Departamento.
	 */
	public static final String TABELA_GRUPO = "GRP_DEPTO_DEP";
	public static final String TABELA_GRUPO_ID = "CGRP_DEPTO_DEP";

	/**
	 * Alocação Usuário.
	 */
	public static final String TABELA_ALOCACAO_USUARIO = "ALOC_USUAR_GRP_DEP";
	public static final String TABELA_ALOCACAO_USUARIO_ID_USUARIO = "CUSUAR_DEP_IDTFD";

	/**
	 * Conta Corrente Motivo Depósito.
	 */
	public static final String TABELA_CONTA_CORRENTE_MOTIVO_DEPOSITO = "CTA_CORR_MOTVO_DEP";
	/**
	 * Usuário - Funcionário.
	 */
	public static final String TABELA_USUARIO = "USUAR_DEP_IDTFD";

	public static final String TABELA_USUARIO_ID = "CUSUAR_DEP_IDTFD";
	public static final String TABELA_USUARIO_NOME = "IUSUAR_DEP_IDTFD";

	public static final String TABELA_DEPOSITO_IDENTIFICADO = "DEP_IDTFD";
	public static final String TABELA_DEPOSITO_IDENTIFICADO_ID = "CDEP_IDTFD";
	public static final String TABELA_DEPOSITO_IDENTIFICADO_TIPO_GRUPO_RECEBIMENTO = "TPO_GRP_RECEB";
	public static final String TABELA_DEPOSITO_DATA_INI = "";

	public static final String TABELA_LOG_DEPOSITO_IDENTIFICADO = "LOG_DEP_IDTFD";
	public static final String TABELA_ITEM_ARQUIVO_TRANSFERENCIA = "ITEM_ARQ_TRNSF";
	/**
	 * Indicador de Ativo.
	 */
	public static final String INDICADOR_ATIVO = "S";
	public static final String INDICADOR_INATIVO = "N";

	/**
	 * Erros.
	 */
	public static final String ERRO_CAMPO_REQUERIDO = "errors.required"; 
	public static final String ERRO_INTERNO = "msg.erro.interno";
	public static final String ERRO_SEMRESULTADO = "errors.semresultado";
	public static final String ERRO_REGISTRO_DUPLICADO = "msg.erro.registroduplicado";
	public static final String ERRO_RELACIONAMENTOS_NAO_CADASTRADOS = "msg.erro.relecionamentosDependestesNaoCadastrados";

	public static final String ERRO_REGISTRO_INEXISTENTE = "msg.erro.registroinexistente";
	public static final String ERRO_REGISTRO_INEXISTENTE2 = "msg.erro.registroinexistente2";
	public static final String ERRO_REGISTRO_COM_DEPENDENCIA = "msg.erro.registrocomdependencia";
	public static final String ERRO_REGISTRO_JA_CADASTRADO = "msg.erro.registrojacadastrado";
	public static final String ERRO_REGISTRO_JA_CADASTRADO2 = "msg.erro.registrojacadastrado2";

	public static final String ERRO_CUSTOMIZADA = "msg.erro.customizada";

	public static final String ERRO_REGISTRO_DEP_COMP_JA_CADASTRADO = "msg.erro.departamentocompanhia.registrojacadastrado";
	public static final String ERRO_PERMISAO_TABELA = "msg.erro.permisaoTabela";

	/**
	 * ConstantesDEPI Dao
	 */
	public static final String SELECT = " SELECT ";
	public static final String FROM = " FROM ";
	public static final String WHERE = " WHERE ";
	public static final String INNER_JOIN = " INNER JOIN ";
	public static final String ON = " ON ";
	public static final String AND = " AND ";
	public static final String ORDER_BY = " ORDER BY ";

	/**
	 * Conta Corrente Autorizada.
	 */
	public static final String TABELA_CONTA_CORRENTE_AUTORIZADA = "CTA_CORR_AUTRZ";
	public static final String TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_INTERNA = "CCTA_CORR_AUTRZ"; // CONTA
																									// INTERNA
	public static final String TABELA_CONTA_CORRENTE_AUTORIZADA_BANCO = "CBCO";
	public static final String TABELA_CONTA_CORRENTE_AUTORIZADA_AGENCIA = "CAG_BCRIA";
	public static final String TABELA_CONTA_CORRENTE_AUTORIZADA_CONTA_CORRENTE = "CCTA_CORR";
	public static final String TABELA_CONTA_CORRENTE_AUTORIZADA_TRPS = "NCLI_TRANS_PERSO";

	/**
	 * Size
	 */
	public static final int SIZE_DUZENTOS = 200;
	public static final int SIZE_CINCOENTA = 50;
	public static final int SIZE_NAO_DEFINIDO = -1;

	public static final String SIM = "S";
	public static final String NAO = "N";

	public static final String PATHCONFIG = "/br/com/bradseg/depi/depositoidentificado/properties/ApplicationResources_pt_BR";

	// ConstantesDEPI gerais da aplicação.
	public static final String CODIGO_SIM = "S";
	public static final String CODIGO_NAO = "N";
	public static final String VAZIO = "";
	public static final String ZERO = "0";
	public static final int QUATRO = 4;
	public static final String PATTERN_TRES = "000";
	public static final String PATTERN_QUATRO = "0000";
	public static final String PATTERN_CINCO = "00000";
	public static final String PATTERN_SEIS = "000000";
	public static final String PATTERN_SETE = "0000000";
	public static final String PATTERN_OITO = "00000000";
	public static final String PATTERN_NOVE = "000000000";
	public static final String PATTERN_TREZE = "0000000000000";

	// Parâmetros sistema.
	public static final String FORMATO_DATA = "formato.data";
	public static final String FORMATO_HORA = "formato.hora";
	public static final String FORMATO_DATA_HORA = "formato.data.hora";
	public static final String FORMATO_DATA_HORA_SEQUENCIAL = "formato.data.hora.sequencial";
	public static final String FORMATO_MES_ANO = "formato.mes.ano";
	public static final String FORMATO_MOEDA = "formato.moeda";
	public static final String LOCALE_IDIOMA = "locale.idioma";
	public static final String LOCALE_PAIS = "locale.pais";
	public static final String URL_WEBSERVICE_CBBS_PAGAMENTO = "http://ws-mb.bradseg.com.br:7804/CBBS-Pagamento";
	public static final String AMBIENTE_SAP = "EC3";

	// Relatório
	public static final String REPORT_LOCALE = "REPORT_LOCALE";
	public static final String LANG_PT = "pt";
	public static final String PAIS_BR = "BR";
	public static final String DIR_RELATORIOS = "/WEB-INF/jasper/";
	public static final String DIR_IMAGENS = "/images/";
	public static final String PARAM_LOGO = "LOGO";
	public static final String DP06_LOGO_JPG = "definir_aqui_a_imagem.jpg";
	public static final String RELATORIO_VAZIO = "RELATORIO_VAZIO";

	// Erros do CICS
	public static final String ERRO_CICS_TPERRO_01 = "msg.erro.cics.sql";
	public static final String ERRO_CICS_TPERRO_02 = "msg.erro.cics.entidade";
	public static final String ERRO_CICS_TPERRO_03 = "msg.erro.cics.entrada";
	public static final String ERRO_CICS_TPERRO_04 = "msg.erro.cics.tabela";
	public static final String ERRO_CICS_EXCLUIDO_S = "msg.erro.cics.registro.excluido";
	public static final String ERRO_CICS_EXCLUIDO_D = "msg.erro.cics.registro.desativado";
	public static final String ERRO_CICS_EXISTE_N = "msg.erro.cics.registro.inexistente";

	// Erros de Negocio
	public static final String ERRO_CAMPO_OBRIGATORIO = "msg.erro.campo.obrigatorio";
	public static final String ERRO_CAMPO_NUMERICO = "msg.erro.campo.numerico";
	public static final String ERRO_DEPARTAMENTO_NOME_NAOENCONTRADO = "msg.erro.departamento.nome.naoencontrado";
	public static final String ERRO_DEPARTAMENTO_DESCRICAO_NAOENCONTRADA = "msg.erro.departamento.descricao.naoencontrada";
	public static final String ERRO_DEPARTAMENTO_REGISTROCOMDEPENDENCIA = "msg.erro.departamento.registrocomdependencia";
	public static final String ERRO_MOTIVODEPOSITO_REGISTROCOMDEPENDENCIA = "msg.erro.motivodeposito.registrocomdependencia";
	public static final String ERRO_DEPENDENCIAS = "msg.erro.dependencias";
	public static final String ERRO_DEPENDENCIA_MODULO = "msg.erro.registrocomdependencia";
	public static final String ERRO_PARAMETRODEPOSITO_REGISTROCOMDEPENDENCIA = "msg.erro.parametrodeposito.registrocomdependencia";
	public static final String ERRO_GRUPOACESSO_REGISTROCOMDEPENDENCIA = "msg.erro.grupoacesso.registrocomdependencia";

	// Erros de Depósito
	public static final String ERRO_DEPOSITO_REGISTROCOMDEPENDENCIA = "msg.erro.deposito.registrocomdependencia";
	public static final String ERRO_DEPOSITO_TRASNFERENCIA = "msg.erro.deposito.transferencia";

	// Erros CICS
	public static final String ERRO_GENERICO_CICS = "msg.erro.cics.generico";
	public static final String ERRO_GENERICO = "msg.erro.generico";
	public static final String ERRO_CICS_CUSTOM = "msg.erro.cics.custom";

	// Customizada
	public static final String MSG_CUSTOMIZADA = "msg.erro.customizada";

	// Constantes do CICS
	public static final String PORTA_CICS = "2006";

	// ConstantesDEPI
	public static final int MAX_SIZE_CODIGO_USUARIO = 9999999;
	public static final int MAX_SIZE_DEFAULT_NOME = 40;
	public static final int MAX_SIZE_SIGLA_DEPARTAMENTO = 3;

	public static final int ARQUIVO_A_ENVIAR = 1;
	public static final int ARQUIVO_ENVIADO = 2;
	public static final int ARQUIVO_ACEITO = 3;
	public static final int ARQUIVO_RENVIAR = 4;
	public static final int ARQUIVO_REJEITADO = 5;
	public static final int ARQUIVO_CANCELADO = 6;

	public static final String MANUTENCAO_ACEITE = "A";
	public static final String MANUTENCAO_TRASFERENCIA = "T";
	public static final String MANUTENCAO_DEVOLUCAO = "D";
	public static final String MANUTENCAO_RECEITA = "R";

	public static final long UM_DIA_MS = 86400000;

	public static final String HORA_INI = "01:00:00";
	public static final String HORA_FIM = "23:59:59";
	public static final String FORMATO_DATO_HORA2 = "dd/MM/yyyy HH:mm:ss";

	public static final String ATUALIZAR_DEPOSITO = "atualizarDeposito";
	public static final String CANCELAR_DEPOSITO = "cancelarDeposito";
	public static final String PRORROGAR_DEPOSITO = "prorrogarDeposito";

	/* Mensagens Globais */
	public static final String MSG_CONSULTA_RETORNO_VAZIO = "msg.consulta.retorno.vazio";
	public static final String MSG_INSERIR_EXITO = "msg.inserir.sucesso";
	public static final String MSG_ALTERAR_EXITO = "msg.alterar.sucesso";
	public static final String MSG_EXCLUIR_EXITO = "msg.excluir.sucesso";


	/**
	 * Constantes Gerais 
	 */
	public static final class Geral {
		public static final String ERRORS_REQUIRED = "errors.required";
		public static final String ERRO_CAMPO_EXCESSO = "msg.erro.campo.excessocaracteres";
		public static final String ERRO_USUARIO_LOGADO = "msg.erro.usuario.logado";
		public static final String ERRO_USUARIO_OBRIGATORIO = "msg.erro.usuario.obrigatorio";
		public static final String ERRO_CODIGO_INVALIDO = "msg.erro.codigo.invalido";
		public static final String ERRO_CRITERIO_INVALIDO = "msg.erro.criterio.invalido";
		public static final String ERRO_INCLUSAO = "msg.erro.inclusao";
		public static final String ERRO_EXCLUSAO = "msg.erro.exclusao";
		public static final String ERRO_EXCLUSAO_LISTA = "msg.erro.exclusao.lista";
		public static final String ERRO_EXCLUSAO_ITEM = "msg.erro.exclusao.item";
		public static final String ERRO_ALTERACAO = "msg.erro.alteracao";
	}
	
	/**
	 * Constantes para Motivo Deposito 
	 */
	public static final class MotivoDeposito {
		
		public static final String STATUSINATIVO = "msg.erro.motivodeposito.status.inativo";
		public static final String NAOCADASTRADO = "msg.erro.motivodeposito.naocadastrado";
		public static final String DESC_BSCO_JA_CADASTRADA = "msg.erro.motivodeposito.descricao.jacadastrada";
		
	}
	
	/**
	 * Constantes para Departamento 
	 */
	public static final class Departamento {
		public static final String ERRO_CADASTRADO_INATIVO = "msg.erro.departamento.inativo_cadastrado";
	}
	
	/**
	 * Constantes para Departamento Companhia. 
	 */
	public static final class DepartamentoCompanhia {
		public static final String ERRO_PARAMETRODEPOSITO_REFERENCIADO = "msg.erro.departamentocompanhia.parametro";
		public static final String ERRO_GRUPODEPARTAMENTO_REFERENCIADO = "msg.erro.grupodepartamento.parametro";
		public static final String ERRO_MOTIVODEPOSITO_REFERENCIADO = "msg.erro.motivodeposito.parametro";
		public static final String ERRO_NAOCONCLUIDO = "msg.erro.departamentocompanhia.naoconcluido";
		public static final String ERRO_DEPENDENCIA = "msg.erro.departamentocompanhia.dependencia";
	}
	
	/**
	 * Constantes gerais para Grupo Acesso 
	 */
	public static final class GrupoAcesso {
		public static final String LABEL_DEPARTAMENTO = "label.cadastro.grupoacesso.departamento";
		public static final String LABEL_FUNCIONARIO = "label.cadastro.grupoacesso.funcionario";
	}

	/**
	 * Constantes para Parâmetro Depósito
	 */
	public static final class ParametroDeposito {

		public static final String ERRO_USUARIO_SEM_GRUPO_ASSOCIADO = "msg.erro.parametrodeposito.semgrupoacessoassociado";
		public static final String DUPLICADO = "msg.erro.parametrodeposito.duplicado";
		
	}
	
	
	public static final String MIME_TYPE_PDF = "application/pdf";
	public static final String MIME_TYPE_HTML = "text/html";
	public static final String MIME_TYPE_DOC = "application/msword";
	public static final String MIME_TYPE_DOCX = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
	public static final String MIME_TYPE_XLS = "application/excel";
	public static final String MIME_TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String MIME_TYPE_JPEG = "image/jpeg";
	public static final String MIME_TYPE_GIF = "image/gif";
	public static final String MIME_TYPE_PNG = "image/png";
	public static final String MIME_TYPE_BMP = "image/bmp";

}
