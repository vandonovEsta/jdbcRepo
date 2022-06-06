package extendedDaos;

import daos.OrdersDao;
import pojos.extended.CustomerOrders;
import pojos.extended.CustomerWithAddress;
import pojos.orders.Order;
import utils.fakers.CustomerOrdersFaker;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrdersDao {

    List<CustomerOrders> customersOrders;
    OrdersDao ordersDao;

    CustomersWithAddressDao customersWithAddressesDao;
    boolean saved = false;

    public CustomerOrdersDao(int numberOfOrders) throws SQLException {
        customersWithAddressesDao = new CustomersWithAddressDao(numberOfOrders);
        ordersDao = new OrdersDao();
        createOrders(numberOfOrders);
    }

    private void createOrders(int numberOfOrders) throws SQLException {
        customersOrders = CustomerOrdersFaker.createFakeCustomersOrders(numberOfOrders);

    }

    public void saveAll() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        List<CustomerWithAddress> customerWithAddresses = new ArrayList<>();
        List<Order> ordersList = new ArrayList<>();
        for (CustomerOrders customerOrders :
                customersOrders) {
            customerWithAddresses.add(customerOrders.getCustomer());
            ordersList.addAll(customerOrders.getOrders());
        }
        customersWithAddressesDao.saveAll(customerWithAddresses);
        List<Integer> orderIds = new ArrayList<>();
        for (Order order :
                ordersList) {
            int id = order.getId();
            if (orderIds.contains(id)) {
                int newID = ordersDao.getLastPk() + 1;
                order.setId(newID);
                orderIds.add(newID);
            } else {
                orderIds.add(id);
            }
            ordersDao.save(order);
        }
        saved = true;
    }

    public List<CustomerOrders> getCustomersOrders()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if (!saved) {
            saveAll();
        }
        return customersOrders;
    }

    public CustomersWithAddressDao getCustomersWithAddressesDao() {
        return customersWithAddressesDao;
    }
}
