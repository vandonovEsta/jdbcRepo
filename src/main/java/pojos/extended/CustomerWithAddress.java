package pojos.extended;

import pojos.customers.Customer;
import pojos.customers.CustomerAddress;

public class CustomerWithAddress {

    private Customer customer;
    private CustomerAddress customerAddress;

    public CustomerWithAddress(Customer customer, CustomerAddress customerAddress) {
        this.customer = customer;
        this.customerAddress = customerAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public String toString() {
        String result = "";

        result = "\nCustomer: " + customer.toString() + "\nAddress" + customerAddress.toString();

        return result;
    }
}
