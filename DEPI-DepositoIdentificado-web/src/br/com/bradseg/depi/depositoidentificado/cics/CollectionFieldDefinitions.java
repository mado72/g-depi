/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics;

import java.beans.PropertyDescriptor;

import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;

/**
 * Classe para mapear campo estruturado repetitivo na definição para o CICS. 
 * @author Marcelo Damasceno
 */
public class CollectionFieldDefinitions extends FieldDefinitions {
	
	private final BookConfiguration innerConfiguration;
	private final int occurrences;

	/**
	 * Cria um mapa para campo de coleção
	 * @param pd Descritor da propriedade
	 * @param cicsField Campo CICS
	 * @param innerCommonArea Common Area definida para as estruturas que se repetem
	 * @param occurrences Quantidade de ocorrências
	 */
	public CollectionFieldDefinitions(PropertyDescriptor pd, CicsField cicsField,
			BookConfiguration innerCommonArea, int occurrences) {
		super(pd, cicsField, true);
		this.innerConfiguration = innerCommonArea;
		this.occurrences = occurrences;
	}

	/**
	 * Retorna innerCommonArea
	 * @return o innerCommonArea
	 */
	public BookConfiguration getInnerConfiguration() {
		return innerConfiguration;
	}

	/**
	 * Retorna occurrences
	 * @return o occurrences
	 */
	public int getOccurrences() {
		return occurrences;
	}
	
}