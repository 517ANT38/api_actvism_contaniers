package com.charity.activism.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivismFondDto {
    
    private int id;

    private FondDto fondDto;

    private ActivismDto activismDto;

    private ActivismUserDtioOut sUserDtioOut;

    private Date date;

    private int countHours;

    private boolean done;

    private boolean paid;
}
