package mappers;

import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetMapper<T> {
    @SuppressWarnings("unchecked")
    public List<T> mapResultSetToObject(ResultSet resultSet, Class outputClass) {
        List<T> outputList = null;
        try {
            if (resultSet != null) {
                if (outputClass.isAnnotationPresent(Entity.class)) {
                    ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

                    Field[] fields = outputClass.getDeclaredFields();

                    while (resultSet.next()) {
                        T bean = (T) outputClass.newInstance();
                        for (int i = 0; i < resultSetMetaData.getColumnCount(); i++) {
                            String columnName = resultSetMetaData.getColumnName(i + 1);
                            Object columnValue = resultSet.getObject(i + 1);
                            for (Field field :
                                    fields) {
                                if (field.isAnnotationPresent(Column.class)) {
                                    Column column = field.getAnnotation(Column.class);
                                    if (column.name().equalsIgnoreCase(columnName)
                                            && columnValue != null) {
                                        BeanUtils.setProperty(bean, field.getName(), columnValue);
                                        break;
                                    }
                                }
                            }
                        }
                        if (outputList == null) {
                            outputList = new ArrayList<T>();
                        }
                        outputList.add(bean);
                    }
                } else {
                    throw new Exception("Class does not have the entity annotation");
                }
            } else {
                return null;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return outputList;
    }
}
