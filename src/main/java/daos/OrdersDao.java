package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.orders.Order;
import utils.Formatter;
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

    public OrdersDao() {
        dataBaseDriver = DataBaseDriver.getInstance();
        orders = new ArrayList<>();
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */

    public Integer getLastPk() throws SQLException {
        Integer id = 0;

        id = dataBaseDriver.getLastPk(table, this.id);
        if (id == null) {
            return 1;
        }
        return id;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        orders.addAll(dataBaseDriver.getAllEntities(table, Order.class));
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
        update();
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
        List<Order> result;
        result = dataBaseDriver.getAllEqualTo(table, Order.class, this.id, String.valueOf(id));

        return result.get(0);
    }

    public Order getByCustomerId(int custId) throws SQLException {
        Order result;
        result = dataBaseDriver
                .getAllEqualTo(table, Order.class, "customer_id", String.valueOf(custId)).get(0);
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
        return dataBaseDriver.countBy(table, id);

    }

    /**
     * returns one random id from the table
     *
     * @return
     */
    @Override
    public Integer getRandomId() throws SQLException {
        update();
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
        update();
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
        List<Order> result;
        String idsString = Formatter.listToString(ids);
        result = dataBaseDriver.getAllIn(table, Order.class, this.id, idsString);


        return result;
    }

    @Override
    public void update() throws SQLException {
        orders = dataBaseDriver.getAllEntities(table, Order.class);
    }


}
