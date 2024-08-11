package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.exception.EquipementNotFoundException;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.service.EquipementService;
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
@RequestMapping("/equipement")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EquipementController {

    private final EquipementService equipementService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addEquipement")
    public ResponseEntity<Equipement> saveEquipement(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(equipementService.save(equipement));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/updateEquipement")
    public ResponseEntity<?> updateEquipement(@RequestBody Equipement equipement) {
        return ResponseEntity.status(HttpStatus.OK).body(equipementService.update(equipement));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/deleteEquipement/{id}")
    public ResponseEntity<?> deleteEquipement(@PathVariable("id") Long id) {
        try {
            Equipement deletedEquipement = equipementService.delete(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedEquipement);
        } catch (EquipementNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/changeEtatEquipement/{etat}")
    public ResponseEntity<?> changeEtat(@RequestBody Equipement equipement,@PathVariable String etat) {
        return ResponseEntity.status(HttpStatus.OK).body(equipementService.changeEtat(equipement,etat));
    }
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN', 'TECH')")
    @GetMapping("/getAllEquipements")
    public ResponseEntity<?> getAllEquipement() {
        try {
            List<Equipement> equipements = equipementService.getAll();
            return ResponseEntity.ok(equipements);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
