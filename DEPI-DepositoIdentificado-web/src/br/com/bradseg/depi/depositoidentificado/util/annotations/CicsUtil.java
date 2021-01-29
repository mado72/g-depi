/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import br.com.bradseg.bsad.framework.ctg.programapi.field.AbstractFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DoubleFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.FieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IntegerFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.LongFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DateFieldType;;
import br.com.bradseg.bsad.framework.ctg.programapi.field.StringFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramImpl;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramProvider;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CommonAreaMetaData;
import br.com.bradseg.bsad.framework.ctg.programapi.program.InputSet;
import br.com.bradseg.bsad.framework.ctg.programapi.program.ResultSet;

/**
 * Classe utilitária para analisar as anotações e fazer as chamadas CICS 
 * @author Marcelo Damasceno
 */
public class CicsUtil {
	
	public final static class Program<T> extends CTGProgramImpl {
		
		private transient final Class<T> programType;
		private final Collection<OrderedField> output;
		private final Collection<OrderedField> input;
		
		/**
		 * @param pPgmName
		 *            Nome do programa
		 * @param pTranName
		 *            Nome da transação
		 * @param pCommonAreaSize
		 *            Common Area
		 * @param pInputCommonAreaMetaData
		 *            Input Common Area
		 * @param pOutputCommonAreaMetaData
		 *            Output Common Area
		 * @param programType
		 *            A classe que representa os dados manipulados pelo
		 *            programa.
		 */
		private Program(String pPgmName, String pTranName, int pCommonAreaSize,
				Class<T> programType,
				Collection<OrderedField> input,
				Collection<OrderedField> output) {
			
			super(pPgmName, pTranName, pCommonAreaSize,
					new CommonAreaMetaData(extractFieldTypeArray(input)),
					new CommonAreaMetaData(extractFieldTypeArray(output)));
			
			this.programType = programType;
			this.input = input;
			this.output = output;
		}

		private static FieldType[] extractFieldTypeArray(
				Collection<OrderedField> commonField) {
			
			FieldType[] input = new FieldType[commonField.size()];
			
			int index = 0;
			for (OrderedField orderField : commonField) {
				input[index++] = orderField.createFieldType();
			}
			return input;
		}
		
		/**
		 * Retorna programType
		 * @return o programType
		 */
		public Class<T> getProgramType() {
			return programType;
		}
		
	}
	
	private static class OrderedField implements Comparable<OrderedField> {
		
		private final Class<?> type;
		
		private final Field javaField;
		
		private final CicsField cicsField;
		
		private final String fieldName;
		
		public OrderedField(Field javaField, CicsField cicsField) {
			this.javaField = javaField;
			this.cicsField = cicsField;
			
			String cicsFieldName = cicsField.fieldName();
			
			if (cicsFieldName.trim().isEmpty()) {
				cicsFieldName = javaField.getName();
			}
			this.fieldName = cicsFieldName;
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
			
			if (type == Integer.class) {
				return new IntegerFieldType(fieldName, fieldSize);
			} else if (type == String.class) {
				return new StringFieldType(fieldName, fieldSize);
			} else if (type == Long.class) {
				return new LongFieldType(fieldName, fieldSize);
			} else if (type == Date.class) {
				return new DateFieldType(fieldName, fieldSize, cicsField.pattern());
			} else if (type == Double.class) {
				int fieldDecimals = cicsField.decimals();
				return new DoubleFieldType(fieldName, fieldSize, fieldDecimals);
			} 
			return null;			
		}
	}
	
	
	public static <T> Program<T> construir(
			CTGProgramProvider provider, Class<T> classePrograma) {
		CicsProgram programAnnotation = classePrograma.getAnnotation(
				CicsProgram.class);
		
		Set<OrderedField> commonFieldIn = new TreeSet<>();
		Set<OrderedField> commonFieldOut = new TreeSet<>();
		
		Field[] fields = classePrograma.getFields();
		for (Field field : fields) {
			OrderedField orderField = mapField(field);
			
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
				programAnnotation.programName(), 
				programAnnotation.transactionName(), 
				programAnnotation.commLength(), 
				classePrograma,
				Collections.unmodifiableSet(commonFieldIn),
				Collections.unmodifiableSet(commonFieldOut));
		
		return program;
	}
	
	private static OrderedField mapField(Field javaField) {
		CicsField cicsField = javaField.getAnnotation(CicsField.class);
		
		if (cicsField == null) {
			return null;
		}

		return new OrderedField(javaField, cicsField);
	}
	
	public static <T> List<T> execute(Program<T> program, T vo) {
		Collection<OrderedField> fieldsIn = program.input;
		
		InputSet inputSet = program.getInputSet();
		
		for (OrderedField ofd : fieldsIn) {
			
			Class<?> type = ofd.javaField.getDeclaringClass();
			
			if (type == Integer.class) {
				int valor = ofd.javaField.getInt(vo);
				inputSet.setInteger(ofd.fieldName, valor);
			} else if (type == String.class) {
				String valor = (String) ofd.javaField.get(vo);
				inputSet.setString(ofd.fieldName, valor);
			} else if (type == Long.class) {
				Long valor = ofd.javaField.getLong(vo);
				inputSet.setLong(ofd.fieldName, valor);
			} else if (type == Double.class) {
				Double valor = ofd.javaField.getDouble(vo);
				inputSet.setDouble(ofd.fieldName, valor);
			} else if (type == Date.class) {
				Date valor = (Date) ofd.javaField.get(vo);
				inputSet.setDate(ofd.fieldName, valor);
			} else {
				continue;
			}
		}
		
		Collection<OrderedField> fieldsOut = program.output;
		
		ResultSet rs = program.execute();
		
		while (rs.next()) {
			
			for (OrderedField ofd : fieldsOut) {
				
				Class<?> type = ofd.javaField.getDeclaringClass();
				
				if (type == Integer.class) {
					ofd.javaField.set(vo, rs.getInteger(ofd.fieldName));
				} else if (type == String.class) {
					ofd.javaField.set(vo, rs.getString(ofd.fieldName));
				} else if (type == Long.class) {
					ofd.javaField.set(vo, rs.getString(ofd.fieldName));
					Long valor = ofd.javaField.getLong(vo);
					inputSet.setLong(ofd.fieldName, valor);
				} else if (type == Double.class) {
					Double valor = ofd.javaField.getDouble(vo);
					inputSet.setDouble(ofd.fieldName, valor);
				} else if (type == Date.class) {
					Date valor = (Date) ofd.javaField.get(vo);
					inputSet.setDate(ofd.fieldName, valor);
				} else {
					continue;
				}
				
				// FIXME Continuar desenvolviemnto
			}
			
		}
	}

}
