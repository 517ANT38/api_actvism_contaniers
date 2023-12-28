package com.charity.activism.services;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.charity.activism.exceptions.SubdivisionNotFoundException;
import com.charity.activism.models.Subdivision;
import com.charity.activism.repositories.SubdivisionRepo;


import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SubdivisionService {

    private final SubdivisionRepo subdivisionRepo;


    public List<Subdivision> getAll(){
        return subdivisionRepo.findAll();
    }
    
    public Subdivision getById(int id){
        return subdivisionRepo.findById(id)
                        .orElseThrow(SubdivisionNotFoundException::new);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public int save(Subdivision s){
        return subdivisionRepo.save(s).getId();
    }
}
