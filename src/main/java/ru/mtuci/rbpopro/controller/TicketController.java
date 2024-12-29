package ru.mtuci.rbpopro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mtuci.rbpopro.model.Ticket;
import ru.mtuci.rbpopro.service.TicketService;

import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestParam Long licenceId, @RequestParam String deviceId) {
        Optional<Ticket> ticketOptional = ticketService.createTicket(licenceId, deviceId);

        if (ticketOptional.isPresent()) {
            return ResponseEntity.ok(ticketOptional.get());
        }

        return ResponseEntity.badRequest().body("Licence not found or invalid");
    }
}
