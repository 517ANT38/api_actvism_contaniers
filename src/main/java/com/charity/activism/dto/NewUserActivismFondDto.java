package com.charity.activism.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserActivismFondDto {

    private FondDto fondDto;

    private ActivismDto activismDto;

    private Date date;

    private int countHours;

    private boolean done;

    private boolean paid;
}
