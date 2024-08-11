package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.service.PanneService;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import com.itSupport.ITsupportApp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/panne")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PanneController {

    private final PanneService panneService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'TECH')")
    @GetMapping("/getAllPannes")
    public ResponseEntity<?> getAllPannes() {
        try {
            List<Panne> pannes = panneService.getAll();
            return ResponseEntity.ok(pannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addPanne")
    public ResponseEntity<Panne> savePanne(@RequestBody Panne panne) {
        return ResponseEntity.status(HttpStatus.CREATED).body(panneService.save(panne));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updatePanne")
    public ResponseEntity<Panne> updatePanne(@RequestBody Panne panne) {
        return ResponseEntity.status(HttpStatus.OK).body(panneService.updatePanne(panne));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deletePanne/{id}")
    public ResponseEntity<?> deletePanne(@PathVariable("id") Long id) {
        Panne deletedPanne = panneService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedPanne);
    }
}
