package pojos.orders;

import daos.CustomerDao;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utils.fakers.OrderFaker;

import java.sql.SQLException;
import java.util.List;

public class OrdersTests {

    @Test
    public void createFakeOrderTest() throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        int randomCustomerId = customerDao.getRandomId();
        Order order = OrderFaker.createFakeOrder(randomCustomerId);

        System.out.println(order);
        Assert.assertNotNull(order);
    }

    @Test
    public void createFakeOrdersTest() throws Exception {
        List<Order> orders = OrderFaker.createFakeOrders();
        for (Order order :
                orders) {
            System.out.println(order);
            Assert.assertNotNull(order);
        }
    }
}
