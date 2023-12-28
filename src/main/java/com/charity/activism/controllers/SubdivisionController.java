package com.charity.activism.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charity.activism.dto.IdDto;
import com.charity.activism.dto.NewSubdivisionDto;
import com.charity.activism.dto.SubdivisionDto;
import com.charity.activism.mapers.SubdivisionMapper;
import com.charity.activism.services.SubdivisionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subdivisions")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT})
public class SubdivisionController {
    
    private final SubdivisionService subdivisionService;
    private final SubdivisionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubdivisionDto>> getAll(){
        return ResponseEntity.ok().body(subdivisionService.getAll().stream()
            .map(mapper::toDto)
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubdivisionDto> getById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(mapper.toDto(subdivisionService.getById(id)));
    }

    @PutMapping
    public ResponseEntity<IdDto> save(@RequestBody NewSubdivisionDto s){
        return ResponseEntity.ok().body(new IdDto(subdivisionService.save(mapper.toEntity(s))));
    }

}
