package com.charity.activism.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivismUserDtioOut {
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private String subdivision;
    private String role;
}
