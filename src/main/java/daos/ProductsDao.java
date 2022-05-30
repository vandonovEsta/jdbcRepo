package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.products.Product;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao implements ICrudDao<Product> {

    List<Product> products;
    private DataBaseDriver dataBaseDriver;
    private String table = "products_inventory";
    private String id = "product_id";

    public ProductsDao() throws SQLException {
        dataBaseDriver = DataBaseDriver.getInstance();
        products = new ArrayList<>();
        products.addAll(dataBaseDriver.getAllEntities(table, Product.class));
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    @Override
    public List<Product> getAll() {
        return products;
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
     * @param product
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SQLException
     */
    @Override
    public void save(Product product) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        dataBaseDriver.insetToTable(table, product);
    }

    /**
     * deletes specific object
     *
     * @param object
     * @throws SQLException
     */
    @Override
    public void delete(Product object) throws SQLException {
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
    public Product getById(int id) throws SQLException {
        Product result = null;
        for (Product product :
                products) {
            if (product.getId() == id) {
                result = product;
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
    public List<Product> getByIds(List<Integer> ids) throws SQLException {
        List<Product> result = new ArrayList<>();
        for (Product product :
                products) {
            if (ids.contains(product.getId())) {
                result.add(product);
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
        return products.size();
    }

    /**
     * returns one random id from the table
     *
     * @return
     */
    @Override
    public Integer getRandomId() {
        Product product = Randomizer.returnRandomFromList(products);
        return product.getId().intValue();
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
        if (numberOrIds <= products.size()) {
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
