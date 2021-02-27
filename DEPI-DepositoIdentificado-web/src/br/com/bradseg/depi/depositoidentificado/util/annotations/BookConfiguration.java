/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.bradseg.depi.depositoidentificado.util.annotations.CicsField.Direction;

/**
 * Classe utilizada para mapear a área comum 
 */
final class BookConfiguration {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(BookConfiguration.class);

	private final Class<?> book;
	
	/**
	 * Construtor 
	 * @param book A classe mapeada
	 */
	protected BookConfiguration(Class<?> book) {
		super();
		this.book = book;
	}

	private final List<FieldDefinitions> fieldList = new ArrayList<>();
	
	private final static Comparator<FieldDefinitions> comparator = new Comparator<FieldDefinitions>() {
		
		@Override
		public int compare(FieldDefinitions o1, FieldDefinitions o2) {
			return o1.getOrder() - o2.getOrder();
		}
	};
	
	protected void addField(FieldDefinitions field) {
		LOGGER.debug(
				"Adicionando campo com as defini\u00E7\u00F5es: campo {} ({}), ordem: {}, tipo: {}",
				field.getFieldName(), field.getCicsField().direction(), field.getOrder(), field.getType());
		this.fieldList.add(field);
	}
	
	protected Collection<FieldDefinitions> getCommonFieldIn() {
		TreeSet<FieldDefinitions> fields = new TreeSet<>(comparator);
		for (FieldDefinitions field : fieldList) {
			Direction direction = field.getCicsField().direction();
			if (direction == Direction.In || direction == Direction.InOut) {
				fields.add(field);
			}
		}
		return fields;
	}

	protected Collection<FieldDefinitions> getCommonFieldOut() {
		TreeSet<FieldDefinitions> fields = new TreeSet<>(comparator);
		for (FieldDefinitions field : fieldList) {
			Direction direction = field.getCicsField().direction();
			if (direction == Direction.Out || direction == Direction.InOut) {
				fields.add(field);
			}
		}
		return fields;
	}
	
	/**
	 * Retorna classe do book
	 * @return a classe
	 */
	public Class<?> getBook() {
		return book;
	}

	/**
	 * Retorna fieldList
	 * @return o fieldList
	 */
	public List<FieldDefinitions> getFieldList() {
		return fieldList;
	}

}