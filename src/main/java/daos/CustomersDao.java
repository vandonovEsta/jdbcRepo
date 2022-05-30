package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.customers.Customer;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao implements ICrudDao<Customer> {

    List<Customer> customers;
    private DataBaseDriver dataBaseDriver;
    private String table = "customers";
    private String id = "customer_id";

    public CustomersDao() throws SQLException {
        dataBaseDriver = DataBaseDriver.getInstance();
        customers = new ArrayList<>();
        customers.addAll(dataBaseDriver.getAllEntities(table, Customer.class));
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    @Override
    public List<Customer> getAll() {
        return customers;
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
    public void save(Customer object) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        dataBaseDriver.insetToTable(table, object);

    }

    /**
     * deletes specific object
     *
     * @param object
     * @throws SQLException
     */
    @Override
    public void delete(Customer object) throws SQLException {
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
    public Customer getById(int id) throws SQLException {
        Customer result = null;
        for (Customer customer :
                customers) {
            if (customer.getId() == id) {
                result = customer;
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
    public List<Customer> getByIds(List<Integer> ids) throws SQLException {
        List<Customer> result = new ArrayList<>();
        for (Customer customer :
                customers) {
            if (ids.contains(customer.getId())) {
                result.add(customer);
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
        return customers.size();
    }

    /**
     * returns one random id from the table
     *
     * @return
     */
    @Override
    public Integer getRandomId() {
        Customer customer = Randomizer.returnRandomFromList(customers);
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
        List<Integer> randomIds = new ArrayList<>();
        if (numberOrIds <= customers.size()) {
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
}
