package com.charity.activism.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FondDto {
    private String url;

    private String token;

    private String name;  
    
    private TypeFondDto typeFond;
}
