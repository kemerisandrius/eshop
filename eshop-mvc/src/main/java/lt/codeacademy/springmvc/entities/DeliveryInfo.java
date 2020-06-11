package lt.codeacademy.springmvc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lt.codeacademy.springmvc.services.validator.PhoneNumber;

@Data
@Entity
@Table(name = "Delivery_info")
public class DeliveryInfo {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "mobile", nullable = false)
    @PhoneNumber
    private String mobile;

    @OneToOne(mappedBy = "deliveryInfo")
    private Order order;
}
