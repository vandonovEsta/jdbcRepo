package utils.fakers;

import com.github.javafaker.Faker;
import pojos.orders.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OrderFaker {
    public static Order createFakeOrder() {
        Faker faker = new Faker();
        Integer orderId = faker.number().randomDigitNotZero();
        Boolean isOrderCompleted = faker.bool().bool();
        Boolean isOrderPayed;
        if (isOrderCompleted) {
            isOrderPayed = true;
        } else {
            isOrderPayed = faker.bool().bool();
        }
        Date dateOfOrder = faker.date().past(90, TimeUnit.DAYS);
        Date dateOrderConfirmed = new Date();
        Integer customerId = 0;

        Order order = new Order(
                orderId, isOrderCompleted, isOrderPayed,
                dateOfOrder, dateOrderConfirmed
        );

        return order;
    }

    public static List<Order> createFakeOrders(int numberOfOrders) {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < numberOfOrders; i++) {
            orders.add(createFakeOrder());
        }

        return orders;
    }

    public static List<Order> createFakeOrders() {
        int numberOfOrders = Faker.instance().number().numberBetween(2, 15);

        return createFakeOrders(numberOfOrders);
    }
}
