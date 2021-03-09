/**
 * 
 */
package br.com.bradseg.depi.depositoidentificado.cics;

import static br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField.QUATRO;
import static br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField.Direction.In;
import static br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField.Direction.InOut;
import static br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField.Direction.Out;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import br.com.bradseg.depi.depositoidentificado.cics.CicsProgramDefinitionParser;
import br.com.bradseg.depi.depositoidentificado.cics.CollectionFieldDefinitions;
import br.com.bradseg.depi.depositoidentificado.cics.FieldDefinitions;
import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsField;
import br.com.bradseg.depi.depositoidentificado.cics.annotations.CicsProgram;

/**
 * Teste para analisadores das anotações CICS 
 * @author Marcelo Damasceno
 */
@SuppressWarnings("unused")
public class CicsProgramDefinitionParserTest {
	
	private static final String CAMPO_ENTRADA = "campoEntrada";
	private static final String CAMPO_ENTRADA_SAIDA = "campoEntradaSaida";
	private static final String CAMPO_SAIDA = "campoSaida";
	private static final String CAMPO_LISTA = "lista";

	@CicsProgram(programName="CS01", transactionName="T01", commLength=20)
	private static class ClasseSimples {
		
		@CicsField(order = 1, size = 4, pattern = QUATRO, direction = In)
		private Integer campoEntrada;
		
		@CicsField(order = 2, size = 6, direction = InOut)
		private String campoEntradaSaida;
		
		@CicsField(order = 3, size = 10, direction = Out)
		private String campoSaida;

		/**
		 * Retorna campoEntrada
		 * @return o campoEntrada
		 */
		public Integer getCampoEntrada() {
			return campoEntrada;
		}

		/**
		 * Define campoEntrada
		 * @param campoEntrada valor campoEntrada a ser definido
		 */
		public void setCampoEntrada(Integer campoEntrada) {
			this.campoEntrada = campoEntrada;
		}

		/**
		 * Retorna campoEntradaSaida
		 * @return o campoEntradaSaida
		 */
		public String getCampoEntradaSaida() {
			return campoEntradaSaida;
		}

		/**
		 * Define campoEntradaSaida
		 * @param campoEntradaSaida valor campoEntradaSaida a ser definido
		 */
		public void setCampoEntradaSaida(String campoEntradaSaida) {
			this.campoEntradaSaida = campoEntradaSaida;
		}

		/**
		 * Retorna campoSaida
		 * @return o campoSaida
		 */
		public String getCampoSaida() {
			return campoSaida;
		}

		/**
		 * Define campoSaida
		 * @param campoSaida valor campoSaida a ser definido
		 */
		public void setCampoSaida(String campoSaida) {
			this.campoSaida = campoSaida;
		}
		
	}
	
	private static class ClasseRepetitiva {

		@CicsField(order = 1, size = 5, pattern = "00000", direction = Out)
		private Integer chave;
		
		@CicsField(order = 2, size = 20, direction = Out)
		private String valor;

		/**
		 * Retorna chave
		 * @return o chave
		 */
		public Integer getChave() {
			return chave;
		}

		/**
		 * Define chave
		 * @param chave valor chave a ser definido
		 */
		public void setChave(Integer chave) {
			this.chave = chave;
		}

		/**
		 * Retorna valor
		 * @return o valor
		 */
		public String getValor() {
			return valor;
		}

		/**
		 * Define valor
		 * @param valor valor valor a ser definido
		 */
		public void setValor(String valor) {
			this.valor = valor;
		}
	}

	@CicsProgram(programName="CP02", transactionName="T02", commLength=20)
	private static class ClasseComposta {

		// OBS: Ordem dos campos alterada propositalmente
		@CicsField(order = 2, size = 6, direction = InOut)
		private String campoEntradaSaida;
		
		// OBS: Ordem dos campos alterada propositalmente
		@CicsField(order = 1, size = 4, pattern = QUATRO, direction = In)
		private Integer campoEntrada;
		
		@CicsField(order = 3, size = 10, direction = Out)
		private String campoSaida;
		
		@CicsField(order = 4, size = 100, direction = Out, occurrences = 5)
		private List<ClasseRepetitiva> lista;
	
		/**
		 * Retorna campoEntrada
		 * @return o campoEntrada
		 */
		public Integer getCampoEntrada() {
			return campoEntrada;
		}
	
		/**
		 * Define campoEntrada
		 * @param campoEntrada valor campoEntrada a ser definido
		 */
		public void setCampoEntrada(Integer campoEntrada) {
			this.campoEntrada = campoEntrada;
		}
	
		/**
		 * Retorna campoEntradaSaida
		 * @return o campoEntradaSaida
		 */
		public String getCampoEntradaSaida() {
			return campoEntradaSaida;
		}
	
		/**
		 * Define campoEntradaSaida
		 * @param campoEntradaSaida valor campoEntradaSaida a ser definido
		 */
		public void setCampoEntradaSaida(String campoEntradaSaida) {
			this.campoEntradaSaida = campoEntradaSaida;
		}
	
		/**
		 * Retorna campoSaida
		 * @return o campoSaida
		 */
		public String getCampoSaida() {
			return campoSaida;
		}
	
		/**
		 * Define campoSaida
		 * @param campoSaida valor campoSaida a ser definido
		 */
		public void setCampoSaida(String campoSaida) {
			this.campoSaida = campoSaida;
		}
		
		/**
		 * Retorna lista
		 * @return o lista
		 */
		public List<ClasseRepetitiva> getLista() {
			return lista;
		}
		
		/**
		 * Define lista
		 * @param lista valor lista a ser definido
		 */
		public void setLista(List<ClasseRepetitiva> lista) {
			this.lista = lista;
		}
		
	}

	@Test
	public void testParseField_simpleField() throws Exception {
		HashMap<String, PropertyDescriptor> mapDescriptors = obterDescritores(ClasseSimples.class);
		
		Field campoEntrada = ClasseSimples.class.getDeclaredField(CAMPO_ENTRADA_SAIDA);
		PropertyDescriptor pd = mapDescriptors.get(CAMPO_ENTRADA_SAIDA);
		
		CicsProgramDefinitionParser parser = new CicsProgramDefinitionParser();
		FieldDefinitions definition = parser.parseField(pd, campoEntrada);
		
		assertEquals(CAMPO_ENTRADA_SAIDA, definition.getFieldName());
		assertEquals(2, definition.getOrder());
		assertEquals(String.class, definition.getType());
	}
	
	@Test
	public void testParseField_listField() throws Exception {
		HashMap<String, PropertyDescriptor> mapDescriptors = obterDescritores(ClasseComposta.class);
		
		Field campoLista = ClasseComposta.class.getDeclaredField(CAMPO_LISTA);
		PropertyDescriptor pd = mapDescriptors.get(CAMPO_LISTA);
		
		CicsProgramDefinitionParser parser = new CicsProgramDefinitionParser();
		FieldDefinitions definition = parser.parseField(pd, campoLista);
		
		assertEquals(CAMPO_LISTA, definition.getFieldName());
		assertEquals(4, definition.getOrder());
		assertEquals(List.class, definition.getType());
		assertEquals(5, definition.getCicsField().occurrences());
		assertTrue(definition.isCollection());
	}

	/**
	 * Test method for {@link br.com.bradseg.depi.depositoidentificado.cics.CicsProgramDefinitionParser#parseBook(java.lang.Class)}.
	 */
	@Test
	public void testParseBook_classeSimples() {
		CicsProgramDefinitionParser parser = new CicsProgramDefinitionParser();
		BookConfiguration config = parser.parseBook(ClasseSimples.class);
		
		List<FieldDefinitions> fieldsIn = new ArrayList<>(config.getCommonFieldIn());
		assertEquals(2, fieldsIn.size());
		
		FieldDefinitions fieldCampoEntradaIn = fieldsIn.get(0);
		assertEquals(CAMPO_ENTRADA, fieldCampoEntradaIn.getFieldName());
		assertEquals(1, fieldCampoEntradaIn.getOrder());
		
		FieldDefinitions fieldCampoEntradaSaidaIn = fieldsIn.get(1);
		assertEquals(CAMPO_ENTRADA_SAIDA, fieldCampoEntradaSaidaIn.getFieldName());
		assertEquals(2, fieldCampoEntradaSaidaIn.getOrder());
		
		List<FieldDefinitions> fieldsOut = new ArrayList<>(config.getCommonFieldOut());
		assertEquals(2, fieldsOut.size());
		
		FieldDefinitions fieldCampoEntradaSaidaOut = fieldsOut.get(0);
		assertEquals(CAMPO_ENTRADA_SAIDA, fieldCampoEntradaSaidaOut.getFieldName());
		assertEquals(2, fieldCampoEntradaSaidaOut.getOrder());
		
		FieldDefinitions fieldCampoSaidaOut = fieldsOut.get(1);
		assertEquals(CAMPO_SAIDA, fieldCampoSaidaOut.getFieldName());
		assertEquals(3, fieldCampoSaidaOut.getOrder());
	}
	
	@Test
	public void testParseBook_classeComposta() {
		CicsProgramDefinitionParser parser = new CicsProgramDefinitionParser();
		BookConfiguration config = parser.parseBook(ClasseComposta.class);
		
		List<FieldDefinitions> fieldsIn = new ArrayList<>(config.getCommonFieldIn());
		assertEquals(2, fieldsIn.size());
		
		FieldDefinitions fieldCampoEntradaIn = fieldsIn.get(0);
		assertEquals(CAMPO_ENTRADA, fieldCampoEntradaIn.getFieldName());
		assertEquals(1, fieldCampoEntradaIn.getOrder());
		
		FieldDefinitions fieldCampoEntradaSaidaIn = fieldsIn.get(1);
		assertEquals(CAMPO_ENTRADA_SAIDA, fieldCampoEntradaSaidaIn.getFieldName());
		assertEquals(2, fieldCampoEntradaSaidaIn.getOrder());
		
		List<FieldDefinitions> fieldsOut = new ArrayList<>(config.getCommonFieldOut());
		assertEquals(3, fieldsOut.size());
		
		FieldDefinitions fieldCampoEntradaSaidaOut = fieldsOut.get(0);
		assertEquals(CAMPO_ENTRADA_SAIDA, fieldCampoEntradaSaidaOut.getFieldName());
		assertEquals(2, fieldCampoEntradaSaidaOut.getOrder());
		
		FieldDefinitions fieldCampoSaidaOut = fieldsOut.get(1);
		assertEquals(CAMPO_SAIDA, fieldCampoSaidaOut.getFieldName());
		assertEquals(3, fieldCampoSaidaOut.getOrder());
		
		CollectionFieldDefinitions fieldLista = (CollectionFieldDefinitions) fieldsOut.get(2);
		assertEquals(4, fieldLista.getOrder());
		assertEquals(5, fieldLista.getOccurrences());
		
		BookConfiguration innerConfiguration = fieldLista.getInnerConfiguration();
		assertEquals(ClasseRepetitiva.class, innerConfiguration.getBook());
		assertEquals(0, innerConfiguration.getCommonFieldIn().size());
		
		List<FieldDefinitions> innerFieldOut = new ArrayList<>(innerConfiguration.getCommonFieldOut());
		assertEquals(2, innerFieldOut.size());

		FieldDefinitions fieldChave = innerFieldOut.get(0);
		assertEquals("chave", fieldChave.getFieldName());
		assertEquals(1, fieldChave.getOrder());
		
		FieldDefinitions fieldValor = innerFieldOut.get(1);
		assertEquals("valor", fieldValor.getFieldName());
		assertEquals(2, fieldValor.getOrder());
	}

	private HashMap<String, PropertyDescriptor> obterDescritores(Class<?> clazz)
			throws IntrospectionException {
		BeanInfo info = Introspector.getBeanInfo(clazz);
		
		HashMap<String, PropertyDescriptor> mapDescriptors = new HashMap<String, PropertyDescriptor>();
		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			mapDescriptors.put(pd.getDisplayName(), pd);
		}
		return mapDescriptors;
	}

}
