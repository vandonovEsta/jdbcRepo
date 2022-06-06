package interfaces;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface ICrudDao<T> {

    /**
     * returns a list of objects populated with data from the coresponding table
     *
     * @return
     */
    public List<T> getAll() throws SQLException;

    /**
     * returns the name of the table
     *
     * @return
     */
    public String getTableName();

    /**
     * Accepts an object and tries to save it to the corresponding table
     *
     * @param object
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws SQLException
     */
    public void save(T object)
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    /**
     * deletes specific object
     *
     * @param object
     * @throws SQLException
     */
    public void delete(T object) throws SQLException;

    /**
     * deletes all rows in the table.
     *
     * @throws SQLException
     */
    public void deleteAll() throws SQLException;

    /**
     * returns a specific object by id
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public T getById(int id) throws SQLException;

    /**
     * returns multiple objects by id
     *
     * @param ids
     * @return
     * @throws SQLException
     */
    public List<T> getByIds(List<Integer> ids) throws SQLException;

    /**
     * returns integer corresponding to the number of rows in the table
     *
     * @return
     * @throws SQLException
     */
    public Integer getAllRecordsCount() throws SQLException;

    /**
     * returns one random id from the table
     *
     * @return
     */
    public Integer getRandomId() throws SQLException;

    /**
     * returns a list of random ids.
     *
     * @param numberOrIds - determines how many ids to be returned
     * @return
     * @throws Exception - returns exception if the required number if
     *                   Ids is bigger than the count of rows in the table.
     */
    public List<Integer> getRandomIds(int numberOrIds) throws Exception;

    void update() throws SQLException;


}
