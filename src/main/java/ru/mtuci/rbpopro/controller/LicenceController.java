package ru.mtuci.rbpopro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpopro.model.*;
import ru.mtuci.rbpopro.service.LicenceService;
import ru.mtuci.rbpopro.repository.ProductRepository;
import ru.mtuci.rbpopro.repository.UserRepository;
import ru.mtuci.rbpopro.repository.LicenceTypeRepository;

import java.util.*;

@RestController
@RequestMapping("/api/licences")
public class LicenceController {

    private final LicenceService licenceService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final LicenceTypeRepository licenceTypeRepository;

    // Inject the repositories in the constructor
    public LicenceController(LicenceService licenceService,
                             ProductRepository productRepository,
                             UserRepository userRepository,
                             LicenceTypeRepository licenceTypeRepository) {
        this.licenceService = licenceService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.licenceTypeRepository = licenceTypeRepository;
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

    @GetMapping("/{id}/ticket")
    public ResponseEntity<Ticket> getLicenceTicket(@PathVariable Long id) {
        Optional<Licence> optionalLicence = licenceService.getLicenceById(id);

        if (optionalLicence.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Licence licence = optionalLicence.get();
        Ticket ticket = new Ticket();

        ticket.setServerDate(new Date());
        ticket.setTicketLifetime(3600L); // Example ticket lifetime: 1 hour
        ticket.setActivationDate(licence.getFirstActivationDate());
        ticket.setExpirationDate(licence.getEndingDate());
        ticket.setUserId(licence.getOwnerId());
        ticket.setDeviceId("device-placeholder"); // Add your device ID logic here
        ticket.setLicenceBlocked(licence.getBlocked());
        ticket.setDigitalSignature("dummy_signature"); // You can replace with real signature logic

        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<LicenceResponse> createLicence(@RequestBody CreateLicenseRequest licenceRequest) {
        // Fetch related entities from repositories
        Product product = productRepository.findById(licenceRequest.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + licenceRequest.getProductId()));

        User owner = userRepository.findById(licenceRequest.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found with ID: " + licenceRequest.getOwnerId()));

        LicenceType licenceType = licenceTypeRepository.findById(licenceRequest.getLicenceTypeId())
                .orElseThrow(() -> new IllegalArgumentException("LicenceType not found with ID: " + licenceRequest.getLicenceTypeId()));

        // Create a new Licence entity
        Licence licence = new Licence();
        licence.setProduct(product);  // Set the entire Product entity
        licence.setOwner(owner);      // Set the entire Owner entity
        licence.setLicenceType(licenceType);  // Set the entire LicenceType entity
        licence.setDeviceCount(licenceRequest.getDeviceCount());
        licence.setBlocked(false);  // Default blocked value, can adjust based on business logic
        licence.setCode(UUID.randomUUID().toString());  // Generate a unique licence code
        licence.setDuration(licenceType.getDefaultDuration());  // Set duration based on LicenceType
        licence.setDescription("Default licence description");  // Optional: Add a description or set as needed
        // Save the Licence and return the response
        Licence createdLicence = licenceService.saveLicence(licence);

        LicenceResponse licenceResponse = new LicenceResponse();
        licenceResponse.setCode(licence.getCode());
        licenceResponse.setLicenceType(licence.getLicenceType().getName());
        licenceResponse.setOwnerId(licence.getId());
        licenceResponse.setProductName(licence.getProduct().getName());
        licenceResponse.setDeviceCount(licence.getDeviceCount());
        licenceResponse.setDuration(licence.getDuration());
        return ResponseEntity.ok(licenceResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Licence> updateLicence(@PathVariable Long id, @RequestBody Licence licenceDetails) {
        Optional<Licence> optionalLicence = licenceService.getLicenceById(id);

        if (optionalLicence.isPresent()) {
            Licence licence = optionalLicence.get();
            licence.setCode(licenceDetails.getCode());
            licence.setProduct(licenceDetails.getProduct()); // Correct: set the full Product entity
            licence.setLicenceType(licenceDetails.getLicenceType()); // Correct: set the full LicenceType entity
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
