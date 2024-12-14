package ru.mtuci.rbpopro.model;

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

    @OneToMany(mappedBy = "licenceType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Licence> licences;
}
