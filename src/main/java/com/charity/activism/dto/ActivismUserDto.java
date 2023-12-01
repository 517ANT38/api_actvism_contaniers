package com.charity.activism.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ActivismUserDto {
    
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private String password;
    private String subdivision;
}
