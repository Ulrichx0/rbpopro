package ru.mtuci.rbpopro.service;

import org.springframework.stereotype.Service;
import ru.mtuci.rbpopro.model.Licence;
import ru.mtuci.rbpopro.model.Ticket;
import ru.mtuci.rbpopro.repository.LicenceRepository;

import java.util.Date;
import java.util.Optional;

@Service
public class TicketService {

    private final LicenceRepository licenceRepository;

    public TicketService(LicenceRepository licenceRepository) {
        this.licenceRepository = licenceRepository;
    }

    public Optional<Ticket> createTicket(Long licenceId, String deviceId) {
        Optional<Licence> licenceOptional = licenceRepository.findById(licenceId);

        if (licenceOptional.isPresent()) {
            Licence licence = licenceOptional.get();

            // Generate ticket
            Ticket ticket = new Ticket();
            Date currentDate = new Date();

            ticket.setServerDate(currentDate);
            ticket.setActivationDate(licence.getFirstActivationDate());
            ticket.setExpirationDate(licence.getEndingDate());
            ticket.setTicketLifetime((licence.getEndingDate().getTime() - currentDate.getTime()) / 1000); // Lifetime in seconds
            ticket.setUserId(licence.getOwnerId());
            ticket.setDeviceId(deviceId);
            ticket.setLicenceBlocked(licence.getBlocked());

            // You can add logic to generate a digital signature
            ticket.setDigitalSignature(generateDigitalSignature(ticket));

            return Optional.of(ticket);
        }

        return Optional.empty();
    }

    private String generateDigitalSignature(Ticket ticket) {
        // Implement digital signature logic (e.g., hash of ticket details + secret key)
        return "dummy-signature"; // Replace with actual signature logic
    }
}
