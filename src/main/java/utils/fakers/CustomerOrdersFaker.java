package utils.fakers;

import com.github.javafaker.Faker;
import pojos.extended.CustomerOrders;
import pojos.extended.CustomerWithAddress;
import pojos.orders.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrdersFaker {

    Faker faker;

    public static List<CustomerOrders> createFakeCustomersOrders(int numberOfOrders) throws SQLException {
        List<CustomerOrders> customersOrders = new ArrayList<>();
        List<CustomerWithAddress> customerWithAddresses =
                CustomerWithAddressFaker.createFakeCustomersWithAddress(numberOfOrders);
        for (CustomerWithAddress customerWithAddress :
                customerWithAddresses) {
            List<Order> orders = OrderFaker.createFakeOrders(customerWithAddress.getCustomer().getId(), numberOfOrders);
            customersOrders.add(new CustomerOrders(customerWithAddress, orders));

        }

        return customersOrders;
    }

    public static CustomerOrders createFakeCustomerOrder(int numberOfOrders) throws SQLException {
        CustomerOrders customerOrders;
        CustomerWithAddress customer = CustomerWithAddressFaker.createFakeCustomerWithAddress();
        List<Order> orders = OrderFaker.createFakeOrders(customer.getCustomer().getId(), numberOfOrders);
        customerOrders = new CustomerOrders(customer, orders);

        return customerOrders;
    }
}
