package extendedDaos;

import daos.CustomerAddressesDao;
import daos.CustomerDao;
import pojos.customers.Customer;
import pojos.customers.CustomerAddress;
import pojos.extended.CustomerWithAddress;
import utils.fakers.CustomerAddressFaker;
import utils.fakers.CustomerFaker;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersWithAddressDao {
    CustomerAddressesDao customerAddressesDao;
    CustomerDao customerDao;
    private List<CustomerWithAddress> customerWithAddresses = new ArrayList<>();

    public CustomersWithAddressDao() throws SQLException {
        customerAddressesDao = new CustomerAddressesDao();
        customerDao = new CustomerDao();
    }

    public CustomersWithAddressDao(int numberOfCustomers) throws SQLException {
        customerAddressesDao = new CustomerAddressesDao();
        customerDao = new CustomerDao();
        createRandomCustomers(numberOfCustomers);
    }

    public void createRandomCustomers(int numberOfCustomers) {
        for (int i = 0; i < numberOfCustomers; i++) {
            CustomerAddress customerAddress = CustomerAddressFaker.createFakeCustomerAddress();
            Customer customer = CustomerFaker.createFakeCustomer();
            customer.setAddressId(customerAddress.getId());
            customerWithAddresses.add(new CustomerWithAddress(customer, customerAddress));
        }
    }


    public void saveAll() throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        saveAll(customerWithAddresses);
    }

    public List<CustomerWithAddress> getFromDb() throws SQLException {
        List<CustomerWithAddress> result = new ArrayList<>();
        for (Customer customer :
                customerDao.getAll()) {
            CustomerAddress customerAddress = customerAddressesDao.getById(customer.getAddressId());
            result.add(new CustomerWithAddress(customer, customerAddress));
        }
        return result;
    }

    public void saveAll(List<CustomerWithAddress> customerWithAddresses)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        for (CustomerWithAddress customerWithAddress :
                customerWithAddresses) {
            customerAddressesDao.save(customerWithAddress.getCustomerAddress());
            customerDao.save(customerWithAddress.getCustomer());
        }
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        for (CustomerWithAddress customerWithAddress :
                customerWithAddresses) {
            customers.add(customerWithAddress.getCustomer());
        }
        return customers;
    }

    public List<CustomerAddress> getCustomerAddresses() {
        List<CustomerAddress> customerAddresses = new ArrayList<>();
        for (CustomerWithAddress custWithAdd :
                customerWithAddresses) {
            customerAddresses.add(custWithAdd.getCustomerAddress());
        }
        return customerAddresses;
    }

    public List<CustomerWithAddress> getCustomerWithAddress() {
        return customerWithAddresses;
    }


}
