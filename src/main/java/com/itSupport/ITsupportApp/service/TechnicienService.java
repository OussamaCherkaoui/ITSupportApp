package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.model.Technicien;
import com.itSupport.ITsupportApp.model.User;
import com.itSupport.ITsupportApp.repository.TechnicienRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicienService {
    private final TechnicienRepository technicienRepository;

    public Technicien save(Technicien technicien)
    {
        return technicienRepository.save(technicien);
    }
    public Technicien getTechnicienById(Long idTechnicien) {
        return technicienRepository.findById(idTechnicien).get();
    }
}
