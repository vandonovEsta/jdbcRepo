package pojos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public @Data class Customer {
    @Column(name = "customer_id")
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
    @Column(name = "customer_profile_status")
    private Boolean customerProfileStatus;
    @Column(name = "datetime_created")
    private Date dateTimeCreated;
    @Column(name = "datetime_deactivated")
    private Date dateTimeDeactivated;
    @Column(name = "reason_for_deactivation")
    private String reasonForDeactivation;
    @Column(name = "notes")
    private String notes;
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "order_id")
    private Integer orderId;

}
