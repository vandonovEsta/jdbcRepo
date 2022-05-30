package pojos.products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "available_quantity")
    private Long availableQuantity;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "price_with_vat")
    private Double priceWithVat;
    @Column(name = "price_withouth_vat")
    private Double priceWithoutVat;
    @Column(name = "is_product_in_stock")
    private Boolean isProductInStock;
    @Column(name = "warehouse")
    private String warehouse;
    @Column(name = "supplier_id")
    private Long supplierId;


}
