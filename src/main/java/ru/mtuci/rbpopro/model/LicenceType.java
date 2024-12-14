package ru.mtuci.rbpopro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "license_type")
public class LicenceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "default_duration", nullable = false)
    private Integer defaultDuration;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "licenseType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Licence> licences = new ArrayList<>();


}
