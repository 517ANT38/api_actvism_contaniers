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

import com.charity.activism.dto.IdDto;
import com.charity.activism.models.Fond;
import com.charity.activism.services.FondService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/fonds")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT})
public class FondController {
    
    private final FondService fService;

    @GetMapping
    public ResponseEntity<List<Fond>> getAll(){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(fService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fond> getById(@PathVariable("id") int id){
        return ResponseEntity
                        .ok()
                        .body(fService.getById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Fond> getByName(@PathVariable("name") String name){
        return ResponseEntity
                        .ok()
                        .body(fService.getByName(name));

    }

    @PutMapping("/save")
    public ResponseEntity<IdDto> save(@RequestBody Fond fond){
        return ResponseEntity
                        .ok()
                        .body(new IdDto(fService.save(fond)));
    }


}
