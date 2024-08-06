package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.exception.EquipementNotFoundException;
import com.itSupport.ITsupportApp.exception.PanneNotFoundException;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.repository.PanneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PanneService {
    private final PanneRepository panneRepository;

    public List<Panne> getAll() {
        List<Panne> pannes = panneRepository.findAll();
        if (pannes.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return pannes;
    }
    public Panne save(Panne panne) {
        panne.setDateCreation(LocalDate.now());
        return panneRepository.save(panne);
    }

    public Panne delete(Long id) throws PanneNotFoundException {
        Panne panne = panneRepository.findById(id).orElseThrow(PanneNotFoundException::new);
        panneRepository.delete(panne);
        return panne;
    }
}
