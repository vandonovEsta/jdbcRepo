package extendedDaos;

import com.github.javafaker.Faker;
import daos.*;
import pojos.extended.CustomerOrders;
import pojos.extended.OrderedProducts;
import pojos.orders.Order;
import pojos.orders.OrderedQuality;
import pojos.products.Product;
import utils.fakers.ProductFaker;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderedProductsDao {
    ProductsDao productsDao;
    private OrderedProducts orderedProducts;
    private CustomerOrdersDao customerOrdersDao;
    private List<CustomerOrders> customersOrders;
    private List<Product> products = new ArrayList<>();
    private List<OrderedQuality> orderedQualities = new ArrayList<>();

    public OrderedProductsDao() throws SQLException {

    }

    public OrderedProductsDao(int numberOfInstances)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        productsDao = new ProductsDao();
        createCustomerOrders(numberOfInstances);
        createProducts(numberOfInstances);
        createOrderedQualities(numberOfInstances);

        orderedProducts = new OrderedProducts(customersOrders, orderedQualities, products);
    }

    public void truncateAll() throws SQLException {
        CustomerDao customerDao = new CustomerDao();
        CustomerAddressesDao customerAddressesDao = new CustomerAddressesDao();
        OrdersDao ordersDao = new OrdersDao();
        productsDao = new ProductsDao();
        OrderedQuantityDao orderedQuantityDao = new OrderedQuantityDao();
        customerDao.deleteAll();
        customerAddressesDao.deleteAll();
        orderedQuantityDao.deleteAll();
        productsDao.deleteAll();
        customerDao.deleteAll();

    }

    private void createCustomerOrders(int numberOfInstances)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        customerOrdersDao = new CustomerOrdersDao(numberOfInstances);
        customersOrders = customerOrdersDao.getCustomersOrders();

    }

    private void createProducts(int numberOfProducts) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (int i = 0; i < numberOfProducts; i++) {
            Product product = ProductFaker.createFakeProduct();
            productsDao.save(product);
            products.add(product);
        }
    }


    private void createOrderedQualities(int numberOfProducts) {
        for (CustomerOrders customerOrders :
                customersOrders) {
            for (int i = 0; i < customerOrders.getOrders().size(); i++) {
                Order order = customerOrders.getOrders().get(i);
                int orderId = order.getId();
                for (int j = 0; j < numberOfProducts; j++) {

                    Product product = products.get(j);
                    OrderedQuality orderedQuality = new OrderedQuality(orderId, product.getId().intValue(),
                            Faker.instance().number().numberBetween(1, 99));
                    orderedQualities.add(orderedQuality);
                }
            }
        }
    }

    public OrderedProducts getOrderedProducts() {
        return orderedProducts;
    }

    public void saveOrderedProducts()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        ProductsDao productsDao = new ProductsDao();
        OrderedQuantityDao orderedQuantityDao = new OrderedQuantityDao();


        for (OrderedQuality orderedQuantity :
                orderedQualities) {
            orderedQuantityDao.save(orderedQuantity);
        }
    }
}
