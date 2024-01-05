package com.charity.activism.mapers;

import org.springframework.stereotype.Component;

import com.charity.activism.dto.NewUserActivismFondDto;
import com.charity.activism.dto.UserActivismFondDto;
import com.charity.activism.models.UserActivismFond;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserActivismFondMapper {
    private final ActivismMapper activismMapper;
    private final FondMapper fondMapper;

    public UserActivismFond toEntity(NewUserActivismFondDto dto){
        
        var uaf = new UserActivismFond();
        uaf.setCountHours(dto.getCountHours());
        uaf.setDate(dto.getDate());
        uaf.setPaid(dto.isPaid());
        uaf.setDone(dto.isDone());
        return uaf;
    }
    public UserActivismFondDto toDto(UserActivismFond dto){
        var a = activismMapper.toDto(dto.getActivism());
        var f = fondMapper.toDto(dto.getFond());
        var uaf = new UserActivismFondDto();
        uaf.setActivismDto(a);
        uaf.setFondDto(f);
        uaf.setCountHours(dto.getCountHours());
        uaf.setDate(dto.getDate());
        uaf.setPaid(dto.isPaid());
        uaf.setDone(dto.isDone());
        return uaf;
    }
}
