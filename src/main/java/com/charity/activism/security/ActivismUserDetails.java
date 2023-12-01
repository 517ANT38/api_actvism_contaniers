package com.charity.activism.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.charity.activism.models.ActivismUser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivismUserDetails implements UserDetails {

    private final ActivismUser aUser;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(aUser.getRole().getNameRole()));
    }

    @Override
    public String getPassword() {
       
        return aUser.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return aUser.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
