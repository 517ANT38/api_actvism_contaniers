package com.charity.activism.exceptions;

public class ActivismUserNotFoundException extends RuntimeException{

    public ActivismUserNotFoundException(){
        super("ActivismUser not found");
    }
}
