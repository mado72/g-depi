package br.com.bradseg.depi.depositoidentificado.model.book;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import br.com.bradseg.depi.dp06.exception.DEPIIntegrationException;
import br.com.bradseg.depi.dp06.model.util.BaseUtil;
import br.com.bradseg.depi.dp06.model.util.ConstantesModel;

/**
 * Classe base para todos os books.
 * @author Fabrica de Salvador
 */
public class BaseBook implements Serializable {

    private static final long serialVersionUID = -2749478984796609195L;

    private static final Logger LOGGER = Logger.getLogger(BaseBook.class);

    public static final String QUATRO = "0000";
    public static final String NOVE = "000000000";

    private static PropertyResourceBundle property = BaseUtil.getInstance().getResources();

    /**
     * setCodigoRetorno
     * @param codigoRetorno codigoRetorno
     * @throws DEPIIntegrationException - Qualquer erro.
     */
    public void verificaRetorno(int codigoRetorno) throws DEPIIntegrationException {
        switch (codigoRetorno) {
            case 1:
                throw new DEPIIntegrationException(property.getString(ConstantesModel.ERRO_CICS_TPERRO_01));
            case 2:
                throw new DEPIIntegrationException(property.getString(ConstantesModel.ERRO_CICS_TPERRO_02));
            case 3:
                throw new DEPIIntegrationException(property.getString(ConstantesModel.ERRO_CICS_TPERRO_03));
            case 4:
                throw new DEPIIntegrationException(property.getString(ConstantesModel.ERRO_CICS_TPERRO_04));
            default:
                break;
        }
    }

    /**
     * Retorna o property.
     * @return O atributo property
     */
    public static PropertyResourceBundle getProperty() {
        return property;
    }

    /**
     * Especifica o property.
     * @param property PropertyResourceBundle do property a ser setado
     */
    public static void setProperty(PropertyResourceBundle property) {
        BaseBook.property = property;
    }

    /**
     * Verifica se o objeto est� vazio.
     * @return true caso o objeto esteja vazio.
     */

    private boolean isEmpty() {
        Field[] fields = this.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Class[] param = null;
            String method = "get" + StringUtils.capitalize(field.getName());
            try {
                Method m = this.getClass().getDeclaredMethod(method, param);
                if ("java.lang.String".equalsIgnoreCase(field.getType().getName()) 
                    && !StringUtils.isEmpty((String) m.invoke(this))) {
                    return false;
                } else if ("java.lang.Long".equalsIgnoreCase(field.getType().getName()) && !((Long) m.invoke(this)).equals(0L)) {
                    return false;
                } else if ("java.sql.Date".equalsIgnoreCase(field.getType().getName()) && ((Date) m.invoke(this)) != null) {
                    return false;
                } else if ("java.lang.Double".equalsIgnoreCase(field.getType().getName()) 
                    && !((Double) m.invoke(this)).equals(0D)) {
                    return false;
                }
            } catch (NoSuchMethodException e) {
                LOGGER.fatal(e.getMessage());
            } catch (IllegalArgumentException e) {
                LOGGER.fatal(e.getMessage());
            } catch (IllegalAccessException e) {
                LOGGER.fatal(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.fatal(e.getMessage());
            }
        }
        return true;
    }

    /**
     * Remove os objetos vazios da lista. Os Objetos da listas devem extender de BookBase.
     */

    public void removerObjetosVazios() {
        Field[] fields = this.getClass().getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Class[] param = null;
            String method = "get" + StringUtils.capitalize(field.getName());
            try {
                Method m = this.getClass().getDeclaredMethod(method, param);
                if ("java.util.List".equalsIgnoreCase(field.getType().getName())) {
                    for (Iterator iter = ((List) m.invoke(this)).iterator(); iter.hasNext();) {
                        BaseBook book = (BaseBook) iter.next();
                        if (book.isEmpty()) {
                            iter.remove();
                        }
                    }
                }
            } catch (NoSuchMethodException e) {
                LOGGER.fatal(e.getMessage());
            } catch (IllegalArgumentException e) {
                LOGGER.fatal(e.getMessage());
            } catch (IllegalAccessException e) {
                LOGGER.fatal(e.getMessage());
            } catch (InvocationTargetException e) {
                LOGGER.fatal(e.getMessage());
            }
        }
    }

}
