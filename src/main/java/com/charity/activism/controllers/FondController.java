package com.charity.activism.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charity.activism.dto.FondDto;
import com.charity.activism.dto.IdDto;
import com.charity.activism.dto.SomeFond;
import com.charity.activism.mapers.FondMapper;
import com.charity.activism.services.FondService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fonds")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT})
public class FondController {
    
    private final FondService fService;
    private final FondMapper mapper;

    @GetMapping
    public ResponseEntity<List<FondDto>> getAll(){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(fService.getAll().stream()
                        .map(mapper::toDto)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FondDto> getById(@PathVariable("id") int id){
        return ResponseEntity
                        .ok()
                        .body(mapper.toDto(fService.getById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<FondDto> getByName(@PathVariable("name") String name){
        return ResponseEntity
                        .ok()
                        .body(mapper.toDto(fService.getByName(name)));

    }

    @PutMapping("/save")
    public ResponseEntity<IdDto> save(@RequestBody SomeFond fond){
        return ResponseEntity
                        .ok()
                        .body(new IdDto(fService.save(mapper.toEntity(fond.getFondDto()))));
    }


}
