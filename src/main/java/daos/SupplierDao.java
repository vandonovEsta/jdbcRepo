package daos;

import drivers.DataBaseDriver;
import interfaces.ICrudDao;
import pojos.suppliers.Supplier;
import utils.Randomizer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDao implements ICrudDao<Supplier> {
    List<Supplier> suppliers;
    private DataBaseDriver dataBaseDriver;
    private String table = "suppliers";
    private String id = "supplier_id";

    public SupplierDao() throws SQLException {
        dataBaseDriver = DataBaseDriver.getInstance();
        suppliers = new ArrayList<>();
        suppliers.addAll(dataBaseDriver.getAllEntities(table, Supplier.class));
    }

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    @Override
    public List<Supplier> getAll() {
        return suppliers;
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
    public void save(Supplier object) throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
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
    public void delete(Supplier object) throws SQLException {
        dataBaseDriver.deleteBy(table, id, object.getId());

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
    public Supplier getById(int id) throws SQLException {
        Supplier result = null;
        for (Supplier supplier :
                suppliers) {
            if (supplier.getId() == id) {
                result = supplier;
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
    public List<Supplier> getByIds(List<Integer> ids) throws SQLException {
        List<Supplier> result = new ArrayList<>();
        for (Supplier supplier :
                suppliers) {
            if (ids.contains(supplier.getId())) {
                result.add(supplier);
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
        return suppliers.size();
    }

    /**
     * returns one random id from the table
     *
     * @return
     */
    @Override
    public Integer getRandomId() {
        Supplier supplier = Randomizer.returnRandomFromList(suppliers);
        return supplier.getId();
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
        if (numberOrIds <= suppliers.size()) {
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
        suppliers = dataBaseDriver.getAllEntities(table, Supplier.class);
    }
}
