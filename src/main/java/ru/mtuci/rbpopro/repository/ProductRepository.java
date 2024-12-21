package ru.mtuci.rbpopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.rbpopro.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);

}
