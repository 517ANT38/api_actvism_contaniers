package com.charity.activism.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.charity.activism.dto.ActivismUserDtioOut;
import com.charity.activism.mapers.ActivismUserMapper;
import com.charity.activism.models.UserActivismFond;
import com.charity.activism.services.UserActivismFondService;
import com.charity.activism.util.DateAndSumHours;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/userActivismFonds")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.POST})
public class UserActivismFondController {
    
    private final UserActivismFondService uService;
    private final ActivismUserMapper mapper;

    @GetMapping("/user/{actUserId}")
    public ResponseEntity<List<UserActivismFond>> getAllByUser(@PathVariable("actUserId") int actUserId){
               
        return ResponseEntity.ok().body(uService.getAllByUser(actUserId));
    }

    @GetMapping("/fond/{fondId}")
    public ResponseEntity<List<UserActivismFond>> getAllByFond(@PathVariable("fondId") int fondId){

        return ResponseEntity.ok().body(uService.getAllByFond(fondId));
    }

    @GetMapping("/activism/{actId}")
    public ResponseEntity<List<UserActivismFond>> getAllByActivism(@PathVariable("actId") int actId){
        
        return ResponseEntity.ok().body(uService.getAllByActivism(actId));
    }

    @GetMapping
    public ResponseEntity<List<UserActivismFond>> getAllByUserAndDate(int actUserId,Date date){

        return ResponseEntity.ok().body(uService.getAllByUserAndDate(actUserId, date));
    }

    @GetMapping("/user/{actUserId}/{id}")
    public ResponseEntity<UserActivismFond> getByUserAndId(@PathVariable("actUserId") int actUserId,@PathVariable("id") int id){

        return ResponseEntity.ok().body(uService.getByUserAndId(actUserId, id));
    }

    @GetMapping("/activism/{actId}/maxCountOurs")
    public ResponseEntity<List<ActivismUserDtioOut>> getFirst10UserByActivismMaxCountOurs(@PathVariable("actId") int actId){

        return ResponseEntity.ok().body(uService.getFirst10UserByActivismMaxCountOurs(actId).stream()
                                                                .map(mapper::toDto).toList());

    }


    @GetMapping("/statisticUserActivismHours/user/{actUserId}")
    public ResponseEntity<List<DateAndSumHours>> getDateAndSumHoursList(@PathVariable("actUserId") int actUserId,
                                                                            @RequestParam Date date1, @RequestParam Date date2)
    {
        return ResponseEntity.ok().body(uService.statisticUserActivismIntervalDate(actUserId, date1, date2));
    }
   
    @PutMapping
    public ResponseEntity<Integer> save(UserActivismFond uFond){
        return ResponseEntity.ok().body(uService.save(uFond));
    }

    @PostMapping
    public ResponseEntity<Double> getByUserSumCharity(int actUserId){

        return ResponseEntity.ok().body(uService.getByUserSumCharity(actUserId));
    }
    
    @PatchMapping("/done/{actUserId}/{id}")
    public ResponseEntity<Integer> updateStatusDone(@PathVariable("actUserId") int actUserId,@PathVariable("id")  int id){
        return ResponseEntity.ok().body(uService.updateStatusDone(actUserId, id));
    }

    @PatchMapping("/paid/{actUserId}/{id}")
    public ResponseEntity<Integer> updateStatusPaid(@PathVariable("actUserId") int actUserId,@PathVariable("id")  int id){
       return ResponseEntity.ok().body(uService.updateStatusPaid(actUserId, id));
    }

}
