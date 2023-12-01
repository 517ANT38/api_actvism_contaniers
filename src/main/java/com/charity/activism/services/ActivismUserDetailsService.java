package com.charity.activism.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.charity.activism.repositories.ActivismUserRepo;
import com.charity.activism.security.ActivismUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ActivismUserDetailsService implements UserDetailsService {

    private final ActivismUserRepo aRepo;



    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var v = aRepo.findByLogin(s);

        if (v.isEmpty())
            throw new UsernameNotFoundException("User not found");

        return new ActivismUserDetails(v.get());
    }
}