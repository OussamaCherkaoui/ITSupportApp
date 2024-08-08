package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.enums.Role;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.*;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import com.itSupport.ITsupportApp.service.TicketService;
import com.itSupport.ITsupportApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final SignalPanneService signalPanneService;
    private final TicketService ticketService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @PostMapping("/signalPanne")
    public ResponseEntity<SignalPanne> signalPanne(@RequestBody SignalPanne signalPanne) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signalPanneService.save(signalPanne));
    }
    @GetMapping("/getAllSignalPannesByUser/{id}")
    public ResponseEntity<?> getAllSignalPannes(@PathVariable Long id) {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanneByIdUser(id);
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/saveTicket")
    public ResponseEntity<Ticket> signalPanne(@RequestBody Ticket ticket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.save(ticket));
    }
    @GetMapping("/getTicketsByIdUser/{id}")
    public ResponseEntity<?> getTicketsByIdUser(@PathVariable Long id) {
        try {
            List<Ticket> ticketList = ticketService.getTicketsByIdUser(id);
            return ResponseEntity.ok(ticketList);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
