package pojos.products;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import utils.fakers.ProductFaker;

import java.util.List;

public class ProductsTests {
    @Test
    public void createFakeProductTest() {
        Product product = ProductFaker.createFakeProduct();

        System.out.println(product);

        Assert.assertNotNull(product);
    }

    @Test
    public void createFakeProductsTest() {
        List<Product> products;
        products = ProductFaker.createFakeProducts();

        for (Product product :
                products) {
            System.out.println(product);
            Assert.assertNotNull(product);
        }
    }
}
