package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.dto.SignalPanneDto;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.mapper.SignalPanneMapper;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.model.User;
import com.itSupport.ITsupportApp.repository.SignalRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/signalPanne")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SignalPanneController {
    private final SignalPanneService signalPanneService;
    private final PanneService panneService;
    private final UserService userService;
    private final EquipementService equipementService;
    private final SignalPanneMapper signalPanneMapper;
    private final SignalRepository signalRepository;

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
    @PreAuthorize("hasAuthority('TECH')")
    @GetMapping("/getSignalPannesByIdTicket/{id}")
    public ResponseEntity<?> getAllSignalPannesByIdTicket(@PathVariable Long id) {
        try {
            SignalPanne signalPanne = signalPanneService.getAllSignalPanneByIdTicket(id);
            return ResponseEntity.ok(signalPanne);
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
    @PutMapping("/changeEtatSignalPanne/{idSignalPanne}/{etat}")
    public ResponseEntity<?> changeEtat(@PathVariable Long idSignalPanne, @PathVariable String etat) {
        Optional<SignalPanne> signalPanne = signalRepository.findById(idSignalPanne);
        return ResponseEntity.status(HttpStatus.OK).body(signalPanneService.changeEtat(signalPanne.get(),etat));
    }
    @PreAuthorize("hasAuthority('TECH')")
    @GetMapping("/getAllSignalPannesByTechnicien/{id}")
    public ResponseEntity<?> getAllSignalPannesByTechnicien(@PathVariable Long id) {
        try {
            List<SignalPanne> signalPannes = signalPanneService.getAllSignalPanneByIdTechnicien(id);
            return ResponseEntity.ok(signalPanneMapper.toDTO(signalPannes));
        } catch (DatabaseEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
