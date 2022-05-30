package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.customers.Customer;
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

    public CustomerDao() throws SQLException {
        dataBaseDriver = DataBaseDriver.getInstance();
        customers = new ArrayList<>();
        customers.addAll(dataBaseDriver.getAllEntities(table, Customer.class));
    }

    @Override
    public List<Customer> getAll() {
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
        Customer result = null;
        for (Customer customer :
                customers) {
            if (customer.getId() == id) {
                result = customer;
            }
        }
        return result;

    }

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

    @Override
    public Integer getAllRecordsCount() throws SQLException {
        return customers.size();
    }

    @Override
    public Integer getRandomId() {
        Customer customer = Randomizer.returnRandomFromList(customers);
        return customer.getId();
    }

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
