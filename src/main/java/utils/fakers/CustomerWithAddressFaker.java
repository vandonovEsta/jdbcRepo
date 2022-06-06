package utils.fakers;

import pojos.customers.Customer;
import pojos.customers.CustomerAddress;
import pojos.extended.CustomerWithAddress;

import java.util.ArrayList;
import java.util.List;

public class CustomerWithAddressFaker {
    public static List<CustomerWithAddress> createFakeCustomersWithAddress(int numberOfCustomer) {
        List<CustomerWithAddress> customerWithAddresses = new ArrayList<>();
        for (int i = 0; i < numberOfCustomer; i++) {
            Customer customer = CustomerFaker.createFakeCustomer();
            CustomerAddress customerAddress = CustomerAddressFaker.createFakeCustomerAddress();
            customer.setAddressId(customerAddress.getId());
            customerWithAddresses.add(new CustomerWithAddress(customer, customerAddress));
        }
        return customerWithAddresses;
    }

    public static CustomerWithAddress createFakeCustomerWithAddress() {
        Customer customer = CustomerFaker.createFakeCustomer();
        CustomerAddress customerAddress = CustomerAddressFaker.createFakeCustomerAddress();
        customer.setAddressId(customerAddress.getId());
        return new CustomerWithAddress(customer, customerAddress);
    }
}
