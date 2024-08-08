package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.enums.Role;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.exception.EquipementNotFoundException;
import com.itSupport.ITsupportApp.model.*;
import com.itSupport.ITsupportApp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;
    private final EquipementService equipementService;
    private final SignalPanneService signalPanneService;
    private final PanneService panneService;
    private final TicketService ticketService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Admin admin) {
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }

    @PostMapping("/addEquipement")
    public ResponseEntity<Equipement> saveEquipement(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.save(equipement));
    }

    @PutMapping("/updateEquipement")
    public ResponseEntity<?> updateEquipement(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.OK).body(equipementService.update(equipement));
    }

    @DeleteMapping("/deleteEquipement/{id}")
    public ResponseEntity<?> deleteEquipement(@PathVariable("id") Long id) {
        try {
            Equipement deletedEquipement = equipementService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedEquipement);
        } catch (EquipementNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping("/changeEtatEquipement")
    public ResponseEntity<?> changeEtat(@RequestBody Equipement equipement,@PathVariable String etat) {
        return ResponseEntity.status(HttpStatus.OK).body(equipementService.changeEtat(equipement,etat));
    }
    @GetMapping("/getAllPannes")
    public ResponseEntity<?> getAllPannes() {
        try {
            List<Panne> pannes = panneService.getAll();
            return ResponseEntity.ok(pannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/addPanne")
    public ResponseEntity<Panne> savePanne(@RequestBody Panne panne) {
        return ResponseEntity.status(HttpStatus.CREATED).body(panneService.save(panne));
    }
    @PutMapping("/updatePanne")
    public ResponseEntity<Panne> updatePanne(@RequestBody Panne panne) {
        return ResponseEntity.status(HttpStatus.CREATED).body(panneService.save(panne));
    }
    @DeleteMapping("/deletePanne/{id}")
    public ResponseEntity<?> deletePanne(@PathVariable("id") Long id) {
        Panne deletedPanne = panneService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedPanne);
    }
    @GetMapping("/getAllSignalPannes")
    public ResponseEntity<?> getAllSignalPannes() {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanne();
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getAllSignalPannesByEquipement/{id}")
    public ResponseEntity<?> getAllSignalPannes(@PathVariable Long id) {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanneByIdEquipement(id);
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/attribuerTechnicien/{etat}")
    public ResponseEntity<Ticket> attribuerTechnicien(@RequestBody Ticket ticket, Long idTechnicien) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.attribuerTechnicien(ticket,idTechnicien));
    }

    @GetMapping("/getAllTicket")
    public ResponseEntity<?> getAllTicket() {
        try {
            List<Ticket> ticketList = ticketService.getAllTicket();
            return ResponseEntity.ok(ticketList);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
