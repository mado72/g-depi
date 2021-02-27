/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import br.com.bradseg.bsad.framework.ctg.programapi.field.AbstractFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DateFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.DoubleFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.FieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.IntegerFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.LongFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.field.StringFieldType;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CTGProgramImpl;
import br.com.bradseg.bsad.framework.ctg.programapi.program.CommonAreaMetaData;
import br.com.bradseg.bsad.framework.ctg.programapi.support.gateway.CTGJavaGateway;

/**
 * Armazena as definições do book CICS 
 *
 * @param <T> Classe que contém as definições do book
 */
public final class Program<T> extends CTGProgramImpl {
	
	transient final Class<T> programType;
	final Collection<FieldDefinitions> output;
	final Collection<FieldDefinitions> input;
	
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
	protected Program(CTGJavaGateway gateway, String pPgmName,
			String pTranName, int pCommonAreaSize, Class<T> programType,
			Collection<FieldDefinitions> input, Collection<FieldDefinitions> output) {
		
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
			Collection<FieldDefinitions> source) {
		
		ArrayList<FieldType> fields = new ArrayList<>();
		
		for (FieldDefinitions ofd : source) {
			fields.add(createFieldType(ofd));
		}
		
		return fields.toArray(new FieldType[0]);
	}

	public static AbstractFieldType createFieldType(FieldDefinitions field) {
		
		final CicsField cicsField = field.getCicsField();
		final int fieldSize = cicsField.size();
		final Class<?> type = field.getType();
		final String fieldName = field.getFieldName();
		
		if (type.equals(Integer.class) || type.equals(int.class)) {
			CicsProgramDefinitionParser.LOGGER.trace("Criando campo Integer {} ({})", fieldName, fieldSize);
			return new IntegerFieldType(fieldName, fieldSize);
		} else if (type.equals(String.class)) {
			CicsProgramDefinitionParser.LOGGER.trace("Criando campo String {} ({})", fieldName, fieldSize);
			return new StringFieldType(fieldName, fieldSize);
		} else if (type.equals(Long.class) || type.equals(long.class)) {
			CicsProgramDefinitionParser.LOGGER.trace("Criando campo Long {} ({})", fieldName, fieldSize);
			return new LongFieldType(fieldName, fieldSize);
		} else if (Date.class.isAssignableFrom(type)) {
			CicsProgramDefinitionParser.LOGGER.trace("Criando campo Date {} ({})", fieldName, fieldSize);
			return new DateFieldType(fieldName, fieldSize, cicsField.pattern());
		} else if (type.equals(Double.class) || type.equals(double.class)) {
			CicsProgramDefinitionParser.LOGGER.trace("Criando campo Double {} ({})", fieldName, fieldSize);
			int fieldDecimals = cicsField.decimals();
			return new DoubleFieldType(fieldName, fieldSize, fieldDecimals);
		}
		CicsProgramDefinitionParser.LOGGER.warn(
				"N\u00E3o criou campo {} ({}): tipo {} n\u00E3o suportado.",
				fieldName, fieldSize, type);
		return null;			
	}

}