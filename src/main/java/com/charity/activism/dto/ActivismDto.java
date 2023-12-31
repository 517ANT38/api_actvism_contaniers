package com.charity.activism.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivismDto {
    private int idActivism;

    private String name;

    private double pay;
}
