package com.charity.activism.mapers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.charity.activism.dto.FondDto;
import com.charity.activism.dto.TypeFondDto;
import com.charity.activism.models.Fond;
import com.charity.activism.models.TypeFond;


@Component
public class FondMapper {
    public Fond toEntity(FondDto fDto){
        var f = new Fond();
        f.setName(fDto.getName());
        f.setToken(fDto.getToken());
        var tf = new TypeFond();
        tf.setId(fDto.getTypeFond().getId());
        tf.setFonds(List.of(f));
        tf.setName(fDto.getTypeFond().getName());
        f.setTypeFond(tf);
        return f;
    }    

    public FondDto toDto(Fond f){
        var fd = new FondDto();
        fd.setName(f.getName());
        fd.setToken(f.getToken());
        fd.setUrl(f.getUrl());
        var tf = f.getTypeFond();
        fd.setTypeFond(new TypeFondDto(tf.getId(), tf.getName()));
        return fd;
    }
}
