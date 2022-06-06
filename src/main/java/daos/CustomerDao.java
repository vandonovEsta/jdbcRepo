package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.customers.Customer;
import utils.Formatter;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements ICrudDao<Customer> {
    List<Customer> customers;
    private DataBaseDriver dataBaseDriver;
    private String table = "customers";
    private String id = "customer_id";

    public CustomerDao() {
        dataBaseDriver = DataBaseDriver.getInstance();
        customers = new ArrayList<>();
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        update();
        return customers;
    }


    @Override
    public String getTableName() {
        return table;
    }

    @Override
    public void save(Customer customer)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        dataBaseDriver.insetToTable(table, customer);
        update();
    }

    @Override
    public void delete(Customer customer) throws SQLException {
        dataBaseDriver.deleteBy(table, id, customer.getId());
    }

    @Override
    public void deleteAll() throws SQLException {
        dataBaseDriver.deleteAll(table);
    }

    @Override
    public Customer getById(int id) throws SQLException {
        List<Customer> result;
        result = dataBaseDriver.getAllEqualTo(table, Customer.class, this.id, String.valueOf(id));

        return result.get(0);

    }

    @Override
    public List<Customer> getByIds(List<Integer> ids) throws SQLException {
        List<Customer> result;
        String idsString = Formatter.listToString(ids);
        result = dataBaseDriver.getAllIn(table, Customer.class, this.id, idsString);


        return result;
    }

    @Override
    public Integer getAllRecordsCount() throws SQLException {
        return dataBaseDriver.countBy(table, id);
    }

    @Override
    public Integer getRandomId() throws SQLException {
        update();
        Customer customer = Randomizer.returnRandomFromList(customers);
        return customer.getId().intValue();
    }

    @Override
    public List<Integer> getRandomIds(int numberOrIds) throws Exception {
        update();
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

    @Override
    public void update() throws SQLException {
        customers = dataBaseDriver.getAllEntities(table, Customer.class);
    }
}
