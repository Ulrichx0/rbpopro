package ru.mtuci.rbpopro.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails { // Implémente UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Licence> licences;

    // Implémentation des méthodes de UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role)); // Retourne le rôle
    }

    @Override
    public String getPassword() {
        return passwordHash; // Renvoie le hash du mot de passe
    }

    @Override
    public String getUsername() {
        return login; // Utilise le login comme nom d'utilisateur
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Vous pouvez personnaliser la logique
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Vous pouvez personnaliser la logique
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Vous pouvez personnaliser la logique
    }

    @Override
    public boolean isEnabled() {
        return true; // Vous pouvez personnaliser la logique
    }
}
