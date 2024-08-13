package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.dto.SignalPanneDto;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.mapper.SignalPanneMapper;
import com.itSupport.ITsupportApp.model.Panne;
import com.itSupport.ITsupportApp.model.SignalPanne;
import com.itSupport.ITsupportApp.repository.PanneRepository;
import com.itSupport.ITsupportApp.repository.SignalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SignalPanneService {

    private final SignalRepository signalPanneRepository;
    private final SignalPanneMapper signalPanneMapper;

    public SignalPanne save(SignalPanne signalPanne) {
        signalPanne.setDateEnregistrement(LocalDateTime.now());
        return signalPanneRepository.save(signalPanne);
    }
    public List<SignalPanne> getAllSignalPanneByIdUser(Long idUser) {
        List<SignalPanne> signalPannes = signalPanneRepository.findByUser_Id(idUser);
        if (signalPannes.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return signalPannes;
    }
    public List<SignalPanneDto> getAllSignalPanneByIdEquipement(Long idEquipement) {
        List<SignalPanne> signalPannes = signalPanneRepository.findByEquipement_Id(idEquipement);
        if (signalPannes.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return signalPanneMapper.toDTO(signalPannes);
    }

    public List<SignalPanne> getAllSignalPanne() {
        List<SignalPanne> signalPannes = signalPanneRepository.findAll();
        if (signalPannes.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return signalPannes;
    }

    public SignalPanne changeEtat(SignalPanne signalPanne,String etat)
    {
        signalPanne.setEtat(etat);
        return signalPanneRepository.save(signalPanne);
    }

    public List<SignalPanne> getAllSignalPanneByIdTechnicien(Long id) {
        List<SignalPanne> signalPannes = signalPanneRepository.findByIdTechnicien(id);
        if (signalPannes.isEmpty()) {
            throw new DatabaseEmptyException();
        }
        return signalPannes;
    }

    public SignalPanne getAllSignalPanneByIdTicket(Long id) {
        SignalPanne signalPanne= signalPanneRepository.findByIdTicket(id);
        return signalPanne;
    }
}
