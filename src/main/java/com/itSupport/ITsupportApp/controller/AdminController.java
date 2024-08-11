package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.enums.Role;
import com.itSupport.ITsupportApp.exception.DatabaseEmptyException;
import com.itSupport.ITsupportApp.exception.EquipementNotFoundException;
import com.itSupport.ITsupportApp.model.*;
import com.itSupport.ITsupportApp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {
    private final PasswordEncoder passwordEncoder;
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Admin admin) {
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return new ResponseEntity<>(adminService.save(admin), HttpStatus.CREATED);
    }
}
