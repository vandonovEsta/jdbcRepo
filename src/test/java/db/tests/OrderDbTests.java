package db.tests;

import com.github.javafaker.Faker;
import daos.CustomerDao;
import daos.OrderedQuantityDao;
import daos.OrdersDao;
import daos.ProductsDao;
import extendedDaos.OrderedProductsDao;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import pojos.orders.Order;
import pojos.orders.OrderedQuality;
import pojos.products.Product;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class OrderDbTests {

    @BeforeEach
    public void init() throws SQLException {
        OrdersDao ordersDao = new OrdersDao();

        Assert.assertTrue("Table is empty", ordersDao.getAll().size() > 0);

    }

    @Test
    public void ordersHaveAllMandatoryFieldsTest() throws Exception {
        List<Integer> randomCustomerIds;
        List<Order> orders = new ArrayList<>();
        CustomerDao customerDao = new CustomerDao();
        OrdersDao ordersDao = new OrdersDao();

        randomCustomerIds = customerDao.getRandomIds(Faker.instance().number().numberBetween(1, 5));

        for (Integer customerID :
                randomCustomerIds) {
            orders.add(ordersDao.getByCustomerId(customerID));
        }

        for (Order order :
                orders) {
            assertNotNull(order.getId());
            assertNotNull(order.getIsOrderCompleted());
            assertNotNull(order.getIsOrderPayed());
            assertNotNull(order.getDateOfOrder());

        }
    }

    @Test
    public void orderHasCustomerTest() throws Exception {
        List<Order> orders;
        OrdersDao ordersDao = new OrdersDao();
        List<Integer> randomOrderIds;
        CustomerDao customerDao = new CustomerDao();

        randomOrderIds = ordersDao.getRandomIds(Faker.instance().number().numberBetween(1, 5));
        orders = ordersDao.getByIds(randomOrderIds);

        for (Order order :
                orders) {
            assertNotNull(customerDao.getById(order.getCustomerId()));
        }

    }

    @Test
    public void verifyOrderProductsExistTest() throws Exception {
        List<Order> orders;
        OrdersDao ordersDao = new OrdersDao();
        OrderedQuantityDao orderedQuantityDao = new OrderedQuantityDao();
        ProductsDao productsDao = new ProductsDao();
        List<Integer> randomOrderIds;

        randomOrderIds = ordersDao.getRandomIds(Faker.instance().number().numberBetween(1, 5));
        orders = ordersDao.getByIds(randomOrderIds);

        for (Order order :
                orders) {
            OrderedQuality orderedQuality = orderedQuantityDao.getById(order.getId());
            Product product = productsDao.getById(orderedQuality.getProductId());

            System.out.println(order);
            System.out.println(product);
            assertNotNull(product);
        }
    }

    @Test
    public void createCustomerOrderAndVerifyTest()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        CustomerDao customerDao = new CustomerDao();
        OrdersDao ordersDao = new OrdersDao();
        ProductsDao productsDao = new ProductsDao();
        OrderedProductsDao orderedProductsDao =
                new OrderedProductsDao(1);
        int customerId = orderedProductsDao.getOrderedProducts()
                .getCustomerOrders().get(0).getCustomer().getCustomer().getId();
        int orderId = orderedProductsDao.getOrderedProducts()
                .getCustomerOrders().get(0).getOrders().get(0).getId();
        long productId = orderedProductsDao.getOrderedProducts().getProducts().get(0).getId();


        orderedProductsDao.saveOrderedProducts();

        assertNotNull(customerDao.getById(customerId));
        assertNotNull(ordersDao.getById(orderId));
        assertNotNull(productsDao.getById((int) productId));

    }

    @Test
    public void orderMandatoryFieldsTest() {
        OrdersDao ordersDao = new OrdersDao();
        Order order = new Order(null, null,
                null, null,
                null, null);

        try {
            ordersDao.save(order);
        } catch (Exception e) {
            assertTrue("Expected " + SQLException.class + " but got "
                    + e.getClass(), e.getClass().equals(PSQLException.class));
        }
    }
}
