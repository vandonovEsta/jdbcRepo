package pojos.cuustomeraddresses;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pojos.customers.CustomerAddress;
import utils.fakers.CustomerAddressFaker;

import java.util.List;

public class CustomerAddressTests {

    @Test
    public void fakeCustomerAddressTest() {
        CustomerAddress customerAddress = CustomerAddressFaker.createFakeCustomerAddress();

        System.out.println(customerAddress);

        Assert.assertNotNull(customerAddress.getAddress());
        Assert.assertNotNull(customerAddress.getCity());
        Assert.assertNotNull(customerAddress.getId());
        Assert.assertNotNull(customerAddress.getCountry());
        Assert.assertNotNull(customerAddress.getProvince());
        Assert.assertNotNull(customerAddress.getState());
        Assert.assertNotNull(customerAddress.getPostalCode());
    }

    @Test
    public void listOfFakeCustomerAddressesTest() {
        Faker faker = new Faker();
        List<CustomerAddress> customerAddresses;
        int listLength = faker.number().numberBetween(2, 15);
        customerAddresses = CustomerAddressFaker.createFakeCustomerAddresses(listLength);

        for (CustomerAddress customerAddress :
                customerAddresses) {
            Long id = customerAddress.getId();
            Assert.assertNotNull("Address is null for customerAddress with id:" + id,
                    customerAddress.getAddress());
            Assert.assertNotNull("City is null for customerAddress with id:" + id,
                    customerAddress.getCity());
            Assert.assertNotNull("Province is null for customerAddress with id:" + id,
                    customerAddress.getProvince());
            Assert.assertNotNull("State is null for customerAddress with id:" + id,
                    customerAddress.getState());
            Assert.assertNotNull("Country is null for customerAddress with id:" + id,
                    customerAddress.getCountry());
            Assert.assertNotNull("Postal Code is null for customerAddress with id:" + id,
                    customerAddress.getPostalCode());
            System.out.println(customerAddress);
        }
    }
}
