package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.exception.EquipementNotFoundException;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.service.EquipementService;
import com.itSupport.ITsupportApp.service.PanneService;
import com.itSupport.ITsupportApp.service.SignalPanneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forAll")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ForAllController {
    private final EquipementService equipementService;
    private final PanneService panneService;
    private final SignalPanneService signalPanneService;

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
