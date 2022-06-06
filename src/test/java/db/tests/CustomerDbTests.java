package db.tests;

import daos.CustomerDao;
import extendedDaos.OrderedProductsDao;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import pojos.customers.Customer;
import utils.fakers.CustomerFaker;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CustomerDbTests {

    @BeforeEach
    public void init() throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        Assert.assertTrue("Table is empty", customerDao.getAll().size() > 0);
    }

    @Test
    public void noCustomersWithoutAddressTest() throws SQLException {
        List<Customer> customers;
        CustomerDao customerDao = new CustomerDao();

        customers = customerDao.getAll();
        for (Customer customer :
                customers) {
            System.out.println(customer);
            assertNotNull(customer.getAddressId());
        }
    }

    @Test
    public void noDuplicateMailOrPhoneTest() throws SQLException {
        List<Customer> customers;
        CustomerDao customerDao = new CustomerDao();

        customers = customerDao.getAll();
        List<String> phones = new ArrayList<>();
        List<String> mails = new ArrayList<>();

        for (Customer customer :
                customers) {
            Assert.assertFalse(phones.contains(customer.getPhone()));
            phones.add(customer.getPhone());
            Assert.assertFalse(mails.contains(customer.getEmail()));
            mails.add(customer.getEmail());
        }
    }

    @Test
    public void createCustomerTest()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CustomerDao customerDao = new CustomerDao();

        //OrderedProductsDao creates a customer, with address, with order and products in the order.
        OrderedProductsDao orderedProductsDao =
                new OrderedProductsDao(1);

        //To get the customer Id I take the combined product "ordered products"
        // that contains orders and customer with addresses from there I extract the customer and then take it's Id
        int customerId = orderedProductsDao
                .getOrderedProducts()
                .getCustomerOrders().get(0)
                .getCustomer().getCustomer().getId();
        orderedProductsDao.saveOrderedProducts();

        Customer customerFromDb = customerDao.getById(customerId);
        System.out.println(customerFromDb);
        assertNotNull(customerFromDb);
    }

    @Test
    public void customerRequiredFieldsTest() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Customer customer = CustomerFaker.createFakeCustomer();
        CustomerDao customerDao = new CustomerDao();

        customer.setId(null);
        customer.setDateTimeCreated(null);
        customer.setEmail(null);
        customer.setPhone(null);

        Assert.assertThrows(PSQLException.class, () -> customerDao.save(customer));

    }
}
