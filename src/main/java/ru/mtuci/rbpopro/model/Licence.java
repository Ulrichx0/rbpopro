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

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "type_id")
    private Long typeId;

    @Column(name = "first_activation_date")
    private Date firstActivationDate;

    @Column(name = "ending_date")
    private Date endingDate;

    @Column(name = "blocked")
    private Boolean blocked;

    @Column(name = "device_count")
    private Integer deviceCount;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "owner_id", insertable = false, updatable = false)
    private Long ownerId;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    @JsonBackReference
    private User owner;

}