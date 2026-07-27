package com.anish.basics;

import java.sql.Date;
import java.time.LocalDate;

public class DateConvert {

    static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }

    static void main()
    {
        // Create a sql.Date from a LocalDate
        LocalDate localDate = LocalDate.of(2026, 6, 15);
        Date sqlDate = Date.valueOf(localDate);
        System.out.println("SQL Date: " + sqlDate);
        System.out.println("Converted to LocalDate: " + convertToLocalDateViaSqlDate(sqlDate));
    }
}
