package com.charity.activism.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charity.activism.exceptions.ActivismNotFoundException;
import com.charity.activism.models.Activism;
import com.charity.activism.repositories.ActivismRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ActivismService {

    private final ActivismRepo activismRepo;

    public List<Activism> getAll(){
        return activismRepo.findAll();
    }

    public Activism getById(int id){
        return activismRepo.findById(id).orElseThrow(ActivismNotFoundException::new);
    }


    public Activism getByName(String name){
        return activismRepo.findByName(name).orElseThrow(ActivismNotFoundException::new);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public int save(Activism a){
        return activismRepo.save(a).getId();
    }

}
