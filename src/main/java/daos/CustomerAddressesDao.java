package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.customers.CustomerAddress;
import utils.Formatter;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerAddressesDao implements ICrudDao<CustomerAddress> {

    List<CustomerAddress> customerAddresses;
    private DataBaseDriver dataBaseDriver;
    private String table = "customers_addresses";
    private String id = "address_id";

    public CustomerAddressesDao() {
        dataBaseDriver = DataBaseDriver.getInstance();
        customerAddresses = new ArrayList<>();
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    @Override
    public List<CustomerAddress> getAll() throws SQLException {
        customerAddresses.addAll(dataBaseDriver.getAllEntities(table, CustomerAddress.class));
        return customerAddresses;
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
    public void save(CustomerAddress object) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        dataBaseDriver.insetToTable(table, object);

    }

    /**
     * deletes specific object
     *
     * @param object
     * @throws SQLException
     */
    @Override
    public void delete(CustomerAddress object) throws SQLException {
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
    public CustomerAddress getById(int id) throws SQLException {
        List<CustomerAddress> result;
        result = dataBaseDriver.getAllEqualTo(table, CustomerAddress.class, this.id, String.valueOf(id));

        return result.get(0);
    }

    /**
     * returns multiple objects by id
     *
     * @param ids
     * @return
     * @throws SQLException
     */
    @Override
    public List<CustomerAddress> getByIds(List<Integer> ids) throws SQLException {
        List<CustomerAddress> result;
        String idsString = Formatter.listToString(ids);
        result = dataBaseDriver.getAllIn(table, CustomerAddress.class, this.id, idsString);


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
        CustomerAddress customer = Randomizer.returnRandomFromList(customerAddresses);
        return customer.getId().intValue();
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
        if (numberOrIds <= customerAddresses.size()) {
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
        customerAddresses = dataBaseDriver.getAllEntities(table, CustomerAddress.class);
    }
}
