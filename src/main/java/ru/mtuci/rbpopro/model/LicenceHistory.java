package ru.mtuci.rbpopro.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "licence_history")
public class LicenceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "licence_id", nullable = false)
    private Licence licence;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String status;

    @Column(name = "change_date", nullable = false)
    private Date changeDate;

    @Column(length = 255)
    private String description;

}

