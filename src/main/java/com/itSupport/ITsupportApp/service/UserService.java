package com.itSupport.ITsupportApp.service;

import com.itSupport.ITsupportApp.model.User;
import com.itSupport.ITsupportApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;

    public User save(User user)
    {
        return userRepository.save(user);
    }

}
