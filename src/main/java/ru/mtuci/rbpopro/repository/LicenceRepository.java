package ru.mtuci.rbpopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mtuci.rbpopro.model.Licence;

public interface LicenceRepository extends JpaRepository<Licence,Long> {
}
