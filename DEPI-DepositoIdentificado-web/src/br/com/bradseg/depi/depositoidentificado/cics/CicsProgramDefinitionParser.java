/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;
import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsProgram;
import br.com.bradseg.depi.depositoidentificado.exception.DEPIIntegrationException;

/**
 * Classe utilizada por {@link CicsProgram} para analisar definições de comunicação ao CICS
 * e preparar o programa para ser chamado.  
 * @author Marcelo Damasceno
 */
public class CicsProgramDefinitionParser {

	final static Logger LOGGER = LoggerFactory.getLogger(CicsProgramDefinitionParser.class);
	
	/**
	 * Mapeia a área comum definida para uma classe com anotações CICS
	 * @param book Classe a ser analisada
	 * @return Resultado da análise
	 */
	protected <E> BookConfiguration parseBook(Class<E> book) {
		BeanInfo beanInfo;
		try {
			beanInfo = Introspector.getBeanInfo(book);
		} catch (IntrospectionException e) {
			throw new DEPIIntegrationException(e);
		}
		
		Map<String, Field> mapPropField = new HashMap<>();
		for (Field field : book.getDeclaredFields()) {
			mapPropField.put(field.getName(), field);
		}
		
		BookConfiguration commonArea = new BookConfiguration(book);
		
		for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
			Field javaField = mapPropField.get(pd.getName());
			FieldDefinitions orderedField = parseField(pd, javaField);
			
			if (orderedField == null) {
				continue;
			}
	
			commonArea.addField(orderedField);
		}
		return commonArea;
	}

	/**
	 * Analisa a definição para comunicação CICS em campo, caso possua uma. Se o
	 * campo não possuir uma definição, retorna nulo.
	 * 
	 * @param pd
	 *            Descritor da propriedade da classe
	 * @param javaField
	 *            Tipo do campo
	 * @return Definição para leitura ou escrita ao CICS.
	 */
	protected FieldDefinitions parseField(PropertyDescriptor pd, Field javaField) {
		if (javaField == null) {
			return null;
		}
		
		CicsField cicsField = javaField.getAnnotation(CicsField.class);
		
		if (cicsField == null) {
			return null;
		}
	
		Class<?> propertyType = pd.getPropertyType();
		boolean isCollection = Collection.class.isAssignableFrom(propertyType)
				|| propertyType.isAssignableFrom(Object[].class);
		
		if (! isCollection) {
			return new FieldDefinitions(pd, cicsField, false);
		}
		
		Class<?> collectionType = (Class<?>) ((ParameterizedType) javaField
				.getGenericType()).getActualTypeArguments()[0];
		
		BookConfiguration collectionCommonArea = parseBook(collectionType);
		int occurrences = cicsField.occurrences();
		
		return new CollectionFieldDefinitions(pd, cicsField, collectionCommonArea,
				occurrences);
	}

}
