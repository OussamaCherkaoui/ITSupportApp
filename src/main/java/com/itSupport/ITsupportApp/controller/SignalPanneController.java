package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.dto.SignalPanneDto;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signalPanne")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SignalPanneController {
    private final SignalPanneService signalPanneService;
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/saveSignalPanne")
    public ResponseEntity<SignalPanne> signalPanne(@RequestBody SignalPanne signalPanne) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signalPanneService.save(signalPanne));
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/getAllSignalPannesByIdUser/{id}")
    public ResponseEntity<?> getAllSignalPannesByIdUser(@PathVariable Long id) {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanneByIdUser(id);
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/getAllSignalPannes")
    public ResponseEntity<?> getAllSignalPannes() {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanne();
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAllSignalPannesByIdEquipement/{id}")
    public ResponseEntity<?> getAllSignalPannesByIdEquipement(@PathVariable Long id) {
        try {
            List<SignalPanneDto> signalPannesDto = signalPanneService.getAllSignalPanneByIdEquipement(id);
            return ResponseEntity.ok(signalPannesDto);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PreAuthorize("hasAuthority('TECH')")
    @PutMapping("/changeEtatSignalPanne")
    public ResponseEntity<?> changeEtat(@RequestBody SignalPanne signalPanne, @PathVariable String etat) {
        return ResponseEntity.status(HttpStatus.OK).body(signalPanneService.changeEtat(signalPanne,etat));
    }
    @PreAuthorize("hasAuthority('TECH')")
    @GetMapping("/getAllSignalPannesByTechnicien/{id}")
    public ResponseEntity<?> getAllSignalPannesByTechnicien(@PathVariable Long id) {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanneByIdTechnicien(id);
            return ResponseEntity.ok(signalPannes);
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
