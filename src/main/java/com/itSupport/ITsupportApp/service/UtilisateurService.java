package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.model.Utilisateur;
import com.itSupport.ITsupportApp.repository.UserRepository;
import com.itSupport.ITsupportApp.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(utilisateur.getUsername(), utilisateur.getPassword(), utilisateur.getAuthorities());
    }

    public Utilisateur getUserByUsername(String username) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return utilisateur;
    }

    public Utilisateur getUserById(Long id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return utilisateur.get();
    }
}
