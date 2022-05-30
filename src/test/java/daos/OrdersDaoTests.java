package daos;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pojos.orders.Order;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class OrdersDaoTests {

    @Test
    public void getAllOrdersTest() throws SQLException {
        List<Order> orders;
        OrdersDao ordersDao = new OrdersDao();

        orders = ordersDao.getAll();

        for (Order order :
                orders) {
            System.out.println(order);
            assertNotNull(order);
        }
    }

    @Test
    public void saveOrderTest() {
        //TODO: implement after other tests!!!
    }

    @Test
    public void deleteOrderTest() {
        //TODO:implement after saveOrderTest!!!
    }

    @Disabled("Disabled until a backup can be created!!!")
    @Test
    public void deleteAllTest() {
        //TODO: do not implement for now!!!
    }

    @Test
    public void getOrderByIdTest() throws SQLException {
        OrdersDao ordersDao = new OrdersDao();
        Order order = ordersDao.getById(15);

        System.out.println(order);

        assertNotNull(order);
    }

    @Test
    public void getAllRecordsCountTest() throws SQLException {
        OrdersDao ordersDao = new OrdersDao();
        int ordersCount = ordersDao.getAllRecordsCount();

        assertTrue(ordersCount > 0);
    }

    @Test
    public void getRandomOrderIdTest() throws SQLException {
        OrdersDao ordersDao = new OrdersDao();
        int randomId = ordersDao.getRandomId();
        boolean isIdContainedInOrdersList = false;

        for (Order order :
                ordersDao.getAll()) {
            if (order.getId().intValue() == randomId) {
                isIdContainedInOrdersList = true;
            }
        }

        System.out.println(randomId);
        assertTrue("Random order id is not real", isIdContainedInOrdersList);
    }

    @Test
    public void getRandomIdsTest() throws Exception {
        OrdersDao ordersDao = new OrdersDao();
        List<Integer> randomIds;
        int requiredIds = 5;
        randomIds = ordersDao.getRandomIds(requiredIds);

        System.out.println(randomIds);

        assertEquals(requiredIds, randomIds.size());
    }

    @Test
    public void getByIdsTest() throws Exception {
        OrdersDao ordersDao = new OrdersDao();
        List<Integer> randomIds;
        int requiredIds = 5;
        randomIds = ordersDao.getRandomIds(requiredIds);
        System.out.println("Ids: " + randomIds);
        List<Order> orders = ordersDao.getByIds(randomIds);

        System.out.println("Customers:");
        for (Order order :
                orders) {
            System.out.println(order);
            assertNotNull(order);
        }
        assertEquals(randomIds.size(), orders.size());
    }
}
