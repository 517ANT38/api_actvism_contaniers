package com.charity.activism.exceptions;

public class SubdivisionNotFoundException extends RuntimeException {
    
    public SubdivisionNotFoundException(){
        super("Subdivision not found");
    }

}
