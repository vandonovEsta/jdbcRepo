package utils.fakers;

import com.github.javafaker.Faker;
import daos.CustomerDao;
import daos.OrdersDao;
import pojos.customers.Customer;
import pojos.orders.Order;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderFaker {

    public static Order createFakeOrder(Integer customerId) throws SQLException {
        return createFakeOrder(customerId, 0);
    }

    public static Order createFakeOrder(Integer customerId, Integer iterator) throws SQLException {
        Faker faker = new Faker();
        OrdersDao ordersDao = new OrdersDao();

        Integer orderId = ordersDao.getLastPk() + 1 + iterator;
        Boolean isOrderCompleted = faker.bool().bool();
        Boolean isOrderPayed;
        if (isOrderCompleted) {
            isOrderPayed = true;
        } else {
            isOrderPayed = faker.bool().bool();
        }
        Date dateOfOrder = faker.date().past(90, TimeUnit.DAYS);
        Date dateOrderConfirmed = new Date();

        Order order = new Order(
                orderId, isOrderCompleted, isOrderPayed,
                dateOfOrder, dateOrderConfirmed, customerId
        );

        return order;
    }

    public static List<Order> createFakeOrders(List<Customer> customers, int numberOfOrders) throws SQLException {
        List<Order> orders = new ArrayList<>();
        for (Customer customer :
                customers) {
            orders = createFakeOrders(customer.getOrderId(), numberOfOrders);
        }

        return orders;
    }

    public static List<Order> createFakeOrders() throws Exception {
        int numberOfInstances = Faker.instance().number().numberBetween(2, 15);
        CustomerDao customerDao = new CustomerDao();
        List<Integer> randomCustomerIds = customerDao.getRandomIds(numberOfInstances);
        List<Customer> randomCustomers = customerDao.getByIds(randomCustomerIds);

        return createFakeOrders(randomCustomers, numberOfInstances);
    }

    public static List<Order> createFakeOrders(int customerId, int numberOfOrders) throws SQLException {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            orders.add(createFakeOrder(customerId, i));
        }

        return orders;
    }
}
