package com.charity.activism.util;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateAndSumHours {
    private Date date;
    private int countHours;
}
