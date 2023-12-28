package com.charity.activism.mapers;

import org.springframework.stereotype.Component;

import com.charity.activism.dto.ActivismDto;
import com.charity.activism.models.Activism;

@Component
public class ActivismMapper {
    public Activism toEntity(ActivismDto dto){
        var v = new Activism();
        v.setName(dto.getName());
        v.setPay(dto.getPay());
        return v;
    }

    public ActivismDto toDto(Activism a){
        return new ActivismDto(a.getId(), a.getName(), a.getPay());
    }
}
