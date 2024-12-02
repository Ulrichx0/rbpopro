package ru.mtuci.rbpopro.service;

import org.springframework.stereotype.Service;
import ru.mtuci.rbpopro.model.Licence;
import ru.mtuci.rbpopro.repository.LicenceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LicenceService {
    private final LicenceRepository licenceRepository;

    public LicenceService(LicenceRepository licenceRepository) {
        this.licenceRepository = licenceRepository;
    }

    public List<Licence> getAllLicences() {
        return licenceRepository.findAll();
    }

    public Optional<Licence> getLicenceById(Long id) {
        return licenceRepository.findById(id);
    }

    public Licence saveLicence(Licence licence) {
        return licenceRepository.save(licence);
    }

    public void deleteLicence(Long id) {
        licenceRepository.deleteById(id);
    }

    // Additional business logic methods (if needed) can be added here
}
