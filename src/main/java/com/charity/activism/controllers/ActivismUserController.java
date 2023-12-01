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

import com.charity.activism.dto.ActivismUserDtioOut;
import com.charity.activism.dto.ActivismUserDto;
import com.charity.activism.mapers.ActivismUserMapper;
import com.charity.activism.services.ActivismUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/activismUsers")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT, RequestMethod.PATCH})
public class ActivismUserController {
    
    private final ActivismUserService aService;
    private final ActivismUserMapper mapper;

    @PutMapping("/save")
    public ResponseEntity<Integer> save(@RequestBody ActivismUserDto aDto){
        var a = mapper.toEntity(aDto);
        return ResponseEntity.ok().body(aService.save(a));

    }
    
    @GetMapping
    public ResponseEntity<List<ActivismUserDtioOut>> getAll(){
        return ResponseEntity.ok().body(aService.getAll().stream().map(mapper::toDto).toList());
    }

    @GetMapping("/subdivision/{id}")
    public ResponseEntity<List<ActivismUserDtioOut>> getAllBySubdivision(@PathVariable("id") int id){
        return ResponseEntity.ok().body(aService.getAllBySubdivision(id).stream()
                                                .map(mapper::toDto)
                                                .toList());
    }

    @GetMapping("/userMaxCountActivism")
    public ResponseEntity<List<ActivismUserDtioOut>> getUserMaxCountActivism(){
        return ResponseEntity.ok().body(aService.getFirst10ActivismUserMaxCountActivism().stream()
                                                .map(mapper::toDto)
                                                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivismUserDtioOut> getById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(mapper.toDto(aService.getById(id)));
    }

    @GetMapping("/{id}/updateRole")
    public ResponseEntity<Integer> updateRole(@PathVariable("id") int id){
        return ResponseEntity.ok().body(aService.updateRoleUser(id));
    }

}
