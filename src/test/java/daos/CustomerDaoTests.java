package daos;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojos.customers.Customer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerDaoTests {

    @Test
    public void getByIdsTest() throws SQLException {
        List<Integer> ids = new ArrayList<>();
        List<Customer> customers;
        ids.add(7);
        ids.add(12);
        CustomerDao customerDao = new CustomerDao();
        customers = customerDao.getByIds(ids);
        for (Customer customer :
                customers) {
            System.out.println(customer);
        }
        Assert.assertTrue(customers.size() > 0);
    }

    @Test
    public void getByIdTest() throws SQLException {
        int id = 7;
        Customer customer;
        CustomerDao customerDao = new CustomerDao();
        customer = customerDao.getById(id);
        System.out.println(customer);
        Assert.assertNotNull(customer);
    }

    @Test
    public void getRandomIdTest() throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        Integer id = null;
        id = customerDao.getRandomId();
        Assert.assertNotNull(id);
    }

    @Test
    public void getRandomIdsTest() throws Exception {
        CustomerDao customerDao = new CustomerDao();
        int numberOfIdsRequired = 7;
        List<Integer> ids;
        ids = customerDao.getRandomIds(numberOfIdsRequired);
        System.out.println(ids);
        Assert.assertEquals(numberOfIdsRequired, ids.size());
    }

    @Test
    public void saveCustomersTest() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Customer customer = new Customer();
        CustomerDao customerDao = new CustomerDao();
        customer.setId(111);
        customer.setName("Bob");
        customer.setEmail("Bob@Bob.bob");
        customer.setPhone("+359000");
        customer.setGdpr(true);
        customer.setDateTimeCreated(new Date());
        customer.setAddressId(28);
        customerDao.save(customer);
    }

    @Test
    public void deleteByIdTest() throws SQLException {
        Customer customer;
        CustomerDao customerDao = new CustomerDao();
        customer = customerDao.getById(111);
        customerDao.delete(customer);
    }
}
