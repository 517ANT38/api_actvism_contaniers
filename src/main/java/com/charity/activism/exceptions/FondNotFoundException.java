package com.charity.activism.exceptions;

public class FondNotFoundException extends RuntimeException{

    public FondNotFoundException(){
        super("Not found fond");
    }

}
