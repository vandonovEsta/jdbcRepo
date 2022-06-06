package pojos.suppliers;

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
public class Supplier {
    @Id
    @Column(name = "supplier_id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private Integer age;
    @Column(name = "gdpr")
    private Boolean gdpr;
    @Column(name = "supplier_profile_status")
    private Boolean customerProfileStatus;
    @Column(name = "datetime_created")
    private Date dateTimeCreated;
    @Column(name = "datetime_deactivated")
    private Date dateTimeDeactivated;
    @Column(name = "reason_for_deactivation")
    private String reasonForDeactivation;
    @Column(name = "notes")
    private String notes;
    @Column(name = "addres_id")
    private Integer addressId;
    @Column(name = "product_id")
    private Integer orderId;
}
