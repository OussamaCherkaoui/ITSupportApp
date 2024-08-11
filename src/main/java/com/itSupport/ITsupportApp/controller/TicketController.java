package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.dto.TicketDto;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.Ticket;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import com.itSupport.ITsupportApp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TicketController {
    private final TicketService ticketService;
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/saveTicket")
    public ResponseEntity<Ticket> signalPanne(@RequestBody Ticket ticket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.save(ticket));
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getTicketsByIdUser/{id}")
    public ResponseEntity<?> getTicketsByIdUser(@PathVariable Long id) {
        try {
            List<Ticket> ticketList = ticketService.getTicketsByIdUser(id);
            return ResponseEntity.ok(ticketList);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/attribuerTechnicien/{idTechnicien}")
    public ResponseEntity<Ticket> attribuerTechnicien(@RequestBody Ticket ticket, Long idTechnicien) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.attribuerTechnicien(ticket,idTechnicien));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllTicket")
    public ResponseEntity<?> getAllTicket() {
        try {
            List<Ticket> ticketList = ticketService.getAllTicket();
            return ResponseEntity.ok(ticketList);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllTicketByIdSignalPanne/{id}")
    public ResponseEntity<?> getAllTicketByIdPanne(@PathVariable Long id) {
        try {
            List<TicketDto> ticketListDto = ticketService.getAllTicketByIdSignalPanne(id);
            return ResponseEntity.ok(ticketListDto);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('TECH')")
    @GetMapping("/getAllTicketByTechnicien/{id}")
    public ResponseEntity<?> getAllTicketByTechnicien(@PathVariable Long id) {
        try {
            List<Ticket> ticketList = ticketService.getAllTicketByTechnicien(id);
            return ResponseEntity.ok(ticketList);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
