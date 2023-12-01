package com.charity.activism.exceptions;

public class UserActivismFondNotFoundException extends RuntimeException{

    public UserActivismFondNotFoundException(){
        super("UserActivismFond not found");
    }
}
