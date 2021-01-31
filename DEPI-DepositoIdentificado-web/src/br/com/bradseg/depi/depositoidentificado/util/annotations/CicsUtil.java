/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import br.com.bradseg.bsad.framework.ctg.programapi.field.AbstractFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DateFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DoubleFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.FieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IntegerFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.LongFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.StringFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramImpl;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CommonAreaMetaData;
import br.com.bradseg.bsad.framework.ctg.programapi.program.InputSet;
import br.com.bradseg.bsad.framework.ctg.programapi.program.ResultSet;
import br.com.bradseg.bsad.framework.ctg.programapi.support.gateway.CTGJavaGateway;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;

/**
 * Classe utilitária para analisar as anotações e fazer as chamadas CICS 
 * @author Marcelo Damasceno
 */
@Service
public class CicsUtil {
	
	public static final class Program<T> extends CTGProgramImpl {
		
		private transient final Class<T> programType;
		private final Collection<OrderedField> output;
		private final Collection<OrderedField> input;
		
		/**
		 * @param gateway
		 * @param pPgmName
		 *            Nome do programa
		 * @param pTranName
		 *            Nome da transação
		 * @param pCommonAreaSize
		 *            Common Area
		 * @param programType
		 *            A classe que representa os dados manipulados pelo
		 *            programa.
		 * @param input
		 *            Input Common Area
		 * @param output
		 *            Output Common Area
		 */
		private Program(CTGJavaGateway gateway, String pPgmName,
				String pTranName, int pCommonAreaSize, Class<T> programType,
				Collection<OrderedField> input, Collection<OrderedField> output) {
			
			super(pPgmName, pTranName, pCommonAreaSize,
					new CommonAreaMetaData(extractFieldTypeArray(input)),
					new CommonAreaMetaData(extractFieldTypeArray(output)),
					gateway);
			
			this.programType = programType;
			this.input = input;
			this.output = output;
		}
		
		/**
		 * Retorna programType
		 * @return o programType
		 */
		public Class<T> getProgramType() {
			return programType;
		}

		private static FieldType[] extractFieldTypeArray(
				Collection<OrderedField> commonField) {
			
			ArrayList<FieldType> fields = new ArrayList<>();
			
			for (OrderedField ofd : commonField) {
				fields.add(ofd.createFieldType());
			}
			
			return fields.toArray(new FieldType[0]);
		}
		
	}
	
	private static class OrderedField implements Comparable<OrderedField> {
		
		private final Class<?> type;
		
//		private final Field javaField;
		
		private final CicsField cicsField;
		
		private final Method getter;
		
		private final Method setter;
		
		private final String fieldName;
		
		public OrderedField(PropertyDescriptor pd, CicsField cicsField) {
			this.cicsField = cicsField;
			
			String cicsFieldName = cicsField.fieldName();
			
			if (cicsFieldName.trim().isEmpty()) {
				cicsFieldName = pd.getName();
			}
			this.fieldName = cicsFieldName;
			this.type = pd.getPropertyType();
			this.getter = pd.getReadMethod();
			this.setter = pd.getWriteMethod();
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(OrderedField o) {
			return cicsField.order() - o.cicsField.order();
		}
		
		/**
		 * Retorna direction
		 * @return o direction
		 */
		public CicsField.Direction getDirection() {
			return cicsField.direction();
		}
		
		public AbstractFieldType createFieldType() {
			
			final int fieldSize = cicsField.size();
			
			if (type.equals(Integer.class) || type.equals(int.class)) {
				return new IntegerFieldType(fieldName, fieldSize);
			} else if (type.equals(String.class)) {
				return new StringFieldType(fieldName, fieldSize);
			} else if (type.equals(Long.class) || type.equals(long.class)) {
				return new LongFieldType(fieldName, fieldSize);
			} else if (Date.class.isAssignableFrom(type)) {
				return new DateFieldType(fieldName, fieldSize, cicsField.pattern());
			} else if (type.equals(Double.class) || type.equals(double.class)) {
				int fieldDecimals = cicsField.decimals();
				return new DoubleFieldType(fieldName, fieldSize, fieldDecimals);
			} 
			return null;			
		}
	}

	/**
	 * Constrói uma instância para uma função CICS
	 * 
	 * @param javaGateway
	 *            Gateway para acessar o CICS
	 * @param classePrograma
	 *            Classe com anotações que informam como realizar a chamada
	 * @return Programa instanciado.
	 * @see CicsProgram
	 * @see CicsField
	 */
	public <T> Program<T> construir(CTGJavaGateway javaGateway,
			Class<T> classePrograma) {
		CicsProgram programAnnotation = classePrograma.getAnnotation(
				CicsProgram.class);
		
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(classePrograma);
		} catch (IntrospectionException e) {
			throw new DEPIIntegrationException(e);
		}
		
		Map<String, Field> mapPropField = new HashMap<>();
		for (Field field : classePrograma.getDeclaredFields()) {
			mapPropField.put(field.getName(), field);
		}
		
		Set<OrderedField> commonFieldIn = new TreeSet<>();
		Set<OrderedField> commonFieldOut = new TreeSet<>();
		
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			Field javaField = mapPropField.get(pd.getName());
			OrderedField orderField = mapField(pd, javaField);
			
			if (orderField == null) {
				continue;
			}
			
			switch (orderField.getDirection()) {
			case In:
				commonFieldIn.add(orderField);
				break;
			case Out:
				commonFieldOut.add(orderField);
				break;
			default:
				commonFieldIn.add(orderField);
				commonFieldOut.add(orderField);
				break;
			}
		}
		
		Program<T> program = new Program<>(
				javaGateway,
				programAnnotation.programName(), 
				programAnnotation.transactionName(), 
				programAnnotation.commLength(), 
				classePrograma,
				Collections.unmodifiableSet(commonFieldIn),
				Collections.unmodifiableSet(commonFieldOut));
		
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
	 */
	public <T> List<T> execute(Program<T> program, T input) {
		prepareInputData(program, input);
		
		ArrayList<T> result = new ArrayList<>();
		ResultSet rs = program.execute();
		
		do {
			T vo = readResultSetRow(program, rs);
			result.add(vo);
		} while (rs.next());
		return result;
	}

	private OrderedField mapField(PropertyDescriptor pd, Field javaField) {
		if (javaField == null) {
			return null;
		}
		
		CicsField cicsField = javaField.getAnnotation(CicsField.class);
		
		if (cicsField == null) {
			return null;
		}
	
		return new OrderedField(pd, cicsField);
	}

	private <T> InputSet prepareInputData(Program<T> program, T vo) {
		Collection<OrderedField> fieldsIn = program.input;
		
		InputSet inputSet = program.getInputSet();

		for (OrderedField ofd : fieldsIn) {
			
			Class<?> type = ofd.type;
			
			try {
				if (type.equals(Integer.class) || type.equals(int.class)) {
					Integer valor = (Integer) ofd.getter.invoke(vo);
					inputSet.setInteger(ofd.fieldName, valor);
				} else if (type.equals(String.class)) {
					String valor = (String) ofd.getter.invoke(vo);
					if (ofd.cicsField.pattern().length() > 0) {
						valor = marshall(ofd.cicsField.pattern(), valor);
					}
					inputSet.setString(ofd.fieldName, valor);
				} else if (type.equals(Long.class) || type.equals(long.class)) {
					Long valor = (Long) ofd.getter.invoke(vo);
					inputSet.setLong(ofd.fieldName, valor);
				} else if (type.equals(Double.class) || type.equals(double.class)) {
					Double valor = (Double) ofd.getter.invoke(vo);
					inputSet.setDouble(ofd.fieldName, valor);
				} else if (type.equals(Date.class)) {
					Date valor = (Date) ofd.getter.invoke(vo);
					inputSet.setDate(ofd.fieldName, valor);
				} else {
					continue;
				}
			} catch (IllegalArgumentException e) {
				throw new DEPIIntegrationException(e);
			} catch (IllegalAccessException e) {
				throw new DEPIIntegrationException(e);
			} catch (InvocationTargetException e) {
				throw new DEPIIntegrationException(e);
			}
		}
		return inputSet;
	}

	private String marshall(String pattern, String valor) {
		if (valor == null) {
			return pattern;
		}
		if (pattern == null || pattern.length() == valor.length()) {
			return valor;
		}
		
		StringBuilder sb = new StringBuilder(pattern.substring(0, pattern.length() - valor.length()));
		sb.append(valor);
		return sb.toString();
	}

	private <T> T readResultSetRow(Program<T> program, ResultSet rs) {

		T vo;
		try {
			vo = program.programType.newInstance();

			for (OrderedField ofd : program.output) {

				Class<?> type = ofd.type;

				if (type == Integer.class) {
					ofd.setter.invoke(vo, rs.getInteger(ofd.fieldName));
				} else if (type == String.class) {
					ofd.setter.invoke(vo, rs.getString(ofd.fieldName));
				} else if (type == Long.class) {
					ofd.setter.invoke(vo, rs.getString(ofd.fieldName));
				} else if (type == Double.class) {
					ofd.setter.invoke(vo, rs.getDouble(ofd.fieldName));
				} else if (type == Date.class) {
					ofd.setter.invoke(vo, rs.getDate(ofd.fieldName));
				} else {
					continue;
				}
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

}
