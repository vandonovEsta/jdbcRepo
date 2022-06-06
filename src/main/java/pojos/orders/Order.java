package pojos.orders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    private Integer id;
    @Column(name = "is_order_completed")
    private Boolean isOrderCompleted;
    @Column(name = "is_order_payed")
    private Boolean isOrderPayed;
    @Column(name = "date_of_order")
    private Date dateOfOrder;
    @Column(name = "date_order_confirmed")
    private Date dateOrderConfirmed;
    @Column(name = "customer_id")
    private Integer customerId;


}
