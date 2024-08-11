package com.itSupport.ITsupportApp.controller;

import com.itSupport.ITsupportApp.config.AuthenticationResponse;
import com.itSupport.ITsupportApp.exception.UserNotFoundException;
import com.itSupport.ITsupportApp.model.AuthenticationRequest;
import com.itSupport.ITsupportApp.model.Utilisateur;
import com.itSupport.ITsupportApp.service.UtilisateurService;
import com.itSupport.ITsupportApp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateur")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UtilisateurController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurService utilisateurService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
        public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        final UserDetails userDetails = utilisateurService.loadUserByUsername(authenticationRequest.getUsername());
        Utilisateur utilisateur = utilisateurService.getUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails,utilisateur.getRole());
        return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/getIdByUserName/{username}")
    public ResponseEntity<?> getIdByUserName(@PathVariable("username") String username) {
        try {
            Utilisateur user = utilisateurService.getUserByUsername(username);
            return ResponseEntity.ok(user.getId());
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getUserByIdUser/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) {
        try {
            Utilisateur user = utilisateurService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
