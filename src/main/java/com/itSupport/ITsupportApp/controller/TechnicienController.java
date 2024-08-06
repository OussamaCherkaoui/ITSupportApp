package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.enums.Role;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.Admin;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.model.Technicien;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import com.itSupport.ITsupportApp.service.TechnicienService;
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
}
