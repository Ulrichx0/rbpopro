package ru.mtuci.rbpopro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpopro.model.Licence;
import ru.mtuci.rbpopro.service.LicenceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licences")
public class LicenceController {
    private final LicenceService licenceService;

    public LicenceController(LicenceService licenceService) {
        this.licenceService = licenceService;
    }

    @GetMapping
    public List<Licence> getAllLicences() {
        return licenceService.getAllLicences();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Licence> getLicenceById(@PathVariable Long id) {
        Optional<Licence> licence = licenceService.getLicenceById(id);
        return licence.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Licence createLicence(@RequestBody Licence licence) {
        return licenceService.saveLicence(licence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Licence> updateLicence(@PathVariable Long id, @RequestBody Licence licenceDetails) {
        Optional<Licence> optionalLicence = licenceService.getLicenceById(id);

        if (optionalLicence.isPresent()) {
            Licence licence = optionalLicence.get();
            licence.setCode(licenceDetails.getCode());
            licence.setProductId(licenceDetails.getProductId());
            licence.setTypeId(licenceDetails.getTypeId());
            licence.setFirstActivationDate(licenceDetails.getFirstActivationDate());
            licence.setEndingDate(licenceDetails.getEndingDate());
            licence.setBlocked(licenceDetails.getBlocked());
            licence.setDeviceCount(licenceDetails.getDeviceCount());
            licence.setDuration(licenceDetails.getDuration());
            licence.setDescription(licenceDetails.getDescription());

            Licence updatedLicence = licenceService.saveLicence(licence);
            return ResponseEntity.ok(updatedLicence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicence(@PathVariable Long id) {
        if (licenceService.getLicenceById(id).isPresent()) {
            licenceService.deleteLicence(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
