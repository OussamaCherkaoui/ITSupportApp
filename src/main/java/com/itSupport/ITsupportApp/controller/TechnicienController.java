package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.enums.Role;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.*;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import com.itSupport.ITsupportApp.service.TechnicienService;
import com.itSupport.ITsupportApp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicien")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TechnicienController {
    private final PasswordEncoder passwordEncoder;
    private final TechnicienService technicienService;
    private final SignalPanneService signalPanneService;
    private final TicketService ticketService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Technicien technicien) {
        technicien.setRole(Role.TECH);
        technicien.setPassword(passwordEncoder.encode(technicien.getPassword()));
        return new ResponseEntity<>(technicienService.save(technicien), HttpStatus.CREATED);
    }
    @PutMapping("/changeEtatSignalPanne")
    public ResponseEntity<?> changeEtat(@RequestBody SignalPanne signalPanne, @PathVariable String etat) {
        return ResponseEntity.status(HttpStatus.OK).body(signalPanneService.changeEtat(signalPanne,etat));
    }
    @GetMapping("/getAllSignalPannesByTechnicien/{id}")
    public ResponseEntity<?> getAllSignalPannes(@PathVariable Long id) {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanneByIdTechnicien(id);
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
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
