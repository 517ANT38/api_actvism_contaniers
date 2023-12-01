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

import com.charity.activism.models.Activism;
import com.charity.activism.services.ActivismService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/activisms")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT})
public class ActivismController {
    
    private final ActivismService aService;

    @GetMapping
    public ResponseEntity<List<Activism>> getAll(){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(aService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activism> getById(@PathVariable("id") int id){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(aService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Activism> getByName(@PathVariable("name") String name){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(aService.getByName(name));
                    
    }

    @PutMapping
    public ResponseEntity<Integer> save(@RequestBody Activism a){
        
        return ResponseEntity.ok().body(aService.save(a));
    }

}
