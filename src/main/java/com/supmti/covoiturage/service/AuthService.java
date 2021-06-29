package com.supmti.covoiturage.service;

import com.supmti.covoiturage.dto.LoginRequest;
import com.supmti.covoiturage.dto.RegisterRequest;
import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.repo.InternauteRepo;
import com.supmti.covoiturage.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private InternauteRepo internauteRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    private final InternauteService internauteService;

    public AuthService(InternauteService internauteService){
        this.internauteService = internauteService;
    }

    public void signup(RegisterRequest registerRequest) {
        Internaute internaute=new Internaute();
        internaute.setCin(registerRequest.getCin());
        internaute.setNom(registerRequest.getNom());
        internaute.setPrenom(registerRequest.getPrenom());
        String avatar = registerRequest.getPrenom().substring(0,1)+registerRequest.getNom().substring(0,1);
        internaute.setAvatar(avatar.toUpperCase());
        internaute.setTel(registerRequest.getTel());
        internaute.setUsername(registerRequest.getEmail());
        internaute.setEmail(registerRequest.getEmail());
        internaute.setDate_creation(new Date());
        internaute.setSexe(registerRequest.getSexe());
        internaute.setDate_naiss(registerRequest.getDate_naiss());
        internaute.setPassword(encodePassword(registerRequest.getPassword()));
        internaute.setVerifie("En Attend");
        internauteRepo.save(internaute);
    }
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

     Internaute i = internauteService.findInternauteByUsername(loginRequest.getUsername());
        System.out.println(i);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(authenticationToken, loginRequest.getUsername(),i.getId());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
