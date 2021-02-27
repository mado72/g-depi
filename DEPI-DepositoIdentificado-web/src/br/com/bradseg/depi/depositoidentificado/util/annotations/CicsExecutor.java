/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.bradseg.bsad.framework.ctg.programapi.program.InputSet;
import br.com.bradseg.bsad.framework.ctg.programapi.program.ResultSet;
import br.com.bradseg.bsad.framework.ctg.programapi.support.gateway.CTGJavaGateway;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;

/**
 * Classe utilitária para analisar as anotações e fazer as chamadas CICS 
 * @author Marcelo Damasceno
 */
@Service
public class CicsExecutor {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CicsExecutor.class);
	
	private final CicsProgramDefinitionParser parser = new CicsProgramDefinitionParser();
	
	/**
	 * Constrói uma instância para uma função CICS
	 * 
	 * @param javaGateway
	 *            Gateway para acessar o CICS
	 * @param book
	 *            Classe com anotações que informam como realizar a chamada
	 * @return Programa instanciado.
	 * @see CicsProgram
	 * @see CicsField
	 * @param <T> Classe que representa o book da comunicação.
	 */
	public <T> Program<T> construir(CTGJavaGateway javaGateway,
			Class<T> book) {
		CicsProgram programAnnotation = book.getAnnotation(
				CicsProgram.class);
		
		LOGGER.debug("Construindo consulta para {}", book.getName());
		
		BookConfiguration commonArea = parser.parseBook(book);
		
		Program<T> program = new Program<T>(
				javaGateway,
				programAnnotation.programName(), 
				programAnnotation.transactionName(), 
				programAnnotation.commLength(), 
				book,
				Collections.unmodifiableCollection(commonArea.getCommonFieldIn()),
				Collections.unmodifiableCollection(commonArea.getCommonFieldOut()));
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug(
					"Programa construido: prog: {}, trans: {}, common area: {}, book: {}",
					programAnnotation.programName(),
					programAnnotation.transactionName(),
					programAnnotation.commLength(), book.getName());
		}

		return program;
	}

	/**
	 * Executa um programa CICS utilizando os dados de entrada definidos em
	 * input e retorna lista de objetos com o resultado deste programa.
	 * 
	 * @param program
	 *            Programa CICS
	 * @param input
	 *            Dados de entrada para a execução do programa
	 * @return Lista com dados retornados do CICS
	 * @param <T> Tipo do BOOK a ser utilizado na execução.
	 */
	public <T> List<T> execute(Program<T> program, T input) {
		LOGGER.debug("Executando programa: pgm: {}, trans: {}", program.getPgmName(), program.getTranName());
		prepareInputData(program, input);
		
		ArrayList<T> result = new ArrayList<>();
		
		try {
			ResultSet rs = program.execute();
			return processResultSet(program, result, rs);
		} catch (Exception e) {
			throw new DEPIIntegrationException(e);
		}
	}

	protected <T> List<T> processResultSet(Program<T> program,
			List<T> result, ResultSet rs) {
		LOGGER.debug(
				"Processando resultado da consulta ao programa: pgm: {}, trans: {}",
				program.getPgmName(), program.getTranName());
		
		do {
			T vo = processResultSetRow(program, rs);
			result.add(vo);
		} while (rs.next());
		
		LOGGER.debug(
				"Obtever {} registros da consulta ao programa: pgm: {}, trans: {}",
				result.size(), program.getPgmName(), program.getTranName());
		
		return result;
	}

	private <T> InputSet prepareInputData(Program<T> program, T vo) {
		Collection<FieldDefinitions> fieldsIn = program.input;
		
		InputSet inputSet = program.getInputSet();
	
		for (FieldDefinitions ofd : fieldsIn) {
			prepareFieldInputData(vo, inputSet, ofd);
		}
		return inputSet;
	}

	private <T> void prepareFieldInputData(T vo, InputSet inputSet,
			FieldDefinitions ofd) {
		Class<?> type = ofd.getType();
		
		try {
			Method getter = ofd.getGetter();
			String fieldName = ofd.getFieldName();
			
			if (type.equals(Integer.class) || type.equals(int.class)) {
				Integer valor = (Integer) getter.invoke(vo);
				inputSet.setInteger(fieldName, valor);
				LOGGER.debug("Inserindo inteiro {} = {}", fieldName, valor);
			} else if (type.equals(String.class)) {
				String valor = (String) getter.invoke(vo);
				if (ofd.getCicsField().pattern().length() > 0) {
					valor = marshallData(ofd.getCicsField().pattern(), valor);
				}
				inputSet.setString(fieldName, valor);
				LOGGER.debug("Inserindo string {} = {}", fieldName, valor);
			} else if (type.equals(Long.class) || type.equals(long.class)) {
				Long valor = (Long) getter.invoke(vo);
				inputSet.setLong(fieldName, valor);
				LOGGER.debug("Inserindo long {} = {}", fieldName, valor);
			} else if (type.equals(Double.class) || type.equals(double.class)) {
				Double valor = (Double) getter.invoke(vo);
				inputSet.setDouble(fieldName, valor);
				LOGGER.debug("Inserindo double {} = {}", fieldName, valor);
			} else if (type.equals(Date.class)) {
				Date valor = (Date) getter.invoke(vo);
				inputSet.setDate(fieldName, valor);
				LOGGER.debug("Inserindo Date {} = {}", fieldName, valor);
			} else if (ofd.isCollection()) {
				LOGGER.debug("Processando cole\u00E7\u00E3o {}", fieldName);
				
				List<?> values = new ArrayList<>((Collection<?>) getter.invoke(vo));
				
				CollectionFieldDefinitions cfd = (CollectionFieldDefinitions) ofd;
				Collection<FieldDefinitions> commonFieldIn = cfd.getInnerConfiguration().getCommonFieldIn();
				
				for (int i = 0; i < cfd.getOccurrences(); i++) {
					LOGGER.debug("Processando ocorr\u00EAncia de leitura {} para {}", i+1, fieldName);
					Object value = values.get(i);
					
					for (FieldDefinitions fieldIn : commonFieldIn) {
						prepareFieldInputData(value, inputSet, fieldIn);
					}
				}
				
			} else {
				LOGGER.warn("Ignorando {}", fieldName);
				return;
			}
		} catch (IllegalArgumentException e) {
			throw new DEPIIntegrationException(e);
		} catch (IllegalAccessException e) {
			throw new DEPIIntegrationException(e);
		} catch (InvocationTargetException e) {
			throw new DEPIIntegrationException(e);
		}
	}

	private String marshallData(String pattern, String value) {
		if (value == null) {
			return pattern;
		}
		if (pattern == null || pattern.length() == value.length()) {
			return value;
		}
		
		String patt = pattern.substring(0, pattern.length() - value.length());
		StringBuilder sb = new StringBuilder(patt);
		sb.append(value);
		String resultado = sb.toString();
		LOGGER.debug("Aplicou pattern \"{}\" em {} => {}", patt, value, resultado);
		return resultado;
	}

	private <T> T processResultSetRow(Program<T> program, ResultSet rs) {
	
		T vo;
		try {
			vo = program.programType.newInstance();
	
			for (FieldDefinitions ofd : program.output) {
				processFieldResultSet(rs, vo, ofd);
			}
			
		} catch (IllegalArgumentException e) {
			throw new DEPIIntegrationException(e);
		} catch (IllegalAccessException e) {
			throw new DEPIIntegrationException(e);
		} catch (InstantiationException e) {
			throw new DEPIIntegrationException(e);
		} catch (InvocationTargetException e) {
			throw new DEPIIntegrationException(e);
		}
		return vo;
	}

	private <T> void processFieldResultSet(ResultSet rs, T vo,
			FieldDefinitions ofd) throws IllegalAccessException,
			InvocationTargetException {
		final Class<?> type = ofd.getType();
		final String fieldName = ofd.getFieldName();
		final Method setter = ofd.getSetter();

		if (type == Integer.class || type.equals(int.class)) {
			int valor = rs.getInteger(fieldName);
			LOGGER.debug("Leu inteiro {} = {}", fieldName, valor);
			setter.invoke(vo, valor);
		} else if (type == String.class) {
			String valor = rs.getString(fieldName);
			LOGGER.debug("Leu string {} = {}", fieldName, valor);
			setter.invoke(vo, valor);
		} else if (type == Long.class || type.equals(long.class)) {
			long valor = rs.getLong(fieldName);
			LOGGER.debug("Leu long {} = {}", fieldName, valor);
			setter.invoke(vo, valor);
		} else if (type == Double.class || type.equals(double.class)) {
			double valor = rs.getDouble(fieldName);
			LOGGER.debug("Leu double {} = {}", fieldName, valor);
			setter.invoke(vo, rs.getDouble(fieldName));
		} else if (type == Date.class) {
			Date valor = rs.getDate(fieldName);
			LOGGER.debug("Leu Date {} = {}", fieldName, valor);
			setter.invoke(vo, rs.getDate(fieldName));
		} else if (ofd.isCollection()) {
			List<Object> values = new ArrayList<>();
			
			CollectionFieldDefinitions cfd = (CollectionFieldDefinitions) ofd;
			Collection<FieldDefinitions> commonFieldOut = cfd.getInnerConfiguration().getCommonFieldOut();
			
			for (int i = 0; i < cfd.getOccurrences(); i++) {
				LOGGER.debug("Processando ocorr\u00EAncia de escrita {} para {}", i+1, fieldName);
				
				Object child;
				try {
					child = cfd.getInnerConfiguration().getBook().newInstance();
				} catch (Exception e) {
					throw new DEPIIntegrationException(e); 
				}

				for (FieldDefinitions fieldOut : commonFieldOut) {
					processFieldResultSet(rs, child, fieldOut);
				}
				
				values.add(child);
			}
			
			setter.invoke(vo, values);
		} else {
			LOGGER.warn("Ignorou leitura de {}", fieldName);
			return;
		}
	}

}
