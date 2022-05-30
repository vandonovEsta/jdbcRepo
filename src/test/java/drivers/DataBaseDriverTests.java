package drivers;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojos.customers.Customer;

import java.util.List;

import static constants.SqlQueries.SELECT_ALL_FROM_CUSTOMERS;

public class DataBaseDriverTests {
    @Test
    public void autoClosableTest() {
        try (DataBaseDriver dataBaseDriver = DataBaseDriver.getInstance()) {
            List<Customer> customers = dataBaseDriver.getResult(SELECT_ALL_FROM_CUSTOMERS, Customer.class);
            System.out.println(customers);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllEqualToTest() {
        try (DataBaseDriver dataBaseDriver = DataBaseDriver.getInstance()) {
            List<Customer> customers = dataBaseDriver.
                    getAllEqualTo("customers", Customer.class, "name", "John");

            System.out.println(customers);
            Assert.assertTrue(customers.size() > 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void countByTest() {
        try (DataBaseDriver dataBaseDriver = DataBaseDriver.getInstance()) {
            dataBaseDriver.countBy("customers", "customer_id");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
