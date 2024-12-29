package ru.mtuci.rbpopro.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "licence_types")
public class LicenceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "default_duration", nullable = false)
    private Integer defaultDuration;

    @Column(length = 255)
    private String description;

    @JsonIgnoreProperties("licenceType")
    @OneToMany(mappedBy = "licenceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Licence> licences;

    // Constructor to initialize with name and defaultDuration
    public LicenceType(String name, Integer defaultDuration) {
        this.name = name;
        this.defaultDuration = defaultDuration;
    }

    // Constructor to initialize with all fields
    public LicenceType(Long id, String name, Integer defaultDuration, String description) {
        this.id = id;
        this.name = name;
        this.defaultDuration = defaultDuration;
        this.description = description;
    }
}
