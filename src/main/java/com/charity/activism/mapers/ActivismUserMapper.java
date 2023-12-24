package com.charity.activism.mapers;

import org.springframework.stereotype.Component;

import com.charity.activism.dto.ActivismUserDtioOut;
import com.charity.activism.dto.ActivismUserDto;
import com.charity.activism.models.ActivismUser;
import com.charity.activism.models.Subdivision;

@Component
public class ActivismUserMapper {
    
    public ActivismUserDtioOut toDto(ActivismUser aDto){
        var a = new ActivismUserDtioOut();
        a.setFirstName(aDto.getFirstName());
        a.setLastName(aDto.getLastName());
        a.setMiddleName(aDto.getMiddleName());
        a.setLogin(aDto.getLogin());
        a.setRole(aDto.getRole().getNameRole());
        a.setSubdivision(aDto.getSubdivision().getName());
        return a;
    }

    public ActivismUser toEntity(ActivismUserDto aDto){
        var a = new ActivismUser();
        a.setFirstName(aDto.getFirstName());
        a.setLastName(aDto.getLastName());
        a.setMiddleName(aDto.getMiddleName());
        a.setPassword(aDto.getPassword());
        a.setLogin(aDto.getLogin());
        a.setSubdivision(new Subdivision(aDto.getSubdivision()));
        return a;
    }
}
