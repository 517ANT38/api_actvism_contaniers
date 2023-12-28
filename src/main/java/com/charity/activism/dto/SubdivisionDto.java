package com.charity.activism.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubdivisionDto {
    private int id;
    private String name;
    private List<ActivismUserDtioOut> lUserDtioOuts;
}
