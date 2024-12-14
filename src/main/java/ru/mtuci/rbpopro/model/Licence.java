package ru.mtuci.rbpopro.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name="licences")
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Many licences can belong to one user

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private LicenceType licenceType; // Many licences can share one licence type

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Many licences can be associated with one product

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

    // Getters and Setters for relationships
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LicenceType getLicenceType() {
        return licenceType;
    }

    public void setLicenceType(LicenceType licenceType) {
        this.licenceType = licenceType;
    }

    // Optional ID-based getters and setters
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
    // Getter for the owner entity
    public User getOwner() {
        return owner;
    }

    // Setter for the owner entity
    public void setOwner(User owner) {
        this.owner = owner;
    }

    // Getter for the owner ID
    public Long getOwnerId() {
        return owner != null ? owner.getId() : null;
    }

    // Setter for the owner ID
    public void setOwnerId(Long ownerId) {
        if (this.owner == null) {
            this.owner = new User(); // Initialize owner if null
        }
        this.owner.setId(ownerId);
    }

    public void setTypeId(Long typeId) {
        if (this.licenceType == null) {
            this.licenceType = new LicenceType();
        }
        this.licenceType.setId(typeId);
    }
}