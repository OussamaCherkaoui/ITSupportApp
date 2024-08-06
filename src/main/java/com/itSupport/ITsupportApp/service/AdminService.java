package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.model.Admin;
import com.itSupport.ITsupportApp.model.Utilisateur;
import com.itSupport.ITsupportApp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    public Admin save(Admin admin)
    {
        return adminRepository.save(admin);
    }

}
