package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.orders.OrderedQuality;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderedQuantityDao implements ICrudDao<OrderedQuality> {
    List<OrderedQuality> orderedQualities;
    private DataBaseDriver dataBaseDriver;
    private String table = "ordered_quantity";
    private String id = "order_id";
    private String productId = "product_id";

    public OrderedQuantityDao() throws SQLException {
        dataBaseDriver = DataBaseDriver.getInstance();
        orderedQualities = new ArrayList<>();
        orderedQualities = dataBaseDriver.getAllEntities(table, OrderedQuality.class);
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    @Override
    public List<OrderedQuality> getAll() {
        return orderedQualities;
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
     * @param object
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SQLException
     */
    @Override
    public void save(OrderedQuality object) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        dataBaseDriver.insetToTable(table, object);
        update();
    }

    /**
     * deletes specific object
     *
     * @param object
     * @throws SQLException
     */
    @Override
    public void delete(OrderedQuality object) throws SQLException {
        dataBaseDriver.deleteBy(table, id, object.getId().intValue());
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
    public OrderedQuality getById(int id) throws SQLException {
        OrderedQuality result = null;
        for (OrderedQuality orderedQuality :
                orderedQualities) {
            if (orderedQuality.getId() == id) {
                result = orderedQuality;
            }
        }
        return result;
    }

    /**
     * returns multiple objects by id
     *
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    public List<OrderedQuality> getByIds(List<Integer> ids) throws SQLException {
        List<OrderedQuality> result = new ArrayList<>();
        for (OrderedQuality orderedQuality :
                orderedQualities) {
            if (ids.contains(orderedQuality.getId())) {
                result.add(orderedQuality);
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
        return orderedQualities.size();
    }

    /**
     * returns one random id from the table
     *
     * @return
     */
    @Override
    public Integer getRandomId() {
        OrderedQuality orderedQuality = Randomizer.returnRandomFromList(orderedQualities);
        return orderedQuality.getId();
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
        if (numberOrIds <= orderedQualities.size()) {
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

    @Override
    public void update() throws SQLException {
        orderedQualities = dataBaseDriver.getAllEntities(table, OrderedQuality.class);
    }

    public Integer getLastPk() throws SQLException {
        Integer id = 0;

        for (OrderedQuality orderedQuality :
                orderedQualities) {
            if (id < orderedQuality.getId()) {
                id = orderedQuality.getId();
            }

        }
        return id;

    }
}
