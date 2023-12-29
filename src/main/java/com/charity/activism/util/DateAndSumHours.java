package com.charity.activism.util;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateAndSumHours {
    private Timestamp date;
    private Long countHours;
}
