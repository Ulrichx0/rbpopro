package ru.mtuci.rbpopro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.rbpopro.model.Licence;
import ru.mtuci.rbpopro.repository.LicenceRepository;
import ru.mtuci.rbpopro.repository.ProductRepository;
import ru.mtuci.rbpopro.repository.UserRepository;
import ru.mtuci.rbpopro.repository.LicenceTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LicenceService {

    private final LicenceRepository licenceRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LicenceTypeRepository licenceTypeRepository;

    // Constructor injection for the required repositories
    @Autowired
    public LicenceService(LicenceRepository licenceRepository,
                          ProductRepository productRepository,
                          UserRepository userRepository,
                          LicenceTypeRepository licenceTypeRepository) {
        this.licenceRepository = licenceRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.licenceTypeRepository = licenceTypeRepository;
    }

    // Method to get all licences
    public List<Licence> getAllLicences() {
        return licenceRepository.findAll();
    }

    // Method to get a Licence by ID
    public Optional<Licence> getLicenceById(Long id) {
        return licenceRepository.findById(id);
    }

    // Method to save a new Licence (create or update)
    public Licence saveLicence(Licence licence) {

        return licenceRepository.save(licence);
    }

    // Method to delete a Licence by ID
    public void deleteLicence(Long id) {
        licenceRepository.deleteById(id);
    }

    // Additional business methods can be added if needed

}
