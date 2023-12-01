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

import com.charity.activism.models.Subdivision;
import com.charity.activism.services.SubdivisionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/subdivisions")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT})
public class SubdivisionController {
    
    private final SubdivisionService subdivisionService;

    @GetMapping
    public ResponseEntity<List<Subdivision>> getAll(){
        return ResponseEntity.ok().body(subdivisionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subdivision> getById(@PathVariable("id") int id){
        return ResponseEntity.ok().body(subdivisionService.getById(id));
    }

    @PutMapping
    public ResponseEntity<Integer> save(@RequestBody Subdivision s){
        return ResponseEntity.ok().body(subdivisionService.save(s));
    }

}
