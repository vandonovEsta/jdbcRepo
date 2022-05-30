package drivers;

import helpers.SingleConnectionHelper;
import mappers.ResultSetMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.Column;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.SqlQueries.*;

public class DataBaseDriver implements AutoCloseable {

    private static ResultSetMapper resultSetMapper;
    private static DataBaseDriver instance;
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;

    static {
        try {
            instance = new DataBaseDriver();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private SingleConnectionHelper singleConnectionHelper;

    private DataBaseDriver() throws SQLException, IOException {
        singleConnectionHelper = SingleConnectionHelper.getInstance();
        connection = singleConnectionHelper.getConnection();
        resultSetMapper = new ResultSetMapper();
    }

    public static DataBaseDriver getInstance() {
        return instance;
    }

    public <T> List<T> getResult(String query, Class<T> object) throws SQLException {
        preparedStatement = connection.prepareStatement(query);
        return returnList(object);
    }

    public <T> List<T> getAllEntities(String tableName, Class<T> object) throws SQLException {

        preparedStatement = connection.prepareStatement(SELECT_ALL + tableName);
        return returnList(object);
    }

    private <T> List<T> returnList(Class<T> object) throws SQLException {
        resultSet = preparedStatement.executeQuery();
        return resultSetMapper.mapResultSetToObject(resultSet, object);
    }

    public <T> List<T> getAllEqualTo(String tableName, Class<T> object, String columnName, String equalTo)
            throws SQLException {
        preparedStatement = connection
                .prepareStatement(SELECT_ALL + tableName + String.format(WHERE_IS_EQUAL_TO, columnName, equalTo));
        return returnList(object);
    }

    public Integer countBy(String tableName, String columnName) throws SQLException {
        Integer result = null;
        preparedStatement = connection
                .prepareStatement(String.format(COUNT_BY, columnName) + tableName);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            result = resultSet.getInt(1);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing result set");
        resultSet.close();
        System.out.println("Closing prepared statement");
        preparedStatement.close();
        System.out.println("Closing connection");
        connection.close();
    }

    public void deleteBy(String tableName, String columnName, int value) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format(DELETE_BY, tableName, columnName, value));
        preparedStatement.executeUpdate();
    }

    public void deleteAll(String tableName) throws SQLException {
        preparedStatement = connection.prepareStatement(String.format(DELETE_ALL, tableName));
        preparedStatement.executeUpdate();
    }

    public void insetToTable(String table, Object obj)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
//        preparedStatement.executeUpdate();

        List<String> columnNames = new ArrayList<>();
        String formattedNames;
        String columnValues = "";
        String finalizedQuery;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field :
                fields) {
            Column column = field.getAnnotation(Column.class);
            columnNames.add(column.name());
            Object value = BeanUtils.getProperty(obj, field.getName());
            if (value instanceof String) {
                if (columnValues.length() < 1) {
                    columnValues += "'" + value + "'";
                } else {
                    columnValues += ", '" + value + "'";
                }
            } else if (value == null) {
                if (columnValues.length() < 1) {
                    columnValues += "null";
                } else {
                    columnValues += ", " + "null";
                }

            } else {
                if (columnValues.length() < 1) {
                    columnValues += value;
                } else {
                    columnValues += ", " + value;
                }
            }
        }

        formattedNames = columnNames
                .toString().replace("[", "").replace("]", "").trim();
        finalizedQuery = String.format(INSERT_INTO_TABLE, table, formattedNames, columnValues);

        System.out.println(finalizedQuery);
        preparedStatement = connection
                .prepareStatement(finalizedQuery);
        preparedStatement.executeUpdate();

    }
}
