package ru.mtuci.rbpopro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mtuci.rbpopro.model.LicenceType;
import ru.mtuci.rbpopro.repository.LicenceTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LicenceTypeService {

    private final LicenceTypeRepository licenceTypeRepository;

    @Autowired
    public LicenceTypeService(LicenceTypeRepository licenceTypeRepository) {
        this.licenceTypeRepository = licenceTypeRepository;
    }

    // Get all LicenceTypes
    public List<LicenceType> getAllLicenceTypes() {
        return licenceTypeRepository.findAll();
    }

    // Get a LicenceType by its ID
    public Optional<LicenceType> getLicenceTypeById(Long id) {
        return licenceTypeRepository.findById(id);
    }

    // Save a new LicenceType
    public LicenceType saveLicenceType(LicenceType licenceType) {
        return licenceTypeRepository.save(licenceType);
    }

    // Update an existing LicenceType
    public LicenceType updateLicenceType(Long id, LicenceType updatedLicenceType) {
        if (licenceTypeRepository.existsById(id)) {
            updatedLicenceType.setId(id);
            return licenceTypeRepository.save(updatedLicenceType);
        }
        return null;
    }

    // Delete a LicenceType by its ID
    public void deleteLicenceType(Long id) {
        licenceTypeRepository.deleteById(id);
    }
}
