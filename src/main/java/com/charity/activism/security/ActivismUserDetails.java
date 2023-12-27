package com.charity.activism.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.charity.activism.models.ActivismUser;
import com.charity.activism.models.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivismUserDetails implements UserDetails {

    private final ActivismUser aUser;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return aUser.getRoles().stream()
            .map(Role::getNameRole)
            .map(SimpleGrantedAuthority::new)
            .toList();
        
    }

    @Override
    public String getPassword() {
       
        return aUser.getPassword();
    }

    @Override
    public String getUsername() {
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
