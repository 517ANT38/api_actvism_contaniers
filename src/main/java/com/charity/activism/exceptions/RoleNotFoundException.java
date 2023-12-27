package com.charity.activism.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(){
        super("Role not found");
    }
}
