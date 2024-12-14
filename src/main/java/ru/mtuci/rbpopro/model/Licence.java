package ru.mtuci.rbpopro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "licences")
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private LicenceType licenceType;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "first_activation_date")
    private Date firstActivationDate;

    @Column(name = "ending_date")
    private Date endingDate;

    @Column(nullable = false)
    private Boolean blocked;

    @Column(name = "device_count", nullable = false)
    private Integer deviceCount;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(nullable = false)
    private Integer duration;

    @Column(length = 255)
    private String description;

    // Utility methods for ID-based relationships
    public Long getProductId() {
        return product != null ? product.getId() : null;
    }

    public void setProductId(Long productId) {
        if (this.product == null) {
            this.product = new Product();
        }
        this.product.setId(productId);
    }

    public Long getTypeId() {
        return licenceType != null ? licenceType.getId() : null;
    }

    public void setTypeId(Long typeId) {
        if (this.licenceType == null) {
            this.licenceType = new LicenceType();
        }
        this.licenceType.setId(typeId);
    }

    public Long getOwnerId() {
        return owner != null ? owner.getId() : null;
    }

    public void setOwnerId(Long ownerId) {
        if (this.owner == null) {
            this.owner = new User();
        }
        this.owner.setId(ownerId);
    }
}
