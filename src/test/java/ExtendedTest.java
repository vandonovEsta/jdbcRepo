import com.github.javafaker.Faker;
import daos.CustomerAddressesDao;
import daos.OrdersDao;
import drivers.DataBaseDriver;
import extendedDaos.CustomerOrdersDao;
import extendedDaos.CustomersWithAddressDao;
import extendedDaos.OrderedProductsDao;
import org.junit.jupiter.api.Test;
import pojos.customers.CustomerAddress;
import pojos.extended.CustomerWithAddress;
import pojos.extended.OrderedProducts;
import utils.Formatter;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtendedTest {

    @Test
    public void extendedCustomer() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CustomersWithAddressDao customersWithAddressDao = new CustomersWithAddressDao(5);
        System.out.println(customersWithAddressDao);

        customersWithAddressDao.saveAll();
    }


    @Test
    public void extendedOrderTest() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CustomerOrdersDao customerOrdersDao = new CustomerOrdersDao(5);

        System.out.println(customerOrdersDao.getCustomersOrders());

        customerOrdersDao.saveAll();
        System.out.println(customerOrdersDao.getCustomersOrders());

    }

    @Test
    public void getLatestOrderIdTest() throws SQLException {
        OrdersDao ordersDao = new OrdersDao();

        System.out.println(ordersDao.getLastPk());
    }

    @Test
    public void getOrderedProductsTest()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        OrderedProductsDao orderedProductsDao =
                new OrderedProductsDao(Faker.instance().number().numberBetween(2, 2));
        OrderedProducts orderedProducts = orderedProductsDao.getOrderedProducts();

        System.out.println(orderedProducts);
    }

    @Test
    public void saveAllTest()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        OrderedProductsDao orderedProductsDao =
                new OrderedProductsDao(Faker.instance().number().numberBetween(1, 15));
        orderedProductsDao.saveOrderedProducts();
    }


    @Test
    public void getAllCustomerWithAddressesTest() throws SQLException {
        CustomersWithAddressDao customers = new CustomersWithAddressDao();
        List<CustomerWithAddress> customerWithAddresses = customers.getFromDb();
        for (CustomerWithAddress cust :
                customerWithAddresses) {
            System.out.println(cust);
        }
    }

    @Test
    public void deleteAllTest() throws SQLException {
        OrderedProductsDao orderedProductsDao = new OrderedProductsDao();
        orderedProductsDao.truncateAll();
    }

    @Test
    public void getAllInQueryTest() throws SQLException {
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(6);
        String formattedIds = Formatter.listToString(ids);
        DataBaseDriver dataBaseDriver = DataBaseDriver.getInstance();
        System.out.println(formattedIds);
        List<CustomerAddress> customerAddresses = dataBaseDriver.getAllIn(customerAddressesDao.getTableName(),
                CustomerAddress.class, "address_id", formattedIds);
        System.out.println(customerAddresses);
    }

    @Test
    public void getAllCustomerAddressRecords() throws SQLException {
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        System.out.println(customerAddressesDao.getAllRecordsCount());
    }

    @Test
    public void getLatestPk() throws SQLException {
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        DataBaseDriver dataBaseDriver = DataBaseDriver.getInstance();
        int i = dataBaseDriver.getLastPk(customerAddressesDao.getTableName(), "address_id");
        System.out.println(i);
    }

}
