package com.charity.activism.services;

import com.charity.activism.exceptions.FondNotFoundException;
import com.charity.activism.models.Fond;
import com.charity.activism.repositories.FondRepo;
import com.charity.activism.repositories.TypeFondRepo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class FondService {

    private final FondRepo fondRepo;
    private final TypeFondRepo tFondRepo;

    public List<Fond> getAll(){
        return fondRepo.findAll();
    }

    public Fond getByName(String name){
        return fondRepo.findByName(name).orElseThrow(FondNotFoundException::new);
    }

    public Fond getById(int id){
        return fondRepo.findById(id)
                .orElseThrow(FondNotFoundException::new);
    }

    @Transactional
    public int save(Fond fond){
        var tf = tFondRepo.findByName(fond.getTypeFond().getName())
            .or(() -> {
                var t = fond.getTypeFond();
                t.setFonds(new ArrayList<>());
                return Optional.of(t);
            }).get();
        tf.getFonds().add(fond);
        return fondRepo.save(fond).getId();
    }


}
