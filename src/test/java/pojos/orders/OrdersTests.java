package pojos.orders;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utils.fakers.OrderFaker;

import java.util.List;

public class OrdersTests {

    @Test
    public void createFakeOrderTest() {
        Order order = OrderFaker.createFakeOrder();

        System.out.println(order);
        Assert.assertNotNull(order);
    }

    @Test
    public void createFakeOrdersTest() {
        List<Order> orders = OrderFaker.createFakeOrders();
        for (Order order :
                orders) {
            System.out.println(order);
            Assert.assertNotNull(order);
        }
    }
}
