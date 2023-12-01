package com.charity.activism.exceptions;

public class ActivismNotFoundException extends RuntimeException{

    public ActivismNotFoundException(){
        super("Actinism not found");
    }

}
