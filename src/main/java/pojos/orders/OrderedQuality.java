package pojos.orders;

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
public class OrderedQuality {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "ordered_quantity")
    private Integer orderedQuantity;


}
