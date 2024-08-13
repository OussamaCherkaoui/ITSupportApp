package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.exception.EquipementNotFoundException;
import com.itSupport.ITsupportApp.model.Equipement;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.repository.EquipementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipementService {
    private final EquipementRepository equipementRepository;

    public List<Equipement> getAll() {
        List<Equipement> equipements = equipementRepository.findAll();
        if (equipements.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return equipements;
    }
    public Equipement save(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public Equipement update(Equipement equipement) {
        return equipementRepository.save(equipement);
    }

    public Equipement getById(Long id) throws EquipementNotFoundException {
        return equipementRepository.findById(id).orElseThrow(EquipementNotFoundException::new);
    }
    public Equipement delete(Long id) throws EquipementNotFoundException {
        Equipement equipement = equipementRepository.findById(id).orElseThrow(EquipementNotFoundException::new);
        equipementRepository.delete(equipement);
        return equipement;
    }
    public Equipement changeEtat(Equipement equipement, String etat)
    {
        equipement.setEtat(etat);
        return equipementRepository.save(equipement);
    }

    public Equipement getByIdSignalPanne(Long id) {
        Equipement equipement = equipementRepository.findByIdSignalPanne(id);
        return equipement;
    }
}
