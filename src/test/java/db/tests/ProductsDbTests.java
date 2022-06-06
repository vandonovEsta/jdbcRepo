package db.tests;

import com.github.javafaker.Faker;
import daos.ProductsDao;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.util.PSQLException;
import pojos.products.Product;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductsDbTests {
    private ProductsDao productsDao;

    @BeforeEach
    public void init() throws SQLException {
        productsDao = new ProductsDao();

        Assert.assertTrue("Table is empty", productsDao.getAll().size() > 0);

    }

    @Test
    public void mandatoryFieldsTest() throws Exception {
        List<Integer> productIds = productsDao.getRandomIds(Faker.instance().number().numberBetween(1, 5));
        List<Product> products = productsDao.getByIds(productIds);


        for (Product product :
                products) {
            System.out.println(product);
            assertNotNull(product.getId());
            assertNotNull(product.getSupplierId());
        }
    }

    @Test
    public void productIdMandatoryFieldTest()
            throws SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Product product = new Product(null,
                null, null,
                null, null, null,
                null, null, 1L);


        try {
            productsDao.save(product);
        } catch (Exception e) {
            assertTrue("Expected " + SQLException.class + " but got "
                    + e.getClass(), e.getClass().equals(PSQLException.class));
            assertTrue(e.getMessage().contains("null value in column \"product_id\""));
        }
    }

    @Test
    public void supplierIdMandatoryTest() throws SQLException {
        Product product = new Product(productsDao.getLastPk() + 1,
                null, null,
                null, null, null,
                null, null, null);

        try {
            productsDao.save(product);
        } catch (Exception e) {
            assertTrue("Expected " + SQLException.class + " but got "
                    + e.getClass(), e.getClass().equals(PSQLException.class));
            assertTrue(e.getMessage().contains("null value in column \"supplier_id\""));
        }
    }
}
