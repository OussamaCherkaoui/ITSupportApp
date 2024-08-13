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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllTechnicien")
    public ResponseEntity<?> getAllTechnicien() {
        List<Technicien> technicienList = technicienService.getAll();
        return new ResponseEntity<>(technicienList, HttpStatus.OK);
    }



}
