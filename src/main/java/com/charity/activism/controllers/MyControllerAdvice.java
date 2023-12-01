package com.charity.activism.controllers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.charity.activism.exceptions.ActivismNotFoundException;
import com.charity.activism.exceptions.ActivismUserNotFoundException;
import com.charity.activism.exceptions.FondNotFoundException;
import com.charity.activism.exceptions.SubdivisionNotFoundException;
import com.charity.activism.exceptions.UserActivismFondNotFoundException;
import com.charity.activism.util.ResponseError;

@ControllerAdvice
public class MyControllerAdvice {
    

    @ExceptionHandler(ActivismNotFoundException.class)
    private ResponseEntity<ResponseError> handleExceotion(ActivismNotFoundException a){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(a.getMessage(),
                        LocalDateTime.now().toString()));
    }


    @ExceptionHandler(SubdivisionNotFoundException.class)
    private ResponseEntity<ResponseError> handleExceotion(SubdivisionNotFoundException a){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(a.getMessage(),
                        LocalDateTime.now().toString()));
    }

    @ExceptionHandler(ActivismUserNotFoundException.class)
    private ResponseEntity<ResponseError> handleExceotion(ActivismUserNotFoundException a){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(a.getMessage(),
                        LocalDateTime.now().toString()));
    }

    @ExceptionHandler(FondNotFoundException.class)
    private ResponseEntity<ResponseError> handleExceotion(FondNotFoundException a){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(a.getMessage(),
                        LocalDateTime.now().toString()));
    }

    @ExceptionHandler(UserActivismFondNotFoundException.class)
    private ResponseEntity<ResponseError> handleExceotion(UserActivismFondNotFoundException a){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseError(a.getMessage(),
                        LocalDateTime.now().toString()));
    }

    

}
