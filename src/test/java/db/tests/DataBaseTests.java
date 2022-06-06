package db.tests;

import com.github.javafaker.Faker;
import extendedDaos.OrderedProductsDao;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class DataBaseTests {
    @Test
    public void saveAllTest()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        OrderedProductsDao orderedProductsDao =
                new OrderedProductsDao(Faker.instance().number().numberBetween(1, 15));
        orderedProductsDao.saveOrderedProducts();
    }


    @Test
    public void deleteAllTest() throws SQLException {
        OrderedProductsDao orderedProductsDao = new OrderedProductsDao();
        orderedProductsDao.truncateAll();
    }


}
