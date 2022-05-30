package utils.fakers;

import com.github.javafaker.Faker;
import pojos.products.Product;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductFaker {
    public static Product createFakeProduct() {
        Product product;
        Faker faker = new Faker();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        decimalFormat.setRoundingMode(RoundingMode.UP);
        Long productId = faker.number().randomNumber();
        String productName = faker.commerce().productName().toLowerCase();
        Long availableQuantity = faker.number().randomNumber();
        String productType = productName.toUpperCase();
        Double priceWithVat = faker.number().randomDouble(2, 1, 1000);
        Double priceWithoutVat = priceWithVat - (priceWithVat * 0.20);
        priceWithoutVat = Double.valueOf(decimalFormat.format(priceWithoutVat));
        boolean isProductInStock = faker.bool().bool();
        String warehouse = faker.numerify("Warehouse##");
        long supplierId = 0L;

        product = new Product(
                productId, productName, availableQuantity,
                productType, priceWithVat, priceWithoutVat,
                isProductInStock, warehouse, supplierId
        );

        return product;
    }

    public static List<Product> createFakeProducts(int numberOfProducts) {
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < numberOfProducts; i++) {
            products.add(createFakeProduct());
        }

        return products;
    }

    public static List<Product> createFakeProducts() {
        int numberOfProducts = Faker.instance().number().numberBetween(2, 15);
        return createFakeProducts(numberOfProducts);
    }
}
