package com.charity.activism.controllers;


import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.charity.activism.dto.ActivismUserDtioOut;
import com.charity.activism.dto.DateDto;
import com.charity.activism.dto.IdDto;
import com.charity.activism.dto.NewUserActivismFondDto;
import com.charity.activism.dto.ResDto;
import com.charity.activism.dto.UserActivismFondDto;
import com.charity.activism.mapers.ActivismUserMapper;
import com.charity.activism.mapers.UserActivismFondMapper;
import com.charity.activism.services.UserActivismFondService;
import com.charity.activism.util.DateAndSumHours;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/userActivismFonds")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET,  RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.POST})
public class UserActivismFondController {
    
    private final UserActivismFondService uService;
    private final UserActivismFondMapper mFondMapper;
    private final ActivismUserMapper mapper;

    @GetMapping("/user/{actUserId}")
    public ResponseEntity<List<UserActivismFondDto>> getAllByUser(@PathVariable("actUserId") int actUserId){
               
        return ResponseEntity.ok().body(uService.getAllByUser(actUserId).stream()
            .map(mFondMapper::toDto).toList());
    }

    @GetMapping("/fond/{fondId}")
    public ResponseEntity<List<UserActivismFondDto>> getAllByFond(@PathVariable("fondId") int fondId){

        return ResponseEntity.ok().body(uService.getAllByFond(fondId).stream()
            .map(mFondMapper::toDto).toList());
    }

    @GetMapping("/activism/{actId}")
    public ResponseEntity<List<UserActivismFondDto>> getAllByActivism(@PathVariable("actId") int actId){
        
        return ResponseEntity.ok().body(uService.getAllByActivism(actId).stream()
            .map(mFondMapper::toDto).toList());
    }

    @GetMapping
    public ResponseEntity<List<UserActivismFondDto>> getAllByUserAndDate(int actUserId,Date date){

        return ResponseEntity.ok().body(uService.getAllByUserAndDate(actUserId, date).stream()
            .map(mFondMapper::toDto).toList());
    }

    @GetMapping("/user/{actUserId}/{id}")
    public ResponseEntity<UserActivismFondDto> getByUserAndId(@PathVariable("actUserId") int actUserId,
        @PathVariable("id") int id){

        return ResponseEntity.ok().body(mFondMapper.toDto(uService.getByUserAndId(actUserId, id)));
    }

    @GetMapping("/activism/{actId}/maxCountOurs")
    public ResponseEntity<List<ActivismUserDtioOut>> getFirst10UserByActivismMaxCountOurs(@PathVariable("actId") int actId){

        return ResponseEntity.ok().body(uService.getFirst10UserByActivismMaxCountOurs(actId).stream()
                                                                .map(mapper::toDto).toList());

    }


    @GetMapping("/statisticUserActivismHours/user/{actUserId}")
    public ResponseEntity<List<DateAndSumHours>> getDateAndSumHoursList(@PathVariable("actUserId") int actUserId,
                                                                           DateDto d)
    {
        return ResponseEntity.ok().body(uService.statisticUserActivismIntervalDate(actUserId, d.getDate1(), d.getDate2()));
    }
   
    @PutMapping("/add")
    public ResponseEntity<IdDto> save(@RequestBody NewUserActivismFondDto uFond,
        @PathParam("actUserId") int actUserId, @PathParam("fondId") int fondId, @PathParam("idActi") int actId){
        return ResponseEntity.ok().body(new IdDto((uService.save(mFondMapper.toEntity(uFond),actUserId,fondId,actId))));
    }

    @PostMapping
    public ResponseEntity<ResDto<Double>> getByUserSumCharity(@PathVariable("actUserId") int actUserId){

        return ResponseEntity.ok().body(new ResDto<>(uService.getByUserSumCharity(actUserId)));
    }
    
    @PatchMapping("/done/{actUserId}/{id}")
    public ResponseEntity<IdDto> updateStatusDone(@PathVariable("actUserId") int actUserId,@PathVariable("id")  int id){
        return ResponseEntity.ok().body(new IdDto(uService.updateStatusDone(actUserId, id)));
    }

    @PatchMapping("/paid/{actUserId}/{id}")
    public ResponseEntity<IdDto> updateStatusPaid(@PathVariable("actUserId") int actUserId,@PathVariable("id")  int id){
       return ResponseEntity.ok().body(new IdDto(uService.updateStatusPaid(actUserId, id)));
    }

}
