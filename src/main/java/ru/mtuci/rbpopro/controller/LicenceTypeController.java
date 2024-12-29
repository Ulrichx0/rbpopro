package ru.mtuci.rbpopro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpopro.model.LicenceType;
import ru.mtuci.rbpopro.service.LicenceTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licence-types")
public class LicenceTypeController {

    private final LicenceTypeService licenceTypeService;

    public LicenceTypeController(LicenceTypeService licenceTypeService) {
        this.licenceTypeService = licenceTypeService;
    }

    // Get all LicenceTypes
    @GetMapping
    public List<LicenceType> getAllLicenceTypes() {
        return licenceTypeService.getAllLicenceTypes();
    }

    // Get a LicenceType by its ID
    @GetMapping("/{id}")
    public ResponseEntity<LicenceType> getLicenceTypeById(@PathVariable Long id) {
        Optional<LicenceType> licenceType = licenceTypeService.getLicenceTypeById(id);
        return licenceType.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new LicenceType
    @PostMapping
    public ResponseEntity<LicenceType> createLicenceType(@RequestBody LicenceType licenceType) {
        LicenceType createdLicenceType = licenceTypeService.saveLicenceType(licenceType);
        return ResponseEntity.ok(createdLicenceType);
    }

    // Update an existing LicenceType
    @PutMapping("/{id}")
    public ResponseEntity<LicenceType> updateLicenceType(@PathVariable Long id, @RequestBody LicenceType updatedLicenceType) {
        LicenceType licenceType = licenceTypeService.updateLicenceType(id, updatedLicenceType);
        return licenceType != null ? ResponseEntity.ok(licenceType) : ResponseEntity.notFound().build();
    }

    // Delete a LicenceType by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicenceType(@PathVariable Long id) {
        licenceTypeService.deleteLicenceType(id);
        return ResponseEntity.noContent().build();
    }
}
