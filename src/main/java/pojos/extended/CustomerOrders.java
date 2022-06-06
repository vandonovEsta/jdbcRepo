package pojos.extended;

import pojos.orders.Order;

import java.util.List;

public class CustomerOrders {
    List<Order> orders;
    CustomerWithAddress customer;

    public CustomerOrders(CustomerWithAddress customer, List<Order> orders) {
        this.orders = orders;
        this.customer = customer;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public CustomerWithAddress getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        String result = "";
        result += "\nCustomer: " + customer.toString() + "\nOrders: ";
        for (Order order :
                orders) {
            result += "\n" + order.toString();
        }
        return result;
    }
}
