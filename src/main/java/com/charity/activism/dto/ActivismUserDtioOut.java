package com.charity.activism.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivismUserDtioOut {
    private int id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private String subdivision;
    private List<String> roles;
}
