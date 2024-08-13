package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.dto.SignalPanneDto;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.model.User;
import com.itSupport.ITsupportApp.service.EquipementService;
import com.itSupport.ITsupportApp.service.PanneService;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import com.itSupport.ITsupportApp.service.UserService;
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
    private final PanneService panneService;
    private final UserService userService;
    private final EquipementService equipementService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/saveSignalPanne/{idUser}/{idEquipement}/{panneId}")
    public ResponseEntity<SignalPanne> signalPanne(@PathVariable Long idUser,@PathVariable Long idEquipement,@PathVariable Long panneId) {
        Panne panne = panneService.getById(panneId);
        User user = userService.getById(idUser);
        Equipement equipement = equipementService.getById(idEquipement);
        SignalPanne signalPanne = new SignalPanne();
        signalPanne.setPanne(panne);
        signalPanne.setUser(user);
        signalPanne.setEquipement(equipement);
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
