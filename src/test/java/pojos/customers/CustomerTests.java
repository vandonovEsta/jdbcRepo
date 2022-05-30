package pojos.customers;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utils.fakers.CustomerFaker;

import java.util.List;

public class CustomerTests {
    @Test
    public void fakeCustomerTest() {
        Customer customer;
        customer = CustomerFaker.createFakeCustomer();

        System.out.println(customer);
        Assert.assertNotNull(customer);
    }

    @Test
    public void fakeListOfCustomersTest() {
        List<Customer> customerList;
        customerList = CustomerFaker.createFakeCustomers(Faker.instance().number().numberBetween(2, 15));
        for (Customer customer :
                customerList) {
            System.out.println(customer);
            Assert.assertNotNull(customer);
        }
    }
}
