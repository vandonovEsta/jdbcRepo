package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.orders.Order;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDao implements ICrudDao<Order> {
    List<Order> orders;
    private DataBaseDriver dataBaseDriver;
    private String table = "orders";
    private String id = "order_id";

    public OrdersDao() throws SQLException {
        dataBaseDriver = DataBaseDriver.getInstance();
        orders = new ArrayList<>();
        orders.addAll(dataBaseDriver.getAllEntities(table, Order.class));
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    @Override
    public List<Order> getAll() {
        return orders;
    }

    /**
     * returns the name of the table
     *
     * @return
     */
    @Override
    public String getTableName() {
        return table;
    }

    /**
     * Accepts an object and tries to save it to the corresponding table
     *
     * @param order
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SQLException
     */
    @Override
    public void save(Order order)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        dataBaseDriver.insetToTable(table, order);
    }

    /**
     * deletes specific object
     *
     * @throws SQLException
     */
    @Override
    public void delete(Order order) throws SQLException {
        dataBaseDriver.deleteBy(table, id, order.getId().intValue());
    }

    /**
     * deletes all rows in the table.
     *
     * @throws SQLException
     */
    @Override
    public void deleteAll() throws SQLException {
        dataBaseDriver.deleteAll(table);
    }

    /**
     * returns a specific object by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    @Override
    public Order getById(int id) throws SQLException {
        Order result = null;
        for (Order order :
                orders) {
            if (order.getId() == id) {
                result = order;
            }
        }
        return result;
    }

    /**
     * returns integer corresponding to the number of rows in the table
     *
     * @return
     * @throws SQLException
     */
    @Override
    public Integer getAllRecordsCount() throws SQLException {
        return orders.size();
    }

    /**
     * returns one random id from the table
     *
     * @return
     */
    @Override
    public Integer getRandomId() {
        Order order = Randomizer.returnRandomFromList(orders);
        return order.getId().intValue();
    }

    /**
     * returns a list of random ids.
     *
     * @param numberOrIds - determines how many ids to be returned
     * @return
     * @throws Exception - returns exception if the required number if
     *                   Ids is bigger than the count of rows in the table.
     */
    @Override
    public List<Integer> getRandomIds(int numberOrIds) throws Exception {
        List<Integer> randomIds = new ArrayList<>();
        if (numberOrIds <= orders.size()) {
            while (randomIds.size() < numberOrIds) {
                int randID = getRandomId();
                if (!randomIds.contains(randID)) {
                    randomIds.add(randID);
                }
            }
        } else {
            throw new Exception("Not enough unique entries in result set!");
        }
        return randomIds;
    }

    /**
     * returns multiple objects by id
     *
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    public List<Order> getByIds(List<Integer> ids) throws SQLException {
        List<Order> result = new ArrayList<>();
        for (Order order :
                orders) {
            if (ids.contains(order.getId())) {
                result.add(order);
            }
        }

        return result;
    }
}
