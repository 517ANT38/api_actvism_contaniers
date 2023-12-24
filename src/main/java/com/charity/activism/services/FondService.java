package com.charity.activism.services;

import com.charity.activism.exceptions.FondNotFoundException;
import com.charity.activism.models.Fond;
import com.charity.activism.repositories.FondRepo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FondService {

    private final FondRepo fondRepo;

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
        return fondRepo.save(fond).getId();
    }


}
