package ru.mtuci.rbpopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.rbpopro.model.LicenceType;

public interface LicenceTypeRepository extends JpaRepository<LicenceType, Long> {
    LicenceType findByName(String name);
}
