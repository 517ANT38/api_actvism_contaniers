package com.charity.activism.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date1;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date2;
}
