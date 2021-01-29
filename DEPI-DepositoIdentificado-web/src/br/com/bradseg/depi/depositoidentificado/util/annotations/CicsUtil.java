/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import br.com.bradseg.bsad.framework.ctg.programapi.field.AbstractFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DoubleFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.FieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IntegerFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.LongFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.StringFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramImpl;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramProvider;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CommonAreaMetaData;

/**
 * Classe utilitária para analisar as anotações e fazer as chamadas CICS 
 * @author Marcelo Damasceno
 */
public class CicsUtil {
	
	public final static class Program<T> extends CTGProgramImpl {
		
		private transient final Class<T> programType;
		
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
		}

		private static FieldType[] extractFieldTypeArray(
				Collection<OrderedField> commonField) {
			
			FieldType[] input = new FieldType[commonField.size()];
			
			int index = 0;
			for (OrderedField orderField : commonField) {
				input[index++] = orderField.getFieldType();
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
		
		private final AbstractFieldType fieldType;
		
		private final CicsField cicsField;
		
		public OrderedField(CicsField cicsField, AbstractFieldType fieldType) {
			this.fieldType = fieldType;
			this.cicsField = cicsField;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object)
		 */
		@Override
		public int compareTo(OrderedField o) {
			return cicsField.order() - o.cicsField.order();
		}
		
		/**
		 * Retorna fieldType
		 * @return o fieldType
		 */
		public AbstractFieldType getFieldType() {
			return fieldType;
		}
		
		/**
		 * Retorna direction
		 * @return o direction
		 */
		public CicsField.Direction getDirection() {
			return cicsField.direction();
		}
	}
	
	
	public static <T> Program<T> construir(
			CTGProgramProvider provider, Class<T> classePrograma) {
		CicsProgram programAnnotation = classePrograma.getAnnotation(
				CicsProgram.class);
		
		TreeSet<OrderedField> commonFieldIn = new TreeSet<>();
		TreeSet<OrderedField> commonFieldOut = new TreeSet<>();
		
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
				commonFieldIn,
				commonFieldOut);
		
		return program;
	}
	
	private static OrderedField mapField(Field field) {
		CicsField cicsField = field.getAnnotation(CicsField.class);
		
		if (cicsField == null) {
			return null;
		}

		OrderedField orderField;
		
		String cicsFieldName = cicsField.fieldName();
		if (cicsFieldName.length() == 0) {
			cicsFieldName = field.getName();
		}
		int fieldSize = cicsField.size();
		int fieldDecimals = cicsField.decimals();

		if (field.getDeclaringClass() == Integer.class) {
			orderField = new OrderedField(cicsField, new IntegerFieldType(
					cicsFieldName, fieldSize));
		} else if (field.getDeclaringClass() == String.class) {
			orderField = new OrderedField(cicsField, new StringFieldType(
					cicsFieldName, fieldSize));
		} else if (field.getDeclaringClass() == Long.class) {
			orderField = new OrderedField(cicsField, new LongFieldType(
					cicsFieldName, fieldSize));
		} else if (field.getDeclaringClass() == Double.class) {
			orderField = new OrderedField(cicsField, new DoubleFieldType(
					cicsFieldName, fieldSize, fieldDecimals));
		} else {
			orderField = null;
		}
		
		return orderField;
	}
	
	public static <T> List<T> execute(Program<T> program) {
		// FIXME
		throw new UnsupportedOperationException();
	}

}
