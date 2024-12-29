package ru.mtuci.rbpopro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    // Owner of the licence
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)  // Ensuring user is required
    private User user;

    // Licence type information
    @ManyToOne
    @JsonIgnoreProperties("licences")
    @JoinColumn(name = "type_id", nullable = false)  // LicenceType is required
    private LicenceType licenceType;

    // Product associated with the licence
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Product is required
    private Product product;

    // Dates for the licence (activation and expiration)
    @Column(name = "first_activation_date")
    private Date firstActivationDate;

    @Column(name = "ending_date")
    private Date endingDate;

    // Licence status: whether it is blocked or not
    @Column(nullable = false)
    private Boolean blocked;

    // Device count associated with the licence
    @Column(name = "device_count", nullable = false)
    private Integer deviceCount;

    // Owner of the product/license
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)  // Owner should be required
    private User owner;

    // Duration of the licence in days, months, etc.
    @Column(nullable = false)
    private Integer duration;

    // Optional description of the licence
    @Column(length = 500)  // Increased length for a more flexible description
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
