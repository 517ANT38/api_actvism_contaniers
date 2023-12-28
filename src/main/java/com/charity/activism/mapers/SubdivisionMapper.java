package com.charity.activism.mapers;

import org.springframework.stereotype.Component;

import com.charity.activism.dto.NewSubdivisionDto;
import com.charity.activism.dto.SubdivisionDto;
import com.charity.activism.models.Subdivision;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class SubdivisionMapper {
    
    private final ActivismUserMapper mapper;

    public Subdivision toEntity(NewSubdivisionDto s){
        return new Subdivision(s.getName());

    }

    public SubdivisionDto toDto(Subdivision s){
        return new SubdivisionDto(
            s.getId(), 
            s.getName(), 
            s.getActivismUsers().stream()
            .map(mapper::toDto).toList());
    }

}
