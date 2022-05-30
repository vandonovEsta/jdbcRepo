package utils.fakers;

import com.github.javafaker.Faker;
import pojos.customers.CustomerAddress;

import java.util.ArrayList;
import java.util.List;

public class CustomerAddressFaker {
    public static CustomerAddress createFakeCustomerAddress() {
        Faker faker = new Faker();
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setAddress(faker.address().streetAddress());
        customerAddress.setCity(faker.address().city());
        customerAddress.setId(faker.number().randomNumber());
        customerAddress.setCountry(faker.address().country());
        customerAddress.setProvince(faker.address().cityPrefix());
        customerAddress.setState(faker.address().state());
        customerAddress.setPostalCode(Integer.valueOf(faker.numerify("####")));

        return customerAddress;
    }

    public static List<CustomerAddress> createFakeCustomerAddresses(int numberOfAddresses) {
        List<CustomerAddress> customerAddressList = new ArrayList<>();
        for (int i = 0; i < numberOfAddresses; i++) {
            customerAddressList.add(createFakeCustomerAddress());
        }
        return customerAddressList;
    }

    public static List<CustomerAddress> createFakeCustomerAddresses() {
        int numberOfCustomers = Faker.instance().number().numberBetween(2, 15);
        return createFakeCustomerAddresses(numberOfCustomers);
    }
}
