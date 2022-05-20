package jdbsProj;

import helpers.DbHelper;
import lombok.Cleanup;
import mappers.ResultSetMapper;
import pojos.Customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static constants.SqlQueries.SELECT_ALL_FROM_CUSTOMERS_GET_ONLY_FIRST_ROW;

public class CustomerCreator {
    public static void main(String[] args) {
        try {
            ResultSetMapper<Customer> resultSetMapper = new ResultSetMapper<Customer>();
            ResultSet resultSet = null;
            DbHelper dbHelper = DbHelper.getInstance();
            @Cleanup Connection connection = dbHelper.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_CUSTOMERS_GET_ONLY_FIRST_ROW);
            resultSet = statement.executeQuery();
            List<Customer> customers = resultSetMapper.mapResultSetToObject(resultSet, Customer.class);

            if (customers != null) {
                for (Customer customer :
                        customers) {
                    System.out.println(customer);
                }
            } else {
                System.out.println("Was not able to extract customers");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
