package db.tests;

import com.github.javafaker.Faker;
import daos.CustomerAddressesDao;
import daos.CustomerDao;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import pojos.customers.Customer;
import pojos.customers.CustomerAddress;
import utils.fakers.CustomerAddressFaker;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CustomerAddressDbTests {

    @BeforeEach
    public void init() throws SQLException {
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        Assert.assertTrue("Table is empty", customerAddressesDao.getAll().size() > 0);
    }

    @Test
    public void getCustomersAndValidateCorrectAddressesTest() throws Exception {
        CustomerDao customerDao = new CustomerDao();
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        List<Integer> addressIds = new ArrayList<>();
        List<CustomerAddress> customerAddresses = new ArrayList<>();

        List<Integer> customerIds =
                customerDao.getRandomIds(Faker.instance().number().numberBetween(1, 5));
        List<Customer> customers = customerDao.getByIds(customerIds);

        customers.forEach(customer -> {
            addressIds.add(customer.getAddressId());
        });

        customerAddresses = customerAddressesDao.getByIds(addressIds);

        for (CustomerAddress customerAddress :
                customerAddresses) {
            assertNotNull(customerAddress.getId());
            assertNotNull(customerAddress.getCity());
            assertNotNull(customerAddress.getPostalCode());
            assertNotNull(customerAddress.getCountry());
        }
    }

    @Test
    public void addressMandatoryFieldsTest() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        CustomerAddress customerAddress = CustomerAddressFaker.createFakeCustomerAddress();

        customerAddress.setId(null);
        customerAddress.setCity(null);
        customerAddress.setPostalCode(null);
        customerAddress.setCountry(null);


        Assert.assertThrows(PSQLException.class, () -> customerAddressesDao.save(customerAddress));

    }
}
