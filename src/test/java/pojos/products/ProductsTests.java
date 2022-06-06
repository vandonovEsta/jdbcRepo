package pojos.products;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utils.fakers.ProductFaker;

import java.sql.SQLException;
import java.util.List;

public class ProductsTests {
    @Test
    public void createFakeProductTest() throws SQLException {
        Product product = ProductFaker.createFakeProduct();

        System.out.println(product);

        Assert.assertNotNull(product);
    }

    @Test
    public void createFakeProductsTest() throws SQLException {
        List<Product> products;
        products = ProductFaker.createFakeProducts();

        for (Product product :
                products) {
            System.out.println(product);
            Assert.assertNotNull(product);
        }
    }
}
