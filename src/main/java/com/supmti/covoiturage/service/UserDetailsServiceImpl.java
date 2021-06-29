package com.supmti.covoiturage.service;

import com.supmti.covoiturage.model.Internaute;
import com.supmti.covoiturage.repo.InternauteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private InternauteRepo internauteRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Internaute internaute= internauteRepo.findByUsername(username).orElseThrow(()->
                new UsernameNotFoundException("Ce Utilisateur n'existe pas!"+username));
        return new org.springframework.security.core.userdetails.User(internaute.getUsername(),
                internaute.getPassword(),
                true, true, true, true,
                getAuthorities("ROLE_USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }
}
