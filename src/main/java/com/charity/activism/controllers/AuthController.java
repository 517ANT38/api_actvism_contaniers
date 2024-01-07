package com.charity.activism.controllers;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.charity.activism.dto.AuthenticationDto;
import com.charity.activism.dto.TokenDto;
import com.charity.activism.models.Role;
import com.charity.activism.security.JWTUtil;
import com.charity.activism.services.ActivismUserService;
import com.charity.activism.util.ResponseError;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.POST})
public class AuthController {
    
    private final JWTUtil jwtUtil;
    private final ActivismUserService aService;
    private final AuthenticationManager aManager;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> logIn(@RequestBody AuthenticationDto aDto){
        
        var authenticationToken = new UsernamePasswordAuthenticationToken(aDto.getLogin(),aDto.getPassword());
        
        aManager.authenticate(authenticationToken);

        var p = aService.getByLogin(aDto.getLogin());

        var roles = p.getRoles().stream()
                .map(Role::getNameRole)
                .collect(Collectors.joining(" "));

        String jwt = jwtUtil.generateToken(aDto.getLogin(), roles);

        return ResponseEntity.ok().body(new TokenDto(jwt));

    }


    @ExceptionHandler(BadCredentialsException.class)
    private ResponseEntity<ResponseError> handleException(BadCredentialsException ex){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ResponseError("Bad login or password",LocalDateTime.now().toString()));
    }


}
