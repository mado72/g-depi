/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.util.annotations;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * Classe usada para mapear os campos marcados com tags {@link CicsField}  
 */
public class FieldDefinitions {
	
	private final PropertyDescriptor pd;
	
	private final Class<?> type;
	
	private final CicsField cicsField;
	
	private final Method getter;
	
	private final Method setter;
	
	private final String fieldName;
	
	private final boolean collection;
	
	private int order;
	
	public FieldDefinitions(PropertyDescriptor pd, CicsField cicsField,
			boolean collection) {
		this.pd = pd;
		this.cicsField = cicsField;
		
		String cicsFieldName = cicsField.fieldName();
		
		if (cicsFieldName.trim().isEmpty()) {
			cicsFieldName = pd.getName();
		}
		this.fieldName = cicsFieldName;
		this.type = pd.getPropertyType();
		this.getter = pd.getReadMethod();
		this.setter = pd.getWriteMethod();
		this.order = cicsField.order();
		this.collection = collection;
	}

	/**
	 * Retorna order
	 * @return o order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * Define order
	 * @param order valor order a ser definido
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * Retorna pd
	 * @return o pd
	 */
	public PropertyDescriptor getPd() {
		return pd;
	}

	/**
	 * Retorna type
	 * @return o type
	 */
	public Class<?> getType() {
		return type;
	}

	/**
	 * Retorna cicsField
	 * @return o cicsField
	 */
	public CicsField getCicsField() {
		return cicsField;
	}

	/**
	 * Retorna getter
	 * @return o getter
	 */
	public Method getGetter() {
		return getter;
	}

	/**
	 * Retorna setter
	 * @return o setter
	 */
	public Method getSetter() {
		return setter;
	}

	/**
	 * Retorna fieldName
	 * @return o fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * Retorna collection
	 * @return o collection
	 */
	public boolean isCollection() {
		return collection;
	}
}