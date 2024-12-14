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
    private User user; // Many licenses can belong to one user

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private LicenceType licenceType; // Many licenses can share one license type

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Many licenses can be associated with one product

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

}